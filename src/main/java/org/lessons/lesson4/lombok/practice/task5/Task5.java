package org.lessons.lesson4.lombok.practice.task5;

import org.lessons.lesson4.lombok.practice.task5.engine.CombustionEngine;
import org.lessons.lesson4.lombok.practice.task5.engine.ElectricEngine;
import org.lessons.lesson4.lombok.practice.task5.engine.Engine;

public class Task5 {
    public static void main(String[] args) {
        Engine engine = new CombustionEngine();
        Vehicle vehicle = new Vehicle(engine);
        vehicle.drive();
    }
}
