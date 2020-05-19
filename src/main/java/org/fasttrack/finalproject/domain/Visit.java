package org.fasttrack.finalproject.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Visit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private LocalDateTime dateTime;
    //@JsonIgnore
    @ManyToOne
    private Pet pet;

    public Visit() {
    }

    public Visit(LocalDateTime dateTime, Pet pet) {
        this.dateTime = dateTime;
        this.pet = pet;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @JsonBackReference
    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    @Override
    public String toString() {
        return "Visit{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", pet=" + pet.getName() +
                '}';
    }
}
