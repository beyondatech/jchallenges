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
        char oneStr = input.charAt(0);
        char twoStr = input.charAt(1);
        String tranTwoStr = transform(twoStr);
        return tranTwoStr.length() > 1 ? transform(oneStr) + tranTwoStr.substring(1, 2) : oneStr + tranTwoStr;
    }

    /**
     * 根据规则转换
     * @param ch
     *      要转换的字符
     * @return
     *      转换后的字符
     */
    private String transform(char ch){
        String str = StringUtil.EMPTY_STRING;
        if (isChar(ch)) {
           str = ch == 'z' || ch == 'Z' ? asciiToString(ch - 25) + asciiToString(ch - 25) : asciiToString(++ch);
        } else if (isNumber(ch)) {
           int num = Character.getNumericValue(ch);
           str = String.valueOf(++num);
        }
        return str;
    }

    private String asciiToString(int ascii) {
        return String.valueOf((char)ascii);
    }

    private boolean isChar(char ch) {
        return inSection(ch, 'a', 'z') || inSection(ch, 'A', 'Z');
    }

    private boolean isNumber(char ch) {
        return inSection(ch, '0', '9');
    }

    private boolean inSection(char ch, char minChar, char maxChar) {
        return ch >= minChar && ch <= maxChar;
    }
}