# tiy-homework-animal-shelter
Create a program to manage animals in a shelter.

Animal Shelter - v1.0

Preface

This assignment is significantly larger and more challenging than anything you will have done so far in class. But, that's ok. You have all of the tools you need to complete it successfully. For example, the MenuService class (described below) can be patterned off the recent Scanner exercise.

Be sure to read the instructions below carefully. Think about your approach to the problem, draw pictures to visualize the structure of your program, and be methodical. You can do this.

Animal Shelter - v1.0

Summary: Create a program to manage animals in a shelter.

The purpose of this project is to create a significantly more complex command-line application than we've created so far. It will use a text-based menu system to control the program. Through the remainder of this class we will be building and evolving this application. Eventually this will become a full-blown Spring web application.

Start by creating a new Github repository named tiy-homework-animal-shelter and clone it locally.

You will create a new branch in Git for each version (IE: new feature) of this program. You will treat the master branch as the gold-standard. When your work is complete for a given version of this assignment you will merge your branch into master. But, you will not work directly in master. When you submit your assignment, you should still provide the url to the root of your repository. However, I will be confirming that you did your work on a branch.

For this first version of the project, you should create a new branch named v1.0, switch to that branch, and do your work there. When you are satisfied with your work, merge that branch into your master branch and submit that. With version 2.0 you will create a new branch named v2.0, do you work there, and then merge that into master. This will be the pattern through the rest of this class.

Follow the instructions below to complete this assignment:

Create a .gitignore File

After cloning your repository, but before you create your v1.0 branch or write any code, you should first create a .gitignore file in your master branch. The contents of the .gitignore should be (at least):

target/
out/
!.mvn/wrapper/maven-wrapper.jar

### STS ###
.classpath
.factorypath
.project
.settings
.springBeans

### IntelliJ IDEA ###
.idea
*.iws
*.iml
*.ipr

### NetBeans ###
nbproject/private/
build/
nbbuild/
dist/
nbdist/
.nb-gradle/
You'll need to add and commit this to your master branch to get started.

Create v1.0 Branch

Now that you have something in your master branch, you can create your feature branches. Let's name our feature branches so that they match each assignment version. EG: v1.0.

Create a branch from master named v1.0 and switch into it. This is where you will do your work.

Be sure to commit frequently as you work through this assignment. I want to see several commits, not just one or two for each submission.

Required Classes and Structure

For this assignment you will need to create a few classes. I give you some guidelines below, but the details of how you implement them are generally left to you. Be sure to think about how you've solved similar problems previously.

You may want to think about using an appropriate package name for your classes. For example, com.theironyard.

Animal Class

You will need to create a class named Animal. Animals have these properties:

Name
Species
Breed
Description
MenuService class

This program is text-based and users interact with it using menus and textual prompts. You'll see all of these details below, but this is an example of what the main menu will look like:

-- Main Menu --

1) List animals
2) Create an animal
3) View animal details
4) Edit an animal
5) Delete an animal
6) Quit

Please choose an option:
When the user enters a value like 3 the program will know that the user wants to view an animal's details and then prompt them for which animal they want to view. If the user selects 2 or 4 the program will provide them with a series of prompts to collect data about the new animal.

Placing all of this logic into the Main class would quickly create a mess of spaghetti code. Instead, you should create a new class named MenuService. This class' responsibility is displaying menus, prompting the user for input, and returning the results. IE, it manages the menus.

For example, your MenuService might have a method named promptForMainMenu() that looks like this:

public int promptForMainMenuSelection() {
    System.out.println("\n-- Main Menu --\n" +
            "\n" +
            "1) List animals\n" +
            "2) Create an animal\n" +
            "3) View animal details\n" +
            "4) Edit an animal\n" +
            "5) Delete an animal\n" +
            "6) Quit\n");

    return waitForInt("Please choose an option:");
}
This method simply prints out the main menu text and then returns the value from another method named waitForInt().

The waitForInt() method might look like this:

/**
 * This method displays the provided prompt and then waits for a user to
 * provide input. The input must be a valid integer. If not, an error
 * message is displayed and the user is prompted to try again. If the input
 * is valid, then the value entered is returned.
 *
 * @param prompt The prompt displayed to the user.
 * @return The value entered by the user
 */
public int waitForInt(String prompt){
    // display the prompt to the user
    System.out.println(prompt);

    // check if the next input is an int.
    if(!scanner.hasNextInt()){
        // if the next input is not an int, read it as a string to show in an error message
        String badInput = scanner.next();

        // show an error message
        System.out.printf("'%s' is not a valid number. Please try again.\n", badInput);

        // recursively prompt the user again
        return waitForInt(prompt);
    } else {
        // return the int the user provided
        return scanner.nextInt();
    }

}
These two methods might look familiar as you've already created something similar for the previous Scanner exercise.

