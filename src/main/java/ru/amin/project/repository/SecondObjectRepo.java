package ru.amin.project.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.amin.project.dto.FirstObject;
import ru.amin.project.dto.SecondObject;

import java.util.List;

@Repository
public interface SecondObjectRepo extends CrudRepository<SecondObject, Long> {

    List<SecondObject> findAll();

}
