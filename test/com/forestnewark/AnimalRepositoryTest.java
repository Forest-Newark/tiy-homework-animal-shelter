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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.apache.poi.util.StringUtil.UTF8;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;


/**
 * Created by forestnewark on 4/4/17.
 */
public class AnimalRepositoryTest {


    private static final String JDBC_DRIVER = org.h2.Driver.class.getName();
    private static final String JDBC_URL = "jdbc:h2:~/animal;USER=sa;";
    private static final String USER = "sa";
    private static final String PASSWORD = "";


    private AnimalRepository repository;



    //Establish table schema using sql file
    @BeforeClass
    public static void createSchema() throws Exception {
        RunScript.execute(JDBC_URL,
                USER, PASSWORD, "schema.sql", UTF8, false);
    }

    //Create new data set from xml file
    private IDataSet readDataSet() throws Exception {
        return new FlatXmlDataSetBuilder().build(new File("dataset.xml"));
    }

    //Import data set and cleanly insert into database before each test
    @Before
    public void importDataSet() throws Exception {
        IDataSet dataSet = readDataSet();
        cleanlyInsertDataset(dataSet);
    }

    //Insert data into database using connection parameters
    private void cleanlyInsertDataset(IDataSet dataSet) throws Exception {
        IDatabaseTester databaseTester = new JdbcDatabaseTester(
                JDBC_DRIVER, JDBC_URL, USER, PASSWORD);
        databaseTester.setSetUpOperation(DatabaseOperation.CLEAN_INSERT);
        databaseTester.setDataSet(dataSet);
        databaseTester.onSetup();

    }

    //Set up respository befor each test and pass in the appropriate JDBC URL
    @Before
    public void setup() throws SQLException {
        repository = new AnimalRepository(JDBC_URL);
    }

    @Test
    /*
    list animal returns arraylist of all animals in database
     */
    public void listAnimalReturnsArrayListWithAllAnimals() throws SQLException {
        ArrayList<Animal> resultArray = new ArrayList<>();
        resultArray = repository.listAnimal();
        assertThat(resultArray.get(0).getName(),equalTo("Melody"));
        assertThat(resultArray.get(1).getName(),equalTo("Johnny"));
    }

    @Test
    /*
    animalDetails returns a result set with only the specified animal in it
     */
    public void animalDetailReturnsOnlyCorrectAnimal() throws SQLException{
        ArrayList<Animal> resultArray = new ArrayList<>();

        ResultSet result = repository.animalDetail("4");
        while(result.next()){
            Animal animal = new Animal(
                    result.getInt("animalid"),
                    result.getString("name"),
                    result.getString("species"),
                    result.getString("breed"),
                    result.getString("description")
            );
            resultArray.add(animal);
        }

        assertThat(resultArray.size(),equalTo(1));
        assertThat(resultArray.get(0).getName(),equalTo("Sasha"));
    }

    @Test
    /*
    editAnimal changes the appropriate information on a specific animal
     */
    public void editAnimalChangesCorrectInformation() throws SQLException {
        Animal animal = new Animal(1,"Change","Change","Change","Change");
        repository.editAnimal(animal);

        ArrayList<Animal> resultArray = new ArrayList<>();

        ResultSet result = repository.animalDetail("1");
        while(result.next()){
            Animal animalChange = new Animal(
                    result.getInt("animalid"),
                    result.getString("name"),
                    result.getString("species"),
                    result.getString("breed"),
                    result.getString("description")
            );
            resultArray.add(animalChange);
        }

        assertThat(resultArray.get(0).getName(),equalTo("Change"));
    }



    /*
    adding an animal should increase the total animal count by 1
     */
    @Test
    public void createAnimalIncreasesTablesize() throws Exception {

        Animal animal = new Animal(4,"Lily","Suger Gider", "", "");
        System.out.println("Count before adding animal is: " + repository.getAnimalCount());

        repository.createAnimal(animal);

        int newCount = repository.getAnimalCount();
        System.out.println("Count after adding animal is: " + newCount);

        assertThat(newCount, equalTo(5));

    }

    /*
    removing an animal should decrease the total animal count by 1
     */
    @Test
    public void deleteAnimalDecreasesTableSize() throws Exception{
        Animal animal = new Animal(1,"Melody","Dog","Setter","Jumps a lot");
        System.out.println("Count before deleting animal is: " + repository.getAnimalCount());
        repository.deleteAnimal(animal);
        int newCount = repository.getAnimalCount();
        System.out.println("Count after deleting animal is: " + newCount);
        assertThat(newCount, equalTo(3));



    }






}
