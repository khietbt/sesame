package io.github.khietbt.modules.user.presentation.rest.controllers;

import io.github.khietbt.modules.user.application.commands.UserCreateStartCommand;
import io.github.khietbt.modules.user.domain.valueobjects.UserId;
import io.github.khietbt.modules.user.domain.valueobjects.UserName;
import io.github.khietbt.modules.user.presentation.rest.requests.UserCreateRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserCreateController {
    private final CommandGateway commandGateway;

    @PostMapping("/users")
    @SneakyThrows
    public CompletableFuture<?> create(@Valid @RequestBody UserCreateRequest request) {
        var name = new UserName(request.getName());
        var userId = new UserId(UUID.randomUUID());

        return commandGateway.send(
                UserCreateStartCommand
                        .builder()
                        .userId(userId)
                        .userName(name)
                        .build()
        );
    }
}
