package com.forestnewark;

import java.time.LocalDate;

/**
 * Created by Forest Newark on 3/23/17. The Aniaml class modesl that animals that are in the shelter.
 */
public class Animal {

    //Private properties to encapsulate Animal data
    private String name;
    private String species;
    private String breed;
    private String description;
    private LocalDate dateAdded;


    /**
     * Public constructor for the Animal. Automatically sets the date the animal was added to the shelter
     * when an Animal is created
     * @param name is the name of the animal being entered into the animal shelter
     * @param species is the species of the animal being entered into the animal shelter
     * @param breed is the breed of the animal being entered into the animal shelter
     * @param description  is the description of the animal being entered into the animal shelter
     */
    public Animal(String name, String species, String breed, String description) {
        this.name = name;
        this.species = species;
        this.breed = breed;
        this.description = description;
        this.dateAdded = LocalDate.now();
    }

    /**
     *
     * @return the name of the animal
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name to set for the animal
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return the species of the animal
     */

    public String getSpecies() {
        return species;
    }

    /**
     *
     * @param species to set for the animal
     */
    public void setSpecies(String species) {
        this.species = species;
    }

    /**
     *
     * @return the breed of the animal
     */

    public String getBreed() {
        return breed;
    }

    /**
     *
     * @param breed to set for the animal
     */
    public void setBreed(String breed) {
        this.breed = breed;
    }

    /**
     *
     * @return the description of the animal
     */

    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description to set for the animal
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @return the date the animal was added to the animal shelter
     */

    public LocalDate getDateAdded() {
        return dateAdded;
    }
}


