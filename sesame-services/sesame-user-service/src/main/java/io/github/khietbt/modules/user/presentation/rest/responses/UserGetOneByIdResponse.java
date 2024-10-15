package io.github.khietbt.modules.user.presentation.rest.responses;

import io.github.khietbt.modules.user.domain.entities.User;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
@Builder
public class UserGetOneByIdResponse {
    private UUID id;

    private String name;

    private Instant createdAt;

    private Instant updatedAt;

    private String createdBy;

    private String updatedBy;

    private Integer version;

    public static UserGetOneByIdResponse fromDomain(User u) {
        return UserGetOneByIdResponse
                .builder()
                .id(u.getId().getValue())
                .name(u.getName().getValue())
                .createdAt(u.getCreatedAt().getValue())
//                .updatedAt(u.getUpdatedAt().getValue())
//                .createdBy(u.getCreatedBy().getValue())
//                .updatedBy(u.getUpdatedBy().getValue())
//                .version(u.getVersion().getValue())
                .build();
    }
}
