package applications.ui.tests;

import annotations.Run;
import com.codeborne.selenide.Selenide;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import applications.ui.pages.LoginPage;
import ru.testit.annotations.ExternalId;
import ru.testit.annotations.Title;
import ru.testit.annotations.WorkItemId;

@Slf4j
public class LoginUiTest extends BaseUiTest {

    @Test
    @ExternalId("АТ-57")
    @WorkItemId("57")
    @Order(10)
    @Run
    @DisplayName("Проверка заполнения полей")
    @Title("API")
    @ru.testit.annotations.DisplayName("Проверка заполнения полей")
    public void testLogin() {
        log.info("Процесс проверки логина и пароля");
        Selenide.open("",LoginPage.class)
               .setLogin(login)
               .setPassword(password)
               .pressButtonWithoutError()
               .pressDownToggle().pressUserName().checkName(login);
    }
}