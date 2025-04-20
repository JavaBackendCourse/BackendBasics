package org.lessons.lesson4.lombok.practice.task1;

public class Task1 {
    public static void main(String[] args) {
        User user = new User(1L, "Damir", "damir@example.com");

        System.out.println("ID: " + user.getId());
        System.out.println("Имя: " + user.getName());
        System.out.println("Email: " + user.getEmail());

        user.setEmail("damirl@gmail.com");

        System.out.println(user);
    }
}
