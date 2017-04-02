package com.forestnewark;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Forest Newark on 3/28/17 using TDD. This project models an animal shelter by allowing users to create, read, update, and delete
 * records of animals within the animal shelter
 */
public class Main {

    public static void main(String[] args) throws IOException {
        MenuService menuService = new MenuService();
        boolean admin = menuService.welcomePrompt();
        ArrayList<Animal> animalArrayList = new ArrayList<>();

        animalArrayList.add(new Animal("Melody","Dog","Border Collie","Jumps a lot", 1));
        animalArrayList.add(new Animal("Amber","Dog","Golden Retriever","Super Sweet!", 2));
        animalArrayList.add(new Animal("Melody","Fish","Gold Fish","Swims a lot", 3));
        animalArrayList.add(new Animal("Sasha","Cat","Tabby","Sleeps a lot", 4));
        animalArrayList.add(new Animal("Frank","Turtle","","", 5));
        animalArrayList.add(new Animal("Lily","Horse","","", 6));


        while(true){

            int selection = menuService.mainMenuPrompt(admin);

            if(selection == 1) {
                menuService.listAnimal(animalArrayList);
            }
            if (selection == 2) {
                menuService.animalDetail(animalArrayList);
            }
            if (selection == 3){
                animalArrayList.add(menuService.createAnimal(animalArrayList));
            }

            if (selection ==4) {
                menuService.editAnimal(animalArrayList);
            }

            if (selection == 5) {

                menuService.deleteAnimal(animalArrayList);
            }

            if (selection == 6) {
                menuService.quit();

            }

        }

    }

}
