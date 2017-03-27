package com.forestnewark;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;



/**
 * Created by Forest Newark on 3/23/17. MenuService prints menus for the user, validates input, and returns results
 * that can be used within the program.
 */
public class MenuService {
    //Initialize scanner with new line as a delimiter
    private Scanner scanner = new Scanner(System.in).useDelimiter("\n");

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
     * Called by the mainMenuPrompt to get user input.
     * Validates user selection before returning the user selection as an int value.
     *
     * @return int value of user selection
     */

    private int mainUserSelectionPrompt() {
        System.out.println("Please make a selection");

        int selection;

        if (!scanner.hasNextInt()) {
            String badInput = scanner.next();

            System.out.printf("Sorry! \'%s\' is not a valid choice\n", badInput);

            return mainMenuPrompt();

        } else {

            selection = scanner.nextInt();

            if (selection > 6 || selection < 1) {

                System.out.printf("Sorry! \'%s\' is not a valid option\n", selection);

                return mainMenuPrompt();
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
        sb.append("-- Animal List -- \n");


        if (animalArray.size() == 0) {

            sb.append("\nThere are no animals in the shelter right now. Come back soon!");


        } else {
            int index = 1;

            for (Animal animal : animalArray) {
                sb.append("ID #");
                sb.append(index);
                sb.append("  ");
                sb.append("Name: ");
                sb.append(String.format("%-10s", animal.getName()));
                sb.append("  ");
                sb.append("Species: ");
                sb.append(String.format("%-10s", animal.getSpecies()));
                sb.append("  ");
                sb.append("Days in Shelter: ");
                sb.append(ChronoUnit.DAYS.between(animal.getDateAdded(), LocalDate.now()));
                sb.append("\n");
                index++;
            }
        }
        System.out.println(sb.toString());
    }


    /**
     * Prompts user for input on creating a new Animal Object. Uses the promptToString method as a helper
     * to validate user input and control for required and optional input fields.
     *
     * @return Animal created based on user input
     */

    public Animal createAnimalPrompt() {

        System.out.println("-- Create an Animal --\n");


        String name = promptToString("Enter animal name(Required): ", null, true);

        String species = promptToString("Enter animal species(Required): ", null, true);

        String breed = promptToString("Enter animal breed(Optional): ", null, false);

        String description = promptToString("Enter animal description(Optional): ", null, false);

        System.out.printf("\nYou have successfully created the following animal:\n" +
                "Name: %s\nSpecies: %s\nBreed: %s\nDescription: %s\n", name, species, breed, description);

        return new Animal(name, species, breed, description);


    }

    /**
     * Prints a provided prompt and validates user input based on a field being required or optional
     *
     * @param prompt   to be printed out to the user
     * @param required denotes of te field is required input or optional
     * @return String of the user response
     */


    private String promptToString(String prompt, String startValue, Boolean required) {
        System.out.print(prompt);

        String response = scanner.next();
        if (startValue == null) {
            if (response.equals("") && required) {

                return promptToString(prompt, startValue, required);

            } else {

                return response;
            }
        } else {
            if (response.equals("")) {
                return startValue;

            } else {
                return response;
            }

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

            System.out.printf("\nAnimal Details:\n" + "----------" +
                            "Name: %s\nSpecies: %s\nBreed: %s\nDescription: %s\nDate Added: %s\nDays In Shelter: %s\n",
                    animal.getName(), animal.getSpecies(), animal.getBreed(), animal.getDescription(),
                    animal.getDateAdded(), ChronoUnit.DAYS.between(animal.getDateAdded(), LocalDate.now()));
        }
    }

    /**
     * Prompts user to change information for a specific, user selected, animal
     *
     * @param animalList ArrayList of animals which contains all animals in the animal shelter
     */
    public void editAnimalPrompt(ArrayList<Animal> animalList) {
        System.out.println("-- Edit Animal Information -- ");

        int index = animalSearch(animalList);

        if (index != -1) {
            Animal animal = animalList.get(index);

            System.out.printf("\nCurrent Animal Information:\n" +
                    "Name: %s\nSpecies: %s\nBreed: %s\nDescription: %s\n\n", animal.getName(), animal.getSpecies(), animal.getBreed(), animal.getDescription());

            System.out.println("Change information or hit return to keep information");

            animal.setName(promptToString("Name [" + animal.getName() + "] : ", animal.getName(), false));

            animal.setSpecies(promptToString("Species [" + animal.getSpecies() + "] : ", animal.getSpecies(), false));

            animal.setBreed(promptToString("Breed [" + animal.getBreed() + "] : ", animal.getBreed(), false));

            animal.setDescription(promptToString("Description [" + animal.getDescription() + "] : ", animal.getDescription(), false));

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
    public void deleteAnimalPrompt(ArrayList<Animal> animalList) {
        System.out.println("-- Delete Animal Record --");

        int index = animalSearch(animalList);

        if (index != -1) {

            System.out.printf("Are you sure you want to delete \'%s\'? (Y/N) ", animalList.get(index).getName());
            String response = scanner.next();
            if (response.toLowerCase().equals("y")) {
                System.out.printf("Successfully Deleted : %s", animalList.get(index).getName());
                animalList.remove(index);
            } else if (response.toLowerCase().equals("n")) {
                System.out.printf("%s was NOT deleted", animalList.get(index).getName());

            } else {
                System.out.println("I did not understand your selection. Please try again");
            }

        }
    }


    /**
     * Searches for an animal based on user supplied ID number or keyword. Provides input validation
     *
     * @param animalList ArrayList of animals which contains all animals in the animal shelter
     * @return int index number for the resulting animal
     */

    private int animalSearch(ArrayList<Animal> animalList) {
        if (animalList.size() == 0) {
            System.out.println("It looks there are no animals currently in the Animal Shelter...\n");

        }

        System.out.println("Enter an animal ID or a keyword to search (q to quit):");
        //Get the search value as a string
        String search = scanner.next().toLowerCase();

        //Check for int and search for animal based of ID number
        try {
            int indexSearch = Integer.parseInt(search);
            if (indexSearch < 1 || indexSearch > animalList.size()) {
                System.out.println("This is not a valid index");
                return animalSearch(animalList);
            } else {
                return indexSearch - 1;
            }
        }
        //catch handles any non integer search queries
        catch (Exception e) {
            if (search.equals("q")) {
                return -1;
            }
            if (search.equals("")) {
                System.out.println("It seems that you have entered an empty search...\n");
                return animalSearch(animalList);
            }

            Map<Integer, Animal> animalResult = new TreeMap<>();
            int index = 0;
            for (Animal animal : animalList) {
                if (animal.getName().toLowerCase().contains(search) || animal.getSpecies().toLowerCase().contains(search) || animal.getBreed().toLowerCase().contains(search) || animal.getDescription().toLowerCase().contains(search)) {

                    animalResult.put(index,animal);
                }
                index++;
            }
            //Return results based on Hashmap size
            if (animalResult.size() == 0) {
                System.out.println("No animal matched search criteria");
                return animalSearch(animalList);
            } else if (animalResult.size() == 1) {
                return index - 1;
            } else {

                System.out.println("The following animals matched your search:");
                System.out.println("-- Search Results --");
                //for (Animal animal : animalResult.keySet()) {
                for(Map.Entry<Integer,Animal> entry : animalResult.entrySet()) {
                    System.out.print("ID #");
                    System.out.print(entry.getKey());
                    //System.out.print(animalResult.get(animal) + 1);
                    System.out.print("  ");
                    System.out.print("Name: ");
                    System.out.print(String.format("%-10s", entry.getValue().getName()));
                    System.out.print("  ");
                    System.out.print("Species: ");
                    System.out.print(String.format("%-10s", entry.getValue().getSpecies()));
                    System.out.println();
                }
                return animalSearch(animalList);
            }


        }

    }

}

