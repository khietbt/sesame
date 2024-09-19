package io.github.khietbt.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfiguration {
    final String[] PUBLIC_ENDPOINTS = {
            "/api/v1/sesame-user-service/sessions"
    };

    final String[] DENIED_ENDPOINTS = {
            "/login/**"
    };

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http
                .authorizeExchange(
                        auth -> auth
                                .pathMatchers(DENIED_ENDPOINTS).denyAll()
                                .pathMatchers(PUBLIC_ENDPOINTS).permitAll()
                                .anyExchange().authenticated()
                )
                .oauth2Login(Customizer.withDefaults())
                .oauth2ResourceServer(
                        (oauth2) -> oauth2.jwt(Customizer.withDefaults())
                );

        http.csrf(ServerHttpSecurity.CsrfSpec::disable);

        return http.build();
    }
}
