package io.github.khietbt.modules.user.application.sagas;

import io.github.khietbt.modules.user.application.commands.*;
import io.github.khietbt.modules.user.domain.events.*;
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
public class UserNameSaga {
    private UserId userId;

    private UserName userName;

    private UserName oldUserName;

    @SagaEventHandler(associationProperty = "userId", keyName = "userName")
    @StartSaga
    public void on(
            UserCreateStartedEvent event,
            CommandGateway commandGateway
    ) {
        this.userId = event.getUserId();
        this.userName = event.getUserName();

        commandGateway.send(
                UserCreateUserNameClaimCommand
                        .builder()
                        .userId(event.getUserId())
                        .userName(event.getUserName())
                        .build()
        );
    }

    @SagaEventHandler(associationProperty = "userId", keyName = "userName")
    @EndSaga
    public void on(
            UserCreateUserNameClaimApprovedEvent event,
            CommandGateway commandGateway
    ) {
        commandGateway.send(
                UserCreateCompleteCommand
                        .builder()
                        .userId(event.getUserId())
                        .userName(event.getUserName())
                        .build()
        );
    }

    @SagaEventHandler(associationProperty = "userId", keyName = "userName")
    @EndSaga
    public void on(
            UserCreateUserNameClaimRejectedEvent event,
            CommandGateway commandGateway
    ) {
        commandGateway.send(
                UserCreateRejectCommand
                        .builder()
                        .userId(event.getUserId())
                        .userName(event.getUserName())
                        .build()
        );
    }

    @SagaEventHandler(associationProperty = "userId", keyName = "userId")
    @StartSaga
    public void on(
            UserUpdateStartedEvent event,
            CommandGateway commandGateway
    ) {
        this.oldUserName = event.getOldUserName();
        this.userId = event.getUserId();
        this.userName = event.getUserName();

        commandGateway.send(
                UserUpdateUserNameClaimCommand
                        .builder()
                        .userId(event.getUserId())
                        .userName(event.getUserName())
                        .build()
        );
    }

    @SagaEventHandler(associationProperty = "userId", keyName = "userId")
    @EndSaga
    public void on(
            UserUpdateUserNameClaimRejectedEvent event,
            CommandGateway commandGateway
    ) {
        commandGateway.send(
                UserUpdateRejectCommand
                        .builder()
                        .userId(event.getUserId())
                        .userName(event.getUserName())
                        .build()
        );
    }

    @SagaEventHandler(associationProperty = "userId", keyName = "userId")
    @EndSaga
    public void on(
            UserUpdateUserNameClaimApprovedEvent event,
            CommandGateway commandGateway
    ) {
        commandGateway.send(
                UserUpdateCompleteCommand
                        .builder()
                        .userId(this.userId)
                        .oldUserName(this.oldUserName)
                        .userName(this.userName)
                        .build()
        );

        commandGateway.send(
                UserUpdateUserNameUnclaimCommand
                        .builder()
                        .userId(this.userId)
                        .userName(this.oldUserName)
                        .build()
        );
    }
}
