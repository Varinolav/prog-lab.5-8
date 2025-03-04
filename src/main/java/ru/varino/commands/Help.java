package ru.varino.commands;

import ru.varino.managers.CommandManager;
import ru.varino.utility.communication.RequestEntity;
import ru.varino.utility.communication.ResponseEntity;

public class Help extends Command {
    private final CommandManager commandManager;

    public Help(CommandManager commandManager) {
        super("help", "вывести справку по доступным командам");
        this.commandManager = commandManager;
    }

    @Override
    public ResponseEntity execute(RequestEntity req) {
        String args = req.getParams();
        try {
            if (!args.isEmpty())
                return ResponseEntity.ok().body(commandManager.getCommands().get(args).getDescription());

        } catch (NullPointerException e) {
            return ResponseEntity.badRequest().body("Команды не существует");
        }

        StringBuilder builder = new StringBuilder();
        commandManager.getCommands().values().forEach(command -> {
            builder.append(command.getName()).append(" - ").append(command.getDescription()).append("\n");
        });
        return ResponseEntity.ok().body(builder.substring(0, builder.length() - 2));
    }
}

