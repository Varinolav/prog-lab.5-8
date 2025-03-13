package ru.varino.managers;

import ru.varino.commands.Command;
import ru.varino.exceptions.EmptyFileException;
import ru.varino.exceptions.PermissionDeniedException;
import ru.varino.exceptions.RecursionException;
import ru.varino.utility.RecursionConfiguration;
import ru.varino.utility.RecursionDequeHandler;
import ru.varino.utility.io.Console;
import ru.varino.utility.communication.RequestEntity;
import ru.varino.utility.communication.ResponseEntity;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class InputManager {
    private static InputManager instance;
    private final Console console;
    private final CommandManager commandManager;
    private final RecursionDequeHandler recursionDequeHandler;


    private static Scanner scanner;


    private InputManager(Console console, CommandManager commandManager, RecursionDequeHandler recursionDequeHandler) {
        this.console = console;
        this.commandManager = commandManager;
        this.recursionDequeHandler = recursionDequeHandler;
    }


    public static InputManager getInstance(Console console, CommandManager commandManager, RecursionDequeHandler recursionDequeHandler) {
        return instance == null ? instance = new InputManager(console, commandManager, recursionDequeHandler) : instance;
    }

    public static void setScanner(Scanner scanner) {
        InputManager.scanner = scanner;
    }

    public static Scanner getScanner() {
        return scanner;
    }

    public void interactiveRun() {
        try {
            String[] userCommand;
            ResponseEntity response;
            do {
                console.printf("~ ");

                userCommand = (scanner.nextLine().trim() + " ").split(" ", 2);
                String command = userCommand[0];
                String params = userCommand[1].trim();
                RequestEntity request = RequestEntity.create(command, params);
                response = runCommand(request);
                console.printResponse(response);
            } while (!response.getBody().equals("Работа программы завершена"));
        } catch (NoSuchElementException exception) {
            console.println("");
            console.printerr("Работа программы прекращена!");
        }

    }

    public void runScript(String fileName) {

        try {
            String[] fileCommand;
            ResponseEntity response = ResponseEntity.ok();
            File filePath = new File(fileName);
            if (!filePath.canRead()) throw new PermissionDeniedException("Чтение");
            if (!filePath.exists()) throw new FileNotFoundException();

            Scanner fileScanner = new Scanner(filePath);
            if (!fileScanner.hasNext()) throw new EmptyFileException(filePath.toString());

            recursionDequeHandler.addFileNameLast(fileName);

            while (fileScanner.hasNextLine() && !response.getBody().equals("Работа программы завершена")) {
                console.printf("%s-> ~ ".formatted(fileName));

                String scannedCommand = fileScanner.nextLine();
                fileCommand = (scannedCommand.trim() + " ").split(" ", 2);
                String command = fileCommand[0];
                String params = fileCommand[1].trim();
                RequestEntity request = RequestEntity.create(command, params);

                console.println(scannedCommand);


                if (command.equals("execute_script")) {
                    if (recursionDequeHandler.countFileName(params) >= RecursionConfiguration.RECURSION_LIMIT) {
                        throw new RecursionException("Достигнута максимальная глубина рекурсии");
                    }
                }

                response = runCommand(request);
                console.printResponse(response);
            }
            recursionDequeHandler.removeFileNameFirst();
            console.printerr("Файл %s прочитан".formatted(fileName));

        } catch (PermissionDeniedException e) {
            return;
        } catch (FileNotFoundException e) {
            return;
        } catch (EmptyFileException e) {
            return;
        } catch (RecursionException e) {
            throw new RuntimeException(e);
        }


    }

    private ResponseEntity runCommand(RequestEntity req) {
        String commandReq = req.getCommand();
        if (commandReq.isEmpty()) return ResponseEntity.badRequest().body("Введено 0 аргументов");
        Command command = commandManager.getCommand(commandReq);
        if (command == null) {
            return ResponseEntity.badRequest().body("Команда '" + commandReq + "' не найдена, воспользуйтесь help");
        }

        return command.execute(req);
    }


}
