package io.github.khietbt.modules.user.presentation.rest.controllers;

import io.github.khietbt.modules.user.application.queries.UserGetOneByIdQuery;
import io.github.khietbt.modules.user.application.usecases.UserGetOneByIdUseCase;
import io.github.khietbt.modules.user.domain.valueobjects.UserId;
import io.github.khietbt.modules.user.presentation.rest.responses.UserGetOneByIdResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class UserGetOneByIdController {
    @Autowired
    private UserGetOneByIdUseCase useCase;

    @GetMapping("/users/{id}")
    public ResponseEntity<?> getOne(@PathVariable(name = "id") UUID id) {
        var query = UserGetOneByIdQuery
                .builder()
                .id(new UserId(id))
                .build();

        var u = useCase.execute(query);

        return ResponseEntity.ok(
                UserGetOneByIdResponse
                        .builder()
                        .id(u.getId().getValue())
                        .name(u.getName().getValue())
                        .createdAt(u.getCreatedAt().getValue())
                        .updatedAt(u.getUpdatedAt().getValue())
                        .createdBy(u.getCreatedBy().getValue())
                        .updatedBy(u.getUpdatedBy().getValue())
                        .version(u.getVersion().getValue())
                        .build()
        );
    }
}
