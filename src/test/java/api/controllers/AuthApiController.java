package api.controllers;
import api.enums.ApiEndPoints;
import config.ApiSpec;
import config.ConfigProvider;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class AuthApiController {

    @Step("Отправка запроса на авторизацию")
    public static ValidatableResponse getUser() {
        return given()
                .spec(ApiSpec.base())
                .get(ApiEndPoints.AUTH_CRM.getPath())
                .then();
    }


}
