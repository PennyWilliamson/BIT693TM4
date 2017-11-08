/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MaxTGui;

import java.util.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import maxTcore.*;//Allows MaxTcood to be accessed.

/**
 *
 * @author owner
 */
public class CalculateMaxTGUI extends javax.swing.JFrame
{

    private int previous = 0;//Holds index int variable for back buttons.
    private MaxTcoord coord;// declares MaxTcoord object
    private List<String> farmNames;// declares list to hold the names of farms for display
    private Collection<Farm> farm;// declares collection to hold farms unmodifiable collection
    private Collection<Herd> herd;// declares collection to hold herds unmodifiable collection
    private Collection<Cow> cow;// declares collection to hold cows unmodifiable collection
    private List<String> herdNames;// declares list to hold the names of herds for display
    private List<String> cowName;// declares list to hold the names of cows for display
    private List<Farm> holdFarm;// declares list to hold the farm objects for indexing
    private List<Herd> holdHerd;// declares list to hold the herd objects for indexing
    private List<Cow> holdCow;// declares list to hold the cow objects for indexing
    private String milkInt;// declares string to hold radio button value for milking interval
    private int herdIndex;// declares int variable to hold selected herd index in lists
    private int farmIndex;// declares int varibale to hold selected farm index in lists
    private boolean tableFilled; //declares boolean for Table 1 done button
    private List<String> herdNamesMain;
    //declares list to hold herdNames and statistics for dislay on main

    /**
     * Creates new form CalculateMaxTGUI
     */
    public CalculateMaxTGUI()
    {
        initComponents();
        coord = new MaxTcoord();
        //sets an instance of MaxT coord to hold and use jTable input

        displayFarm();//allows gui to run. Sets farm lists
    }

    /**
     * Resets the displayed lists, by calling the display methods.
     */
    private void refresh()
    {
        displayFarm();
        displayHerd();
        displayCow();
        displayMainHerd();
    }

    /**
     * Displays the names of farm objects held in collection farms to the
     * appropriate lists.
     */
    private void displayFarm()
    {
        farm = coord.getFarm();// gets the unmodifiable collection
        holdFarm = new ArrayList<Farm>(farm);
        // sets the array for indexing objects from list selection.
        farmArray(farm); //sets the array of names
        editHerdFarmList.setListData(farmNames);//displays the ArrayList
        editHerdFarmList.setSelectedIndex(0);
        //sets the selected index to 0 (first item)
        editCowFarmMList.setListData(farmNames);
        editCowFarmMList.setSelectedIndex(0);
        editMilkFarmList.setListData(farmNames);
        editMilkFarmList.setSelectedIndex(0);
        maxTFarmList.setListData(farmNames);
        maxTFarmList.setSelectedIndex(0);
        mainFarmList.setListData(farmNames);
        mainFarmList.setSelectedIndex(0);
        editFarmFarmList.setListData(farmNames);
        editFarmFarmList.setSelectedIndex(0);
    }

    /**
     * Displays the names of herd objects held in collection herds to the
     * appropriate lists.
     */
    private void displayHerd()
    {
        if (farmIndex != -1)//only fires if a farm object is present.
        {
            Farm aFarm = (Farm) holdFarm.get(farmIndex);
            //gets the farm associated with the selected index           
            herd = coord.getHerd(aFarm);
            if (herd.size() > 0)
            {
                // sets the array for indexing objects from list selection.
                holdHerd = new ArrayList<Herd>(herd);
                herdArray(herd, aFarm);
                //sets the Arraylist of names of herds for a selected farm
                editCowHerdMList.setListData(herdNames);//displays the ArrayList
                editCowHerdMList.setSelectedIndex(0);
                //Sets the selected index to 0 (start of list)
                editMilkHerdList.setListData(herdNames);
                editMilkHerdList.setSelectedIndex(0);
                maxTHerdList.setListData(herdNames);
                maxTHerdList.setSelectedIndex(0);
                editHerdHerdList.setListData(herdNames);
                editHerdHerdList.setSelectedIndex(0);
            }
        }
    }

    /**
     * Displays the ids of cows held in the selected herds collection to the
     * appropriate list.
     */
    private void displayCow()
    {
        if (herdIndex != -1)// fires if there are herd names displayed
        {
            Herd aHerd = (Herd) holdHerd.get(herdIndex);
            // gets the herd associated with the selected index
            cow = coord.getCow(aHerd);
            if (cow.size() > 0)
            {
                //sets the ArrayList for indexing objects from selection
                holdCow = new ArrayList<Cow>(cow);
                cowArray(cow, aHerd);
                //sets the array list of cowIds to be displayed in lists
                editMilkCowList.setListData(cowName);//Displays the ArryList
                editMilkCowList.setSelectedIndex(0);
                //Sets the index for selected items to 0
                editCowCowList.setListData(cowName);
                editCowCowList.setSelectedIndex(0);
            }

        }
    }

    private void displayMainHerd()
    {
        if (farmIndex != -1)
        {
            Farm aFarm = (Farm) holdFarm.get(farmIndex);
            //gets the farm associated with the selected index
            herd = coord.getHerd(aFarm);
            if (herd.size() > 0)
            {
                // sets the array for indexing objects from list selection.
                holdHerd = new ArrayList<Herd>(herd);
                herdArrayMain(herd, aFarm);
                //sets the array list to be displayed.
                mainHerdList.setListData(herdNamesMain);//displays the arraylist
                mainHerdList.setSelectedIndex(0);//sets the selection index to 0
            }
        }
    }
    
    /**
     * Displays any milk takings the selected cow has.
     */
    private void displayMilkTakings()
    {
        int indexC = editMilkCowList.getSelectedIndex();//gets index of selected cow
        Cow aCow = holdCow.get(indexC);//gets cow object associated with index
        int am = coord.getAmMilk(aCow);//gets cow objects am milk takings
        int pm = coord.getPmMilk(aCow);//get cow objects pm milk takings
        if(am > 0 && pm > 0)//only displays if greater takings are greater than 0
        {
        editMilkTakingsAmTextField.setText(Integer.toString(am));
        editMilkTakingsPmTextField.setText(Integer.toString(pm));
        }
    }

    /**
     * Sets the ArrayList of farm names to be displayed.
     *
     * @param farm - a Collection
     */
    private void farmArray(Collection<Farm> farm)
    {
        farmNames = new ArrayList<String>();//sets array list to hold names
        for (Farm afarm : holdFarm)//iterates over the ArrayList of farm objects
        {
            String f = coord.getFarmName(afarm);
            //sets a temporary string to the name of afarm object
            farmNames.add(f);//adds name to ArrayList to be displayed.
        }

    }

    /**
     * Sets the ArrayList of herd names for a selected farm object to be
     * displayed
     *
     * @param herd - Collection
     * @param aFarm - a Farm object
     */
    private void herdArray(Collection<Herd> herd, Farm aFarm)
    {
        herdNames = new ArrayList<String>();//sets array list to hold names
        for (Herd aHerd : holdHerd)//iterates over the ArrayList of herd objects
        {
            String h = coord.getHerdName(aFarm, aHerd);
            //sets a temporary string to the name of aHerd object
            herdNames.add(h);//adds name to ArrayList to be displayed.
        }
    }

    /**
     * Sets the arrayList of herd names, MaxT times and statistics to be
     * displayed on the main page.
     *
     * @param herd - Collection
     * @param aFarm - a Farm object
     */
    private void herdArrayMain(Collection<Herd> herd, Farm aFarm)
    {
        herdNamesMain = new ArrayList<String>();
        for (Herd aHerd : holdHerd)
        {
            String n = coord.getHerdName(aFarm, aHerd);
            String[] mT = coord.getMaxT(aHerd);
            String s = Integer.toString(holdHerd.size());
            String ave = coord.getAverageYield(aHerd);
            String h = n + " , MaxT pm:" + mT[0] + " am:" + mT[1] + " Size:" + s
                    + "Average:" + ave;
            herdNamesMain.add(h);
        }
    }

    /**
     * Sets the ArrayList of cow ids for a selected herd to be displayed.
     *
     * @param cow - Collection
     * @param aHerd - a Herd object
     */
    private void cowArray(Collection<Cow> cow, Herd aHerd)
    {
        cowName = new ArrayList<String>();//sets array list to hold ids
        for (Cow aCow : holdCow)//iterates over the ArrayList of cow objects
        {
            String c = coord.getCowID(aHerd, aCow);
            //sets a temporary string to the id of aCow object
            cowName.add(c);//adds id to ArrayList to be displayed.
        }
    }

    /**
     * Adds a farm, unique by farm name. Alerts user if required fields have not
     * been filled in, then exits method.
     */
    private void addFarm()
    {
        //Alerts user by dislaying a pop up window and stops method if field is
        //blank
        if (editFarmIDTextField.getText().length() < 1)
        {
            JOptionPane.showMessageDialog(null,
                    "Please enter an Id and try again");
            return;
        }
        if (editFarmNameTextField.getText().length() < 1)
        {
            JOptionPane.showMessageDialog(null,
                    "Please enter a name and try again");
            return;
        }
        if (editFarmLocationTextArea.getText().length() < 1)
        {
            JOptionPane.showMessageDialog(null,
                    "Please enter a location and try again");
            return;
        }

        //sets variables from text fields
        String anId = editFarmIDTextField.getText();
        String aName = editFarmNameTextField.getText();
        String aLocation = editFarmLocationTextArea.getText();
        if (aName.trim().isEmpty() || aName.matches("[^a-zA-Z0-9]"))
        {//checks that name is only letters and/or numbers
            JOptionPane.showMessageDialog(null,
                    "Please enter a valid name and try again");
        }
        //passes variables in method call. Fires if add successful
        if (coord.addFarm(anId, aName, aLocation))
        {
            editFarmMessageTextArea.setText("Farm " + aName + " has been added");
            //resets text field to null. 
            //Message area kept so you know where you are upto.
            editFarmIDTextField.setText("");
            editFarmNameTextField.setText("");
            editFarmLocationTextArea.setText("");
            if (holdFarm.size() > 1)
            {
                refresh();// redisplays all the lists with new farm.
            }
            else
            {
                displayFarm();
            }
        }
        else//fires is add not successful. Displays fail message.
        {//text fields left populated so error can be corrected.
            JOptionPane.showMessageDialog(null,
                    "Farm " + aName + " could not be added");
        }

    }

