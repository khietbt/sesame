package io.github.khietbt.modules.user.application.aggregates;

import io.github.khietbt.modules.user.application.commands.UserCreateCompleteCommand;
import io.github.khietbt.modules.user.application.commands.UserCreateRequestCommand;
import io.github.khietbt.modules.user.domain.events.UserCreatedEvent;
import io.github.khietbt.modules.user.domain.events.UserNameClaimRequestedEvent;
import io.github.khietbt.modules.user.domain.exceptions.UserAlreadyExistsException;
import io.github.khietbt.modules.user.domain.repositories.UserRepository;
import io.github.khietbt.modules.user.domain.valueobjects.UserId;
import io.github.khietbt.modules.user.domain.valueobjects.UserName;
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
    private UserId userId;

    private UserName userName;

    @CommandHandler
    public UserAggregate(
            UserCreateRequestCommand command,
            UserRepository userRepository
    ) {
        /* There is a user in database using the incoming user's name. */
        if (userRepository.exists(command.getUserName())) {
            throw new UserAlreadyExistsException(command.getUserName());
        }

        /* Claim this user's name. */
        AggregateLifecycle.apply(
                UserNameClaimRequestedEvent
                        .builder()
                        .userId(command.getUserId())
                        .userName(command.getUserName())
                        .build()
        );
    }

    @EventSourcingHandler
    public void on(UserNameClaimRequestedEvent event) {
        this.userName = event.getUserName();
        this.userId = event.getUserId();
    }

    @CommandHandler
    public void on(UserCreateCompleteCommand command) {
        AggregateLifecycle.apply(
                UserCreatedEvent
                        .builder()
                        .userName(command.getUserName())
                        .userId(command.getUserId())
                        .build()
        );
    }

    @EventSourcingHandler
    public void on(UserCreatedEvent event) {
        this.userName = event.getUserName();
        this.userId = event.getUserId();
    }
}
