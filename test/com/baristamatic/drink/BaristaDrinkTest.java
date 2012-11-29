package com.baristamatic.drink;

import com.baristamatic.inventory.SingleBaristaInventory;
import java.util.HashMap;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author  Tom Stewart (seasonedgeek@mac.com)
 */
public class BaristaDrinkTest {
    
    BaristaDrink instance = null;
    HashMap<String, Integer> recipe1 = null;
    SingleBaristaInventory inventory = null;
    
    public BaristaDrinkTest() {
        // because BaristaDrink is abstract ... 
        instance = new BaristaDrinkImpl();
        
        inventory = instance.getSingleBaristaInventory();
        inventory.stockInventory();
        inventory.priceInventory();
        
        recipe1 = new HashMap<String, Integer>();
        recipe1.put("Coffee", 3);
        recipe1.put("Sugar", 1);
        recipe1.put("Cream", 1);
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
        
        // clean up ...
        recipe1.clear();
        if (recipe1.isEmpty())
            recipe1 = null;
        
        instance = null;
        inventory = null;
    }

    /**
     * Test of getSingleBaristaInventory method, of class BaristaDrink.
     */
    @Test
    public void testGetSingleBaristaInventory() {
        System.out.println("getSingleBaristaInventory");
        assertNotNull("Successful Instantiation", inventory);
    }

    /**
     * Test of getCost method, of class BaristaDrink.
     */
    @Test
    public void testGetCost() {
        System.out.println("getCost");
        
        try {
            inventory.getBaristaDrinkCost(recipe1);

            String expResult = "$0.00";
            String result = instance.getCost();
            assertTrue(result.compareToIgnoreCase(expResult) >= 0);
        }
        catch(NullPointerException npe) {
            System.err.println(npe.getMessage());
        }
    }

    /**
     * Test of setCost method, of class BaristaDrink.
     */
    @Test
    public void testSetCost() {
        System.out.println("setCost");
        String cost = "";
        BaristaDrink instance = new BaristaDrinkImpl();
        instance.setCost(cost);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isInStock method, of class BaristaDrink.
     */
    @Test
    public void testIsInStock() {
        System.out.println("isInStock");
        BaristaDrink instance = new BaristaDrinkImpl();
        boolean expResult = false;
        boolean result = instance.isInStock();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setInStock method, of class BaristaDrink.
     */
    @Test
    public void testSetInStock() {
        System.out.println("setInStock");
        boolean inStock = false;
        BaristaDrink instance = new BaristaDrinkImpl();
        instance.setInStock(inStock);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getName method, of class BaristaDrink.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        BaristaDrink instance = new BaristaDrinkImpl();
        String expResult = "";
        String result = instance.getName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setName method, of class BaristaDrink.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String name = "";
        BaristaDrink instance = new BaristaDrinkImpl();
        instance.setName(name);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRecipe method, of class BaristaDrink.
     */
    @Test
    public void testGetRecipe() {
        System.out.println("getRecipe");
        BaristaDrink instance = new BaristaDrinkImpl();
        HashMap expResult = null;
        HashMap result = instance.getRecipe();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setRecipe method, of class BaristaDrink.
     */
    @Test
    public void testSetRecipe() {
        System.out.println("setRecipe");
        HashMap<String, Integer> recipe = null;
        BaristaDrink instance = new BaristaDrinkImpl();
        instance.setRecipe(recipe);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of drinkAttributes method, of class BaristaDrink.
     */
    @Test
    public void testDrinkAttributes() {
        System.out.println("drinkAttributes");
        //BaristaDrink instance = new BaristaDrinkImpl();
        //String[] expResult = null;
        //String[] result = instance.
        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of dispenseDrink method, of class BaristaDrink.
     */
    @Test
    public void testDispenseDrink() {
        System.out.println("dispenseDrink");
        BaristaDrink instance = new BaristaDrinkImpl();
        String expResult = "";
        String result = instance.dispenseDrink();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class BaristaDrinkImpl extends BaristaDrink {
    }
}
