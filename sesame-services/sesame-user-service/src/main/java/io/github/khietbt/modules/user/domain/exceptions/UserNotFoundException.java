package io.github.khietbt.modules.user.domain.exceptions;

import io.github.khietbt.shared.domain.exceptions.DomainException;

public class UserNotFoundException extends DomainException {
    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
