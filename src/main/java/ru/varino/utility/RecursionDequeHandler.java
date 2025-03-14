package ru.varino.utility;



import java.util.ArrayDeque;
import java.util.Deque;

public class RecursionDequeHandler {
    private static RecursionDequeHandler instance;
    private Deque<String> fileNameDeque = new ArrayDeque<>();

    public RecursionDequeHandler() {
    }

    public static RecursionDequeHandler getInstance() {
        return instance == null ? instance = new RecursionDequeHandler() : instance;
    }

    public void addFileNameLast(String filename) {
        fileNameDeque.addLast(filename);
    }

    public void removeFileNameFirst() {
        fileNameDeque.removeFirst();
    }

    public int countFileName(String filename) {
        int count = 0;
        for (String s : fileNameDeque) {
            if (s.equals(filename)) count++;
        }
        return count;
    }

    public boolean isEmpty() {
        return fileNameDeque.isEmpty();
    }
}
