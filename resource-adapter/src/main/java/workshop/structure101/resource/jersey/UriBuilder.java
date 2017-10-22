package workshop.structure101.resource.jersey;

import workshop.structure101.core.CustomerRating;

import javax.ws.rs.core.UriInfo;
import java.net.URI;

class UriBuilder {
    URI buildURI(CustomerRating rating, UriInfo uriInfo) {
        return uriInfo.getBaseUriBuilder()
            .path(uriInfo.getPath() + "{customerId}")
            .resolveTemplate("id", rating.getCustomerId())
            .build();
    }
}
