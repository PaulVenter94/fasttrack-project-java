package org.fasttrack.finalproject.controllers;

import org.fasttrack.finalproject.domain.Visit;
import org.fasttrack.finalproject.services.VisitService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("visits")
@CrossOrigin(origins = "http://localhost:4200")
public class VisitController {
    private final VisitService visitService;

    public VisitController(VisitService visitService) {
        this.visitService = visitService;
    }

    @GetMapping
    public List<Visit> getOwners() {
        return visitService.getAll();
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
}
