package ru.naumen.model;

import java.util.List;
import java.util.Optional;

import ru.naumen.entities.Course;
import ru.naumen.entities.Student;

/**
 * @author aarkaev
 * @since 19.11.2016
 */
public interface StudentDao {
    List<Student> findAll();

    List<Student> listCourseStudents(Course course);

    void create(Student student);

    Optional<Student> find(int id);

    void delete(Student student);
}
