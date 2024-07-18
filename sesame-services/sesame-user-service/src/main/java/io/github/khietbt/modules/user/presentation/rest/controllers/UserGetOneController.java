package io.github.khietbt.modules.user.presentation.rest.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class UserGetOneController {
    @GetMapping("/users/{id}")
    public ResponseEntity<?> getOne(@PathVariable(name = "id") UUID id) {
        return ResponseEntity.ok(id);
    }
}
