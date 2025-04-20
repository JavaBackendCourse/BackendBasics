package org.lessons.lesson4.lombok.constructor;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Person3 {
    private String firstName;
    private String lastName;
    private int age;
}
