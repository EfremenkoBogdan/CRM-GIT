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
public class UploadXlsUiTest extends BaseUiTest{

    private final File file = new File("src/test/resources/data/statement.xls");

    @Test
    @ExternalId("АТ-60")
    @WorkItemId("60")
    @Order(13)
    @Run
    @DisplayName("Загрузка файла Xls")
    @Title("API")
    @ru.testit.annotations.DisplayName("Загрузка файла Xls")
    public void uploadXls() {
       log.info("Процесс загрузки файла Xls");
        open("", LoginPage.class)
                .setLogin(login)
                .setPassword(password)
                .pressButtonWithoutError();

        open("#Document", DocumentPage.class)
                .clickCreateDocumentBtn().attachFile(file)
                .clickSaveBtn();
    }
}
