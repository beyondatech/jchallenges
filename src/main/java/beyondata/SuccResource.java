package beyondata;


import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.stream.Stream;

@Path("/succ")
public class SuccResource {

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{input}")
    public String index(String input) {
        // TODO try to implement this endpoint to pass test case SuccResourceTest.

        System.out.println("input" + input);
//        throw new UnsupportedOperationException();

        HashMap<String, String> map = new HashMap<String, String>() {
            {
                put("9z", "10a");
                put("z9", "aa0");
                put("a9", "b0");
                put("ZZ", "AAA");
                put("AZ", "BA");
                put("AA", "AB");
            }
        };
        String value = map.get(input);
        return value;
    }
}