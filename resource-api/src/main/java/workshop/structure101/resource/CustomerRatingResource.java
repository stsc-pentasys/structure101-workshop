package workshop.structure101.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 * @author Stefan Schulze, PENTASYS AG
 * @since 20.10.2017
 */
@Path("/rating")
public interface CustomerRatingResource {

    @GET
    @Path("{customerId}")
    @Produces(MediaType.APPLICATION_JSON)
    Response getRatingByCustomerId(@PathParam("customerId") String customerId);

    @PUT
    @Path("{customerId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({MediaType.APPLICATION_JSON})
    Response putExistingRating(@PathParam("customerId") String customerId, ModifyRatingRequest request);

    @DELETE
    @Path("{customerId}")
    @Produces(MediaType.APPLICATION_JSON)
    Response deleteExistingRating(@PathParam("customerId") String customerId);

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({MediaType.APPLICATION_JSON})
    Response postNewRating(NewRatingRequest request, @Context UriInfo uriInfo);

}
