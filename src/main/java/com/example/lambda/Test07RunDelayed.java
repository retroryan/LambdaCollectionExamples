package com.example.lambda;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test07RunDelayed {
    public static void main(String[] args) {
        DelayGetAirportFromCode("SLC");
    }


    public static String DelayGetAirportFromCode(String iata) {
        return Delay.runDelayed(Test07RunDelayed::getAirports, iata);
    }

    private static String getAirports(String iata) {
        try {
            Stream<String> lines = Files.lines(Paths.get("data/airline-flights/airports.csv"));

            Consumer<String> stringConsumer = line -> System.out.println("line = " + line);
            // lines.peek(stringConsumer).count();

            List<Airport> airportList = lines
                    .skip(1)
                    //.peek(stringConsumer)
                    .map(line -> Airport.createAirport(line))
                    .collect(Collectors.toList());

            airportList.forEach(System.out::println);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}
