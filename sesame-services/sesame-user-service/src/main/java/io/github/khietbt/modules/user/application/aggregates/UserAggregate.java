package io.github.khietbt.modules.user.application.aggregates;

import io.github.khietbt.modules.user.application.commands.UserCreateCommand;
import io.github.khietbt.modules.user.domain.events.UserCreatedEvent;
import io.github.khietbt.modules.user.domain.events.UserNameClaimRequestedEvent;
import io.github.khietbt.modules.user.domain.exceptions.UserAlreadyExistsException;
import io.github.khietbt.modules.user.domain.repositories.UserRepository;
import io.github.khietbt.modules.user.domain.valueobjects.UserName;
import io.github.khietbt.shared.domain.valueobjects.AggregateId;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Slf4j
public class UserAggregate {
    @AggregateIdentifier
    private AggregateId aggregateId;

    private UserName name;

    @CommandHandler
    public UserAggregate(
            UserCreateCommand command,
            UserRepository userRepository
    ) {
        if (userRepository.exists(command.getName())) {
            throw new UserAlreadyExistsException(command.getName());
        }

        AggregateLifecycle.apply(
                UserCreatedEvent
                        .builder()
                        .aggregateId(command.getAggregateId())
                        .name(command.getName())
                        .build()
        );

        AggregateLifecycle.apply(
                UserNameClaimRequestedEvent
                        .builder()
                        .aggregateId(command.getAggregateId())
                        .name(command.getName())
                        .build()
        );
    }

    @EventSourcingHandler
    public void on(UserCreatedEvent event) {
        this.name = event.getName();
        this.aggregateId = event.getAggregateId();
    }
}
