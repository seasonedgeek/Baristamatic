package com.baristamatic.inventory;

import java.util.ArrayList;
import java.util.HashMap;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author  Tom Stewart (seasonedgeek@mac.com)
 */
public class SingleBaristaInventoryTest {
    
    SingleBaristaInventory instance = null;
    
    ArrayList<HashMap> recipes = null;
    HashMap<String, Integer> recipe1 = null;
    
    String[] drinks = null;
    String[] ingredients = null;
    
    public SingleBaristaInventoryTest() {
        instance = SingleBaristaInventory.getInstance();
        instance.stockInventory();
        instance.priceInventory();
    }
    
    @Before
    public void setUp() {
        recipe1 = new HashMap<String, Integer>();
        recipe1.put("Coffee", 3);
        recipe1.put("Sugar", 1);
        recipe1.put("Cream", 1);
        
        recipes = new ArrayList<HashMap>();
        recipes.add(recipe1);
        
        HashMap<String, Integer> recipe2 = new HashMap<String, Integer>();
        recipe2.put("Decaf Coffee", 3);
        recipe2.put("Sugar", 1);
        recipe2.put("Cream", 1);        
        recipes.add(recipe2);
        
        HashMap<String, Integer> recipe3 = new HashMap<String, Integer>();
        recipe3.put("Espresso", 2);
        recipe3.put("Steamed Milk", 1);       
        recipes.add(recipe3);
        
        HashMap<String, Integer> recipe4 = new HashMap<String, Integer>();
        recipe4.put("Espresso", 3);        
        recipes.add(recipe4);
        
        HashMap<String, Integer> recipe5 = new HashMap<String, Integer>();
        recipe5.put("Espresso", 1);
        recipe5.put("Cocoa", 1);
        recipe5.put("Steamed Milk", 1);       
        recipe5.put("Whipped Cream", 1);        
        recipes.add(recipe5);
        
        HashMap<String, Integer> recipe6 = new HashMap<String, Integer>();
        recipe6.put("Espresso", 2);
        recipe6.put("Steamed Milk", 1);       
        recipe6.put("Foamed Milk", 1);       
        recipes.add(recipe6);
        
        drinks = new String[] { 
            "Coffee", "Decaf Coffee", "Caffe Latte", 
            "Caffe Americano", "Caffe Mocha", "Cappuccino"   
        };
        
        ingredients = new String[] { 
            "$0.75", "$0.75", "$0.25", "$0.25", "$0.35", 
            "$0.35", "$1.10", "$0.90", "$1.00"
        };
    }
    
    @After
    public void tearDown() {
        
        // clean up ...
        recipe1.clear();
        if (recipe1.isEmpty())
            recipe1 = null;
        
        recipes.clear();
        if (recipes.isEmpty())
            recipes = null;
        
        ingredients = null;
        instance = null;
    }

    /**
     * Test of isBaristaDrinkAvailable method, of class SingleBaristaInventory.
     */
    @Test
    public void testIsBaristaDrinkAvailable() {
        System.out.println("---------- isBaristaDrinkAvailable ----------");
        boolean expResult = true;
        
        int count = 0;
        for (HashMap hmRecipe : recipes) {
            boolean result = instance.isBaristaDrinkAvailable(hmRecipe);
            assertEquals(expResult, result);
            count++;
        }
    }

    /**
     * Test of getBaristaDrinkCost method, of class SingleBaristaInventory.
     */
    @Test
    public void testGetBaristaDrinkCost() {
        System.out.println("---------- getBaristaDrinkCost ----------");
        
        int count = 0;
        for (HashMap hmRecipe : recipes) {            
            String result = instance.getBaristaDrinkCost(hmRecipe);            
            String expResult = ingredients[count];            
            assertTrue(result.compareToIgnoreCase(expResult) >= 0);
        }
    }

    /**
     * Test of getAvailableUnits method, of class SingleBaristaInventory.
     */
    @Test
    public void testGetAvailableUnits() {
        System.out.println("---------- getAvailableUnits ----------");
        
        String expResult = "";
        
        String result = instance.getAvailableInventoryUnits();
        
        assertTrue(result.length() > expResult.length());
    }

    /**
     * Test of stockInventory method, of class SingleBaristaInventory.
     */
    @Test
    public void testStockInventory() {
        System.out.println("---------- stockInventory ----------");
        
        instance.stockInventory();
        
        System.out.print("\nINITIALIZED: ");
        System.out.println(instance.getAvailableInventoryUnits());
        
        instance.consumeResources(recipe1);

        System.out.print("\nCONSUMED: ");
        System.out.println(instance.getAvailableInventoryUnits());

        
        instance.stockInventory();
        
        System.out.print("\nRESTOCKED: ");
        System.out.println(instance.getAvailableInventoryUnits());
    }
}
