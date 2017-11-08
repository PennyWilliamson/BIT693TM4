/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maxTcore;

import java.util.*;
import java.io.*;

/**
 * The interface between the GUI and the rest of the core system. Holds the
 * collection of Farms for the system.
 *
 * @author Penny Williamson.
 */
public class MaxTcoord implements Serializable
{

    private Collection<Farm> farms;//holds the farm objects.
    private CalcMaxT maxT; //declaration for CalcMaxT constructor.

    /**
     * Constructor.
     */
    public MaxTcoord()
    {
        farms = new HashSet();//sets the farms hashset
        maxT = new CalcMaxT();//sets the CalcMaxT constructor
    }

    /**
     * Returns an unmodifiable copy of the farms collection
     *
     * @return unmodifiableCollection of farms
     */
    public Collection<Farm> getFarm()
    {
        return Collections.unmodifiableCollection(farms);
    }

    /**
     * Calls getHerd() method in Farm class, using passed in aFarm.
     *
     * @param aFarm - a Farm object
     * @return unmodifiableCollection of herds
     */
    public Collection<Herd> getHerd(Farm aFarm)
    {
        Collection<Herd> aHerd = aFarm.getHerd();
        return Collections.unmodifiableCollection(aHerd);
    }

    /**
     * Calls getCow()method in Herd class, using passed in aHerd.
     *
     * @param aHerd - a Herd object
     * @return unmodifiableCollection of cows
     */
    public Collection<Cow> getCow(Herd aHerd)
    {
        Collection<Cow> aCow = aHerd.getCow();
        return Collections.unmodifiableCollection(aCow);
    }

    /**
     * Calls getName() method in Farm class, using passed in aFarm.
     *
     * @param aFarm - a Farm object
     * @return String name
     */
    public String getFarmName(Farm aFarm)
    {
        return aFarm.getName();
    }

    /**
     * Calls getHerdName() in Farm class, using passed in aFarm, in order to
     * obtain the name of a herd from that aFarms' herd collection.
     *
     * @param aFarm - a Farm object
     * @param aHerd - a Herd object
     * @return String name
     */
    public String getHerdName(Farm aFarm, Herd aHerd)
    {
        return aFarm.getHerdName(aHerd);
    }

    /**
     * Calls getCowId() in Herd class, using passed in aHerd, in order to obtain
     * the id of a cow from aHerds' cow collection.
     *
     * @param aHerd - a Herd object
     * @param aCow - a Cow object
     * @return String ID
     */
    public String getCowID(Herd aHerd, Cow aCow)
    {
        return aHerd.getCowID(aCow);
    }

    /**
     * Adds a farm to farms collection, if it does not already contain a farm of
     * the same name.
     *
     * @param anId - a String
     * @param aName - a String
     * @param aLocation - a String
     * @return boolean
     */
    public boolean addFarm(String anId, String aName, String aLocation)
    {
        Farm aFarm = new Farm(anId, aName, aLocation);
        if (farms.isEmpty())//adds first farm added.
        {
            farms.add(aFarm);
            return true;//exits method returns true.
        }
        if (farms.size() > 0)//checks farms if it contains farm
        {
            for (Farm bFarm : farms)//iterates over farms
            {
                if (anId.equals(bFarm.getID()))//checks the id is the same
                {
                    return false;//exits method, returns false.
                }
            }
            farms.add(aFarm);//adds farm if it is a unique name
            return true;//exits method, returns true
        }
        return false;//default return if rest of method does not fire.

    }

    /**
     * Adds a herd to aFarm objects collection
     *
     * @param anId - a String
     * @param aName - a String
     * @param afarm - a Far object
     * @param aMilkInt - a String
     * @return boolean
     */
    public boolean addHerd(String anId, String aName, Farm afarm,
            String aMilkInt)
    {
        Herd aHerd = new Herd(anId, aName, aMilkInt);//creates a herd object
        boolean b = afarm.addHerd(aHerd);
        //passes object in method to afarm's addHerd method.
        //Sets return boolean to result.
        return b;
    }

    /**
     * Adds a cow object to aHerd objects collection
     *
     * @param anId - a String
     * @param aHerd - a Herd object
     * @return boolean
     */
    public boolean addCow(String anId, Herd aHerd)
    {
        Cow aCow = new Cow(anId);//creates a new cow object
        boolean b = aHerd.addCow(aCow);
        //adds cow object to aHerd objects collection.
        //sets return boolean to return form method
        return b;
    }