    /**
     * Adds a herd, unique by herd name, to the selected farms herd collection.
     * Alerts users if fields are blank, and exits the method.
     */
    private void addHerd()
    {
        //Alerts user by displaying a pop up window and stops method if 
        //field is blank or not selected
        if (editHerdIDTextField.getText().length() < 1)
        {
            JOptionPane.showMessageDialog(null,
                    "Please enter an Id and try again");
            return;
        }
        if (editHerdNameTextField.getText().length() < 1)
        {
            JOptionPane.showMessageDialog(null,
                    "Please enter a name and try again");
            return;
        }
        if (editHerdFarmList.isSelectionEmpty())
        {
            JOptionPane.showMessageDialog(null,
                    "Please select a Farm and try again");
            return;
        }

        if (milkInt == null)//checks radio button has been selected
        {
            JOptionPane.showMessageDialog(null,
                    "Please select a milking interval and try again");
            return;
        }

        //sets variables to be passed to addHerd method
        String anId = editHerdIDTextField.getText();
        String aName = editHerdNameTextField.getText();
        String aMilkInt = milkInt;
        if (aName.trim().isEmpty() || aName.matches("[^a-zA-Z0-9]"))
        {//checks that name is only letters and/or numbers
            JOptionPane.showMessageDialog(null,
                    "Please enter a valid name and try again");
        }
        int index = editHerdFarmList.getSelectedIndex();
        Farm aFarm = (Farm) holdFarm.get(index);
        //gets farm object associated with selected index
        //passes variables in method, fires if addHerd returns true.
        if (coord.addHerd(anId, aName, aFarm, aMilkInt))
        {
            editHerdTextArea.setText("Herd " + aName + " added");
            //sets success message
            editHerdIDTextField.setText("");
            //sets fields back to empty
            editHerdNameTextField.setText("");
            refresh();//redisplays lists with new herd
        }
        else//Add returned false. Displays fail message
        {//text fields left populated so error can be corrected.
            JOptionPane.showMessageDialog(null,
                    "Herd " + aName + " could not be added");

        }
    }

    /**
     * Adds a new cow object to herds collection of cows, unique by cow id.
     * Checks fields are filled, if empty alerts user and exits method
     */
    private void addCow()
    {
        //pops up window alerting user to unselected lists and blank field,
        //and exits method.
        if (editCowIDTextField.getText().length() < 1)
        {
            JOptionPane.showMessageDialog(null,
                    "Please enter an Id and try again");
            return;
        }
        if (editCowHerdMList.isSelectionEmpty())
        {
            JOptionPane.showMessageDialog(null,
                    "Please select a herd and try again");
            return;
        }
        if (editCowFarmMList.isSelectionEmpty())
        {
            JOptionPane.showMessageDialog(null,
                    "Please select a Farm and try again");
            return;
        }

        //sets variables to be passed to addCow method
        int index = editCowHerdMList.getSelectedIndex();
        Herd aHerd = holdHerd.get(index);
        String anId = editCowIDTextField.getText();
        if(anId.trim().isEmpty() || anId.matches("[^a-zA-Z0-9]"))
        {//checks that name is only letters and/or numbers
            JOptionPane.showMessageDialog(null,
                    "Please enter a valid id and try again");
        }
        if (coord.addCow(anId, aHerd))//fires if addCow returns true.
        {
            editCowTextArea.setText("Cow " + anId + " added");
            //sets text area to succes method
            editCowIDTextField.setText("");//sets text field to blank
            refresh();//redisplays lists with new cow
        }
        else//fires if addCow returns false, displays fail message
        {//text fields left populated so error can be corrected.
            JOptionPane.showMessageDialog(null,
                    "Herd " + anId + " could not be added");
        }
    }

    /**
     * Adds the milking taking object to a cows collection. Parses string from
     * text field into int value. Checks all lists have a selection, and exits
     * method if not selected.
     *
     * @throws NumberFormatException
     */
    private void addMilkTakings() throws NumberFormatException
    {   //pops up window alerting user to unselected lists and blank field,
        //and exits method.
        if (editMilkHerdList.isSelectionEmpty())
        {
            JOptionPane.showMessageDialog(null,
                    "Please select a herd and try again");
            return;
        }
        if (editMilkFarmList.isSelectionEmpty())
        {
            JOptionPane.showMessageDialog(null,
                    "Please select a Farm and try again");
            return;
        }
        if (editMilkCowList.isSelectionEmpty())
        {
            JOptionPane.showMessageDialog(null,
                    "Please select a cow and try again");
            return;
        }
        if (editMilkTakingsAmTextField.getText().length() < 1)
        {
            JOptionPane.showMessageDialog(null,
                    "Please enter an AM milk taking and try again");
            return;
        }
        if (editMilkTakingsPmTextField.getText().length() < 1)
        {
            JOptionPane.showMessageDialog(null,
                    "Please enter a PM milk taking and try again");
            return;
        }

        //sets variables for addMilk method
        int index = editMilkCowList.getSelectedIndex();
        Cow aCow = holdCow.get(index);
        String anAm = editMilkTakingsAmTextField.getText();
        String aPm = editMilkTakingsPmTextField.getText();
        //variable declared and set outside try loop, 
        //so the can be accessed outside try loop
        int am = 0;
        int pm = 0;
        try //as parsing string to int
        {
            am = Integer.parseInt(anAm);
            pm = Integer.parseInt(aPm);
        }
        catch (NumberFormatException e)//fires if exception thrown
        {//pops up message dialog asking for number and exits method
            JOptionPane.showMessageDialog(null,
                    "Please enter a number and try again");
            return;
        }
        if (am > 0 && pm > 0)
        {
            if (coord.addMilk(am, pm, aCow))//fires if addMilk returns true
            {
                editMilkTakingsAmTextField.setText("");//blanks text fields
                editMilkTakingsPmTextField.setText("");
                editMilkTakingsTextArea.setText("Milk takings added/updated");
                //sets message area to success message
                displayMilkTakings();//updates the display for new values
            }
            else//fires if addMilk returns false, pops up message window
            {//Text fields left populated so mistake can be fixed
                JOptionPane.showMessageDialog(null,
                        "Did you wish to update this cows milk takings?");
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null,
                    "Please enter positive values");
        }
    }

    /**
     * Removes a cow from the selected herds collection.
     */
    private void deleteCow()
    {
        //pops up window alerting user to unselected lists and blank field,
        //and exits method.
        if (editCowIDTextField.getText().length() < 1)
        {
            JOptionPane.showMessageDialog(null,
                    "Please enter an Id and try again");
            return;
        }
        if (editCowHerdMList.isSelectionEmpty())
        {
            JOptionPane.showMessageDialog(null,
                    "Please select a herd and try again");
            return;
        }

        //sets variables for deleteCow method
        int indexH = editCowHerdMList.getSelectedIndex();
        Herd aHerd = holdHerd.get(indexH);
        int indexC = editCowCowList.getSelectedIndex();
        Cow aCow = holdCow.get(indexC);
        String cowId = coord.getCowID(aHerd, aCow);
        //asks if user wishes to delete cow
        int del = JOptionPane.showConfirmDialog(null,
                "Do you wish to delete cow " + cowId + "?", "",
                JOptionPane.YES_NO_OPTION);
        if (del == JOptionPane.YES_OPTION)//fires if user selects yes
        {
            if (coord.deleteCow(aHerd, cowId))//fires if delete cow returns true
            {
                editCowIDTextField.setText("");//sets textfield to blank
                editCowTextArea.setText("Cow " + cowId + " deleted");
                //sets text area to success message
                refresh();//redisplays lists without deleted cow
            }
            else//fires if deleteCow returns false
            {//text fields left populated to fix mistakes
                JOptionPane.showMessageDialog(null,
                    "Cow " + cowId + " could not be deleted");
            }
        }

    }

    /**
     * removes a herd form the selected farms collection. Only removes if the
     * selected herds collection of cows is empty.
     */
    private void deleteHerd()
    {
        //pops up window alerting user to unselected lists and blank field,
        //and exits method.
        if (editHerdNameTextField.getText().length() < 1)
        {
            JOptionPane.showMessageDialog(null,
                    "Please enter a name and try again");
            return;
        }
        if (editHerdFarmList.isSelectionEmpty())
        {
            JOptionPane.showMessageDialog(null,
                    "Please select a farm and try again");
            return;
        }

        //sets variables for deleteHerd method
        int indexF = editHerdFarmList.getSelectedIndex();
        Farm aFarm = holdFarm.get(indexF);
        int indexH = editHerdHerdList.getSelectedIndex();
        Herd aHerd = holdHerd.get(indexH);
        String herdName = coord.getHerdName(aFarm, aHerd);
        //pop up window asking if users wishes to delete herd
        int del = JOptionPane.showConfirmDialog(null,
                "Do you wish to delete herd " + herdName + "?", "",
                JOptionPane.YES_NO_OPTION);
        if (del == JOptionPane.YES_OPTION)
        {
            if (coord.deleteHerd(aFarm, herdName))
            //fires if deleteHerd returns true
            {
                editHerdNameTextField.setText("");//resets textfield to blank
                editHerdTextArea.setText("Herd " + herdName + " deleted");
                //sets message area to success message
                refresh();//redisplays lists without dleted herd
            }
            else//fires if deleteHerd returns false
            {//asks user to check if herd still contains cows
                JOptionPane.showMessageDialog(null,
                        "Herd could not be deleted. Please check there are no cows in " + herdName);
            }
        }
    }

