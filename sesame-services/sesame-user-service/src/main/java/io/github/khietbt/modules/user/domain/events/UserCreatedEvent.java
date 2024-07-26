package io.github.khietbt.modules.user.domain.events;

import lombok.ToString;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@ToString(callSuper = true)
public class UserCreatedEvent extends UserEvent {
}
