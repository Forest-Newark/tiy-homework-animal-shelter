package com.forestnewark;

import java.util.ArrayList;

/**
 * Created by forestnewark on 3/23/17.
 */
public class Main {


    public static void main(String[] args) {
        //Declare and initialize animalArrayList -- Really this should not be in the Main class...
        ArrayList<Animal> animalArrayList = new ArrayList<>();

        //Initialize Menuserive and call mainMenuPrompt Method
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
                int index = menuService.deleteAnimalPrompt(animalArrayList);
                if (index != -1) {
                    animalArrayList.remove(index);
                }
            }

            selection = menuService.mainMenuPrompt();
        }

        System.out.println("Goodbye");


    }


}
