package io.github.khietbt.modules.user.application.commands;

import io.github.khietbt.modules.user.domain.valueobjects.UserName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class UserCreateCommand {
    private UserName name;
}
