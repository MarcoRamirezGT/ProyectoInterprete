/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author oscar
 */
public class SexpTest {
    
    public SexpTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of two method, of class Sexp.
     */
    @Test
    public void testTwo() {
        System.out.println("two");
        Sexp instance = null;
        Sexp expResult = null;
        Sexp result = instance.two();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of three method, of class Sexp.
     */
    @Test
    public void testThree() {
        System.out.println("three");
        Sexp instance = null;
        Sexp expResult = null;
        Sexp result = instance.three();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of four method, of class Sexp.
     */
    @Test
    public void testFour() {
        System.out.println("four");
        Sexp instance = null;
        Sexp expResult = null;
        Sexp result = instance.four();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of bad method, of class Sexp.
     */
    @Test
    public void testBad() {
        System.out.println("bad");
        Sexp instance = null;
        boolean expResult = false;
        boolean result = instance.bad();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toS method, of class Sexp.
     */
    @Test
    public void testToS() {
        System.out.println("toS");
        Sexp instance = null;
        String expResult = "";
        String result = instance.toS();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
