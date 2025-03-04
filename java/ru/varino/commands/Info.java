package ru.varino.commands;

import ru.varino.managers.CollectionManager;
import ru.varino.utility.communication.RequestEntity;
import ru.varino.utility.communication.ResponseEntity;

public class Info extends Command {
    private CollectionManager collectionManager;

    public Info(CollectionManager collectionManager) {
        super("info",
                "������� � ����������� ����� ������ ���������� � ��������� (���, ���� �������������, ���������� ��������� � �.�.)");
        this.collectionManager = collectionManager;

    }

    public ResponseEntity execute(RequestEntity req) {
        String args = req.getParams();
        if (!args.isEmpty()) return ResponseEntity.badRequest().body("�������� ���������");

        return ResponseEntity.ok().body(collectionManager.getCollectionInfo());
    }
}
