package api;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import org.testng.annotations.BeforeClass;

import java.util.HashMap;
import java.util.Map;

import static com.jayway.restassured.RestAssured.given;

/**
 * Created by adam on 11/19/2017.
 */
public abstract class FunctionalTest {

    @BeforeClass
    public static void setup() {
        String port = System.getProperty("server.port");
        if (port == null) {
            RestAssured.port = 8080;
        }
        else{
            RestAssured.port = Integer.valueOf(port);
        }


        String basePath = System.getProperty("server.base");
        if(basePath==null){
            basePath = "/api/";
        }
        RestAssured.basePath = basePath;

        String baseHost = System.getProperty("server.host");
        if(baseHost==null){
            baseHost = "http://localhost";
        }
        RestAssured.baseURI = baseHost;

    }

    protected String getAuthToken() {
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
