package configs;

import org.aeonbits.owner.Config;

@Config.Sources({
        "system:properties",
        "classpath:configs/db.properties"
})
@Config.LoadPolicy(Config.LoadType.MERGE)
public interface DbConfiguration extends Config {

    String dbUrl();

    String dbUser();

    String dbPassword();
}