package ui.tests;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ui.pages.LoginPage;

public class LoginTestFailed extends BaseTest{
    private final String loginWithError = "admin123";
    private final String passwordWithError = "admin123";

    @Test
    @DisplayName("Ошибка при входе - Логин и пароль неверные")
    public void testLoginWithError() {

        Selenide.open("", LoginPage.class)
                .setLogin(loginWithError)
                .setPassword(passwordWithError)
                .pressButtonWithError()
                .appearanceAlert();

    }
}
