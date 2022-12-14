package ru.example.project.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.example.project.dto.FirstObject;

import java.util.List;

@Repository
public interface FirstObjectRepo extends CrudRepository<FirstObject, Long> {

    List<FirstObject> findAll();

}
