package ru.varino.managers;

import ru.varino.commands.Command;
import ru.varino.utility.io.Console;
import ru.varino.utility.communication.RequestEntity;
import ru.varino.utility.communication.ResponseEntity;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class InputManager {
    private static InputManager instance;
    private final Console console;
    private final CommandManager commandManager;


    private static Scanner usedScanner;


    private InputManager(Console console, CommandManager commandManager) {
        this.console = console;
        this.commandManager = commandManager;
    }


    public static InputManager getInstance(Console console, CommandManager commandManager) {
        return instance == null ? instance = new InputManager(console, commandManager) : instance;
    }

    public static void setUsedScanner(Scanner scanner) {
        usedScanner = scanner;
    }

    public static Scanner getUsedScanner() {
        return usedScanner;
    }

    public void interactiveRun() {
        try {
            String[] userCommand;
            ResponseEntity response;

            do {
                console.printf("~ ");

                userCommand = (usedScanner.nextLine().trim() + " ").split(" ", 2);
                String command = userCommand[0];
                String params = userCommand[1].trim();
                RequestEntity request = RequestEntity.create(command, params);
                response = runCommand(request);
                console.printResponse(response);
            } while (response.getExitCode() != 500);
        } catch (NoSuchElementException exception) {
            console.println("");
            console.printerr("Работа программы прекращена!");
        }

    }

    private ResponseEntity runCommand(RequestEntity req) {
        String commandReq = req.getCommand();
        if (commandReq.isEmpty()) return ResponseEntity.badRequest().body("Введено 0 аргументов");
        Command command = commandManager.getCommand(commandReq);
        if (commandReq.equals("exit")) {
            return ResponseEntity.exit().body("EXIT");
        }
        if (command == null) {
            return ResponseEntity.badRequest().body("Команда '" + commandReq + "' не найдена, используйте help");
        }

        return command.execute(req);
    }


}
