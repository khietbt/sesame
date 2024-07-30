package io.github.khietbt.modules.user.application.handlers;

import io.github.khietbt.modules.user.application.commands.UserCreateCommand;
import io.github.khietbt.modules.user.domain.events.UserNameValidatedEvent;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
@Slf4j
public class UserNameValidatedEventHandler {
    @EventHandler
    void on(UserNameValidatedEvent event, CommandGateway commandGateway) {
        commandGateway.send(
                UserCreateCommand
                        .builder()
                        .name(event.getName())
                        .aggregateId(event.getAggregateId())
                        .build()
        );
    }
}

