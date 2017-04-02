package com.forestnewark;


import com.github.lalyos.jfiglet.FigletFont;
import de.vandermeer.asciitable.v2.RenderedTable;
import de.vandermeer.asciitable.v2.V2_AsciiTable;
import de.vandermeer.asciitable.v2.render.V2_AsciiTableRenderer;
import de.vandermeer.asciitable.v2.render.WidthAbsoluteEven;
import de.vandermeer.asciitable.v2.themes.V2_E_TableThemes;


import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * Created by Forest Newark on 3/28/17 using TDD. MenuService prints menus for the user, validates input, and returns results
 * that can be used within the program.
 */
public class MenuService {

    //Initialize scanner with new line as a delimiter
    private Scanner scanner = new Scanner(System.in).useDelimiter("\n");

    //Prints Welcome Prompt with ASCII
    public boolean welcomePrompt() {

        System.out.println();
        String asciiArt1 = null;
        try {
            asciiArt1 = FigletFont.convertOneLine("Animal Shelter");
            System.out.println(asciiArt1);
            System.out.println(String.format("%50s", "v1.1"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return loginPrompt();
    }




    //Prints login Prompt (Guest/Admin) Login

    public boolean loginPrompt() {
        System.out.println("Please Select a Login Option below");
        System.out.println("1 - Guest Login");
        System.out.println("2 - Admin Login");
        System.out.print("Choice: ");
        String choice = scanner.next();

        try {
            int loginInt = Integer.parseInt(choice);
            if(loginInt == 1) {
                return false;
            } else if (loginInt == 2) {
                System.out.println("Username: ");

                String username = scanner.next();

                System.out.println("Password: ");
                String password = scanner.next();
                return loginValidator(username, password);
            }else {
                System.out.println("Not a valid option");
                return loginPrompt();
            }
        }catch (Exception e){
            System.out.println("Not a valid option");
            return loginPrompt();
        }

    }


    //Validates Admin login, currently only accepts username: admin & password: admin

    public boolean loginValidator(String username, String password) {
        if (username.toLowerCase().equals("admin") && password.toLowerCase().equals("admin")) {
            return true;
        }
        return false;
    }


    //Prints main menu prompts, filters based on admin/guest login

    public int mainMenuPrompt(boolean admin) {
        // String user = admin ? "Admin" : "Guest";
        StringBuilder sb = new StringBuilder();
        sb.append("-- Main Menu --\n");
        sb.append(String.format("%5s", "["));
        sb.append(admin ? "Admin" : "Guest");
        sb.append("]\n");
        sb.append("1) List Animals\n");
        sb.append("2) View Animal Details\n");
        if (admin) {
            sb.append("3) Create Animal\n");
            sb.append("4) Edit Animal\n");
            sb.append("5) Delete Animal\n");
        }
        sb.append(admin ? "6) Quit" : "3) Quit");
        System.out.println(sb.toString());
        return userSelectionPrompt(admin);

    }



    // Returns int value based on user selection, contorls for admin/guest menu

    public int userSelectionPrompt(boolean admin) {
        System.out.println("Please choose an option: ");

        String input = scanner.next();
        try {
            int selection = Integer.parseInt(input);
            if (selection < 1 || (selection > 6 && admin) || (selection > 3 && !admin)) {
                System.out.println("\'" + selection + "\' is not a valid selection.");
                return userSelectionPrompt(admin);
            } else if (!admin) {
                if (selection == 3) {
                    return 6;
                } else {
                    return selection;
                }
            } else {
                return selection;
            }
        } catch (Exception e) {
            System.out.println("\'" + input + "\' is not a valid selection.");
            return userSelectionPrompt(admin);
        }

    }


    //Prints out animals with provided arraylist, uses tableBuilder as helper method
    public void listAnimal(ArrayList<Animal> animalArrayList) {
        System.out.println("-- Animal List -- \n");
        System.out.println(tableBuilder(animalArrayList,true));

    }

    //Prints out a formatted table from animal Array

    private RenderedTable tableBuilder(ArrayList<Animal> printArray,boolean partial) {

        V2_AsciiTable at = new V2_AsciiTable();
        at.addRule();
        if (partial){
            at.addRow("ID", "NAME", "SPECIES", "DAYS IN SHELTER");
            at.addRule();

            for (Animal animal : printArray) {
                at.addRow(animal.getUniqueId(), animal.getName(), animal.getSpecies(), ChronoUnit.DAYS.between(animal.getDateAdded(), LocalDate.now()));
                at.addRule();
            }
        }
        if (!partial){
            at.addRow("ID","NAME", "SPECIES", "Breed", "Description", "Date Added", "Days In Shelter");
            at.addRule();
            for (Animal animal : printArray) {
                at.addRow(animal.getUniqueId(), animal.getName(),animal.getSpecies(),animal.getBreed(),animal.getDescription(),animal.getDateAdded(),ChronoUnit.DAYS.between(animal.getDateAdded(), LocalDate.now()));
                at.addRule();
            }

        }

        V2_AsciiTableRenderer rend = new V2_AsciiTableRenderer();

        rend.setTheme(V2_E_TableThemes.UTF_LIGHT.get());
        rend.setWidth(new WidthAbsoluteEven(76));

        return rend.render(at);

    }


    //Prints out specific information on animal
    //TODO: FIX THIS SHIT!
    public void animalDetail(ArrayList<Animal> animalArrayList) {
        System.out.println("-- Animal Detail --");

        int index = animalSearch(animalArrayList);

        if(index != -1){
            ArrayList<Animal> animalPrintArray = new ArrayList<>();
            animalPrintArray.add(animalArrayList.get(index));
            System.out.println(tableBuilder(animalPrintArray,false));

        }
    }



    //Prompts user to create new animal
    //TODO: Control for empty values
    public Animal createAnimal(ArrayList<Animal> animalArrayList) {
        System.out.println("-- Create Animal --");

        String name = promptToString("Enter animal name(Required): ", null, true);

        String species = promptToString("Enter animal species(Required): ", null, true);

        String breed = promptToString("Enter animal breed(Optional): ", null, false);

        String description = promptToString("Enter animal description(Optional): ", null, false);
        System.out.println("You Created the following animal!");
        Animal animal = new Animal(name,species,breed,description, animalArrayList.size()+1);

        ArrayList<Animal> animalPrintArray = new ArrayList<>();
        animalPrintArray.add(animal);
        System.out.println(tableBuilder(animalPrintArray,false));
        return animal;
    }

    //Allows user to edit Animal
    //TODO: Write this method
    public void editAnimal(ArrayList<Animal> animalArrayList) {

        System.out.println("-- Edit Animal -- ");

        int index = animalSearch(animalArrayList);

        if (index != -1) {
            Animal animal = animalArrayList.get(index);

            ArrayList<Animal> animalPrintArray = new ArrayList<>();
            animalPrintArray.add(animalArrayList.get(index));
            System.out.println(tableBuilder(animalPrintArray,false));


            System.out.println("Change information or hit return to keep information");

            animal.setName(promptToString("Name [" + animal.getName() + "] : ", animal.getName(), true));

            animal.setSpecies(promptToString("Species [" + animal.getSpecies() + "] : ", animal.getSpecies(), true));

            animal.setBreed(promptToString("Breed [" + animal.getBreed() + "] : ", animal.getBreed(), false));

            animal.setDescription(promptToString("Description [" + animal.getDescription() + "] : ", animal.getDescription(), false));

            System.out.printf("\nUpdated Animal Information:\n" +
                    "Name: %s\nSpecies: %s\nBreed: %s\nDescription: %s", animal.getName(), animal.getSpecies(), animal.getBreed(), animal.getDescription());

        }



    }

    private String promptToString(String prompt, String startValue, boolean required) {

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


    //Allows user to delete an animal

    public void deleteAnimal(ArrayList<Animal> animalArrayList) {
        System.out.println("-- Delete Animal --");
        int index = animalSearch(animalArrayList);

        if (index != -1) {
            Animal animal = animalArrayList.get(index);
            ArrayList<Animal> animalPrintArray = new ArrayList<>();
            animalPrintArray.add(animalArrayList.get(index));
            System.out.println(tableBuilder(animalPrintArray,false));

            System.out.printf("Are you sure you want to delete %s? (Y/N)",animal.getName());
            String response = scanner.next();
            try {
                int badResponse =Integer.parseInt(response);
                System.out.printf("'%s' is not a valid response",badResponse);
                deleteAnimal(animalArrayList);

            }catch (Exception e) {
                if(response.toLowerCase().equals("y")){
                    System.out.printf("'%s' was deleted!\n",animal.getName());
                    animalArrayList.remove(index);
                }else if (response.toLowerCase().equals("n")) {
                    System.out.println("Returning to Main Menu ");
                }else {
                    System.out.printf("'%s' was not a valid response",response);
                    deleteAnimal(animalArrayList);
                }
            }

        }

    }

    //Quits the program with user verification
    public void quit() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("-- Quit --");
        System.out.println("Are you sure you want to quit? (Y/N)");
        if (scanner.next().toLowerCase().contains("y")){
            System.out.println("Goodbye");
            System.exit(0);
        }else {
            System.out.println("Returning to main menu");
        }
    }



    public int animalSearch(ArrayList<Animal> animalArrayList) {
        if (animalArrayList.size() == 0) {
            System.out.println("The Shelter is currently EMPTY! Please check back soon!");
            return -1;
        } else {
            System.out.println("Enter valid Id or a keyword to search for animal(s) [q to quit]: ");
            Scanner scanner = new Scanner(System.in);
            //Get the search value as a string
            String search = scanner.next().toLowerCase();

            //Check for int and search for animal based of ID number
            try {
                int uniqueIndexSearch = Integer.parseInt(search);
                if (uniqueIndexSearch < 1 || uniqueIndexSearch > animalArrayList.size()) {
                    System.out.println("This is not a valid index");
                    return animalSearch(animalArrayList);
                } else {
                    for(Animal animal : animalArrayList) {
                        if (animal.getUniqueId() == uniqueIndexSearch) {
                            return animalArrayList.indexOf(animal);
                        }
                    }
                }
            }
            //catch handles any non integer search queries
            catch (Exception e) {
                if (search.equals("q")) {
                    return -1;
                }
                if (search.equals("")) {
                    System.out.println("It seems that you have entered an empty search...\n");
                    return animalSearch(animalArrayList);
                }

                ArrayList<Animal> animalPrintArray = new ArrayList<>();
                for (Animal animal : animalArrayList) {
                    if (animal.getName().toLowerCase().contains(search) || animal.getSpecies().toLowerCase().contains(search) || animal.getBreed().toLowerCase().contains(search) || animal.getDescription().toLowerCase().contains(search)) {
                        animalPrintArray.add(animal);
                    }
                }
                //Return results based on Hashmap size
                if (animalPrintArray.size() == 0) {
                    System.out.println("No animal matched search criteria");
                    return animalSearch(animalArrayList);
                } else if (animalPrintArray.size() == 1) {
                    return animalArrayList.indexOf(animalPrintArray.get(0));
                } else {
                    System.out.println("The following animals matched your search:");

                    System.out.println(tableBuilder(animalPrintArray,true));

                    return animalSearch(animalArrayList);
                }

            }

        }

        return 0;
    }

}





