package io.github.khietbt.modules.user.application.aggregates;

import io.github.khietbt.modules.user.application.commands.UserNameClaimCreateCommand;
import io.github.khietbt.modules.user.application.commands.UserNameClaimDeleteCommand;
import io.github.khietbt.modules.user.application.commands.UserUpdateUserNameClaimCommand;
import io.github.khietbt.modules.user.application.commands.UserUpdateUserNameUnclaimCommand;
import io.github.khietbt.modules.user.domain.events.*;
import io.github.khietbt.modules.user.domain.valueobjects.UserId;
import io.github.khietbt.modules.user.domain.valueobjects.UserName;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateCreationPolicy;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.modelling.command.CreationPolicy;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.Objects;

@Aggregate
@NoArgsConstructor
public class UserNameAggregate {
    @AggregateIdentifier
    private UserName userName;

    private UserId userId;

    public UserNameAggregate(UserName name, UserId userId) {
        this.userName = name;
        this.userId = userId;
    }

    @CommandHandler
    @CreationPolicy(AggregateCreationPolicy.CREATE_IF_MISSING)
    public void handle(UserNameClaimCreateCommand command) {
        /* This user's name is not owned by any user. */
        if (this.userId == null) {
            AggregateLifecycle.apply(
                    UserNameClaimApprovedEvent
                            .builder()
                            .userId(command.getUserId())
                            .userName(command.getUserName())
                            .build()
            );

            return;
        }

        AggregateLifecycle.apply(
                UserNameClaimRejectedEvent
                        .builder()
                        .userId(command.getUserId())
                        .userName(command.getUserName())
                        .build()
        );
    }

    @CommandHandler
    @CreationPolicy(AggregateCreationPolicy.CREATE_IF_MISSING)
    public void handle(UserNameClaimDeleteCommand command) {
        AggregateLifecycle.apply(
                UserNameClaimDeletedEvent
                        .builder()
                        .userId(command.getUserId())
                        .userName(command.getUserName())
                        .build()
        );
    }

    @EventSourcingHandler
    public void on(UserNameClaimApprovedEvent event) {
        this.userId = event.getUserId();
        this.userName = event.getUserName();
    }

    @EventSourcingHandler
    public void on(UserNameClaimDeletedEvent event) {
        this.userId = null;
        this.userName = event.getUserName();
    }

    @EventSourcingHandler
    public void on(UserNameClaimRejectedEvent event) {
        //
    }

    @CommandHandler
    @CreationPolicy(AggregateCreationPolicy.CREATE_IF_MISSING)
    public void on(UserUpdateUserNameClaimCommand command) {
        if (this.userId != null && !this.userId.equals(command.getUserId())) {
            AggregateLifecycle.apply(
                    UserUpdateUserNameClaimRejectedEvent
                            .builder()
                            .userId(command.getUserId())
                            .userName(command.getUserName())
                            .build()
            );

            return;
        }

        AggregateLifecycle.apply(
                UserUpdateUserNameClaimApprovedEvent
                        .builder()
                        .userId(command.getUserId())
                        .userName(command.getUserName())
                        .build()
        );
    }

    @EventSourcingHandler
    public void on(UserUpdateUserNameClaimRejectedEvent event) {
        this.userName = event.getUserName();
    }

    @EventSourcingHandler
    public void on(UserUpdateUserNameClaimApprovedEvent event) {
        this.userName = event.getUserName();
    }

    @CommandHandler
    public void on(UserUpdateUserNameUnclaimCommand command) {
        AggregateLifecycle.apply(
                UserUpdateUserNameUnclaimCompletedEvent
                        .builder()
                        .userId(command.getUserId())
                        .build()
        );
    }

    @EventSourcingHandler
    public void on(UserUpdateUserNameUnclaimCompletedEvent event) {
        if (Objects.equals(this.userId, event.getUserId())) {
            this.userId = null;
        }
    }
}
