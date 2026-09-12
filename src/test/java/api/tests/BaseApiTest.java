package api.tests;
import config.ConfigProvider;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import utils.CustomTpl;

public abstract class BaseApiTest {

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = ConfigProvider.TEST_CONFIGURATION.baseApiUrl();
        RestAssured.filters(CustomTpl.customLogFilter().getFilter());
    }
}
