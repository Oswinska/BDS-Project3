package vut.cz.bpcbdsproject3.configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DataSourceConfig {

    private static final Logger logger = LoggerFactory.getLogger(DataSourceConfig.class);

    private static final HikariConfig config = new HikariConfig();
    private static HikariDataSource ds;

    private static final String APPLICATION_PROPERTIES = "login.credentials";

    static {
        try (InputStream resourceStream = DataSourceConfig.class.getClassLoader().getResourceAsStream(APPLICATION_PROPERTIES)) {
            Properties properties = new Properties();
            properties.load(resourceStream);
            config.setJdbcUrl(properties.getProperty("datasource.url"));
            config.setUsername(properties.getProperty("datasource.username"));
            config.setPassword(properties.getProperty("datasource.password"));
            ds = new HikariDataSource(config);
        } catch (IOException | NullPointerException | IllegalArgumentException e) {
            logger.error("Configuration of the datasource was not successful.", e);
        } catch (Exception e) {
            logger.error("Could not connect to the database.", e);
        }
    }

    private DataSourceConfig() {

    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}