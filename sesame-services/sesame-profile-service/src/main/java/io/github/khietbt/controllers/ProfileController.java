package io.github.khietbt.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController()
@RequestMapping("/profiles")
public class ProfileController {
    @GetMapping
    public ResponseEntity<String> getAll() {
        log.info("current thread {}", Thread.currentThread());
        return ResponseEntity.ok("All profiles");
    }
}
