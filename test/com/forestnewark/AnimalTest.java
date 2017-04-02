package com.forestnewark;

import org.junit.Test;

import java.time.LocalDate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;

/**
 * Created by forestnewark on 3/28/17.
 */
public class AnimalTest {

    @Test
    /**
     * animal constructor creates a non-null object
     */
    public void animalConstructorCreatesNonNullObject() {
        Animal animal = new Animal();
        assertThat(animal,notNullValue());
    }

    @Test
    /**
     *  animal 'name' getter and setter assign and return correct value
     */
    public void nameGetterAndSetterAssignAndReturnCorrectValue() {
        Animal animal = new Animal();
        animal.setName("Melody");
        assertThat(animal.getName(),equalTo("Melody"));
    }

    @Test
    /**
     * animal 'species' getter and setter assign and return correct value
     */
    public void speciesGetterAndSetterAssignAndReturnCorrectValue() {
        Animal animal = new Animal();
        animal.setSpecies("Dog");
        assertThat(animal.getSpecies(),equalTo("Dog"));
    }

    @Test
    /**
     * animal 'breed' getter and setter assign and return correct value
     */
    public void breedGetterAndSetterAssignAndReturnCorrectValue() {
        Animal animal = new Animal();
        animal.setBreed("Border Collie");
        assertThat(animal.getBreed(),equalTo("Border Collie"));
    }

    @Test
    /**
     * animal 'description' getter and setter assign and return correct value
     */
    public void descriptionGetterAndSetterAssignAndReturnCorrectValue() {
        Animal animal = new Animal();
        animal.setDescription("Black and white dog that jumps a lot!");
        assertThat(animal.getDescription(),equalTo("Black and white dog that jumps a lot!"));
    }

    @Test
    /**
     * animal 'dateAdded' getter and setter assign and return correct value
     */
    public void dateAddedGetterAndSetterAssignAndReturnCorrectValues() {
        Animal animal = new Animal();
        animal.setDateAdded(LocalDate.now());
        assertThat(animal.getDateAdded(),equalTo(LocalDate.now()));
    }

    @Test
    /**
     * animal constructor sets correct name value
     */
    public void animalConstructorSetsCorrectName() {
        Animal animal = new Animal("Melody","Dog","Border Collie","Jumps a lot", 1);
        assertThat(animal.getName(),equalTo("Melody"));
    }

    @Test
    /**
     * animal constructor sets correct species value
     */
    public void animalConstructorSetsCorrectSpecies(){
        Animal animal = new Animal("Melody","Dog","Border Collie","Jumps a lot", 1);
        assertThat(animal.getSpecies(),equalTo("Dog"));
    }

    @Test
    /**
     * animal constructor sets correct breed value
     */
    public void animalConstructorSetsCorrectBreed() {
        Animal animal = new Animal("Melody","Dog","Border Collie","Jumps a lot", 1);
        assertThat(animal.getBreed(),equalTo("Border Collie"));
    }

    @Test
    /**
     * animal constructor sets correct description value
     */
    public void animalConstructorSetsCorrectDescription() {
        Animal animal = new Animal("Melody","Dog","Border Collie","Jumps a lot", 1);
        assertThat(animal.getDescription(),equalTo("Jumps a lot"));
    }

    @Test
    /**
     * animal constructor sets correct dateAdded value
     */
    public void animalConstructorSetsCorrectDateAdded() {
        Animal animal = new Animal("Melody","Dog","Border Collie","Jumps a lot", 1);
        assertThat(animal.getDateAdded(),equalTo(LocalDate.now()));
    }

}
