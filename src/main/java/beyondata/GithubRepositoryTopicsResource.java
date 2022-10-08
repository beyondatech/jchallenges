package beyondata;


import beyondata.client.GithubClient;
import beyondata.response.Topics;
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
    @Inject
    GithubClient countriesService;

    @GET
    @Path("/{owner}/{repo}")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<List<String>> index(String owner,  String repo) {
        //https://api.github.com/repos/beyondatech/jchallenges/topics
        Topics topics = countriesService.getTopics(owner, repo);
        return Uni.createFrom().item(topics.getNames());
    }
}