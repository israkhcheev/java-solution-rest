package ru.example.project.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.example.project.dto.FirstObject;
import ru.example.project.repository.FirstObjectRepo;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class FirstObjectService {

    private final FirstObjectRepo firstObjectRepo;

    @Autowired
    public FirstObjectService(FirstObjectRepo firstObjectRepo) {
        this.firstObjectRepo = firstObjectRepo;
    }

    public FirstObject saveFO(FirstObject firstObject) {
        Optional<FirstObject> firstObjectOptional = Optional.empty();
        if(Objects.nonNull(firstObject.getId())) firstObjectOptional = firstObjectRepo.findById(firstObject.getId());
        if(firstObjectOptional.isEmpty()) {
            return firstObjectRepo.save(firstObject);
        } else {
            return updateFO(firstObject, firstObjectOptional.get());
        }
    }

    public FirstObject getFO(Long id) throws EntityNotFoundException {
        Optional<FirstObject> firstObjectOptional = firstObjectRepo.findById(id);
        if(firstObjectOptional.isEmpty()) throw new EntityNotFoundException();
        return firstObjectOptional.get();
    }

    public List<FirstObject> getAllFO() {
        return firstObjectRepo.findAll();
    }

    public void removeFO(Long id) {
        Optional<FirstObject> firstObjectOptional = firstObjectRepo.findById(id);
        if(firstObjectOptional.isEmpty()) throw new EntityNotFoundException();
        firstObjectRepo.delete(firstObjectOptional.get());
    }

    private FirstObject updateFO(FirstObject firstObject, FirstObject databaseFirstObject) {
        if(Objects.nonNull(firstObject.getTitle())) databaseFirstObject.setTitle(firstObject.getTitle());
        if(Objects.nonNull(firstObject.getStatus())) databaseFirstObject.setStatus(firstObject.getStatus());
        firstObjectRepo.save(databaseFirstObject);
        return databaseFirstObject;
    }

}
