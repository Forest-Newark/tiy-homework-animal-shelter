package com.forestnewark;


import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;


/**
 * Created by forestnewark on 3/28/17 using TDD
 */
public class MenuServiceTest {

    private MenuService menuService;
    private ArrayList<Animal> animalTestArray;
    private ArrayList<Animal> emptyTestArray;

    //Setup
    @Before
    public void initObjects() {
        //menuService
        menuService = new MenuService();
        //Arraylist of Animals
        emptyTestArray = new ArrayList<>();
        animalTestArray = new ArrayList<>();
        animalTestArray.add(new Animal(1,"Melody", "Dog", "Border Collie", "Jumps a lot"));
        animalTestArray.add(new Animal(2,"Amber", "Dog", "Golden Retriever", "Super Sweet!"));
        animalTestArray.add(new Animal(3,"Melody", "Fish", "Gold Fish", "Swims a lot"));
        animalTestArray.add(new Animal(4,"Sasha", "Cat", "Tabby", "Sleeps a lot"));
        animalTestArray.add(new Animal(5,"Frank", "Turtle", "", ""));
        animalTestArray.add(new Animal(6,"Lily", "Horse", "", ""));
    }

    //Create rule to enable log
    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();


    //Create rule to enable console data input
    @Rule
    public final TextFromStandardInputStream systemInMock
            = emptyStandardInputStream();


    //MenuService Object Test
    @Test
    /*
      Checks that menuService is not a null object
     */
    public void menuServiceIsNotNull() {
        assertThat(menuService, notNullValue());
    }


    //Login Prompt Test
    @Test
    /*
      Login prompt ask for choice
     */
    public void loginPromptAskForChoice() {
        systemInMock.provideLines("1");
        menuService.loginPrompt();
        assertThat(systemOutRule.getLog(), containsString("Choice:"));
    }

    @Test
    /*
      Selecting 1 - "Guest Login" on LoginPrompt returns 0
     */
    public void loginPromptReturnsZeroWhenGuestLoginOptionSelected() {
        systemInMock.provideLines("1");
        assertThat(menuService.loginPrompt(), equalTo(false));
    }


    @Test
    /*
      Login Prompt prompts for user name
     */
    public void loginPromptAskForUserName() {
        systemInMock.provideLines("2", "admin", "admin");
        menuService.loginPrompt();
        assertThat(systemOutRule.getLog(), containsString("Username"));

    }

    @Test
    /*
      Login Prompt prompts for password
     */
    public void loginPromptAskForPassword() {
        systemInMock.provideLines("2", "admin", "admin");
        menuService.loginPrompt();
        assertThat(systemOutRule.getLog(), containsString("Password:"));

    }

    @Test
    /*
    login prompt validates input - non-valid integer
     */
    public void loginPromptValidatesInputNonValidInteger() {
        systemInMock.provideLines("4", "1");
        menuService.loginPrompt();
        assertThat(systemOutRule.getLog(), containsString("Not a valid option"));
    }

    @Test
    /*
    login prompt validates input - double
     */
    public void loginPromptValidatesInputDouble() {
        systemInMock.provideLines("2.1", "1");
        menuService.loginPrompt();
        assertThat(systemOutRule.getLog(), containsString("Not a valid option"));
    }

    @Test
    /*
    login prompt validates input - string
     */
    public void loginPromptValidatesInputString() {
        systemInMock.provideLines("cheeseburger", "1");
        menuService.loginPrompt();
        assertThat(systemOutRule.getLog(), containsString("Not a valid option"));
    }


    //Login Validator test

    @Test
    /*
      Login Validator should return 1 for admin username/password
     */
    public void loginValidatorReturnsOneForAdmin() {
        assertThat(menuService.loginValidator("admin", "admin"), equalTo(true));
    }

    @Test
    /*
      Login Validator should return 0 for non-admin username/password
     */
    public void loginValidatorReturnsZeroForNonAdmin() {
        assertThat(menuService.loginValidator("Forest", "Newark"), equalTo(false));
    }


