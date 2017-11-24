package api;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;

/**
 * Created by adam on 11/22/2017.
 */
public class RegisterTest extends FunctionalTest {

    @Test
    public void testSuccessfulRegister() {
        Map<String, String> credentials = new HashMap<>();
        credentials.put("username", "Patrick");
        credentials.put("password", "test");

        given()
                .contentType("application/json")
                .body(credentials)
                .when().post("/register").then()
                .body("id", greaterThan(1))
                .body("username", equalTo("Patrick"));
    }

    @Test
    public void testDuplicateUsername() {
        Map<String, String> credentials = new HashMap<>();
        credentials.put("username", "Adam");
        credentials.put("password", "test");

        given()
                .contentType("application/json")
                .body(credentials)
                .when().post("/register").then()
                .statusCode(400);
    }
}
