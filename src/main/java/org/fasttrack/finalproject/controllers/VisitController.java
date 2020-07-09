package org.fasttrack.finalproject.controllers;

import org.fasttrack.finalproject.domain.Visit;
import org.fasttrack.finalproject.services.VisitService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("visits")
public class VisitController {
    private final VisitService visitService;

    public VisitController(VisitService visitService) {
        this.visitService = visitService;
    }

    @GetMapping
    public List<Visit> getOwners(@RequestParam(required = false) Integer petId) {
        return petId == null ? visitService.getAll() : visitService.getVisits(petId);
    }

    @GetMapping("/{id}")
    public Visit getById(@PathVariable int id) {
        return visitService.getById(id);
    }

    @PostMapping
    public Visit addOwner(@RequestBody Visit visit) {
        return visitService.add(visit);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        visitService.deleteById(id);
    }

//    @PutMapping("/{id}")
//    public void editVisits(@PathVariable int id, @RequestBody Visit visit) {
//        visitService.editVisit(id, visit);
//    }

    @PutMapping("/{id}")
    public Visit updateVisits(@PathVariable int id, @RequestBody String bodyUpdate) {
       return visitService.updateVisit(id, bodyUpdate);
    }
}
