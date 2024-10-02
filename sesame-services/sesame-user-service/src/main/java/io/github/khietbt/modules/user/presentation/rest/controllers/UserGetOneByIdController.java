package io.github.khietbt.modules.user.presentation.rest.controllers;

import io.github.khietbt.modules.user.application.queries.UserGetOneByIdQuery;
import io.github.khietbt.modules.user.domain.entities.User;
import io.github.khietbt.modules.user.domain.valueobjects.UserId;
import io.github.khietbt.modules.user.presentation.rest.responses.UserGetOneByIdResponse;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;


@RestController
@Slf4j
public class UserGetOneByIdController {
    @Autowired
    private QueryGateway queryGateway;

    @GetMapping("/users/{id}")
    public CompletableFuture<?> getOneById(@PathVariable(name = "id") UUID id) {
        var query = new UserGetOneByIdQuery(new UserId(id));

        return queryGateway
                .query(query, User.class)
                .thenApplyAsync(UserGetOneByIdResponse::fromDomain)
                .thenApplyAsync(EntityModel::of)
                .thenApplyAsync(
                        (u) -> u.add(
                                WebMvcLinkBuilder.linkTo(
                                        WebMvcLinkBuilder.methodOn(UserGetOneByIdController.class).getOneById(id)
                                ).withSelfRel()
                        )
                );
    }
}
