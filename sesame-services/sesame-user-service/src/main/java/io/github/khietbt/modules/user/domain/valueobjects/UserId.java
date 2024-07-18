package io.github.khietbt.modules.user.domain.valueobjects;

import io.github.khietbt.shared.domain.valueobjects.UuidValueObject;

import java.util.UUID;

public class UserId extends UuidValueObject {
    public UserId(UUID id) {
        super(id);
    }
}
