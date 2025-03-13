package ru.varino.commands;

import ru.varino.managers.CollectionManager;
import ru.varino.models.Movie;
import ru.varino.models.utility.InteractiveMovieCreator;
import ru.varino.utility.communication.RequestEntity;
import ru.varino.utility.communication.ResponseEntity;
import ru.varino.utility.io.Console;

import java.util.Scanner;

public class Update extends Command {
    private final CollectionManager collectionManager;
    private final Console console;
    private final Scanner scanner;

    public Update(CollectionManager collectionManager, Scanner scanner, Console console) {
        super("update <id>", "обновить значение элемента коллекции, id которого равен заданному");
        this.collectionManager = collectionManager;
        this.scanner = scanner;
        this.console = console;
    }

    @Override
    public ResponseEntity execute(RequestEntity req) {
        String args = req.getParams();
        if (args.isEmpty()) return ResponseEntity.badRequest().body("Неверные аргументы");
        try {
            Integer id = Integer.parseInt(args);
            if (collectionManager.getElementById(id) == null) return ResponseEntity.badRequest()
                    .body("Элемента с таким id не существует в коллекции");
            Movie movie = InteractiveMovieCreator.create(console, scanner);
            collectionManager.addElementToCollection(id, movie);
            return ResponseEntity.ok().body("Элемент успешно перезаписан");

        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body("Ключ должен быть Int");
        } catch (InterruptedException e) {
            return ResponseEntity.serverError().body("Ввод прекращен");

        }
    }
}
