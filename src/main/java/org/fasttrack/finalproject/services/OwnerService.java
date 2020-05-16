package org.fasttrack.finalproject.services;

import org.fasttrack.finalproject.domain.Owner;
import org.fasttrack.finalproject.domain.Pet;
import org.fasttrack.finalproject.repositories.OwnerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
        Pet newPet=pet;
        newPet.setId(0);
        newOwner.addPet(pet);
        ownerRepository.save(newOwner);
    }
}
