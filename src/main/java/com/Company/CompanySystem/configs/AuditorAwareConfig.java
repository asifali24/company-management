package com.Company.CompanySystem.configs;

import com.Company.CompanySystem.auth.AuditOrAwareImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "getAuditorAwareImpl") //getAuditorAwareImpl is the method name
public class AuditorAwareConfig {

    @Bean
    AuditorAware<String> getAuditorAwareImpl() {
        return new AuditOrAwareImpl();
    }
}
