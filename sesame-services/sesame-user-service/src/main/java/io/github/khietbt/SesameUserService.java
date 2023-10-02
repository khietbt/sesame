package io.github.khietbt;

import io.github.khietbt.entities.User;
import io.github.khietbt.repositories.UserRepository;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.r2dbc.config.EnableR2dbcAuditing;

@Slf4j
@SpringBootApplication
@EnableR2dbcAuditing
public class SesameUserService implements CommandLineRunner {

  @Autowired
  private UserRepository userRepository;

  public static void main(String[] args) {
    SpringApplication.run(SesameUserService.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    User u = userRepository.save(
        User
          .builder()
          .username(UUID.randomUUID().toString())
          .fullname("test")
          .build()
      )
      .block();

    log.error("User = {}", u);
  }
}