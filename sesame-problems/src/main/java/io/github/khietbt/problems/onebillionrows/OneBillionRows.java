package io.github.khietbt.problems.onebillionrows;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.Duration;

public class OneBillionRows {
    public static void main(String[] args) throws IOException, URISyntaxException {
        final String fileName = "/OneBillionRows/weather_stations.csv";
        final Path path = Path.of(OneBillionRows.class.getResource(fileName).toURI());

        long startTime = System.currentTimeMillis();

        try (var fileChannel = FileChannel.open(path, StandardOpenOption.READ)) {
            var buffer = fileChannel.map(FileChannel.MapMode.READ_ONLY, 0, fileChannel.size());

            while (buffer.hasRemaining()) {
                buffer.get();
            }
        }

        long endTime = System.currentTimeMillis();

        final Duration eta = Duration.ofMillis(endTime - startTime);

        System.out.println("Time taken: " + eta.toMillis() + " ms");
    }
}
