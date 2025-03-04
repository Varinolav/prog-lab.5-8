package ru.varino.commands;

import ru.varino.managers.CollectionManager;
import ru.varino.models.Movie;
import ru.varino.utility.communication.RequestEntity;
import ru.varino.utility.communication.ResponseEntity;

public class MinByDirector extends Command {
    private final CollectionManager collectionManager;

    public MinByDirector(CollectionManager collectionManager) {
        super("min_by_director", "������� ����� ������ �� ���������, �������� ���� director �������� �������� �����������");
        this.collectionManager = collectionManager;
    }

    @Override
    public ResponseEntity execute(RequestEntity req) {
        String args = req.getParams();
        if (!args.isEmpty()) return ResponseEntity.badRequest().body("�������� ���������");
        if (collectionManager.getCollection().isEmpty()) return ResponseEntity.badRequest().body("��������� �����");

        Movie minMovieByDirector = null;
        for (Movie movie : collectionManager.getElements()) {
            if (movie.getDirector() != null) {
                minMovieByDirector = movie;
                break;
            }
        }
        if (minMovieByDirector == null) return ResponseEntity.badRequest().body("� ���� ��������� ���� director �� ����������");


        for (Movie movie : collectionManager.getElements()) {
            if (movie.getDirector() == null) continue;
            if (movie.getDirector().compareTo(minMovieByDirector.getDirector()) < 0) {
                minMovieByDirector = movie;

            }
        }

        return ResponseEntity.ok().body(minMovieByDirector.toString());
    }
}
