/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maxTcore;

import java.util.*;

/**
 * A class for creating a cow objects milkTakings objects
 *
 * @author Penny Williamson
 */
public class MilkTakings
{

    private int am;//declares milk takings am variable
    private int pm;//declares milk takings pm variable

    public MilkTakings(int aAm, int aPm)
    {
        am = aAm;//sets am variable to pm variable
        pm = aPm;//sets pm variable to am variable
    }

    /**
     * Gets a milkTakings objects am variable.
     *
     * @return int am
     */
    int getAm()
    {
        return am;
    }

    /**
     * Gets a milkTakings objects pm variable
     *
     * @return int pm
     */
    int getPm()
    {
        return pm;
    }

}
