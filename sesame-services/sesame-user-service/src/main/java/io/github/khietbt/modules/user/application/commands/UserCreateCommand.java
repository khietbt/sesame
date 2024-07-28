package io.github.khietbt.modules.user.application.commands;

import io.github.khietbt.modules.user.domain.valueobjects.UserName;
import io.github.khietbt.shared.application.Command;
import io.github.khietbt.shared.domain.valueobjects.AggregateId;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@ToString(callSuper = true)
public class UserCreateCommand extends Command {
    private final UserName name;

    public UserCreateCommand(AggregateId id, UserName name) {
        super(id);

        this.name = name;
    }
}
