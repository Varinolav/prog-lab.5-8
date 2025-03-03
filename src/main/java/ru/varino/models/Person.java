package ru.varino.models;


import ru.varino.models.utility.builders.PersonBuilder;

import java.time.LocalDateTime;

public final class Person {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private java.time.LocalDateTime birthday; //Поле может быть null
    private Long weight; //Поле может быть null, Значение поля должно быть больше 0
    private Country nationality; //Поле не может быть null

    public Person(PersonBuilder builder) {
        this.name = builder.getName();
        this.birthday = builder.getBirthday();
        this.weight = builder.getWeight();
        this.nationality = builder.getNationality();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDateTime birthday) {
        this.birthday = birthday;
    }

    public Long getWeight() {
        return weight;
    }

    public void setWeight(Long weight) {
        this.weight = weight;
    }

    public Country getNationality() {
        return nationality;
    }

    public void setNationality(Country nationality) {
        this.nationality = nationality;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", birthday=" + birthday +
                ", weight=" + weight +
                ", nationality=" + nationality +
                '}';
    }
}