package ru.varino.models.utility;

import ru.varino.models.Movie;

import java.util.HashSet;
import java.util.Hashtable;

/**
 * Класс для генерации айди элементов коллекции
 */
public class IdGenerator {
    private static IdGenerator instance;
    private Integer ID = 1;
    private HashSet<Integer> IdList;


    public static IdGenerator getInstance() {
        return instance == null ? instance = new IdGenerator() : instance;
    }

    /**
     * Установить все существующие айдишники в коллекции
     *
     * @param collection коллекция
     */
    public void setIdsFromCollection(Hashtable<Integer, Movie> collection) {
        HashSet<Integer> Ids = new HashSet<>();
        for (Movie m : collection.values()) {
            Integer id = m.getId();
            Ids.add(id);
        }
        IdList = Ids;
    }

    /**
     * Сгенерировать новый айди
     *
     * @return айди
     */
    public Integer generateId() {
        while (IdList.contains(ID)) {
            ID++;
        }
        IdList.add(ID);
        return ID;
    }

}
