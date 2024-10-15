package io.github.khietbt.modules.user.infrastructure.keycloak.clients;

import io.github.khietbt.modules.user.infrastructure.keycloak.configurations.KeycloakClientConfiguration;
import io.github.khietbt.modules.user.infrastructure.keycloak.models.KeycloakUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "keycloakUserClient",
        url = "${KEYCLOAK_SERVER}",
        configuration = KeycloakClientConfiguration.class
)
public interface KeycloakUserClient {
    @GetMapping("/admin/realms/{realm}/users/{userId}")
    KeycloakUser getOne(
            @PathVariable(value = "realm") String realm,
            @PathVariable(value = "userId") String userId
    );
}
