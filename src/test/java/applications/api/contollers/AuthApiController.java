package applications.api.contollers;

import applications.api.assertions.AssertableResponse;
import applications.api.enums.ApiEndPoints;
import applications.api.enums.StatusCode;
import applications.api.request.ApiUserPost;
import applications.api.specification.ApiSpec;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import utils.CreateRandomData;

import java.util.List;
import java.util.Map;

import static applications.api.specification.ApiSpec.authAdmin;
import static io.restassured.RestAssured.given;

public class AuthApiController {
    @ru.testit.annotations.Step()
    @Step("Отправка запроса на авторизацию под API юзером")
    public static AssertableResponse getUser() {
        return new AssertableResponse(given()
                .spec(ApiSpec.authApi())
                .get(ApiEndPoints.AUTH_CRM.getPath())
                .then());
    }

    @ru.testit.annotations.Step()
    @Step("Отправка запроса на создание нового API юзера")
    public static String createApiUserAndGetKey() {
        ApiUserPost apiUser = CreateRandomData.getRandomApiUser();

        String roleId = getRoleIdByName("admin");
        apiUser.setRolesIds(List.of(roleId));

        return given()
                .spec(authAdmin())
                .body(apiUser)
                .post(ApiEndPoints.USERS_CRM.getPath())
                .then()
                .statusCode(StatusCode.OK.getCode())
                .extract().path("apiKey");
    }

    @ru.testit.annotations.Step()
    @Step("Создание роли для API юзера")
    private static String getRoleIdByName(String roleName) {
        // 1. Ищем роль
        List<String> ids = given()
                .spec(authAdmin())
                .queryParam("where[0][type]", "equals")
                .queryParam("where[0][attribute]", "name")
                .queryParam("where[0][value]", roleName)
                .get(ApiEndPoints.ROLE_CRM.getPath())
                .then()
                .statusCode(StatusCode.OK.getCode())
                .extract().jsonPath().getList("list.id");

        if (!ids.isEmpty()) {
            return ids.get(0);
        }

        // 2. Если роли нет — создаём её с минимальным набором полей
        System.out.println("=== ROLE NOT FOUND, TRYING TO CREATE: " + roleName + " ===");

        // Оставляем только те поля, которые точно проходят валидацию
        Map<String, Object> newRole = Map.of(
                "name", roleName,
                "assignmentPermission", "all",
                "userPermission", "all",
                "massUpdatePermission", "all",
                "userActionsPermission", "all"
        );

        Response response = given()
                .spec(authAdmin())
                .body(newRole)
                .post(ApiEndPoints.ROLE_CRM.getPath())
                .then()
                .extract().response();

        // Логируем ответ для отладки
        System.out.println("=== CREATE ROLE RESPONSE ===");
        System.out.println("Status: " + response.getStatusCode());
        System.out.println("Body: " + response.getBody().asString());
        System.out.println("============================");

        if (response.getStatusCode() == 200) {
            return response.path("id");
        }

        // Если роль уже создана (409 Conflict), ищем её снова
        if (response.getStatusCode() == 409) {
            List<String> retryIds = given()
                    .spec(authAdmin())
                    .queryParam("where[0][type]", "equals")
                    .queryParam("where[0][attribute]", "name")
                    .queryParam("where[0][value]", roleName)
                    .get(ApiEndPoints.ROLE_CRM.getPath())
                    .then()
                    .statusCode(200)
                    .extract().jsonPath().getList("list.id");

            if (!retryIds.isEmpty()) {
                return retryIds.get(0);
            } else {
                throw new IllegalStateException("Роль была создана, но не найдена при повторном поиске. Имя: " + roleName);
            }
        }

        // Если не 200 и не 409 — выбрасываем ошибку с текстом ответа
        throw new IllegalStateException("Не удалось создать роль. Код: " + response.getStatusCode() + ". Ответ: " + response.getBody().asString());
    }
}
