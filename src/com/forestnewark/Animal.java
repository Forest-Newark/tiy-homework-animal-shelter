package com.forestnewark;

import java.time.LocalDate;

/**
 * Created by Forest Newark on 3/28/17 using TDD. The Animal class model animals that are in the shelter.
 *
 */
public class Animal {

    private String name;
    private String species;
    private String breed;
    private String description;
    private LocalDate dateAdded;
    private int uniqueId;

    /**
     * @param name sets the name of the animal
     * @param species sets the species for the animal
     * @param breed sets the breed for the animal
     * @param description sets the description for the animal
     * @param uniqueId is a unique identification number for the animal
     */
    public Animal(int uniqueId, String name, String species, String breed, String description) {

        this.uniqueId = uniqueId;
        this.name = name;
        this.species = species;
        this.breed = breed;
        this.description = description;
        this.dateAdded = LocalDate.now();

    }

    /**
     * Empty constructor for the animal class
     */
    public Animal() {}

    /**
     * @param name of the animal to to be set
     */
    public void setName(String name) {
        this.name = name;
        
    }

    /**
     * @return name of the animal
     */
    public String getName() {
        return name;
    }

    /**
     * @param species of the animal to be set
     */
    public void setSpecies(String species) {
        this.species = species;
    }

    /**
     * @return species of the animal
     */
    public String getSpecies() {
        return species;
    }

    /**
     * @param breed of the animal to be set
     */
    public void setBreed(String breed) {
        this.breed = breed;
    }

    /**
     * @return breed of the animal
     */
    public String getBreed() {
        return breed;
    }

    /**
     * @param description of the animal to be set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return description of the animal
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param dateAdded of the animal to be set
     */
    public void setDateAdded(LocalDate dateAdded) {
        this.dateAdded = dateAdded;
    }

    /**
     * @return dateAdded of the animal
     */
    public LocalDate getDateAdded() {
        return dateAdded;
    }

    public int getUniqueId() {
        return uniqueId;
    }
}