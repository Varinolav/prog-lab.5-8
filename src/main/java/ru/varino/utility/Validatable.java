package ru.varino.utility;

/**
 * Интерфейс валидации моделей
 */
public interface Validatable {
    /**
     * Валидирует данные при составлении модели
     *
     * @return успешность валидации
     */
    boolean validate();
}