    /**
     * Deletes a farm from collection. Only removes farm if farm has no herds
     * associated with it.
     */
    private void deleteFarm()
    {
        //Alerts user and stops method if field is blank
        if (editFarmNameTextField.getText().length() < 1)
        {
            JOptionPane.showMessageDialog(null,
                    "Please enter a name and try again");
            return;
        }

        //sets variables for deleteFarm method
        int indexF = editFarmFarmList.getSelectedIndex();
        Farm aFarm = holdFarm.get(indexF);
        String farmName = coord.getFarmName(aFarm);
        //pop up window asking if user wishes to delete farm
        int del = JOptionPane.showConfirmDialog(null,
                "Do you wish to delete farm " + farmName + "?", "",
                JOptionPane.YES_NO_OPTION);
        if (del == JOptionPane.YES_OPTION)//fires if user selects yes
        {
            if (coord.deleteFarm(farmName))//fires if deleteFarm returns true
            {//sets textfields to blank, sets success message
                editFarmIDTextField.setText("");
                editFarmNameTextField.setText("");
                editFarmLocationTextArea.setText("");
                editFarmMessageTextArea.setText("Farm " + farmName + " deleted.");
                refresh();//redisplays lists without deleted farm
            }
            else//fires if deleteFarm returns false
            {//pop up window asking user to check that farm has no herds
                JOptionPane.showMessageDialog(null,
                        "Farm could not be deleted. Please check " + farmName + "has no herds.");

            }
        }
    }

    /**
     * Removes milk takings for aCow object
     */
    private void deleteMilkTakings()
    {
        //pops up window alerting user to unselected lists,
        //and exits method.
        if (editMilkCowList.isSelectionEmpty())
        {
            JOptionPane.showMessageDialog(null,
                    "Please select a cow and try again");
            return;
        }

        //sets variables for deleteMilkTakings method
        int indexC = editMilkCowList.getSelectedIndex();
        Cow aCow = holdCow.get(indexC);
        //Pop up window asking if user wishes to delete milk takings
        int del = JOptionPane.showConfirmDialog(null,
                "Do you wish to delete milk takings? ", "",
                JOptionPane.YES_NO_OPTION);
        if (del == JOptionPane.YES_OPTION)//fires if users chooses yes
        {
            if (coord.deleteMilkTakings(aCow))//fires if method returns true
            {//sets success message, and clears fields
                editMilkTakingsTextArea.setText("Milk takings deleted");
                editMilkTakingsAmTextField.setText("");
                editMilkTakingsPmTextField.setText("");
                displayMilkTakings();//updates the display for new values
            }
            else//fires if method returns false, sets fail message
            {
                JOptionPane.showMessageDialog(null,
                        "Milk takings could not be entered.");
            }
        }
    }

    /**
     * Allows a user to enter new milk takings for a cow object
     */
    private void updateMilkTakings()
    {//Pops up window if no cow selected to alert user, and exits method
        if (editMilkCowList.isSelectionEmpty())
        {
            JOptionPane.showMessageDialog(null,
                    "Please select a cow and try again");
            return;
        }

        //sets variables for deleteMilkTakings in coord
        int indexC = editMilkCowList.getSelectedIndex();
        Cow aCow = holdCow.get(indexC);
        //pops up a window asking if user wishes to update milk takings
        int del = JOptionPane.showConfirmDialog(null,
                "Do you wish to update milk takings? ", "",
                JOptionPane.YES_NO_OPTION);
        if (del == JOptionPane.YES_OPTION)//fires if user chooses yes
        {
            if (coord.deleteMilkTakings(aCow))//fires if delete returns true
            {
                addMilkTakings();//calls gui method.
            }
            else//fires if deleteMilkTakings returns false
            {//sets fail message
                editMilkTakingsTextArea.setText(
                        "Milk takings could not be updated");
            }
        }
    }

    /**
     * Takes the data for milking interval 8_16 from the GUI jTable and passes
     * it to be added to CalcMaxT class i8_16 HashMap
     */
    private void addData8_16()
    {
        Map<String, String> value;//declares map for values
        String yield = null;//varaible to hold value from column 0.
        for (int i = 0; i < 6; i++)//only gets first three columns
        {//set at 6 as there are 6 rows.
            value = new HashMap<String, String>();
            yield = (String) jTable.getValueAt(i, 0);
            //sets variable to value at row i, column 0
            String pm = (String) jTable.getValueAt(i, 1);
            //sets variable to value at row i, column 1
            String am = (String) jTable.getValueAt(i, 2);
            //sets variable to value at row i, column 2
            if (validateTable(yield, am, pm))//checks that all fields contain data
            {
                value.put(pm, am);//adds to HashMap for passing
                coord.addData8_16(yield, value);
                //adds yield and value HashMap to i8_16 HashMap
            }
            else//fires if a filed is left blank.
            {
                JOptionPane.showMessageDialog(null,
                        "Please fill in all the values in Table 1 and try again");
                return;//exits method, so empty field/s can be filled in.
            }
        }
    }

    /**
     * Takes the data for milking interval 9_15 from the GUI jTable and passes
     * it to be added to CalcMaxT class i9_15 HashMap
     */
    private void addData9_15()
    {
        Map<String, String> value;//declares Map for values
        String yield = null;
        for (int i = 0; i < 6; i++)
        //only gets first three columns. Set at 6 as there are 6 rows
        {
            value = new HashMap<String, String>();
            yield = (String) jTable.getValueAt(i, 0);
            //sets variable to value at row i, column 0
            String pm = (String) jTable.getValueAt(i, 3);
            //sets variable to value at row i, column 3
            String am = (String) jTable.getValueAt(i, 4);
            //sets variable to value at row i, column 4
            if (validateTable(yield, am, pm))//checks all fiels contain data
            {
                value.put(pm, am);//adds to HashMap for passing
                coord.addData9_15(yield, value);
                //adds yield and value HashMap to i9_15 HashMap
            }
            else//fires if a field is blank
            {
                JOptionPane.showMessageDialog(null,
                        "Please fill in all the values in Table 1 and try again");
                return;//allows field/s to be filled
            }
        }
    }

    /**
     * Checks the jTable fields are not empty
     *
     * @param yield - a String
     * @param am - a String
     * @param pm - a String
     * @return boolean
     */
    private boolean validateTable(String yield, String am, String pm)
    {
        if (yield.equals(""))//"" as this is what is returned when field is blank.
        {
            return false;//exits method and returns false
        }
        if (am == null)//null as that is what is returned when field is blank
        {
            return false;
        }
        if (pm == null)
        {
            return false;
        }

        return true;//returns true
    }

