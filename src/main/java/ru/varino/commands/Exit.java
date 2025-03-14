package ru.varino.commands;

import ru.varino.utility.communication.RequestEntity;
import ru.varino.utility.communication.ResponseEntity;

/**
 * Класс команды Exit
 */
public class Exit extends Command {
    public Exit() {
        super("exit", "завершить программу (без сохранения в файл)");
    }

    /**
     * {@inheritDoc}
     * @param req запрос для выполнения команды
     * @return {@link ResponseEntity}
     */
    @Override
    public ResponseEntity execute(RequestEntity req) {
        return ResponseEntity.exit().body("Работа программы завершена");
    }

}
