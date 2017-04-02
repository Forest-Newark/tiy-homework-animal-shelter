package com.forestnewark;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Forest Newark on 3/28/17 using TDD. This project models an animal shelter by allowing users to create, read, update, and delete
 * records of animals within the animal shelter
 */
public class Main {

    public static void main(String[] args) throws IOException {

        //Instantiate MenuService object as menuService
        MenuService menuService = new MenuService();

        //Prompt user for login information and set login state as "admin"
        boolean admin = menuService.welcomePrompt();

        //Instantiate new ArrayList to contain animals
        ArrayList<Animal> animalArrayList = new ArrayList<>();

        //Add animals into the animalArrayList
        animalArrayList.add(new Animal("Melody","Dog","Border Collie","Jumps a lot", 1));
        animalArrayList.add(new Animal("Amber","Dog","Golden Retriever","Super Sweet!", 2));
        animalArrayList.add(new Animal("Melody","Fish","Gold Fish","Swims a lot", 3));
        animalArrayList.add(new Animal("Sasha","Cat","Tabby","Sleeps a lot", 4));
        animalArrayList.add(new Animal("Frank","Turtle","","", 5));
        animalArrayList.add(new Animal("Lily","Horse","","", 6));


        //While loop runs the program
        while(true){

            //Prompt for user selection gets validated user input
            int selection = menuService.mainMenuPrompt(admin);

            //Selection 1 list all of the animals currently in the animalArrayList
            if(selection == 1) {
                menuService.listAnimal(animalArrayList);
            }

            //Selection 2 allows users to to display detailed information for a specific animal
            if (selection == 2) {
                menuService.animalDetail(animalArrayList);
            }

            //Selection 3 allows users to create a new animal
            if (selection == 3){
                menuService.createAnimal(animalArrayList);
            }

            //Selection 4 allows users to edit information about an animal
            if (selection ==4) {
                menuService.editAnimal(animalArrayList);
            }

            //Selection 5 allows users to delete an animal
            if (selection == 5) {

                menuService.deleteAnimal(animalArrayList);
            }

            //Selection 6 allows users to exit the program
            if (selection == 6) {
                menuService.quit();

            }

        }

    }

}
