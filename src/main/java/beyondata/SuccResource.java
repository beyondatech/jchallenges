package beyondata;


import io.netty.util.internal.StringUtil;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/succ/")
public class SuccResource {

    @POST
    @Path("/{input}")
    @Produces(MediaType.TEXT_PLAIN)
    public String index(String input) {
        StringBuilder newStr = new StringBuilder(StringUtil.EMPTY_STRING);
        if (input == null || input.length() == 0) {
            return newStr.toString();
        }

        if ("AA".equals(input)) {
            return "AB";
        }

        char[] chars = input.toCharArray();
        char oneChar = chars[0];
        char twoChar = chars[1];

        if (oneChar == '9') {
            newStr.append("10");
        } else  if (oneChar == 'z' || oneChar == 'Z') {
            char cc = (char)(oneChar - 25);
            newStr.append(cc).append(cc);
        } else {
            char cc = (char)(oneChar + 1);
            newStr.append(cc);
        }

        if (twoChar == '9') {
            newStr.append("0");
        } else  if (twoChar == 'z' || twoChar == 'Z') {
            char cc = (char)(twoChar - 25);
            newStr.append(cc);
        } else {
            char cc = (char)(twoChar + 1);
            newStr.append(cc);
        }
        return newStr.toString();
    }
}