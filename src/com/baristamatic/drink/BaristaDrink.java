package com.baristamatic.drink;

import com.baristamatic.drink.menu.SingleBaristaDrinkMenu;
import com.baristamatic.inventory.SingleBaristaInventory;
import java.util.Formatter;
import java.util.HashMap;

/**
 * This class represents the common features and operations a Baristamatic 
 * machine employs when checking inventory, preparing, dispensing, and
 * communicating with a customer about the coffee flavored drink. 
 * 
 * @author    Tom Stewart <pre>seasonedgeek@mac.com</pre>
 * 
 * @version 1.0, 09/04/11
 * @version 1.1, 09/08/11
 * @version 1.2, 09/15/11
 */
public abstract class BaristaDrink {
       
    /** Constant for Dispensing template: <pre>"\nDispensing: %1$s"</pre> */
    public final String DISPENSING = "\nDispensing: %1$s";

    /** Constant for Out-of-Stock template: <pre>"\nOut of stock: %1$s"</pre> */
    public final String OUT_OF_STOCK = "\nOut of stock: %1$s";

    /** Name of a featured drink. */
    private String name;
    
    /** Is the component ingredient "on-hand" in the inventory? */
    private boolean inStock;
    
    /** A decimal formatted summary cost of a featured drink. */
    private String cost;
    
    /** A single inventory instance field. */
    private SingleBaristaInventory singleBaristaInventory = null;
    
    /** A single drink menu instance field. */
    private SingleBaristaDrinkMenu singleBaristaDrinkMenu = null;

    /** Maps a featured drink recipe. */
    private HashMap<String, Integer> recipe;
    
    //-------------------------------------------------------------------------

    /** Sets instances to a distinct inventory and a distinct menu. */ 
    public BaristaDrink() {
        singleBaristaDrinkMenu = SingleBaristaDrinkMenu.getInstance();
        singleBaristaInventory = SingleBaristaInventory.getInstance();
    }
    

    /**
     * Returns a final reference to a menu.
     * 
     * @return  SingleBaristaDrinkMenu   instance.
     */
    public final SingleBaristaDrinkMenu getSingleBaristaDrinkMenu() {
        return singleBaristaDrinkMenu;
    }
    

    /**
     * Returns a final reference to the inventory.
     * 
     * @return  SingleBaristaInventory   instance.
     */
    public final SingleBaristaInventory getSingleBaristaInventory() {
        return singleBaristaInventory;
    }
    
    
    /**
     * Returns the availability of a featured drink in inventory.
     * 
     * @return boolean  Drink is in-stock (<code>true</code>), or out-of-stock 
     *                  (<code>false</code>). 
     */    
    public boolean doubleCheckAvailability() { 
        boolean doubleCheck = singleBaristaInventory.isBaristaDrinkAvailable(
                this.getRecipe());
        
        singleBaristaDrinkMenu.updateMenuDrinkAvailability(
                this.getName(), doubleCheck);
        
        return doubleCheck;
    }


    /**
     * Returns the cost of a featured drink, as determined by the sum of its
     * component ingredient costs.
     * 
     * @return String   <pre>$N.NN</pre>
     */ 
    public String getCost() {        
        return this.cost;
    }
    

    /**
     * Sets the cost of a featured drink, as determined by the sum of its
     * component ingredient costs.
     * 
     * @param cost 
     */
    public void setCost(String cost) {
        this.cost = cost;
    }
    

    /**
     * Returns the state of a featured drink, based on the availability of
     * its component ingredients in the inventory.
     * 
     * @return boolean  Drink is in-stock (<code>true</code>), or out-of-stock 
     *                  (<code>false</code>).
     */
    public boolean isInStock() {
        return inStock;
    }
    

    /**
     * This method sets the state of a featured drink, based on the availability of
     * its component ingredients in the inventory.
     * 
     * @param stockState 
     */
    public void setInStock(boolean stockState) {
        this.inStock = stockState;
    }
    

    /**
     * Returns the name of a featured drink.
     * 
     * @return String   name of a drink 
     */
    public String getName() {
        return name;
    }
    

    /**
     * This method sets the name of a featured drink.
     * 
     * @param name  A String element  
     */
    public void setName(String name) {
        this.name = name;
    }
    

    /**
     * Returns a unique recipe for a featured drink.
     * 
     * @return HashMap<String, Integer>
     *           String: Ingredient keys maintained by this map.
     *          Integer: Mapped values.
     */
    public HashMap<String, Integer> getRecipe() {
        return recipe;
    }
    

    /**
     * This method sets the recipe of a featured drink.
     * 
     * @param recipe    HashMap<String, Integer>
     *                  String: Ingredient keys maintained by this map.
     *                 Integer: Mapped values. 
     */
    public void setRecipe(HashMap<String, Integer> recipe) {
        this.recipe = recipe;
    }
    
    
    /**
     * This method sets the name and cost attributes of a featured drink.
     */
    public void setDrinkAttributes() {
        this.singleBaristaDrinkMenu.setDrinkAttributes(this.getName(), 
                this.getCost());
    }
    
    
    /**
     * An overrideable method that should return a user message about a 
     * featured drink.
     *  
     * @return String 
     */
    public String dispenseDrink() { return ""; }
    
    
    /**
     * Returns a formatted message about a featured drink.
     * 
     * @param template  A constant field value.
     * @return String
     */
    public String dispenseDrinkMessage(String template) {
        
        Formatter formatter = new Formatter();
        String message = formatter.format(template, this.getName()).toString();
        
        return message;
    }
    
    /**
     * Initializes the Barista Drink object after being fully instantiated.
     */
    public void initBaristaDrink() {}
    
    
}   // End of Abstract Class
