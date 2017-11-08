/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maxTcore;

import java.util.Collection;
import java.util.HashSet;

/**
 * A class for handling collections, variables and methods associated with herd
 * objects and their creation. Holds the collection of cows for each herd.
 *
 * @author Penny Williamson
 */
public class Herd
{

    private Collection<Cow> cows;//Declares cow collection
    private String id;//Declares herd objects id variable
    private String name;//Declares herd objects name variable
    private String milkInt;//Declares herd objects milkInt(erval) variable

    /**
     * Constructor for herd
     *
     * @param anId - String
     * @param aName - String
     * @param aMilkInt - String
     */
    public Herd(String anId, String aName, String aMilkInt)
    {
        id = anId;//sets variable id to passed in variable
        name = aName;//sets variable name to passed in variable
        milkInt = aMilkInt;//sets variable milkInt to passed in variable
        cows = new HashSet();//sets objects cows Hashset
    }

    /**
     * Gets herd objects name
     *
     * @return String name
     */
    String getHerdName()
    {
        return name;
    }

    /**
     * Gets herd objects id
     *
     * @return String id
     */
    String getHerdId()
    {
        return id;
    }

    /**
     * Gets cow object held in a herd objects collections id
     *
     * @param aCow - a Cow object
     * @return String id
     */
    String getCowID(Cow aCow)
    {
        return aCow.getCowId();
    }

    /**
     * Gets a herd objects collection of cows
     *
     * @return collection cows
     */
    Collection<Cow> getCow()
    {
        return cows;
    }

    /**
     * Gets a herds milking interval
     *
     * @return String milkInt
     */
    String getMilkInt()
    {
        return milkInt;
    }

    /**
     * Adds a cow object to a herd objects collection, if the collection
     * contains no cows with the same id
     *
     * @param aCow - a Cow object
     * @return boolean
     */
    boolean addCow(Cow aCow)
    {
        if (cows.isEmpty())//fires if collection is empty
        {
            cows.add((aCow));//adds the first cow in the collection
            return true;//returns true and exits method
        }
        if (cows.size() > 0)//fires is collection is not empty
        {
            for (Cow bCow : cows)//iterates over cow objects in collection
            {
                if (aCow.getCowId().equals(bCow.getCowId()))
                //fires if a cow object in the collection has the same id as 
                //the passed in cow
                {
                    return false;//returns false and exits the method
                }
            }
            cows.add(aCow);
            //adds a cow if the collection is not empty, and contains no cows 
            //with the same id
            return true;//returns true and exits the method
        }
        return false;//returns false if loops fail to fire
    }

    /**
     * Deletes a cow object from a herd objects collection
     *
     * @param cowId - String
     * @return boolean
     */
    boolean deleteCow(String cowId)
    {
        if (cows.size() > 0)//fires if collection contains cow objects
        {
            for (Cow aCow : cows)//iterates over a herd objects cows collection
            {
                String id = aCow.getCowId();//get the id of aCow
                if (id.equals(cowId))//fires if id of a cow equals passed in id
                {
                    cows.remove(aCow);//removes cow object form collection
                    return true;//returns true and exits method
                }
            }
        }
        return false;
        //returns false if collection is empty or a cow is not found in collecton
    }

    /**
     * Returns the herds average milk yield for a day, as a string.
     *
     * @return String average
     */
    String getAverage()
    {
        int herdTotal = 0;//sets int to hold running total
        if (cows.size() != 0)//check, so that not dividing by 0
        {
            for (Cow aCow : cows)//iterates over collection
            {
                int cowTotal = aCow.getTotalMilk();//sets varibale to return
                if (cowTotal > 0)//does not add cows with no milkTakings
                {
                    herdTotal = herdTotal + cowTotal;
                    //sets herdTotal to current total plus aCow's total milk yeild
                }
            }

            int herdAve = (herdTotal / cows.size());
            //sets variable to average milk yield for a herd.
            String average = Integer.toString(herdAve);//set int variable to string
            return average;//returns string.
        }
        return "0";//returns if herd has no cows
    }
}
