package io.github.khietbt.problems.onebillionrows;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;

public class OneBillionRows {
    public static void main(String[] args) throws IOException, URISyntaxException {
        final String fileName = "/weather_stations.csv";

        try (var is = OneBillionRows.class.getResourceAsStream(fileName)) {
            assert is != null;

            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            reader.lines().forEach(
                    System.out::println
            );
        }
    }
}
