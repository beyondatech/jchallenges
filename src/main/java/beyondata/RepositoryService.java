package beyondata;

import org.eclipse.microprofile.rest.client.annotation.RegisterClientHeaders;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/repos")
@ApplicationScoped
@RegisterRestClient(configKey = "repository-service")
@RegisterClientHeaders(RequestHeaderFactory.class)

public interface RepositoryService {
    @GET
    @Path("/{owner}/{repo}/topics")
    @Produces(MediaType.APPLICATION_JSON)
    ResponseParam getRepositoryTopic(@PathParam("owner") String owner, @PathParam("repo") String repo);

}
