package io.github.khietbt.modules.user.application.usecases;

import io.github.khietbt.modules.user.application.commands.UserUpdateCommand;
import io.github.khietbt.modules.user.domain.entities.User;
import io.github.khietbt.modules.user.domain.exceptions.UserAlreadyExistsException;
import io.github.khietbt.modules.user.domain.exceptions.UserNotFoundException;
import io.github.khietbt.modules.user.domain.repositories.UserRepository;
import io.github.khietbt.shared.application.UseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class UserUpdateUseCase implements UseCase<UserUpdateCommand, User> {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User execute(UserUpdateCommand command) {
        var updating = userRepository.getOne(command.getId()).orElseThrow(
                () -> new UserNotFoundException(command.getId())
        );

        userRepository.getOne(command.getName()).ifPresent(
                (existing) -> {
                    if (!Objects.equals(existing.getId(), command.getId())) {
                        throw new UserAlreadyExistsException(command.getName());
                    }
                }
        );

        updating.setName(command.getName());

        return userRepository.update(updating);
    }
}
