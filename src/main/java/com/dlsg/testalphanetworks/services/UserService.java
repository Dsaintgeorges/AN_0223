package com.dlsg.testalphanetworks.services;

import com.dlsg.testalphanetworks.models.dao.Animal;
import com.dlsg.testalphanetworks.models.dao.User;
import com.dlsg.testalphanetworks.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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


    public void createUser(User user) {
        userRepository.save(user);
    }

    public Optional<User> getUserById(UUID userId) {
        return userRepository.findById(userId);
    }

    public Page<User> getAllUsers(Pageable pageable){
        return userRepository.findAll(pageable);
    }

    public void updateUser(User user) {
        userRepository.save(user);
    }

    public void deleteUser(UUID userId) {
        userRepository.deleteById(userId);
    }
}
