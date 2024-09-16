package io.github.khietbt.problems.onebillionrows;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.TreeMap;

public class OneBillionRows {
    public static Map<String, Double> data = new TreeMap<>();

    public static void parseData(String s) {
        final var SEMI_COLON = ";";
        final var index = s.indexOf(SEMI_COLON);

        if (index > 0) {
            var values = s.split(SEMI_COLON);
            var city = values[0];
            var temperature = Double.parseDouble(values[1]);

            OneBillionRows.data.put(city, temperature);
        }
    }

    public static void main(String[] args) throws IOException, URISyntaxException {

        var fileName = "/weather_stations.csv";

        var start = System.currentTimeMillis();

        try (var is = OneBillionRows.class.getResourceAsStream(fileName)) {
            assert is != null;

            var buffer = new BufferedReader(new InputStreamReader(is));

            buffer.lines().forEach(OneBillionRows::parseData);
        }

        var end = System.currentTimeMillis();

        System.out.println("eta in ms: " + (end - start));
    }
}
