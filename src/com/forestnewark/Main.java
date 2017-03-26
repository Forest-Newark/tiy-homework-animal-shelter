package com.forestnewark;

import java.util.ArrayList;

/**
 * Created by Forest Newark on 3/23/17. This project models an animal shelter by allowing users to create, read, update, and delete
 * records of animals within the animal shelter
 */
public class Main {


    public static void main(String[] args) {
        //Declare and initialize animalArrayList
        ArrayList<Animal> animalArrayList = new ArrayList<>();


        //Initialize MenuService and call mainMenuPrompt Method
        MenuService menuService = new MenuService();

        int selection = menuService.mainMenuPrompt();

        while (selection != 6) {

            if (selection == 1) {
                menuService.listAllAnimals(animalArrayList);

            } else if (selection == 2) {
                animalArrayList.add(menuService.createAnimalPrompt());

            } else if (selection == 3) {
                menuService.animalDetailPrompt(animalArrayList);

            } else if (selection == 4) {
                menuService.editAnimalPrompt(animalArrayList);

            } else if (selection == 5) {

                menuService.deleteAnimalPrompt(animalArrayList);

            }

            selection = menuService.mainMenuPrompt();
        }

        System.out.println("Goodbye");

    }

}