    /**
     * Gets the maxT times for selected herd.
     */
    private void getMaxT()
    {
        String[] times = null;//string array to hold times
        if (tableFilled)//fires if the done button has been pressed on table 1.
        {
            int index = editCowHerdMList.getSelectedIndex();//gets the index position
            Herd aHerd = holdHerd.get(index);//gets the herd associated with index position
            times = coord.getMaxT(aHerd);//sets array to return
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Please fill in table 1 first");
            return;
        }
        maxTAMTextField.setText(times[1]);//sets am to array position 1
        maxTPMTextField.setText(times[0]);//sets pm to array position 0
        displayMainHerd();//displays herds and values on main page.
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        Table1 = new javax.swing.JTabbedPane();
        MaxT = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        maxTAMTextField = new javax.swing.JTextField();
        maxTPMTextField = new javax.swing.JTextField();
        maxTCalculateButton = new javax.swing.JButton();
        maxTExitButton = new javax.swing.JButton();
        maxTMainScreenButton = new javax.swing.JButton();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        maxTBackButton = new javax.swing.JButton();
        jScrollPane16 = new javax.swing.JScrollPane();
        maxTHerdList = new m256gui.M256JList();
        jScrollPane18 = new javax.swing.JScrollPane();
        maxTFarmList = new m256gui.M256JList();
        EditMilk = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        editMilkTakingUpdateButton = new javax.swing.JButton();
        editMilkTakingsDeleteButton = new javax.swing.JButton();
        editMilkTakingsAmTextField = new javax.swing.JTextField();
        editMilkTakingsPmTextField = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jScrollPane10 = new javax.swing.JScrollPane();
        editMilkTakingsTextArea = new javax.swing.JTextArea();
        EditMilkTakingsBackButton = new javax.swing.JButton();
        EditMilkTakingsMainButton = new javax.swing.JButton();
        EditMilkTakingsExitButton = new javax.swing.JButton();
        editMilkAddButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        editMilkFarmList = new m256gui.M256JList();
        jScrollPane6 = new javax.swing.JScrollPane();
        editMilkHerdList = new m256gui.M256JList();
        jScrollPane13 = new javax.swing.JScrollPane();
        editMilkCowList = new m256gui.M256JList();
        jLabel43 = new javax.swing.JLabel();
        EditFarm = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        editFarmIDTextField = new javax.swing.JTextField();
        editFarmNameTextField = new javax.swing.JTextField();
        jScrollPane11 = new javax.swing.JScrollPane();
        editFarmLocationTextArea = new javax.swing.JTextArea();
        editFarmAddButton = new javax.swing.JButton();
        jScrollPane12 = new javax.swing.JScrollPane();
        editFarmMessageTextArea = new javax.swing.JTextArea();
        editFarmBackButton = new javax.swing.JButton();
        editFarmEditHerdButton = new javax.swing.JButton();
        editFarmExitButton = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        editFarmDeleteButton = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        editFarmFarmList = new m256gui.M256JList();
        jLabel40 = new javax.swing.JLabel();
        MainScreen = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        mainFarmButton = new javax.swing.JButton();
        mainHerdButton = new javax.swing.JButton();
        mainCowButton = new javax.swing.JButton();
        mainExitButton = new javax.swing.JButton();
        mainEditMilkButton = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        mainScreenMaxTButton = new javax.swing.JButton();
        mainFarmList = new m256gui.M256JList();
        mainHerdList = new m256gui.M256JList();
        EditCow = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        editCowIDTextField = new javax.swing.JTextField();
        editCowAddButton = new javax.swing.JButton();
        editCowDeleteButton = new javax.swing.JButton();
        jScrollPane17 = new javax.swing.JScrollPane();
        editCowTextArea = new javax.swing.JTextArea();
        editCowBackButton = new javax.swing.JButton();
        editCowMainScreenButton = new javax.swing.JButton();
        editCowExitButton = new javax.swing.JButton();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        editCowFarmMList = new m256gui.M256JList();
        jScrollPane5 = new javax.swing.JScrollPane();
        editCowHerdMList = new m256gui.M256JList();
        jLabel38 = new javax.swing.JLabel();
        editCowCowList = new m256gui.M256JList();
        jLabel39 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        EditHerd = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        editHerdIDTextField = new javax.swing.JTextField();
        editHerdNameTextField = new javax.swing.JTextField();
        editHerd8_16RadioButton = new javax.swing.JRadioButton();
        editHerd9_15RadioButton = new javax.swing.JRadioButton();
        jScrollPane14 = new javax.swing.JScrollPane();
        editHerdTextArea = new javax.swing.JTextArea();
        editHerdBackButton = new javax.swing.JButton();
        editHerdEditCowButton = new javax.swing.JButton();
        editHerdExitButton = new javax.swing.JButton();
        editHerdAddButton = new javax.swing.JButton();
        editHerdDeleteButton = new javax.swing.JButton();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        editHerdFarmList = new m256gui.M256JList();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        editHerdHerdList = new m256gui.M256JList();
        jLabel37 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane15 = new javax.swing.JScrollPane();
        jTable = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        table1Done = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        Shared = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel31.setText("Calculate MaxT");

        maxTCalculateButton.setText("Calculate MaxT");
        maxTCalculateButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                maxTCalculateButtonActionPerformed(evt);
            }
        });

        maxTExitButton.setText("Exit");
        maxTExitButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                maxTExitButtonActionPerformed(evt);
            }
        });

        maxTMainScreenButton.setText("Main Screen");
        maxTMainScreenButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                maxTMainScreenButtonActionPerformed(evt);
            }
        });

        jLabel32.setText("AM:");

        jLabel33.setText("PM:");

        jLabel34.setText("MaxT:");

        jLabel35.setText("Farm:");

        jLabel36.setText("Herd:");

        maxTBackButton.setText("Back");
        maxTBackButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                maxTBackButtonActionPerformed(evt);
            }
        });

        maxTHerdList.addListSelectionListener(new javax.swing.event.ListSelectionListener()
        {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt)
            {
                maxTHerdListValueChanged(evt);
            }
        });
        jScrollPane16.setViewportView(maxTHerdList);

        maxTFarmList.setModel(new javax.swing.AbstractListModel()
        {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        maxTFarmList.addListSelectionListener(new javax.swing.event.ListSelectionListener()
        {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt)
            {
                maxTFarmListValueChanged(evt);
            }
        });
        jScrollPane18.setViewportView(maxTFarmList);

        javax.swing.GroupLayout MaxTLayout = new javax.swing.GroupLayout(MaxT);
        MaxT.setLayout(MaxTLayout);
        MaxTLayout.setHorizontalGroup(
            MaxTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MaxTLayout.createSequentialGroup()
                .addGroup(MaxTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(MaxTLayout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(MaxTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(MaxTLayout.createSequentialGroup()
                                .addComponent(jLabel36)
                                .addGap(26, 26, 26)
                                .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                            .addGroup(MaxTLayout.createSequentialGroup()
                                .addGroup(MaxTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(MaxTLayout.createSequentialGroup()
                                        .addComponent(jLabel35)
                                        .addGap(18, 18, 18))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MaxTLayout.createSequentialGroup()
                                        .addComponent(jLabel32)
                                        .addGap(6, 6, 6)))
                                .addGroup(MaxTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane18, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(MaxTLayout.createSequentialGroup()
                                        .addComponent(maxTAMTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(36, 36, 36)
                                        .addComponent(jLabel33)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(maxTPMTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(34, 34, 34)
                        .addComponent(maxTCalculateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(MaxTLayout.createSequentialGroup()
                        .addGroup(MaxTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(MaxTLayout.createSequentialGroup()
                                .addGap(233, 233, 233)
                                .addComponent(jLabel31))
                            .addGroup(MaxTLayout.createSequentialGroup()
                                .addGap(37, 37, 37)
                                .addComponent(maxTBackButton, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(45, 45, 45)
                                .addComponent(maxTMainScreenButton)
                                .addGap(36, 36, 36)
                                .addComponent(maxTExitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(MaxTLayout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(jLabel34)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        MaxTLayout.setVerticalGroup(
            MaxTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MaxTLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel31)
                .addGap(30, 30, 30)
                .addGroup(MaxTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(MaxTLayout.createSequentialGroup()
                        .addGroup(MaxTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, MaxTLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(105, 105, 105))
                            .addGroup(MaxTLayout.createSequentialGroup()
                                .addGroup(MaxTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(MaxTLayout.createSequentialGroup()
                                        .addComponent(jLabel35)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 95, Short.MAX_VALUE)
                                        .addComponent(jLabel36)
                                        .addGap(109, 109, 109))
                                    .addGroup(MaxTLayout.createSequentialGroup()
                                        .addGap(68, 68, 68)
                                        .addComponent(maxTCalculateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addComponent(jLabel34)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(MaxTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(maxTAMTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(maxTPMTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel33)
                                    .addComponent(jLabel32))))
                        .addGap(53, 53, 53)
                        .addGroup(MaxTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(maxTBackButton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(maxTMainScreenButton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(maxTExitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(124, Short.MAX_VALUE))
                    .addGroup(MaxTLayout.createSequentialGroup()
                        .addComponent(jScrollPane18, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        Table1.addTab("MaxT", MaxT);

        jLabel11.setText("Milk Takings");

        jLabel12.setText("Farm:");

        jLabel13.setText("Herd:");

        jLabel14.setText("Cow:");

        editMilkTakingUpdateButton.setText("Update");
        editMilkTakingUpdateButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                editMilkTakingUpdateButtonActionPerformed(evt);
            }
        });

        editMilkTakingsDeleteButton.setText("Delete");
        editMilkTakingsDeleteButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                editMilkTakingsDeleteButtonActionPerformed(evt);
            }
        });

        jLabel15.setText("Milk Takings");

        jLabel16.setText("AM:");

        jLabel17.setText("PM:");

        editMilkTakingsTextArea.setColumns(20);
        editMilkTakingsTextArea.setRows(5);
        jScrollPane10.setViewportView(editMilkTakingsTextArea);

        EditMilkTakingsBackButton.setText("Back");
        EditMilkTakingsBackButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                EditMilkTakingsBackButtonActionPerformed(evt);
            }
        });

        EditMilkTakingsMainButton.setText("Main Screen");
        EditMilkTakingsMainButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                EditMilkTakingsMainButtonActionPerformed(evt);
            }
        });

        EditMilkTakingsExitButton.setText("Exit");
        EditMilkTakingsExitButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                EditMilkTakingsExitButtonActionPerformed(evt);
            }
        });

        editMilkAddButton.setText("Add");
        editMilkAddButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                editMilkAddButtonActionPerformed(evt);
            }
        });

        editMilkFarmList.setToolTipText("Select team caring for patient");
        editMilkFarmList.addListSelectionListener(new javax.swing.event.ListSelectionListener()
        {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt)
            {
                editMilkFarmListValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(editMilkFarmList);

        editMilkHerdList.setToolTipText("Select team caring for patient");
        editMilkHerdList.addListSelectionListener(new javax.swing.event.ListSelectionListener()
        {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt)
            {
                editMilkHerdListValueChanged(evt);
            }
        });
        jScrollPane6.setViewportView(editMilkHerdList);

        editMilkCowList.setToolTipText("Select team caring for patient");
        editMilkCowList.addListSelectionListener(new javax.swing.event.ListSelectionListener()
        {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt)
            {
                editMilkCowListValueChanged(evt);
            }
        });
        jScrollPane13.setViewportView(editMilkCowList);

        jLabel43.setText("Messages:");

        javax.swing.GroupLayout EditMilkLayout = new javax.swing.GroupLayout(EditMilk);
        EditMilk.setLayout(EditMilkLayout);
        EditMilkLayout.setHorizontalGroup(
            EditMilkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EditMilkLayout.createSequentialGroup()
                .addGroup(EditMilkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(EditMilkLayout.createSequentialGroup()
                        .addGroup(EditMilkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(EditMilkLayout.createSequentialGroup()
                                .addGap(104, 104, 104)
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, EditMilkLayout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel14)
                                .addGap(16, 16, 16)))
                        .addGroup(EditMilkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(EditMilkLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(editMilkTakingUpdateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(EditMilkLayout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(editMilkAddButton, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, EditMilkLayout.createSequentialGroup()
                                .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(editMilkTakingsDeleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, EditMilkLayout.createSequentialGroup()
                        .addGap(102, 102, 102)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(EditMilkLayout.createSequentialGroup()
                .addGroup(EditMilkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(EditMilkLayout.createSequentialGroup()
                        .addGap(232, 232, 232)
                        .addComponent(jLabel11))
                    .addGroup(EditMilkLayout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(EditMilkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(EditMilkLayout.createSequentialGroup()
                                .addComponent(EditMilkTakingsBackButton, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(55, 55, 55)
                                .addComponent(EditMilkTakingsMainButton)
                                .addGap(71, 71, 71)
                                .addComponent(EditMilkTakingsExitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(EditMilkLayout.createSequentialGroup()
                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(editMilkTakingsAmTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(46, 46, 46)
                                .addComponent(jLabel17)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(editMilkTakingsPmTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(EditMilkLayout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addGroup(EditMilkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel43)
                            .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(52, Short.MAX_VALUE))
        );
        EditMilkLayout.setVerticalGroup(
            EditMilkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EditMilkLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addGroup(EditMilkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(EditMilkLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(EditMilkLayout.createSequentialGroup()
                        .addGroup(EditMilkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(EditMilkLayout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addComponent(jLabel12)
                                .addGap(71, 71, 71))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, EditMilkLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(editMilkAddButton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(43, 43, 43)))
                        .addGroup(EditMilkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(EditMilkLayout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(jLabel13))
                            .addComponent(editMilkTakingUpdateButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(EditMilkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(EditMilkLayout.createSequentialGroup()
                        .addGroup(EditMilkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(EditMilkLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(EditMilkLayout.createSequentialGroup()
                                .addGap(64, 64, 64)
                                .addComponent(jLabel14)))
                        .addGap(22, 22, 22)
                        .addGroup(EditMilkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(jLabel16)
                            .addComponent(editMilkTakingsAmTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17)
                            .addComponent(editMilkTakingsPmTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(EditMilkLayout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(editMilkTakingsDeleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel43)
                .addGap(6, 6, 6)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addGroup(EditMilkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(EditMilkTakingsBackButton, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(EditMilkTakingsMainButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(EditMilkTakingsExitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        Table1.addTab("Milk Takings", EditMilk);

        jLabel18.setText("AddFarm");

        editFarmLocationTextArea.setColumns(20);
        editFarmLocationTextArea.setRows(5);
        jScrollPane11.setViewportView(editFarmLocationTextArea);

        editFarmAddButton.setText("Add");
        editFarmAddButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                editFarmAddButtonActionPerformed(evt);
            }
        });

        editFarmMessageTextArea.setColumns(20);
        editFarmMessageTextArea.setRows(5);
        jScrollPane12.setViewportView(editFarmMessageTextArea);

        editFarmBackButton.setText("Back");
        editFarmBackButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                editFarmBackButtonActionPerformed(evt);
            }
        });

        editFarmEditHerdButton.setText("Add/Delete Herd");
        editFarmEditHerdButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                editFarmEditHerdButtonActionPerformed(evt);
            }
        });

        editFarmExitButton.setText("Exit");
        editFarmExitButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                editFarmExitButtonActionPerformed(evt);
            }
        });

        jLabel19.setText("Farm ID:");

        jLabel20.setText("Farm Name:");

        jLabel21.setText("Farm Location:");

        editFarmDeleteButton.setText("Delete");
        editFarmDeleteButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                editFarmDeleteButtonActionPerformed(evt);
            }
        });

        jLabel5.setText("Delete Farm");

        jLabel9.setText("Farm Name:");

        editFarmFarmList.setModel(new javax.swing.AbstractListModel()
        {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        editFarmFarmList.addListSelectionListener(new javax.swing.event.ListSelectionListener()
        {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt)
            {
                editFarmFarmListValueChanged(evt);
            }
        });
        jScrollPane7.setViewportView(editFarmFarmList);

        jLabel40.setText("Messages:");

        javax.swing.GroupLayout EditFarmLayout = new javax.swing.GroupLayout(EditFarm);
        EditFarm.setLayout(EditFarmLayout);
        EditFarmLayout.setHorizontalGroup(
            EditFarmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EditFarmLayout.createSequentialGroup()
                .addGroup(EditFarmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(EditFarmLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(EditFarmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(EditFarmLayout.createSequentialGroup()
                                .addGroup(EditFarmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(EditFarmLayout.createSequentialGroup()
                                        .addGroup(EditFarmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel19)
                                            .addComponent(jLabel20)
                                            .addComponent(jLabel21)
                                            .addComponent(jLabel9))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(EditFarmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(editFarmNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(editFarmIDTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(EditFarmLayout.createSequentialGroup()
                                        .addGap(103, 103, 103)
                                        .addComponent(jLabel5)))
                                .addGroup(EditFarmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(EditFarmLayout.createSequentialGroup()
                                        .addGap(45, 45, 45)
                                        .addComponent(editFarmAddButton, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(EditFarmLayout.createSequentialGroup()
                                        .addGap(34, 34, 34)
                                        .addComponent(editFarmDeleteButton))))
                            .addGroup(EditFarmLayout.createSequentialGroup()
                                .addComponent(editFarmBackButton, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(56, 56, 56)
                                .addComponent(editFarmEditHerdButton)
                                .addGap(47, 47, 47)
                                .addComponent(editFarmExitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(EditFarmLayout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(EditFarmLayout.createSequentialGroup()
                        .addGap(222, 222, 222)
                        .addComponent(jLabel18))
                    .addGroup(EditFarmLayout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(jLabel40)))
                .addContainerGap(84, Short.MAX_VALUE))
        );
        EditFarmLayout.setVerticalGroup(
            EditFarmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EditFarmLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(EditFarmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(editFarmIDTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19))
                .addGap(9, 9, 9)
                .addGroup(EditFarmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(editFarmNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20)
                    .addComponent(editFarmAddButton, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(EditFarmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(EditFarmLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                        .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(EditFarmLayout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(jLabel21)))
                .addGap(29, 29, 29)
                .addGroup(EditFarmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(EditFarmLayout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                        .addComponent(jLabel40)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, EditFarmLayout.createSequentialGroup()
                        .addComponent(editFarmDeleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, EditFarmLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)))
                .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(EditFarmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(editFarmBackButton, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(editFarmEditHerdButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(editFarmExitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(127, Short.MAX_VALUE))
        );

        Table1.addTab("Add/DeleteFarm", EditFarm);

        jLabel1.setText("Farm:");

        jLabel2.setText("Herd:");

        mainFarmButton.setText("Add/Delete Farm");
        mainFarmButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                mainFarmButtonActionPerformed(evt);
            }
        });

        mainHerdButton.setText("Add/Delete Herd");
        mainHerdButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                mainHerdButtonActionPerformed(evt);
            }
        });

        mainCowButton.setText("Add/Delete Cow");
        mainCowButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                mainCowButtonActionPerformed(evt);
            }
        });

        mainExitButton.setText("Exit");
        mainExitButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                mainExitButtonActionPerformed(evt);
            }
        });

        mainEditMilkButton.setText("Milk Takings");
        mainEditMilkButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                mainEditMilkButtonActionPerformed(evt);
            }
        });

        jLabel3.setText("Calculate MaxT Main Screen");

        mainScreenMaxTButton.setText("MaxT");
        mainScreenMaxTButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                mainScreenMaxTButtonActionPerformed(evt);
            }
        });

        mainFarmList.setToolTipText("Select team caring for patient");
        mainFarmList.addListSelectionListener(new javax.swing.event.ListSelectionListener()
        {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt)
            {
                mainFarmListValueChanged(evt);
            }
        });

        mainHerdList.setToolTipText("");
        mainHerdList.addListSelectionListener(new javax.swing.event.ListSelectionListener()
        {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt)
            {
                mainHerdListValueChanged(evt);
            }
        });

        javax.swing.GroupLayout MainScreenLayout = new javax.swing.GroupLayout(MainScreen);
        MainScreen.setLayout(MainScreenLayout);
        MainScreenLayout.setHorizontalGroup(
            MainScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MainScreenLayout.createSequentialGroup()
                .addGroup(MainScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(MainScreenLayout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(mainEditMilkButton)
                        .addGap(43, 43, 43)
                        .addComponent(mainScreenMaxTButton, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(71, 71, 71)
                        .addComponent(mainExitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(MainScreenLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(mainFarmList, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(mainHerdList, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(MainScreenLayout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(mainFarmButton)
                        .addGap(48, 48, 48)
                        .addComponent(mainHerdButton)
                        .addGap(18, 18, 18)
                        .addComponent(mainCowButton))
                    .addGroup(MainScreenLayout.createSequentialGroup()
                        .addGap(151, 151, 151)
                        .addComponent(jLabel3)))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        MainScreenLayout.setVerticalGroup(
            MainScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MainScreenLayout.createSequentialGroup()
                .addComponent(jLabel3)
                .addGap(27, 27, 27)
                .addGroup(MainScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MainScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(mainFarmList, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2)
                        .addComponent(mainHerdList, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 130, Short.MAX_VALUE)
                .addGroup(MainScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mainFarmButton, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mainHerdButton, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mainCowButton, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(80, 80, 80)
                .addGroup(MainScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mainScreenMaxTButton, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mainEditMilkButton, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mainExitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(78, 78, 78))
        );

        Table1.addTab("Main Screen", MainScreen);

        jLabel27.setText("Add/Delete Cow");

        editCowAddButton.setText("Add");
        editCowAddButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                editCowAddButtonActionPerformed(evt);
            }
        });

        editCowDeleteButton.setText("Delete");
        editCowDeleteButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                editCowDeleteButtonActionPerformed(evt);
            }
        });

        editCowTextArea.setColumns(20);
        editCowTextArea.setRows(5);
        jScrollPane17.setViewportView(editCowTextArea);

        editCowBackButton.setText("Back");
        editCowBackButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                editCowBackButtonActionPerformed(evt);
            }
        });

        editCowMainScreenButton.setText("Main Screen");
        editCowMainScreenButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                editCowMainScreenButtonActionPerformed(evt);
            }
        });

        editCowExitButton.setText("Exit");
        editCowExitButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                editCowExitButtonActionPerformed(evt);
            }
        });

        jLabel28.setText("Farm:");

        jLabel29.setText("Herd:");

        jLabel30.setText("Cow ID:");

        editCowFarmMList.setToolTipText("Select team caring for patient");
        editCowFarmMList.addListSelectionListener(new javax.swing.event.ListSelectionListener()
        {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt)
            {
                editCowFarmMListValueChanged(evt);
            }
        });
        jScrollPane3.setViewportView(editCowFarmMList);

        editCowHerdMList.setToolTipText("Select team caring for patient");
        editCowHerdMList.addListSelectionListener(new javax.swing.event.ListSelectionListener()
        {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt)
            {
                editCowHerdMListValueChanged(evt);
            }
        });
        jScrollPane5.setViewportView(editCowHerdMList);

        jLabel38.setText("Delete cow. First select farm and herd from above lists");

        editCowCowList.setToolTipText("Select team caring for patient");

        jLabel39.setText("Cows:");

        jLabel42.setText("Messages:");

        javax.swing.GroupLayout EditCowLayout = new javax.swing.GroupLayout(EditCow);
        EditCow.setLayout(EditCowLayout);
        EditCowLayout.setHorizontalGroup(
            EditCowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EditCowLayout.createSequentialGroup()
                .addGroup(EditCowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(EditCowLayout.createSequentialGroup()
                        .addGap(236, 236, 236)
                        .addComponent(jLabel27))
                    .addGroup(EditCowLayout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(EditCowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(EditCowLayout.createSequentialGroup()
                                .addGroup(EditCowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel28)
                                    .addComponent(jLabel29)
                                    .addComponent(jLabel30))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(EditCowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(EditCowLayout.createSequentialGroup()
                                        .addGroup(EditCowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
                                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                        .addGap(34, 34, 34)
                                        .addComponent(editCowAddButton, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(editCowIDTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jScrollPane17, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(EditCowLayout.createSequentialGroup()
                                .addComponent(editCowBackButton, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(57, 57, 57)
                                .addComponent(editCowMainScreenButton)
                                .addGap(51, 51, 51)
                                .addComponent(editCowExitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel42)))
                    .addGroup(EditCowLayout.createSequentialGroup()
                        .addGap(77, 77, 77)
                        .addComponent(jLabel38))
                    .addGroup(EditCowLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jLabel39)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(editCowCowList, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(editCowDeleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(94, Short.MAX_VALUE))
        );
        EditCowLayout.setVerticalGroup(
            EditCowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EditCowLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel27)
                .addGroup(EditCowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(EditCowLayout.createSequentialGroup()
                        .addGroup(EditCowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(EditCowLayout.createSequentialGroup()
                                .addGap(38, 38, 38)
                                .addComponent(jLabel28))
                            .addGroup(EditCowLayout.createSequentialGroup()
                                .addGap(37, 37, 37)
                                .addComponent(editCowAddButton)))
                        .addGap(84, 84, 84))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, EditCowLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addGroup(EditCowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(EditCowLayout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jLabel29)))
                .addGap(30, 30, 30)
                .addGroup(EditCowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(editCowIDTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel38)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(EditCowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(editCowCowList, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(EditCowLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabel39))
                    .addComponent(editCowDeleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addComponent(jLabel42)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane17, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addGroup(EditCowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(editCowBackButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(editCowMainScreenButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(editCowExitButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        Table1.addTab("Add/Delete Cow.", EditCow);

        jLabel22.setText("Add/Delete Herd");

        buttonGroup1.add(editHerd8_16RadioButton);
        editHerd8_16RadioButton.setText("8, 16");
        editHerd8_16RadioButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                editHerd8_16RadioButtonActionPerformed(evt);
            }
        });

        buttonGroup1.add(editHerd9_15RadioButton);
        editHerd9_15RadioButton.setText("9, 15");
        editHerd9_15RadioButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                editHerd9_15RadioButtonActionPerformed(evt);
            }
        });

        editHerdTextArea.setColumns(20);
        editHerdTextArea.setRows(5);
        jScrollPane14.setViewportView(editHerdTextArea);

        editHerdBackButton.setText("Back");
        editHerdBackButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                editHerdBackButtonActionPerformed(evt);
            }
        });

        editHerdEditCowButton.setText("Add/Delete Cow");
        editHerdEditCowButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                editHerdEditCowButtonActionPerformed(evt);
            }
        });

        editHerdExitButton.setText("Exit");
        editHerdExitButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                editHerdExitButtonActionPerformed(evt);
            }
        });

        editHerdAddButton.setText("Add");
        editHerdAddButton.setMaximumSize(new java.awt.Dimension(55, 35));
        editHerdAddButton.setMinimumSize(new java.awt.Dimension(55, 35));
        editHerdAddButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                editHerdAddButtonActionPerformed(evt);
            }
        });

        editHerdDeleteButton.setText("Delete");
        editHerdDeleteButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                editHerdDeleteButtonActionPerformed(evt);
            }
        });

        jLabel23.setText("Farm:");

        jLabel24.setText("Herd ID:");

        jLabel25.setText("Herd Name:");

        jLabel26.setText("Milking Interval");

        editHerdFarmList.setModel(new javax.swing.AbstractListModel()
        {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        editHerdFarmList.addListSelectionListener(new javax.swing.event.ListSelectionListener()
        {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt)
            {
                editHerdFarmListValueChanged(evt);
            }
        });
        jScrollPane4.setViewportView(editHerdFarmList);

        jLabel10.setText("Delete Herd. First select Farm from list above.");

        editHerdHerdList.setModel(new javax.swing.AbstractListModel()
        {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        editHerdHerdList.addListSelectionListener(new javax.swing.event.ListSelectionListener()
        {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt)
            {
                editHerdHerdListValueChanged(evt);
            }
        });
        jScrollPane8.setViewportView(editHerdHerdList);

        jLabel37.setText("Herd Name:");

        jLabel41.setText("Messages:");

        javax.swing.GroupLayout EditHerdLayout = new javax.swing.GroupLayout(EditHerd);
        EditHerd.setLayout(EditHerdLayout);
        EditHerdLayout.setHorizontalGroup(
            EditHerdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EditHerdLayout.createSequentialGroup()
                .addGroup(EditHerdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(EditHerdLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(EditHerdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel24)
                            .addComponent(jLabel23)
                            .addComponent(jLabel25))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(EditHerdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(EditHerdLayout.createSequentialGroup()
                                .addGroup(EditHerdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(editHerdNameTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
                                    .addComponent(editHerdIDTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
                                    .addComponent(jScrollPane4))
                                .addGap(42, 42, 42)
                                .addComponent(editHerdAddButton, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(EditHerdLayout.createSequentialGroup()
                                .addGap(59, 59, 59)
                                .addComponent(jLabel22))))
                    .addGroup(EditHerdLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(editHerdBackButton, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(editHerdEditCowButton)
                        .addGap(32, 32, 32)
                        .addComponent(editHerdExitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(EditHerdLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel26)
                        .addGap(27, 27, 27)
                        .addComponent(editHerd8_16RadioButton)
                        .addGap(38, 38, 38)
                        .addComponent(editHerd9_15RadioButton))
                    .addGroup(EditHerdLayout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addComponent(jLabel10))
                    .addGroup(EditHerdLayout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(jLabel37)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25)
                        .addComponent(editHerdDeleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(EditHerdLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(EditHerdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel41)
                            .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(108, Short.MAX_VALUE))
        );
        EditHerdLayout.setVerticalGroup(
            EditHerdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EditHerdLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel22)
                .addGroup(EditHerdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(EditHerdLayout.createSequentialGroup()
                        .addGroup(EditHerdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(EditHerdLayout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(editHerdAddButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(EditHerdLayout.createSequentialGroup()
                                .addGroup(EditHerdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(EditHerdLayout.createSequentialGroup()
                                        .addGap(48, 48, 48)
                                        .addComponent(jLabel23))
                                    .addGroup(EditHerdLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addGroup(EditHerdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(editHerdIDTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel24))
                                .addGap(18, 18, 18)
                                .addGroup(EditHerdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(editHerdNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel25))))
                        .addGap(23, 23, 23)
                        .addGroup(EditHerdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel26)
                            .addComponent(editHerd8_16RadioButton)
                            .addComponent(editHerd9_15RadioButton))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel10)
                        .addGroup(EditHerdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(EditHerdLayout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(jLabel37))
                            .addGroup(EditHerdLayout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(EditHerdLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(editHerdDeleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(15, 15, 15)
                .addComponent(jLabel41)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(EditHerdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(editHerdBackButton, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(editHerdEditCowButton)
                    .addComponent(editHerdExitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(131, Short.MAX_VALUE))
        );

        Table1.addTab("Add/Delete Herd", EditHerd);

        jTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][]
            {
                {"20", null, null, null, null},
                {"21", null, null, null, null},
                {"22", null, null, null, null},
                {"23", null, null, null, null},
                {"24", null, null, null, null},
                {"25", null, null, null, null}
            },
            new String []
            {
                "Yield (Litres)", "8 - pm", "16 - am", "9  - pm", "15 - am"
            }
        ));
        jTable.setToolTipText("Please enter a whole number, ie 20");
        jScrollPane15.setViewportView(jTable);

        jLabel4.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.disabledShadow"));
        jLabel4.setText("                             Milking Intervals");

        jLabel6.setText("Milking Intervals - Please enter into the table, the amount in litres, ie 7 ");

        table1Done.setText("Done");
        table1Done.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                table1DoneActionPerformed(evt);
            }
        });

        jLabel7.setText("Press Done button when finished.");

        jLabel8.setText("Daily Milk");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(213, 213, 213)
                        .addComponent(table1Done, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(27, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(85, 85, 85))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(6, 6, 6)
                .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addComponent(table1Done, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(181, Short.MAX_VALUE))
        );

        Table1.addTab("Table1", jPanel4);

        javax.swing.GroupLayout SharedLayout = new javax.swing.GroupLayout(Shared);
        Shared.setLayout(SharedLayout);
        SharedLayout.setHorizontalGroup(
            SharedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        SharedLayout.setVerticalGroup(
            SharedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Table1, javax.swing.GroupLayout.PREFERRED_SIZE, 508, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(242, 242, 242))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 434, Short.MAX_VALUE)
                    .addComponent(Shared, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 435, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Table1)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 306, Short.MAX_VALUE)
                    .addComponent(Shared, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 307, Short.MAX_VALUE)))
        );

        Table1.getAccessibleContext().setAccessibleName("MaxT");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void editCowExitButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_editCowExitButtonActionPerformed
    {//GEN-HEADEREND:event_editCowExitButtonActionPerformed
        System.exit(0);//exits system
    }//GEN-LAST:event_editCowExitButtonActionPerformed

    private void editCowMainScreenButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_editCowMainScreenButtonActionPerformed
    {//GEN-HEADEREND:event_editCowMainScreenButtonActionPerformed
        previous = Table1.getSelectedIndex();//sets index for back button
        Table1.setSelectedComponent(MainScreen);//changes window on button press
    }//GEN-LAST:event_editCowMainScreenButtonActionPerformed

    private void editCowBackButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_editCowBackButtonActionPerformed
    {//GEN-HEADEREND:event_editCowBackButtonActionPerformed
        Table1.setSelectedIndex(previous);
        //changes window to index held in previous on button press
    }//GEN-LAST:event_editCowBackButtonActionPerformed

    private void editCowDeleteButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_editCowDeleteButtonActionPerformed
    {//GEN-HEADEREND:event_editCowDeleteButtonActionPerformed
        deleteCow();//calls gui method on button press
    }//GEN-LAST:event_editCowDeleteButtonActionPerformed

    private void editCowAddButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_editCowAddButtonActionPerformed
    {//GEN-HEADEREND:event_editCowAddButtonActionPerformed
        addCow();//calls gui method on button press
    }//GEN-LAST:event_editCowAddButtonActionPerformed

    private void editHerdDeleteButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_editHerdDeleteButtonActionPerformed
    {//GEN-HEADEREND:event_editHerdDeleteButtonActionPerformed
        deleteHerd();//calls gui method on button press
    }//GEN-LAST:event_editHerdDeleteButtonActionPerformed

    private void editHerdAddButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_editHerdAddButtonActionPerformed
    {//GEN-HEADEREND:event_editHerdAddButtonActionPerformed
        addHerd();//calls gui method on button press
    }//GEN-LAST:event_editHerdAddButtonActionPerformed

    private void editHerdExitButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_editHerdExitButtonActionPerformed
    {//GEN-HEADEREND:event_editHerdExitButtonActionPerformed
        System.exit(0); //exits system on buton press
    }//GEN-LAST:event_editHerdExitButtonActionPerformed

    private void editHerdEditCowButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_editHerdEditCowButtonActionPerformed
    {//GEN-HEADEREND:event_editHerdEditCowButtonActionPerformed
        previous = Table1.getSelectedIndex();//sets variable to index of current screen.
        Table1.setSelectedComponent(EditCow);//changes window on button press
    }//GEN-LAST:event_editHerdEditCowButtonActionPerformed

    private void editHerdBackButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_editHerdBackButtonActionPerformed
    {//GEN-HEADEREND:event_editHerdBackButtonActionPerformed
        Table1.setSelectedIndex(previous);
        //changes window to index held in previous on button press
    }//GEN-LAST:event_editHerdBackButtonActionPerformed

    private void editFarmExitButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_editFarmExitButtonActionPerformed
    {//GEN-HEADEREND:event_editFarmExitButtonActionPerformed
        System.exit(0);//exits system on buton press
    }//GEN-LAST:event_editFarmExitButtonActionPerformed

    private void editFarmEditHerdButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_editFarmEditHerdButtonActionPerformed
    {//GEN-HEADEREND:event_editFarmEditHerdButtonActionPerformed
        previous = Table1.getSelectedIndex();//sets variable to index of current screen.
        Table1.setSelectedComponent(EditHerd);//changes window on button press
    }//GEN-LAST:event_editFarmEditHerdButtonActionPerformed

    private void editFarmBackButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_editFarmBackButtonActionPerformed
    {//GEN-HEADEREND:event_editFarmBackButtonActionPerformed
        Table1.setSelectedIndex(previous);
        //changes window to index held in previous on button press
    }//GEN-LAST:event_editFarmBackButtonActionPerformed

    private void editFarmDeleteButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_editFarmDeleteButtonActionPerformed
    {//GEN-HEADEREND:event_editFarmDeleteButtonActionPerformed
        deleteFarm();//calls gui method on button press
    }//GEN-LAST:event_editFarmDeleteButtonActionPerformed

    private void editFarmAddButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_editFarmAddButtonActionPerformed
    {//GEN-HEADEREND:event_editFarmAddButtonActionPerformed
        addFarm();//calls gui method on button press
    }//GEN-LAST:event_editFarmAddButtonActionPerformed


    private void EditMilkTakingsExitButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_EditMilkTakingsExitButtonActionPerformed
    {//GEN-HEADEREND:event_EditMilkTakingsExitButtonActionPerformed
        System.exit(0);//exits system on buton press
    }//GEN-LAST:event_EditMilkTakingsExitButtonActionPerformed

    private void EditMilkTakingsMainButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_EditMilkTakingsMainButtonActionPerformed
    {//GEN-HEADEREND:event_EditMilkTakingsMainButtonActionPerformed
        previous = Table1.getSelectedIndex();//sets variable to index of current screen.
        Table1.setSelectedComponent(MainScreen);//displays the component, Main Screen.
    }//GEN-LAST:event_EditMilkTakingsMainButtonActionPerformed

    private void EditMilkTakingsBackButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_EditMilkTakingsBackButtonActionPerformed
    {//GEN-HEADEREND:event_EditMilkTakingsBackButtonActionPerformed
        Table1.setSelectedIndex(previous);
        //changes window to index held in previous on button press
    }//GEN-LAST:event_EditMilkTakingsBackButtonActionPerformed

    private void editMilkTakingsDeleteButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_editMilkTakingsDeleteButtonActionPerformed
    {//GEN-HEADEREND:event_editMilkTakingsDeleteButtonActionPerformed
        deleteMilkTakings();//calls gui method on button press
    }//GEN-LAST:event_editMilkTakingsDeleteButtonActionPerformed

    private void editMilkTakingUpdateButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_editMilkTakingUpdateButtonActionPerformed
    {//GEN-HEADEREND:event_editMilkTakingUpdateButtonActionPerformed
        updateMilkTakings();//calls gui method on button press
    }//GEN-LAST:event_editMilkTakingUpdateButtonActionPerformed

    private void maxTBackButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_maxTBackButtonActionPerformed
    {//GEN-HEADEREND:event_maxTBackButtonActionPerformed
        Table1.setSelectedIndex(previous);
        //changes window to index held in previous on button press
    }//GEN-LAST:event_maxTBackButtonActionPerformed

    private void maxTMainScreenButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_maxTMainScreenButtonActionPerformed
    {//GEN-HEADEREND:event_maxTMainScreenButtonActionPerformed
        previous = Table1.getSelectedIndex();//sets variable to index of current screen.
        Table1.setSelectedComponent(MainScreen);//changes window on button press
    }//GEN-LAST:event_maxTMainScreenButtonActionPerformed

    private void maxTExitButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_maxTExitButtonActionPerformed
    {//GEN-HEADEREND:event_maxTExitButtonActionPerformed
        System.exit(0);//exits the program.
    }//GEN-LAST:event_maxTExitButtonActionPerformed

    private void mainScreenMaxTButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_mainScreenMaxTButtonActionPerformed
    {//GEN-HEADEREND:event_mainScreenMaxTButtonActionPerformed
        previous = Table1.getSelectedIndex();//sets variable to index of current screen.
        Table1.setSelectedComponent(MaxT);//changes window on button press
    }//GEN-LAST:event_mainScreenMaxTButtonActionPerformed

    private void mainEditMilkButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_mainEditMilkButtonActionPerformed
    {//GEN-HEADEREND:event_mainEditMilkButtonActionPerformed
        previous = Table1.getSelectedIndex();//sets variable to index of current screen.
        Table1.setSelectedComponent(EditMilk);//changes window on button press
    }//GEN-LAST:event_mainEditMilkButtonActionPerformed

    private void mainExitButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_mainExitButtonActionPerformed
    {//GEN-HEADEREND:event_mainExitButtonActionPerformed
        System.exit(0);//exits system on buton press
    }//GEN-LAST:event_mainExitButtonActionPerformed

    private void mainCowButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_mainCowButtonActionPerformed
    {//GEN-HEADEREND:event_mainCowButtonActionPerformed
        previous = Table1.getSelectedIndex();//sets variable to index of current screen.
        Table1.setSelectedComponent(EditCow);//changes window on button press
    }//GEN-LAST:event_mainCowButtonActionPerformed

    private void mainHerdButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_mainHerdButtonActionPerformed
    {//GEN-HEADEREND:event_mainHerdButtonActionPerformed
        previous = Table1.getSelectedIndex();//sets variable to index of current screen.
        Table1.setSelectedComponent(EditHerd);//changes window on button press
    }//GEN-LAST:event_mainHerdButtonActionPerformed

    private void mainFarmButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_mainFarmButtonActionPerformed
    {//GEN-HEADEREND:event_mainFarmButtonActionPerformed
        previous = Table1.getSelectedIndex();//sets variable to index of current screen.
        Table1.setSelectedComponent(EditFarm);//changes window on button press
    }//GEN-LAST:event_mainFarmButtonActionPerformed

    private void editHerd8_16RadioButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_editHerd8_16RadioButtonActionPerformed
    {//GEN-HEADEREND:event_editHerd8_16RadioButtonActionPerformed
        milkInt = "8_16";//sets radio button milkInt variable on selection
    }//GEN-LAST:event_editHerd8_16RadioButtonActionPerformed

    private void editHerd9_15RadioButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_editHerd9_15RadioButtonActionPerformed
    {//GEN-HEADEREND:event_editHerd9_15RadioButtonActionPerformed
        milkInt = "9_15";//sets radio button milkInt variable on selection
    }//GEN-LAST:event_editHerd9_15RadioButtonActionPerformed

    private void editMilkAddButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_editMilkAddButtonActionPerformed
    {//GEN-HEADEREND:event_editMilkAddButtonActionPerformed
        addMilkTakings();//calls gui method on button press
    }//GEN-LAST:event_editMilkAddButtonActionPerformed

    private void editMilkFarmListValueChanged(javax.swing.event.ListSelectionEvent evt)//GEN-FIRST:event_editMilkFarmListValueChanged
    {//GEN-HEADEREND:event_editMilkFarmListValueChanged
        farmIndex = editMilkFarmList.getSelectedIndex();//sets index of selected item
        displayHerd();//calls gui method on button press
    }//GEN-LAST:event_editMilkFarmListValueChanged

    private void editMilkHerdListValueChanged(javax.swing.event.ListSelectionEvent evt)//GEN-FIRST:event_editMilkHerdListValueChanged
    {//GEN-HEADEREND:event_editMilkHerdListValueChanged
        herdIndex = editMilkHerdList.getSelectedIndex();//sets index of selected item
        displayCow();//calls gui method on button press
    }//GEN-LAST:event_editMilkHerdListValueChanged

    private void editCowFarmMListValueChanged(javax.swing.event.ListSelectionEvent evt)//GEN-FIRST:event_editCowFarmMListValueChanged
    {//GEN-HEADEREND:event_editCowFarmMListValueChanged
        farmIndex = editCowFarmMList.getSelectedIndex();//sets index of selected item
        displayHerd();//calls gui method on button press
    }//GEN-LAST:event_editCowFarmMListValueChanged

    private void editCowHerdMListValueChanged(javax.swing.event.ListSelectionEvent evt)//GEN-FIRST:event_editCowHerdMListValueChanged
    {//GEN-HEADEREND:event_editCowHerdMListValueChanged
        herdIndex = editCowHerdMList.getSelectedIndex();//sets index of selected item
    }//GEN-LAST:event_editCowHerdMListValueChanged

    private void editHerdFarmListValueChanged(javax.swing.event.ListSelectionEvent evt)//GEN-FIRST:event_editHerdFarmListValueChanged
    {//GEN-HEADEREND:event_editHerdFarmListValueChanged
        farmIndex = editHerdFarmList.getSelectedIndex();//sets index of selected item
    }//GEN-LAST:event_editHerdFarmListValueChanged

    private void table1DoneActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_table1DoneActionPerformed
    {//GEN-HEADEREND:event_table1DoneActionPerformed
        jTable.editCellAt(-1, -1);//sets selected field to outside of jTable
        tableFilled = true;//sets boolean to true.
        addData8_16();//adds the data for milking interval 8_16 to HashMap
        addData9_15();//adds the data for milking interval 9_15 to HashMap
    }//GEN-LAST:event_table1DoneActionPerformed

    private void maxTCalculateButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_maxTCalculateButtonActionPerformed
    {//GEN-HEADEREND:event_maxTCalculateButtonActionPerformed
        int calc = JOptionPane.showConfirmDialog(null,
                "Have all milk takings been added? ", "",
                JOptionPane.YES_NO_OPTION);//check that all milk takings have been added
        if (calc == JOptionPane.YES_OPTION)//fires if user chooses yes
        {
            getMaxT();//gets the maxT figures
        }
    }//GEN-LAST:event_maxTCalculateButtonActionPerformed

    private void maxTHerdListValueChanged(javax.swing.event.ListSelectionEvent evt)//GEN-FIRST:event_maxTHerdListValueChanged
    {//GEN-HEADEREND:event_maxTHerdListValueChanged
        herdIndex = maxTHerdList.getSelectedIndex();//sets index of selected item
    }//GEN-LAST:event_maxTHerdListValueChanged

    private void maxTFarmListValueChanged(javax.swing.event.ListSelectionEvent evt)//GEN-FIRST:event_maxTFarmListValueChanged
    {//GEN-HEADEREND:event_maxTFarmListValueChanged
        farmIndex = maxTFarmList.getSelectedIndex();//sets index of selected item
    }//GEN-LAST:event_maxTFarmListValueChanged

    private void mainFarmListValueChanged(javax.swing.event.ListSelectionEvent evt)//GEN-FIRST:event_mainFarmListValueChanged
    {//GEN-HEADEREND:event_mainFarmListValueChanged
        farmIndex = mainFarmList.getSelectedIndex();//sets index of selected item
    }//GEN-LAST:event_mainFarmListValueChanged

    private void mainHerdListValueChanged(javax.swing.event.ListSelectionEvent evt)//GEN-FIRST:event_mainHerdListValueChanged
    {//GEN-HEADEREND:event_mainHerdListValueChanged
        farmIndex = mainFarmList.getSelectedIndex();//sets index of selected item
    }//GEN-LAST:event_mainHerdListValueChanged

    private void editFarmFarmListValueChanged(javax.swing.event.ListSelectionEvent evt)//GEN-FIRST:event_editFarmFarmListValueChanged
    {//GEN-HEADEREND:event_editFarmFarmListValueChanged
        farmIndex = mainFarmList.getSelectedIndex();//sets index of selected item
    }//GEN-LAST:event_editFarmFarmListValueChanged

    private void editHerdHerdListValueChanged(javax.swing.event.ListSelectionEvent evt)//GEN-FIRST:event_editHerdHerdListValueChanged
    {//GEN-HEADEREND:event_editHerdHerdListValueChanged
        farmIndex = mainFarmList.getSelectedIndex();//sets index of selected item
    }//GEN-LAST:event_editHerdHerdListValueChanged

    private void editMilkCowListValueChanged(javax.swing.event.ListSelectionEvent evt)//GEN-FIRST:event_editMilkCowListValueChanged
    {//GEN-HEADEREND:event_editMilkCowListValueChanged
        displayMilkTakings();
    }//GEN-LAST:event_editMilkCowListValueChanged

    /**
     * @param args the command line arguments
     */
    public static void main(String args[])
    {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try
        {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
            {
                if ("Nimbus".equals(info.getName()))
                {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }
        catch (ClassNotFoundException ex)
        {
            java.util.logging.Logger.getLogger(CalculateMaxTGUI.class.getName()).log(
                    java.util.logging.Level.SEVERE, null, ex);
        }
        catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(CalculateMaxTGUI.class.getName()).log(
                    java.util.logging.Level.SEVERE, null, ex);
        }
        catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(CalculateMaxTGUI.class.getName()).log(
                    java.util.logging.Level.SEVERE, null, ex);
        }
        catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(CalculateMaxTGUI.class.getName()).log(
                    java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                new CalculateMaxTGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel EditCow;
    private javax.swing.JPanel EditFarm;
    private javax.swing.JPanel EditHerd;
    private javax.swing.JPanel EditMilk;
    private javax.swing.JButton EditMilkTakingsBackButton;
    private javax.swing.JButton EditMilkTakingsExitButton;
    private javax.swing.JButton EditMilkTakingsMainButton;
    private javax.swing.JPanel MainScreen;
    private javax.swing.JPanel MaxT;
    private javax.swing.JPanel Shared;
    private javax.swing.JTabbedPane Table1;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton editCowAddButton;
    private javax.swing.JButton editCowBackButton;
    private m256gui.M256JList editCowCowList;
    private javax.swing.JButton editCowDeleteButton;
    private javax.swing.JButton editCowExitButton;
    private m256gui.M256JList editCowFarmMList;
    private m256gui.M256JList editCowHerdMList;
    private javax.swing.JTextField editCowIDTextField;
    private javax.swing.JButton editCowMainScreenButton;
    private javax.swing.JTextArea editCowTextArea;
    private javax.swing.JButton editFarmAddButton;
    private javax.swing.JButton editFarmBackButton;
    private javax.swing.JButton editFarmDeleteButton;
    private javax.swing.JButton editFarmEditHerdButton;
    private javax.swing.JButton editFarmExitButton;
    private m256gui.M256JList editFarmFarmList;
    private javax.swing.JTextField editFarmIDTextField;
    private javax.swing.JTextArea editFarmLocationTextArea;
    private javax.swing.JTextArea editFarmMessageTextArea;
    private javax.swing.JTextField editFarmNameTextField;
    private javax.swing.JRadioButton editHerd8_16RadioButton;
    private javax.swing.JRadioButton editHerd9_15RadioButton;
    private javax.swing.JButton editHerdAddButton;
    private javax.swing.JButton editHerdBackButton;
    private javax.swing.JButton editHerdDeleteButton;
    private javax.swing.JButton editHerdEditCowButton;
    private javax.swing.JButton editHerdExitButton;
    private m256gui.M256JList editHerdFarmList;
    private m256gui.M256JList editHerdHerdList;
    private javax.swing.JTextField editHerdIDTextField;
    private javax.swing.JTextField editHerdNameTextField;
    private javax.swing.JTextArea editHerdTextArea;
    private javax.swing.JButton editMilkAddButton;
    private m256gui.M256JList editMilkCowList;
    private m256gui.M256JList editMilkFarmList;
    private m256gui.M256JList editMilkHerdList;
    private javax.swing.JButton editMilkTakingUpdateButton;
    private javax.swing.JTextField editMilkTakingsAmTextField;
    private javax.swing.JButton editMilkTakingsDeleteButton;
    private javax.swing.JTextField editMilkTakingsPmTextField;
    private javax.swing.JTextArea editMilkTakingsTextArea;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane17;
    private javax.swing.JScrollPane jScrollPane18;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JTable jTable;
    private javax.swing.JButton mainCowButton;
    private javax.swing.JButton mainEditMilkButton;
    private javax.swing.JButton mainExitButton;
    private javax.swing.JButton mainFarmButton;
    private m256gui.M256JList mainFarmList;
    private javax.swing.JButton mainHerdButton;
    private m256gui.M256JList mainHerdList;
    private javax.swing.JButton mainScreenMaxTButton;
    private javax.swing.JTextField maxTAMTextField;
    private javax.swing.JButton maxTBackButton;
    private javax.swing.JButton maxTCalculateButton;
    private javax.swing.JButton maxTExitButton;
    private m256gui.M256JList maxTFarmList;
    private m256gui.M256JList maxTHerdList;
    private javax.swing.JButton maxTMainScreenButton;
    private javax.swing.JTextField maxTPMTextField;
    private javax.swing.JButton table1Done;
    // End of variables declaration//GEN-END:variables
}
