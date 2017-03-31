package com.forestnewark;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Forest Newark on 3/28/17 using TDD. This project models an animal shelter by allowing users to create, read, update, and delete
 * records of animals within the animal shelter
 */
public class Main {

    public static void main(String[] args) throws IOException {
        MenuService menuService = new MenuService();
        boolean admin = menuService.welcomePrompt();
        ArrayList<Animal> animalArrayList = new ArrayList<>();

        while(true){

            int selection = menuService.mainMenuPrompt(admin);

            if(selection == 1) {
                menuService.listAnimal(animalArrayList);
            }
            if (selection == 2) {
                menuService.animalDetail();
            }
            if (selection == 3){
                animalArrayList.add(menuService.createAnimal());

            }

            if (selection ==4) {
                menuService.editAnimal();

            }

            if (selection == 5) {

                animalArrayList.remove(menuService.deleteAnimal());

            }

            if (selection == 6) {
                menuService.quit();

            }

        }

    }

}
