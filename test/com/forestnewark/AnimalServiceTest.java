package com.forestnewark;

import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;


/**
 * Created by forestnewark on 4/4/17.
 */
public class AnimalServiceTest  {
    private AnimalRepository animalRepository;
    AnimalService animalService = new AnimalService(animalRepository);
    Animal animal;

    @Before
    public void setup() throws SQLException {
        animalRepository =  new AnimalRepository("jdbc:h2:~/animal;USER=sa;");
        animal = null;
    }


    @Test
    /*
    if null value is provided no animal is created
     */
    public void ifNullNoAnimalIsCreated() throws SQLException {

        int beforeAdd = animalRepository.getAnimalCount();
        animalService.createAnimal(animal);
        int afterAdd = animalRepository.getAnimalCount();

        assertThat(beforeAdd,equalTo(afterAdd));
    }

    @Test
    /*
    if null value is provided no animal is deleted
     */
    public void ifNullNoAnimalIsDeleted() throws SQLException {

        int beforeDelete = animalRepository.getAnimalCount();
        animalService.createAnimal(animal);
        int afterDelete = animalRepository.getAnimalCount();

        assertThat(beforeDelete,equalTo(afterDelete));
    }


}
