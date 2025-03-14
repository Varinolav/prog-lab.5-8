package ru.varino.commands;

import ru.varino.managers.InputManager;
import ru.varino.utility.communication.RequestEntity;
import ru.varino.utility.communication.ResponseEntity;

/**
 * Класс команды ExecuteScript
 */
public class ExecuteScript extends Command {
    private final InputManager inputManager;

    public ExecuteScript(InputManager inputManager) {
        super("execute_script <file_name>", "считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.");
        this.inputManager = inputManager;
    }

    /**
     * {@inheritDoc}
     * @param req запрос для выполнения команды
     * @return {@link ResponseEntity}
     */
    @Override
    public ResponseEntity execute(RequestEntity req) {
        String args = req.getParams();
        if (args.isEmpty()) return ResponseEntity.badRequest().body("Неверные аргументы");

        inputManager.runScript(args);
        return ResponseEntity.ok().body("Скрипт выполнен");
    }
}
