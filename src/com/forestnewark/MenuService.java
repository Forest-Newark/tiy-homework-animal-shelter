package com.forestnewark;


import com.github.lalyos.jfiglet.FigletFont;


import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Forest Newark on 3/28/17 using TDD. MenuService prints menus for the user, validates input, and returns results
 * that can be used within the program.
 */
public class MenuService {


    public void welcomePrompt(){

        System.out.println();
        String asciiArt1 = null;
        try {
            asciiArt1 = FigletFont.convertOneLine("Animal Shelter");
            System.out.println(asciiArt1);
            System.out.println(String.format("%50s", "v1.1"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        loginPrompt();
    }

    public int loginPrompt() {
        System.out.println("Please Select a Login Option below");
        System.out.println("1 - Guest Login");
        System.out.println("2 - Admin Login");
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        System.out.print("Choice: ");
        String choice = scanner.next();

        if (Integer.parseInt(choice) == 1) {
            return 0;
        }else if (Integer.parseInt(choice) == 2) {

            System.out.println("Username: ");

            String username = scanner.next();

            System.out.println("Password: ");
            String password = scanner.next();
            return loginValidator(username,password);
        }else {
            System.out.println("Not a valid option");
            return loginPrompt();
        }

    }

    public int loginValidator(String username, String password) {
        if (username.toLowerCase().equals("admin") && password.toLowerCase().equals("admin")){
            return 1;
        }
        return 0;
    }


    public void mainMenuPrompt(boolean admin) {
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



    }
    public void userSelectionPrompt(boolean admin) {
        System.out.println("Please choose an option: ");
    }
}





