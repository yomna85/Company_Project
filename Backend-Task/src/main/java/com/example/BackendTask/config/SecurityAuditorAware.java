package com.example.BackendTask.config;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import java.util.Optional;

@Configuration
@Log4j2
public class SecurityAuditorAware implements AuditorAware {
    @Override
    public Optional getCurrentAuditor() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof User) {
            User user = (User) principal;
            log.debug("Current Auditor {}", user.getUsername());
            return Optional.of(user.getUsername());
        } else {
            log.debug("Current Auditor {}", principal);
            return Optional.of(principal);
        }


    }
}

