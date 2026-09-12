package api.controllers;

import api.enums.ApiEndPoints;
import api.pojo.request.ProductPost;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

public class ProductController
{

    @Step("Отправка запроса на получение всех продуктов")
    public static ValidatableResponse getAllProduct () {
        return RestAssured.given()
                .log().all()
                .get(ApiEndPoints.PRODUCTS.getPath())
                .then();
    }

    @Step("Отправка запроса на создание нового продукта")
    public static ValidatableResponse postCreateProduct ( ProductPost newProduct) {
                return RestAssured.given()
                .contentType(ContentType.JSON)
                .log().all()
                .body(newProduct)
                .post(ApiEndPoints.PRODUCTS.getPath())
                .then();
    }
}
