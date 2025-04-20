package org.lessons.lesson5.coupling.tight;

import org.lessons.lesson5.coupling.tight.repository.ElasticsearchRepository;
import org.lessons.lesson5.coupling.tight.repository.PostgresRepository;

import java.util.List;
import java.util.Optional;

public class TestService {
    private final ElasticsearchRepository databaseRepository = new ElasticsearchRepository(); // tight coupling (жесткая связность)

    public List<Object> getAllElements() {
        // какая-то бизнес логика
        return databaseRepository.findAll();
    }

    public Optional<Object> getElementById(String id) {
        // какая-то бизнес логика
        return databaseRepository.findById(id);
    }

    public void addNewElement(Object element) {
        // какая-то бизнес логика
        databaseRepository.insertNewValue(element);
    }
}
