package ru.varino.managers;

import ru.varino.commands.Command;
import ru.varino.utility.io.Console;

import java.util.HashMap;
import java.util.Map;

public class CommandManager {
    private static CommandManager instance;
    private final Map<String, Command> commands = new HashMap<>();



    private CommandManager() {
    }

    public static CommandManager getInstance() {
        return instance == null ? instance = new CommandManager() : instance;
    }

    public Map<String, Command> getCommands() {
        return commands;
    }

    public CommandManager add(String name, Command command) {
        commands.put(name, command);
        return this;

    }
    public Command getCommand(String name) {
        return commands.get(name);
    }


}
