package com.dlsg.testalphanetworks.services;

import com.dlsg.testalphanetworks.models.Animal;
import com.dlsg.testalphanetworks.models.User;
import com.dlsg.testalphanetworks.repository.AnimalRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
public class AnimalService {


    private final AnimalRepository animalRepository;

    public AnimalService(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    public Animal createAnimal(Animal animal) {
        return animalRepository.save(animal);
    }

    public void deleteAnimal(UUID animalId) {
        animalRepository.deleteById(animalId);
    }

    public void updateAnimal(Animal animal) {
        animalRepository.save(animal);
    }

    public Optional<Animal> findAnimal(UUID animalId) {
        return animalRepository.findById(animalId);
    }

    public Iterable<Animal> getAllAnimals() {
        return animalRepository.findAll();
    }

    @Transactional
    public void createAnimalsForUser(User user) {
        Set<Animal> animals = user.getAnimals();
        user.setAnimals(null);

        for (Animal animal : animals) {
            animal.setUser(user);
            animalRepository.save(animal);
        }
    }
}




