package tests;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.LoginPage;

public class LoginTestFailed extends BaseTest{
    private final String login = "admin123";
    private final String password = "admin123";

    @Test
    @DisplayName("Ошибка при входе - Логин и пароль неверные")
    public void testLoginWithError() {

        Selenide.open("", LoginPage.class)
                .setLogin(login)
                .setPassword(password)
                .pressButtonWithError()
                .appearanceAlert();

    }
}
