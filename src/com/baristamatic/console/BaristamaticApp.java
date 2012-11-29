/**
 * Provides runtime access to the Baristamatic Simulator main application.
 *
 * @since 1.0
 */
package com.baristamatic.console;

import com.baristamatic.drink.CaffeAmericanoBaristaDrink;
import com.baristamatic.drink.CaffeLatteBaristaDrink;
import com.baristamatic.drink.CaffeMochaBaristaDrink;
import com.baristamatic.drink.CappuccinoBaristaDrink;
import com.baristamatic.drink.CoffeeBaristaDrink;
import com.baristamatic.drink.DecafCoffeeBaristaDrink;
import com.baristamatic.drink.menu.SingleBaristaDrinkMenu;
import com.baristamatic.inventory.SingleBaristaInventory;

import java.util.Formatter;
import java.util.Scanner;

/**
 * This is the main application class for the Baristamatic Simulator, an 
 * automatic coffee dispensing machine. The Baristamatic Simulator is capable 
 * of dispensing a fixed number of drinks by combining ingredients from its 
 * running inventory, in different amounts.
 * 
 * <p>
 * The simulator is executed by entering the following command in a terminal 
 * window.
 * 
 * <p>
 * <code>java -jar /pathToFile/Baristamatic.jar</code>.
 * 
 * <p>
 * The Baristamatic Simulator displays its current inventory, and a menu of
 * featured drinks.  A user may select any number of available drinks, so long 
 * as there are sufficient ingredients on-hand to create drinks. The running
 * inventory is updated as drinks are dispensed. Messages are displayed to
 * provide feedback. The user may replenish the inventory whenever desired.
 * 
 * @author  Tom Stewart <pre>seasonedgeek@mac.com</pre>
 * 
 * @version 1.0, 09/05/11
 * @version 1.1, 09/08/11
 * @version 1.2, 09/14/11
 * @version 1.3, 09/15/11 
 */
public class BaristamaticApp {
        
    /** Constant for invalid menu entry: <pre>"Invalid selection: %1$s"</pre> */
    private static final String INVALID_ENTRY_TEMPLATE = "Invalid selection: %1$s";

    /** Object instance variables */
    private static SingleBaristaInventory inventory = null;
    private static SingleBaristaDrinkMenu drinkMenu = null;
    
    private static CaffeAmericanoBaristaDrink caffeAmericano = null;
    private static CaffeLatteBaristaDrink caffeLatte = null;
    private static CaffeMochaBaristaDrink caffeMocha = null;
    private static CappuccinoBaristaDrink cappuccino = null;
    private static CoffeeBaristaDrink coffee = null;
    private static DecafCoffeeBaristaDrink decafCoffee = null;
    
    private static Scanner scanner = null;
                
    /**
     * This is the main "command line driven" application.
     * 
     * @param  args         the command line arguments
     * @throws Exception  
     */
    public static void main(String[] args) throws Exception {
                       
        // Set up a single, distinct inventory
		inventory = SingleBaristaInventory.getInstance();
        inventory.stockInventory();
        inventory.priceInventory();
        
        // Set up a single, distinct menu of featured drinks
        drinkMenu = SingleBaristaDrinkMenu.getInstance();
        
        coffee = new CoffeeBaristaDrink();
        coffee.initBaristaDrink();
        
        decafCoffee = new DecafCoffeeBaristaDrink();
        decafCoffee.initBaristaDrink();

        caffeAmericano = new CaffeAmericanoBaristaDrink();
        caffeAmericano.initBaristaDrink();
        
        caffeLatte = new CaffeLatteBaristaDrink();
        caffeLatte.initBaristaDrink();
        
        caffeMocha = new CaffeMochaBaristaDrink();
        caffeMocha.initBaristaDrink();
        
        cappuccino = new CappuccinoBaristaDrink();
        cappuccino.initBaristaDrink();

        // Ready, set, go ...
        boolean isQuitting = false;
        boolean noPrompt = true;

        // Execute Baristamatic's event loop
        int commandSelection = 0;
        scanner = new Scanner(System.in);        
        while(true) {
                    
            // Always start clean 
            StringBuilder builder = new StringBuilder();
            
            // Report availability of ingredients on inventory
            builder.append(inventory.getAvailableInventoryUnits());
            
            // Report availability of featured drinks
            builder.append(drinkMenu.getFeaturedDrinksMenu(noPrompt));
            
            // Display reports
            System.out.print(builder.toString());
            
            String mySelection = "";
            try {
                // Capture a single character or a sequence of characters
                mySelection = scanner.nextLine();

                /*
                 * The first character in the mySelection field is of interest.
                 * Convert character to Unicode, such as:
                 * 
                 *                        {'1..6'=1..6; 'Q|q'=26; 'R|r'=27}
                 */
                commandSelection = Character.getNumericValue(
                        mySelection.charAt(0));
            }
            catch (IndexOutOfBoundsException ibe) {
                System.err.println(ibe.getMessage());            
            }
            catch (Exception e) {
                System.err.println(e.getMessage());
            }

            //
            // Dispese drink equal to menu unicode number
            String actionTaken = "";
            switch(commandSelection) {

                case 1:
                    actionTaken = caffeAmericano.dispenseDrink();
                    break;
                case 2:
                    actionTaken = caffeLatte.dispenseDrink();
                    break;
                case 3:
                    actionTaken = caffeMocha.dispenseDrink();
                    break;
                case 4:
                    actionTaken = cappuccino.dispenseDrink();
                    break;
                case 5:
                    actionTaken = coffee.dispenseDrink();
                    break;
                case 6:
                    actionTaken = decafCoffee.dispenseDrink();
                    break;
                case 26:
                    isQuitting = true;          // quitting
                    break;
                case 27:                    
                    inventory.stockInventory(); // restock & sort inventory
                    break;

                default: 
                    Formatter formatter = new Formatter();                    
                    actionTaken = formatter.format(INVALID_ENTRY_TEMPLATE,
                            mySelection).toString();
                    break;                      // not necessary; just consist
                    
            } // End of switch commandSelection

            if (isQuitting)
                break;
            
            if (!actionTaken.isEmpty())
                System.out.println(actionTaken);
                    
            /*
             * Update drink availability right away.
             * 
             * We want to encourage this type of behavior ...
             * 
             * If Espresso units = 0 in the inventory, then all espresso based 
             * drinks should set their <in-stock> attribute to false, if and 
             * only if, there are insufficient resources available to make 
             * a featured type of Espresso drink.
             * 
             */
            caffeAmericano.doubleCheckAvailability();
            caffeLatte.doubleCheckAvailability();
            caffeMocha.doubleCheckAvailability();
            cappuccino.doubleCheckAvailability();
            coffee.doubleCheckAvailability();
            decafCoffee.doubleCheckAvailability();    
               
        } // End of "event" loop
        scanner.close();
 
    } // End of main application
    
} // End of class
