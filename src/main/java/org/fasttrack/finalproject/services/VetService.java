package org.fasttrack.finalproject.services;

import org.fasttrack.finalproject.domain.Vet;
import org.fasttrack.finalproject.repositories.VetRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VetService {
    private final VetRepository vetRepository;

    public VetService(VetRepository vetRepository) {
        this.vetRepository = vetRepository;
    }


    public List<Vet> getAll() {
        List<Vet> result = new ArrayList<>();
        vetRepository.findAll().forEach(result::add);
        return result;
    }

    public Vet getById(int id) {
        return vetRepository.findById(id).orElse(null);
    }

    public List<Vet> findByLastName(String lastName) {
        return vetRepository.findByLastName(lastName);
    }

    public Vet add(Vet vet) {
        Vet newVet=vet;
        newVet.setId(0);
        System.out.println(newVet);
        return vetRepository.save(newVet);
    }

    public void deleteById(int id) {
        vetRepository.deleteById(id);
    }
}
