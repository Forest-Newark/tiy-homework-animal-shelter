package com.forestnewark;


import javafx.scene.control.Menu;

/**
 * Created by Forest Newark on 3/28/17 using TDD. This project models an animal shelter by allowing users to create, read, update, and delete
 * records of animals within the animal shelter
 */
public class Main {

    public static void main(String[] args) {
        MenuService menuService = new MenuService();
        menuService.welcomePrompt();
    }

}
