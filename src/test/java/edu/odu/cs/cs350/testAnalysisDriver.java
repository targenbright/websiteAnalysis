package edu.odu.cs.cs350;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;

public class testAnalysisDriver {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void setUp() throws Exception
    {
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void testValidURL() throws IOException
    {
        // Needs a valid website path in the future
        AnalysisDriver.main(new String[] {"/home/user/testWebsite"});
        assertThat(outContent.toString(), not(containsString("***************~ERROR~***************")));
    }

    @Test
    public void testEmptyURL() throws IOException
    {
        AnalysisDriver.main(new String[] {});
        assertThat(outContent.toString(), containsString("You must specify a rootDirectory to analyze"));
    }

    @Test
    public void testInvalidURL() throws IOException
    {
        AnalysisDriver.main(new String[] {"al;sdfkjas;dlfkj"});
        assertThat(outContent.toString(), containsString("Given rootDirectory does not exist"));
        //
    }
}
