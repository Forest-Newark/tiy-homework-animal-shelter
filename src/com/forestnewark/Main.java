package com.forestnewark;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Forest Newark on 3/28/17 using TDD. This project models an animal shelter by allowing users to create, read, update, and delete
 * records of animals within the animal shelter
 */
public class Main {

    public static void main(String[] args) throws IOException, SQLException {

       //Setup JBDC with PostgreSQL
        String jdbcUrl = "jdbc:postgresql://localhost/animal_shelter";
        AnimalRepository repository = new AnimalRepository(jdbcUrl);
        AnimalService service = new AnimalService(repository);


        //Instantiate MenuService object as menuService
        MenuService menuService = new MenuService();

        //Prompt user for login information and set login state as "admin"
        boolean admin = menuService.welcomePrompt();


        //While loop runs the program
        while(true){

            //Prompt for user selection gets validated user input
            int selection = menuService.mainMenuPrompt(admin);

            //Selection 1 list all of the animals currently in the animalArrayList
            if(selection == 1) {
                menuService.listAnimal(service.listAnimal());
            }

            //Selection 2 allows users to to display detailed information for a specific animal
            if (selection == 2) {
                menuService.animalDetail(service.listAnimal());
            }

            //Selection 3 allows users to create a new animal
            if (selection == 3){
                service.createAnimal(menuService.createAnimal(service.listAnimal()));
            }

            //Selection 4 allows users to edit information about an animal
            if (selection ==4) {
                service.editAnimal(menuService.editAnimal(service.listAnimal()));
            }

            //Selection 5 allows users to delete an animal
            if (selection == 5) {

                service.deleteAnimal(menuService.deleteAnimal(service.listAnimal()));
            }

            //Selection 6 allows users to exit the program
            if (selection == 6) {
                menuService.quit();

            }

        }

    }

}
