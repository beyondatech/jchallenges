package beyondata;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/succ")
public class SuccResource {

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{input}")
    public String index(@PathParam("input") String input) {
        // TODO try to implement this endpoint to pass test case SuccResourceTest.
        return input;
//        throw new UnsupportedOperationException();
    }
}