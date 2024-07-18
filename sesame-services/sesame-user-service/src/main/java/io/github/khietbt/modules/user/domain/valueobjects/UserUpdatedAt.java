package io.github.khietbt.modules.user.domain.valueobjects;

import io.github.khietbt.shared.domain.valueobjects.InstantValueObject;
import lombok.ToString;

import java.time.Instant;

@ToString(callSuper = true)
public class UserUpdatedAt extends InstantValueObject {
    public UserUpdatedAt(Instant updatedAt) {
        super(updatedAt);
    }
}
