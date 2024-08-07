package io.github.khietbt.modules.user.presentation.rest.controllers;

import io.github.khietbt.modules.user.application.commands.UserNameUnclaimCommand;
import io.github.khietbt.modules.user.domain.valueobjects.UserName;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
@RestController
public class UserNameUnclaimController {
    private final CommandGateway commandGateway;

    @DeleteMapping("/user-names/{name}")
    public CompletableFuture<?> delete(@PathVariable("name") String name) {
        var userName = new UserName(name);

        return commandGateway.send(
                UserNameUnclaimCommand
                        .builder()
                        .userName(userName)
                        .build()
        ).thenApply((n) -> userName);
    }
}
