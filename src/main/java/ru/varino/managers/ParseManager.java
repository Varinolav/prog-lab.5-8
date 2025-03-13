package ru.varino.managers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.varino.models.Movie;
import ru.varino.models.utility.IdGenerator;
import ru.varino.utility.io.Console;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Hashtable;
import java.time.LocalDate;

import com.google.gson.reflect.TypeToken;
import ru.varino.managers.utility.adapters.LocalDateAdapter;
import ru.varino.managers.utility.adapters.LocalDateTimeAdapter;


public class ParseManager {
    private static ParseManager instance;

    private final Console console;
    private final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .serializeNulls()
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
            .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
            .create();

    private ParseManager(Console console) {
        this.console = console;
    }


    public static ParseManager getInstance(Console console) {
        return instance == null ? instance = new ParseManager(console) : instance;
    }


    public Hashtable<Integer, Movie> getHashTableFromJson(String json) {
        IdGenerator idGen = IdGenerator.getInstance();
        try {
            Hashtable<Integer, Movie> movies = new Hashtable<Integer, Movie>();
            if (!json.isEmpty()) {
                Type collectionType = new TypeToken<Hashtable<Integer, Movie>>() {
                }.getType();
                movies = gson.fromJson(json, collectionType);
            }
            ArrayList<Integer> IdList = new ArrayList<>();

            for (Movie m : movies.values()) {
                Integer movieId = m.getId();
                if (IdList.contains(movieId) || !m.validate()) throw new IllegalArgumentException();
                IdList.add(movieId);
            }
            idGen.setIdsFromCollection(movies);
            return movies;
        } catch (Exception e) {
            console.println("Json-файл повреждён, данные из него не были взяты. Коллекция, с которой вы работаете пуста");
            return new Hashtable<Integer, Movie>();
        }
    }


    public String getJsonFromHashTable(Hashtable<Integer, Movie> movies) {
        try {
            return gson.toJson(movies);
        } catch (Exception e) {
            console.println(e.toString());
            return "Ошибка парсинга";
        }
    }
}