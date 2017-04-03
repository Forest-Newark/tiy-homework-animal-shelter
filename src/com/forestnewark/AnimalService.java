package com.forestnewark;

import java.sql.*;

/**
 * Created by forestnewark on 4/3/17.
 */
public class AnimalService {

    private AnimalRepository animalRepository;

    public AnimalService(AnimalRepository peopleRepository){
        this.animalRepository = peopleRepository;
    }

    public void listAnimal() throws SQLException {
        ResultSet resultSet = this.animalRepository.listAnimal();
        while(resultSet.next()){
            System.out.printf("%s %s\n",
                    resultSet.getString("name"),
                    resultSet.getString("species"));
        }
    }
}
