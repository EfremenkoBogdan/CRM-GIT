package crm.db;
import config.ConfigProvider;
import crm.dto.ApiUser;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CrmDbClient {

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(ConfigProvider.TEST_CONFIGURATION.dbUrl(),
                ConfigProvider.TEST_CONFIGURATION.dbUser(),
                ConfigProvider.TEST_CONFIGURATION.dbPassword());
    }

    /**
     * Получает API-ключ для пользователя с типом 'api'.
     */
    public static ApiUser  getApiKey() {
        String apiKey = null;

        String sql = "SELECT user_name, api_key FROM `user` " +
                "WHERE type = 'api' LIMIT 1 ";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                return new ApiUser(rs.getString("user_name"), rs.getString("api_key"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Не удалось получить API-пользователя из БД", e);
        }
        throw new IllegalStateException("В таблице user нет ни одного API-пользователя с ключом");
    }
}
