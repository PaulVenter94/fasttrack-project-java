package org.fasttrack.finalproject.controllers;

import org.fasttrack.finalproject.domain.Owner;
import org.fasttrack.finalproject.domain.Pet;
import org.fasttrack.finalproject.services.OwnerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class OwnerController {
    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @GetMapping("/owners")
    public List<Owner> getOwners() {
        return ownerService.getAll();
    }

    @GetMapping("/owners/{id}")
    public Owner getById(@PathVariable int id) {
        return ownerService.getById(id);
    }

    @PostMapping("/owners")
    public Owner addOwner(@RequestBody Owner owner) {
        return ownerService.add(owner);
    }

    @DeleteMapping("/owners/{id}")
    public void delete(@PathVariable int id) {
        ownerService.deleteById(id);
    }

    @PostMapping("/owners/{id}")
    public void addPet(@PathVariable int id, @RequestBody Pet pet) {
        ownerService.addPet(id, pet);
    }

    @PutMapping("/owners/{id}")
    public void editOwner(@PathVariable int id, @RequestBody Owner owner) {
        ownerService.editOwner(id, owner);
    }

    @GetMapping("/owners/{id}/pets")
    public Set<Pet> getPets(@PathVariable int id) {
        return ownerService.getPets(id);
    }

}
