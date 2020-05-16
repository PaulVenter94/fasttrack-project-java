package org.fasttrack.finalproject.bootstrap;

import org.fasttrack.finalproject.domain.Owner;
import org.fasttrack.finalproject.domain.Pet;
import org.fasttrack.finalproject.domain.Vet;
import org.fasttrack.finalproject.domain.Visit;
import org.fasttrack.finalproject.services.OwnerService;
import org.fasttrack.finalproject.services.PetService;
import org.fasttrack.finalproject.services.VetService;
import org.fasttrack.finalproject.services.VisitService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class DataLoader implements CommandLineRunner {
    private final OwnerService ownerService;
    private final PetService petService;
    private final VetService vetService;
    private final VisitService visitService;

    public DataLoader(OwnerService ownerService, PetService petService, VetService vetService, VisitService visitService) {
        this.ownerService = ownerService;
        this.petService = petService;
        this.vetService = vetService;
        this.visitService = visitService;
    }

    @Override
    public void run(String... args) throws Exception {
        loadData();
    }

    private void loadData() {
        ownerService.add(new Owner("Dumitru", "Pol"));
        ownerService.add(new Owner("Lionel", "Messi"));
        ownerService.add(new Owner("Ousmane", "Dembele"));
        ownerService.add(new Owner("Luis", "Suarez"));
        ownerService.add(new Owner("Gerrard", "Pique"));
        System.out.println("Owners loaded");

        ownerService.addPet(1, new Pet("Archibald"));
        ownerService.addPet(2, new Pet("Glenn"));
        ownerService.addPet(3, new Pet("Castron"));
        ownerService.addPet(4, new Pet("Pennywise"));
        ownerService.addPet(5, new Pet("Jason"));
        System.out.println("Pets loaded");

        vetService.add(new Vet("Gregory", "Pol", 74, "Radiology"));
        vetService.add(new Vet("Laris", "Jurcut", 21, null));
        vetService.add(new Vet("Gregor", "Clegane", 45, "Dermatology"));
        vetService.add(new Vet("Maluma", "Baby", 26, "Behavior"));
        vetService.add(new Vet("John", "Cena", 34, "Dermatology"));
        System.out.println("Vets loaded");

        petService.getAll().forEach(pet -> petService.addVisit(pet.getId(), new Visit(LocalDateTime.now(), pet)));
        System.out.println(petService.getAll());
        System.out.println(visitService.getAll());
        System.out.println("Visits Loaded");
        System.out.println(petService.getAll().stream()
                .map(Pet::getVisits)
                .collect(Collectors.toList()));
    }
}
