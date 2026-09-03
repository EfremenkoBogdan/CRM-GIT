package api.tests;

import api.data.CreateProduct;
import api.data.CreateRandomData;
import api.pojo.response.productList.Product;
import api.pojo.response.userPost.User;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ApiTest extends BaseApiTest {

    @Test
    public void testGetAllUsers(){
        List<User> usersResponse =   RestAssured.given()
                .log().all()
                .get("/users")
                .then()
                .log().all()
                .statusCode(200)
                .body("", Matchers.hasSize(10))
                .extract()
                .jsonPath()
                .getList(".", User.class);

        usersResponse.stream().filter(user -> user.getName().getFirstname().equals("jonh"));
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

    @Test
    public void postCreateUser () {
        RestAssured.given()
                .contentType(ContentType.JSON)
                .log().all()
                .body(CreateRandomData.getRandomBodyUserPost())
                .post("/users")
                .then()
                .log().all()
                .statusCode(201)
                .body("id", Matchers.notNullValue());
    }

    @Test
    public void testGetAllProducts(){
        List<Product> productsResponse =  RestAssured.given()
                .log().all()
                .get("/Products")
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .jsonPath()
                .getList(".", Product.class);

        Product foundProduct = productsResponse.stream()
                .filter(product -> product.getDescription()
                        .equals("From our Legends Collection, the Naga was inspired by the mythical water dragon that protects the ocean's pearl. Wear facing inward to be bestowed with love and abundance, or outward for protection."))
                .findFirst()
                .orElse(null);
        assertNotNull(foundProduct);
    }

    @Test
    public void postCreateProduct (){
        String title = "Заголовок";
        RestAssured.given()
                .contentType(ContentType.JSON)
                .log().all()
                .body(CreateProduct.getProduct())
                .post("/Products")
                .then()
                .log().all()
                .statusCode(201)
                .body("title", Matchers.equalTo(title));
    }
}
