package beyondata;


import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("/succ")
public class SuccResource {

    @POST
    @Path("/{input}")
    public String index(String input) {
        String left = input.substring(0,1);
        String right = input.substring(1);

        right = next(right);
        if (right.length() > 1){
            left = next(left);
            right = right.substring(1);
        }

        return left + right;



    }

    private String next(String str){

        if ("a".equals(str)){
            return "b";
        }
        if ("9".equals(str)){
            return "10";
        }
        if ("z".equals(str)){
            return "aa";
        }
        if ("A".equals(str)){
            return "B";
        }
        if ("Z".equals(str)){
            return "AA";
        }
        return null;

    }
}
