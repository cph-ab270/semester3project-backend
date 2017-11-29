package api;

import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.notNullValue;

/**
 * Created by adam on 27/11/2017.
 */
public class LocationTest extends FunctionalTest{
    @Test
    public void testSuccessfulGetAllLocations() {

        given()
                .contentType("application/json")
                .when().get("/locations").then()
                .body("id",hasItems(1,2));
    }

    @Test
    public void testSuccessfulGetSingleLocation() {

        given()
                .contentType("application/json")
                .when().get("/locations/1")
                .then()
                .body("id", equalTo(1))
                .body("title", equalTo("some location"));
    }

    @Test
    public void testSuccessfulAddNewLocation() throws FileNotFoundException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("download.jpg").getFile());

        String token = getAuthToken();
        given()
                .multiPart("file", file)
                .multiPart("title", "Title")
                .multiPart("description", "Great environment")
                .multiPart("latitude", 60.0)
                .multiPart("longitude", 10.0)
                .header("Authorization","Bearer "+token)
                .when().post("/locations").then()
                .body("id", notNullValue())
                .body("description", equalTo("Great environment"))
                .body("title", equalTo("Title"))
                .body("imageUrl", endsWith("download.jpg"))
                .body("latitude", is(60f))
                .body("longitude", is(10f));

    }
}
