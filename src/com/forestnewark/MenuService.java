package com.forestnewark;

//ASCII Banner Printer
import com.github.lalyos.jfiglet.FigletFont;

//ASCII Table Generator
import de.vandermeer.asciitable.v2.RenderedTable;
import de.vandermeer.asciitable.v2.V2_AsciiTable;
import de.vandermeer.asciitable.v2.render.V2_AsciiTableRenderer;
import de.vandermeer.asciitable.v2.render.WidthAbsoluteEven;
import de.vandermeer.asciitable.v2.themes.V2_E_TableThemes;


import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * Created by Forest Newark on 3/28/17 using TDD. MenuService prints menus for the user, validates input, and returns results
 * that can be used within the program.
 */
public class MenuService {

    //Initialize scanner with new line as a delimiter
    private Scanner scanner = new Scanner(System.in).useDelimiter("\n");

    /**
     * Prints Welcome Prompt with ASCII and calls loginPrompt()
     * @return boolean which indicates the login status of the user (Guest or Admin)
     */

    public boolean welcomePrompt() {

        System.out.println();

        String asciiArt1;
        try {
            asciiArt1 = FigletFont.convertOneLine("Animal Shelter");
            System.out.println(asciiArt1);
            System.out.println(String.format("%50s", "v1.1"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return loginPrompt();
    }


    /**
     * Prints login Prompt
     * @return boolean that indicates the login status of the user (Guest or Admin)
     */
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


    /**
     * Validates the admin login username and password
     * @param username given by the user
     * @param password given by the user
     * @return boolean that indicates if the username and password combination were correct
     */
    //Validates Admin login, currently only accepts username: admin & password: admin

    public boolean loginValidator(String username, String password) {
        if (username.toLowerCase().equals("admin") && password.toLowerCase().equals("admin")) {
            return true;
        }
        return false;
    }

    /**
     * Prints main menu prompts, filters selection options based on admin/guest login
     * @param admin indicates the state of the menu to be printed, either full menu (admin) or partial menu (Guest)
     * @return int value that is used the main class to control program flow
     */
    public int mainMenuPrompt(boolean admin) {

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


    /**
     * Returns int value based on user selection which is used by the mainMenuPrompt()
     * @param admin indicates the state of the menu which allows for the correct selection to be returned
     * @return the correct selection value that is used to control the flow of the program
     */

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

    /**
     * Prints out animals currently in the program.
     * Uses tablebuilder as helper method
     * @param animalArrayList to be printed
     */
    public void listAnimal(ArrayList<Animal> animalArrayList) {
        System.out.println("-- Animal List -- \n");

        System.out.println(tableBuilder(animalArrayList,true));

    }

    /**
     * Prints out a formatted table
     * @param printArray is the list of animals to be printed
     * @param partial indicates the amount of information to be printed about each animal
     * @return RenderedTable to be printed
     */
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

    /**
     * Prints out specific information on a single animal.
     * Uses tabelBuilder as a helper method
     * @param animalArrayList
     */
    public void animalDetail(ArrayList<Animal> animalArrayList) {
        System.out.println("-- Animal Detail --");

        int uniqueId = animalSearch(animalArrayList);

        if(uniqueId != -1){
            ArrayList<Animal> printArray = new ArrayList<>();
            for(Animal animal : animalArrayList) {
                if (animal.getUniqueId() == uniqueId) {
                    printArray.add(animal);
                }
            }
            System.out.println(tableBuilder(printArray,false));

        }
    }


    /**
     * Prompts user to create new animal
     * @param animalArrayList
     */


    public Animal createAnimal(ArrayList<Animal> animalArrayList) {
        System.out.println("-- Create Animal --");

        String name = promptToString("Animal Name [Required]: ", null, true);

        String species = promptToString("Animal Species [Required]: ", null, true);

        String breed = promptToString("Animal Breed [Optional]: ", null, false);

        String description = promptToString("Animal Description [Optional]: ", null, false);

        System.out.println("You Created the following animal!");

        Animal animal = new Animal(animalArrayList.get(animalArrayList.size()-1).getUniqueId()+1,name,species,breed,description);

        ArrayList<Animal> animalPrintArray = new ArrayList<>();
        animalPrintArray.add(animal);
        System.out.println(tableBuilder(animalPrintArray,false));

        return animal;
    }

    /**
     * Prompts user edit the details of a specific animal
     * @param animalArrayList
     */
    //Allows user to edit Animal

    public Animal editAnimal(ArrayList<Animal> animalArrayList) {
        ArrayList<Animal> animalPrintArray = new ArrayList<>();
        Animal editAnimal = null;

        System.out.println("-- Edit Animal -- ");

        int uniqueId = animalSearch(animalArrayList);

        if (uniqueId != -1) {

            for(Animal animal : animalArrayList) {
                if (animal.getUniqueId() == uniqueId) {
                    animalPrintArray.add(animal);
                    editAnimal = animal;
                }
            }

            System.out.println(tableBuilder(animalPrintArray,false));


            System.out.println("Change information or hit return to keep information");

            editAnimal.setName(promptToString("Name [" +  editAnimal.getName() + "] : ",  editAnimal.getName(), true));
            editAnimal.setSpecies(promptToString("Species [" +  editAnimal.getSpecies() + "] : ",  editAnimal.getSpecies(), true));
            editAnimal.setBreed(promptToString("Breed [" +  editAnimal.getBreed() + "] : ",  editAnimal.getBreed(), false));
            editAnimal.setDescription(promptToString("Description [" +  editAnimal.getDescription() + "] : ",  editAnimal.getDescription(), false));

            System.out.println(tableBuilder(animalPrintArray,false));

            return editAnimal;

        }
        return null;
    }

    /**
     * Prints specific prompts to the console and validates input
     * @param prompt to be printed the console
     * @param startValue of the value to be edited
     * @param required determines if this value is required
     * @return the users response
     */
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

    /**
     * Prompts user to delete an animal
     * @param animalArrayList
     */

    public Animal deleteAnimal(ArrayList<Animal> animalArrayList) {
        ArrayList<Animal> animalPrintArray = new ArrayList<>();
        Animal deleteAnimal = null;

        System.out.println("-- Delete Animal --");
        int uniqueId = animalSearch(animalArrayList);

        if (uniqueId != -1) {

            for(Animal animal : animalArrayList) {
                if (animal.getUniqueId() == uniqueId) {
                    animalPrintArray.add(animal);
                    deleteAnimal = animal;
                }
            }

            System.out.println(tableBuilder(animalPrintArray,false));

            System.out.printf("Are you sure you want to delete %s? (Y/N)",deleteAnimal.getName());

            String response = scanner.next();
            try {
                int badResponse =Integer.parseInt(response);

                System.out.printf("'%s' is not a valid response\n",badResponse);

                deleteAnimal(animalArrayList);

            }catch (Exception e) {
                if(response.toLowerCase().equals("y")){

                    System.out.printf("'%s' was deleted!\n",deleteAnimal.getName());

                    return deleteAnimal;
                }else if (response.toLowerCase().equals("n")) {

                    System.out.println("Returning to Main Menu ");
                }else {

                    System.out.printf("'%s' was not a valid response",response);

                    deleteAnimal(animalArrayList);
                }
            }

        }

        return null;
    }

    /**
     * Prompts user to quit the program with verification
     */

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


    /**
     * search method which returns the int of search for animals
     * @param animalArrayList to be searched
     * @return the uniqueID of the animal. Returns -1 if no animal is found that matches search
     */

    public int animalSearch(ArrayList<Animal> animalArrayList) {
        if (animalArrayList.size() == 0) {

            System.out.println("The Shelter is currently EMPTY! Please check back soon!");

            return -1;
        } else {

            System.out.println("Enter valid Id or a keyword to search for animal(s) [q to quit]: ");

            Scanner scanner = new Scanner(System.in);

            String search = scanner.next().toLowerCase();


            try {
                int uniqueIndexSearch = Integer.parseInt(search);

                for(Animal animal : animalArrayList) {
                    if (animal.getUniqueId() == uniqueIndexSearch) {
                        return animal.getUniqueId();
                    }
                }

                System.out.println("This is not a valid id");
                animalSearch(animalArrayList);
            }

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

                if (animalPrintArray.size() == 0) {

                    System.out.println("No animal matched search criteria");

                    return animalSearch(animalArrayList);
                } else if (animalPrintArray.size() == 1) {

                    return animalPrintArray.get(0).getUniqueId();
                } else {

                    System.out.println("The following animals matched your search:");

                    System.out.println(tableBuilder(animalPrintArray,true));

                    return animalSearch(animalArrayList);
                }

            }

        }

        return -1;
    }


}





