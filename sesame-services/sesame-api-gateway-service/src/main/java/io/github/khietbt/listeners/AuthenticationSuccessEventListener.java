package io.github.khietbt.listeners;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AuthenticationSuccessEventListener {
    @EventListener
    public void on(AuthenticationSuccessEvent event) {
        log.debug("Logged in with username={}", event.getAuthentication().getName());
    }
}
