package io.github.khietbt.shared.domain.valueobjects;

import lombok.ToString;

import java.util.UUID;

@ToString(callSuper = true)
public class AggregateId extends UuidValueObject {
    public AggregateId(UUID id) {
        super(id);
    }

    public AggregateId() {
        super();
    }

    public AggregateId(String id) {
        super(id);
    }
}
