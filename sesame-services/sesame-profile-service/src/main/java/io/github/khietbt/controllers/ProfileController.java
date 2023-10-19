package io.github.khietbt.controllers;

import java.time.Duration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/profiles")
public class ProfileController {

  @GetMapping
  public Flux<Integer> getAllProfiles() {
    Flux.range(0, 10).delayElements(Duration.ofSeconds(1)).subscribe(System.out::println);

    return null;
  }
}