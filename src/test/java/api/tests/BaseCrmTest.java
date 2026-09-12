package api.tests;

import api.controllers.AuthApiController;
import api.enums.StatusCode;
import config.ApiSpec;
import config.ConfigProvider;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import utils.CustomTpl;

import static io.restassured.RestAssured.given;

public abstract class BaseCrmTest {

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = ConfigProvider.TEST_CONFIGURATION.crmApiUrl();
        RestAssured.filters(CustomTpl.customLogFilter().getFilter());

        Response r = given()
                .spec(ApiSpec.base())
                .auth().preemptive().basic(
                        ConfigProvider.TEST_CONFIGURATION.authLogin(),
                        ConfigProvider.TEST_CONFIGURATION.authPassword()
                )
                .get(ConfigProvider.TEST_CONFIGURATION.appUserEndpoint());

        r.then().statusCode(StatusCode.OK.getCode());
        ApiSpec.setAuthToken(r.getHeader("X-Auth-Token"));
    }
}
