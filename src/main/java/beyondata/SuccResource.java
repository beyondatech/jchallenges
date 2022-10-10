package beyondata;


import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/succ")
public class SuccResource {

    @Path("/{input}")
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public String index(@PathParam("input") String input) {
        return transform(input);
    }

    private static String transform(String input) {
        char l = input.charAt(0);
        char r = input.charAt(1);
        char rr = (char) (r + 1);
        String left = l + "";
        String right = rr + "";
        if (!Character.isLetterOrDigit(rr)) {
            if (r == '9') {
                right = "0";
            } else {
                right = (char) (r - 25) + "";
            }
            char ll = (char) (l + 1);
            if (Character.isLetterOrDigit(ll)) {
                left = ll + "";
            } else {
                if (l == '9') {
                    left = "10";
                } else {
                    ll = (char) (l - 25);
                    left = ll + "" + ll;
                }
            }
        }
        return left + right;
    }
}