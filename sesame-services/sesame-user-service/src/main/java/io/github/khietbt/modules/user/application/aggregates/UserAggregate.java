package io.github.khietbt.modules.user.application.aggregates;

import io.github.khietbt.modules.user.application.commands.UserCreateCommand;
import io.github.khietbt.modules.user.domain.events.UserCreatedEvent;
import io.github.khietbt.modules.user.domain.exceptions.UserAlreadyExistsException;
import io.github.khietbt.modules.user.domain.repositories.UserRepository;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.time.Instant;
import java.util.UUID;

@Aggregate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserAggregate {
    @AggregateIdentifier
    private UUID id;

    @CommandHandler
    public UserAggregate(
            UserCreateCommand command,
            final UserRepository userRepository
    ) {
        var name = command.getName();

        if (userRepository.exists(name)) {
            throw new UserAlreadyExistsException(command.getName());
        }

        var user = userRepository.create(name);

        AggregateLifecycle.apply(
                UserCreatedEvent
                        .builder()
                        .aggregateId(user.getId().getValue())
                        .source(user)
                        .createdAt(Instant.now())
                        .build()
        );
    }

    @EventSourcingHandler
    public void publish(UserCreatedEvent event) {
        this.id = event.getAggregateId();
    }
}
