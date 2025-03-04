package ru.varino.models.utility;

import java.util.function.Function;

@FunctionalInterface
public interface ThrowingFunction<T, R> extends Function<T, R> {

    @Override
    default R apply(T t) {
        try {
            return applyThrows(t);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Значение должно быть типа " + t.getClass().getSimpleName());
        }
    }

    R applyThrows(T t) throws IllegalArgumentException;

}
