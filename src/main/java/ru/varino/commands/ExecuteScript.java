package ru.varino.commands;

import ru.varino.managers.CollectionManager;
import ru.varino.managers.InputManager;
import ru.varino.utility.communication.RequestEntity;
import ru.varino.utility.communication.ResponseEntity;
import ru.varino.utility.io.Console;

public class ExecuteScript extends Command {
    private final CollectionManager collectionManager;
    private final InputManager inputManager;
    private final Console console;

    public ExecuteScript(CollectionManager collectionManager, InputManager inputManager, Console console) {
        super("execute_script <file_name>", "считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.");
        this.collectionManager = collectionManager;
        this.inputManager = inputManager;
        this.console = console;
    }

    @Override
    public ResponseEntity execute(RequestEntity req) {
        String args = req.getParams();
        if (args.isEmpty()) return ResponseEntity.badRequest().body("Неверные аргументы");
        console.println("Выполняется скрипт из файлы %s".formatted(args));
        inputManager.runScript(args);
        return ResponseEntity.ok().body("Скрипт выполнен");
    }
}
