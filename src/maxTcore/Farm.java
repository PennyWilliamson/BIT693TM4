/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maxTcore;

import java.util.Collection;
import java.util.HashSet;
import java.util.*;

/**
 * A class for handling farm objects creation, and associated variables and
 * methods.
 * Holds the collection of herds for each farm.
 *
 * @author Penny Williamson
 */
public class Farm implements java.io.Serializable
{

    private String id;// declared for farms id variable
    private String name;// declared for farms name variable
    private String location;// declared for farms location variable
    private Collection<Herd> herds;// declared for farms herd collection

    /**
     * Constructor for farm.
     *
     * @param anId - String
     * @param aName - String
     * @param aLocation - String
     */
    public Farm(String anId, String aName, String aLocation)
    {
        id = anId;//sets id varaible to passed in variable
        name = aName;//sets name varaible to passed in variable
        location = aLocation;//sets location varaible to passed in variable
        herds = new HashSet();//sets herds collection to a new HashSet
    }

    /**
     * Returns a farms id
     *
     * @return String id
     */
    String getID()
    {
        return id;
    }

    /**
     * Returns a farms name
     *
     * @return String name
     */
    String getName()
    {
        return name;
    }

    /**
     * Returns a herd objects, held in a farms collection, name
     *
     * @param aHerd - a Herd object
     * @return String herd name
     */
    String getHerdName(Herd aHerd)
    {
        return aHerd.getHerdName();
    }

    /**
     * Adds a herd to a farm objects collection, if a farm object does not
     * already hold a herd of the same name.
     *
     * @param aHerd - a Herd object
     * @return boolean
     */
    boolean addHerd(Herd aHerd)
    {
        if (herds.isEmpty())//fires if herds is empty
        {
            herds.add(aHerd);//adds first herd object to collection
            return true;//returns true and exits method
        }
        if (herds.size() > 0)//fires if herd already contains herd objects
        {
            for (Herd bHerd : herds)//iterates over farms herd collection
            {
                if (aHerd.getHerdId().equals(bHerd.getHerdId()))
                {//fires if a herd object in collection has the same id
                    return false;//returns false and exits method
                }
            }
            herds.add(aHerd);
            //if no herd with the same name is found, a new herd object is 
            //added to herds collection
            return true;//returns true and exits method
        }
        return false;//returns false if above loops fail to fire.
    }

    /**
     * Returns farm objects herd collection
     *
     * @return Collection herds
     */
    Collection<Herd> getHerd()
    {
        return herds;
    }

    /**
     * Deletes a herd form farm objects herd collection
     *
     * @param herdName - String
     * @return boolean
     */
    boolean deleteHerd(String herdName)
    {
        for (Herd aHerd : herds)//iterates over farm objects her collection
        {
            String name = aHerd.getHerdName();//sets temporary name variable
            if (herdName.equals(name))//fires if a herd with the same name is found
            {
                Collection<Cow> c = aHerd.getCow();//gets a copy of that hreds cow collection
                if (c.isEmpty())//fires if collection is empty
                {
                    herds.remove(aHerd);//removes a herd if it has no cows
                    return true;//returns true and exits method
                }
            }
        }
        return false;
        //returns if herd name is not found, or herd contains cows.
    }

}
