package org.lessons.lesson4.lombok.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person5 {
    private String firstName;
    private String lastName;
    private int age;
}
