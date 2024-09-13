package io.github.khietbt.problems.characterinfilecounter;

import lombok.Getter;
import lombok.SneakyThrows;

import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.util.ArrayList;

class VirtualCountingThread extends Thread {
    private final MappedByteBuffer buffer;
    private final char letter;

    @Getter
    private Long count;


    public VirtualCountingThread(MappedByteBuffer buffer, char letter) {
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

public class VirtualMultiThreadFileChannelCounter extends AbstractCharacterInFileCounter {
    public VirtualMultiThreadFileChannelCounter(String file) {
        super(file);
    }

    @Override
    @SneakyThrows
    public Long count(char letter) {
        try (var channel = FileChannel.open(Paths.get(this.getFile()))) {
            Long count = 0L;

            var fileSize = channel.size();
            var totalThreads = Runtime.getRuntime().availableProcessors();

            var threads = new ArrayList<Thread>();
            var counters = new ArrayList<VirtualCountingThread>();

            for (var i = 0; i < totalThreads; i++) {
                var chunkSize = fileSize / totalThreads;
                var start = i * fileSize / totalThreads;

                var buffer = channel.map(FileChannel.MapMode.READ_ONLY, start, chunkSize);

                var counter = new VirtualCountingThread(buffer, letter);
                var thread = Thread.startVirtualThread(counter);

                threads.add(thread);
                counters.add(counter);
            }

            for (var thread : threads) {
                thread.join();
            }

            return counters.stream().map(VirtualCountingThread::getCount).reduce(0L, Long::sum);
        }
    }
}
