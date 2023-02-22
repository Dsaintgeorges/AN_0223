package com.dlsg.testalphanetworks.repository;

import com.dlsg.testalphanetworks.models.Animal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AnimalRepository extends CrudRepository<Animal, UUID> {

}