This method prints out a prompt, such as "Please choose an option:". It then uses Scanner to read user input. This waits until the user has provided data and then checks to see if it was an integer. If so, that integer is returned. If not, an error message is shown and the method recurses.

I suggest that you create methods for each action that can be performed on the menu. For example:

int = promptForMainMenuSelection() - This might print the main menu, wait for user input, and return an integer indicating the user's choice.
Animal = promptForAnimalData() - This might prompt the user for each field for an animal and then create and return a new instance of the Animal class using that data.
There are many ways to accomplish this. But, it's generally an indicator you can do better if your methods become longer than ten or twenty lines.
Somewhere in your application you will need to have an ArrayList of Animal instances. Give careful consideration to where you keep this. It might not make sense to have this in the MenuService. [callout-hint]

[callout-hint] You could write methods that return an Animal or accept one as an argument. For example, why not write one method to collect information for a new animal, promptForNewAnimal(). This might prompt the user for the Animal's name, etc, and then actually construct and return an Animal. Maybe you could do something similar for editing an animal?

Main class

While console input and output for this application is managed via the MenuService, we still need something to control the application. You will need to create a Main class with a main() method that does this.

Your main method will have a while(true){} loop in it to keep the application running forever. It will likely have some conditional statements that are executed based on user input. For example:

while(true) {
    int action = menuService.promptForMainMenuSelection();

    if(action == MenuService.CREATE_ANIMAL){
        // do something
    } else if(action == MenuService.LIST_ANIMALS){
        // do something
    }
}
Note that I'm using static final properties on the MenuService to hold constants that make reading my code easier. While I could check if action == 1, out of context it's hard to tell what that means. It is easier to read and understand action == MenuService.CREATE_ANIMAL.

Program Behavior

The following instructions describe how the program should behave. The menu structure, messages, etc, shown below are suggestions, not rules. You have creative freedom, but your program must be friendly and usable without any prior knowledge, while still holding true to the requirements listed above.

The only additional requirements are that the program provides some way to do the following:

Create animals
Animal instances should be stored in an ArrayList of Animal instances. Hint: you probably don't want to keep this data in the MenuService.
Read animals
Edit/Update animals
When editing an animal, don't make users retype everything. See the suggestions below regarding editing an animal. Hint: You could create a method on MenuService that accepts an Animal as an argument and, for each field, if the user doesn't provide a new value, it reuses the current value.
Delete animals
Quit the application
Main Menu

The main menu will give the user a list of options they can choose from. The user will be able to enter a number from 1 to 6 to preform the indicated action. Invalid input at the menu will cause a friendly error message to be displayed and the user will be prompted to try again.

For example:

-- Main Menu --

1) List animals
2) Create an animal
3) View animal details
4) Edit an animal
5) Delete an animal
6) Quit

Please choose an option: 9

Error: Sorry, that isn't a valid option.

Please choose an option: 
Bonus points if you colorize the error messages or other parts of this UI to make them stand out.

List Animals

If the user chooses option 1 in the main menu they will see a list of animals. Animals will be printed out with an index that can be used to view that animal (EG: main menu option 3). The list will include the name of the animal and the type of animal (dog, cat, bird, etc). This data will be retrieved from the ArrayList of Animal instances.

After the list of animals has been printed out, the main menu will be printed again.

For example:

-- Main Menu --

1) List animals
2) Create an animal
3) View animal details
4) Edit an animal
5) Delete an animal
6) Quit

Please choose an option: 1

-- List of Animals --

1) Sparky           Dog
2) Sam              Cat
3) Bob              Tarantula
4) Zed              Parrot

-- Main Menu --

1) List animals
2) Create an animal
3) View animal details
4) Edit an animal
5) Delete an animal
6) Quit

Please choose an option: 
Create an Animal

If the user chooses option 2 in the main menu they will receive prompts for information about the new animal. The user must provide information for the name, species, and description. The breed field is optional.

After the last field is provided the animal will be added to the list of animals and the user will be returned to the main menu. The list of animals will be stored in an ArrayList of Animals.

If a required field is not provided the user will be presented with an error message and prompted to provide the field again.

For example:

-- Main Menu --

1) List animals
2) Create an animal
3) View animal details
4) Edit an animal
5) Delete an animal
6) Quit

Please choose an option: 2

-- Create an Animal --

Please answer the following questions.

Animal Name: Fred
Species: 

Error: The Species field is required. Please try again.

Species: Cat
Breed (optional):
Description: Fat and happy. Very long when stretched out.

Success: The animal has been created!

-- Main Menu --

1) List animals
2) Create an animal
3) View animal details
4) Edit an animal
5) Delete an animal
6) Quit

Please choose an option: 
View Animal Details

