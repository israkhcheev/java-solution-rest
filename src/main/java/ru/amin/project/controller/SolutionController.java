package ru.amin.project.controller;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.amin.project.dto.Request;
import ru.amin.project.dto.Solution;
import ru.amin.project.model.SolutionInsertion;
import ru.amin.project.service.RequestService;
import ru.amin.project.service.SolutionService;

import java.util.Objects;
import java.util.Optional;


@Controller
@RequestMapping(path = "solution")
public class SolutionController {

    private final SolutionService solutionService;
    private final RequestService requestService;

    @Autowired
    public SolutionController(SolutionService solutionService, RequestService requestService) {
        this.solutionService = solutionService;
        this.requestService = requestService;
    }

    @PostMapping
    public ResponseEntity<Solution> createSolution(@RequestBody SolutionInsertion solution) {
        if(Objects.isNull(solution.getId())) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        try {
            Request request = requestService.getRequest(solution.getRequestId());
            Solution resultSolution = new Solution(solution, request);
            solutionService.saveSolution(resultSolution);
            return new ResponseEntity<>(resultSolution, HttpStatus.CREATED);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Solution> getSolution(@PathVariable Long id) {
        try {
            Solution solution = solutionService.getSolution(id);
            return new ResponseEntity<>(solution, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Solution> updateSolution(@PathVariable Long id, @RequestBody Solution updateSolution) {
        try {
            updateSolution.setId(id);
            solutionService.saveSolution(updateSolution);
            return new ResponseEntity<>(updateSolution, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSolution(@PathVariable Long id) {
        try {
            solutionService.removeSolution(id);
            return new ResponseEntity<>("Solution successful removed", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);


        }
    }

}
