/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jacoco.examples.java.gradle;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Никита
 */
public class GreetingTest {
    
    public GreetingTest() {
    }
    
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setUpStream() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void cleanUpStream() {
        System.setOut(null);
    }

     /**
     * Test of main method, of class Greeting.
     */
    @Test
    public void testConsoleOutputGreet() {
        Greeting instance = new Greeting();
        instance.greet();
        // TODO review the generated test code and remove the default call to fail.
        assertEquals("Unexpected result", "Hello from .java!", outContent.toString());
    }
    
}
