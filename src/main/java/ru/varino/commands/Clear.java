package ru.varino.commands;

import ru.varino.managers.CollectionManager;
import ru.varino.managers.CommandManager;
import ru.varino.utility.communication.RequestEntity;
import ru.varino.utility.communication.ResponseEntity;

public class Clear extends Command {
    private final CollectionManager collectionManager;

    public Clear(CollectionManager collectionManager) {
        super("clear", "�������� ���������");
        this.collectionManager = collectionManager;
    }

    @Override
    public ResponseEntity execute(RequestEntity req) {
        String args = req.getParams();
        if (!args.isEmpty()) return ResponseEntity.badRequest().body("�������� ���������");
        collectionManager.clearCollection();
        return ResponseEntity.ok().body("��������� �������");


    }

}
