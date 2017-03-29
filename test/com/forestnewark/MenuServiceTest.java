package com.forestnewark;


import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;


/**
 * Created by forestnewark on 3/28/17.
 */
public class MenuServiceTest {

    private MenuService menuService;

    @Before
    public void initObjecst() {
        menuService = new MenuService();
    }

    //Create rule to enable log
    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();


    //Create rule to enable console data input
    @Rule
    public final TextFromStandardInputStream systemInMock
            = emptyStandardInputStream();

    @Test
    /**
     * Checks that menuService is not a null object
     */
    public void menuServiceIsNotNull() {
        assertThat(menuService,notNullValue());
    }

    @Test
    /**
     * Login prompt ask for choice
     */
    public void loginPromptAskForChoice() {
        systemInMock.provideLines("1");
        menuService.loginPrompt();
        assertThat(systemOutRule.getLog(),containsString("Choice:"));
    }

    @Test
    /**
     * Selecting 1 - "Guest Login" on LoginPrompt returns 0
     */
    public void loginPromptReturnsZeroWhenGuestLoginOptionSelected(){
        systemInMock.provideLines("1");
        assertThat(menuService.loginPrompt(),equalTo(0));
    }


    @Test
    /**
     * Login Prompt prompts for user name
     */
    public void loginPromptAskForUserName() {
        systemInMock.provideLines("2","admin","admin");
        menuService.loginPrompt();
        assertThat(systemOutRule.getLog(),containsString("Username"));

    }

    @Test
    /**
     * Login Prompt prompts for password
     */
    public void loginPromptAskForPassword(){
        systemInMock.provideLines("2","admin","admin");
        menuService.loginPrompt();
        assertThat(systemOutRule.getLog(),containsString("Password:"));

    }

    @Test
    /**
     * Login Validator should return 1 for admin username/password
     */
    public void loginValidatorReturnsOneForAdmin(){
        assertThat(menuService.loginValidator("admin","admin"),equalTo(1));
    }

    @Test
    /**
     * Login Validator should return 0 for non-admin username/password
     */
    public void loginValidatorReturnsZeroForNonAdmin(){
        assertThat(menuService.loginValidator("Forest","Newark"),equalTo(0));
    }

    @Test
    /**
     * mainMenu prompt indicates Guest user when provided false argument
     */

    public void mainMenuIndicatesGuestWithFalseArgument() {
        menuService.mainMenuPrompt(false);
        assertThat(systemOutRule.getLog(),containsString("Guest"));
    }

    @Test
    /**
     * mainMenu prompt indicates Admin user when provided true argument
     */
    public void mainMenuIndicatesAdminWithTrueArgument(){
        menuService.mainMenuPrompt(true);
        assertThat(systemOutRule.getLog(),containsString("Admin"));
    }

    @Test
    /**
     * mainMenu prompt should include List animals when provided false
     */
    public void mainMenuPromptAlwaysIncludesListAnimals() {
        menuService.mainMenuPrompt(false);
        assertThat(systemOutRule.getLog(),containsString("List Animals"));
    }

    @Test
    /**
     * mainMenu prompt should not include Delete Animal when provided false
     */
    public void mainMenuPromptDoesNotIncludeDeleteAnimalWhenProivdedFalse() {
        menuService.mainMenuPrompt(false);
        assertThat(systemOutRule.getLog(),not(containsString("Delete Animal")));
    }

    @Test
    /**
     * mainMenuPrompt should include List Animals when provided True
     */
    public void mainMethodPromptIncludesListAnimalsWhenProvidedTrue(){
        menuService.mainMenuPrompt(true);
        assertThat(systemOutRule.getLog(),containsString("List Animals"));
    }

    @Test
    /**
     * mainMenPrompt should include Delete Animal when provided True
     */
    public void mainMenuPromptIncludesDeleteAnimalWhenProvidedTrue(){
        menuService.mainMenuPrompt(true);
        assertThat(systemOutRule.getLog(),containsString("Delete Animal"));
    }

    @Test
    /**
     * userSelectionPrompt should display correct prompt
     */
    public void userSelectionPromptDisplaysCorrectPrompt(){
        menuService.userSelectionPrompt(true);
        assertThat(systemOutRule.getLog(),containsString("Please choose an option: "));
    }




}



