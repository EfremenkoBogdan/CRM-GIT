package api.tests;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

public class FirstApiTest  extends BaseApiTest{

    @Test
    public void testGetAllUsers(){
        RestAssured.given()
                .log().all()
                .get("/users")
                .then()
                .log().all()
                .statusCode(200)
                .body("", Matchers.hasSize(10))
                .body("address.geolocation.lat[0]", Matchers.equalTo("-37.3159"));
    }

    @Test
    public void testGetSingleUser (){
        int id = 5;
        String city = "san Antonio";
        RestAssured.given()
                .log().all()
                .pathParam("id", id)
                .get("/users/{id}")
                .then()
                .log().all()
                .statusCode(200)
                .body("id", Matchers.equalTo(id))
                .body("address.city", Matchers.equalTo(city))
                .body("address.zipcode", Matchers.matchesPattern("\\d{5}-\\d{4}"));

    }
}
