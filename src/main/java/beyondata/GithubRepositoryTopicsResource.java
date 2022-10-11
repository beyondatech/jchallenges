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
    /**
     * 请更换自己的token
     */
    @ConfigProperty(name = "github.token")
    String token;
    @ConfigProperty(name = "github.url")
    String url;

    @GET
    @Path("/{owner}/{repo}")
    public Uni<List<String>> index(@PathParam("owner") String owner, @PathParam("repo") String repo) throws IOException {
        // TODO try to implement this method to pass test case GithubRepositoryTopicsResourceTest

        OkHttpClient okHttpClient = new OkHttpClient();
        // 创建请求参数
        Request request = new Request.Builder()
                .addHeader("Accept","application/vnd.github+json")
                .addHeader("Authorization", "Bearer " + token)
                .url(String.format(url, owner, repo))
                .get()
//                .post(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), ""))
                .build();
        // 发送请求并获取响应体
        String string = okHttpClient.newCall(request).execute().body().string();
        JSONObject resultJson = JSONObject.parseObject(string);
        // 判断转换后的json对象是否有效且是否包含 names 键
        // 不包含 names 返回空数组
        if(null != resultJson && resultJson.containsKey("names")){
            List<String> list = resultJson.getList("names", String.class);
            return Uni.createFrom().item(list);
        }
        return Uni.createFrom().item(new ArrayList<>());

//        throw new UnsupportedOperationException();
    }
}