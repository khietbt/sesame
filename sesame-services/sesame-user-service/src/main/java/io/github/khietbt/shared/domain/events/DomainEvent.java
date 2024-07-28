package io.github.khietbt.shared.domain.events;

import io.github.khietbt.shared.domain.entities.DomainEntity;
import io.github.khietbt.shared.domain.valueobjects.AggregateId;
import io.github.khietbt.shared.domain.valueobjects.InstantValueObject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
@ToString
public abstract class DomainEvent<T extends DomainEntity> {
    private AggregateId aggregateId;

    private InstantValueObject createdAt;
}
