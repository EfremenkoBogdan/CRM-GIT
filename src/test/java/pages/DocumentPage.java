package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;

import java.io.File;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class DocumentPage {

    private SelenideElement downloadBtn = $x("//td[@data-name='file']//a[contains (@title, '.pdf')]");
    private SelenideElement createDocumentBtn = $x ("//a[@data-name = 'quickCreate']");
    private SelenideElement attachFileBtn  = $("input[type='file']");
    private SelenideElement saveBtn = $x("//button[@data-name = 'save']");


    @Step ("Скачиваем документ")
    public File clickDownloadBtn() {
        File file =  downloadBtn.should(Condition.enabled).download();
        Assertions.assertNotNull(file, "Файл не скачался");
        Assertions.assertTrue(file.length() > 0, "Файл пустой");
        return file;
    }

    @Step ("Нажать на кнопку - Create Document")
    public DocumentPage clickCreateDocumentBtn() {
        createDocumentBtn.shouldBe(visible);
        createDocumentBtn.click();
        return this;
    }

    @Step ("Прикрепить файл")
    public DocumentPage attachFile(File file) {
        attachFileBtn.should(Condition.enabled).uploadFile(file);
        return this;
    }


    @Step ("Нажать на кнопку - Save")
    public DocumentPage clickSaveBtn() throws InterruptedException {
        saveBtn.shouldBe(visible);
        sleep(100);
        saveBtn.click();
        return this;
    }
}
