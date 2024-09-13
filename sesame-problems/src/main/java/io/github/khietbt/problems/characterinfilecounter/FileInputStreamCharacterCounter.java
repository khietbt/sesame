package io.github.khietbt.problems.characterinfilecounter;

import lombok.SneakyThrows;

import java.io.FileInputStream;

public class FileInputStreamCharacterCounter extends AbstractCharacterInFileCounter {
    public FileInputStreamCharacterCounter(String file) {
        super(file);
    }

    @Override
    @SneakyThrows
    public Long count(char letter) {
        Long count = 0L;
        int processed = 0;

        try (FileInputStream fis = new FileInputStream(this.getFile())) {
            int character;

            /* Read until the end of file. */
            while ((character = fis.read()) != -1) {
                if (character == letter) {
                    count++;
                }

                processed++;
            }
        }

        return count;
    }
}
