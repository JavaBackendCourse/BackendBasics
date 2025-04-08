package org.lessons.lesson1.databasebasics.example1;

import org.lessons.lesson1.databasebasics.example1.config.DbConfig;
import org.lessons.lesson1.databasebasics.example1.models.Product;
import org.lessons.lesson1.databasebasics.example1.repository.ProductsRepository;

import java.sql.Connection;
import java.util.List;

public class DatabaseExample {
    private static final ProductsRepository productsRepository = new ProductsRepository();

    public static void main(String[] args) {
        Connection conn = DbConfig.getConnection();
        createProductsTableIfNotExists(conn);
//        insertProductsUnsafe(conn);
        insertProductsSafe(conn);
    }

    private static void createProductsTableIfNotExists(Connection conn) {
        productsRepository.createProductsTable(conn);
    }

    private static void insertProductsUnsafe(Connection conn) {
        List<Product> products = List.of(
                new Product("Laptop", "Electronics", 1200.00, 10),
                new Product("Smartphone", "Electronics", 800.00, 15),
                new Product("Table", "Furniture", 150.00, 20),
                new Product("Chair", "Furniture", 80.00, 30)
        );

        products.forEach(product -> productsRepository.addNewProductUnsafe(conn, product));
    }

    private static void insertProductsSafe(Connection conn) {
        List<Product> products = List.of(
                new Product("TV", "Electronics", 3200.00, 10),
                new Product("Sofa", "Furniture", 700.00, 15)
        );

        products.forEach(product -> productsRepository.addNewProductSafe(conn, product));
    }
}
