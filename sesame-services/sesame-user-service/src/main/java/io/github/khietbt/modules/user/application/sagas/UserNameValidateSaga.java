package io.github.khietbt.modules.user.application.sagas;

import io.github.khietbt.modules.user.application.commands.UserNameClaimCreateCommand;
import io.github.khietbt.modules.user.domain.events.UserNameClaimCreatedEvent;
import io.github.khietbt.modules.user.domain.events.UserNameClaimRequestedEvent;
import io.github.khietbt.modules.user.domain.valueobjects.UserName;
import io.github.khietbt.shared.domain.valueobjects.AggregateId;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.EndSaga;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;

@Saga
@Slf4j
public class UserNameValidateSaga {
    private AggregateId aggregateId;
    private UserName name;

    @SagaEventHandler(associationProperty = "aggregateId", keyName = "name")
    @StartSaga
    public void on(
            UserNameClaimRequestedEvent event,
            CommandGateway commandGateway
    ) {
        this.aggregateId = event.getAggregateId();
        this.name = event.getName();

        commandGateway.send(
                UserNameClaimCreateCommand
                        .builder()
                        .aggregateId(event.getAggregateId())
                        .name(event.getName())
                        .build()
        );
    }

    @SagaEventHandler(associationProperty = "name", keyName = "name")
    @EndSaga
    public void on(
            UserNameClaimCreatedEvent event,
            CommandGateway commandGateway
    ) {
        log.info("Claimed an user name: {}", event.getName());
    }
}
