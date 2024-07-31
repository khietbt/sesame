package io.github.khietbt.shared.domain.events;

import io.github.khietbt.shared.domain.valueobjects.AggregateId;
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
public abstract class DomainEvent {
    private AggregateId aggregateId;
}
