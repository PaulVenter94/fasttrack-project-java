package org.fasttrack.finalproject.services;

import org.fasttrack.finalproject.domain.Pet;
import org.fasttrack.finalproject.domain.Visit;
import org.fasttrack.finalproject.repositories.PetRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class PetService {
    private final PetRepository petRepository;

    public PetService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    public List<Pet> getAll() {
        List<Pet> result = new ArrayList<>();
        petRepository.findAll().forEach(result::add);
        return result;
    }

    public Pet getById(int id) {
        return petRepository.findById(id).orElse(null);
    }

    public List<Pet> findByLastName(String name) {
        return petRepository.findByName(name);
    }

    public Pet add(Pet pet) {
        Pet newPet = pet;
        newPet.setId(0);
        return petRepository.save(newPet);
    }

    public void deleteById(int id) {
        petRepository.deleteById(id);
    }

    public void addVisit(int id, Visit visit) {
        Pet newPet = Objects.requireNonNull(petRepository.findById(id).orElse(null));
        newPet.addVisit(visit);
        petRepository.save(newPet);
    }
}