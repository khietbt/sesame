package io.github.khietbt.modules.user.application.aggregates;

import io.github.khietbt.modules.user.application.commands.UserCreateCommand;
import io.github.khietbt.modules.user.domain.events.UserCreatedEvent;
import io.github.khietbt.modules.user.domain.valueobjects.UserName;
import io.github.khietbt.shared.domain.valueobjects.AggregateId;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserCreateAggregate {
    @AggregateIdentifier
    private AggregateId id;

    private UserName name;

    @CommandHandler
    public UserCreateAggregate(
            UserCreateCommand command
    ) {
        AggregateLifecycle.apply(
                UserCreatedEvent
                        .builder()
                        .aggregateId(command.getAggregateId())
                        .name(command.getName())
                        .build()
        );
    }

    @EventSourcingHandler
    public void publish(UserCreatedEvent event) {
        this.id = event.getAggregateId();
        this.name = event.getName();
    }
}
