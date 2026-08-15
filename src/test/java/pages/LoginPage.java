package pages;


import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class LoginPage {

    private final SelenideElement usernameField = $("#field-userName");
    private final SelenideElement passwordField = $("#field-password");
    private final SelenideElement loginButton = $("button[type='submit']");
    private final SelenideElement exceptionAlert = $x("//div[@id='notification' and contains(., 'Wrong username/password')]");
    public LoginPage() {
        usernameField.shouldBe(visible);
    }


  /*  @Step("Открыть страницу - 'Логин'")
    public LoginPage openPage() {
        open("");
        return Selenide.page(new LoginPage());
    }*/

    @Step ("Заполнить поле - 'Логин'")
    public LoginPage setLogin(String username) {
        usernameField.shouldBe(visible);
        usernameField.setValue(username);
        return this;
    }

    @Step ("Заполнить поле  - 'Пароль'")
    public LoginPage setPassword(String password) {
        passwordField.shouldBe(visible);
        passwordField.setValue(password);
        return this;
    }

    @Step ("Нажать кнопку  - 'Подтвердить'")
    public MainPage pressButtonWithoutError() {
        loginButton.shouldBe(visible);
        loginButton.click();
        return Selenide.page(MainPage.class);
    }

    @Step ("Нажать кнопку  - 'Подтвердить'")
    public LoginPage pressButtonWithError() {
        loginButton.shouldBe(visible);
        loginButton.click();
        return this;
    }

    @Step ("Появляется плашка Ошибка регистрации")
    public LoginPage appearanceAlert() {
        exceptionAlert.shouldBe(visible);
        return this;
    }

}
