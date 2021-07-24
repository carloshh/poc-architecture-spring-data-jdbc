package com.github.carloshh.poc.test;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
public abstract class IntegrationTestContext {

    @Container
    private static final MySQLContainer<?> mysqlUser = new MySQLContainer<>("mysql:8.0.25")
            .withDatabaseName("database-user")
            .withUsername("database-user-username")
            .withPassword("database-user-password");

    @Container
    private static final MySQLContainer<?> mysqlUserDetails = new MySQLContainer<>("mysql:8.0.25")
            .withDatabaseName("database-details")
            .withUsername("database-details-username")
            .withPassword("database-details-password")
            .withInitScript("db/schema/init-details-database.sql");

    @DynamicPropertySource
    public static void setDatasourceProperties(final DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.user.jdbcUrl", mysqlUser::getJdbcUrl);
        registry.add("spring.datasource.user.username", mysqlUser::getUsername);
        registry.add("spring.datasource.user.password", mysqlUser::getPassword);

        registry.add("spring.datasource.details.jdbcUrl", mysqlUserDetails::getJdbcUrl);
        registry.add("spring.datasource.details.username", mysqlUserDetails::getUsername);
        registry.add("spring.datasource.details.password", mysqlUserDetails::getPassword);
    }

}
