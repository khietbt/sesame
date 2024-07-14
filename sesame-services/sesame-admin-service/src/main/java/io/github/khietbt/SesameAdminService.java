package io.github.khietbt;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAdminServer
@SpringBootApplication
public class SesameAdminService {
    public static void main(String[] args) {
        SpringApplication.run(SesameAdminService.class, args);
    }
}
