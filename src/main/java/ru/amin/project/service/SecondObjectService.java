package ru.amin.project.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.amin.project.dto.FirstObject;
import ru.amin.project.dto.SecondObject;
import ru.amin.project.repository.SecondObjectRepo;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class SecondObjectService {

    private final SecondObjectRepo secondObjectRepo;

    @Autowired
    public SecondObjectService(SecondObjectRepo secondObjectRepo) {
        this.secondObjectRepo = secondObjectRepo;
    }

    public SecondObject saveSO(SecondObject secondObject) {
        Optional<SecondObject> secondObjectOptional = Optional.empty();
        if(Objects.nonNull(secondObject.getId())) secondObjectOptional = secondObjectRepo.findById(secondObject.getId());
        if(secondObjectOptional.isEmpty()) {
            return secondObjectRepo.save(secondObject);
        } else {
            return updateSO(secondObject, secondObjectOptional.get());
        }
    }

    public SecondObject getSO(Long id) throws EntityNotFoundException {
        Optional<SecondObject> secondObjectOptional = secondObjectRepo.findById(id);
        if(secondObjectOptional.isEmpty()) throw new EntityNotFoundException();
        return secondObjectOptional.get();
    }

    public List<SecondObject> getAllSO() {
        return secondObjectRepo.findAll();
    }

    public void removeSO(Long id) {
        Optional<SecondObject> secondObjectOptional = secondObjectRepo.findById(id);
        if(secondObjectOptional.isEmpty()) throw new EntityNotFoundException();
        secondObjectRepo.delete(secondObjectOptional.get());
    }

    private SecondObject updateSO(SecondObject secondObject, SecondObject databaseSecondObject) {
        if(Objects.nonNull(secondObject.getTitle())) databaseSecondObject.setTitle(secondObject.getTitle());
        secondObjectRepo.save(databaseSecondObject);
        return databaseSecondObject;
    }

}
