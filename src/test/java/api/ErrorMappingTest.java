package api;

import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;

/**
 * Created by adam on 22/11/2017.
 */
public class ErrorMappingTest extends FunctionalTest {
    @Test
    public void testWrongMethod() {
        given()
                .contentType("application/json")
                .when().post("/rentals/1").then()
                .statusCode(405);
    }

    @Test
    public void testNotExistentResource() {
        given()
                .contentType("application/json")
                .when().get("/thisdoesnexist").then()
                .statusCode(404);
    }
}
