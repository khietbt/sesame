package io.github.khietbt.shared.domain.valueobjects;

import lombok.ToString;

import java.util.UUID;

@ToString(callSuper = true)
public abstract class UuidValueObject extends ValueObject<UUID> {
    protected UuidValueObject(UUID value) {
        super(value);
    }

    protected UuidValueObject() {
        super(UUID.randomUUID());
    }

    protected UuidValueObject(String value) {
        super(UUID.fromString(value));
    }
}
