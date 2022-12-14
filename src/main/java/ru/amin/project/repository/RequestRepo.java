package ru.amin.project.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.amin.project.dto.Request;

@Repository
public interface RequestRepo extends CrudRepository<Request, Long> {
}
