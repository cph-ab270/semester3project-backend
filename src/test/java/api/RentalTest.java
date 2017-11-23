package api;

import org.junit.Test;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;

public class RentalTest {


    @Test
    public void testSuccessfulGetAllRentals() {

        given()
                .contentType("application/json")
                .when().get("/rentals").then()
                .body("id", greaterThan("1"))
                .body("city", equalTo("city"))
                .body("zip", equalTo("zip"))
                .body("address", equalTo("address"))
                .body("description", equalTo("description"));
    }

    @Test
    public void testSuccessfulGetSingleRental() {

        given()
            .contentType("application/json")
            .when().get("/rentals/1")
                .then()
                .body("id", equalTo("1"))
                .body("city", equalTo("city"))
                .body("zip", equalTo("zip"))
                .body("address", equalTo("address"))
                .body("description", equalTo("description"))
                .body("rating", equalTo("5"))
                .body("image_url", equalTo("url"));

    }

    /*@Test
    public void testSuccessfulAddNewRental() throws FileNotFoundException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("download.jpg").getFile());

        given()
                .multiPart("file",file)
                .multiPart("city","CPH")
                .multiPart("zip", "2500")
                .multiPart("address", "Kjeldsgårdsvej 27C")
                .multiPart("description", "Great environment")
                .when().post("/rentals").then()
                .body("id", notNullValue())
                .body("city", equalTo("CPH"))
                .body("zip", equalTo("2500"))
                .body("address", equalTo("Kjeldsgårdsvej 27C"))
                .body("description", equalTo("Great environment"))
                .body("image_url", equalTo("download.jpg")); */


    }


