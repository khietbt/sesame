package io.github.khietbt.modules.user.application.handlers;

import io.github.khietbt.modules.user.domain.events.UserNameUnclaimCompletedEvent;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
@Slf4j
public class UserNameUnclaimCompletedEventHandler {
    @EventHandler
    public void on(UserNameUnclaimCompletedEvent event) {
        log.warn("A name has been freed for using: {}", event.getUserName());
    }
}

