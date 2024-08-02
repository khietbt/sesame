package io.github.khietbt.modules.user.application.handlers;

import io.github.khietbt.modules.user.domain.events.UserCreateRejectedEvent;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
@Slf4j
public class UserCreateRejectedEventHandler {
    @EventHandler
    public void on(UserCreateRejectedEvent event) {
        log.info("Rejected a request creating a new user {}", event);
    }
}

