package beyondata;


import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;

@Path("/topics")
public class GithubRepositoryTopicsResource {

    @ConfigProperty(name = "github.token")
    String token;

    @GET
    @Path("/{owner}/{repo}")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<List<String>> index(String owner,  String repo) throws Exception {
        // TODO try to implement this method to pass test case GithubRepositoryTopicsResourceTest
//        throw new UnsupportedOperationException();

        String res = null;
        try {
            URL url = new URL("https://api.github.com/repos/" + owner + "/"+ repo+"/topics");
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            if (conn.getResponseCode() == 200){

                InputStream inputStream = conn.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuffer buffer = new StringBuffer();
                String readLine = "";
                while ((readLine = reader.readLine()) != null) {
                    buffer.append(readLine);
                }
                inputStream.close();
                reader.close();
                conn.disconnect();
                res = buffer.toString();
            }
        } catch (Exception e) {
//            logger.error("通过url地址获取文本内容失败 Exception：" + e);
            res = "";
        }
        //转换成map
        Map map = JSON.parseObject(res, Map.class);
        ArrayList<String> names = new ArrayList((List)map.get("names"));

        Uni<List<String>> item = Uni.createFrom().item(()->{
            return names;
        });
        return item;
    }

}