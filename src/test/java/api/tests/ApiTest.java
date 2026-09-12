package api.tests;

import api.controllers.ProductController;
import api.controllers.UserController;
import api.data.CreateProduct;
import api.data.CreateRandomData;
import api.enums.StatusCode;
import api.pojo.request.ProductPost;
import api.pojo.response.productList.Product;
import api.pojo.response.userPost.User;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ApiTest extends BaseApiTest {

    @Test
    @DisplayName("Проверка конкретного юзера в списке юзеров")
    public void testGetAllUsers(){
        String expectedName ="john";
        List<User> usersResponse =   UserController.getAllUsers()
                .log().all()
                .statusCode(StatusCode.OK.getCode())
                .body("", Matchers.hasSize(10))
                .extract()
                .jsonPath()
                .getList(".", User.class);

        System.out.println(usersResponse);
        boolean isExist = usersResponse.stream().anyMatch(user -> user.getName().getFirstname().equals(expectedName));
        Assertions.assertTrue(isExist, "User с именем %s не найден".formatted(expectedName));
    }

    @Test
    @DisplayName("Проверка конкретного юзера при получении по id")
    public void testGetSingleUser (){
        int id = 5;
        String city = "san Antonio";
            UserController.getSingleUser(id)
                .log().all()
                .statusCode(StatusCode.OK.getCode())
                .body("id", Matchers.equalTo(id))
                .body("address.city", Matchers.equalTo(city))
                .body("address.zipcode", Matchers.matchesPattern("\\d{5}-\\d{4}"));

    }

    @Test
    @DisplayName("Создание нового юзера")
    public void testPostCreateUser () {
           UserController.postCreateUser(CreateRandomData.getRandomBodyUserPost())
                .log().all()
                .statusCode(StatusCode.CREATED.getCode())
                .body("id", Matchers.notNullValue());
    }

    @Test
    @DisplayName("Проверка конкретного продукта по описанию в списке продуктов")
    public void testGetAllProducts(){

        String expectedDescription = "From our Legends Collection, the Naga was inspired by the mythical water dragon that protects the ocean's pearl. Wear facing inward to be bestowed with love and abundance, or outward for protection.";

        List<Product> productsResponse = ProductController.getAllProduct()
                .log().all()
                .statusCode(StatusCode.OK.getCode())
                .extract()
                .jsonPath()
                .getList(".", Product.class);

        Product foundProduct = productsResponse.stream()
                .filter(product -> product.getDescription()
                        .equals(expectedDescription))
                .findFirst()
                .orElse(null);
        assertNotNull(foundProduct, "Продукт с указанным Description не найден");
    }

    @Test
    @DisplayName("Создание нового продукта")
    public void testPostCreateProduct (){
        ProductPost newProduct = CreateProduct.getProduct();
        String title = newProduct.getTitle();
       ProductController.postCreateProduct(newProduct)
                .log().all()
                .statusCode(StatusCode.CREATED.getCode())
                .body("title", Matchers.equalTo(title));
    }
}
