package restdemo.rservice.errors;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Error class used whenever a queried resource isn't found
 */
public class ResourceNotFoundException extends WebApplicationException {
    public ResourceNotFoundException(String errMessage) {
        super(Response.status(Response.Status.BAD_REQUEST).entity(errMessage).type(MediaType.TEXT_PLAIN).build());
    }
}


