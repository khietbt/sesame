package io.github.khietbt.modules.user.domain.valueobjects;

import io.github.khietbt.shared.domain.valueobjects.InstantValueObject;
import lombok.ToString;

import java.time.Instant;

@ToString(callSuper = true)
public class UserCreatedAt extends InstantValueObject {
    public UserCreatedAt(Instant createdAt) {
        super(createdAt);
    }
}
