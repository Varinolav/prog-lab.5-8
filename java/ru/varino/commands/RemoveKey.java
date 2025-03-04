package ru.varino.commands;

import ru.varino.managers.CollectionManager;
import ru.varino.models.Movie;
import ru.varino.models.utility.MovieCreator;
import ru.varino.utility.communication.RequestEntity;
import ru.varino.utility.communication.ResponseEntity;

public class RemoveKey extends Command {
    private final CollectionManager collectionManager;

    public RemoveKey(CollectionManager collectionManager) {
        super("remove_key <id>", "������� ������� �� ��������� �� ��� �����");
        this.collectionManager = collectionManager;
    }

    @Override
    public ResponseEntity execute(RequestEntity req) {
        String args = req.getParams();
        if (args.isEmpty()) return ResponseEntity.badRequest().body("�������� ���������");

        try {
            Integer id = Integer.parseInt(args);
            if (collectionManager.getElementById(id) == null) return ResponseEntity.badRequest()
                    .body("�������� � ����� id �� ���������� � ���������");
            collectionManager.removeElementFromCollection(id);
            return ResponseEntity.ok().body("������� ������ �� ���������");

        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body("���� ������ ���� Int");

        }
    }
}
