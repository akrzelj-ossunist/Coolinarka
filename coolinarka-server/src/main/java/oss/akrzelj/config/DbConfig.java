package oss.akrzelj.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;

@Configuration
public class DbConfig {

    private static final Logger logger = LoggerFactory.getLogger(DbConfig.class);

    @Bean
    public FlywayMigrationStrategy cleanMigrationStrategy() {
        return library -> {
            library.repair();
            library.migrate();
        };
    }
}