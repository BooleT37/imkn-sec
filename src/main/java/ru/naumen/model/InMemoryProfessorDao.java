package ru.naumen.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import javax.inject.Named;

import ru.naumen.entities.Professor;

@Named
public class InMemoryProfessorDao implements ProfessorDao {

    private List<Professor> storage = new ArrayList<>();
    private AtomicInteger counter = new AtomicInteger(0);

    @Override
    public Optional<Professor> find(int id) {
        return storage.stream()
            .filter(s -> s.getId() == id)
            .findFirst();
    }

    @Override
    public void save(Professor professor) {
        professor.setId(counter.incrementAndGet());
        storage.add(professor);
    }
}
