package org.lessons.lesson4.lombok.to_string;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Person2 {
    private String firstName;
    private String lastName;
    private int age;

    public Person2(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }
}
