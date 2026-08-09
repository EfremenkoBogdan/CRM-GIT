import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.LoginPage;
import pages.MainPage;
import pages.UserSettingPage;

import static com.codeborne.selenide.Selenide.sleep;

public class LoginTest extends BaseTest{

    private final String login = "admin";
    private final String password = "admin";

    @Test
    @DisplayName("Проверка заполнения полей")
    public void testLogin() {
       new LoginPage()
               .openPage()
               //спросить как трекать ошибку если неправильный логин
               .setLogin(login)
               .setPassword(password)
               .pressButton();

       new MainPage().pressDownToggle().pressUserName();

       new UserSettingPage().checkName(login);

    }
}
