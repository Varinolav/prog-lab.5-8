package ru.varino.commands;

import ru.varino.managers.CollectionManager;
import ru.varino.utility.communication.RequestEntity;
import ru.varino.utility.communication.ResponseEntity;

public class Show extends Command {
    private final CollectionManager collectionManager;

    public Show(CollectionManager collectionManager) {
        super("show", "вывести в стандартный поток вывода все элементы коллекции в строковом представлении");
        this.collectionManager = collectionManager;
    }

    @Override
    public ResponseEntity execute(RequestEntity req) {
        String args = req.getParams();
        if (!args.isEmpty()) return ResponseEntity.badRequest().body("Ќеверные аргументы");

        return ResponseEntity.ok().body(collectionManager.toString());


    }

}
