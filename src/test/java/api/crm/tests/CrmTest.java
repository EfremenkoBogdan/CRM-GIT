package api.crm.tests;

import api.crm.contollers.AuthApiController;
import api.crm.contollers.CrmUserController;
import docs.fakestore.data.CreateRandomData;
import api.crm.enums.StatusCode;
import api.crm.pojo.request.UserCrmPost;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class CrmTest extends BaseCrmTest{

    @Test
    @DisplayName("Проверка конкретного юзера в списке юзеров")
    public void testGetAllCrmUser() {
        CrmUserController.getAllUsers().log().all()
                .statusCode(StatusCode.OK.getCode())
                .body("list", Matchers.hasSize(20))
                // первый элемент — Admin
                .body("list[0].userName", Matchers.equalTo("admin"))
                .body("list[0].name", Matchers.equalTo("Admin"))
                .body("list[0].type", Matchers.equalTo("admin"))
                .body("list[0].isActive", Matchers.equalTo(true));
    }

    @Test
    @DisplayName("Логин под админом и проверка ответа")
    public void userProfileTest() {
        AuthApiController.getUser()
                .statusCode(StatusCode.OK.getCode())
                .body("user.userName", Matchers.equalTo("admin"))
                .body("user.name", Matchers.equalTo("Admin"))
                .body("user.type", Matchers.equalTo("admin"))
                .body("user.isActive", Matchers.equalTo(true))
                .body("language", Matchers.equalTo("en_US"))
                .body("settings.applicationName", Matchers.equalTo("EspoCRM"))
                .body("settings  .baseCurrency", Matchers.equalTo("USD"))
                .body("settings.currencyList", Matchers.hasItem("USD"))
                .body("settings.version",  Matchers.equalTo("8.4.2"))
                .body("appParams.timeZoneList", Matchers.hasItem("UTC"))
                .body("appParams.maxUploadSize", Matchers.equalTo(30))
                .body("appParams.systemUserId", Matchers.equalTo("system"))
                .body("appParams.auth2FARequired", Matchers.equalTo(false));
    }

    @Test
    @DisplayName("Создание нового пользователя")
    public void testPostCreateNewUser() {
        UserCrmPost newUser = CreateRandomData.getRandomCrmUser();
        CrmUserController.postNewUsers(newUser)
                .statusCode(StatusCode.OK.getCode())
                .body("salutationName", Matchers.equalTo(newUser.getSalutationName()))
                .body("firstName", Matchers.equalTo(newUser.getFirstName()))
                .body("lastName", Matchers.equalTo(newUser.getLastName()));
    }


}
