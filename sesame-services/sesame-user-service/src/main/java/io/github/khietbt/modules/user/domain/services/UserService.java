package io.github.khietbt.modules.user.domain.services;

import io.github.khietbt.modules.user.domain.entities.User;
import io.github.khietbt.modules.user.domain.valueobjects.UserName;

public interface UserService {
    User getOne(UserName name);
}
