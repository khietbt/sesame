package io.github.khietbt.modules.user.presentation.rest.controllers;

import io.github.khietbt.modules.user.domain.entities.User;
import io.github.khietbt.modules.user.presentation.rest.requests.UserCreateRequest;
import io.github.khietbt.modules.user.presentation.rest.responses.UserCreateResponse;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@Slf4j
@RestController
public class UserCreateController {
    @Autowired
    private CommandGateway commandGateway;

    @PostMapping("/users")
    public UserCreateResponse create(@RequestBody UserCreateRequest request) throws ExecutionException, InterruptedException {
        return commandGateway
                .<User>send(request.toCommand())
                .thenApplyAsync(UserCreateResponse::fromDomain)
                .get();
    }
}
