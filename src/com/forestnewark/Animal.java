package com.forestnewark;

import java.time.LocalDate;

/**
 * Created by Forest Newark on 3/28/17 using TDD. The Aniaml class modesl that animals that are in the shelter.
 *
 */
public class Animal {

    private String name;
    private String species;
    private String breed;
    private String description;
    private LocalDate dateAdded;

    public Animal(String name, String species, String breed, String description) {

        this.name = name;
        this.species = species;
        this.breed = breed;
        this.description = description;
        this.dateAdded = LocalDate.now();
    }

    public Animal() {}

    public void setName(String name) {
        this.name = name;
        
    }

    public String getName() {
        return name;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getSpecies() {
        return species;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getBreed() {
        return breed;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDateAdded(LocalDate dateAdded) {
        this.dateAdded = dateAdded;
    }

    public LocalDate getDateAdded() {
        return dateAdded;
    }
}