package io.github.khietbt.problems.onemillitionrowproblem;

import lombok.SneakyThrows;

import java.nio.channels.FileChannel;
import java.nio.file.Paths;

public class FileChannelCounter extends AbstractCharacterInFileCounter {
    public FileChannelCounter(String file) {
        super(file);
    }

    @Override
    @SneakyThrows
    public Long count(char letter) {
        try (var channel = FileChannel.open(Paths.get(this.getFile()))) {
            Long count = 0L;

            var size = channel.size();

            var buffer = channel.map(FileChannel.MapMode.READ_ONLY, 0, size);

            while (buffer.hasRemaining()) {
                if (buffer.get() == letter) {
                    count++;
                }
            }

            return count;
        }
    }
}
