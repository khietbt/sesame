package io.github.khietbt.modules.session.rest.controllers;

import io.github.khietbt.modules.session.rest.requests.LoginCreateRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SessionCreateController {
    @PostMapping("/sessions")
    public ResponseEntity<?> create(@RequestBody LoginCreateRequest request) {
        // TODO:
        return ResponseEntity.ok("logged in");
    }
}
