package ru.varino.commands;

import ru.varino.managers.CollectionManager;
import ru.varino.models.Movie;
import ru.varino.models.utility.MovieCreator;
import ru.varino.utility.io.Console;
import ru.varino.utility.communication.RequestEntity;
import ru.varino.utility.communication.ResponseEntity;

import java.util.Scanner;

public class Insert extends Command {
    private CollectionManager collectionManager;
    private Scanner scanner;
    private Console console;

    public Insert(CollectionManager collectionManager, Scanner scanner, Console console) {
        super("insert", "�������� ����� ������� � �������� ������");
        this.collectionManager = collectionManager;
        this.scanner = scanner;
        this.console = console;
    }

    @Override
    public ResponseEntity execute(RequestEntity req) {
        String args = req.getParams();
        try {
            Integer id = Integer.parseInt(args);
            if (collectionManager.getElementById(id) != null) return ResponseEntity.badRequest()
                    .body("������� � ����� id ��� ���������� � ���������");
            Movie movie = MovieCreator.create(console, scanner);
            collectionManager.addElementToCollection(id, movie);
            return ResponseEntity.ok().body("������� �������� � ���������");

        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body("���� ������ ���� Int");
        } catch (InterruptedException e) {
            return ResponseEntity.serverError().body("���� ���������");

        }
    }
}