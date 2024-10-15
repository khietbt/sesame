package io.github.khietbt.modules.user.infrastructure.keycloak.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.khietbt.modules.user.domain.entities.User;
import io.github.khietbt.modules.user.domain.valueobjects.UserCreatedAt;
import io.github.khietbt.modules.user.domain.valueobjects.UserId;
import io.github.khietbt.modules.user.domain.valueobjects.UserName;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
public class KeycloakUser {
    private String id;

    private String username;

    @JsonProperty("createdTimestamp")
    private Long createdAt;

    public User toDomain() {
        return User
                .builder()
                .id(new UserId(UUID.fromString(id)))
                .name(new UserName(username))
                .createdAt(new UserCreatedAt(Instant.ofEpochMilli(createdAt)))
                .build();
    }
}