    //Main Menu Prompt test

    @Test
    /*
      mainMenu prompt indicates Guest user when provided false argument
     */

    public void mainMenuIndicatesGuestWithFalseArgument() {
        systemInMock.provideLines("3", "Y");
        menuService.mainMenuPrompt(false);
        assertThat(systemOutRule.getLog(), containsString("Guest"));
    }

    @Test
    /*
      mainMenu prompt indicates Admin user when provided true argument
     */
    public void mainMenuIndicatesAdminWithTrueArgument() {
        systemInMock.provideLines("6", "Y");
        menuService.mainMenuPrompt(true);
        assertThat(systemOutRule.getLog(), containsString("Admin"));
    }

    @Test
    /*
      mainMenu prompt should include List animals when provided false
     */
    public void mainMenuPromptAlwaysIncludesListAnimals() {
        systemInMock.provideLines("3", "Y");
        menuService.mainMenuPrompt(false);
        assertThat(systemOutRule.getLog(), containsString("List Animals"));
    }

    @Test
    /*
      mainMenu prompt should not include Delete Animal when provided false
     */
    public void mainMenuPromptDoesNotIncludeDeleteAnimalWhenProivdedFalse() {
        systemInMock.provideLines("3", "Y");
        menuService.mainMenuPrompt(false);
        assertThat(systemOutRule.getLog(), not(containsString("Delete Animal")));
    }

    @Test
    /*
      mainMenuPrompt should include List Animals when provided True
     */
    public void mainMethodPromptIncludesListAnimalsWhenProvidedTrue() {
        systemInMock.provideLines("6", "Y");
        menuService.mainMenuPrompt(true);
        assertThat(systemOutRule.getLog(), containsString("List Animals"));
    }

    @Test
    /*
      mainMenPrompt should include Delete Animal when provided True
     */
    public void mainMenuPromptIncludesDeleteAnimalWhenProvidedTrue() {
        systemInMock.provideLines("6", "Y");
        menuService.mainMenuPrompt(true);
        assertThat(systemOutRule.getLog(), containsString("Delete Animal"));
    }


    //User Selection Prompt Test
    @Test
    /*
      userSelectionPrompt does not accept non-integer value and reprompts user
     */
    public void userSelectionPromptDoesNotAcceptNonIntegerValue() {
        systemInMock.provideLines("John", "1");
        menuService.userSelectionPrompt(true);
        assertThat(systemOutRule.getLog(), containsString("not a valid selection."));

    }

    @Test
    /*
     userSelectionPrompt prints "not a valid selection." if the user enters 0
     */
    public void userSelectionPromptZeroIsNotValidSelection() {
        systemInMock.provideLines("0", "1");
        menuService.userSelectionPrompt(true);
        assertThat(systemOutRule.getLog(), containsString("not a valid selection."));
    }


    @Test
    /*
      userSelectionPrompt should return 6 if boolean admin is false and the user has entered 3
     */
    public void userSelectionPromptReturns6IfUserEnters3AndAdminIsFalse() {
        systemInMock.provideLines("3");
        assertThat(menuService.userSelectionPrompt(false), equalTo(6));
    }

    @Test
    /*
      userSelectionPrompt should return 3 if boolean admin is true and the user has entered 3
     */
    public void userSelectionPromptReturns3IfUserEnters3AndAdminIsTrue() {
        systemInMock.provideLines("3");
        assertThat(menuService.userSelectionPrompt(true), equalTo(3));
    }

    @Test
    /*
      userSelectionPrompt should reject any number higher than 3 if admin is false
     */
    public void userSelectionPromptRejectsNumberHighThan3WithAdminFalse() {
        systemInMock.provideLines("4", "1");
        menuService.userSelectionPrompt(false);
        assertThat(systemOutRule.getLog(), containsString("not a valid selection."));
    }


