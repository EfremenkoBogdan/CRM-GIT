package ui.tests;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ui.pages.LoginPage;

public class LoginTest extends BaseTest{

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
