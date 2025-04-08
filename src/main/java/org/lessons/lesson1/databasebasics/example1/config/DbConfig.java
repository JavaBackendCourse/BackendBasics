package org.lessons.lesson1.databasebasics.example1.config;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConfig {
    private static final String URL = "jdbc:postgresql://localhost:5432/shop_test";
    private static final String USER = "postgres";
    private static final String PASSWORD = "test123";

    public static Connection getConnection() {
        Connection conn = null;

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(URL, USER, PASSWORD);

            if (conn != null) {
                System.out.println("Соеднение к базе данных прошло успешно!");
            } else {
                System.out.println("Соединение к базе данных не удалось!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return conn;
    }
}
