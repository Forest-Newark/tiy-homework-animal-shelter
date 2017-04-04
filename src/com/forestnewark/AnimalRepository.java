package com.forestnewark;

import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

/**
 * Created by forestnewark on 4/3/17.
 */
public class AnimalRepository {


    private Connection conn;

    /**
     * AnimalRespository constructor
     * @param jdbcUrl to be used to connect to the database
     * @throws SQLException
     */
    public AnimalRepository(String jdbcUrl) throws SQLException {
        this.conn = DriverManager.getConnection(jdbcUrl);
    }

    /**
     * List all animals in the database
     * @return ArrayList of Animals
     * @throws SQLException
     */
    public ArrayList<Animal> listAnimal() throws SQLException {

        ArrayList<Animal> animalResultArray = new ArrayList<>();

        Statement stmt = conn.createStatement();

        ResultSet result = stmt.executeQuery("SELECT * FROM ANIMAL ORDER BY animalid ASC ");
        while(result.next()){
            Animal animal = new Animal(
                    result.getInt("animalid"),
                    result.getString("name"),
                    result.getString("species"),
                    result.getString("breed"),
                    result.getString("description")
            );
            animalResultArray.add(animal);
        }
        return animalResultArray;
    }


    /**
     * Finds details on a specific animal
     * @param uniqueId to identify specific animal
     * @return Result
     * @throws SQLException
     */
    public ResultSet animalDetail(String uniqueId) throws SQLException {
        // create a prepared statement
        PreparedStatement preparedStatement = conn.prepareStatement("SELECT * " +
                "FROM animal " +
                "WHERE animalid = ?");

        // set parameter values
        preparedStatement.setString(1, uniqueId);

        // execute the query
        return preparedStatement.executeQuery();
    }


    /**
     * Creates a new animal in the database
     * @param animal to be created in the database
     * @throws SQLException
     */
    public void createAnimal(Animal animal) throws SQLException {
        // create a prepared statement
        PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO animal(name, species, breed, description) " +
                "VALUES(?, ?, ? ,?) ");

        // set parameter values
        preparedStatement.setString(1, animal.getName());
        preparedStatement.setString(2, animal.getSpecies());
        preparedStatement.setString(3, animal.getBreed());
        preparedStatement.setString(4, animal.getDescription());

        // execute the query
        preparedStatement.execute();
    }

    /**
     * Edit animal that currently exist in the database
     * @param animal to be edited
     * @throws SQLException
     */
    public void editAnimal(Animal animal) throws SQLException {
        PreparedStatement preparedStatement = conn.prepareStatement("UPDATE animal SET " +
                "name = ?,species = ?, breed = ?, description = ? " +
                "WHERE animalid = ?");

        // set parameter values
        preparedStatement.setString(1, animal.getName());
        preparedStatement.setString(2, animal.getSpecies());
        preparedStatement.setString(3, animal.getBreed());
        preparedStatement.setString(4, animal.getDescription());
        preparedStatement.setInt(5,animal.getUniqueId());

        // execute the query
        preparedStatement.execute();

    }


    /**
     * Delets animal that currently exist in the database
     * @param animal to be deleted
     * @throws SQLException
     */
    public void deleteAnimal(Animal animal) throws SQLException {
        // create a prepared statement
        PreparedStatement preparedStatement = conn.prepareStatement("DELETE " +
                "FROM animal " +
                "WHERE animalid = ?");


        // set parameter values
        preparedStatement.setInt(1,animal.getUniqueId());

        // execute the query
        preparedStatement.execute();
    }


    /**
     * Counts the number of animals currently in the database
     * @return the current number of animals in the database
     * @throws SQLException
     */
    public int getAnimalCount() throws SQLException {
        Statement stmt = conn.createStatement();

        ResultSet result =  stmt.executeQuery("SELECT COUNT(*) AS total FROM ANIMAL ");

        int total = 0;

        while(result.next()) {
            total = result.getInt("total");

        }

        return total;
    }
}
