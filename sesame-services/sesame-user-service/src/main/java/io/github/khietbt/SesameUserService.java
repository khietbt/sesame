package io.github.khietbt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SesameUserService {
    public static void main(String[] args) {
        SpringApplication.run(SesameUserService.class, args);
    }
}
