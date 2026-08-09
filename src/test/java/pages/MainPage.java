package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class MainPage {

    private final SelenideElement dropDownToggle = $("#nav-menu-dropdown");
    private final SelenideElement currentUserName =  $("a[href='#User/view/6a7635ab9d37a4bd5']");

    public MainPage() {
        dropDownToggle.shouldBe(visible);
    }

    @Step("Нажать кнопку  - 'Выпадающий список 3 точки'")
    public MainPage pressDownToggle() {
        dropDownToggle.click();
        return this;
    }

    @Step ("Нажать кнопку -'Текущий пользователь'")
    public MainPage pressUserName() {
        currentUserName.click();
        return this;
    }
}
