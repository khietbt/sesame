package io.github.khietbt.modules.user.presentation.rest.controllers;

import io.github.khietbt.modules.user.application.commands.UserCreateCommand;
import io.github.khietbt.modules.user.application.usecases.UserCreateUseCase;
import io.github.khietbt.modules.user.domain.valueobjects.UserName;
import io.github.khietbt.modules.user.presentation.rest.requests.UserCreateRequest;
import io.github.khietbt.modules.user.presentation.rest.responses.UserCreateResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class UserCreateController {
    @Autowired
    private UserCreateUseCase useCase;

    @PostMapping("/users")
    public ResponseEntity<?> run(@RequestBody UserCreateRequest request) {
        var u = useCase.execute(new UserCreateCommand(new UserName(request.getName())));

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        UserCreateResponse
                                .builder()
                                .id(u.getId().getValue())
                                .name(u.getName().getValue())
                                .createdAt(u.getCreatedAt().getValue())
                                .updatedAt(u.getUpdatedAt().getValue())
                                .build()
                );
    }
}
