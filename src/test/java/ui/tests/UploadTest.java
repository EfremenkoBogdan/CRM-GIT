package ui.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ui.pages.DocumentPage;
import ui.pages.LoginPage;

import java.io.File;

import static com.codeborne.selenide.Selenide.open;

public class    UploadTest extends BaseTest {


    private final File file = new File("src/test/resources/selenide-file-xls-pdf.pdf");

    @Test
    @DisplayName("Загрузка файла PDF")
    public void uploadPDF () throws InterruptedException {
        open("", LoginPage.class)
                .setLogin(login)
                .setPassword(password)
                .pressButtonWithoutError();
        open("#Document", DocumentPage.class)
                .clickCreateDocumentBtn().attachFile(file)
                .clickSaveBtn();
    }
}
