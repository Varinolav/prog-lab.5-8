package ru.varino.commands;

import ru.varino.exceptions.PermissionDeniedException;
import ru.varino.managers.CollectionManager;
import ru.varino.managers.FileManager;
import ru.varino.managers.ParseManager;
import ru.varino.models.Movie;
import ru.varino.utility.communication.RequestEntity;
import ru.varino.utility.communication.ResponseEntity;

import java.util.Hashtable;

public class Save extends Command {
    private final String fileName;
    private final ParseManager parseManager;
    private final FileManager fileManager;
    private final CollectionManager collectionManager;

    public Save(String fileName, ParseManager parseManager, FileManager fileManager, CollectionManager collectionManager) {
        super("save", "сохранить коллекцию в файл");
        this.fileName = fileName;
        this.parseManager = parseManager;
        this.fileManager = fileManager;
        this.collectionManager = collectionManager;
    }

    @Override
    public ResponseEntity execute(RequestEntity req) {
        String args = req.getParams();
        if (!args.isEmpty()) return ResponseEntity.badRequest().body("Неверные аргументы");
        try {
            Hashtable<Integer, Movie> collection = collectionManager.getCollection();
            String json = parseManager.getJsonFromHashTable(collection);
            fileManager.write(fileName, json);
        } catch (PermissionDeniedException e) {
            return ResponseEntity.badRequest().body(e.getMessage());

        }
        return ResponseEntity.ok().body("Коллекция сохранена в файл");
    }
}
