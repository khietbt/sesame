package io.github.khietbt.modules.user.application.usecases;

import io.github.khietbt.modules.user.application.queries.UserGetOneByIdQuery;
import io.github.khietbt.modules.user.domain.entities.User;
import io.github.khietbt.modules.user.domain.exceptions.UserNotFoundException;
import io.github.khietbt.modules.user.domain.repositories.UserRepository;
import io.github.khietbt.shared.application.UseCase;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class UserGetOneByIdUseCase implements UseCase<UserGetOneByIdQuery, User> {
    private final UserRepository userRepository;

    public UserGetOneByIdUseCase(@Qualifier("keycloakUserRepository") UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @QueryHandler
    public User execute(UserGetOneByIdQuery userGetOneByIdQuery) {
        return userRepository.getOne(userGetOneByIdQuery.getId())
                .orElseThrow(
                        () -> new UserNotFoundException(userGetOneByIdQuery.getId())
                );
    }
}
