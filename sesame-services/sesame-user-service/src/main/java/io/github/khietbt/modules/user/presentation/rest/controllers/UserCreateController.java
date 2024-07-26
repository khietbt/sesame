package io.github.khietbt.modules.user.presentation.rest.controllers;

import io.github.khietbt.modules.user.presentation.rest.requests.UserCreateRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserCreateController {
    private final CommandGateway commandGateway;

    @PostMapping("/users")
    public CompletableFuture<?> create(@RequestBody UserCreateRequest request) {
        return commandGateway.send(request.toCommand());
    }
}
