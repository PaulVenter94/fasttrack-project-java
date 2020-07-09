package org.fasttrack.finalproject.services;

import org.fasttrack.finalproject.domain.Visit;
import org.fasttrack.finalproject.repositories.VisitRepository;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public Visit updateVisit(int id, String string) {
        Map<String, String> bodyUpdate = new HashMap();
        String[] strings = string.split(",");
        bodyUpdate.put(strings[0], strings[1]);
        Visit visit = visitRepository.findById(id).orElseThrow(() -> new RuntimeException("Visit with id: " + id + "does not exist"));
        Class visitClass = visit.getClass();
        bodyUpdate.keySet().forEach(f -> {
                    try {
                        Field field = visitClass.getDeclaredField(f);
                        field.setAccessible(true);
                        field.set(visit, bodyUpdate.get(f));
                    } catch (NoSuchFieldException | IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
        );
        return visitRepository.save(visit);
    }
}
