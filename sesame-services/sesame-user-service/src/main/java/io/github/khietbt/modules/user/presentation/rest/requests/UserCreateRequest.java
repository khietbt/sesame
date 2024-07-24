package io.github.khietbt.modules.user.presentation.rest.requests;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.github.khietbt.modules.user.application.commands.UserCreateCommand;
import io.github.khietbt.modules.user.domain.valueobjects.UserName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserCreateRequest {
    private String name;

    public UserCreateCommand toCommand() {
        return new UserCreateCommand(new UserName(name));
    }
}
