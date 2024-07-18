package io.github.khietbt.modules.user.application.usecases;

import io.github.khietbt.modules.user.application.commands.UserCreateCommand;
import io.github.khietbt.modules.user.domain.entities.User;
import io.github.khietbt.modules.user.domain.exceptions.UserAlreadyExistsException;
import io.github.khietbt.modules.user.domain.repositories.UserRepository;
import io.github.khietbt.shared.application.UseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserCreateUseCase implements UseCase<UserCreateCommand, User> {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User execute(UserCreateCommand command) {
        var name = command.getName();

        if (userRepository.exists(name)) {
            throw new UserAlreadyExistsException(name);
        }

        return userRepository.create(User.builder().name(name).build());
    }
}
