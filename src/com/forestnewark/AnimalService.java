package com.forestnewark;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by forestnewark on 4/3/17.
 */
public class AnimalService {

    private AnimalRepository animalRepository;


    public AnimalService(AnimalRepository animalRepositoryRepository){
        this.animalRepository = animalRepositoryRepository;
    }

    //Animal List
    public ArrayList<Animal> listAnimal() throws SQLException {
      return this.animalRepository.listAnimal();

    }


    //Create Animal
    public void createAnimal(Animal animal) throws SQLException {
        if( animal != null){
            this.animalRepository.createAnimal(animal);
        }

    }

    //Edit Animal
    public void editAnimal(Animal animal) throws SQLException{
        if (animal != null) {
            this.animalRepository.editAnimal(animal);
        }

    }

    //Delete Animal
    public void deleteAnimal(Animal animal) throws SQLException{
        if (animal != null) {
            this.animalRepository.deleteAnimal(animal);
        }
    }

}
