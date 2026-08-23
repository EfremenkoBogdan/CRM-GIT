package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import config.DriverConfiguration;
import extensions.AllureExtensions;
import net.datafaker.Faker;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Locale;

import static com.codeborne.selenide.Selenide.open;

@ExtendWith(AllureExtensions.class)
public abstract class BaseTest {
    Faker faker = new Faker(new Locale("eng"));
    String userName = faker.internet().username();
    String newPassword = faker.internet().password();
    String firstName = faker.name().firstName();
    String lastName = faker.name().lastName();
    String gender = "Mr.";

    protected final String login = "admin";
    protected final String password = "admin";

    @BeforeAll
    public static void setup() {
            Selenide.open(Configuration.baseUrl);
    }

    @AfterAll
    public static void close() {
        Selenide.closeWebDriver();
    }
}

