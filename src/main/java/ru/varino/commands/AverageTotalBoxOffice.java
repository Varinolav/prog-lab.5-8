package ru.varino.commands;

import ru.varino.managers.CollectionManager;
import ru.varino.models.Movie;
import ru.varino.utility.communication.RequestEntity;
import ru.varino.utility.communication.ResponseEntity;

public class AverageTotalBoxOffice extends Command {
    private final CollectionManager collectionManager;

    public AverageTotalBoxOffice(CollectionManager collectionManager) {
        super("average_of_total_box_office", "������� ������� �������� ���� totalBoxOffice ��� ���� ��������� ���������");
        this.collectionManager = collectionManager;
    }

    @Override
    public ResponseEntity execute(RequestEntity req) {
        String args = req.getParams();
        if (!args.isEmpty()) return ResponseEntity.badRequest().body("�������� ���������");
        if (collectionManager.getCollection().isEmpty()) return ResponseEntity.badRequest().body("��������� �����");
        long sum = 0;
        for (Movie m : collectionManager.getElements()) {
            sum += m.getTotalBoxOffice();
        }
        long res = sum / collectionManager.getCollection().size();
        return ResponseEntity.ok().body("������� �������� totalBoxOffice: %s".formatted(res));
    }
}
