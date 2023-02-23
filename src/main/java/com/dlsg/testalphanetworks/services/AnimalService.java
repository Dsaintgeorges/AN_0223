package com.dlsg.testalphanetworks.services;

import com.dlsg.testalphanetworks.models.dao.Animal;
import com.dlsg.testalphanetworks.models.dao.User;
import com.dlsg.testalphanetworks.repository.AnimalRepository;
import com.sun.istack.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Animal createAnimal(@NotNull Animal animal) {
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

    public Page<Animal> getAllAnimals(Pageable pageable) {
            return animalRepository.findAll(pageable);
    }


}




