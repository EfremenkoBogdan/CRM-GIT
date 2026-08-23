package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.DocumentPage;
import pages.LoginPage;
import pages.MainPage;
import pages.UserPage;

import java.io.File;
import java.util.Locale;
import java.util.Random;

import static com.codeborne.selenide.Selenide.open;

public class CreateUserTest extends BaseTest {

    private final File file = new File("src/test/resources/777.jpg");

/*    Random random = new Random();
    int number = random.nextInt(1000);
    String userName = "TestUser" + number;
    String newPassword = "12345";
    String firstName = "Имя";
    */


    @Test
    @DisplayName("Создание нового пользователя")

    public void CreateNewUser (){
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


        open("", MainPage.class)
                .pressDownToggle()
                .pressLogout();

        open("", LoginPage.class)
                .setLogin(userName)
                .setPassword(newPassword)
                .pressButtonWithoutError()
                .pressDownToggle().pressUserName()
                .checkUserName(userName.toLowerCase(Locale.ROOT))
                .checkName(String.format("%s %s %s", gender, firstName, lastName));
    }

}
