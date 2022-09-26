package beyondata;


import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.util.List;

public class GithubRepositoryTopicsResource {

    @ConfigProperty(name = "github.token")
    String token;

    public Uni<List<String>> index( String owner,  String repo) {
        // TODO try to implement this method to pass test case GithubRepositoryTopicsResourceTest
        throw new UnsupportedOperationException();
    }
}