package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import config.DriverConfiguration;
import extensions.AllureExtensions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;

import static com.codeborne.selenide.Selenide.open;

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

