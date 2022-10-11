package beyondata;


import com.alibaba.fastjson2.JSONObject;
import io.smallrye.mutiny.Uni;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.ws.rs.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Path("/topics")
public class GithubRepositoryTopicsResource {

    @ConfigProperty(name = "github.token")
    String token;
    @ConfigProperty(name = "github.url")
    String url;

    @GET
    @Path("/{owner}/{repo}")
    public Uni<List<String>> index(@PathParam("owner") String owner, @PathParam("repo") String repo) throws IOException {
        // TODO try to implement this method to pass test case GithubRepositoryTopicsResourceTest

        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .addHeader("Accept","application/vnd.github+json")
                .addHeader("Authorization", "Bearer " + token)
                .url(String.format(url, owner, repo))
                .get()
//                .post(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), ""))
                .build();
        String string = okHttpClient.newCall(request).execute().body().string();
        JSONObject resultJson = JSONObject.parseObject(string);
        if(null != resultJson && resultJson.containsKey("names")){
            List<String> list = resultJson.getList("names", String.class);
            return Uni.createFrom().item(list);
        }
        return Uni.createFrom().item(new ArrayList<>());

//        throw new UnsupportedOperationException();
    }
}