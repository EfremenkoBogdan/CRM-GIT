package configs;

import org.aeonbits.owner.Config;

@Config.Sources({
        "system:properties",
        "classpath:configs/test.properties"
})
@Config.LoadPolicy(Config.LoadType.MERGE)
public interface TestConfiguration extends Config {
    String baseUiUrl();

    String browser();

    String browserSize();

    Integer pageLoadTimeout();

    String pageLoadStrategy();

    boolean headless();

    boolean screenshots();

    boolean savePageSource();

    Integer timeout();

    String crmApiUrl();

    String baseApiUrl();

    String authLogin();

    String authPassword();
}