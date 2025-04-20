package org.lessons.lesson5.coupling.tight.repository;

import java.util.List;
import java.util.Optional;

public class PostgresRepository {
    public PostgresRepository() {
    }

    public final List<Object> getAll() {
        // представим запрос в базу данных
        return List.of("row1", "row2", "row3", "row4", "row5");
    }


    public final Optional<Object> getById(String id) {
        // представим запрос в базу данных
        return Optional.of("row1");
    }

    public final void insertNewRow(Object newObject) {
        // представим запрос в базу данных
    }
}
