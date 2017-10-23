package workshop.structure101.resource.jersey;

import java.net.URI;
import javax.ws.rs.core.UriInfo;

import workshop.structure101.core.CustomerRating;

class UriBuilder {
    URI buildURI(CustomerRating rating, UriInfo uriInfo) {
        return uriInfo.getBaseUriBuilder()
            .path(uriInfo.getPath() + "/{customerId}")
            .resolveTemplate("customerId", rating.getCustomerId())
            .build();
    }
}
