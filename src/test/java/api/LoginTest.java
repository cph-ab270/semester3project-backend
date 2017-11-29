package api;

import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

/**
 * Created by adam on 22/11/2017.
 */
public class LoginTest extends FunctionalTest {
    @Test
    public void testSuccessfulLogin() {
        Map<String, String> credentials = new HashMap<>();
        credentials.put("username", "User");
        credentials.put("password", "test");

        given()
                .contentType("application/json")
                .body(credentials)
                .when().post("/login").then()
                .body("username", equalTo("User"))
                .body("token",notNullValue());
    }

    @Test
    public void testWrongPassword() {
        Map<String, String> credentials = new HashMap<>();
        credentials.put("username", "Adam");
        credentials.put("password", "wrong");

        given()
                .contentType("application/json")
                .body(credentials)
                .when().post("/login").then()
                .statusCode(400);
    }

    @Test
    public void testWrongUsername() {
        Map<String, String> credentials = new HashMap<>();
        credentials.put("username", "Wrong");
        credentials.put("password", "test");

        given()
                .contentType("application/json")
                .body(credentials)
                .when().post("/login").then()
                .statusCode(400);
    }
}
