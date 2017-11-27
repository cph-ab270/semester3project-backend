package api;

import org.junit.Test;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;

/**
 * Created by adam on 27/11/2017.
 */
public class LocationTest {
    @Test
    public void testSuccessfulGetAllRentals() {

        given()
                .contentType("application/json")
                .when().get("/locations").then()
                .body("id",hasItems(1,2));
    }

    @Test
    public void testSuccessfulGetSingleRental() {

        given()
                .contentType("application/json")
                .when().get("/locations/1")
                .then()
                .body("id", equalTo(1))
                .body("title", equalTo("some location"));
    }
}
