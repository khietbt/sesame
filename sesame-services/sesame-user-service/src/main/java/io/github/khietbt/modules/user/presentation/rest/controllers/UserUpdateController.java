package io.github.khietbt.modules.user.presentation.rest.controllers;

import io.github.khietbt.modules.user.application.commands.UserUpdateCommand;
import io.github.khietbt.modules.user.application.usecases.UserUpdateUseCase;
import io.github.khietbt.modules.user.domain.valueobjects.UserId;
import io.github.khietbt.modules.user.domain.valueobjects.UserName;
import io.github.khietbt.modules.user.presentation.rest.requests.UserUpdateRequest;
import io.github.khietbt.modules.user.presentation.rest.responses.UserUpdateResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Slf4j
@RestController
public class UserUpdateController {
    @Autowired
    private UserUpdateUseCase useCase;

    @PatchMapping("/users/{id}")
    public ResponseEntity<?> run(@PathVariable("id") UUID id,
                                 @RequestBody UserUpdateRequest request) {
        var command = new UserUpdateCommand(new UserId(id), new UserName(request.getName()));

        var u = useCase.execute(command);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        UserUpdateResponse
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
