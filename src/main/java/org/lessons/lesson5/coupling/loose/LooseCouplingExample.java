package org.lessons.lesson5.coupling.loose;

import org.lessons.lesson5.coupling.loose.repository.BaseRepository;
import org.lessons.lesson5.coupling.loose.repository.ElasticsearchRepository;

public class LooseCouplingExample {
    public static void main(String[] args) {
        BaseRepository repository = new ElasticsearchRepository();

        TestService t1 = new TestService(repository);
        TestService2 t2 = new TestService2(repository);
        TestService3 t3 = new TestService3(repository);
    }
}
