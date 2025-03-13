package ru.varino.commands;

import ru.varino.managers.CollectionManager;
import ru.varino.models.Movie;
import ru.varino.models.MovieGenre;
import ru.varino.utility.communication.RequestEntity;
import ru.varino.utility.communication.ResponseEntity;

public class CountLessGenre extends Command {
    private final CollectionManager collectionManager;

    public CountLessGenre(CollectionManager collectionManager) {
        super("count_less_than_genre <genre>", "вывести количество элементов, значение поля genre которых меньше заданного");
        this.collectionManager = collectionManager;
    }

    @Override
    public ResponseEntity execute(RequestEntity req) {
        String args = req.getParams();
        if (args.isEmpty()) return ResponseEntity.badRequest().body("Неверные аргументы");
        if (collectionManager.getCollection().isEmpty()) return ResponseEntity.badRequest().body("Коллекция пуста");
        try {
            long count = 0;
            for (Movie movie : collectionManager.getElements()) {
                if (movie.getGenre().compareTo(MovieGenre.valueOf(args)) < 0) {
                    count++;
                }


            }
            return ResponseEntity.ok().body(String.valueOf(count));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Неверное значение. (Возможные варианты - %s)".formatted(MovieGenre.getNames()));

        }
    }
}
