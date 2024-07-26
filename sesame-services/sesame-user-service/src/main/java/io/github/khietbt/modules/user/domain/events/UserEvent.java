package io.github.khietbt.modules.user.domain.events;

import io.github.khietbt.modules.user.domain.entities.User;
import io.github.khietbt.shared.domain.events.DomainEvent;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@ToString
public abstract class UserEvent extends DomainEvent<User> {
}
