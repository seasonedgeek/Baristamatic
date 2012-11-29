/**
 * Presents the state of the Baristamatic Simulator inventory as resources 
 * are consumed or replenished.
 *
 * @since 1.0
 */
package com.baristamatic.inventory;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * 
 * This class provides access to a single and distinct inventory of component
 * ingredients for the Baristamatic simulator.
 * 
 * @author  Tom Stewart <pre>seasonedgeek@mac.com</pre>
 * 
 * @version 1.0, 09/04/11
 * @version 1.1, 09/08/11
 * @version 1.2, 09/14/11 
 */
public class SingleBaristaInventory implements Inventory {

    /* Constant for printing inventory */
    private static final String COMMA = ",";
    
    /* Constant for no cost */
    private static final String NO_COST = "$0.00";
    
    /* Constant for printing inventory */
    private static final String SKIP_DOUBLE_LINES = "\n\n";
        
    /* Constant for printing inventory */
    private static final String START_INVENTORY = "\nInventory: ";

    /* Maps each component ingredient to its amount. */
    private HashMap<String, Integer> ingredientUnits = null;
    
    /* Maps each component ingredient to its cost. */
    private HashMap<String, Double>  ingredientCosts = null;
    
    /* Maps sorted component ingredients to their amounts. */
    private TreeMap<String, Integer> sortedIngredientUnits = null;
    
    /* A single inventory instance field. */
    private static SingleBaristaInventory instance = null;
    
    // ------------------------------------------------------------------------
    
    /* Prevents inheritance. */
    private SingleBaristaInventory(){}

    /** 
     * Returns an instance of this object.
     * @return instance
     */
    public static SingleBaristaInventory getInstance(){
        if (instance == null) {
            instance = new SingleBaristaInventory();
        }
        return instance;
    }
        
    /**
     * Returns the availability of a featured drink by determining if 
     * sufficient ingredients are "on hand" in the inventory.
     *
     * @param recipe 
     * @return boolean  <code>true</code>  amounts are <pre> &le; </pre> to inventory.
     *                  <code>false</code> otherwise. 
     */
    @Override
    public boolean isBaristaDrinkAvailable(HashMap<String, Integer> recipe) {        
        boolean available = true;
        try {            
            Set<Map.Entry<String, Integer>> consumables = recipe.entrySet();        
            Set<Map.Entry<String, Integer>> resources = this.sortedIngredientUnits.entrySet();

            int consumableUnits = 0;
            int resourceUnits = 0;

            boolean availability[] = new boolean[consumables.size()];
            int recipeIngredientIndex = 0;                  

            String consumableKey = "";        
            for (Map.Entry<String, Integer> consumable : consumables) {

                consumableKey = consumable.getKey().toString();
                consumableUnits = consumable.getValue().intValue();

                for (Map.Entry<String, Integer> ingredient : resources) {
                    if (ingredient.getKey().equals(consumableKey)) {
                        resourceUnits = ingredient.getValue().intValue();
                        availability[recipeIngredientIndex] = (consumableUnits <= resourceUnits);
                        break;
                    }
                }
                recipeIngredientIndex++;
            }

            // evaluate target availability
            for (int i=0; i < availability.length; i++)
                available = (available && availability[i]);  
        }
        catch(NullPointerException npe) {
            System.err.println(npe.getMessage());
            available = false;
        }
        return available;
    }

    /**
     * Returns the summary cost of the component ingredient units combined 
     * to produce a featured drink.
     * 
     * @param recipe 
     * @return  String <pre>$N.NN</pre> formatted 
     */
    @Override
    public String getBaristaDrinkCost(HashMap<String, Integer> recipe) {        
        String realCost = "";        
        int consumableUnits = 0;
        try {
            Set<Map.Entry<String, Integer>> consumables = recipe.entrySet();
            Set<Map.Entry<String, Double>> ingredientCost = this.ingredientCosts.entrySet();
            
            Double unitCost = 0.0;
            Double totalCost = 0.0;
            String consumableKey = "";

            // step through recipe and calculate total cost of resources
            for (Map.Entry<String, Integer> consumable : consumables) {
                consumableKey  = consumable.getKey().toString();
                consumableUnits = consumable.getValue().intValue();

                for (Map.Entry<String, Double> ingredient : ingredientCost) {
                    if (ingredient.getKey().equals(consumableKey)) {
                        unitCost = ingredient.getValue().doubleValue();
                        break;
                    }
                }
                totalCost += (unitCost * consumableUnits);
                unitCost = 0.0;
            }
            realCost = getDoubleDollars(totalCost);
        }
        catch(NullPointerException npe) {
            System.err.println(npe.getMessage());
            realCost = NO_COST;
        }
        return realCost;
    }

