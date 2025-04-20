package org.lessons.lesson4.lombok.practice.task3;

import lombok.Data;
import lombok.NonNull;

@Data
public class Product {
    private final Long id;
    @NonNull
    private String name;
    @NonNull
    private Double price;
    @NonNull
    private String category;
}