package io.github.khietbt.shared.domain.events;

import io.github.khietbt.shared.domain.entities.DomainEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public abstract class DomainEvent<T extends DomainEntity> {
    private final T original;
}
