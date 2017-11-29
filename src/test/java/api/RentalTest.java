package api;

import com.jayway.restassured.response.Response;
import com.nimbusds.jose.JOSEException;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.notNullValue;

public class RentalTest extends FunctionalTest {


    @Test
    public void testSuccessfulGetAllRentals() {

        given()
                .contentType("application/json")
                .when().get("/rentals").then()
                .body("id", hasItems(1, 2))
                .body("city", hasItems("city", "city"));
    }

    @Test
    public void testSuccessfulGetSingleRental() {

        given()
                .contentType("application/json")
                .when().get("/rentals/1")
                .then()
                .body("id", equalTo(1))
                .body("title", equalTo("title"))
                .body("city", equalTo("city"))
                .body("zip", equalTo("13404"))
                .body("address", equalTo("some addr"))
                .body("description", equalTo("desc"))
                .body("imageUrl", equalTo("url"));

    }

    @Test
    public void testSuccessfulAddNewRental() throws FileNotFoundException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("download.jpg").getFile());

        given()
                .multiPart("file", file)
                .multiPart("city", "CPH")
                .multiPart("title", "Title")
                .multiPart("zip", "2500")
                .multiPart("address", "Kjeldsgaardsvej 27C")
                .multiPart("description", "Great environment")
                .multiPart("latitude", 60)
                .multiPart("longitude", 10)
                .when().post("/rentals").then()
                .body("id", notNullValue())
                .body("city", equalTo("CPH"))
                .body("zip", equalTo("2500"))
                .body("address", equalTo("Kjeldsgaardsvej 27C"))
                .body("description", equalTo("Great environment"))
                .body("latitude", equalTo(60f))
                .body("longitude", equalTo(10f))
                .body("rating", equalTo(0))
                .body("imageUrl", endsWith("download.jpg"));

    }

    @Test
    public void testFindNearbyLocationsWithDefaultRadius() throws FileNotFoundException {
        given()
                .contentType("application/json")
                .when().get("/rentals/1/near-locations")
                .then()
                .body("id", hasItems(1));

    }

    @Test
    public void testFindNearbyLocationsWithCustomRadius() throws FileNotFoundException {
        given()
                .contentType("application/json")
                .when().get("/rentals/1/near-locations/400")
                .then()
                .body("id", hasItems(1, 2));

    }

    @Test
    public void testGetRating() throws JOSEException {
        given()
                .contentType("application/json")
                .when().get("/rentals/1/rating/user/1").then()
                .body("rating", equalTo(5));
    }

    @Test
    public void testGetNonExistingRating() throws JOSEException {
        given()
                .contentType("application/json")
                .when().get("/rentals/1/rating/user/2").then()
                .statusCode(404);
    }

    @Test(priority = 1)
    public void testUpdateRating() throws JOSEException {
        String token = getAuthToken();

        Map<String, Integer> data = new HashMap<>();
        data.put("rating", 3);
        given()
                .contentType("application/json")
                .body(data)
                .header("Authorization", "Bearer " + token)
                .when().put("/rentals/1/rating").then()
                .body("id", equalTo(1))
                .body("rating", equalTo(3));
    }

    private String getAuthToken() {
        Map<String, String> credentials = new HashMap<>();
        credentials.put("username", "User");
        credentials.put("password", "test");

        Response response = given()
                .contentType("application/json")
                .body(credentials)
                .when().post("/login").andReturn();

        return response.getBody().jsonPath().get("token");
    }
}


