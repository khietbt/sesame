package io.github.khietbt.modules.user.domain.exceptions;

import io.github.khietbt.modules.user.domain.valueobjects.UserName;
import io.github.khietbt.shared.domain.exceptions.ConflictException;

public class IdenticalUserNameException extends ConflictException {
    public IdenticalUserNameException(UserName name) {
        super(String.format("User's name has been %s already", name.getValue()));
    }

    public IdenticalUserNameException(String message) {
        super(message);
    }
}
