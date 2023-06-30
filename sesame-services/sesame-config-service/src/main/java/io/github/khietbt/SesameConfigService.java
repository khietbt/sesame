package io.github.khietbt;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
@Slf4j
public class SesameConfigService implements CommandLineRunner {

  @Value("${spring.cloud.config.server.native.searchLocations}")
  private String searchLocations;

  public static void main(String[] args) {
    SpringApplication.run(SesameConfigService.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    log.info("Woke up successfully with search locations = {}", searchLocations);
  }
}