package beyondata.client;

import beyondata.response.Topics;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@RegisterRestClient(configKey = "github-client")
public interface GithubClient {
    @GET
    @Path("/repos/{owner}/{repo}")
    Topics getTopics(@PathParam("owner") String owner, @PathParam("repo") String repo);
}
