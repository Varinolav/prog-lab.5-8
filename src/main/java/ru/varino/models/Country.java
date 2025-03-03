package ru.varino.models;

public enum Country {
    UNITED_KINGDOM,
    GERMANY,
    SPAIN,
    THAILAND,
    NORTH_KOREA;


    public static String getNames() {
        StringBuilder countyBuilder = new StringBuilder();
        for (Country country : values()) {
            countyBuilder.append(country).append(", ");
        }
        return countyBuilder.substring(0, countyBuilder.length() - 2);
    }


}