package org.fasttrack.finalproject.services;

import org.fasttrack.finalproject.domain.Pet;
import org.fasttrack.finalproject.domain.Visit;
import org.fasttrack.finalproject.repositories.PetRepository;
import org.fasttrack.finalproject.repositories.VisitRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class PetService {
    private final PetRepository petRepository;
    private final VisitRepository visitRepository;

    public PetService(PetRepository petRepository, VisitRepository visitRepository) {
        this.petRepository = petRepository;
        this.visitRepository = visitRepository;
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
        pet.setId(0);
        return petRepository.save(pet);
    }

    public void deleteById(int id) {
        petRepository.deleteById(id);
    }

    public void addVisit(int id, Visit visit) {
        Pet newPet = Objects.requireNonNull(petRepository.findById(id).orElse(null));
        visit.setId(0);
        visit.setPet(newPet);
        visitRepository.save(visit);
    }


    public void addVisit(int id, String date) {
        visitRepository.save(new Visit(LocalDateTime.parse(date), petRepository.findById(id).get()));
    }

    public Pet editPet(int id, Pet pet) {
        pet.setId(id);
        pet.setNewBirthDate(LocalDate.parse(pet.getBirthDate()));
        petRepository.save(pet);
        return pet;
    }

    public List<Visit> getVisits(int id) {
        return visitRepository.findByPet_Id(id);
    }

    public List<Pet> getByOwnerId(Integer ownerId) {
        return petRepository.findByOwner_Id(ownerId);
    }
}
