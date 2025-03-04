package ru.varino.commands;

import ru.varino.managers.CollectionManager;
import ru.varino.utility.communication.RequestEntity;
import ru.varino.utility.communication.ResponseEntity;

public class RemoveLowerKey extends Command {
    private final CollectionManager collectionManager;

    public RemoveLowerKey(CollectionManager collectionManager) {
        super("remove_lower_key <id>", "������� �� ��������� ��� ��������, ���� ������� ������, ��� ��������");
        this.collectionManager = collectionManager;
    }

    @Override
    public ResponseEntity execute(RequestEntity req) {
        String args = req.getParams();
        if (args.isEmpty()) return ResponseEntity.badRequest().body("�������� ���������");
        try {
            Integer id = Integer.parseInt(args);
            for (Integer k : collectionManager.getElementsIds()) {
                if (k < id) {
                    collectionManager.removeElementFromCollection(k);
                }
            }
            return ResponseEntity.ok().body("�������� ������� �� ���������");

        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body("���� ������ ���� Int");

        }
    }
}
