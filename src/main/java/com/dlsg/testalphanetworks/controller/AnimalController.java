package com.dlsg.testalphanetworks.controller;

import com.dlsg.testalphanetworks.models.dao.Animal;
import com.dlsg.testalphanetworks.models.dao.User;
import com.dlsg.testalphanetworks.models.dto.AnimalRequest;
import com.dlsg.testalphanetworks.models.dto.AnimalUpdateRequest;
import com.dlsg.testalphanetworks.services.AnimalService;
import com.dlsg.testalphanetworks.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@RestController
@RequestMapping("/animal")
public class AnimalController {

    private final AnimalService animalService;
    private static final Logger logger = LoggerFactory.getLogger(AnimalController.class);

    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @PostMapping
    public ResponseEntity<?> createAnimal(@RequestBody AnimalRequest animal) {
        try {
            Animal createdAnimal = new Animal();
            createdAnimal.setName(animal.getName());
            createdAnimal.setType(animal.getType());
            createdAnimal.setUserId(animal.getUserId());
            Animal retVal = animalService.createAnimal(createdAnimal);
            return ResponseEntity.status(HttpStatus.CREATED).body(retVal);
        } catch (Exception e) {
            logger.error("Invalid animal data", e);
            return ResponseEntity.badRequest().body("Invalid animal data");
        }
    }
    @GetMapping("/{animalId}")
    public ResponseEntity<Animal> findAnimal(@PathVariable UUID animalId) {
        try{
            Animal animal = animalService.findAnimal(animalId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Animal not found"));
            return ResponseEntity.ok(animal);
        }catch (IllegalArgumentException e) {
            logger.error("Internal server error", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error", e);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<Page<Animal>> getAllAnimals(@RequestParam("page") int page, @RequestParam("size") int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<Animal> animals = animalService.getAllAnimals(pageable);
            return ResponseEntity.ok(animals);
        } catch (IllegalArgumentException e) {
            logger.error("Invalid page or size parameter", e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid page or size parameter", e);
        } catch (Exception e) {
            logger.error("Internal server error", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error", e);
        }
    }

    @PutMapping("/{animalId}")
    public ResponseEntity<Void> updateAnimal(@PathVariable UUID animalId, @RequestBody AnimalUpdateRequest animalRequest) {
        try{
            Animal animal = this.animalService.findAnimal(animalId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Animal not found"));
            animal.setName(animalRequest.getName());
            animal.setType(animalRequest.getType());
            animalService.updateAnimal(animal);
            return ResponseEntity.noContent().build();
        }catch(Exception e){
            logger.error("Internal server error", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error", e);
        }

    }
    @DeleteMapping("/{animalId}")
    public ResponseEntity<Void> deleteAnimal(@PathVariable UUID animalId) {
        animalService.deleteAnimal(animalId);
        return ResponseEntity.noContent().build();
    }







}
