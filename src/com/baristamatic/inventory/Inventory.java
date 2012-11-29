/**
 * Presents the state of the Baristamatic Simulator inventory as resources 
 * are consumed or replenished.
 *
 * @since 1.0
 */
package com.baristamatic.inventory;

import java.util.HashMap;

/**
 * This interface provides access to a distinct single inventory and operations
 * for the Baristamatic Simulator application.
 * 
 * @author  Tom Stewart <pre>seasonedgeek@mac.com</pre>
 * 
 * @version 1.0, 9/4/2011 
 */
public interface Inventory {

    /** 
     * Returns the state of whether or not there are sufficient ingredients 
     * <pre>"on hand"}, on the inventory for drink preparation.
     *
     * @param recipe 
     * @return boolean  The drink is in-stock (<code>true</code>), or
     *                  out-of-stock (<code>false</code>).
     * 
     */
    public boolean isBaristaDrinkAvailable(HashMap<String, Integer> recipe);
    
    /**
     * Returns the cost of a featured drink as determined by the individual 
     * costs of its component ingredients. 
     * 
     * @param recipe 
     * @return String   <pre>$N.NN</pre>
     */
    public String getBaristaDrinkCost(HashMap<String, Integer> recipe);
    
    /**
     * Returns a report on whether or not, there are sufficient overall 
     * ingredients "on hand", on the inventory to continue dispensing drinks.
     * 
     * @return String  
     */
    public String getAvailableInventoryUnits();
    
    /**
     * Reduces resources in the inventory by the number of units found in the
     * recipe of a featured drink.
     * 
     * @param recipe 
     */ 
    public abstract void consumeResources(HashMap<String, Integer> recipe);
    
    /**
     * Generates a price list for component ingredients.
     */
    public abstract void priceInventory();
    
    /**
     * Replenishes the inventory of component ingredients. The default amount 
     * for all ingredients is ten (10).
     */
    public abstract void stockInventory();
    
}
