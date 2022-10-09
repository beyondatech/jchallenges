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
        char[] chars = input.toCharArray();
        char oneStr = chars[0];
        char twoStr = chars[1];
        String tranTwoStr = transform(twoStr);
        return tranTwoStr.length() > 1 ? transform(oneStr) + tranTwoStr.substring(1) : oneStr + tranTwoStr;
    }

    /**
     * 字符对应关系
     * @param ch
     *  要替换的字符
     * @return
     */
    private String transform(char ch){
        StringBuilder str = new StringBuilder();
        // 英文字符 a->b b->c ..  z-aa 大写同理
       if ((ch >= 65 && ch <= 90) || (ch >= 97 && ch <= 122)) {
           if (ch == 90 || ch == 122) {
               str.append((char)(ch - 25)).append((char)(ch - 25));
           } else {
               str.append((char)(ch + 1));
           }
       // 数字直接加1
       } else if (ch >= 48 && ch <= 57) {
           Integer integer = Integer.valueOf(String.valueOf(ch));
           str.append(++integer);
       }
       return str.toString();
    }
}