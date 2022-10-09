package beyondata;


import beyondata.client.GithubClient;
import io.smallrye.common.annotation.Blocking;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/topics/")
public class GithubRepositoryTopicsResource {
    // 获取topics不需要token，这里拿到了我的token
    @ConfigProperty(name = "github.token")
    String token;

    @RestClient
    GithubClient countriesService;

    @GET
    @Path("/{owner}/{repo}")
    @Produces(MediaType.APPLICATION_JSON)
    @Blocking
    public Uni<List<String>> index(String owner,  String repo) {
        return Uni.createFrom().item(() -> {return countriesService.getTopics(owner, repo).getTopics();});
    }
}