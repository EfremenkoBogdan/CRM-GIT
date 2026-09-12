package config;

import org.aeonbits.owner.Config;
@Config.Sources("classpath:test.properties")
public interface TestConfiguration extends Config  {
    String baseUiUrl ();
    String browser ();
    String browserSize ();
    Integer  pageLoadTimeout ();
    String pageLoadStrategy  ();
    boolean headless ();
    boolean screenshots ();
    boolean savePageSource ();
    Integer  timeout();
    String baseApiUrl();
    String crmApiUrl();
    String authLogin();
    String authPassword();
    String appUserEndpoint();
}
