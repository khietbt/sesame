package io.github.khietbt.problems.characterinfilecounter;

import java.time.Duration;

public class CharacterInFileCounterWithMultiThreadFileChannel {
    public static void main(String[] args) {
        var character = 'a';
        var simpleCounter = new MultiThreadFileChannelCounter("/home/khietbt/largefile");

        CharacterInFileCounterWithMultiThreadFileChannel.count(character, simpleCounter);
    }

    private static void count(char c, AbstractCharacterInFileCounter counter) {
        Long start = System.nanoTime();

        Long count = counter.count(c);

        Long finish = System.nanoTime();

        Duration eta = Duration.ofNanos(finish - start);

        String message = String.format("There are %d character of '%c' in the file '%s' in %d ms", count, c, counter.getFile(), eta.toMillis());

        System.out.println(message);
    }
}
