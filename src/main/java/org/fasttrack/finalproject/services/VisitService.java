package org.fasttrack.finalproject.services;

import org.fasttrack.finalproject.domain.Visit;
import org.fasttrack.finalproject.repositories.VisitRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VisitService {
    VisitRepository visitRepository;

    public VisitService(VisitRepository visitRepository) {
        this.visitRepository = visitRepository;
    }

    public List<Visit> getAll() {
        List<Visit> result = new ArrayList<>();
        visitRepository.findAll().forEach(result::add);
        return result;
    }

    public Visit getById(int id) {
        return visitRepository.findById(id).orElse(null);
    }

    public Visit add(Visit visit) {
        visit.setId(0);
        return visitRepository.save(visit);
    }

    public void deleteById(int id) {
        visitRepository.deleteById(id);
    }

    public Visit editVisit(int id, Visit visit) {
        Visit newVisit = visitRepository.findById(id).orElse(null);
        if (newVisit != null) {
            newVisit.setDateTime(visit.getDateTime());
            visitRepository.save(newVisit);
        }
        return newVisit;
    }

    public List<Visit> getVisits(Integer petId) {
        return visitRepository.findByPet_Id(petId);
    }
}
