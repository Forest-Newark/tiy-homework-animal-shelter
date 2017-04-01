package com.forestnewark;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;


import java.io.IOException;


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
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();

    @Test
    /*
    Enter as guest and exit the program
     */
    public void test() throws IOException {
        exit.expectSystemExit();
    String[] args = null;
    systemInMock.provideLines("1","3","Y");
    Main.main(args);



    }

}
