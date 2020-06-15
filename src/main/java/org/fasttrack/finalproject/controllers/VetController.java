package org.fasttrack.finalproject.controllers;

import org.fasttrack.finalproject.domain.Vet;
import org.fasttrack.finalproject.services.VetService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("vets")
@CrossOrigin(origins = "http://localhost:4200")
public class VetController {
    private VetService vetService;

    public VetController(VetService vetService) {
        this.vetService = vetService;
    }

    @GetMapping
    public List<Vet> getAll() {
        return vetService.getAll();
    }

    @GetMapping("/{id}")
    public Vet getById(@PathVariable int id) {
        return vetService.getById(id);
    }

    @PostMapping
    public Vet addPet(@RequestBody Vet vet) {
        return vetService.add(vet);
    }

    @DeleteMapping
    public void deletePet(@RequestBody int id) {
        vetService.deleteById(id);
    }

    @PutMapping("/vets/{id}")
    public void editOwner(@PathVariable int id, @RequestBody Vet vet) {
        vetService.editVet(id, vet);
    }
}
