package io.github.khietbt.modules.user.application.aggregates;

import io.github.khietbt.modules.user.application.commands.UserNameClaimCreateCommand;
import io.github.khietbt.modules.user.application.commands.UserNameClaimDeleteCommand;
import io.github.khietbt.modules.user.domain.events.UserNameClaimCreatedEvent;
import io.github.khietbt.modules.user.domain.events.UserNameClaimDeletedEvent;
import io.github.khietbt.modules.user.domain.events.UserNameClaimRejectedEvent;
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
public class UserNameAggregate {
    @AggregateIdentifier
    private UserName name;

    private AggregateId aggregateId;

    private boolean claimed = false;

    public UserNameAggregate(UserName name, AggregateId aggregateId) {
        this.name = name;
        this.aggregateId = aggregateId;
    }

    @CommandHandler
    @CreationPolicy(AggregateCreationPolicy.CREATE_IF_MISSING)
    public void handle(UserNameClaimCreateCommand command) {
        if (!this.claimed) {
            // approved
            AggregateLifecycle.apply(
                    UserNameClaimCreatedEvent
                            .builder()
                            .aggregateId(command.getAggregateId())
                            .name(command.getName())
                            .build()
            );

            return;
        }

        AggregateLifecycle.apply(
                UserNameClaimRejectedEvent
                        .builder()
                        .aggregateId(command.getAggregateId())
                        .name(command.getName())
                        .build()
        );
    }

    @CommandHandler
    public void handle(UserNameClaimDeleteCommand command) {
        AggregateLifecycle.apply(
                UserNameClaimDeletedEvent
                        .builder()
                        .aggregateId(command.getAggregateId())
                        .name(command.getName())
                        .build()
        );
    }

    @EventSourcingHandler
    public void on(UserNameClaimCreatedEvent event) {
        this.aggregateId = event.getAggregateId();
        this.name = event.getName();
        this.claimed = true;
    }

    @EventSourcingHandler
    public void on(UserNameClaimDeletedEvent event) {
        this.aggregateId = null;
        this.claimed = false;
    }

    @EventSourcingHandler
    public void on(UserNameClaimRejectedEvent event) {
        //
    }
}
