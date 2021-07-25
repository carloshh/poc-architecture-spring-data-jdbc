package com.github.carloshh.poc.config;

import com.github.carloshh.poc.domain.UserDetails;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.relational.core.mapping.event.BeforeSaveEvent;

import java.util.UUID;

@Configuration
public class PersistenceConfig {

    @Bean
    public ApplicationListener<BeforeSaveEvent> idGenerator() {
        return event -> {
            var entity = event.getEntity();
            if (entity instanceof UserDetails) {
                ((UserDetails) entity).setId(UUID.randomUUID().toString());
            }
        };
    }

}
