package io.github.khietbt.rabbitmq.consumers;

import io.github.khietbt.rabbitmq.events.UserCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UserCreatedEventConsumer {
    @RabbitListener(
            queues = "user.created"
    )
    public void consume(UserCreatedEvent event) {
        log.info("Received user created event: {} by {}", event, Thread.currentThread());
    }
}
