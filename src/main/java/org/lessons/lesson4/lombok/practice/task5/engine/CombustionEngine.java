package org.lessons.lesson4.lombok.practice.task5.engine;

public class CombustionEngine implements Engine {
    @Override
    public void start() {
        System.out.println("Запуск бензинового двигателя...");
    }
}