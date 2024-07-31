package io.github.khietbt.shared.domain.valueobjects;

import lombok.ToString;

@ToString(callSuper = true)
public abstract class IntegerValueObject extends ValueObject<Integer> {
    protected IntegerValueObject(Integer value) {
        super(value);
    }
}
