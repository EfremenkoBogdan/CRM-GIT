package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class MainPage {

    private final SelenideElement dropDownToggle = $("#nav-menu-dropdown");
    private final SelenideElement currentUserName =  $x("//ul[@class ='dropdown-menu']//a[contains(@href, 'User/')]");

    public MainPage() {
        dropDownToggle.shouldBe(visible);
    }

    @Step("Нажать кнопку  - 'Выпадающий список 3 точки'")
    public MainPage pressDownToggle() {
        dropDownToggle.shouldBe(visible);
        dropDownToggle.click();
        return this;
    }

    @Step ("Нажать кнопку -'Текущий пользователь'")
    public UserSettingPage pressUserName() {
        currentUserName.shouldBe(visible);
        currentUserName.click();
        return Selenide.page(UserSettingPage.class);
    }
}
