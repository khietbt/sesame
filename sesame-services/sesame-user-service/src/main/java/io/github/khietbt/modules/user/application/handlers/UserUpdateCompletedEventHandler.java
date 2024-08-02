package io.github.khietbt.modules.user.application.handlers;

import io.github.khietbt.modules.user.domain.events.UserUpdateCompletedEvent;
import io.github.khietbt.modules.user.domain.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
@Slf4j
public class UserUpdateCompletedEventHandler {
    private final UserRepository userRepository;

    @EventHandler
    public void on(UserUpdateCompletedEvent event) {
        this.userRepository.getOne(event.getUserId()).ifPresent(
                (u) -> {
                    u.setName(event.getUserName());

                    this.userRepository.update(u);
                }
        );
    }
}

