package org.lessons.lesson4.lombok.practice.task2;

public class Task2 {
    public static void main(String[] args) {
        User user = new User();
        user.setId(1L);
        user.setName("Damir");
        user.setEmail("damir@example.com");

        User user2 = new User(
                2L, "John", "john@example.com"
        );

        System.out.println(user);
        System.out.println(user2);
    }
}
