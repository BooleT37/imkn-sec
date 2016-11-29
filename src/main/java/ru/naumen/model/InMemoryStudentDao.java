package ru.naumen.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.inject.Named;

import ru.naumen.entities.Course;
import ru.naumen.entities.Student;

/**
 * @author aarkaev
 */
@Named
public class InMemoryStudentDao implements StudentDao {

    private List<Student> storage = new ArrayList<>();

    @Override
    public List<Student> findAll() {
        return storage;
    }

    @Override
    public List<Student> listCourseStudents(Course course) {
        return storage.stream()
            .filter(student -> student.getCourse().equals(course))
            .collect(Collectors.toList());
    }

    @Override
    public void create(Student student) {
        storage.add(student);
    }

    @Override
    public Optional<Student> find(int id) {
        return storage.stream()
            .filter(st -> st.getId() == id)
            .findFirst();
    }

    @Override
    public void delete(Student student) {
        storage.remove(student);
    }
}
