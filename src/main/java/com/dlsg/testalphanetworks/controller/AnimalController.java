package com.dlsg.testalphanetworks.controller;

import com.dlsg.testalphanetworks.models.Animal;
import com.dlsg.testalphanetworks.services.AnimalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@RestController
@RequestMapping("/animal")
public class AnimalController {

    private final AnimalService animalService;

    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @DeleteMapping("/{animalId}")
    public ResponseEntity<Void> deleteAnimal(@PathVariable UUID animalId) {
        animalService.deleteAnimal(animalId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{animalId}")
    public ResponseEntity<Void> updateAnimal(@PathVariable UUID animalId, @RequestBody Animal animal) {
        animal.setId(animalId);
        animalService.updateAnimal(animal);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{animalId}")
    public ResponseEntity<Animal> findAnimal(@PathVariable UUID animalId) {
        Animal animal = animalService.findAnimal(animalId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Animal not found"));
        return ResponseEntity.ok(animal);
    }

    @GetMapping("/all")
    public ResponseEntity<Iterable<Animal>> getAllAnimals() {
        Iterable<Animal> animals = animalService.getAllAnimals();
        return ResponseEntity.ok(animals);
    }

    @PostMapping
    public ResponseEntity<Animal> createAnimal(@RequestBody Animal animal) {
        Animal createdAnimal = animalService.createAnimal(animal);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAnimal);
    }
}
