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

import java.time.LocalDate;
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
        ownerService.getAll().forEach(System.out::println);
        System.out.println("Owners loaded");

        petService.add(new Pet("Archibald", LocalDate.of(2008, 8, 20), ownerService.getById(1)));
        petService.add(new Pet("Glenn", LocalDate.of(2016, 5, 12), ownerService.getById(2)));
        petService.add(new Pet("Castron", LocalDate.of(2019, 8, 4), ownerService.getById(3)));
        petService.add(new Pet("Pennywise", LocalDate.of(2018, 8, 20), ownerService.getById(4)));
        petService.add(new Pet("Jason", LocalDate.of(2015, 6, 13), ownerService.getById(5)));
        petService.add(new Pet("Fredi Kruger", LocalDate.of(2015, 6, 13), ownerService.getById(5)));
        petService.getById(7).setOwner(ownerService.getById(1));
        petService.getAll().forEach(System.out::println);
        System.out.println("Pets loaded");


        vetService.add(new Vet("Gregory", "Pol", 74, "Radiology"));
        vetService.add(new Vet("Laris", "Jurcut", 21, null));
        vetService.add(new Vet("Gregor", "Clegane", 45, "Dermatology"));
        vetService.add(new Vet("Maluma", "Baby", 26, "Behavior"));
        vetService.add(new Vet("John", "Cena", 34, "Dermatology"));
        System.out.println("Vets loaded");

        petService.getAll().forEach(pet -> petService.addVisit(pet.getId(), new Visit(LocalDateTime.of(2020, 05, 12, 13, 0), pet)));
        System.out.println(petService.getAll());
        System.out.println(visitService.getAll());
        System.out.println("Visits Loaded");

    }
}
