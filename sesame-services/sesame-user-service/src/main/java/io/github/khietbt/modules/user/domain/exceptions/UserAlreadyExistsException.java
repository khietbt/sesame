package io.github.khietbt.modules.user.domain.exceptions;

import io.github.khietbt.modules.user.domain.valueobjects.UserName;
import io.github.khietbt.shared.domain.exceptions.ConflictException;

public class UserAlreadyExistsException extends ConflictException {
    public UserAlreadyExistsException(UserName name) {
        super(String.format("User with name '%s' already exists", name.getValue()));
    }

    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
