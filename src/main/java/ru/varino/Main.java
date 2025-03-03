package ru.varino;


import ru.varino.commands.Help;
import ru.varino.commands.Info;
import ru.varino.commands.Show;
import ru.varino.commands.Insert;
import ru.varino.managers.*;
import ru.varino.utility.io.Console;
import ru.varino.utility.io.StandartConsole;

import ru.varino.models.Movie;

import java.util.Hashtable;
import java.util.Scanner;

public class Main {
    private static Integer bibi;

    public static void main(String[] args) {
        System.out.println("Initialization...");

        String fileName = "D:\\Java projects\\lab5\\src\\main\\java\\ru\\varino\\start.json";

        Console console = new StandartConsole();

        if (fileName == null) {
            console.println("Введите имя файла в виде аргумента командной строки");
            System.exit(0);
        }
        FileManager fileManager = FileManager.getInstance(console);
        ParseManager parseManager = ParseManager.getInstance(console);
        CollectionManager collectionManager = CollectionManager.getInstance();

        String json = fileManager.read(fileName);
        Hashtable<Integer, Movie> initCollection = parseManager.getHashTableFromJson(json);
        collectionManager.setCollection(initCollection);

        Scanner scanner = new Scanner(System.in);
        InputManager.setUsedScanner(scanner);


        CommandManager commandManager = CommandManager.getInstance();
        commandManager
                .add("show", new Show(collectionManager))
                .add("help", new Help(commandManager))
                .add("info", new Info(collectionManager))
                .add("insert", new Insert(collectionManager, scanner, console));

        InputManager inputManager = InputManager.getInstance(console, commandManager);

        inputManager.interactiveRun();


    }
}