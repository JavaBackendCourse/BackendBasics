package org.lessons.lesson4.lombok.getter_setter;

public class GetterAndSetterExample {
    public static void main(String[] args) {
        Person p = new Person("John", "Johns", 22);

        p.getFirstName();
        p.getLastName();
        p.getAge();

        p.setAge(23);
        p.setLastName("Johnss");
        p.setFirstName("Johnatan");

        System.out.println(p);
    }
}
