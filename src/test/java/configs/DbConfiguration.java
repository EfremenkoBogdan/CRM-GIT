package configs;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:db.properties")
public interface DbConfiguration extends Config {

    String dbUrl();

    String dbUser();

    String dbPassword();
}