package ru.amin.project.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.amin.project.dto.Request;
import ru.amin.project.dto.Solution;
import ru.amin.project.repository.SolutionRepo;

import java.util.Objects;
import java.util.Optional;

@Service
public class SolutionService {

    private final SolutionRepo solutionRepo;

    @Autowired
    public SolutionService(SolutionRepo solutionRepo) {
        this.solutionRepo = solutionRepo;
    }

    public Solution saveSolution(Solution solution) {
        Optional<Solution> solutionOptional = solutionRepo.findById(solution.getId());
        return solutionOptional
                .map(value -> updateSolution(solution, value))
                .orElseGet(() -> solutionRepo.save(solution));
    }

    public Solution getSolution(Long id) throws EntityNotFoundException {
        Optional<Solution> solutionOptional = solutionRepo.findById(id);
        if(solutionOptional.isEmpty()) throw new EntityNotFoundException();
        return solutionOptional.get();
    }

    public void removeSolution(Long id) {
        Optional<Solution> solutionOptional = solutionRepo.findById(id);
        if(solutionOptional.isEmpty()) throw new EntityNotFoundException();
        solutionRepo.delete(solutionOptional.get());
    }

    private Solution updateSolution(Solution solution, Solution dbSolution) {
        if(Objects.nonNull(solution.getMessage())) dbSolution.setMessage(solution.getMessage());
        if(Objects.nonNull(solution.getTitle())) dbSolution.setTitle(solution.getTitle());
        solutionRepo.save(dbSolution);
        return dbSolution;
    }

}
