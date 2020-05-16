package org.fasttrack.finalproject.repositories;

import org.fasttrack.finalproject.domain.Pet;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PetRepository extends CrudRepository<Pet, Integer> {
    List<Pet> findByName(String name);
}
