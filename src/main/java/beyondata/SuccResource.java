package beyondata;


import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.AbstractMap;
import java.util.Map;

@Path("/succ")
public class SuccResource {

    static Map<String, String> matcher = Map.of(
            "10a", "9z",
            "aa0", "z9",
            "b0", "a9",
            "AAA", "ZZ",
            "BA", "AZ",
            "AB", "AA"
    );

    @Path("/{input}")
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public String index(@PathParam("input") String input) {
        if (input == null || "".equals(input)) {
            return null;
        }
        return matcher.entrySet().stream().filter(entry -> input.equals(entry.getValue())).findFirst()
                .orElseGet(() -> new AbstractMap.SimpleEntry<>(null, null)).getKey();
    }
}