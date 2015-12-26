package restdemo.rservice.errors;


import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class ResourceAlreadyExistsException extends WebApplicationException {
    public ResourceAlreadyExistsException(String errMessage) {
        super(Response.status(Response.Status.BAD_REQUEST).entity(errMessage).type(MediaType.TEXT_PLAIN).build());
    }
    
    
}
