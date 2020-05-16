package org.fasttrack.finalproject.controllers;

import org.fasttrack.finalproject.domain.Owner;
import org.fasttrack.finalproject.domain.Pet;
import org.fasttrack.finalproject.services.OwnerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @DeleteMapping("/owners")
    public void delete(@RequestBody int id) {
        ownerService.deleteById(id);
    }

    @PostMapping("/owners/{id}")
    public void addPet(@PathVariable int id, @RequestBody Pet pet) {
        ownerService.addPet(id, pet);
    }

}
