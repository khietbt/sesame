package io.github.khietbt.modules.user.application.handlers;

import io.github.khietbt.modules.user.domain.events.UserCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UserCreatedEventHandler {
    @EventHandler
    public void handle(UserCreatedEvent event) {
        log.info("Handling an UserCreatedEvent event: {}", event);
    }
}
