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
        if (input == null || "".equals(input) || input.length() != 2) {
            return input;
        }
        char l = input.charAt(0);
        char r = input.charAt(1);
        if (!Character.isLetterOrDigit(l) || !Character.isLetterOrDigit(r)) {
            return input;
        }
        boolean rightIsNum = Character.isDigit(r);
        boolean rightIsUpperCase = Character.isUpperCase(r);
        String left;
        String right;
        boolean overflow = false;
        if (rightIsNum) {
            int rr = Character.getNumericValue(r) + 1;
            if (rr > 9) {
                overflow = true;
                rr = 0;
            }
            right = rr + "";
        } else {
            char rr;
            if (r == 'Z' || r == 'z') {
                overflow = true;
                rr = rightIsUpperCase ? 'A' : 'a';
            } else {
                rr = (char) (r + 1);
            }
            right = rr + "";
        }
        if (overflow) {
            if (Character.isDigit(l)) {
                left = Character.getNumericValue(l) + 1 + "";
            } else {
                if (l == 'Z' || l == 'z') {
                    left = Character.isUpperCase(l) ? "AA" : "aa";
                } else {
                    left = (char) (l + 1) + "";
                }
            }
        } else {
            left = l + "";
        }
        return left + right;
    }
}