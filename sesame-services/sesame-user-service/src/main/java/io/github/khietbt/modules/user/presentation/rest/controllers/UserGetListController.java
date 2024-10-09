package io.github.khietbt.modules.user.presentation.rest.controllers;

import io.github.khietbt.modules.user.application.queries.UserGetListQuery;
import io.github.khietbt.modules.user.domain.entities.User;
import io.github.khietbt.modules.user.presentation.rest.responses.UserGetListItemResponse;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;


@RestController
@Slf4j
public class UserGetListController {
    @Autowired
    private QueryGateway queryGateway;

    @GetMapping("/users")
    @PreAuthorize("hasAuthority('urn:role:sesame-user-service:users:getList')")
    public CompletableFuture<Page<UserGetListItemResponse>> getList(
            @RequestParam(name = "number", defaultValue = "0") Integer number,
            @RequestParam(name = "size", defaultValue = "10") Integer size,
            Authentication auth
    ) {
        number = Math.min(Math.max(number, 0), 100);
        size = Math.min(Math.max(size, 1), 100);

        //noinspection unchecked
        return queryGateway
                .query(new UserGetListQuery(number, size), Page.class)
                .thenApplyAsync(
                        p -> ((Page<User>) p).map(UserGetListItemResponse::fromDomain)
                );
    }
}
