package ru.varino.models;

public enum MovieGenre {
    WESTERN,
    COMEDY,
    ADVENTURE,
    THRILLER,
    HORROR;


    public static String getNames() {
        StringBuilder genreBuilder = new StringBuilder();
        for (MovieGenre genre : values()) {
            genreBuilder.append(genre).append(", ");
        }
        return genreBuilder.substring(0, genreBuilder.length() - 2);
    }


}