package org.lessons.lesson5.coupling.tight.repository;

import java.util.List;
import java.util.Optional;

public class ElasticsearchRepository {
    public ElasticsearchRepository() {
    }

    public final List<Object> findAll() {
        // представим запрос в базу данных
        return List.of("row1", "row2", "row3", "row4", "row5");
    }


    public final Optional<Object> findById(String id) {
        // представим запрос в базу данных
        return Optional.of("row1");
    }

    public final void insertNewValue(Object newObject) {
        // представим запрос в базу данных
    }
}
