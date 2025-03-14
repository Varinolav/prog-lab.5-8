package ru.varino.utility;

import ru.varino.utility.communication.RequestEntity;
import ru.varino.utility.communication.ResponseEntity;

/**
 * Интерфейс, описывающий выполнение команды
 */
public interface Executable {
    /**
     * Выполнить команду
     *
     * @param request запрос для выполнения команды
     * @return ответ, содержащий данные о выполнении команды
     */
    ResponseEntity execute(RequestEntity request);
}
