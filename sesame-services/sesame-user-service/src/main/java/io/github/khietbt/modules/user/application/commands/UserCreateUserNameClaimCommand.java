package io.github.khietbt.modules.user.application.commands;

import io.github.khietbt.modules.user.domain.valueobjects.UserId;
import io.github.khietbt.modules.user.domain.valueobjects.UserName;
import io.github.khietbt.shared.application.Command;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Getter
@SuperBuilder
@ToString(callSuper = true)
public class UserCreateUserNameClaimCommand extends Command {
    private final UserId userId;

    @TargetAggregateIdentifier
    private final UserName userName;
}
