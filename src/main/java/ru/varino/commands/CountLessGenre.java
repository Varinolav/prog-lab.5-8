package ru.varino.commands;

import ru.varino.managers.CollectionManager;
import ru.varino.models.Movie;
import ru.varino.models.MovieGenre;
import ru.varino.utility.communication.RequestEntity;
import ru.varino.utility.communication.ResponseEntity;

public class CountLessGenre extends Command {
    private final CollectionManager collectionManager;

    public CountLessGenre(CollectionManager collectionManager) {
        super("count_less_than_genre <genre>", "������� ���������� ���������, �������� ���� genre ������� ������ ���������");
        this.collectionManager = collectionManager;
    }

    @Override
    public ResponseEntity execute(RequestEntity req) {
        String args = req.getParams();
        if (args.isEmpty()) return ResponseEntity.badRequest().body("�������� ���������");
        if (collectionManager.getCollection().isEmpty()) return ResponseEntity.badRequest().body("��������� �����");
        try {
            long count = 0;
            for (Movie movie : collectionManager.getElements()) {
                if (movie.getGenre().compareTo(MovieGenre.valueOf(args)) < 0) {
                    count++;
                }


            }
            return ResponseEntity.ok().body(String.valueOf(count));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("�������� �������� ����. (��������� �������� - %s)".formatted(MovieGenre.getNames()));

        }
    }
}
