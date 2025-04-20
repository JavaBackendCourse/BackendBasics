package org.lessons.lesson4.lombok.builder;

public class BuilderExample {
    public static void main(String[] args) {
        Person6 person = Person6.builder()
                .lastName("Johns")
                .firstName("John")
                .age(35)
                .build();

        System.out.println(person.toString());
    }
}
