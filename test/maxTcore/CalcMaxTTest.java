/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maxTcore;

import java.util.HashMap;
import java.util.Map;
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
public class CalcMaxTTest
{
    
    public CalcMaxTTest()
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
     * Test of getData8_16 method, of class CalcMaxT.
     */
    @Test
    public void testGetData8_16()
    {
        System.out.println("getData8_16");
        CalcMaxT maxT = new CalcMaxT();
        Map<String, String> values = new HashMap<String, String>();
        values.put("7", "13");
        maxT.addData8_16("20", values);
        String[] expResult = new String[2];
        expResult[0] = "04:51";
        expResult[1] = "07:32";
        String[] result = maxT.getData8_16("20");
        assertArrayEquals(expResult, result);

    }


}
