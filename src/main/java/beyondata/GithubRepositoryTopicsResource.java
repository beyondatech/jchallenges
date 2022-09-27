package beyondata;


import com.fasterxml.jackson.databind.ObjectMapper;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;


@Path("/topics")
public class GithubRepositoryTopicsResource {

    @ConfigProperty(name = "github.token")
    String token;
    @GET
    @Path("/{owner}/{repo}")

    public Uni<List<String>> index( String owner,  String repo)throws Exception {


        String targetUrl = "https://api.github.com/repos/" + owner + "/" + repo;
        URL url =new URL(targetUrl);
        HttpURLConnection conn = ( HttpURLConnection) url.openConnection();
        conn.addRequestProperty( "Accept","application/vnd.github+json");
        conn.addRequestProperty( "Authorization","Bearer " + token);
        conn.setDoOutput(false);
        conn.setDoInput(true);
        conn.connect();
        InputStream in= conn.getInputStream();

        int count = conn.getContentLength();

        ByteArrayOutputStream byteArrayOutputStream =new ByteArrayOutputStream();
        byte[] buffer = new byte[count];
        for(int len  ; (len =in.read(buffer)) >0;) {
            byteArrayOutputStream.write(buffer, 0, len);
        }

        String result =new String(byteArrayOutputStream.toByteArray(),"utf-8" );

        ObjectMapper objectMapper = new ObjectMapper();
        HashMap resMap = objectMapper.readValue(result, HashMap.class);

        byteArrayOutputStream.flush();

        byteArrayOutputStream.close();

        in.close();

        conn.disconnect();

        return Uni.createFrom().item((List<String>) resMap.get("topics"));

    }
}
