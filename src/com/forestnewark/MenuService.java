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
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        System.out.print("Choice: ");
        String choice = scanner.next();

        if (Integer.parseInt(choice) == 1) {
            return false;
        } else if (Integer.parseInt(choice) == 2) {

            System.out.println("Username: ");

            String username = scanner.next();

            System.out.println("Password: ");
            String password = scanner.next();
            return loginValidator(username, password);
        } else {
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
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");

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
        Animal[] listArray = new Animal[animalArrayList.size()];
        for (int x = 0;x < animalArrayList.size();x++) {
            listArray[x] = animalArrayList.get(x);
        }

        System.out.println(tableBuilder(listArray,true));

    }

    //Prints out a formatted table from animal Array

    private RenderedTable tableBuilder(Animal[] animalList,boolean partial) {


        V2_AsciiTable at = new V2_AsciiTable();
        at.addRule();
        int index = 1;
        if (partial){
            at.addRow("ID", "NAME", "SPECIES", "DAYS IN SHELTER");
            at.addRule();

            for (int x = 0;x < animalList.length;x++) {
                at.addRow(index, animalList[x].getName(), animalList[x].getSpecies(), ChronoUnit.DAYS.between(animalList[x].getDateAdded(), LocalDate.now()));
                at.addRule();
                index++;
            }
        }
        if (!partial){
            at.addRow("ID","NAME", "SPECIES", "Breed", "Description", "Date Added", "Days In Shelter");
            at.addRule();
            for (int x = 0;x < animalList.length;x++) {
                at.addRow(index, animalList[x].getName(),animalList[x].getSpecies(),animalList[x].getBreed(),animalList[x].getDescription(),animalList[x].getDateAdded(),ChronoUnit.DAYS.between(animalList[x].getDateAdded(), LocalDate.now()));
                at.addRule();
                index++;
            }

        }



        V2_AsciiTableRenderer rend = new V2_AsciiTableRenderer();

        rend.setTheme(V2_E_TableThemes.UTF_LIGHT.get());
        rend.setWidth(new WidthAbsoluteEven(76));

        RenderedTable rt = rend.render(at);
        return rt;
    }




    //Prints out specific information on animal
    //TODO: FIX THIS SHIT!
    public void animalDetail() {
        System.out.println("-- Animal Detail --");
    }



    //Prompts user to create new animal
    //TODO: Control for empty values
    public Animal createAnimal() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("-- Create Animal --");
        System.out.println("Animal Name [Required]: ");
        String name = scanner.next();
        System.out.println("Animal Species [Required]: ");
        String species = scanner.next();
        System.out.println("Animal Breed [Optional]: ");
        String breed = scanner.next();
        System.out.println("Animal Description [Optional]:");
        String description = scanner.next();
        System.out.println("You Created the following animal!");
        Animal animal = new Animal(name,species,breed,description);
        Animal[] animalArray = {animal};
        System.out.println(tableBuilder(animalArray,false));
        return animal;
    }

    //Allows user to edit Animal
    //TODO: Write this method
    public void editAnimal() {
        System.out.println("-- Edit Animal --");
    }


    //Allows user to delete an animal
    //TODO: Write this method
    public int deleteAnimal() {
        System.out.println("-- Delete Animal --");

        return 0;
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


    //Search for animals
    //TODO: Fix Print of of Animals to formatted table

    public int animalSearch(ArrayList<Animal> animalArrayList) {
        if (animalArrayList.size() == 0) {
            System.out.println("The Shelter is currently EMPTY! Please check back soon!");
        } else {
            System.out.println("Enter valid Id or a keyword to search for animal(s) [q to quit]: ");
            Scanner scanner = new Scanner(System.in);
            //Get the search value as a string
            String search = scanner.next().toLowerCase();

            //Check for int and search for animal based of ID number
            try {
                int indexSearch = Integer.parseInt(search);
                if (indexSearch < 1 || indexSearch > animalArrayList.size()) {
                    System.out.println("This is not a valid index");
                    return animalSearch(animalArrayList);
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
                    return animalSearch(animalArrayList);
                }

                Map<Integer, Animal> animalResult = new TreeMap<>();
                int index = 0;
                for (Animal animal : animalArrayList) {
                    if (animal.getName().toLowerCase().contains(search) || animal.getSpecies().toLowerCase().contains(search) || animal.getBreed().toLowerCase().contains(search) || animal.getDescription().toLowerCase().contains(search)) {

                        animalResult.put(index, animal);
                    }
                    index++;
                }
                //Return results based on Hashmap size
                if (animalResult.size() == 0) {
                    System.out.println("No animal matched search criteria");
                    return animalSearch(animalArrayList);
                } else if (animalResult.size() == 1) {
                    return index - 1;
                } else {
                    System.out.println("The following animals matched your search:");

                    System.out.println("-- Search Results --");
                    //for (Animal animal : animalResult.keySet()) {
                    for (Map.Entry<Integer, Animal> entry : animalResult.entrySet()) {
                        System.out.print("ID #");
                        System.out.print(entry.getKey() + 1);
                        //System.out.print(animalResult.get(animal) + 1);
                        System.out.print("  ");
                        System.out.print("Name: ");
                        System.out.print(String.format("%-10s", entry.getValue().getName()));
                        System.out.print("  ");
                        System.out.print("Species: ");
                        System.out.print(String.format("%-10s", entry.getValue().getSpecies()));
                        System.out.println();
                    }
                    return animalSearch(animalArrayList);
                }

            }

        }
        return 0;
    }

}





