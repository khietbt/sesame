package io.github.khietbt.modules.user.application.usecases;

import io.github.khietbt.modules.user.application.queries.UserGetOneByNameQuery;
import io.github.khietbt.modules.user.domain.entities.User;
import io.github.khietbt.modules.user.domain.exceptions.UserNotFoundException;
import io.github.khietbt.modules.user.domain.repositories.UserRepository;
import io.github.khietbt.shared.application.UseCase;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

@Component
public class UserGetOneByNameUseCase implements UseCase<UserGetOneByNameQuery, User> {
    private final UserRepository userRepository;

    public UserGetOneByNameUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @QueryHandler
    public User execute(UserGetOneByNameQuery userGetOneByNameQuery) {
        var name = userGetOneByNameQuery.getName();

        return userRepository.getOne(name)
                .orElseThrow(() -> new UserNotFoundException(name));
    }
}
