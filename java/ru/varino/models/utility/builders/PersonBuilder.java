package ru.varino.models.utility.builders;

import ru.varino.models.Country;
import ru.varino.models.Person;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PersonBuilder implements Builder<Person> {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private java.time.LocalDateTime birthday; //Поле может быть null
    private Long weight; //Поле может быть null, Значение поля должно быть больше 0
    private Country nationality; //Поле не может быть null


    public String getName() {
        return name;
    }

    public PersonBuilder buildName(String name) {
        if (name == null) throw new IllegalArgumentException("Имя не может быть null");
        if (name.isEmpty()) throw new IllegalArgumentException("Имя не может быть пустым");
        this.name = name;
        return this;
    }

    public LocalDateTime getBirthday() {
        return birthday;
    }

    public PersonBuilder buildBirthday(LocalDateTime birthday) {
        this.birthday = birthday;
        return this;
    }

    public Long getWeight() {
        return weight;
    }

    public PersonBuilder buildWeight(Long weight) {
        if (weight != null && weight <= 0) throw new IllegalArgumentException("Вес должен быть больше 0");
        this.weight = weight;
        return this;
    }

    public Country getNationality() {
        return nationality;
    }

    public PersonBuilder buildNationality(Country nationality) {
        if (nationality == null) throw new IllegalArgumentException("Национальность не может быть null");

        this.nationality = nationality;
        return this;
    }

    @Override
    public Person build() {
        return new Person(this);
    }


    @Override
    public void reset() {
        name = null;
        birthday = null;
        weight = null;
        nationality = null;


    }

}
