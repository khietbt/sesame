package io.github.khietbt.modules.user.domain.repositories;

import io.github.khietbt.modules.user.domain.entities.User;
import io.github.khietbt.modules.user.domain.valueobjects.UserId;
import io.github.khietbt.modules.user.domain.valueobjects.UserName;

import java.util.Optional;

public interface UserRepository {
    Optional<User> getOne(UserId id);

    Optional<User> getOne(UserName name);

    User create(User user);

    boolean exists(UserName name);
}
