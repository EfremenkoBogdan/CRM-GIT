package applications.ui.tests;

import annotations.Run;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import applications.ui.pages.DocumentPage;
import applications.ui.pages.LoginPage;
import ru.testit.annotations.ExternalId;
import ru.testit.annotations.Title;
import ru.testit.annotations.WorkItemId;
import utils.PDFUtils;

import java.io.File;
import java.io.IOException;

import static com.codeborne.selenide.Selenide.open;

@Slf4j
public class DownloadPdfUiTest extends BaseUiTest {

    @Test
    @ExternalId("АТ-61")
    @WorkItemId("61")
    @Order(14)
    @Run
    @DisplayName("Скачивание файла PDF")
    @Title("API")
    @ru.testit.annotations.DisplayName("Скачивание файла PDF")
    public void downloadPDF() throws IOException {
        log.info("Процесс скачивание файла PDF");
        open("", LoginPage.class)
                .setLogin(login)
                .setPassword(password)
                .pressButtonWithoutError();

        File file = open("#Document", DocumentPage.class)
                .clickDownloadBtn("selenide-file-xls-pdf.pdf"); // указываем имя PDF-файла

        Assertions.assertTrue(PDFUtils.getText(file).contains("2. Скачивание файла через Selenide"));
        Assertions.assertEquals(9, PDFUtils.getCountPage(file));
    }
}