package org.lessons.lesson4.lombok.practice.task4;

public class Task4 {
    public static void main(String[] args) {
        Order order = Order.builder()
                .orderId(1001L)
                .customerName("Дмитрий")
                .totalAmount(1200.50)
                .status("Оплачен")
                .build();

        System.out.println(order);
    }
}
