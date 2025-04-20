package org.lessons.lesson4.lombok.builder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Person6 {
    private String firstName;
    private String lastName;
    private int age;
}
