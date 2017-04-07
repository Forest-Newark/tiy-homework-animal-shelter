package com.forestnewark;

import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.h2.tools.RunScript;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;


import static org.apache.poi.util.StringUtil.UTF8;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;


/**
 * Created by forestnewark on 4/4/17.
 */
public class AnimalServiceTest  {

    private AnimalRepository repository;
    private AnimalService animalService;
    Animal animal;


    //Set up respository before each test and pass in the appropriate JDBC URL
    @Before
    public void setup() throws SQLException {
        repository =  new AnimalRepository("jdbc:h2:~/animal;USER=sa;");
        animalService = new AnimalService(repository);
        animal = null;

    }


    @Test
    /*
    if null value is provided no animal is created
     */
    public void ifNullNoAnimalIsCreated() throws SQLException {

        int beforeAdd = repository.getAnimalCount();
        animalService.createAnimal(animal);
        int afterAdd = repository.getAnimalCount();

        assertThat(beforeAdd,equalTo(afterAdd));
    }






    @Test
    /*
    if null value is provided no animal is deleted
     */
    public void ifNullNoAnimalIsDeleted() throws SQLException {

        int beforeDelete = repository.getAnimalCount();
        animalService.createAnimal(animal);
        int afterDelete = repository.getAnimalCount();

        assertThat(beforeDelete,equalTo(afterDelete));
    }

    @Test
    /*
    editAnimals changes values of correct animal
     */
    public void serviceEditAnimalChangesAnimalInformation() throws SQLException {
        Animal animal = new Animal(1,"Frank","Bird","","");
        animalService.editAnimal(animal);

        ArrayList<Animal> serviceArray;

        serviceArray = animalService.listAnimal();

        assertThat(serviceArray.get(1).getName(),equalTo("Frank"));
    }

    @Test
    /*
    deleteAnimal removes animal from database
     */
    public void serviceDeleteAnimalFromDatabase() throws SQLException {
        Animal animal = new Animal(1,"Melody","","","");
        animalService.deleteAnimal(animal);

        ArrayList<Animal> serviceArray;
        serviceArray = animalService.listAnimal();
        assertThat(serviceArray.get(0).getName(),not(equalTo("Melody")));


    }




}
