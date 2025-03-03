package ru.varino.managers;

import ru.varino.models.Movie;

import java.util.Date;
import java.util.Hashtable;
import java.util.Map;

public class CollectionManager {
    private static CollectionManager instance;
    private Hashtable<Integer, Movie> collection;
    private final Date creationDate;

    private CollectionManager() {
        collection = new Hashtable<Integer, Movie>();
        creationDate = new Date();
    }

    public static CollectionManager getInstance() {
        return instance == null ? instance = new CollectionManager() : instance;
    }

    public Hashtable<Integer, Movie> getCollection() {
        return collection;
    }

    public void setCollection(Hashtable<Integer, Movie> collection) {
        this.collection = collection;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public String getCollectionInfo() {
        return "Тип данных: " + collection.getClass().getName() + "\n" +
                "Дата инициализации: " + creationDate + "\n" +
                "Количество элементов: " + collection.size();
    }

    public static String formatMovies(Hashtable<Integer, Movie> movies) {
        StringBuilder sb = new StringBuilder();
        sb.append("{\n");

        for (Map.Entry<Integer, Movie> entry : movies.entrySet()) {
            sb.append("  ")
                    .append(entry.getKey())
                    .append("=")
                    .append(entry.getValue())
                    .append(",\n");
        }

        sb.append("}");
        return sb.toString();
    }
    @Override
    public String toString() {
        if (collection.isEmpty()) return "Коллекция пуста!";
        return formatMovies(collection);
    }

    public void addElementToCollection(Integer id, Movie movie) {
        collection.put(id, movie);

    }

    public Movie getElementById(Integer id) {
        return collection.get(id);
    }
}


