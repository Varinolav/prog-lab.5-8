package ru.varino.exceptions;

public class EmptyFileException extends Exception {
    public EmptyFileException(String fileName) {
        super(fileName);
    }

    @Override
    public String getMessage() {
        return "���� %s ������".formatted(super.getMessage());
    }
}
