package applications.ui.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;

import java.io.File;
import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;

@Slf4j
public class    DocumentPage {

    private final SelenideElement createDocumentBtn = $x ("//a[@data-name = 'quickCreate']");
    private final SelenideElement attachFileBtn  = $("input[type='file']");
    private final SelenideElement saveBtn = $x("//button[@data-name = 'save']");
    private final SelenideElement nameImp = $("input[data-name='name']");
    private static final String FILE_LINK_XPATH = "//td[@data-name='file']//a[@title='%s']";
    @Step("Проверяем, есть ли документ '{fileName}' в списке")
    public boolean hasDocument(String fileName) {
        return $x(FILE_LINK_XPATH.formatted(fileName))
                .is(Condition.exist, Duration.ofSeconds(3));
    }

    @Step("Скачиваем документ: {fileName}")
    public File clickDownloadBtn(String fileName) {
        SelenideElement downloadLink = $x(
                FILE_LINK_XPATH.formatted(fileName)
        );
        File file = downloadLink.should(Condition.enabled).download();
        Assertions.assertNotNull(file, "Файл не скачался");
        Assertions.assertTrue(file.length() > 0, "Файл пустой");
        return file;
    }

    @Step("Загружаем документ: {file}")
    public DocumentPage uploadDocument(File file) {
        createDocumentBtn.should(Condition.enabled).click();
        attachFileBtn.should(Condition.enabled).uploadFile(file);
        nameImp.should(Condition.value(file.getName()));
        saveBtn.should(Condition.enabled).click();
        return this;
    }

    @Step("Скачиваем документ '{fileName}' (при отсутствии — загружаем)")
    public File ensureAndDownload(String fileName, File localFile) {
        if (!hasDocument(fileName)) {
            log.info("Документ '{}' не найден — загружаем", fileName);
            uploadDocument(localFile);
            // после сохранения мы уже на списке — ждём появления строки
            $x(FILE_LINK_XPATH.formatted(fileName))
                    .shouldBe(Condition.visible, Duration.ofSeconds(15));
        } else {
            log.info("Документ '{}' уже есть — скачиваем", fileName);
        }
        return clickDownloadBtn(fileName);
    }

    @Step ("Нажать на кнопку - Create Document")
    public DocumentPage clickCreateDocumentBtn() {
        createDocumentBtn.click();
        return this;
    }

    @Step ("Прикрепить файл")
    public DocumentPage attachFile(File file) {
       attachFileBtn.should(Condition.enabled).uploadFile(file);
       nameImp.should(Condition.value(file.getName()));
       return this;
    }


    @Step ("Нажать на кнопку - Save")
    public DocumentPage clickSaveBtn()  {
        saveBtn.click();
        return this;
    }

}
