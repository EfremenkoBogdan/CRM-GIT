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
import utils.XlsUtils;

import java.io.File;

import static com.codeborne.selenide.Selenide.open;
    @Slf4j
    public class DownloadXlsUiTest extends BaseUiTest {

        private final File xlsFile = new File("src/test/resources/data/statement.xls");

        @Test
        @ExternalId("АТ-62")
        @WorkItemId("62")
        @Order(15)
        @Run
        @DisplayName("Скачивание файла Xls")
        @Title("API")
        @ru.testit.annotations.DisplayName("Скачивание файла Xls")
        public void downloadXls() {
            log.info("Процесс скачивания файла Xls");
            open("", LoginPage.class)
                    .setLogin(login)
                    .setPassword(password)
                    .pressButtonWithoutError();

            File file = open("#Document", DocumentPage.class)
                    .ensureAndDownload("statement.xls", xlsFile);

            String text = XlsUtils.getText(file);
            Assertions.assertTrue(text.contains("сюда. Интернет-банк"));

            String firstPage = XlsUtils.getSheetText(file, "Sheet1");
            Assertions.assertTrue(firstPage.contains("Выписка"));

            String thirdRowFirstColumnText = XlsUtils.getCellValue(file, "Sheet1", 3, 0);
            Assertions.assertTrue(thirdRowFirstColumnText.contains("Период"));
        }
    }