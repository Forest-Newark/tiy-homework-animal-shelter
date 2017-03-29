package com.forestnewark;

import java.io.IOException;

/**
 * Created by Forest Newark on 3/28/17 using TDD. This project models an animal shelter by allowing users to create, read, update, and delete
 * records of animals within the animal shelter
 */
public class Main {

    public static void main(String[] args) throws IOException {
        MenuService menuService = new MenuService();
        menuService.welcomePrompt();


    }

}
