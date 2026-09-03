package api.tests;
import config.ConfigProvider;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public abstract class BaseApiTest {

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = ConfigProvider.TEST_CONFIGURATION.baseApiUrl();
    }
}
