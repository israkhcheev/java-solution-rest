package ru.amin.project.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.amin.project.dto.FirstObject;

import java.util.List;

@Repository
public interface FirstObjectRepo extends CrudRepository<FirstObject, Long> {

    List<FirstObject> findAll();

}
