package ru.varino.models.utility.builders;

import ru.varino.models.Coordinates;
import ru.varino.models.Movie;
import ru.varino.models.MovieGenre;
import ru.varino.models.Person;

import java.time.LocalDate;

public class MovieBuilder implements Builder<Movie> {
    private String name;
    private Coordinates coordinates;
    private java.time.LocalDate creationDate;
    private Integer oscarsCount;
    private int totalBoxOffice;
    private String tagline;
    private MovieGenre genre;
    private Person director;


    public MovieBuilder buildName(String name) {
        if (name == null) throw new IllegalArgumentException("��� �� ����� ���� null");
        if (name.isEmpty()) throw new IllegalArgumentException("��� �� ����� ���� ������");
        this.name = name;
        return this;
    }

    public MovieBuilder buildCoordinates(Coordinates coords) {
        if (coords == null) throw new IllegalArgumentException("���������� �� ����� ���� null");


        this.coordinates = coords;
        return this;
    }

    public MovieBuilder buildCreationDate(LocalDate creationDate) {
        if (creationDate == null) throw new IllegalArgumentException("���� �������� �� ����� ���� null");

        this.creationDate = creationDate;
        return this;

    }

    public MovieBuilder buildOscarsCount(Integer oscarsCount) {
        if (oscarsCount != null && oscarsCount <= 0)
            throw new IllegalArgumentException("���������� ������� ������ ���� ������ 0");
        this.oscarsCount = oscarsCount;
        return this;

    }

    public MovieBuilder buildTotalBoxOffice(Integer totalBoxOffice) {
        if (totalBoxOffice == null) throw new IllegalArgumentException("����� ����������� ������ �������� ����");
        if (totalBoxOffice <= 0)
            throw new IllegalArgumentException("���������� ������ ������ ���� ������ 0");

        this.totalBoxOffice = totalBoxOffice;
        return this;

    }

    public MovieBuilder buildTagline(String tagline) {
        this.tagline = tagline;
        return this;

    }

    public MovieBuilder buildGenre(MovieGenre genre) {

        if (genre == null) throw new IllegalArgumentException("���� �� ����� ���� null");

        this.genre = genre;
        return this;

    }

    public MovieBuilder buildDirector(Person director) {
        this.director = director;
        return this;

    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public Integer getOscarsCount() {
        return oscarsCount;
    }

    public int getTotalBoxOffice() {
        return totalBoxOffice;
    }

    public String getTagline() {
        return tagline;
    }

    public MovieGenre getGenre() {
        return genre;
    }

    public Person getDirector() {
        return director;
    }

    @Override
    public void reset() {
        name = null;
        coordinates = null;
        creationDate = null;
        oscarsCount = null;
        totalBoxOffice = 0;
        tagline = null;
        genre = null;
        director = null;
    }

    @Override
    public Movie build() {
        return new Movie(this);
    }
}
