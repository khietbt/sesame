package io.github.khietbt.shared.domain.valueobjects;

import lombok.ToString;

@ToString(callSuper = true)
public abstract class StringValueObject extends ValueObject<String> {
    protected StringValueObject(String value) {
        super(value);
    }
}
