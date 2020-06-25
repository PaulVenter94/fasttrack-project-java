package org.fasttrack.finalproject.services;

import org.fasttrack.finalproject.domain.Owner;
import org.fasttrack.finalproject.domain.Pet;
import org.fasttrack.finalproject.repositories.OwnerRepository;
import org.fasttrack.finalproject.repositories.PetRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class OwnerService {
    private final OwnerRepository ownerRepository;
    private final PetRepository petRepository;

    public OwnerService(OwnerRepository ownerRepository, PetRepository petRepository) {
        this.ownerRepository = ownerRepository;
        this.petRepository = petRepository;
    }

    public List<Owner> getAll() {
        List<Owner> result = new ArrayList<>();
        ownerRepository.findAll().forEach(result::add);
        return result;
    }

    public Owner getById(int id) {
        return ownerRepository.findById(id).orElse(null);
    }

    public List<Owner> findByLastName(String lastName) {
        return ownerRepository.findByLastName(lastName);
    }

    public Owner add(Owner owner) {
        owner.setId(0);
        return ownerRepository.save(owner);
    }

    public void deleteById(int id) {
        ownerRepository.deleteById(id);
    }

    public void addPet(int id, Pet pet) {
        pet.setId(0);
        pet.setOwner(ownerRepository.findById(id).get());
        pet.setNewBirthDate(LocalDate.parse(pet.getBirthDate()));
        petRepository.save(pet);
    }

    public Owner editOwner(int id, Owner owner) {
        Owner newOwner = ownerRepository.findById(id).orElse(null);
        if (newOwner != null) {
            newOwner.setFirstName(owner.getFirstName());
            newOwner.setLastName(owner.getLastName());
            ownerRepository.save(newOwner);
        }
        return newOwner;
    }

}
