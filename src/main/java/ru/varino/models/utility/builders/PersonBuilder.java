package ru.varino.models.utility.builders;

import ru.varino.models.Country;
import ru.varino.models.Person;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PersonBuilder implements Builder<Person> {
    private String name; //���� �� ����� ���� null, ������ �� ����� ���� ������
    private java.time.LocalDateTime birthday; //���� ����� ���� null
    private Long weight; //���� ����� ���� null, �������� ���� ������ ���� ������ 0
    private Country nationality; //���� �� ����� ���� null


    public String getName() {
        return name;
    }

    public PersonBuilder buildName(String name) {
        if (name == null) throw new IllegalArgumentException("��� �� ����� ���� null");
        if (name.isEmpty()) throw new IllegalArgumentException("��� �� ����� ���� ������");
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
        if (weight != null && weight <= 0) throw new IllegalArgumentException("��� ������ ���� ������ 0");
        this.weight = weight;
        return this;
    }

    public Country getNationality() {
        return nationality;
    }

    public PersonBuilder buildNationality(Country nationality) {
        if (nationality == null) throw new IllegalArgumentException("�������������� �� ����� ���� null");

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
