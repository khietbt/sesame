package io.github.khietbt.modules.user.application.commands;

import io.github.khietbt.modules.user.domain.valueobjects.UserName;
import io.github.khietbt.shared.application.Command;
import io.github.khietbt.shared.domain.valueobjects.AggregateId;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Getter
@SuperBuilder
@ToString(callSuper = true)
public class UserNameClaimDeleteCommand extends Command {
    private final AggregateId aggregateId;

    @TargetAggregateIdentifier
    private final UserName name;

    public UserNameClaimDeleteCommand(AggregateId aggregateId, UserName name) {
        this.aggregateId = aggregateId;
        this.name = name;
    }
}
