package io.github.khietbt.modules.user.application.usecases;

import io.github.khietbt.modules.user.application.queries.UserGetListQuery;
import io.github.khietbt.modules.user.domain.entities.User;
import io.github.khietbt.modules.user.domain.repositories.UserRepository;
import io.github.khietbt.shared.application.UseCase;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class UserGetListUseCase implements UseCase<UserGetListQuery, Page<User>> {
    private final UserRepository userRepository;

    public UserGetListUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @QueryHandler
    public Page execute(UserGetListQuery query) {
        return userRepository.getList(query.getNumber(), query.getSize());
    }
}
