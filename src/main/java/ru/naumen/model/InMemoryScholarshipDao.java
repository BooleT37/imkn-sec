package ru.naumen.model;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import ru.naumen.entities.Scholarship;

/**
 * @author aarkaev
 * @since 21.11.2016
 */
@Named
public class InMemoryScholarshipDao implements ScholarshipDao {
    private List<Scholarship> storage = new ArrayList<>();

    @Override
    public double findByStudentId(int studentId) {
        return storage.stream()
            .filter(s -> s.getStudent().getId() == studentId)
            .findFirst()
            .map(Scholarship::getAmount)
            .orElse(0.0);
    }

    @Override
    public void create(Scholarship scholarship) {
        storage.add(scholarship);
    }
}
