package io.github.khietbt.modules.user.application.usecases;

import io.github.khietbt.modules.user.application.queries.UserGetOneByIdQuery;
import io.github.khietbt.modules.user.domain.entities.User;
import io.github.khietbt.modules.user.domain.exceptions.UserNotFoundException;
import io.github.khietbt.modules.user.domain.repositories.UserRepository;
import io.github.khietbt.shared.application.UseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserGetOneByIdUseCase implements UseCase<UserGetOneByIdQuery, User> {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User execute(UserGetOneByIdQuery userGetOneByIdQuery) {
        return userRepository.getOne(userGetOneByIdQuery.getId())
                .orElseThrow(
                        () -> new UserNotFoundException(userGetOneByIdQuery.getId())
                );
    }
}
