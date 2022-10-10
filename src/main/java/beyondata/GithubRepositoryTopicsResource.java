package beyondata;


import io.quarkus.logging.Log;
import io.smallrye.mutiny.Uni;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.mutiny.core.Vertx;
import io.vertx.mutiny.core.buffer.Buffer;
import io.vertx.mutiny.ext.web.client.WebClient;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.annotation.PreDestroy;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/topics")
public class GithubRepositoryTopicsResource {

    //    private final WebClient webClient;
    @ConfigProperty(name = "github.token")
    String token;
    @RestClient
    GithubApiClient githubApiClient;

//    public GithubRepositoryTopicsResource(Vertx vertx) {
//        this.webClient = WebClient.create(vertx);
//    }

    @Path("/{owner}/{repo}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<List<String>> index(@PathParam("owner") String owner, @PathParam("repo") String repo) {
//        String url = "https://api.github.com/repos/" + owner + "/" + repo;
//        return webClient.getAbs(url).ssl(true)
//                .putHeader("Bearer", token)
//                .putHeader("Accept", "application/vnd.github+json")
//                .putHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/106.0.0.0 Safari/537.36")
//                .send()
//                .onItem()
//                .transform(response -> {
//                    Buffer buffer = response.body();
//                    if (response.statusCode() == 200) {
//                        return (List<String>) buffer.toJsonObject().getJsonArray("topics", new JsonArray()).getList();
//                    }
//                    Log.error(buffer.toString());
//                    throw new RuntimeException(response.statusMessage());
//                });
        return githubApiClient.getRepoInfo(owner, repo).onItem().transform(jsonObject ->
                (List<String>) jsonObject.getJsonArray("topics", new JsonArray()).getList());
    }

//    @PreDestroy
//    public void destroy() {
//        if (webClient != null) {
//            webClient.close();
//        }
//    }
}