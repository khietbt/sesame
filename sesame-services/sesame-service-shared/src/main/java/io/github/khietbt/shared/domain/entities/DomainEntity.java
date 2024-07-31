package io.github.khietbt.shared.domain.entities;

import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@SuperBuilder
@NoArgsConstructor
@ToString
public abstract class DomainEntity implements Serializable {
    @Override
    public boolean equals(Object o) {
        return this == o || this.deepEquals(o);
    }

    private boolean deepEquals(Object o) {
        return o != null
                && this.getClass() == o.getClass()
                && this.toString().equals(o.toString());
    }
}
