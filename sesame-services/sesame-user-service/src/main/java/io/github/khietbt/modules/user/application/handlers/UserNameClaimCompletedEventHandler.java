package io.github.khietbt.modules.user.application.handlers;

import io.github.khietbt.modules.user.domain.events.UserNameClaimCompletedEvent;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
@Slf4j
public class UserNameClaimCompletedEventHandler {
    @EventHandler
    public void on(UserNameClaimCompletedEvent event) {
        log.warn("A name has been locked, not for using: {}", event.getUserName());
    }
}

