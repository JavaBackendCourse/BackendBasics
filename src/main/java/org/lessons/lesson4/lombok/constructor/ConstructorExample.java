package org.lessons.lesson4.lombok.constructor;

public class ConstructorExample {
    public static void main(String[] args) {
        Person3 personEmpty = new Person3();
        Person3 personFull = new Person3("John", "Johns", 22);



        Person4 personRequired = new Person4("John", "Johns");
    }
}
