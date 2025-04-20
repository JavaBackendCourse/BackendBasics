package org.lessons.lesson4.lombok.practice.task4;

import lombok.Builder;
import lombok.ToString;

@Builder
@ToString
public class Order {
    private long orderId;
    private String customerName;
    private double totalAmount;
    private String status;
}