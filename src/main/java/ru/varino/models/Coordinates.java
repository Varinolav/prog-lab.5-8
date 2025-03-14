package ru.varino.models;


import ru.varino.models.utility.builders.CoordinatesBuilder;
import ru.varino.utility.Validatable;

/**
 * Модель координат
 */
public final class Coordinates implements Validatable {
    private final double x;
    private final double y; //Максимальное значение поля: 522

    /**
     * Конструктор координат
     *
     * @param builder {@link CoordinatesBuilder} билдер координат
     */
    public Coordinates(CoordinatesBuilder builder) {
        x = builder.getX();
        y = builder.getY();
    }

    public double getX() {
        return x;
    }


    public double getY() {
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
        return y <= 522;
    }
}