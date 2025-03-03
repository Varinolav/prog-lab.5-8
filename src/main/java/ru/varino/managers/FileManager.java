package ru.varino.managers;

import ru.varino.exceptions.PermissionDeniedException;
import ru.varino.utility.io.Console;

import java.io.*;

public class FileManager {
    private static FileManager instance;
    private final Console console;

    private FileManager(Console console) {
        this.console = console;
    }

    public static FileManager getInstance(Console console) {
        return instance == null ? instance = new FileManager(console) : instance;
    }


    public void write(String fileName, String text) throws PermissionDeniedException {
        try (OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(fileName))) {
            var filePath = new File(fileName);
            if (!filePath.canWrite()) throw new PermissionDeniedException("������");

            char[] chars = text.toCharArray();
            outputStreamWriter.write(chars, 0, chars.length);
        } catch (IOException e) {
            console.println("������ ��� ������ �����");
        }
    }

    public String read(String fileName) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            var filePath = new File(fileName);
            if (!filePath.canRead()) throw new PermissionDeniedException("������");
            if (!filePath.canWrite()) console.println("��������! �� �� ������� ������������ ������� save");

            StringBuilder out = new StringBuilder();
            String s;
            while ((s = bufferedReader.readLine()) != null) {
                out.append(s);
            }
            return out.toString();
        } catch (PermissionDeniedException e) {
            console.printerr(e.getMessage() + ". ��������� �����");
            return "";
        } catch (IOException e) {
            console.printerr("Json-���� �� ������. ��������� �����");
            return "";
        }
    }
}
