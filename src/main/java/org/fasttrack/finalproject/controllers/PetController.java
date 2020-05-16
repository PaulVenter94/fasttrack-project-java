package org.fasttrack.finalproject.controllers;

import org.fasttrack.finalproject.domain.Pet;
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
    public List<Pet> getAll() {
        return petService.getAll();
    }

    @GetMapping("/{id}")
    public Pet getById(@PathVariable int id) {
        return petService.getById(id);
    }

    @PostMapping
    public Pet addPet(@RequestBody Pet pet) {
        return petService.add(pet);
    }

    @DeleteMapping
    public void deletePet(@RequestBody int id) {
        petService.deleteById(id);
    }
}
