/**
 * Provides runtime interaction with the Baristamatic Simulator menu.
 *
 * @since 1.0
 */
package com.baristamatic.drink.menu;

/**
 * This interface provides access to a menu of featured drinks on the 
 * Baristamatic Simulator user control panel.
 * 
 * @author  Tom Stewart <pre>seasonedgeek@mac.com</pre>
 * 
 * @version 1.0, 09/15/11
 */
public interface Menu {

    /**
     * Returns  optional instructions on how to select a featured drink on the 
     * menu, or replenish an inventory of ingredients, or exit the application.  
     *
     * @param  noPrompt     A boolean, when <code>true</code> includes prompt, 
     *                      else <code>false</code> excludes it.
     * @return String       A formatted String element. 
     */
    public String getFeaturedDrinksMenu(boolean noPrompt);
    
  
    /**
     * Subclasses, should call this method, once per featured drink during 
     * initialization, after instantiation.
     * 
     * @param drinkName     A String element, identifies the drink by name. 
     * @param drinkCost     A formatted String element, such as $N.NN, 
     *                      the cost of a drink. 
     */
    public void setDrinkAttributes(String drinkName, String drinkCost);
    
    
    /**
     * Subclasses, should call this method, frequently during business hours,
     * to update the availability of a named drink offered on a menu.
     * 
     * @param drinkName     A String element, identifies the drink by name.
     * @param inStock       A boolean value, when <code>true</code> is in-stock,
     *                      else <code>false</code> is out-of-stock. 
     */
    public void updateMenuDrinkAvailability(String drinkName, boolean inStock);
    
}
