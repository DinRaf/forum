package com.forum.server.dao.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 15.08.16
 *
 * @author Dinar Rafikov (First Software Engineering Platform)
 * @version 1.0
 */
@Configuration
@ComponentScan(basePackages = "com.forum.server")
public class PersistenceConfig {
//    private static final String DRIVER_CLASS_NAME = "org.postgresql.Driver";
//    private static final String URL = "jdbc:postgresql://localhost:5432/mydb";
//    private static final String USER_NAME = "admin";
//    private static final String PASSWORD = "12345678";
//
//    private static final String DRIVER_CLASS_NAME = "org.postgresql.Driver";
//    private static final String URL = "jdbc:postgresql://localhost:5432/forum_db";
//    private static final String USER_NAME = "admin";
//    private static final String PASSWORD = "12345678";

    private static final String DRIVER_CLASS_NAME = "org.postgresql.Driver";
    private static final String URL = "jdbc:postgresql://127.6.189.130:5432/forums";
    private static final String USER_NAME = "adminit4u8sr";
    private static final String PASSWORD = "CMHqI1DI3PeL";

    @Bean
    public DataSource DataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(DRIVER_CLASS_NAME);
        dataSource.setUrl(URL);
        dataSource.setUsername(USER_NAME);
        dataSource.setPassword(PASSWORD);
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(DataSource());
    }

    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate() {
        return new NamedParameterJdbcTemplate(DataSource());
    }
}
