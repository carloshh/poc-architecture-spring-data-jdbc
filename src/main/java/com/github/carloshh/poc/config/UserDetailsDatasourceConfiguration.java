package com.github.carloshh.poc.config;

import com.github.carloshh.poc.repository.details.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.core.convert.DataAccessStrategy;
import org.springframework.data.jdbc.core.convert.DefaultDataAccessStrategy;
import org.springframework.data.jdbc.core.convert.JdbcConverter;
import org.springframework.data.jdbc.core.convert.SqlGeneratorSource;
import org.springframework.data.jdbc.core.mapping.JdbcMappingContext;
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.data.relational.core.dialect.Dialect;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableJdbcRepositories(basePackageClasses = UserDetailsRepository.class,
                            jdbcOperationsRef  = "userDetailsJdbcOperations",
                            dataAccessStrategyRef = "userDetailDataAccessStrategy",
                            transactionManagerRef = "userDetailsTransactionManager")
public class UserDetailsDatasourceConfiguration extends AbstractJdbcConfiguration {

    @Bean(name = "userDetailsDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.details")
    DataSource dataSourceUserDetails() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "userDetailsJdbcOperations")
    NamedParameterJdbcOperations userDetailsJdbcOperations(@Qualifier("userDetailsDataSource") DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }

    @Bean
    DataAccessStrategy userDetailDataAccessStrategy(@Qualifier("userDetailsJdbcOperations") NamedParameterJdbcOperations operations, JdbcConverter jdbcConverter, JdbcMappingContext context, Dialect dialect) {
        return new DefaultDataAccessStrategy(new SqlGeneratorSource(context, jdbcConverter, dialect), context, jdbcConverter, operations);
    }

    @Bean
    PlatformTransactionManager userDetailsTransactionManager(@Qualifier("userDetailsDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

}
