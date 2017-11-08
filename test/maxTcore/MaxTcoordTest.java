/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maxTcore;

import java.text.ParseException;
import java.util.Collection;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Penny Williason
 */
public class MaxTcoordTest
{

    public MaxTcoordTest()
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
     * Test of addMilk method, of class MaxTcoord. Cow already contains Milk
     * takings, so testing that it fails.
     */
    @Test
    public void testFalseAddMilk()
    {
        System.out.println("addMilk");
        int am = 0;
        int pm = 0;
        Cow aCow = new Cow("7699");
        MaxTcoord coord = new MaxTcoord();
        coord.addMilk(am, pm, aCow);
        boolean expResult = false;
        boolean result = coord.addMilk(am, pm, aCow);
        assertEquals(expResult, result);

    }

    /**
     * Test of addMilk method, of class MaxTcoord. Cow has no milk takings, so
     * should return true.
     */
    @Test
    public void testTrueAddMilk()
    {
        System.out.println("addMilk");
        int am = 0;
        int pm = 0;
        Cow aCow = new Cow("7699");
        MaxTcoord coord = new MaxTcoord();
        boolean expResult = true;
        boolean result = coord.addMilk(am, pm, aCow);
        assertEquals(expResult, result);

    }

    /**
     * Test of deleteCow method, of class MaxTcoord. Tests that a cow cannot be
     * deleted if it does not exist in herd.
     */
    @Test
    public void testFalseDeleteCow()
    {
        System.out.println("deleteCow");
        Herd aHerd = new Herd("8978", "TestHerd", "8_16");
        String cowId = "";
        MaxTcoord coord = new MaxTcoord();
        boolean expResult = false;
        boolean result = coord.deleteCow(aHerd, cowId);
        assertEquals(expResult, result);
    }

    /**
     * Test of deleteCow method, of class MaxTcoord. Tests that a cow cannot be
     * deleted if it does not exist in herd.
     */
    @Test
    public void testTrueDeleteCow()
    {
        System.out.println("deleteCow");
        Herd aHerd = new Herd("8978", "TestHerd", "8_16");
        String cowId = "123";
        MaxTcoord coord = new MaxTcoord();
        boolean expResult = true;
        coord.addCow("123", aHerd);
        boolean result = coord.deleteCow(aHerd, cowId);
        assertEquals(expResult, result);
    }

    /**
     * Test of deleteFarm method, of class MaxTcoord. Tests that a farm can be
     * deleted.
     */
    @Test
    public void testTrueDeleteFarm()
    {
        System.out.println("deleteFarm");
        String farmName = "TestFarm";
        MaxTcoord coord = new MaxTcoord();
        coord.addFarm("789", "TestFarm", "Nowhere");
        boolean expResult = true;
        boolean result = coord.deleteFarm(farmName);
        assertEquals(expResult, result);
    }

    /**
     * Test of deleteFarm method, of class MaxTcoord. Tests that a farm can not
     * be deleted if it does not exist
     */
    @Test
    public void testFalse1DeleteFarm()
    {
        System.out.println("deleteFarm");
        String farmName = "TestFarm";
        MaxTcoord coord = new MaxTcoord();
        boolean expResult = false;
        boolean result = coord.deleteFarm(farmName);
        assertEquals(expResult, result);
    }

    /**
     * Test of deleteFarm method, of class MaxTcoord. Tests that a farm can not
     * be deleted if contains a herd
     */
    @Test
    public void testFalse2DeleteFarm()
    {
        System.out.println("deleteFarm");
        String farmName = "TestFarm";
        MaxTcoord coord = new MaxTcoord();
        Farm aFarm = new Farm("789", "TestFarm", "Nowhere");
        coord.addHerd("123", "TestHerd", aFarm,
                "8_16");
        boolean expResult = false;
        boolean result = coord.deleteFarm(farmName);
        assertEquals(expResult, result);
    }
    





}
