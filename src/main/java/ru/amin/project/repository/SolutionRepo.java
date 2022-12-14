package ru.amin.project.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.amin.project.dto.Solution;

@Repository
public interface SolutionRepo extends CrudRepository<Solution, Long> {
}
