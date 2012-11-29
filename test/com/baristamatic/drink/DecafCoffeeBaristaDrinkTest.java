package com.baristamatic.drink;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author  Tom Stewart (seasonedgeek@mac.com)
 */
public class DecafCoffeeBaristaDrinkTest {
    
    public DecafCoffeeBaristaDrinkTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of dispenseDrink method, of class DecafCoffeeBaristaDrink.
     */
    @Test
    public void testDispenseDrink() {
        System.out.println("dispenseDrink");
        
	DecafCoffeeBaristaDrink instance = new DecafCoffeeBaristaDrink();
	instance.getSingleBaristaInventory().stockInventory();   
        instance.initBaristaDrink();
                        
        String[] expResult = new String[] { 
            "Dispensing: " + instance.getName(), 
            "Out of stock: " + instance.getName() 
        };
        
        String result = instance.dispenseDrink();
        
        for (int i=0; i < expResult.length; i++) {
            if (result.length() == expResult[i].length() ) {
                assertEquals(expResult[i], result);
                System.out.println(expResult[i] + "/" + result);
            }
        }
    }
}
