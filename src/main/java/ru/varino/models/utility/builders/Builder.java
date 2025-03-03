package ru.varino.models.utility.builders;

public interface Builder<T> {
    T build();

    void reset();
}
