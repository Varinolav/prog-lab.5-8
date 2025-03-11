package ru.varino.models.utility;

import ru.varino.models.Movie;

import java.util.HashSet;
import java.util.Hashtable;

public class IdGenerator {
    private static IdGenerator instance;
    private Integer ID = 1;
    private HashSet<Integer> IdList;


    public static IdGenerator getInstance() {
        return instance == null ? instance = new IdGenerator() : instance;
    }

    public void setIdsFromCollection(Hashtable<Integer, Movie> collection) {
        HashSet<Integer> Ids = new HashSet<>();
        for (Movie m : collection.values()) {
            Integer id = m.getId();
            Ids.add(id);
        }
        IdList = Ids;
    }

    public Integer generateId() {
        while (IdList.contains(ID)) {
            ID++;
        }
        IdList.add(ID);
        return ID;
    }

}
