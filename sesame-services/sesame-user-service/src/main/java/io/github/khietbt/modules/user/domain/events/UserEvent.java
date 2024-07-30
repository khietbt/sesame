package io.github.khietbt.modules.user.domain.events;

import io.github.khietbt.shared.domain.events.DomainEvent;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@ToString(callSuper = true)
public abstract class UserEvent extends DomainEvent {
}
