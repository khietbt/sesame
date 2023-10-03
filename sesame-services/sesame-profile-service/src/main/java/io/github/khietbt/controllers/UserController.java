package io.github.khietbt.controllers;

import java.util.Arrays;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/users")
public class UserController {

  @GetMapping
  public Mono<List<String>> getAllUsers() {
    return Mono.just(Arrays.asList("a", "b"));
  }
}
