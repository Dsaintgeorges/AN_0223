package com.dlsg.testalphanetworks.repository;

import com.dlsg.testalphanetworks.models.dao.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends CrudRepository<User,UUID> {

   Page<User> findAll(Pageable pageable);
}
