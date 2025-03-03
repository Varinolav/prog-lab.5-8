package ru.varino.commands;

import ru.varino.managers.CollectionManager;
import ru.varino.models.Movie;
import ru.varino.models.utility.MovieCreator;
import ru.varino.utility.communication.RequestEntity;
import ru.varino.utility.communication.ResponseEntity;
import ru.varino.utility.io.Console;

import java.util.Scanner;

public class Update extends Command {
    private final CollectionManager collectionManager;
    private final Console console;
    private final Scanner scanner;

    public Update(CollectionManager collectionManager, Scanner scanner, Console console) {
        super("update <id>", "вывести в стандартный поток вывода все элементы коллекции в строковом представлении");
        this.collectionManager = collectionManager;
        this.scanner = scanner;
        this.console = console;
    }

    @Override
    public ResponseEntity execute(RequestEntity req) {
        String args = req.getParams();
        if (args.isEmpty()) return ResponseEntity.badRequest().body("Ќеверные аргументы");
        try {
            Integer id = Integer.parseInt(args);
            if (collectionManager.getElementById(id) == null) return ResponseEntity.badRequest()
                    .body("Ёлемента с таким id не существует в коллекции");
            Movie movie = MovieCreator.create(console, scanner);
            collectionManager.addElementToCollection(id, movie);
            return ResponseEntity.ok().body("Ёлемент успешно перезаписан");

        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body(" люч должен быть Int");
        } catch (InterruptedException e) {
            return ResponseEntity.serverError().body("¬вод прекращен");

        }
    }
}
