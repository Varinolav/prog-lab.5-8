package ru.varino.utility.io;

import ru.varino.utility.communication.ResponseEntity;

/**
 * Консоль для ввода команд и вывода результата
 */
public interface Console {
    void println(String text);
    void printerr(String text);
    void printf(String text);
    void printResponse(ResponseEntity response);
}