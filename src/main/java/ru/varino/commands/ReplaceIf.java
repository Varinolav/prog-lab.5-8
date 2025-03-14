package ru.varino.commands;

import ru.varino.managers.CollectionManager;
import ru.varino.managers.ScannerManager;
import ru.varino.models.Movie;
import ru.varino.models.utility.InteractiveMovieCreator;
import ru.varino.utility.communication.RequestEntity;
import ru.varino.utility.communication.ResponseEntity;
import ru.varino.utility.io.Console;

import java.util.Scanner;

public class ReplaceIf extends Command {
    private final CollectionManager collectionManager;
    private final Console console;
    private final ScannerManager scannerManager;

    private final String type;

    public ReplaceIf(String type, CollectionManager collectionManager, ScannerManager scannerManager, Console console) {
        super("replace_if_%s <id>".formatted(type), "заменить значение по ключу, если новое значение " + (type.equals("больше") ? "больше" : "меньше") + " старого");
        this.collectionManager = collectionManager;
        this.console = console;
        this.scannerManager = scannerManager;
        this.type = type;
    }

    @Override
    public ResponseEntity execute(RequestEntity req) {
        String args = req.getParams();
        if (args.isEmpty()) return ResponseEntity.badRequest().body("Неверные аргументы");
        try {
            Integer id = Integer.parseInt(args);
            if (collectionManager.getElementById(id) == null) return ResponseEntity.badRequest()
                    .body("Элемента с таким id не существует");
            Movie movie = InteractiveMovieCreator.create(console, scannerManager.getCurrentScanner());
            Boolean comparisonResult = null;
            if (type.equals("greater")) {
                comparisonResult = movie.compareTo(collectionManager.getElementById(id)) > 0;
            }
            if (type.equals("lower")) {
                comparisonResult = movie.compareTo(collectionManager.getElementById(id)) < 0;
            }
            if (comparisonResult == null) throw new RuntimeException();
            if (comparisonResult) {
                collectionManager.addElementToCollection(id, movie);
                return ResponseEntity.ok().body("Элемент успешно заменен");
            } else {
                return ResponseEntity.badRequest().body("Элемент не заменен");
            }
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body("Ключ должен быть Int");
        } catch (InterruptedException e) {
            return ResponseEntity.serverError().body("Ввод прекращен");

        }
    }
}
