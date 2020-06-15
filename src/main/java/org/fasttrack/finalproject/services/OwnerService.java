package org.fasttrack.finalproject.services;

import org.fasttrack.finalproject.domain.Owner;
import org.fasttrack.finalproject.domain.Pet;
import org.fasttrack.finalproject.repositories.OwnerRepository;
import org.springframework.stereotype.Service;

import javax.persistence.SecondaryTable;
import java.time.LocalDate;
import java.util.*;

@Service
public class OwnerService {
    private final OwnerRepository ownerRepository;

    public OwnerService(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
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
        Owner newOwner = owner;
        newOwner.setId(0);
        return ownerRepository.save(newOwner);
    }

    public void deleteById(int id) {
        ownerRepository.deleteById(id);
    }

    public void addPet(int id, Pet pet) {
        Owner newOwner = Objects.requireNonNull(ownerRepository.findById(id).orElse(null));
        pet.setId(0);
        pet.setNewBirthDate(LocalDate.parse(pet.getBirthDate()));
        newOwner.addPet(pet);
        ownerRepository.save(newOwner);
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

    public Set<Pet> getPets(int id) {
        Set<Pet> res = new HashSet<>(ownerRepository.findById(id).get().getPets());
        return res;
    }
}
