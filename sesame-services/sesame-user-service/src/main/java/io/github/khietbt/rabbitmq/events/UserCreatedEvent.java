package io.github.khietbt.rabbitmq.events;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
@ToString
public class UserCreatedEvent implements Serializable {
    private String username;
}
