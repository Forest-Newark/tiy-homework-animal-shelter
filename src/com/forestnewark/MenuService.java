package com.forestnewark;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * Created by forestnewark on 3/23/17.
 */
public class MenuService {

    Scanner scanner = new Scanner(System.in);

    /**
     * Prints Main Menu. Calls mainUserSelectionPrompt to get and validate user input
     *
     * @return user selection as int value
     */

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

    /**
     * Private method that is called by the mainMenuPrompt to get user input
     *
     * @return int value of user selection
     */

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

    /**
     * Generates and prints a formatted listed of all animals in the animal shelter
     *
     * @param animalArray ArrayList of animals which contains all animals in the animal shelter
     */

    public void listAllAnimals(ArrayList<Animal> animalArray) {

        StringBuilder sb = new StringBuilder();
        sb.append("-- Animal List -- ");
        sb.append("\n");

        if (animalArray.size() == 0) {
            sb.append("\n");
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

    /**
     * Prompts user for input on creating a new Animal Object
     *
     * @return Animal created based on user input
     */

    public Animal createAnimalPrompt() {

        System.out.println("-- Create an Animal --");
        System.out.println();

        String name = promptToString("Enter animal name(Required): ",true);

        String species = promptToString("Enter animal species(Required): ",true);

        String breed = promptToString("Enter animal breed(Optional): ",false);

        String description = promptToString("Enter animal description(Optional): ",false);

        System.out.printf("\nYou have successfully created the following animal:\n" +
                "Name: %s\nSpecies: %s\nBreed: %s\nDescription: %s\n", name, species, breed, description);
        return new Animal(name, species, breed, description);


    }


    private String promptToString(String prompt, Boolean required) {
        System.out.print(prompt);
        String response = scanner.nextLine();

        if(response.equals("") && required) {
           return promptToString(prompt, required);

        }else {
            return response;
        }


    }

    /**
     * Prints detailed information out for a specific, user selected, animal
     *
     * @param animalList ArrayList of animals which contains all animals in the animal shelter
     */
    public void animalDetailPrompt(ArrayList<Animal> animalList) {
        System.out.println("-- Animal Detail -- ");
        int index = animalSearch(animalList);
        if (index != -1) {
            Animal animal = animalList.get(index);
            System.out.printf("\nAnimal Details:\n" +
                    "Name: %s\nSpecies: %s\nBreed: %s\nDescription: %s", animal.getName(), animal.getSpecies(), animal.getBreed(), animal.getDescription());
        }
    }

    /**
     * Prompts user to change information for a specific, user selected, animal
     *
     * @param animalList ArrayList of animals which contains all animals in the animal shelter
     */
    public void editAnimalPrompt(ArrayList<Animal> animalList) {
        System.out.println("-- Edit Animal Information -- ");
        Scanner scanner = new Scanner(System.in);
        int index = animalSearch(animalList);
        if (index != -1) {
            Animal animal = animalList.get(index);
            System.out.printf("\nCurrent Animal Information:\n" +
                    "Name: %s\nSpecies: %s\nBreed: %s\nDescription: %s\n\n", animal.getName(), animal.getSpecies(), animal.getBreed(), animal.getDescription());

            System.out.println("Change information or hit return to keep information");
            System.out.print("Name[" + animal.getName() + "] : ");
            String name = scanner.nextLine();
            if (!name.equals("")) {
                animal.setName(name);
            }
            System.out.print("Species[" + animal.getSpecies() + "] : ");
            String species = scanner.nextLine();
            if (!species.equals("")) {
                animal.setSpecies(species);
            }

            System.out.print("Breed[" + animal.getBreed() + "] : ");
            String breed = scanner.nextLine();
            if (!breed.equals("")) {
                animal.setBreed(breed);
            }

            System.out.print("Description[" + animal.getDescription() + "] : ");
            String description = scanner.nextLine();
            if (!description.equals("")) {
                animal.setDescription(description);
            }
            System.out.printf("\nUpdated Animal Information:\n" +
                    "Name: %s\nSpecies: %s\nBreed: %s\nDescription: %s", animal.getName(), animal.getSpecies(), animal.getBreed(), animal.getDescription());

        }

    }

    /**
     * Prompts user to select an Animal to be removed from the animal shelter
     *
     * @param animalList ArrayList of animals which contains all animals in the animal shelter
     * @return returns the int index number of the animal to be deleted
     */
    public int deleteAnimalPrompt(ArrayList<Animal> animalList) {
        System.out.println("-- Delete Animal Record --");
        int index = animalSearch(animalList);
        if (index != -1) {
            System.out.printf("Successfully Deleted : %s", animalList.get(index).getName());
            return index;
        } else {
            return -1;
        }

    }



    /**
     * Searches for an animal based on user supplied ID number or Name. Provides input validation
     *
     * @param animalList ArrayList of animals which contains all animals in the animal shelter
     * @return int index number for the resulting animal
     */

    private int animalSearch(ArrayList<Animal> animalList) {
        System.out.println("Enter an animal numeric ID or name to search (q to quit):");
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
            if (name.equals("q")) {
                return -1;
            } else {
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

        }
        return -1;
    }
}
