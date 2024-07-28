package io.github.khietbt.modules.user.presentation.rest.controllers;

import io.github.khietbt.modules.user.application.commands.UserCreateCommand;
import io.github.khietbt.modules.user.domain.valueobjects.UserName;
import io.github.khietbt.modules.user.presentation.rest.requests.UserCreateRequest;
import io.github.khietbt.shared.domain.valueobjects.AggregateId;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserCreateController {
    private final CommandGateway commandGateway;

    private final QueryGateway queryGateway;

    @PostMapping("/users")
    public CompletableFuture<?> create(@Valid @RequestBody UserCreateRequest request) {
        var name = new UserName(request.getName());
        var aggregateId = new AggregateId();

        return commandGateway.send(new UserCreateCommand(aggregateId, name));
    }
}
