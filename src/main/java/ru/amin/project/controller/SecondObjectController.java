package ru.amin.project.controller;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.amin.project.dto.FirstObject;
import ru.amin.project.dto.SecondObject;
import ru.amin.project.model.SecondObjectCreationModel;
import ru.amin.project.service.FirstObjectService;
import ru.amin.project.service.SecondObjectService;

import java.util.List;
import java.util.Objects;


@Controller
@RequestMapping("second/object")
public class SecondObjectController {

    private final SecondObjectService secondObjectService;
    private final FirstObjectService firstObjectService;

    @Autowired
    public SecondObjectController(SecondObjectService secondObjectService, FirstObjectService firstObjectService) {
        this.secondObjectService = secondObjectService;
        this.firstObjectService = firstObjectService;
    }

    @PostMapping
    public ResponseEntity<SecondObject> createSO(@RequestBody SecondObjectCreationModel solution) {
        if(Objects.isNull(solution.getRequestId())) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        try {
            FirstObject request = firstObjectService.getFO(solution.getRequestId());
            SecondObject resultSolution = new SecondObject(solution, request);
            secondObjectService.saveSO(resultSolution);
            return new ResponseEntity<>(resultSolution, HttpStatus.CREATED);
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<SecondObject> getSO(@PathVariable Long id) {
        try {
            SecondObject secondObject = secondObjectService.getSO(id);
            return new ResponseEntity<>(secondObject, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<SecondObject>> getAllSO() {
        try {
            return new ResponseEntity<>(secondObjectService.getAllSO(), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<SecondObject> updateSO(@PathVariable Long id, @RequestBody SecondObject updateSecondObject) {
        try {
            updateSecondObject.setId(id);
            secondObjectService.saveSO(updateSecondObject);
            return new ResponseEntity<>(updateSecondObject, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSO(@PathVariable Long id) {
        try {
            secondObjectService.removeSO(id);
            return new ResponseEntity<>("Solution successful removed", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);


        }
    }

}
