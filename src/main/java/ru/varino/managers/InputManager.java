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


/**
 * Класс для управления вводом и запуском команд из скрипта
 */
public class InputManager {
    private static InputManager instance;
    private final Console console;
    private final CommandManager commandManager;
    private final RecursionDequeHandler recursionDequeHandler;
    private final ScannerManager scannerManager;


    private static Scanner scanner;


    private InputManager(Console console, CommandManager commandManager, RecursionDequeHandler recursionDequeHandler, ScannerManager scannerManager) {
        this.console = console;
        this.commandManager = commandManager;
        this.recursionDequeHandler = recursionDequeHandler;
        this.scannerManager = scannerManager;
    }


    public static InputManager getInstance(Console console, CommandManager commandManager, RecursionDequeHandler recursionDequeHandler, ScannerManager scannerManager) {
        return instance == null ? instance = new InputManager(console, commandManager, recursionDequeHandler, scannerManager) : instance;
    }

    public static void setScanner(Scanner scanner) {
        InputManager.scanner = scanner;
    }

    public static Scanner getScanner() {
        return scanner;
    }

    /**
     * Запуск интерактивного режима работы
     */
    public void interactiveRun() {
        try {
            scannerManager.setCurrentScanner(scanner);
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

    /**
     * Метод запуска скрипта
     *
     * @param fileName имя файла
     */
    public void runScript(String fileName) {

        try {
            String[] fileCommand;
            ResponseEntity response = ResponseEntity.ok().body("");
            File filePath = new File(fileName);
            if (!filePath.exists()) throw new FileNotFoundException();
            if (!filePath.canRead()) throw new PermissionDeniedException("Чтение");

            Scanner fileScanner = new Scanner(filePath);
            scannerManager.setCurrentScanner(fileScanner);
            if (!fileScanner.hasNext()) throw new EmptyFileException(filePath.toString());

            console.println("Начинается выполнение скрипта из файла %s".formatted(fileName));
            recursionDequeHandler.addFileNameLast(fileName);

            while (fileScanner.hasNextLine() && !response.getBody().equals("Работа программы завершена")) {
                console.printf("%s -> ~ ".formatted(fileName));
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
        } catch (PermissionDeniedException | RecursionException e) {
            console.printerr(e.getMessage());
        } catch (FileNotFoundException e) {
            console.printerr("Файл не найден.");
        } catch (EmptyFileException e) {
            console.printerr("Файл пуст");
        } finally {
            if (recursionDequeHandler.isEmpty()) {
                scannerManager.setCurrentScanner(scanner);
            }
        }


    }

    /**
     * Метод исполнения команды
     *
     * @param req запрос
     * @return ответ после выполнения команды
     */
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
