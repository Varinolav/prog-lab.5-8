package ru.varino.managers;

import ru.varino.commands.Command;
import ru.varino.utility.io.Console;

import java.util.HashMap;
import java.util.Map;

/**
 * Класс для работы с командами
 */
public class CommandManager {
    private final Map<String, Command> commands = new HashMap<>();


    public CommandManager() {
    }

    public Map<String, Command> getCommands() {
        return commands;
    }

    /**
     * Добавить команду
     *
     * @param name    имя команды
     * @param command {@link Command} класс команды
     * @return {@link CommandManager}
     */
    public CommandManager add(String name, Command command) {
        commands.put(name, command);
        return this;

    }

    /**
     * Получить команду
     *
     * @param name имя команды
     * @return {@link Command} команда
     */
    public Command getCommand(String name) {
        return commands.get(name);
    }


}
