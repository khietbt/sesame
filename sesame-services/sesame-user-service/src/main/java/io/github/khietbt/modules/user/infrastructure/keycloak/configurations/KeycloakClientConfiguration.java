package io.github.khietbt.modules.user.infrastructure.keycloak.configurations;

import feign.Logger;
import feign.RequestInterceptor;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

public class KeycloakClientConfiguration {
    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            var authentication = (JwtAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

            requestTemplate.header(HttpHeaders.AUTHORIZATION, "Bearer " + authentication.getToken().getTokenValue());
        };
    }

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    public ErrorDecoder errorDecoder() {
        return (k, m) -> new RuntimeException("Error in requesting");
    }
}
