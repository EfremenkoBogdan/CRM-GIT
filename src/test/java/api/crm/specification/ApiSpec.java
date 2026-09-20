package api.crm.specification;

import config.ConfigProvider;
import api.crm.db.CrmDbClient;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;


public class ApiSpec {

    public static RequestSpecification base() {
        return new RequestSpecBuilder()
                .setBaseUri(ConfigProvider.TEST_CONFIGURATION.crmApiUrl())
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .build();
    }

    public static RequestSpecification auth() {
        return new RequestSpecBuilder()
                .addHeader("X-Api-Key", CrmDbClient.getApiKey().getApiKey())
                .setContentType(ContentType.JSON)
                .build();
    }
}
