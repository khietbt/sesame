package io.github.khietbt.conrollers;

import io.github.khietbt.rabbitmq.producers.UserCreatedEventProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserCreatedEventProducer userCreatedEventProducer;

    @PostMapping
    public ResponseEntity<?> create(@RequestParam(name = "username") String username) {
        this.userCreatedEventProducer.create(username);

        return ResponseEntity.ok("Created a new user: " + username);
    }
}
