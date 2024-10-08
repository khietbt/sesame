package io.github.khietbt.configurations;

import org.keycloak.representations.adapters.config.PolicyEnforcerConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KeycloakPolicyEnforcerConfiguration {
    @Bean
    @ConfigurationProperties("keycloak.policy-enforcer-config")
    public PolicyEnforcerConfig policyEnforcerConfig() {
        return new PolicyEnforcerConfig();
    }
}
