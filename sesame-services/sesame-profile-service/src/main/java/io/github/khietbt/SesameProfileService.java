package io.github.khietbt;

import io.github.khietbt.entities.Profile;
import io.github.khietbt.repositories.ProfileRepository;
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
public class SesameProfileService implements CommandLineRunner {

  @Autowired
  private ProfileRepository profileRepository;

  public static void main(String[] args) {
    SpringApplication.run(SesameProfileService.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    Profile p = profileRepository
      .save(
        Profile
          .builder()
          .userId(UUID.randomUUID().toString())
          .email("khietbt@gmail.com")
          .phone("+84-0976800301")
          .build()
      )
      .block();

    log.error("Profile = {}", p);
  }
}