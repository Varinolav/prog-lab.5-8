package ru.varino.managers;


import java.util.Scanner;

/**
 * Класс для работы со сканерами
 */
public class ScannerManager {
    private static ScannerManager instance;
    private Scanner currentScanner;

    public static ScannerManager getInstance() {
        return instance == null ? instance = new ScannerManager() : instance;
    }

    public Scanner getCurrentScanner() {
        return currentScanner;
    }

    public void setCurrentScanner(Scanner currentScanner) {
        this.currentScanner = currentScanner;
    }
}
