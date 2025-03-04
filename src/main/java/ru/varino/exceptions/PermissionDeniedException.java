package ru.varino.exceptions;

public class PermissionDeniedException extends Exception {
    public PermissionDeniedException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return "Недостаточно прав на " + super.getMessage();
    }
}

