package org.lessons.lesson4.lombok.practice.task3;

public class Task3 {
    public static void main(String[] args) {
        Product product = new Product(101L, "Laptop", 799.99, "Electronics");

        System.out.println("ID: " + product.getId());
        System.out.println("Название: " + product.getName());
        System.out.println("Цена: " + product.getPrice());
        System.out.println("Категория: " + product.getCategory());


        // Изменяем цену и категорию
        product.setPrice(699.99);
        product.setCategory("Computers");

        System.out.println(product);
    }
}
