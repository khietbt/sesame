package io.github.khietbt.modules.user.presentation.rest.controllers;

import io.github.khietbt.modules.user.application.requests.UserCreateRequest;
import io.github.khietbt.modules.user.application.usecases.UserCreateUseCase;
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
    private UserCreateUseCase userCreateUseCase;

    @PostMapping("/users")
    public ResponseEntity<?> run(@RequestBody UserCreateRequest request) {
        log.info("Processing a request: {}", request);

        var response = userCreateUseCase.execute(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