    //List Animal Tests
    @Test
    /*
      listAnimal displays correct Menu Label
     */
    public void listAnimalDisplaysMenuLabel() {
        menuService.listAnimal(animalTestArray);
        assertThat(systemOutRule.getLog(), containsString("-- Animal List --"));

    }

    @Test
    /*
    list animal displays correct animal name and species
     */
    public void listAnimalDisplaysCorrectAnimalInformation() {
        menuService.listAnimal(animalTestArray);
        assertThat(systemOutRule.getLog(), containsString("Amber            │ Dog              "));

    }

    @Test
    /*
    listAnimal prints the correct number of animals
     */
    public void listAnimalPrintsCorrectNumberOfAniamls() {
        int arraySize = animalTestArray.size();
        menuService.listAnimal(animalTestArray);
        String logFile = systemOutRule.getLog();
        String[] characters = logFile.split("\\.");
        int runningCount = 0;
        int animalTotal = 0;
        for (String character : characters) {
            try {

                int number = Integer.parseInt(character);
                if (number > runningCount) {
                    runningCount++;
                    animalTotal++;
                }

            } catch (Exception e) {
                //Not an int!
            }
        }
        assertThat(animalTotal, equalTo(animalTotal));

    }

    //Animal Search Test
    @Test
    public void animalSearchDisplaysEmptyShelterMessage() {
        menuService.animalSearch(emptyTestArray);
        assertThat(systemOutRule.getLog(), containsString("The Shelter is currently EMPTY! Please check back soon!"));


    }

    @Test
    /*
    animalSearch displays correct initial prompt to users if
     */
    public void animalSearchDisplaysCorrectPrompt() {
        systemInMock.provideLines("1");
        menuService.animalSearch(animalTestArray);
        assertThat(systemOutRule.getLog(), containsString("Enter valid Id or a keyword to search for animal(s) [q to quit]: "));
    }

    @Test
    /*
    animalSearch rejects int input less then 1
     */
    public void animalSearchValidatesInputLessThen1() {
        systemInMock.provideLines("0", "1");
        menuService.animalSearch(animalTestArray);
        assertThat(systemOutRule.getLog(), containsString("This is not a valid id"));
    }

    @Test
    /*
    animalSearch returns -1 when the user enters q
     */
    public void animalReturnsZeroWhenUserEntersQ() {
        systemInMock.provideLines("q");
        assertThat(menuService.animalSearch(animalTestArray), equalTo(-1));
    }

    @Test
    /*
    animalSearch returns correct message when no animal matches search criteria
     */
    public void animalSearchReturnsNoMatechesMethod() {
        systemInMock.provideLines("Lollipop", "1");
        menuService.animalSearch(animalTestArray);
        assertThat(systemOutRule.getLog(), containsString("No animal matched search criteria"));

    }

    @Test
    /*
    animalSearch returns correct unqiue id for an animal
     */
    public void animalSearchReturnsIndexForUniqueMatch() {
        systemInMock.provideLines("Lily");
        assertThat(menuService.animalSearch(animalTestArray), equalTo(6));
    }

    @Test
    /*
    animalSearch returns correct prompt for multiple matches
     */
    public void animalSearchReturnsPromptForMultipleMatches() {
        systemInMock.provideLines("Melody", "2");
        menuService.animalSearch(animalTestArray);
        assertThat(systemOutRule.getLog(), containsString("The following animals matched your search:"));
    }


    //Animal Detail Test
    @Test
    /*
      animalDetail displays correct Menu Label
     */
    public void animalDetailDisplaysMenuLabel() {
        systemInMock.provideLines("q");
        menuService.animalDetail(animalTestArray);
        assertThat(systemOutRule.getLog(), containsString("-- Animal Detail --"));


    }

