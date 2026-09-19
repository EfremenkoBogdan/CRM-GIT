package crm.contollers;

import enums.ApiEndPoints;
import crm.pojo.request.UserCrmPost;
import config.ApiSpec;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class CrmUserController {

    @Step("Отправка запроса на получение всех юзеров")
    public static ValidatableResponse getAllUsers() {
        return given()
                .spec(ApiSpec.base())
                .queryParam("userType", "internal")
                .queryParam("select", "isActive,emailAddressIsOptedOut,emailAddressIsInvalid,"
                        + "emailAddress,emailAddressData,title,userName,salutationName,"
                        + "firstName,lastName,middleName,name")
                .queryParam("maxSize", 20)
                .queryParam("offset", 0)
                .queryParam("orderBy", "userName")
                .queryParam("order", "asc")
                .get(ApiEndPoints.USERS_CRM.getPath())
                .then();
    }

    @Step("Отправка запроса на создание новых юзеров")
    public static ValidatableResponse postNewUsers (UserCrmPost newUser) {
        return given()
                .spec(ApiSpec.base())
                .body(newUser)
                .post(ApiEndPoints.USERS_CRM.getPath())
                .then();
}
}

