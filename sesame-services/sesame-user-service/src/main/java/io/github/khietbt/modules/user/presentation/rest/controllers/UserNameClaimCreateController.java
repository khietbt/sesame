package io.github.khietbt.modules.user.presentation.rest.controllers;

import io.github.khietbt.modules.user.application.commands.UserNameClaimCreateCommand;
import io.github.khietbt.modules.user.domain.valueobjects.UserName;
import io.github.khietbt.modules.user.presentation.rest.requests.UserNameClaimCreateRequest;
import io.github.khietbt.shared.domain.valueobjects.AggregateId;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
@RestController
public class UserNameClaimCreateController {
    private final CommandGateway commandGateway;

    @PostMapping("/user-names")
    public CompletableFuture<?> obtain(@RequestBody UserNameClaimCreateRequest request) {
        var userName = new UserName(request.getName());
        var aggregateId = new AggregateId();

        return commandGateway.send(
                UserNameClaimCreateCommand
                        .builder()
                        .aggregateId(aggregateId)
                        .name(userName)
                        .build()
        ).thenApply((n) -> userName);
    }
}
