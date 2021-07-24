package com.github.carloshh.poc.test;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MSSQLServerContainer;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
public abstract class IntegrationTestContext {

    @Container
    private static final MSSQLServerContainer<?> mssqlserver = new MSSQLServerContainer<>("mcr.microsoft.com/mssql/server:2017-CU12")
            .acceptLicense();

    @Container
    private static final MySQLContainer<?> mysqlUserDetails = new MySQLContainer<>("mysql:8.0.25")
            .withDatabaseName("database-details")
            .withUsername("database-details-username")
            .withPassword("database-details-password")
            .withInitScript("db/schema/init-details-database.sql");

    @DynamicPropertySource
    public static void setDatasourceProperties(final DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.user.jdbcUrl", mssqlserver::getJdbcUrl);
        registry.add("spring.datasource.user.username", mssqlserver::getUsername);
        registry.add("spring.datasource.user.password", mssqlserver::getPassword);

        registry.add("spring.datasource.details.jdbcUrl", mysqlUserDetails::getJdbcUrl);
        registry.add("spring.datasource.details.username", mysqlUserDetails::getUsername);
        registry.add("spring.datasource.details.password", mysqlUserDetails::getPassword);
    }

}
