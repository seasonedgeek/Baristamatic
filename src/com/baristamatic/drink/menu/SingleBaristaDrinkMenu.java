/**
 * Provides runtime interaction with the Baristamatic Simulator menu.
 *
 * @since 1.0
 */
package com.baristamatic.drink.menu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import org.apache.commons.lang3.StringUtils;


/**
 * This class creates a single reusable menu of featured drinks for the
 * Baristamatic Simulator application.
 *
 * @author  Tom Stewart <pre>seasonedgeek@mac.com</pre>
 * 
 * @version 1.0, 09/14/11
 * @version 1.1, 09/15/11
 */
public class SingleBaristaDrinkMenu implements Menu {
    
    /* Constant for generating report and menu strings */
    private static final String COMMA = ",";
        
    /* Constant for regular expression */
    private static final String COMMA_SPACE = "\\, ";
    
    /* Constant for regular expression */
    private static final String EMPTY_STRING = "";
    
    /* Constant for regular expression */
    private static final String NO_BRACKETS = "[\\[]*[\\]]*";
    
    /* Constant for generating report and menu strings */
    private static final String SKIP_DOUBLE_LINES = "\n\n";
    
    /* Constant for menu */
    private static final String START_MENU = "\nMenu: ";
      
    /* Index positions of drink attributes */
    private static final int DRINK_NAME_INDEX = 0;
    private static final int DRINK_AVAILABLE_INDEX = 2;

    /* A list of drink attribute arrays */
    private ArrayList<String[]> drinkAttributes = null;

    // -----------------------------------------------------------
    
    /* A single inventory instance field. */
    private static SingleBaristaDrinkMenu instance = null;
    
    /* Prevents inheritance. */
    private SingleBaristaDrinkMenu(){}

    /** 
     * Returns an instance of this object.
     * @return instance
     */
    public static SingleBaristaDrinkMenu getInstance(){
        if (instance == null) {
            instance = new SingleBaristaDrinkMenu();
        }
        return instance;
    }
    
    // -----------------------------------------------------------
    
    /**
     * Returns a report of the depleted stock or replenished stock on the 
     * inventory.
     * 
     * @return String
     * 
     * getFeaturedDrinksMenu
     */
    @Override
    public String getFeaturedDrinksMenu(boolean noPrompt) {

        sortDrinksByName();

        StringBuilder builder = new StringBuilder();
        builder.append(START_MENU);
        builder.append(SKIP_DOUBLE_LINES);
                
        /*
         * Append drink attribute to menu (i.e. #,drinkName,$N.NN,true||false)
         */
        int index = 1;
        for (String[] menuItem : this.drinkAttributes) {
            builder.append(index);
            builder.append(COMMA);
            String lineItem = getCleanMenuItem(Arrays.toString(menuItem));
            builder.append(lineItem);
            builder.append(SKIP_DOUBLE_LINES);
            index++;
        }
        
        if (!noPrompt) {
            builder.append(StringUtils.repeat('-', 40));
            builder.append("Enter a number 1..6 to order a drink.");
            builder.append(SKIP_DOUBLE_LINES);
            builder.append("Enter: R -OR- r to restock the inventory.");
            builder.append(SKIP_DOUBLE_LINES);
            builder.append("Enter: Q -OR- q to quit simulator.");
            builder.append(StringUtils.repeat('-', 40));
            builder.append("Enter your selection: ");
        } 
                
        // Menu completed
        String newMenu = builder.toString();
        return newMenu;
    }
  
    /**
     * This method is called once during the initialization of a featured drink.
     * An Array of drink attributes is added an ArrayList, such as
     * <pre>[<name of drink>,<price of drink>,<availability>]</pre>.
     * 
     * @param drinkName     A String element
     * @param drinkCost     A formatted String element 
     */
    @Override
    public void setDrinkAttributes(String drinkName, String drinkCost) {
        
        if (this.drinkAttributes == null) {
            this.drinkAttributes = new ArrayList<String[]>();
        }
        
        String inStock = Boolean.toString(true); // default availability
        String[] attributes = new String[] { drinkName, drinkCost, inStock };
        
        this.drinkAttributes.add(attributes);
    }
    
    /**
     * This method is called frequently, during business hours, to update
     * the availability of a featured drink on the menu.
     * 
     * @param drinkName     A String element
     * @param inStock       A boolean value, such that
     *                      <code>true</code>    in-stock.
     *                      <code>false</code>   out-of-stock. 
     */
    @Override
    public void updateMenuDrinkAvailability(String drinkName, boolean inStock) {
        try {            
            for(String[] attributes : drinkAttributes) {
                String targetName = attributes[DRINK_NAME_INDEX].toString();

                if (drinkName.compareTo(targetName) == 0) {
                    String stocked = Boolean.toString(inStock);
                    attributes[DRINK_AVAILABLE_INDEX] = stocked;
                    break;
                }
            }    
        }
        catch(NullPointerException npe) {
            System.err.println(npe.getMessage());
        }
    }
    
    // -----------------------------------------------------------------------
    
    /* 
     * This method strips String of brackets, and extra white spaces.
     * 
     * @param   lineItem    <pre>["name of drink", "price of drink", "availability"]</pre>
     * @return  String      <pre> "name of drink","price of drink","availability"</pre>
     * 
     */
    private String getCleanMenuItem(String lineItem) {            
        lineItem = lineItem.replaceAll(NO_BRACKETS, EMPTY_STRING);
        return(lineItem.replaceAll(COMMA_SPACE, COMMA));
    }
    
    /**
     * This method sorts the collection of drink attributes by name.
     */
    private void sortDrinksByName() {
        
        Collections.sort(this.drinkAttributes, new Comparator<String[]>() {
            
            @Override
            public int compare(String[] attributes, String[] otherAttributes) {

                return attributes[DRINK_NAME_INDEX].compareToIgnoreCase(
                        otherAttributes[DRINK_NAME_INDEX] );

            } 
            
        });  
    }
    
}
