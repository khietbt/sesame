package io.github.khietbt.modules.user.infrastructure.mysql.services;

import io.github.khietbt.modules.user.domain.entities.User;
import io.github.khietbt.modules.user.domain.exceptions.UserNotFoundException;
import io.github.khietbt.modules.user.domain.services.UserService;
import io.github.khietbt.modules.user.domain.valueobjects.UserName;
import io.github.khietbt.modules.user.infrastructure.mysql.repositories.DatabaseUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DatabaseUserService implements UserService {
    @Autowired
    private DatabaseUserRepository userRepository;

    @Override
    public User getOne(UserName name) {
        return userRepository
                .getOne(name).
                orElseThrow(
                        () -> new UserNotFoundException(String.format("User with name '%s' not found", name))
                );
    }
}
