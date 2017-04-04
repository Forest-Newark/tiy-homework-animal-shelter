package com.forestnewark;

import java.sql.*;
import java.util.ArrayList;


/**
 * Created by forestnewark on 4/3/17. This class interfaces between the Animal Repository
 * and the rest of the application
 */
public class AnimalService {

    private AnimalRepository animalRepository;


    /**
     * AnimalService constructor
     * @param animalRepositoryRepository
     */
    public AnimalService(AnimalRepository animalRepositoryRepository){
        this.animalRepository = animalRepositoryRepository;
    }

    //Provides a list of all animals currently in the database
    public ArrayList<Animal> listAnimal() throws SQLException {
      return this.animalRepository.listAnimal();

    }


    //Creates a new animal to be added to the database
    public void createAnimal(Animal animal) throws SQLException {
        if( animal != null){
            this.animalRepository.createAnimal(animal);
        }

    }

    //Edits an animal that is currently in the database
    public void editAnimal(Animal animal) throws SQLException{
        if (animal != null) {
            this.animalRepository.editAnimal(animal);
        }

    }

    //Delete an animal from the database
    public void deleteAnimal(Animal animal) throws SQLException{
        if (animal != null) {
            this.animalRepository.deleteAnimal(animal);
        }
    }

}
