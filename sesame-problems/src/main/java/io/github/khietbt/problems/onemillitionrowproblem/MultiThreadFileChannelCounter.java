package io.github.khietbt.problems.onemillitionrowproblem;

import lombok.Getter;
import lombok.SneakyThrows;

import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.util.ArrayList;

class CountingThread extends Thread {
    private final MappedByteBuffer buffer;
    private final char letter;

    @Getter
    private Long count;


    public CountingThread(MappedByteBuffer buffer, char letter) {
        this.buffer = buffer;
        this.letter = letter;
        this.count = 0L;
    }

    @Override
    public void run() {
        while (buffer.hasRemaining()) {
            if (buffer.get() == letter) {
                count++;
            }
        }
    }
}

public class MultiThreadFileChannelCounter extends AbstractCharacterInFileCounter {
    public MultiThreadFileChannelCounter(String file) {
        super(file);
    }

    @Override
    @SneakyThrows
    public Long count(char letter) {
        try (var channel = FileChannel.open(Paths.get(this.getFile()))) {
            Long count = 0L;

            var fileSize = channel.size();
            var totalThreads = Runtime.getRuntime().availableProcessors();

            var threads = new ArrayList<CountingThread>();

            for (var i = 0; i < totalThreads; i++) {
                var chunkSize = fileSize / totalThreads;
                var start = i * fileSize / totalThreads;

                var buffer = channel.map(FileChannel.MapMode.READ_ONLY, start, chunkSize);

                var thread = new CountingThread(buffer, letter);

                threads.add(thread);
                thread.start();
            }

            for (var thread : threads) {
                thread.join();
            }

            return threads.stream().map(CountingThread::getCount).reduce(0L, Long::sum);
        }
    }
}
