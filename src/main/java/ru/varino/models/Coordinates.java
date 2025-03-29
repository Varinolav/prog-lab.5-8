package ru.varino.models;


import ru.varino.models.utility.builders.CoordinatesBuilder;
import ru.varino.utility.Validatable;

import java.math.BigDecimal;

/**
 * Модель координат
 */
public final class Coordinates implements Validatable {
    private final BigDecimal x;
    private final BigDecimal y; //Максимальное значение поля: 522

    /**
     * Конструктор координат
     *
     * @param builder {@link CoordinatesBuilder} билдер координат
     */
    public Coordinates(CoordinatesBuilder builder) {
        x = builder.getX();
        y = builder.getY();
    }

    public BigDecimal getX() {
        return x;
    }


    public BigDecimal getY() {
        return y;
    }


    @Override
    public String toString() {
        return x + " ; " + y;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean validate() {
        return y.compareTo(new BigDecimal(522)) <= 0;
    }
}