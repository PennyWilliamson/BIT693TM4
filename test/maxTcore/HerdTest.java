/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maxTcore;

import java.util.Collection;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author owner
 */
public class HerdTest
{
    
    public HerdTest()
    {
    }
    
    @BeforeClass
    public static void setUpClass()
    {
    }
    
    @AfterClass
    public static void tearDownClass()
    {
    }
    
    @Before
    public void setUp()
    {
    }
    
    @After
    public void tearDown()
    {
    }

 
    /**
     * Test of getMilkInt method, of class Herd.
     */
    @Test
    public void testGetMilkInt()
    {
        System.out.println("getMilkInt");
        Herd aHerd = new Herd("1", "11", "8_16");
        String expResult = "8_16";
        String result = aHerd.getMilkInt();
        assertEquals(expResult, result);
    }
   

    
    /**
     * Test of getAverage method, of class Herd.
     */
    @Test
    public void testGetAverage1()
    {
        System.out.println("getAverage");
        Herd aHerd = new Herd("1", "11", "8_16");
        Cow aCow = new Cow("a");
        Cow bCow = new Cow("b");
        aHerd.addCow(aCow);
        aHerd.addCow(bCow);
        String expResult = "0";
        String result = aHerd.getAverage();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getAverage method, of class Herd.
     */
    @Test
    public void testGetAverage2()
    {
        System.out.println("getAverage");
        Herd aHerd = new Herd("1", "11", "8_16");
        Cow aCow = new Cow("a");
        Cow bCow = new Cow("b");
        aHerd.addCow(aCow);
        aHerd.addCow(bCow);
        aCow.addMilkTakings(12, 7);
        bCow.addMilkTakings(13, 8);
        String expResult = "20";
        String result = aHerd.getAverage();
        assertEquals(expResult, result);
    }
}
