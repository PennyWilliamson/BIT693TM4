/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maxTcore;

import java.util.ArrayList;
import java.util.Collection;

/**
 * A class for handling methods and variables associated with cow objects and
 * their creation.
 * Holds the collection of milkTakings for each cow.
 *
 * @author Penny Williamson
 */
public class Cow
{

    private String id;//declares variable for cows id
    Collection<MilkTakings> milkTakings;//declares cows milk takings collection

    /**
     * Constructor for cow objects
     *
     * @param anId - String
     */
    public Cow(String anId)
    {
        id = anId;//sets id variable to passed in variable
        milkTakings = new ArrayList<MilkTakings>();
        //sets objects milkTakings collection
    }

    /**
     * Gets a cow objects id
     *
     * @return String id
     */
    String getCowId()
    {
        return id;
    }

    /**
     * Adds milk takings to a cow objects collection
     *
     * @param aAm - int
     * @param aPm - int
     * @return boolean
     */
    boolean addMilkTakings(int aAm, int aPm)
    {
        MilkTakings milk = new MilkTakings(aAm, aPm);
        //sets new milkTakings object
        if (milkTakings.isEmpty())//checks if a cow does not contain milkTakings
        {
            milkTakings.add(milk);//adds milkTakings object if it is empty
            return true;//returns true
        }
        else//fires if milk takings already contains an object
        {
            return false;//returns false
        }
    }

    /**
     * Deletes a cow objects milkTakings
     *
     * @return boolean
     */
    boolean deleteMilkTakings()
    {
        if (milkTakings.size() > 0)//fires if collection contains a milk object
        {
            milkTakings.clear();//clears the collection
            return true;//returns true and exits the method
        }
        return false;//returns false if collection is empty
    }

    /**
     * Gets the total amount of milk for a cow. Returns am + pm
     *
     * @return int totalMilk
     */
    int getTotalMilk()
    {
        int totalMilk;//variable for return
        int eve = 0;//variable for morning milk taking
        int morn = 0;//variable for evening milk taking
        for (MilkTakings milk : milkTakings)//iterates over collection
        {
            eve = milk.getPm();//sets variable to return
            morn = milk.getAm();//sets variable to return
        }
        totalMilk = eve + morn;//sets return variable to total of eve and morn
        return totalMilk;//returns int   
    }
    
    /**
     * Gets the am milk taking value for a cow object.
     * @return int morn
     */
    int getAmMilk()
    {
        int morn = 0;
        for(MilkTakings milk : milkTakings)
        {//iterates over collection
         morn = milk.getAm();//sets variable to return
        }
        return morn;//returns int
    }
    
    /**
     * Gets the pm milk taking value for a cow object.
     * @return int eve
     */
    int getPmMilk()
    {
        int eve = 0;
        for(MilkTakings milk : milkTakings)
        {//iterates over collection
            eve = milk.getPm();//sets variable to return
        }
        return eve;//returns int
    }

}
