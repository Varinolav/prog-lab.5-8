package ru.varino.models.utility;


import ru.varino.managers.utility.adapters.LocalDateAdapter;
import ru.varino.managers.utility.adapters.LocalDateTimeAdapter;
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

public class MovieCreator {

    public static Movie create(Console console, Scanner scanner) throws InterruptedException {
        Function<String, Country> CountryValueOf = x -> {
            try {
                return Country.valueOf(x);
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("���� ������ ���� ���� Country");
            }
        };

        Function<String, MovieGenre> GenreValueOf = x -> {
            try {
                return MovieGenre.valueOf(x);
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("���� ������ ���� ���� MovieGenre");
            }
        };

        Function<String, Double> parseDouble = x -> {
            try {
                return Double.parseDouble(x);
            } catch (NumberFormatException e) {
                throw new NumberFormatException("���� ������ ���� ���� double");
            }
        };

        Function<String, Integer> parseInt = x -> {
            try {
                return Integer.parseInt(x);
            } catch (NumberFormatException e) {
                throw new NumberFormatException("���� ������ ���� ���� integer");
            }
        };

        Function<String, Long> parseLong = x -> {
            try {
                return Long.parseLong(x);
            } catch (NumberFormatException e) {
                throw new NumberFormatException("���� ������ ���� ���� Long");
            }
        };

        Function<String, LocalDateTime> parseLocalDateTime = x -> LocalDateTime.parse(x, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

        MovieBuilder movieBuilder = new MovieBuilder();
        input("������� ���: ", console, movieBuilder::buildName, String::valueOf, scanner);

        CoordinatesBuilder coordinatesBuilder = new CoordinatesBuilder();
        input("������� ���������� X: ", console, coordinatesBuilder::buildX, parseDouble, scanner);
        input("������� ���������� Y: ", console, coordinatesBuilder::buildY, parseDouble, scanner);
        movieBuilder.buildCoordinates(coordinatesBuilder.build());

        movieBuilder.buildCreationDate(LocalDate.now());
        input("������� ����� �������: ", console, movieBuilder::buildOscarsCount, parseInt, scanner);
        input("������� ����� �������� ������: ", console, movieBuilder::buildTotalBoxOffice, parseInt, scanner);
        input("������� ������: ", console, movieBuilder::buildTagline, String::valueOf, scanner);
        input("������� ����: (��������� �������� - %s) ".formatted(MovieGenre.getNames()), console, movieBuilder::buildGenre, GenreValueOf, scanner);

        PersonBuilder personBuilder = new PersonBuilder();
        input("������� ��� ���������: ", console, personBuilder::buildName, String::valueOf, scanner);
        input("������� ���� �������� ���������: ", console, personBuilder::buildBirthday, parseLocalDateTime, scanner);
        input("������� ��� ���������: ", console, personBuilder::buildWeight, parseLong, scanner);
        input("������� �������������� ���������: (��������� �������� - %s) ".formatted(Country.getNames()), console, personBuilder::buildNationality, CountryValueOf, scanner);
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
                    throw new InterruptedException("���� ���������");
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
                console.printerr("���� ������� �����������. ���������� ������: yyyy-MM-dd HH:mm");
            }
        }

    }


}


