package fakestore.tests;
import config.ConfigProvider;
import io.restassured.RestAssured;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import utils.allure.CustomTpl;

@Slf4j
public abstract class BaseApiTest {

    @BeforeAll
    public static void setup() {
        log.info("Инициализация тестового окружения");
        RestAssured.baseURI = ConfigProvider.TEST_CONFIGURATION.baseApiUrl();
        RestAssured.filters(CustomTpl.customLogFilter().getFilter());
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }
}
