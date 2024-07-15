package io.github.khietbt.rabbitmq.producers;

import io.github.khietbt.rabbitmq.events.UserCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UserCreatedEventProducer {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void create(String username) {
        var event = new UserCreatedEvent(username);

        rabbitTemplate.convertAndSend("user.created", event);

        log.info("Placed a UserCreatedEvent into the queue with {}", event);
    }
}
