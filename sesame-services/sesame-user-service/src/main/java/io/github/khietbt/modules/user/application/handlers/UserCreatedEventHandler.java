package io.github.khietbt.modules.user.application.handlers;

import io.github.khietbt.modules.user.domain.events.UserCreatedEvent;
import io.github.khietbt.modules.user.domain.repositories.UserRepository;
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
        log.error("Consumed by handler {}", event);
//        this.userRepository.create(
//                User
//                        .builder()
//                        .id(new UserId(event.getAggregateId().getValue()))
//                        .name(event.getName())
//                        .version(new UserVersion(1))
//                        .build()
//        );
    }
}

