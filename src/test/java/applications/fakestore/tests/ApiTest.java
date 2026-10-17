package applications.fakestore.tests;

import annotations.Run;
import applications.api.enums.StatusCode;
import applications.fakestore.controllers.ProductController;
import applications.fakestore.controllers.UserController;
import applications.fakestore.data.CreateProduct;
import applications.fakestore.data.CreateRandomData;
import applications.fakestore.pojo.request.ProductPost;
import applications.fakestore.pojo.response.productList.Product;
import extensions.TestLoggingExtension;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import ru.testit.annotations.ExternalId;
import ru.testit.annotations.Title;
import ru.testit.annotations.WorkItemId;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
@ExtendWith({TestLoggingExtension.class})
public class ApiTest extends BaseApiTest {

    @Test
    @ExternalId("АТ-12")
    @WorkItemId("12")
    @Order(4)
    @Run
    @DisplayName("Проверка конкретного юзера в списке юзеров")
    @Title("API")
    @ru.testit.annotations.DisplayName("Проверка конкретного юзера в списке юзеров")
    public void testGetAllUsers() {
        final String expectedName = "john";
        log.info("Отправляем запрос на получение всех юзеров");
        UserController.getAllUsers()
                .statusCode(StatusCode.OK.getCode())
                .body("", Matchers.hasSize(10))
                .body("name.firstname", Matchers.hasItem(expectedName));

    }

    @Test
    @ExternalId("АТ-52")
    @WorkItemId("52")
    @Order(5)
    @Run
    @DisplayName("Проверка конкретного юзера при получении по id = {id}")
    @Title("API")
    @ru.testit.annotations.DisplayName("Проверка конкретного юзера при получении по id = {id}")
    public void testGetSingleUser() {
        int id = 5;
        log.info("Отправляем запрос на получение юзера по  id = {}", id);
        String city = "san Antonio";
        UserController.getSingleUser(id)
                .statusCode(StatusCode.OK.getCode())
                .body("id", Matchers.equalTo(id))
                .body("address.city", Matchers.equalTo(city))
                .body("address.zipcode", Matchers.matchesPattern("\\d{5}-\\d{4}"));

    }

    @Test
    @ExternalId("АТ-53")
    @WorkItemId("53")
    @Order(6)
    @Run
    @DisplayName("Создание нового юзера")
    @Title("API")
    @ru.testit.annotations.DisplayName("Создание нового юзера")
    public void testPostCreateUser() {
        log.info("Отправляем запрос на создание пользователя");
        UserController.postCreateUser(CreateRandomData.getRandomBodyUserPost())
                .statusCode(StatusCode.CREATED.getCode())
                .body("id", Matchers.notNullValue());
    }

    @Test
    @ExternalId("АТ-54")
    @WorkItemId("54")
    @Order(7)
    @Run
    @DisplayName("Проверка конкретного продукта по описанию в списке продуктов")
    @Title("API")
    @ru.testit.annotations.DisplayName("СПроверка конкретного продукта по описанию в списке продуктов")
    public void testGetAllProducts() {
        log.info("Отправляем запрос на проверку конкретного продукта");
        String expectedDescription = "From our Legends Collection, the Naga was inspired by the mythical water dragon that protects the ocean's pearl. Wear facing inward to be bestowed with love and abundance, or outward for protection.";
        List<Product> productsResponse = ProductController.getAllProduct()
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
    @ExternalId("АТ-55")
    @WorkItemId("55")
    @Order(8)
    @Run
    @DisplayName("Создание нового продукта")
    @Title("API")
    @ru.testit.annotations.DisplayName("Создание нового продукта")
    public void testPostCreateProduct() {
        log.info("Отправляем запрос создание нового продукта");
        ProductPost newProduct = CreateProduct.getProduct();
        String title = newProduct.getTitle();
        ProductController.postCreateProduct(newProduct)
                .statusCode(StatusCode.CREATED.getCode())
                .body("title", Matchers.equalTo(title));
    }
}
