package io.github.khietbt.modules.user.presentation.rest.controllers;

import io.github.khietbt.modules.user.application.commands.UserNameClaimDeleteCommand;
import io.github.khietbt.modules.user.domain.valueobjects.UserId;
import io.github.khietbt.modules.user.domain.valueobjects.UserName;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
@RestController
public class UserNameClaimDeleteController {
    private final CommandGateway commandGateway;

    @DeleteMapping("/user-names/{name}")
    public CompletableFuture<?> unclaim(@PathVariable("name") String name) {
        var userName = new UserName(name);
        var userId = new UserId(UUID.randomUUID());

        return commandGateway.send(
                UserNameClaimDeleteCommand
                        .builder()
                        .userId(userId)
                        .userName(userName)
                        .build()
        ).thenApply((n) -> userName);
    }
}
