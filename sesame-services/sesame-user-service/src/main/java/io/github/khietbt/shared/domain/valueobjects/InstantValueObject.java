package io.github.khietbt.shared.domain.valueobjects;

import lombok.ToString;

import java.time.Instant;

@ToString
public class InstantValueObject extends ValueObject<Instant> {
    public InstantValueObject(Instant value) {
        super(value);
    }
}
