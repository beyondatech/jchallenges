package beyondata;


import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/{owner}/{repo}/topics")
public class GithubRepositoryTopicsResource {

    @RestClient
    RepositoryService repositoryService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<List<String>> index(@PathParam String owner, @PathParam String repo) {
        ResponseParam respParam = repositoryService.getRepositoryTopic(owner, repo);
        return Uni.createFrom().item(respParam.getNames());
    }
}