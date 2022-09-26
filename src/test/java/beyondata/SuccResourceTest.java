package beyondata;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@QuarkusTest
public class SuccResourceTest {

    static Stream<Arguments> cases() {
        return Stream.of(
                arguments("10a", "9z"),
                arguments("aa0", "z9"),
                arguments("b0", "a9"),
                arguments("AAA", "ZZ"),
                arguments("BA", "AZ"),
                arguments("AB", "AA")
        );
    }

    @ParameterizedTest
    @MethodSource("cases")
    public void should_succ(String expected, String input) {
        given()
          .when().post("/succ/{input}", input)
          .then()
             .statusCode(200)
             .body(is(expected));
    }
}