package com.example.quizapp.config;

import com.example.quizapp.entity.User;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;
import java.util.UUID;

public class AuditAware implements AuditorAware<UUID> {
    @Override
    public Optional<UUID> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()){
            User principal = (User) authentication.getPrincipal();
            return Optional.of(principal.getId());
        }
        return Optional.empty();
    }
}
