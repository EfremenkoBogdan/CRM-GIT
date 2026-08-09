package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class UserSettingPage {

    private final SelenideElement userName = $("div[data-name='userName']");
    public UserSettingPage() {
        userName.shouldBe(visible);
    }

    @Step("Проверить, что поле 'expectedName' совпадает с {expectedName}")
    public UserSettingPage checkName (String expectedName) {
        Assertions.assertTrue($("div[data-name='userName']").getText().contains(expectedName),
                "Имя пользователя не совпадает с " + expectedName);
        return this;
    }
}


