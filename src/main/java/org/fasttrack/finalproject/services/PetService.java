package org.fasttrack.finalproject.services;

import org.fasttrack.finalproject.domain.Owner;
import org.fasttrack.finalproject.domain.Pet;
import org.fasttrack.finalproject.domain.Visit;
import org.fasttrack.finalproject.repositories.OwnerRepository;
import org.fasttrack.finalproject.repositories.PetRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class PetService {
    private final PetRepository petRepository;
    private final OwnerRepository ownerRepository;

    public PetService(PetRepository petRepository, OwnerRepository ownerRepository) {
        this.petRepository = petRepository;
        this.ownerRepository = ownerRepository;
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
        Owner owner = Objects.requireNonNull(ownerRepository.findById(newPet.getOwner().getId()).orElse(null));
        visit.setId(0);
        visit.setPet(newPet);
        newPet.addVisit(visit);
        petRepository.save(newPet);
    }

    public void addVisit(int id, int year, int month, int day, int hour, int minutes) {
        Pet newPet = Objects.requireNonNull(petRepository.findById(id).orElse(null));
        newPet.addVisit(new Visit(LocalDateTime.of(year, month, day, hour, minutes), newPet));
        petRepository.save(newPet);
    }

    public void addVisit(int id, String date) {
        Pet pet = petRepository.findById(id).get();
        LocalDateTime newDate = LocalDateTime.parse(date);
        Visit visit = new Visit(newDate, pet);
        visit.setId(0);
        pet.addVisit(visit);
        petRepository.save(pet);
    }

    public Pet editPet(int id, Pet pet) {
        Pet newPet = petRepository.findById(id).orElse(null);
        if (newPet != null) {
            newPet.setName(pet.getName());
            petRepository.save(pet);
        }
        return newPet;
    }

}
