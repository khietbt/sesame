package io.github.khietbt.problems.characterinfilecounter;

import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.FileReader;

public class BufferedReaderCharacterCounter extends AbstractCharacterInFileCounter {
    public BufferedReaderCharacterCounter(String file) {
        super(file);
    }

    @Override
    @SneakyThrows
    public Long count(char letter) {
        Long count = 0L;

        try (var reader = new BufferedReader(new FileReader(this.getFile()))) {
            int character;

            /* Read until the end of file. */
            while ((character = reader.read()) != -1) {
                if (character == letter) {
                    count++;
                }
            }
        }

        return count;
    }
}
