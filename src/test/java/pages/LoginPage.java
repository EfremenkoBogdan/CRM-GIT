package pages;


import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class LoginPage {

    private final SelenideElement usernameField = $("#field-userName");
    private final SelenideElement passwordField = $("#field-password");
    private final SelenideElement loginButton = $("button[type='submit']");

    public LoginPage() {
        usernameField.shouldBe(visible);
    }


    @Step("Открыть страницу - 'Логин'")
    public LoginPage openPage() {
        open("");
        return Selenide.page(new LoginPage());
    }

    @Step ("Заполнить поле - 'Логин'")
    public LoginPage setLogin(String username) {
        usernameField.setValue(username);
        return this;
    }

    @Step ("Заполнить поле  - 'Пароль'")
    public LoginPage setPassword(String password) {
        passwordField.setValue(password);
        return this;
    }

    @Step ("Нажать кнопку  - 'Подтвердить'")
    public LoginPage pressButton() {
        loginButton.click();
        return this;
    }

}
