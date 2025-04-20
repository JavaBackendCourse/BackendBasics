package org.lessons.lesson4.lombok.practice.task5;

import org.lessons.lesson4.lombok.practice.task5.engine.Engine;

public class Vehicle {
    private Engine engine;

    // DI через конструктор
    public Vehicle(Engine engine) {
        this.engine = engine;
    }

    // DI через сеттер
    public void setVehicle(Engine engine) {
        this.engine = engine;
    }

    public void drive() {
        engine.start();
        System.out.println("Машина поехала!");
    }
}