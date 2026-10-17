package applications.ui.tests;

import annotations.Run;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import applications.ui.pages.DocumentPage;
import applications.ui.pages.LoginPage;
import ru.testit.annotations.ExternalId;
import ru.testit.annotations.Title;
import ru.testit.annotations.WorkItemId;

import java.io.File;

import static com.codeborne.selenide.Selenide.open;
@Slf4j
public class UploadPdfUiTest extends BaseUiTest {

    private final File file = new File("src/test/resources/data/selenide-file-xls-pdf.pdf");

    @Test
    @ExternalId("АТ-59")
    @WorkItemId("59")
    @Order(12)
    @Run
    @DisplayName("Загрузка файла PDF")
    @Title("API")
    @ru.testit.annotations.DisplayName("Загрузка файла PDF")
    public void uploadPDF () {
        log.info("Процесс загрузки файла PDF");
        open("", LoginPage.class)
                .setLogin(login)
                .setPassword(password)
                .pressButtonWithoutError();

        open("#Document", DocumentPage.class)
                .clickCreateDocumentBtn().attachFile(file)
                .clickSaveBtn();
    }
}