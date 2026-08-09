import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import config.DriverConfiguration;
import extensions.AllureExtensions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;

import static com.codeborne.selenide.Selenide.sleep;

@ExtendWith(AllureExtensions.class)
public abstract class BaseTest {

    @BeforeAll
    public static void setup() {
        DriverConfiguration.setupDriver();
        Selenide.open(Configuration.baseUrl);
    }

    @AfterAll
    public static void close() {
        Selenide.closeWebDriver();
    }
}

