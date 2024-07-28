package io.github.khietbt.shared.domain.valueobjects;

import lombok.ToString;

import java.time.Instant;

@ToString
public class AggregateCreatedAt extends InstantValueObject {
    public AggregateCreatedAt(Instant value) {
        super(value);
    }
}
