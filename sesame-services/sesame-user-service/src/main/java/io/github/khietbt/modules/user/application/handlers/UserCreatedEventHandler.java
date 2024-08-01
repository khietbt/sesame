package io.github.khietbt.modules.user.application.handlers;

import io.github.khietbt.modules.user.domain.entities.User;
import io.github.khietbt.modules.user.domain.events.UserCreatedEvent;
import io.github.khietbt.modules.user.domain.repositories.UserRepository;
import io.github.khietbt.modules.user.domain.valueobjects.UserVersion;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
@Slf4j
public class UserCreatedEventHandler {
    private final UserRepository userRepository;

    @EventHandler
    public void on(UserCreatedEvent event) {
        this.userRepository.create(
                User
                        .builder()
                        .id(event.getUserId())
                        .name(event.getUserName())
                        .version(new UserVersion(1))
                        .build()
        );
    }
}

