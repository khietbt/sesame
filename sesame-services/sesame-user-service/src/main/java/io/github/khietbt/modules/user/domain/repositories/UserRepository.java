package io.github.khietbt.modules.user.domain.repositories;

import io.github.khietbt.modules.user.domain.entities.User;
import io.github.khietbt.modules.user.domain.valueobjects.UserId;
import io.github.khietbt.modules.user.domain.valueobjects.UserName;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface UserRepository {
    Page<User> getList(Integer number, Integer size);

    Optional<User> getOne(UserId id);

    Optional<User> getOne(UserName name);

    User create(String name);

    User create(UserName name);

    User create(User user);

    boolean exists(UserName name);

    User update(User user);
}
