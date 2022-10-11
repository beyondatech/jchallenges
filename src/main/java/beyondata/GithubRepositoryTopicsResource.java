package beyondata;


import cn.hutool.core.map.MapUtil;
import cn.hutool.http.HttpUtil;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
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
        String getResult = HttpUtil.get(String.format("https://api.github.com/repos/%s/%s/topics", owner,repo));
        Map map = JSON.parseObject(getResult, Map.class);
        ArrayList<String> names = new ArrayList((List)map.get("names"));

        Uni<List<String>> item = Uni.createFrom().item(()->{
            return names;
        });
        return item;
    }

}