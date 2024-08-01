package io.github.khietbt.modules.user.application.handlers;

import io.github.khietbt.modules.user.domain.events.UserNameClaimDeletedEvent;
import io.github.khietbt.modules.user.domain.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
@Slf4j
public class UserNameClaimDeletedEventHandler {
    private final UserRepository userRepository;

    @EventHandler
    public void on(UserNameClaimDeletedEvent event) {
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

