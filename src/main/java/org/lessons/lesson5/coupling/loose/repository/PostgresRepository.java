package org.lessons.lesson5.coupling.loose.repository;

import java.util.List;
import java.util.Optional;

public class PostgresRepository extends BaseRepository {
    public PostgresRepository() {
    }

    @Override
    public final List<Object> getAll() {
        // представим запрос в базу данных
        return List.of("row1", "row2", "row3", "row4", "row5");
    }


    @Override
    public final Optional<Object> getById(String id) {
        // представим запрос в базу данных
        return Optional.of("row1");
    }

    @Override
    public final void add(Object newObject) {
        // представим запрос в базу данных
    }
}
