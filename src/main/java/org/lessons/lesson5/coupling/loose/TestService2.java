package org.lessons.lesson5.coupling.loose;

import org.lessons.lesson5.coupling.loose.repository.BaseRepository;

import java.util.List;
import java.util.Optional;

public class TestService2 {
    private final BaseRepository databaseRepository; // loose coupling (слабая связность)

    public TestService2(BaseRepository databaseRepository) {
        this.databaseRepository = databaseRepository;
    }

    public List<Object> getAllElements() {
        // какая-то бизнес логика
        return databaseRepository.getAll();
    }

    public Optional<Object> getElementById(String id) {
        // какая-то бизнес логика
        return databaseRepository.getById(id);
    }

    public void addNewElement(Object element) {
        // какая-то бизнес логика
        databaseRepository.add(element);
    }
}
