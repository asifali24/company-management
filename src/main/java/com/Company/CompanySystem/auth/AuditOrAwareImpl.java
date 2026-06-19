package com.Company.CompanySystem.auth;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class AuditOrAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of("Asif Ali");
    }
}
