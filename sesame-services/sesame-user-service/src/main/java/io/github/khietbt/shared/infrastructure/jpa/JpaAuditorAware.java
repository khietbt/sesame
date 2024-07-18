package io.github.khietbt.shared.infrastructure.jpa;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class JpaAuditorAware implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of("anonymous");
    }
}
