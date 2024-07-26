package io.github.khietbt.shared.domain.events;

import io.github.khietbt.shared.domain.entities.DomainEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.time.Instant;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
@ToString
public abstract class DomainEvent<T extends DomainEntity> {
    private UUID aggregateId;

    private T source;

    private Instant createdAt;
}
