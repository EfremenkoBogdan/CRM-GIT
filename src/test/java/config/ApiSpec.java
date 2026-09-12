package config;
import config.ConfigProvider;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import lombok.*;


public class ApiSpec {
    @Getter
    @Setter
    private static String authToken;

    public static RequestSpecification base() {
        RequestSpecBuilder b = new RequestSpecBuilder()
                .setBaseUri(ConfigProvider.TEST_CONFIGURATION.crmApiUrl())
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .log(LogDetail.ALL)
                .setAuth(RestAssured.preemptive().basic(
                        ConfigProvider.TEST_CONFIGURATION.authLogin(),
                        ConfigProvider.TEST_CONFIGURATION.authPassword()
                ));
        return b.build();
    }
}
