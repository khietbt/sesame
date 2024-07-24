package io.github.khietbt.modules.user.application.commands;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.github.khietbt.modules.user.domain.valueobjects.UserId;
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
public class UserUpdateCommand {
    private UserId id;

    private UserName name;
}
