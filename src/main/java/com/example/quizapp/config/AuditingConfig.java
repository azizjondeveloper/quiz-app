package com.example.quizapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@EnableJpaAuditing
public class AuditingConfig {
    @Bean
    public AuditorAware<UUID> auditorAware(){
        return new AuditAware();
    }

}
