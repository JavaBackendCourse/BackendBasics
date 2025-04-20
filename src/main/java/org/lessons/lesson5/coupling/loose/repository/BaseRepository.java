package org.lessons.lesson5.coupling.loose.repository;

import java.util.List;
import java.util.Optional;

public abstract class BaseRepository {
    public abstract List<Object> getAll();

    public abstract Optional<Object> getById(String id);

    public abstract void add(Object object);
}
