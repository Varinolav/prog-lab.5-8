package ru.varino.commands;

import ru.varino.managers.CollectionManager;
import ru.varino.models.Movie;
import ru.varino.utility.communication.RequestEntity;
import ru.varino.utility.communication.ResponseEntity;

/**
 * Класс команды MinByDirector
 */
public class MinByDirector extends Command {
    private final CollectionManager collectionManager;

    public MinByDirector(CollectionManager collectionManager) {
        super("min_by_director", "вывести любой объект из коллекции, значение поля director которого является минимальным");
        this.collectionManager = collectionManager;
    }

    /**
     * {@inheritDoc}
     * @param req запрос для выполнения команды
     * @return {@link ResponseEntity}
     */
    @Override
    public ResponseEntity execute(RequestEntity req) {
        String args = req.getParams();
        if (!args.isEmpty()) return ResponseEntity.badRequest().body("Неверные аргументы");
        if (collectionManager.getCollection().isEmpty()) return ResponseEntity.badRequest().body("Коллекция пуста");

        Movie minMovieByDirector = null;
        for (Movie movie : collectionManager.getElements()) {
            if (movie.getDirector() != null) {
                minMovieByDirector = movie;
                break;
            }
        }
        if (minMovieByDirector == null) return ResponseEntity.badRequest().body("У всех элементов поле director не определено");


        for (Movie movie : collectionManager.getElements()) {
            if (movie.getDirector() == null) continue;
            if (movie.getDirector().compareTo(minMovieByDirector.getDirector()) < 0) {
                minMovieByDirector = movie;

            }
        }

        return ResponseEntity.ok().body(minMovieByDirector.toString());
    }
}
