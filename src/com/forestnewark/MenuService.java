package com.forestnewark;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by forestnewark on 3/23/17.
 */
public class MenuService {

    public int mainMenuPrompt() {
        System.out.println("\n-- Main Menu --\n" +
                "\n" +
                "1) List animals\n" +
                "2) Create an animal\n" +
                "3) View animal details\n" +
                "4) Edit an animal\n" +
                "5) Delete an animal\n" +
                "6) Quit\n");

        return mainUserSelectionPrompt();
    }

    private int mainUserSelectionPrompt() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please make a selection");
        int selection = 0;
        if (!scanner.hasNextInt()) {
            String badInput = scanner.next();
            System.out.printf("Sorry! \'%s\' is not a valid choice\n", badInput);
            mainUserSelectionPrompt();
        } else {
            selection = scanner.nextInt();
            if (selection > 6 || selection < 1) {
                System.out.printf("Sorry! \'%s\' is not a valid option\n", selection);
                mainUserSelectionPrompt();
            }
        }
        return selection;
    }


    public void listAllAnimals(ArrayList<Animal> animalArray) {

        StringBuilder sb = new StringBuilder();
        sb.append("Animal List");
        sb.append("\n");

        if (animalArray.size() == 0) {
            sb.append("There are no animals in the shelter right now. Come back soon!");

        } else {
            int index = 1;
            for (Animal animal : animalArray) {
                sb.append("# ");
                sb.append(index);
                sb.append("  ");
                sb.append("Name: ");
                sb.append(String.format("%-10s", animal.getName()));
                sb.append("  ");
                sb.append("Species: ");
                sb.append(animal.getSpecies());
                sb.append("\n");
                index++;
            }
        }
        System.out.println(sb.toString());
    }


    public Animal createAnimalPrompt() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("**Create an Animal**");
        System.out.print("Enter animal name: ");
        String name = scanner.nextLine();
        System.out.print("Enter animal species: ");
        String species = scanner.nextLine();
        System.out.print("Enter animal breed(optional): ");
        String breed = scanner.nextLine();
        System.out.print("Enter animal description(optional): ");
        String description = scanner.nextLine();
        System.out.printf("\nYou have successfully created the following animal:\n" +
                "Name: %s\nSpecies: %s\nBreed: %s\nDescription: %s", name, species, breed, description);
        return new Animal(name, species, breed, description);


    }

    public void animalDetailPrompt(ArrayList<Animal> animalList) {
        System.out.println("Animal Detail ");
        System.out.println("detail animal prompt");
        int index = animalSearch(animalList);
        Animal animal = animalList.get(index);
        System.out.printf("\nAnimal Details:\n" +
                "Name: %s\nSpecies: %s\nBreed: %s\nDescription: %s", animal.getName(), animal.getSpecies(), animal.getBreed(), animal.getDescription());
    }

    public void editAnimalPrompt(ArrayList<Animal> animalList) {
        System.out.println("edit animal prompt");
        Scanner scanner = new Scanner(System.in);
        int index = animalSearch(animalList);
        Animal animal = animalList.get(index);
        System.out.printf("\nCurrent Animal Information:\n" +
                "Name: %s\nSpecies: %s\nBreed: %s\nDescription: %s", animal.getName(), animal.getSpecies(), animal.getBreed(), animal.getDescription());

        System.out.println("Change information or hit return to keep information");
        System.out.print("Name[" + animal.getName() +"] : ");
        String name = scanner.nextLine();
        if(!name.equals("")) {
            animal.setName(name);
        }
        System.out.print("Species[" + animal.getSpecies() +"] : ");
        String species = scanner.nextLine();
        if(!species.equals("")) {
            animal.setSpecies(species);
        }

        System.out.print("Breed[" + animal.getBreed() +"] : ");
        String breed = scanner.nextLine();
        if(!breed.equals("")) {
            animal.setBreed(breed);
        }

        System.out.print("Description[" + animal.getDescription() +"] : ");
        String description = scanner.nextLine();
        if(!description.equals("")) {
            animal.setDescription(description);
        }
        System.out.printf("\nUpdated Animal Information:\n" +
                "Name: %s\nSpecies: %s\nBreed: %s\nDescription: %s", animal.getName(), animal.getSpecies(), animal.getBreed(), animal.getDescription());



    }

    public int deleteAnimalPrompt(ArrayList<Animal> animalList) {
        System.out.println("delete animal prompt");
        int index = animalSearch(animalList);
        System.out.printf("Successfully Deleted %s", animalList.get(index).getName());
        return index;



    }

    public int animalSearch(ArrayList<Animal> animalList) {
        System.out.println("Enter an animal numeric ID or name to search:");
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNextInt()) {
            int id = scanner.nextInt();
            if (id < 1 || id > animalList.size()) {
                System.out.println("This is not a valid ID. Please Try again");
                animalSearch(animalList);

            } else {
                return id - 1;
            }
        } else {
            String name = scanner.nextLine().toLowerCase();
            int index = 0;
            boolean animalFound = false;
            for (Animal animal : animalList) {
                if (animal.getName().toLowerCase().contains(name)) {
                    animalFound = true;
                    break;
                } else {
                    index++;
                }
            }
            if (animalFound) {
                return index;

            } else {
                System.out.println("This is not a valid Name. Please Try again");
                animalSearch(animalList);
            }

        }
        return 0;
    }
}
