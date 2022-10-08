package beyondata;


import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/topics/")
public class GithubRepositoryTopicsResource {

    @ConfigProperty(name = "github.token")
    String token;

    @GET
    @Path("/{owner}/{repo}")
    @Produces(MediaType.TEXT_PLAIN)
    public Uni<List<String>> index(String owner,  String repo) {
        // TODO try to implement this method to pass test case GithubRepositoryTopicsResourceTest
        throw new UnsupportedOperationException();
    }
}