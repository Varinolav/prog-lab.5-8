package ru.varino.models.utility;


import ru.varino.models.Country;
import ru.varino.models.Movie;
import ru.varino.models.MovieGenre;
import ru.varino.models.utility.builders.CoordinatesBuilder;
import ru.varino.models.utility.builders.MovieBuilder;
import ru.varino.models.utility.builders.PersonBuilder;
import ru.varino.utility.io.Console;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Function;

public class InteractiveMovieCreator {

    public static Movie create(Console console, Scanner scanner) throws InterruptedException {
        Function<String, Country> CountryValueOf = x -> {
            try {
                return Country.valueOf(x);
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Поле должно быть типа Country");
            }
        };

        Function<String, MovieGenre> GenreValueOf = x -> {
            try {
                return MovieGenre.valueOf(x);
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Поле должно быть типа MovieGenre");
            }
        };

        Function<String, Double> parseDouble = x -> {
            try {
                return Double.parseDouble(x);
            } catch (NumberFormatException e) {
                throw new NumberFormatException("Поле должно быть типа double");
            }
        };

        Function<String, Integer> parseInt = x -> {
            try {
                return Integer.parseInt(x);
            } catch (NumberFormatException e) {
                throw new NumberFormatException("Поле должно быть типа integer");
            }
        };

        Function<String, Long> parseLong = x -> {
            try {
                return Long.parseLong(x);
            } catch (NumberFormatException e) {
                throw new NumberFormatException("Поле должно быть типа Long");
            }
        };

        Function<String, LocalDateTime> parseLocalDateTime = x -> LocalDateTime.parse(x, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

        MovieBuilder movieBuilder = new MovieBuilder();
        movieBuilder.buildId();
        input("Введите имя: ", console, movieBuilder::buildName, String::valueOf, scanner);

        CoordinatesBuilder coordinatesBuilder = new CoordinatesBuilder();
        input("Введите координату X: ", console, coordinatesBuilder::buildX, parseDouble, scanner);
        input("Введите координату Y: ", console, coordinatesBuilder::buildY, parseDouble, scanner);
        movieBuilder.buildCoordinates(coordinatesBuilder.build());

        movieBuilder.buildCreationDate(LocalDate.now());
        input("Введите число оскаров: ", console, movieBuilder::buildOscarsCount, parseInt, scanner);
        input("Введите число кассовых сборов: ", console, movieBuilder::buildTotalBoxOffice, parseInt, scanner);
        input("Введите слоган: ", console, movieBuilder::buildTagline, String::valueOf, scanner);
        input("Введите жанр: (Возможные варианты - %s) ".formatted(MovieGenre.getNames()), console, movieBuilder::buildGenre, GenreValueOf, scanner);

        PersonBuilder personBuilder = new PersonBuilder();
        input("Введите имя режиссера: ", console, personBuilder::buildName, String::valueOf, scanner);
        input("Введите дату рождения режиссера: ", console, personBuilder::buildBirthday, parseLocalDateTime, scanner);
        input("Введите вес режиссера: ", console, personBuilder::buildWeight, parseLong, scanner);
        input("Введите национальность режиссера: (Возможные варианты - %s) ".formatted(Country.getNames()), console, personBuilder::buildNationality, CountryValueOf, scanner);
        movieBuilder.buildDirector(personBuilder.build());

        return movieBuilder.build();
    }


    private static <T> void input(String prompt,
                                  Console console,
                                  Consumer<T> setter,
                                  Function<String, T> parser,
                                  Scanner scanner) throws InterruptedException {
        while (true) {
            try {
                console.printf(prompt);
                String input = scanner.nextLine().trim();
                if (input.equals("q")) {
                    throw new InterruptedException("Ввод прекращен");
                }

                if (input.isEmpty()) {
                    setter.accept(null);
                } else {
                    setter.accept(parser.apply(input));
                }

                return;

            } catch (IllegalArgumentException e) {
                console.printerr(e.getMessage());
            } catch (DateTimeParseException e) {
                console.printerr("Дата должна быть введена в формате: yyyy-MM-dd HH:mm");
            }
        }

    }


}


