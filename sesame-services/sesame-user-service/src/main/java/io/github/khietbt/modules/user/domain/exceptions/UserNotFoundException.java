package io.github.khietbt.modules.user.domain.exceptions;

import io.github.khietbt.modules.user.domain.valueobjects.UserId;
import io.github.khietbt.modules.user.domain.valueobjects.UserName;
import io.github.khietbt.shared.domain.exceptions.NotFoundException;

public class UserNotFoundException extends NotFoundException {
    public UserNotFoundException(UserId id) {
        super(String.format("User with id '%s' not found", id.getValue()));
    }

    public UserNotFoundException(UserName name) {
        super(String.format("User with name '%s' not found", name.getValue()));
    }
}
