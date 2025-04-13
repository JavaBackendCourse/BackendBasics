package org.lessons.lesson1.databasebasics.example1.repository;

import org.lessons.lesson1.databasebasics.example1.models.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public List<Product> getAllProducts(Connection conn) {
        List<Product> products = new ArrayList<>();

        String query = String.format("SELECT * FROM %s", TABLE_NAME);

        try {
            ResultSet resultSet = executeQuery(conn, query);
            while (resultSet.next()) {
                products.add(
                        new Product(
                                resultSet.getLong("id"),
                                resultSet.getString("name"),
                                resultSet.getString("category"),
                                resultSet.getDouble("price"),
                                resultSet.getInt("stock")
                        )
                );
            }
            System.out.printf("Данные успешно получены из таблицы %s\n", TABLE_NAME);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return products;
    }

    public List<Product> getProductsByName(Connection conn, String productName) {
        List<Product> products = new ArrayList<>();

        String query = String.format("""
                    SELECT * FROM %s WHERE name = ?
                """, TABLE_NAME);

        try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setString(1, productName);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                products.add(
                        new Product(
                                resultSet.getLong("id"),
                                resultSet.getString("name"),
                                resultSet.getString("category"),
                                resultSet.getDouble("price"),
                                resultSet.getInt("stock")
                        )
                );
            }
            System.out.printf("Данные успешно получены по имени из таблицы %s!\n", TABLE_NAME);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return products;
    }

    public Optional<Product> getProductById(Connection conn, Long id) {
        String query = "SELECT * FROM products WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setLong(1, id);
            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                Product product = new Product(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("category"),
                        resultSet.getDouble("price"),
                        resultSet.getInt("stock")
                );
                return Optional.of(product);
            } else {
                return Optional.empty();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public void updateCategory(Connection conn, String newCategory, String oldCategory) {
        String query = String.format("UPDATE %s SET category = ? WHERE category = ?", TABLE_NAME);
        try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setString(1, newCategory);
            preparedStatement.setString(2, oldCategory);

            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.printf("Данные были успешно обновлены в таблице %s\n", TABLE_NAME);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateProductPrice(Connection conn, String productName, Double newPrice) {
        String query = String.format("UPDATE %s SET price = ? WHERE name = ?", TABLE_NAME);
        try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setDouble(1, newPrice);
            preparedStatement.setString(2, productName);

            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.printf("Данные были успешно обновлены в таблице %s\n", TABLE_NAME);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteProductByName(Connection conn, String productName) {
        String query = String.format("DELETE FROM %s WHERE name = ?", TABLE_NAME);

        try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setString(1, productName);
            int rowsDeleted = preparedStatement.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.printf("Данные были успешно удалены из таблицы %s!\n", TABLE_NAME);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
