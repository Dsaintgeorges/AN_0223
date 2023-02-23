package com.dlsg.testalphanetworks.repository;

import com.dlsg.testalphanetworks.models.dao.Animal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AnimalRepository extends CrudRepository<Animal, UUID> {

	Page<Animal>findAll(Pageable pageable);

}
