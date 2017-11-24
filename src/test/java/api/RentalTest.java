package api;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.notNullValue;

public class RentalTest extends FunctionalTest {


    @Test
    public void testSuccessfulGetAllRentals() {

        given()
                .contentType("application/json")
                .when().get("/rentals").then()
                .body("id",hasItems(1,2))
                .body("city", hasItems("city", "city"));
    }

    @Test
    public void testSuccessfulGetSingleRental() {

        given()
            .contentType("application/json")
            .when().get("/rentals/1")
                .then()
                .body("id", equalTo(1))
                .body("city", equalTo("city"))
                .body("zip", equalTo("zip"))
                .body("address", equalTo("address"))
                .body("description", equalTo("desc"))
                .body("rating", equalTo(5))
                .body("imageUrl", equalTo("url"));

    }

    @Test
    public void testSuccessfulAddNewRental() throws FileNotFoundException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("download.jpg").getFile());

        given()
                .multiPart("file", file)
                .multiPart("city", "CPH")
                .multiPart("zip", "2500")
                .multiPart("address", "Kjeldsgaardsvej 27C")
                .multiPart("description", "Great environment")
                .when().post("/rentals").then()
                .body("id", notNullValue())
                .body("city", equalTo("CPH"))
                .body("zip", equalTo("2500"))
                .body("address", equalTo("Kjeldsgaardsvej 27C"))
                .body("description", equalTo("Great environment"))
                .body("imageUrl", endsWith("download.jpg"));

        }
    }

