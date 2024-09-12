package io.github.khietbt.problems.onemillitionrowproblem;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.Duration;

@SpringBootApplication
@Slf4j
public class OneMillionRowProblem implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(OneMillionRowProblem.class, args);
    }

    private void count(char c, AbstractCharacterInFileCounter counter) {
        Long start = System.nanoTime();

        Long count = counter.count(c);

        Long finish = System.nanoTime();

        Duration eta = Duration.ofNanos(finish - start);

        log.info("There are {} character '{}' in the file '{}'", count, c, counter.getFile());

        log.info("Elapsed time = {} ns", eta.toNanos());
    }

    @Override
    public void run(String... args) throws Exception {
        var character = 'a';
//        var simpleCounter = new FileInputStreamCharacterCounter("/home/khietbt/Desktop/Head_First_Java_A_Brain-Friendly_Guide.pdf");
        var simpleCounter = new FileInputStreamCharacterCounter("/home/khietbt/largefile");

        this.count(character, simpleCounter);
    }
}
