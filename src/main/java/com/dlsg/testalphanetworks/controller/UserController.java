package com.dlsg.testalphanetworks.controller;

import com.dlsg.testalphanetworks.models.dao.User;
import com.dlsg.testalphanetworks.models.dto.UserRequest;
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

import java.util.Optional;
import java.util.UUID;

@RestController
public class UserController {

    private final UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserRequest user) {
        User userToCreate = new User(user);
        userService.createUser(userToCreate);
        return ResponseEntity.ok(userToCreate);
    }
    @GetMapping("/all")
    public ResponseEntity<Page<User>> getAllUsers(@RequestParam("page") int page, @RequestParam("size") int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            return ResponseEntity.ok(userService.getAllUsers(pageable));
        } catch (IllegalArgumentException e) {
            logger.error("Invalid page or size parameter", e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid page or size parameter", e);
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable String userId) {
        try{
            User user = userService.getUserById(UUID.fromString(userId))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
            return ResponseEntity.ok(user);
        }catch(IllegalArgumentException e){
            logger.error("Internal Server error", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error", e);
        }

    }

    @PutMapping("/{userId}")
    public ResponseEntity<Void> updateUser(@PathVariable UUID userId, @RequestBody UserRequest user) {
        try{
            User userToUpdate = userService.getUserById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
            userToUpdate.setFirstname(user.getFirstname());
            userToUpdate.setName(user.getName());
            userService.updateUser(userToUpdate);
            return ResponseEntity.noContent().build();
        }catch (IllegalArgumentException e){
            logger.error("Internal Server error", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error", e);
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }
}
