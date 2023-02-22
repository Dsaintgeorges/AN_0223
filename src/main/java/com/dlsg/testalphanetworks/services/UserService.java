package com.dlsg.testalphanetworks.services;

import com.dlsg.testalphanetworks.models.Animal;
import com.dlsg.testalphanetworks.models.User;
import com.dlsg.testalphanetworks.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;


@Service
public class UserService {

    private final UserRepository userRepository;
    private final AnimalService animalService;

    public UserService(UserRepository userRepository, AnimalService animalService) {
        this.userRepository = userRepository;
        this.animalService = animalService;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(UUID userId) {
        return userRepository.findById(userId);
    }

    @Transactional
    public void createUser(User user) {
        Set<Animal> animals = user.getAnimals();
        user.setAnimals(null);

        userRepository.save(user);

        for (Animal animal : animals) {
            animal.setUser(user);
            animalService.createAnimal(animal);
        }
    }

    public void updateUser(User user) {
        userRepository.save(user);
    }

    public void deleteUser(UUID userId) {
        userRepository.deleteById(userId);
    }
}
