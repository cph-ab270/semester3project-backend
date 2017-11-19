package api;

import org.junit.Test;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

/**
 * Created by adam on 11/19/2017.
 */
public class HelloWorldTest extends FunctionalTest{
    @Test
    public void testHello() {
        given().when().get("/hello").then().body(containsString("Hello world")).statusCode(200);
    }

    @Test
    public void testHelloUser() {
        given().when().get("/hello/user").then().body(containsString("Hello Adam")).statusCode(200);
    }
}
