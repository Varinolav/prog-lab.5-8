package ru.varino.models;

import ru.varino.models.utility.builders.MovieBuilder;
import ru.varino.utility.Entity;
import ru.varino.utility.Validatable;

import java.time.LocalDate;

public final class Movie extends Entity implements Comparable<Movie>, Validatable {

    private Integer id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Integer oscarsCount; //Значение поля должно быть больше 0, Поле может быть null
    private int totalBoxOffice; //Значение поля должно быть больше 0
    private String tagline; //Поле может быть null
    private MovieGenre genre; //Поле не может быть null
    private Person director; //Поле может быть null

    public Movie(MovieBuilder builder) {
        id = builder.getId();
        name = builder.getName();
        coordinates = builder.getCoordinates();
        creationDate = builder.getCreationDate();
        oscarsCount = builder.getOscarsCount();
        totalBoxOffice = builder.getTotalBoxOffice();
        tagline = builder.getTagline();
        genre = builder.getGenre();
        director = builder.getDirector();


    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {

        this.creationDate = creationDate;
    }

    public Integer getOscarsCount() {
        return oscarsCount;
    }

    public void setOscarsCount(Integer oscarsCount) {

        this.oscarsCount = oscarsCount;
    }

    public int getTotalBoxOffice() {
        return totalBoxOffice;
    }

    public void setTotalBoxOffice(int totalBoxOffice) {

        this.totalBoxOffice = totalBoxOffice;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public MovieGenre getGenre() {
        return genre;
    }

    public void setGenre(MovieGenre genre) {

        this.genre = genre;
    }

    public Person getDirector() {
        return director;
    }

    public void setDirector(Person director) {
        this.director = director;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public int compareTo(Movie m) {
        int delta;
        if (this.oscarsCount == null || m.getOscarsCount() == null) {
            delta = this.totalBoxOffice - m.getTotalBoxOffice();

        } else {
            delta = (this.oscarsCount + this.totalBoxOffice) - (m.getOscarsCount() + m.getTotalBoxOffice());
        }
        return Integer.compare(delta, 0);
    }


    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coordinates=" + coordinates +
                ", creationDate=" + creationDate +
                ", oscarsCount=" + oscarsCount +
                ", totalBoxOffice=" + totalBoxOffice +
                ", tagline='" + tagline + '\'' +
                ", genre=" + genre +
                ", director=" + director +
                '}';
    }

    @Override
    public boolean validate() {
        if (id == null || id <=0 ) return false;
        if (name == null || name.isBlank()) return false;
        if (coordinates == null || !coordinates.validate()) return false;
        if (creationDate == null) return false;
        if (oscarsCount != null && oscarsCount <= 0) return false;
        if (totalBoxOffice <= 0) return false;
        if (genre == null) return false;
        if (director != null && !director.validate()) return false;
        return true;
    }
}