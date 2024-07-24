package io.github.khietbt.modules.user.presentation.rest.controllers;

import io.github.khietbt.modules.user.domain.entities.User;
import io.github.khietbt.modules.user.presentation.rest.requests.UserUpdateRequest;
import io.github.khietbt.modules.user.presentation.rest.responses.UserUpdateResponse;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Slf4j
@RestController
public class UserUpdateController {
    @Autowired
    private CommandGateway commandGateway;

    @PatchMapping("/users/{id}")
    public CompletableFuture<?> update(
            @PathVariable("id") UUID id,
            @RequestBody UserUpdateRequest request
    ) {
        return commandGateway
                .<User>send(request.toCommand(id))
                .thenApplyAsync(UserUpdateResponse::fromDomain);
    }
}