If the user chooses option 3 in the main menu they will be prompted to enter a number for a specific pet. This number comes from the list of pets (main menu option 1) and the corresponding animal will be retrieved from the ArrayList of Animals.

If the animal does not exist or the input is invalid an error message will be shown and the user will be prompted again.

Details of the selected pet will be displayed and the user will be returned to the main menu.

For example:

-- Main Menu --

1) List animals
2) Create an animal
3) View animal details
4) Edit an animal
5) Delete an animal
6) Quit

Please choose an option: 3

-- View an Animal --

What is the numeric ID of the animal you want to view?: Fred

Error: Sorry, that isn't a valid option. 

What is the numeric ID of the animal you want to view?: 1

Name: Fred
Species: Cat
Breed: 
Description: Fat and happy. Very long when stretched out.

-- Main Menu --

1) List animals
2) Create an animal
3) View animal details
4) Edit an animal
5) Delete an animal
6) Quit

Please choose an option: 
Edit an Animal

If the user chooses option 4 in the main menu they will be prompted to enter a number for a specific pet. This number comes from the list of pets (main menu option 1) and the corresponding animal will be retrieved from the ArrayList of Animals.

If the animal does not exist or the input is invalid an error message will be shown and the user will be prompted again.

The user will be given the same fields as when creating an animal, but default values will be provided (indicated in [] square brackets) based on the specified animal's values. If a user simply presses enter without providing a new value the current value will be retained. If a new value is provided it will replace the old value. As with adding an animal, all fields are required except for breed.

When an animal is updated a success message will be printed, followed by the animal's new details (just like when viewing an animal).

For example:

-- Main Menu --

1) List animals
2) Create an animal
3) View animal details
4) Edit an animal
5) Delete an animal
6) Quit

Please choose an option: 4

-- Edit an Animal --

What is the numeric ID of the animal you want to edit?: Fred

Error: Sorry, that isn't a valid option. 

What is the numeric ID of the animal you want to edit?: 1

Please answer the following questions. Press enter to keep the current values.

Animal Name [Fred]: 
Species [Cat]: 
Breed (optional) []: Tabby
Description [Fat and happy. Very long when stretched out.]: 

Success: The animal has been updated!

Name: Fred
Species: Cat
Breed: Tabby
Description: Fat and happy. Very long when stretched out.

-- Main Menu --

1) List animals
2) Create an animal
3) View animal details
4) Edit an animal
5) Delete an animal
6) Quit

Please choose an option: 
Delete an Animal

If the user chooses option 5 in the main menu they will be prompted to enter a number for a specific pet. This number comes from the list of pets (main menu option 1) and the corresponding animal will be retrieved from the ArrayList of Animals.

If the animal does not exist or the input is invalid an error message will be shown and the user will be prompted again.

If a valid animal is selected the animal's details will be printed out and the user will be prompted to confirm their choice by typing either "yes" or "no". If the user enters "no" they will be prompted to select a different animal. If the user enters "yes" the animal will be deleted, a success message will be shown, and the main menu will be redisplayed.

For example:

-- Main Menu --

1) List animals
2) Create an animal
3) View animal details
4) Edit an animal
5) Delete an animal
6) Quit

Please choose an option: 5

-- Delete an Animal --

What is the numeric ID of the animal you want to delete?: Fred

Error: Sorry, that isn't a valid option. 

What is the numeric ID of the animal you want to delete?: 1

Name: Fred
Species: Cat
Breed: Tabby
Description: Fat and happy. Very long when stretched out.

Are you sure you want to delete this animal?: arrgh!

Error: Sorry, that isn't a valid option. 

Are you sure you want to delete this animal?: no

What is the numeric ID of the animal you want to delete?: 2

Name: Zed
Species: Bird
Breed: Parrot
Description: Curses like a sailor.

Are you sure you want to delete this animal?: yes

Success: The animal has been deleted!

-- Main Menu --

1) List animals
2) Create an animal
3) View animal details
4) Edit an animal
5) Delete an animal
6) Quit

Please choose an option: 
Quit

If the user chooses option 6 in the main menu they will be asked to confirm they wish to quit. If the user enters "no" they will be redirected to the main menu. If they enter invalid input they will see an error message and be prompted again. If they enter "yes" the application will quit.

For example:

-- Main Menu --

1) List animals
2) Create an animal
3) View animal details
4) Edit an animal
5) Delete an animal
6) Quit

Please choose an option: 6

Are you sure you want to quit? All of your data will be lost!: arrgh!

Error: Sorry, that isn't a valid option. 

Are you sure you want to quit? All of your data will be lost!: yes

Goodbye!!
Submitting

When you are done with the assignment, merge your changes from your v1.0 branch into master. Submit the root url to your repository. I will be checking to make sure you worked on v1.0, not master, and have made several commits, not just one.