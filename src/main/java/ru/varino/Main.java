package ru.varino;


import ru.varino.commands.*;
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
                .add("insert", new Insert(collectionManager, scanner, console))
                .add("update", new Update(collectionManager, scanner, console))
                .add("remove_key", new RemoveKey(collectionManager))
                .add("clear", new Clear(collectionManager))
                .add("save", new Save(fileName, parseManager, fileManager, collectionManager))
                .add("exit", new Exit())
                .add("replace_if_greater", new ReplaceIf("greater", collectionManager, scanner, console))
                .add("replace_if_lower", new ReplaceIf("lower", collectionManager, scanner, console))
                .add("remove_lower_key", new RemoveLowerKey(collectionManager))
                .add("average_of_total_box_office", new AverageTotalBoxOffice(collectionManager))
                .add("min_by_director", new MinByDirector(collectionManager))
                .add("count_less_than_genre", new CountLessGenre(collectionManager));

        InputManager inputManager = InputManager.getInstance(console, commandManager);

        inputManager.interactiveRun();


    }
}