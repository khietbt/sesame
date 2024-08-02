package io.github.khietbt.modules.user.application.handlers;

import io.github.khietbt.modules.user.domain.events.UserUpdateRejectedEvent;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
@Slf4j
public class UserUpdateRejectedEventHandler {
    @EventHandler
    public void on(UserUpdateRejectedEvent event) {
        log.info("Rejected a request updating a user {}", event);
    }
}

