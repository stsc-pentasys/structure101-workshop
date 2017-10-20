package workshop.structure101.resource.jersey;

import java.net.URI;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import workshop.structure101.core.AccountType;
import workshop.structure101.core.CustomerRating;
import workshop.structure101.core.CustomerRatingService;
import workshop.structure101.core.Score;
import workshop.structure101.resource.CustomerRatingResource;
import workshop.structure101.resource.ModifyRatingRequest;
import workshop.structure101.resource.NewRatingRequest;

/**
 * @author Stefan Schulze, PENTASYS AG
 * @since 20.10.2017
 */
public class CustomerRatingResourceBean implements CustomerRatingResource {

    private final CustomerRatingService customerRatingService;

    CustomerRatingResourceBean(CustomerRatingService customerRatingService) {
        this.customerRatingService = customerRatingService;
    }

    @Override
    public Response getRatingByCustomerId(String customerId) {
        return customerRatingService
            .retrieveById(customerId)
            .map(Response::ok)
            .orElse(Response.status(Response.Status.NOT_FOUND))
            .build();
    }

    @Override
    public Response putExistingRating(String customerId, ModifyRatingRequest request) {
        CustomerRating modifiedRating = modifyRequestToCustomerRating(customerId, request);
        return customerRatingService.modifyById(modifiedRating)
            .map(Response::accepted)
            .orElse(Response.status(Response.Status.NOT_FOUND))
            .build();
    }

    private CustomerRating modifyRequestToCustomerRating(String customerId, ModifyRatingRequest request) {
        return new CustomerRating(
            customerId,
            request.getFirstName(),
            request.getLastName(),
            AccountType.valueOf(request.getTypeOfAccount()),
            Score.valueOf(request.getRating())
        );
    }

    @Override
    public Response deleteExistingRating(String customerId, ModifyRatingRequest request) {
        return customerRatingService
            .removeById(customerId)
            .map(Response::accepted)
            .orElse(Response.status(Response.Status.NOT_FOUND))
            .build();
    }

    @Override
    public Response postNewRating(NewRatingRequest request, UriInfo uriInfo) {
        CustomerRating customerRating = newRequestToCustomerRating(request);
        return customerRatingService
            .createNewRating(customerRating)
            .map(cr -> Response.created(buildURI(cr, uriInfo)))
            .orElse(Response.status(Response.Status.CONFLICT))
            .build();
    }

    private URI buildURI(CustomerRating rating, UriInfo uriInfo) {
        return uriInfo.getBaseUriBuilder()
            .path(uriInfo.getPath() + "{customerId}")
            .resolveTemplate("id", rating.getCustomerId())
            .build();
    }

    private CustomerRating newRequestToCustomerRating(NewRatingRequest request) {
        return new CustomerRating(
            request.getCustomerId(),
            request.getFirstName(),
            request.getLastName(),
            AccountType.valueOf(request.getTypeOfAccount()),
            Score.valueOf(request.getRating())
        );
    }
}
