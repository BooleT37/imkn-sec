package ru.naumen.model;

import java.util.List;

import ru.naumen.entities.Course;

/**
 * @author aarkaev
 * @since 21.11.2016
 */
public interface CourseDao {
    List<Course> findAll();

    void create(Course course);
}
