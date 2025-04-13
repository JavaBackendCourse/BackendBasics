package org.lessons.lesson1.databasebasics.example1;

import org.lessons.lesson1.databasebasics.example1.config.DbConfig;
import org.lessons.lesson1.databasebasics.example1.models.Product;
import org.lessons.lesson1.databasebasics.example1.repository.ProductsRepository;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class DatabaseExample {
    private static final ProductsRepository productsRepository = new ProductsRepository();

    public static void main(String[] args) {
        Connection conn = DbConfig.getConnection();
        createProductsTableIfNotExists(conn);
//        insertProductsUnsafe(conn);
//        insertProductsSafe(conn);
//        getAllProducts(conn);
//        getProductsByName(conn, "Laptop");

//        System.out.println("До обновления: ");
//
//        getAllProducts(conn);
//
//        updateCategory(conn, "Electronics and Tech", "Electronics");
//
//        System.out.println("После обновления: ");
//
//        getAllProducts(conn);

//        System.out.println("До обновления: ");
//
//        getProductsByName(conn, "Laptop");
//
//        updatePrice(conn, "Laptop", 1300.00);
//
//        System.out.println("После обновления: ");
//
//        getProductsByName(conn, "Laptop");

        System.out.println("До удаления: ");

        getAllProducts(conn);

        deleteProduct(conn, "Chair");

        System.out.println("После удаления: ");

        getAllProducts(conn);

        getProductById(conn, 10L);
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

    private static void getAllProducts(Connection conn) {
        List<Product> products = productsRepository.getAllProducts(conn);

        products.forEach(System.out::println);
    }

    private static void getProductsByName(Connection conn, String productName) {
        List<Product> products = productsRepository.getProductsByName(conn, productName);

        products.forEach(System.out::println);
    }

    private static void getProductById(Connection conn, Long id) {
        Optional<Product> product = productsRepository.getProductById(conn, id);

        if (product.isPresent())
            System.out.println("Found product: " + product.get());
        else
            System.out.println("Product not found!");
    }

    private static void updateCategory(Connection conn, String newCategory, String oldCategory) {
        productsRepository.updateCategory(conn, newCategory, oldCategory);
    }

    private static void updatePrice(Connection conn, String productName, Double newPrice) {
        productsRepository.updateProductPrice(conn, productName, newPrice);
    }

    private static void deleteProduct(Connection conn, String productName) {
        productsRepository.deleteProductByName(conn, productName);
    }
}
