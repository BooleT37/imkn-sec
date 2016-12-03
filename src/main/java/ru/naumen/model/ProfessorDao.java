package ru.naumen.model;

import ru.naumen.entities.Professor;

import java.util.Optional;

public interface ProfessorDao {

    Optional<Professor> find(int id);

    void save(Professor professor);

    Optional<Professor> findByLogin(String login);
}
