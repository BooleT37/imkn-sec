package ru.naumen.model;

import ru.naumen.entities.Scholarship;

/**
 * @author aarkaev
 * @since 21.11.2016
 */
public interface ScholarshipDao {
    double findByStudentId(int studentId);

    void create(Scholarship scholarship);
}
