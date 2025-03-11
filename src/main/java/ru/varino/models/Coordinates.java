package ru.varino.models;


import ru.varino.models.utility.builders.CoordinatesBuilder;
import ru.varino.utility.Validatable;

public final class Coordinates implements Validatable {
    private double x;
    private double y; //Максимальное значение поля: 522

    public Coordinates(CoordinatesBuilder builder) {
        x = builder.getX();
        y = builder.getY();
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }


    @Override
    public String toString() {
        return x + " ; " + y;
    }

    @Override
    public boolean validate() {
        return y <= 522;
    }
}