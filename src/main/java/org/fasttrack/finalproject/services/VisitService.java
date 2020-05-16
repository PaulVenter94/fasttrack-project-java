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
        Visit newVisit = visit;
        newVisit.setId(0);
        return visitRepository.save(newVisit);
    }

    public void deleteById(int id) {
        visitRepository.deleteById(id);
    }
}
