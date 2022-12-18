package ru.example.project.controller;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.example.project.dto.FirstObject;
import ru.example.project.service.FirstObjectService;

import java.util.List;

@Controller
// Установка основного пути
@RequestMapping("first/object")
@CrossOrigin
public class FirstObjectController {

    private final FirstObjectService firstObjectService;

    @Autowired
    public FirstObjectController(FirstObjectService firstObjectService) {
        this.firstObjectService = firstObjectService;
    }

    // придаточный путь (пример итога: first/object/12)
    @GetMapping("/{id}")
    public ResponseEntity<FirstObject> getFO(@PathVariable Long id) {
        try {
            FirstObject request = firstObjectService.getFO(id);
            return new ResponseEntity<>(request, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<FirstObject>> getAllFO() {
        try {
            List<FirstObject> request = firstObjectService.getAllFO();
            return new ResponseEntity<>(request, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<FirstObject> createFO(@RequestBody FirstObject newFirstObject) {
        try {
            FirstObject firstObject = firstObjectService.saveFO(newFirstObject);
            return new ResponseEntity<>(firstObject, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<FirstObject> updateFO(@PathVariable Long id, @RequestBody FirstObject newFirstObject) {
        try {
            newFirstObject.setId(id);
            FirstObject firstObject = firstObjectService.saveFO(newFirstObject);
            return new ResponseEntity<>(firstObject, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFO(@PathVariable Long id) {
        try {
            firstObjectService.removeFO(id);
            return new ResponseEntity<>("Request was deleted", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
