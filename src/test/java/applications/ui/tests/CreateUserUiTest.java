package applications.ui.tests;

import annotations.Run;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import applications.ui.pages.LoginPage;
import applications.ui.pages.MainPage;
import applications.ui.pages.UserPage;
import applications.ui.pages.UsersListPage;
import ru.testit.annotations.ExternalId;
import ru.testit.annotations.Title;
import ru.testit.annotations.WorkItemId;

import java.io.File;

import static com.codeborne.selenide.Selenide.open;
import static utils.RandomUtils.FAKER;

@Slf4j
public class CreateUserUiTest extends BaseUiTest {

    private final File file = new File("src/test/resources/data/777.jpg");
    private final String userName = FAKER.internet().username();
    private final String newPassword = FAKER.internet().password();
    private final String firstName = FAKER.name().firstName();
    private final String lastName = FAKER.name().lastName();
    private final String gender = "Mr.";

    @Test
    @ExternalId("АТ-56")
    @WorkItemId("56")
    @Run
    @Order(9)
    @DisplayName("Создание нового пользователя")
    @Title("API")
    @ru.testit.annotations.DisplayName("Создание нового пользователя")
    public void CreateNewUser() {
        log.info("Процесс создания нового пользователя");
        open("", LoginPage.class)
                .setLogin(login)
                .setPassword(password)
                .pressButtonWithoutError();

        open("#User/create", UserPage.class)
                .setUserName(userName)
                .setFirstName(firstName)
                .setLastName(lastName)
                .setGender(gender)
                .attachFile(file)
                .setPassword(newPassword)
                .pressSaveBtn();

        open("#User", UsersListPage.class)
                .checkUserFromRow(userName);

        open("", MainPage.class)
                .pressDownToggle()
                .pressLogout();

        open("", LoginPage.class)
                .setLogin(userName)
                .setPassword(newPassword)
                .pressButtonWithoutError()
                .pressDownToggle().pressUserName()
                .checkUserName(userName)
                .checkName("%s %s %s".formatted(gender, firstName, lastName));
    }
}