    //Create Animal Test
    @Test
    /*
     createAnimal displays correct Menu Label
     */
    public void createAnimalDisplaysMenuLabel() {
        systemInMock.provideLines("Freddy", "Dog", "Setter", "Sweet!");
        menuService.createAnimal(animalTestArray);
        assertThat(systemOutRule.getLog(), containsString("-- Create Animal --"));

    }

    @Test
    /*
    createAnimal prompts for animal name (required)
     */
    public void createAnimalPromptsForAnimalName() {
        systemInMock.provideLines("Freddy", "Dog", "Setter", "Sweet!");
        menuService.createAnimal(animalTestArray);
        assertThat(systemOutRule.getLog(), containsString("Animal Name [Required]"));

    }

    @Test
    /*
    createAnimal prompts for animal species (required)
     */
    public void createAnimalPromptsForAnimalSpecies() {
        systemInMock.provideLines("Freddy", "Dog", "Setter", "Sweet!");
        menuService.createAnimal(animalTestArray);
        assertThat(systemOutRule.getLog(), containsString("Animal Species [Required]"));
    }

    @Test
    /*
    createAnimal prompts for animal breed (optional)
     */
    public void createAnimalPromptsForAnimalBreed() {
        systemInMock.provideLines("Freddy", "Dog", "Setter", "Sweet!");
        menuService.createAnimal(animalTestArray);
        assertThat(systemOutRule.getLog(), containsString("Animal Breed [Optional]"));
    }

    @Test
    /*
    createAnimal prompts for animal description (optional)
     */
    public void createAnimalPromptsForAnimalDescription() {
        systemInMock.provideLines("Freddy", "Dog", "Setter", "Sweet!");
        menuService.createAnimal(animalTestArray);
        assertThat(systemOutRule.getLog(), containsString("Animal Description [Optional]"));
    }

    @Test
    /*
    createAnimal prints out new animal
     */
    public void createAnimalReturnsAnAnimal() {
        systemInMock.provideLines("Freddy", "Dog", "Setter", "Sweet!");
        menuService.createAnimal(animalTestArray);
        assertThat(systemOutRule.getLog(), containsString("Freddy"));
    }

    //Edit Animal Test
    @Test
    /*
    editAnimal displays correct Menu Label
     */
    public void editAnimalDisplaysMenuLabel() {
        systemInMock.provideLines("q");
        menuService.editAnimal(animalTestArray);
        assertThat(systemOutRule.getLog(), containsString("-- Edit Animal --"));

    }

    //Delete Animal Test
    @Test
    /*
    deleteAnimal displays correct Menu Label
     */
    public void deleteAnimalDisplaysMenuLabel() {
        systemInMock.provideLines("q");
        menuService.deleteAnimal(animalTestArray);
        assertThat(systemOutRule.getLog(), containsString("-- Delete Animal --"));

    }


    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();

    //Quit Test
    @Test
    /*
    Quit displays correct Menu Label
     */
    public void quitDisplaysMenuLabel() {
        exit.expectSystemExit();
        systemInMock.provideLines("Y");
        menuService.quit();
        assertThat(systemOutRule.getLog(), containsString("-- Quit --"));

    }

    @Test
    /*
    method confirms user's intent to exit
     */
    public void quitConfirmsExit() {
        exit.expectSystemExit();
        systemInMock.provideLines("Y");
        menuService.quit();
        assertThat(systemOutRule.getLog(), containsString("-- Quit --"));
    }

    @Test
    /*
    method does not exit if user selects "N"
     */
    public void userDidNotConfirmMethod() {
        systemInMock.provideLines("N");
        menuService.quit();
        assertThat(systemOutRule.getLog(), containsString("Returning to main menu"));
    }

    @Test
    /*
    method does not exit if user supplies anything except for "Y"
     */
    public void programDoesNotQuitWIthAnyInputExceptY() {
        systemInMock.provideLines("HELLO!");
        menuService.quit();
        assertThat(systemOutRule.getLog(), containsString("Returning to main menu"));
    }
}