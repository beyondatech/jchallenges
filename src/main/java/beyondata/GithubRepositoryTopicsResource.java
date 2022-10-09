package beyondata;


import io.quarkus.logging.Log;
import io.smallrye.mutiny.Uni;
import io.vertx.core.MultiMap;
import io.vertx.core.http.HttpClientOptions;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.RequestOptions;
import io.vertx.core.json.JsonArray;
import io.vertx.mutiny.core.Vertx;
import io.vertx.mutiny.core.http.HttpClient;
import io.vertx.mutiny.core.http.HttpClientRequest;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Path("/topics")
public class GithubRepositoryTopicsResource {

    static Vertx vertx;
    static HttpClient httpClient;
    @ConfigProperty(name = "github.token")
    String token;


    @Path("/{owner}/{repo}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<List<String>> index(@PathParam("owner") String owner, @PathParam("repo") String repo) {
        RequestOptions options = new RequestOptions();
        options.setMethod(HttpMethod.GET);
        options.setAbsoluteURI("https://api.github.com/repos/" + owner + "/" + repo);
        options.setHeaders(MultiMap.caseInsensitiveMultiMap()
                .add("Bearer", token)
                .add("Accept", "application/vnd.github+json")
                .add("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/106.0.0.0 Safari/537.36")
        );
        return httpClient.request(options)
                .subscribeAsCompletionStage()
                .thenApply(HttpClientRequest::send)
                .join()
                .flatMap(response -> response.body().onItem().transform(buffer -> {
                    if (response.statusCode() == 200) {
                        return (List<String>) buffer.toJsonObject().getJsonArray("topics", new JsonArray()).getList();
                    }
                    Log.error(buffer.toString());
                    throw new RuntimeException(response.statusMessage());
                }));
    }


    @PostConstruct
    public void init() {
        if (vertx == null) {
            vertx = Vertx.vertx();
        }
        if (httpClient == null) {
            HttpClientOptions config = new HttpClientOptions();
            config.setIdleTimeout(10);
            config.setIdleTimeoutUnit(TimeUnit.SECONDS);
            config.setSsl(true);
            config.setTrustAll(true);
            httpClient = vertx.createHttpClient(config);
        }
    }

    @PreDestroy
    public void destroy() {
        if (httpClient != null) {
            httpClient.closeAndAwait();
        }
        if (vertx != null) {
            vertx.closeAndAwait();
        }
    }
}