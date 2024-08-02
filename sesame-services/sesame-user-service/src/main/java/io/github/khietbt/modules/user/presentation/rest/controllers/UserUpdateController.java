package io.github.khietbt.modules.user.presentation.rest.controllers;

import io.github.khietbt.modules.user.application.commands.UserUpdateStartCommand;
import io.github.khietbt.modules.user.domain.repositories.UserRepository;
import io.github.khietbt.modules.user.domain.valueobjects.UserId;
import io.github.khietbt.modules.user.domain.valueobjects.UserName;
import io.github.khietbt.modules.user.presentation.rest.requests.UserUpdateRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserUpdateController {
    private final CommandGateway commandGateway;

    private final UserRepository userRepository;

    @PatchMapping("/users/{id}")
    public CompletableFuture<?> update(
            @PathVariable("id") UUID id,
            @RequestBody UserUpdateRequest request
    ) {
        var userId = new UserId(id);
        var userName = new UserName(request.getName());

        return commandGateway.send(
                UserUpdateStartCommand
                        .builder()
                        .userId(userId)
                        .userName(new UserName(request.getName()))
                        .build()
        );
    }
}
