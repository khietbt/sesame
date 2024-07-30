package io.github.khietbt.modules.user.application.aggregates;

import io.github.khietbt.modules.user.application.commands.UserNameValidateCommand;
import io.github.khietbt.modules.user.domain.events.UserNameValidatedEvent;
import io.github.khietbt.modules.user.domain.exceptions.UserAlreadyExistsException;
import io.github.khietbt.modules.user.domain.repositories.UserRepository;
import io.github.khietbt.modules.user.domain.valueobjects.UserName;
import io.github.khietbt.shared.domain.valueobjects.AggregateId;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateCreationPolicy;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.modelling.command.CreationPolicy;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
@NoArgsConstructor
public class UserNameValidateAggregate {
    @AggregateIdentifier
    private UserName name;

    private AggregateId id;

    @CommandHandler
    @CreationPolicy(AggregateCreationPolicy.CREATE_IF_MISSING)
    public void handle(
            UserNameValidateCommand command,
            UserRepository userRepository
    ) {
        if (userRepository.exists(command.getName())) {
            throw new UserAlreadyExistsException(command.getName());
        }

        AggregateLifecycle.apply(
                UserNameValidatedEvent
                        .builder()
                        .name(command.getName())
                        .aggregateId(command.getAggregateId())
                        .build()
        );
    }

    @EventSourcingHandler
    public void on(UserNameValidatedEvent event) {
        this.name = event.getName();
        this.id = event.getAggregateId();
    }
}
