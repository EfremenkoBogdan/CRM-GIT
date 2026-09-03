package ui.tests;

import com.codeborne.selenide.Selenide;
import config.DriverConfiguration;
import extensions.AllureExtensions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;

import static com.codeborne.selenide.Selenide.open;

@ExtendWith(AllureExtensions.class)
public abstract class BaseUiTest {


    protected final String login = "admin";
    protected final String password = "admin";

    @BeforeAll
    public static void setup() {
        DriverConfiguration.setupDriver();
    }

    @AfterAll
    public static void close() {
        Selenide.closeWebDriver();
    }
}

