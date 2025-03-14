package ru.varino.commands;

import ru.varino.managers.CollectionManager;
import ru.varino.managers.ScannerManager;
import ru.varino.models.Movie;
import ru.varino.models.utility.InteractiveMovieCreator;
import ru.varino.utility.io.Console;
import ru.varino.utility.communication.RequestEntity;
import ru.varino.utility.communication.ResponseEntity;

/**
 * Класс команды Insert
 */
public class Insert extends Command {
    private final CollectionManager collectionManager;
    private final ScannerManager scannerManager;
    private final Console console;

    public Insert(CollectionManager collectionManager, ScannerManager scannerManager, Console console) {
        super("insert <id>", "добавить новый элемент с заданным ключом");
        this.collectionManager = collectionManager;
        this.scannerManager = scannerManager;
        this.console = console;
    }

    /**
     * {@inheritDoc}
     * @param req запрос для выполнения команды
     * @return {@link ResponseEntity}
     */
    @Override
    public ResponseEntity execute(RequestEntity req) {
        String args = req.getParams();
        try {
            Integer id = Integer.parseInt(args);
            if (collectionManager.getElementById(id) != null) return ResponseEntity.badRequest()
                    .body("Элемента с таким id уже существует в коллекции");
            Movie movie = InteractiveMovieCreator.create(console, scannerManager.getCurrentScanner());
            collectionManager.addElementToCollection(id, movie);
            return ResponseEntity.ok().body("Элемент добавлен в коллекцию");

        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body("Ключ должен быть типа Int");
        } catch (InterruptedException e) {
            return ResponseEntity.serverError().body("Ввод прекращен");

        }
    }
}