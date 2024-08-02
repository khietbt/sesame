package io.github.khietbt.modules.user.application.aggregates;

import io.github.khietbt.modules.user.application.commands.*;
import io.github.khietbt.modules.user.domain.events.*;
import io.github.khietbt.modules.user.domain.exceptions.IdenticalUserNameException;
import io.github.khietbt.modules.user.domain.exceptions.UserAlreadyExistsException;
import io.github.khietbt.modules.user.domain.exceptions.UserNotFoundException;
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

import java.util.Objects;

@Aggregate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Slf4j
public class UserAggregate {
    @AggregateIdentifier
    private UserId userId;

    private UserName userName;

    @CommandHandler
    public UserAggregate(
            UserCreateStartCommand command,
            UserRepository userRepository
    ) {
        /* There is a user in database using the incoming user's name. */
        if (userRepository.exists(command.getUserName())) {
            throw new UserAlreadyExistsException(command.getUserName());
        }

        /* Claim this user's name. */
        AggregateLifecycle.apply(
                UserCreateStartedEvent
                        .builder()
                        .userId(command.getUserId())
                        .userName(command.getUserName())
                        .build()
        );
    }

    @EventSourcingHandler
    public void on(UserCreateStartedEvent event) {
        this.userName = event.getUserName();
        this.userId = event.getUserId();
    }

    @CommandHandler
    public void on(UserCreateCompleteCommand command) {
        AggregateLifecycle.apply(
                UserCreateCompletedEvent
                        .builder()
                        .userName(command.getUserName())
                        .userId(command.getUserId())
                        .build()
        );
    }

    @EventSourcingHandler
    public void on(UserCreateCompletedEvent event) {
        this.userName = event.getUserName();
        this.userId = event.getUserId();
    }

    @CommandHandler
    public void on(UserUpdateStartCommand command, UserRepository userRepository) {
        var existing = userRepository.getOne(command.getUserId())
                .orElseThrow(() -> new UserNotFoundException(command.getUserId()));

        if (Objects.equals(existing.getName(), command.getUserName())) {
            throw new IdenticalUserNameException(command.getUserName());
        }

        userRepository.getOne(command.getUserName()).ifPresent(
                (u) -> {
                    if (u.getId() != command.getUserId()) {
                        throw new UserAlreadyExistsException(command.getUserName());
                    }
                }
        );

        AggregateLifecycle.apply(
                UserUpdateStartedEvent
                        .builder()
                        .userId(command.getUserId())
                        .oldUserName(existing.getName())
                        .userName(command.getUserName())
                        .build()
        );
    }

    @EventSourcingHandler
    public void on(UserUpdateStartedEvent event) {
        this.userId = event.getUserId();
    }

    @CommandHandler
    public void on(UserUpdateCompleteCommand command) {
        AggregateLifecycle.apply(
                UserUpdateCompletedEvent
                        .builder()
                        .userId(command.getUserId())
                        .oldUserName(command.getOldUserName())
                        .userName(command.getUserName())
                        .build()
        );
    }

    @EventSourcingHandler
    public void on(UserUpdateCompletedEvent event) {
        this.userId = event.getUserId();
        this.userName = event.getUserName();
    }

    @CommandHandler
    public void on(UserCreateRejectCommand command) {
        AggregateLifecycle.apply(
                UserCreateRejectedEvent
                        .builder()
                        .userId(command.getUserId())
                        .userName(command.getUserName())
                        .build()
        );
    }

    @CommandHandler
    public void on(UserUpdateRejectCommand command) {
        AggregateLifecycle.apply(
                UserUpdateRejectedEvent
                        .builder()
                        .userId(command.getUserId())
                        .userName(command.getUserName())
                        .build()
        );
    }
}
