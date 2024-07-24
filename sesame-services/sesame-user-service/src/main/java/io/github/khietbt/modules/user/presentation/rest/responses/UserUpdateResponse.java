package io.github.khietbt.modules.user.presentation.rest.responses;

import io.github.khietbt.modules.user.domain.entities.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class UserUpdateResponse {
    private UUID id;

    private String name;

    private Instant createdAt;

    private Instant updatedAt;

    private String createdBy;

    private String updatedBy;

    private Integer version;

    public static UserUpdateResponse fromDomain(User u) {
        return UserUpdateResponse
                .builder()
                .id(u.getId().getValue())
                .name(u.getName().getValue())
                .createdAt(u.getCreatedAt().getValue())
                .updatedAt(u.getUpdatedAt().getValue())
                .createdBy(u.getCreatedBy().getValue())
                .updatedBy(u.getUpdatedBy().getValue())
                .version(u.getVersion().getValue())
                .build();
    }
}
