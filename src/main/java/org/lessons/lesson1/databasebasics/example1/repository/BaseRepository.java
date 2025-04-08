package org.lessons.lesson1.databasebasics.example1.repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class BaseRepository {
    protected void createTable(Connection connection, String tableName, String query) throws SQLException {
        executeUpdate(connection, query);
        System.out.printf("Таблица %s создана!\n", tableName);
    }

    protected void executeUpdate(Connection connection, String query) throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate(query);
    }

    protected void executeQuery(Connection connection, String query) throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeQuery(query);
    }
}
