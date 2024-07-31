package io.github.khietbt.shared.domain.valueobjects;


import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Objects;

@Getter
@ToString
public abstract class ValueObject<T> implements Serializable {
    private final T value;

    protected ValueObject(T value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof ValueObject<?> rhs
                && rhs.getClass().equals(this.getClass())
                && Objects.equals(this.value, rhs.value);
    }
}