    /**
     * Returns a report of the depleted stock or replenished stock on the 
     * inventory.
     * 
     * @return String 
     */
    @Override
    public String getAvailableInventoryUnits() {
        
        StringBuilder builder = new StringBuilder();
        builder.append(START_INVENTORY);
        builder.append(SKIP_DOUBLE_LINES);
        
        try {
            Set<Map.Entry<String, Integer>> resources = sortedIngredientUnits.entrySet();

            for (Map.Entry<String, Integer> resourceAmounts : resources) {
                builder.append(resourceAmounts.getKey().toString());
                builder.append(COMMA);
                builder.append(resourceAmounts.getValue().intValue());
                builder.append(SKIP_DOUBLE_LINES);
            }
        }
        catch(NullPointerException npe) {
            System.err.println(npe.getMessage());
        }
        
        return builder.toString();
    }
    
    /**
     * This method consumes resources during drink preparation. It reduces
     * the total number of in-stock units, per ingredient, as directed by the 
     * drink recipe.   
     * 
     * @param recipe 
     */
    @Override
    public void consumeResources(HashMap<String, Integer> recipe) {                
        try {
            Set<Map.Entry<String, Integer>> consumables = recipe.entrySet();
            Set<Map.Entry<String, Integer>> resources = this.sortedIngredientUnits.entrySet();
            
            int resourceUnits = 0;
            int consumableUnits = 0;
            String consumableName = "";

            for (Map.Entry<String, Integer> consumable : consumables) {
                consumableName  = consumable.getKey().toString();
                consumableUnits = consumable.getValue().intValue();

                for (Map.Entry<String, Integer> resource : resources) {
                    // search for this named ingredient
                    if (resource.getKey().equals(consumableName)) {
                        resourceUnits = resource.getValue().intValue();
                        resourceUnits -= consumableUnits;
                        resource.setValue(resourceUnits);
                        break;
                    }
                }
            }            
        }
        catch(NullPointerException npe) {
            System.err.println(npe.getMessage());
        }          
    }

    /**
     * This method generates a price list of component ingredients.
     */
    @Override
    public void priceInventory() { 
        ingredientCosts = new HashMap<String, Double>();        
        
        // add unit cost of ingredients
        try {
            ingredientCosts.put("Coffee", 0.75);
            ingredientCosts.put("Decaf Coffee", 0.75);
            ingredientCosts.put("Sugar", 0.25);
            ingredientCosts.put("Cream", 0.25);
            ingredientCosts.put("Steamed Milk", 0.35);
            ingredientCosts.put("Foamed Milk", 0.35);
            ingredientCosts.put("Espresso", 1.10);
            ingredientCosts.put("Cocoa", 0.90);
            ingredientCosts.put("Whipped Cream", 1.00);
        }
        catch(ClassCastException cce) {
            System.err.println(cce.getMessage());
        }
        catch(NullPointerException npe) {
            System.err.println(npe.getMessage());
        }       
    }

    /**
     * This method replenishes the inventory by setting amounts 
     * to 10 units each per ingredient.
     */
    @Override
    public void stockInventory() {  
        if (ingredientUnits == null)
            ingredientUnits = new HashMap<String, Integer>();

        try {
            ingredientUnits.put("Cocoa", 10);
            ingredientUnits.put("Coffee", 10);
            ingredientUnits.put("Cream", 10);
            ingredientUnits.put("Decaf Coffee", 10);
            ingredientUnits.put("Espresso", 10);
            ingredientUnits.put("Foamed Milk", 10);
            ingredientUnits.put("Steamed Milk", 10);
            ingredientUnits.put("Sugar", 10);
            ingredientUnits.put("Whipped Cream", 10);
        }
        catch(ClassCastException cce) {
            System.err.println(cce.getMessage());
        }
        catch(NullPointerException npe) {
            System.err.println(npe.getMessage());
        }
        sortInventoryHelper();
    }
    
    // -----------------------------------------------------------
    
    /**
     * Returns Double number as $N.NN.
     * 
     * @param totalCost
     * @return String 
     */
    private String getDoubleDollars(Double totalCost) {
        DecimalFormat costFormatter = new DecimalFormat(NO_COST);
        return costFormatter.format(totalCost);
    }
    
    /*
     * This method creates a sorted map of resources on the inventory.
     */
    private void sortInventoryHelper() {  
        if (sortedIngredientUnits == null)
            sortedIngredientUnits = new TreeMap<String, Integer>();
        else
            sortedIngredientUnits.clear(); 
        
        
        try { 
            sortedIngredientUnits.putAll(ingredientUnits);
        }
        catch(ClassCastException cce) {
            System.err.println(cce.getMessage());
        }
        catch(NullPointerException npe) {
            System.err.println(npe.getMessage());
        }
    } 
}
