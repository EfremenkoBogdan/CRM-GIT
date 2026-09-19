package crm.tests;

import crm.db.CrmDbClient;
import crm.dto.ApiUser;
import enums.StatusCode;
import config.ApiSpec;
import config.ConfigProvider;
import extensions.LogTestExtensions;
import extensions.TestLoggingExtension;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import utils.allure.CustomTpl;

import static io.restassured.RestAssured.given;


@Slf4j
@ExtendWith({TestLoggingExtension.class})
public abstract class BaseCrmTest {

    protected static String apiKey;

    @BeforeAll
    public static void setup() {
        log.info("Инициализация тестового окружения");
        RestAssured.baseURI = ConfigProvider.TEST_CONFIGURATION.crmApiUrl();
        RestAssured.filters(CustomTpl.customLogFilter().getFilter());

        ApiUser apiUser = CrmDbClient.getApiKey();
        apiKey = apiUser.apiKey;

        log.info("API-пользователь: user_name='{}', apiKey='{}…'",
                apiUser.userName, mask(apiUser.apiKey));

        given()
                .header("X-Api-Key", apiKey)
                .contentType(ContentType.JSON)
                .when()
                .get(ConfigProvider.TEST_CONFIGURATION.appUserEndpoint())
                .then()
                .statusCode(StatusCode.OK.getCode());

    }

    private static String mask(String key) {
        if (key == null || key.length() < 8) return "***";
        return key.substring(0, 6) + "…" + key.substring(key.length() - 4);
    }
}
