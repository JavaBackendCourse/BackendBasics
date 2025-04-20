package org.lessons.lesson4.lombok.practice.task2;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private long id;
    private String name;
    private String email;
}