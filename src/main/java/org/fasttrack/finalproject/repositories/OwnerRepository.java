package org.fasttrack.finalproject.repositories;

import org.fasttrack.finalproject.domain.Owner;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OwnerRepository extends CrudRepository<Owner, Integer> {
    List<Owner> findByLastName(String lastName);
}
