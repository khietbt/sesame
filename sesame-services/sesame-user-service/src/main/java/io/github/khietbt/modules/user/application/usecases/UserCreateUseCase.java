package io.github.khietbt.modules.user.application.usecases;

import io.github.khietbt.modules.user.application.requests.UserCreateRequest;
import io.github.khietbt.modules.user.application.responses.UserCreateResponse;
import io.github.khietbt.modules.user.domain.entities.User;
import io.github.khietbt.modules.user.domain.exceptions.UserAlreadyExistsException;
import io.github.khietbt.modules.user.domain.repositories.UserRepository;
import io.github.khietbt.modules.user.domain.valueobjects.UserName;
import io.github.khietbt.shared.application.UseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserCreateUseCase implements UseCase<UserCreateRequest, UserCreateResponse> {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserCreateResponse execute(UserCreateRequest userCreateRequest) {
        var name = new UserName(userCreateRequest.getName());

        userRepository.getOne(name).ifPresent(
                u -> {
                    throw new UserAlreadyExistsException(name);
                }
        );

        var user = userRepository.create(User.builder().name(name).build());

        return UserCreateResponse
                .builder()
                .id(user.getId().getValue())
                .build();
    }
}
