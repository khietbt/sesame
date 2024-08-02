package io.github.khietbt.modules.user.presentation.rest.controllers;

import io.github.khietbt.modules.user.application.commands.UserCreateUserNameClaimCommand;
import io.github.khietbt.modules.user.domain.valueobjects.UserId;
import io.github.khietbt.modules.user.domain.valueobjects.UserName;
import io.github.khietbt.modules.user.presentation.rest.requests.UserNameClaimCreateRequest;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
@RestController
public class UserNameClaimCreateController {
    private final CommandGateway commandGateway;

    @PostMapping("/user-names")
    public CompletableFuture<?> obtain(@RequestBody UserNameClaimCreateRequest request) {
        var userName = new UserName(request.getName());
        var userId = new UserId(UUID.randomUUID());

        return commandGateway.send(
                UserCreateUserNameClaimCommand
                        .builder()
                        .userId(userId)
                        .userName(userName)
                        .build()
        ).thenApply((n) -> userName);
    }
}