    /**
     * Adds a MilkTakings to a cow objects collection
     *
     * @param am - an int
     * @param pm - an int
     * @param aCow - a Cow object
     * @return boolean
     */
    public boolean addMilk(int am, int pm, Cow aCow)
    {
        boolean b = aCow.addMilkTakings(am, pm);
        //sets aCow objects Milktakings and sets return boolean to result
        return b;
    }

    /**
     * Removes a cow object form a herd objects collection
     *
     * @param aHerd - a Herd object
     * @param cowId - a String
     * @return boolean
     */
    public boolean deleteCow(Herd aHerd, String cowId)
    {
        boolean b = aHerd.deleteCow(cowId);
        //Sets boolean to return
        return b;//returns boolean
    }

    /**
     * Removes a herd object from a Farm objects collection
     *
     * @param aFarm - a Farm object
     * @param herdName - a String
     * @return boolean
     */
    public boolean deleteHerd(Farm aFarm, String herdName)
    {
        boolean b = aFarm.deleteHerd(herdName);
        //sets boolean to return from deleteHerd
        return b;//returns boolean
    }

    /**
     * Deletes a farm object from collection
     *
     * @param farmName - a String
     * @return boolean
     */
    public boolean deleteFarm(String farmName)
    {
        for (Farm aFarm : farms)//iterates over farms
        {
            String name = aFarm.getName();//sets temporary variable to aFarm name
            if (farmName.equals(name))//checks names equal and fires if true
            {
                Collection<Herd> h = aFarm.getHerd();//gets a herd collection copy
                if (h.isEmpty())//fires if herd is empty
                {
                    farms.remove(aFarm);//removes the passed in farm from collection
                    return true;//returns true and exits method
                }
            }
        }
        return false;
        //returns if farm is empty, or farm object has herds in collection
    }

    /**
     * Deletes a cow objects milk takings
     *
     * @param aCow - a Cow object
     * @return boolean
     */
    public boolean deleteMilkTakings(Cow aCow)
    {
        boolean b = aCow.deleteMilkTakings();
        //sets boolean to return.
        return b;//returns boolean
    }

    /**
     * Gets the maxT time for morning and evening milking. Comes back in an
     * array, evening then morning for splitting into text field on GUI.
     *
     * @param aHerd - a Herd object
     * @return String[] times
     */
    public String[] getMaxT(Herd aHerd)
    {
        String[] times = null;//array for return
        String average = aHerd.getAverage();//sets variable to to return
        String milkInt = aHerd.getMilkInt();//setd variable to return
        try//as parsining to an int
        {
            int aveInt = Integer.parseInt(average);
            if (aveInt > 19 && aveInt < 26)//checks average is in range of jTable
            {
                if (milkInt.equals("8_16"))//fires if milking interval is 8 16
                {
                    times = maxT.getData8_16(average);//sets array to return
                }
                if (milkInt.equals("9_15"))//fires if milking interval is 9 15.
                {
                    times = maxT.getData9_15(average);//sets array to return
                }
                return times;//returns array
            }
            else
            {
                times = new String[2];
                times[0] = "n/a";
                times[1] = "n/a";
            }
        }
        catch (NumberFormatException e)
        {
            System.out.println(e);
        }
        return times;
    }

    /**
     * Gets the average milk yield, per cow, for a herd.
     *
     * @param aHerd - a Herd object
     * @return String average
     */
    public String getAverageYield(Herd aHerd)
    {
        String average = aHerd.getAverage();//sets varibale to return
        return average;//returns String
    }

    /**
     * Adds the data for milking interval 8, 16 to nested HashMap i8_16 in
     * CalcMaxT class.
     *
     * @param yield - a String
     * @param value - a Map
     */
    public void addData8_16(String yield, Map value)
    {
        maxT.addData8_16(yield, value);
    }

    /**
     * Adds the data for milking interval 9, 15 to nested HashMap i9_15 in
     * CalcMaxT class.
     *
     * @param yield - a String
     * @param value - a Map
     */
    public void addData9_15(String yield, Map value)
    {
        maxT.addData9_15(yield, value);
    }

    /**
     * Gets the am milk taking values for a cow object
     *
     * @param aCow - Cow object
     * @return int
     */
    public int getAmMilk(Cow aCow)
    {
        return aCow.getAmMilk();
    }

    /**
     * Gets the am milk takings for a cow object
     *
     * @param aCow - Cow object
     * @return int
     */
    public int getPmMilk(Cow aCow)
    {
        return aCow.getPmMilk();
    }
}
