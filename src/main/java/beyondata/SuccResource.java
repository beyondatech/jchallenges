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
        String oneStr = input.substring(0, 1);
        String twoStr = input.substring(1);
        String tranTwoStr = transform(twoStr);
        return tranTwoStr.length() > 1 ? transform(oneStr) + tranTwoStr.substring(1) : oneStr + tranTwoStr;
    }

    /**
     * 字符对应关系
     * @param str
     *  要替换的字符
     * @return
     */
    private String transform(String str){
        switch (str) {
            case "a" : return "b";
            case "9" : return "10";
            case "z" : return "aa";
            case "A" : return "B";
            case "Z" : return "AA";
            default: return StringUtil.EMPTY_STRING;
        }
    }
}