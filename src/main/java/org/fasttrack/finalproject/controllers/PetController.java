package org.fasttrack.finalproject.controllers;

import org.fasttrack.finalproject.domain.Pet;
import org.fasttrack.finalproject.domain.Visit;
import org.fasttrack.finalproject.services.PetService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("pets")
@CrossOrigin(origins = "http://localhost:4200")
public class PetController {
    private final PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    @GetMapping
    public List<Pet> getAll(@RequestParam(required = false) Integer ownerId) {
        return ownerId == null ? petService.getAll() : petService.getByOwnerId(ownerId);
    }   

    @GetMapping("/{id}")
    public Pet getById(@PathVariable int id) {
        return petService.getById(id);
    }

    @PostMapping
    public Pet addPet(@RequestBody Pet pet) {
        return petService.add(pet);
    }

    @DeleteMapping("/{id}")
    public void deletePet(@PathVariable Integer id) {
        petService.deleteById(id);
    }

    @PostMapping("/{id}")
    public void addVisit(@PathVariable Integer id, @RequestBody String date) {
        petService.addVisit(id, date);
    }

    @PutMapping("{id}")
    public Pet editPet(@PathVariable int id, @RequestBody Pet pet) {
        return petService.editPet(id, pet);
    }

}
