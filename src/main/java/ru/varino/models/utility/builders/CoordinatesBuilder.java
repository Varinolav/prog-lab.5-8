package ru.varino.models.utility.builders;

import ru.varino.models.Coordinates;

public class CoordinatesBuilder implements Builder<Coordinates> {
    private double x;
    private double y;


    public double getX() {
        return x;
    }

    public CoordinatesBuilder buildX(Double x) {
        if (x == null) throw new IllegalArgumentException("����� ����������� ������ �������� X");
        try {
            this.x = x;
        } catch (NumberFormatException e){
            throw new NumberFormatException("X ������ ���� ���� double");
        }
        return this;
    }

    public double getY() {

        return y;
    }

    public CoordinatesBuilder buildY(Double y) {
        if (y == null) throw new IllegalArgumentException("����� ����������� ������ �������� Y");
        if (y > 522) throw new IllegalArgumentException("������������ �������� ���� Y: 522");

        try {
            this.y = y;
        } catch (NumberFormatException e){
            System.out.println("shit");
            throw new NumberFormatException("Y ������ ���� ���� double");
        }
        return this;
    }

    @Override
    public Coordinates build() {
        return new Coordinates(this);
    }

    @Override
    public void reset() {
        this.x = 0;
        this.y = 0;

    }
}
