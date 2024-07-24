package io.github.khietbt.modules.user.presentation.rest.requests;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.github.khietbt.modules.user.application.commands.UserUpdateCommand;
import io.github.khietbt.modules.user.domain.valueobjects.UserId;
import io.github.khietbt.modules.user.domain.valueobjects.UserName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserUpdateRequest {
    private String name;

    public UserUpdateCommand toCommand(UUID id) {
        return new UserUpdateCommand(
                new UserId(id),
                new UserName(name)
        );
    }
}
