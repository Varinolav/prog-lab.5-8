package ru.varino.commands;


import ru.varino.utility.Executable;
import ru.varino.utility.communication.RequestEntity;
import ru.varino.utility.communication.ResponseEntity;

public abstract class Command implements Executable {
    private final String name;
    private final String description;



    protected Command(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }


    public abstract ResponseEntity execute(RequestEntity request);

    @Override
    public String toString() {
        return "Command{" +
                "name='" + name + "'" +
                ", description='" + description + "'" +
                '}';
    }
}
