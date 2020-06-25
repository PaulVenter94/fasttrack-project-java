package org.fasttrack.finalproject.repositories;

import org.fasttrack.finalproject.domain.Visit;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface VisitRepository extends CrudRepository<Visit, Integer> {
    List<Visit> findByPet_Id(int id);
}
