package com.baristamatic.drink;

import com.baristamatic.drink.menu.SingleBaristaDrinkMenu;
import com.baristamatic.inventory.SingleBaristaInventory;
import java.util.HashMap;

/**
 * This featured drink class creates a unique recipe of component ingredients. 
 *
 * @author  Tom Stewart <pre>seasonedgeek@mac.com</pre>
 * 
 * @version 1.0, 09/04/11
 * @version 1.1, 09/08/11
 * @version 1.2, 09/15/11
 */
public class DecafCoffeeBaristaDrink extends BaristaDrink {
    
    /* Name of this featured coffee drink. */
    private final String DECAF = "Decaf Coffee"; 
    
    /* A collection of component ingredients. */ 
    private HashMap<String, Integer> ingredients = null;
    
    /* An drink menu instance member. */ 
    private SingleBaristaDrinkMenu drinkMenu = null;
    
    /* An instance member. */ 
    private SingleBaristaInventory inventory = null;    

    /**
     * Constructor
     */
    public DecafCoffeeBaristaDrink() {               
        inventory = super.getSingleBaristaInventory();        
        drinkMenu = super.getSingleBaristaDrinkMenu();
    }
    
    /**
     * This method creates a unique identity and recipe for a Decaf Coffee drink.
     */
    @Override
    public void initBaristaDrink() {
        
        this.setName(DECAF);
        
        // create a unique (one-of-a-kind) recipe for "Decaf Coffee"
        ingredients = new HashMap<String, Integer>();
        ingredients.put(DECAF, 3);
        ingredients.put("Sugar", 1);
        ingredients.put("Cream", 1);
        this.setRecipe(ingredients);
    
        String totalCost = inventory.getBaristaDrinkCost(this.getRecipe());
        
        // Add drink to menu array list
        drinkMenu.setDrinkAttributes(this.getName(), totalCost);
    }

    /**
     * Returns a <pre>"dispensed"</pre> or <pre>"out-of-stock"</pre> drink message.
     * 
     * @return String
     */
    @Override
    public String dispenseDrink() {        
        String message = "";
        String template= "";
        
        // Is drink possibly unavailable?
        if (doubleCheckAvailability()) {
            inventory.consumeResources(this.getRecipe());           
            template = DISPENSING;
        }
        else {
            template = OUT_OF_STOCK;
        }        
        
        return super.dispenseDrinkMessage(template);
    }
    
    // -----------------------------------------------------------------------
    
    /**
     * Returns availability of featured drink in inventory.
     * 
     * @return boolean  Drink is in-stock (<code>true</code>), or 
     *                  out-of-stock (<code>false</code>). 
     */
    @Override
    public boolean doubleCheckAvailability() { 
        return super.doubleCheckAvailability();
    }
}
