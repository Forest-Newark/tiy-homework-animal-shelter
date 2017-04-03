package com.forestnewark;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;


import java.io.IOException;


import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.AllOf.allOf;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;

/**
 * Created by forestnewark on 3/28/17.
 */
public class MainTest {

    //Create rule to enable console data input
    @Rule
    public final TextFromStandardInputStream systemInMock
            = emptyStandardInputStream();

    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();


    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();


    @Test
    /*
    ensure that the main method is created correctly (IE not null)
     */
    public void test(){

        Main main = new Main();
        assertThat(main, notNullValue());
    }
    @Test
    /*
    Enter as guest and exit the program
     */
    public void guestLoginAndExit() throws IOException {
        exit.expectSystemExit();
    String[] args = null;
    systemInMock.provideLines("1","3","y");
    Main.main(args);

    }


    @Test
    /*
    Enter as admin and check for animal that does not exist, creates the animal,
     */
    public void adminLoginAnimalSearchAnimalAdd() throws IOException {
        exit.expectSystemExit();
        String[] args = null;
        systemInMock.provideLines(
                "2",
                "admin","admin",
                "2","Timothy","q",
                "3","Timothy","Bear","Polar","Super Cool",
                "6","y");
        Main.main(args);
        assertThat(systemOutRule.getLog(),allOf(containsString("You Created the following animal!"),
                containsString("No animal matched search criteria")
        ));

    }


    @Test
    /*
    Enter as admin, check that an animal exist, delete that animal and and exit
     */
    public void adminLoginAnimalDelete() throws IOException {
        exit.expectSystemExit();
        String[] args = null;
        systemInMock.provideLines(
                "2",
                "admin","admin",
                "2","Sasha",
                "5","Sasha","y",
                "2","Sasha","q",
                "6","y"

        );
        Main.main(args);
        assertThat(systemOutRule.getLog(),allOf(
                containsString("Tabby"),
                containsString("Are you sure you want to delete Sasha"),
                containsString("No animal matched search criteria")
        ));

    }



    @Test
    /*
    Enter as admin, edit current animal information, exit the program
     */
    public void adminLoginEditAnimal() throws IOException {
        exit.expectSystemExit();
        String[] args = null;
        systemInMock.provideLines(
                "2",
                "admin","admin",
                "4","Amber",
                "Amber","Dog","Doberman","Super Sweet",
                "2","Amber",
                "6","y"

        );
        Main.main(args);
        assertThat(systemOutRule.getLog(),allOf(
                containsString("Golden Retriever"),
                containsString("Doberman")

        ));

    }

}
