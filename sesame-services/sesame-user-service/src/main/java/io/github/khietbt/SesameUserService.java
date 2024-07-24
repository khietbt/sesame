package io.github.khietbt;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.Instant;

@SpringBootApplication
@Slf4j
public class SesameUserService {
    public static void main(String[] args) {
//        SpringApplication.run(SesameUserService.class, args);
        Instant i = Instant.now();

        log.info("{}", i);
    }
}
