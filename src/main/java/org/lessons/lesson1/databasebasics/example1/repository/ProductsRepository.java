package org.lessons.lesson1.databasebasics.example1.repository;

import org.lessons.lesson1.databasebasics.example1.models.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class ProductsRepository extends BaseRepository {
    private final String TABLE_NAME = "products";

    public void createProductsTable(Connection connection) {
        var query = String.format("""
                CREATE TABLE IF NOT EXISTS %s (
                    id SERIAL PRIMARY KEY,
                    name VARCHAR(50) NOT NULL,
                    category VARCHAR(30),
                    price DECIMAL(10,2),
                    stock INT
                );
                """, TABLE_NAME);

        try {
            createTable(connection, TABLE_NAME, query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addNewProductUnsafe(Connection connection, Product product) { // подвержен SQL инъекциям
        String query = String.format("""
                INSERT INTO %s (name, category, price, stock)
                VALUES
                ('%s', '%s', '%s', '%s')
                """, TABLE_NAME, product.getName(), product.getCategory(), product.getPrice(), product.getQuantity());

        try {
            executeUpdate(connection, query);
            System.out.printf("Данные успешно записаны в таблицу %s!\n", TABLE_NAME);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addNewProductSafe(Connection connection, Product product) { // защищен от SQL инъекций
        String query = String.format("""
                INSERT INTO %s (name, category, price, stock)
                VALUES (?, ?, ?, ?);
                """, TABLE_NAME);

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getCategory());
            preparedStatement.setDouble(3, product.getPrice());
            preparedStatement.setInt(4, product.getQuantity());

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.printf("Данные были успешно записаны в таблицу %s\n", TABLE_NAME);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
