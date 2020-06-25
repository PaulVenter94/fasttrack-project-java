package org.fasttrack.finalproject.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Entity
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "pet_id")
    private Integer id;
    private String birthDate;
    @JsonIgnore
    private LocalDate newBirthDate;
    @JsonIgnore
    private Period period;
    private String age;
    private String name;
    @ManyToOne
    @JoinColumn(name = "OWNER_ID", referencedColumnName = "owner_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Owner owner;

    public Pet() {
    }

    public Pet(String name) {
        this.name = name;
    }

    public Pet(String firstName, LocalDate newBirthDate, Owner owner) {
        this.name = firstName;
        this.newBirthDate = newBirthDate;
        this.birthDate = String.valueOf(newBirthDate);
        this.period = Period.between(newBirthDate, LocalDate.now());
        this.age = period.getYears() + " years " + period.getMonths() + " months";
        this.owner = owner;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    /*@JsonManagedReference
    public List<Visit> getVisits() {
        return visits;
    }*/

 /*   public void setVisits(List<Visit> visits) {
        this.visits = visits;
    }*/

    public String getBirthDate() {
        return birthDate;
    }

 /*   public void addVisit(Visit visit) {
        visits.add(visit);
        visit.setPet(this);
    }*/

    public LocalDate getNewBirthDate() {
        return newBirthDate;
    }

    public void setNewBirthDate(LocalDate birthDate) {
        this.newBirthDate = birthDate;
        this.period = Period.between(birthDate, LocalDate.now());
        this.age = period.getYears() + " years " + period.getMonths() + " months";
    }

    public String getAge() {
        return age;
    }


    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pet pet = (Pet) o;

        if (id != null ? !id.equals(pet.id) : pet.id != null) return false;
        if (birthDate != null ? !birthDate.equals(pet.birthDate) : pet.birthDate != null) return false;
        if (newBirthDate != null ? !newBirthDate.equals(pet.newBirthDate) : pet.newBirthDate != null) return false;
        if (period != null ? !period.equals(pet.period) : pet.period != null) return false;
        if (age != null ? !age.equals(pet.age) : pet.age != null) return false;
        if (name != null ? !name.equals(pet.name) : pet.name != null) return false;
        return owner != null ? owner.equals(pet.owner) : pet.owner == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (birthDate != null ? birthDate.hashCode() : 0);
        result = 31 * result + (newBirthDate != null ? newBirthDate.hashCode() : 0);
        result = 31 * result + (period != null ? period.hashCode() : 0);
        result = 31 * result + (age != null ? age.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (owner != null ? owner.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "id=" + id +
                ", birthDate='" + birthDate + '\'' +
                ", newBirthDate=" + newBirthDate +
                ", period=" + period +
                ", age='" + age + '\'' +
                ", name='" + name + '\'' +
                ", owner=" + owner +
                '}';
    }
}
