package org.lessons.lesson4.lombok.data;

import org.lessons.lesson4.lombok.constructor.Person3;

public class DataExample {
    public static void main(String[] args) {
        Person3 personEmpty = new Person3();
        Person3 personFull = new Person3("John", "Johns", 22);

        personFull.getAge();
        personFull.setAge(22);

        personFull.getFirstName();
        personFull.getLastName();

        personFull.setLastName("Johnss");
        personFull.setFirstName("Johnatan");
    }
}
