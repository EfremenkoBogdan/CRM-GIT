package tests;

import com.codeborne.xlstest.XLS;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.DocumentPage;
import pages.LoginPage;
import utils.PDFUtils;
import utils.XlsUtils;

import java.io.File;
import java.io.IOException;

import static com.codeborne.selenide.Selenide.open;

public class DownloadXlsTest extends BaseTest{


    @Test
    @DisplayName("Скачивание файла Xls")
    public void downloadXls() {
        open("", LoginPage.class)
                .setLogin(login)
                .setPassword(password)
                .pressButtonWithoutError();

        File file = open("#Document", DocumentPage.class)
                .clickDownloadBtn();
        String text = XlsUtils.getText(file);
        Assertions.assertTrue(text.contains("сюда. Интернет-банк"));

        String firstPage = XlsUtils.getSheetText(file, "Sheet1");
        Assertions.assertTrue(firstPage.contains("Выписка"));

        String thirdRowFirstColumnText = XlsUtils.getCellValue(file, "Sheet1",3,0);
        Assertions.assertTrue(thirdRowFirstColumnText.contains("Период"));
    }

}
