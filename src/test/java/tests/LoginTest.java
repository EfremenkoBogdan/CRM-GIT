package tests;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.LoginPage;

public class LoginTest extends BaseTest{

    private final String login = "admin";
    private final String password = "admin";

    @Test
    @DisplayName("Проверка заполнения полей")
    public void testLogin() {

        Selenide.open("",LoginPage.class)
               .setLogin(login)
               .setPassword(password)
               .pressButtonWithoutError()
               .pressDownToggle().pressUserName().checkName(login);

    }
}
