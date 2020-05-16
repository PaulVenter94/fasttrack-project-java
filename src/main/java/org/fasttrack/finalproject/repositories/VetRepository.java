package org.fasttrack.finalproject.repositories;


import org.fasttrack.finalproject.domain.Vet;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface VetRepository extends CrudRepository<Vet, Integer> {
    List<Vet> findByLastName(String lastName);
}
