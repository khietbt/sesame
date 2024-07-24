package io.github.khietbt.shared.domain.events;

import io.github.khietbt.shared.domain.entities.DomainEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.time.Instant;
import java.util.UUID;

@AllArgsConstructor
@Data
@SuperBuilder
public abstract class DomainEvent<T extends DomainEntity> {
    private final T original;

    private final Instant timestamp;

    private final UUID id;

    private final long version;

    private final UUID aggregateId;
}
