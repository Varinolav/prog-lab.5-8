package ru.varino.commands;

import ru.varino.managers.CollectionManager;
import ru.varino.models.Movie;
import ru.varino.models.utility.MovieCreator;
import ru.varino.utility.communication.RequestEntity;
import ru.varino.utility.communication.ResponseEntity;
import ru.varino.utility.io.Console;

import java.util.Scanner;

public class ReplaceIf extends Command {
    private final CollectionManager collectionManager;
    private final Console console;
    private final Scanner scanner;

    private final String type;

    public ReplaceIf(String type, CollectionManager collectionManager, Scanner scanner, Console console) {
        super("replace_if_%s <id>".formatted(type), "�������� �������� �� �����, ���� ����� �������� ������ �������");
        this.collectionManager = collectionManager;
        this.console = console;
        this.scanner = scanner;
        this.type = type;
    }

    @Override
    public ResponseEntity execute(RequestEntity req) {
        String args = req.getParams();
        if (args.isEmpty()) return ResponseEntity.badRequest().body("�������� ���������");
        try {
            Integer id = Integer.parseInt(args);
            if (collectionManager.getElementById(id) == null) return ResponseEntity.badRequest()
                    .body("�������� � ����� id �� ���������� � ���������");
            Movie movie = MovieCreator.create(console, scanner);
            Boolean comparisonResult = null;
            if (type.equals("greater")) {
                comparisonResult = movie.compareTo(collectionManager.getElementById(id)) > 0;
            }
            if (type.equals("lower")) {
                comparisonResult = movie.compareTo(collectionManager.getElementById(id)) < 0;
            }
            if (comparisonResult == null) throw new RuntimeException();
            if (comparisonResult) {
                collectionManager.addElementToCollection(id, movie);
                return ResponseEntity.ok().body("������� ������� �����������");
            } else {
                return ResponseEntity.badRequest().body("������� �� �����������");
            }
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body("���� ������ ���� Int");
        } catch (InterruptedException e) {
            return ResponseEntity.serverError().body("���� ���������");

        }
    }
}
