package beyondata;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsInAnyOrder;

@QuarkusTest
public class GithubRepositoryTopicsResourceTest {
    @Test
    void should_get_topics_of_jchallenges() {
        given()
                .when().get("/topics/{owner}/{repo}", "beyondatech", "jchallenges")
                .then()
                .statusCode(200)
                .body("$",containsInAnyOrder("java","quarkus","reactive"));
    }
}