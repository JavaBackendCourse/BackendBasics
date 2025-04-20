package org.lessons.lesson4.lombok.to_string;

public class ToStringExample {
    public static void main(String[] args) {
        Person2 p = new Person2("John", "Johns", 22);

        String pStr = p.toString();

        System.out.println(pStr);
    }
}
