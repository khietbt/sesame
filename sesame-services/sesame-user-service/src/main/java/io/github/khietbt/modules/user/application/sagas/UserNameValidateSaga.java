package io.github.khietbt.modules.user.application.sagas;

import io.github.khietbt.modules.user.application.commands.UserCreateCompleteCommand;
import io.github.khietbt.modules.user.application.commands.UserNameClaimCreateCommand;
import io.github.khietbt.modules.user.domain.events.UserNameClaimApprovedEvent;
import io.github.khietbt.modules.user.domain.events.UserNameClaimRequestedEvent;
import io.github.khietbt.modules.user.domain.valueobjects.UserId;
import io.github.khietbt.modules.user.domain.valueobjects.UserName;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.EndSaga;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;

@Saga
@Slf4j
public class UserNameValidateSaga {
    private UserId userId;

    private UserName userName;

    @SagaEventHandler(associationProperty = "userId", keyName = "userName")
    @StartSaga
    public void on(
            UserNameClaimRequestedEvent event,
            CommandGateway commandGateway
    ) {
        this.userId = event.getUserId();
        this.userName = event.getUserName();

        commandGateway.send(
                UserNameClaimCreateCommand
                        .builder()
                        .userId(event.getUserId())
                        .userName(event.getUserName())
                        .build()
        );
    }

    @SagaEventHandler(associationProperty = "userId", keyName = "userName")
    @EndSaga
    public void on(
            UserNameClaimApprovedEvent event,
            CommandGateway commandGateway
    ) {
        log.info("Claimed an user name: {}", event.getUserName());

        commandGateway.send(
                UserCreateCompleteCommand
                        .builder()
                        .userId(event.getUserId())
                        .userName(event.getUserName())
                        .build()
        );
    }
}
