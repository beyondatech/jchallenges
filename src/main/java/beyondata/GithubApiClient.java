package beyondata;

import io.smallrye.mutiny.Uni;
import io.vertx.core.json.JsonObject;
import org.eclipse.microprofile.rest.client.annotation.ClientHeaderParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@RegisterRestClient(configKey="github-api")
@ClientHeaderParam(name = "Bearer", value = "${github.token}")
public interface GithubApiClient {

    @GET
    @Path("/repos/{owner}/{repo}")
    Uni<JsonObject> getRepoInfo(String owner, String repo);
}
