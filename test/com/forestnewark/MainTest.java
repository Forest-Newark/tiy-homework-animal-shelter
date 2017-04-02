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
    Enter as guest and exit the program
     */
    public void guestLoginAndExit() throws IOException {
        exit.expectSystemExit();
    String[] args = null;
    systemInMock.provideLines("1","3","Y");
    assertThat(systemOutRule.getLog(),allOf(
            containsString("Guest"),
            containsString("Are you sure you want to quit? "),
            containsString("Goodbye")));
    Main.main(args);

    }



    @Test
    /*
    Enter as admin and check for animal that does not exist, creates the animal
     */
    public void adminLoginAnimalSearchAnimalAdd() throws IOException {

        String[] args = null;
        systemInMock.provideLines(
                "2",
                "admin","admin",
                "2","Timothy",
                 "3","Timothy","Bear","Polar","Super Cool",
                "2");
        Main.main(args);
        assertThat(systemOutRule.getLog(),allOf(
                containsString("Timothy"),
                containsString("Bear"),
                containsString("Polar"),
                containsString("Super Cool")
                )
        );

    }




}
