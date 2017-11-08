/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maxTcore;

import java.util.*;

/**
 * Handles the HashMaps for milking interval milk yields. And the calculation
 * and return of MaxT time values, for a herd.
 *
 * @author Penny Williamson
 */
public class CalcMaxT
{

    private Map<String, Map> i8_16;
    private Map<String, Map> i9_15;

    /**
     * Constructor. Sets and holds two HashMaps, one for each milking interval
     * (8, 16 and 9, 15) from GUI JTable)
     */
    public CalcMaxT()
    {
        //sets a new hashmaps to hold values entered into JTbale on GUI.
        //Both hold a string yield value, and a map of the two corrosponding yields.
        //ie <"20"<"17", "13">
        i8_16 = new HashMap<String, Map>();
        i9_15 = new HashMap<String, Map>();

    }

    /**
     * Adds to data from signature into i8_16 nested HashMap.
     *
     * @param yield - String
     * @param row - Map
     */
    void addData8_16(String yield, Map<String, String> row)
    {
        i8_16.put(yield, row);
    }

    /**
     * Adds to data from signature into i9_15 nested HashMap.
     *
     * @param yield - String
     * @param row -Map
     */
    void addData9_15(String yield, Map row)
    {
        i9_15.put(yield, row);
    }

    /**
     * Gets the MaxT times for a herd with Milking Interval 8, 16
     *
     * @param average - String
     * @return String[] times
     */
    String[] getData8_16(String average)
    {
        String[] times = new String[2];//String array for return
        String evening = null;//variable for evening vaules from internal HashMap
        String morning = null;//variable for morning vaules from internal HashMap
        for (Map.Entry<String, Map> amount : i8_16.entrySet())//iterates over i8_16 HashMap
        {
            String yield = amount.getKey();//sets variable to key
            Map<String, String> row = amount.getValue();
            //sets a new map to the amount map value, to iterate over internal HashMap.
            if (average.equals(yield))//fires if average equals yield
            {
                for (Map.Entry<String, String> internal : row.entrySet())
                {//iterates over and gets keys and values from the row
                    evening = internal.getKey();
                    morning = internal.getValue();
                }
            }
        }
        String pmTime = lookUpMaxT(evening);//sets variable to return
        String amTime = lookUpMaxT(morning);//sets variable to return
        times[0] = pmTime;//adds variable to array
        times[1] = amTime;//adds variable to array
        return times;//returns array
    }

    /**
     * Gets the MaxT times for a herd with Milking Interval 8, 16
     *
     * @param average - String
     * @return String[] times
     */
    String[] getData9_15(String average)
    {
        String[] times = new String[2];//String array for return
        String evening = null;//variable for evening vaules from internal HashMap
        String morning = null;//variable for morning vaules from internal HashMap
        for (Map.Entry<String, Map> amount : i9_15.entrySet())//iterates over i9_15 HashMap
        {
            String yield = amount.getKey();//sets variable to key
            Map<String, String> row = amount.getValue();
            //sets a new map to the amount map value, to iterate over internal HashMap.
            if (average.equals(yield))//fires if average equals yield
            {
                for (Map.Entry<String, String> internal : row.entrySet())
                {//iterates over and gets keys and values from the row
                    evening = internal.getKey();
                    morning = internal.getValue();
                }
            }
        }
        String pmTime = lookUpMaxT(evening);//sets variable to return
        String amTime = lookUpMaxT(morning);//sets variable to return
        times[0] = pmTime;//adds variable to array
        times[1] = amTime;//adds variable to array
        return times;//returns array
    }

    /**
     * Returns the maxT time value for a given milk volume. Exits method once
     * given volume has been matched.
     *
     * @param milk - String
     * @return String of time value
     */
    private String lookUpMaxT(String milk)
    {
        if (milk.equals("7"))
        {
            return "04:51";
        }
        if (milk.equals("8"))
        {
            return "05:20";
        }
        if (milk.equals("9"))
        {
            return "05:48";
        }
        if (milk.equals("10"))
        {
            return "06:15";
        }
        if (milk.equals("11"))
        {
            return "06:42";
        }
        if (milk.equals("12"))
        {
            return "07:07";
        }
        if (milk.equals("13"))
        {
            return "07:32";
        }
        if (milk.equals("14"))
        {
            return "07:57";
        }
        if (milk.equals("15"))
        {
            return "08:21";
        }
        if (milk.equals("16"))
        {
            return "08:44";
        }

        return "n/a";//returns if milk does not match values.
    }
}
