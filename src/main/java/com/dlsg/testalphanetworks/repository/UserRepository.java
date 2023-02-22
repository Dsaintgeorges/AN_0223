package com.dlsg.testalphanetworks.repository;

import com.dlsg.testalphanetworks.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepository extends CrudRepository<User,UUID> {

   List<User> findAll();
}
