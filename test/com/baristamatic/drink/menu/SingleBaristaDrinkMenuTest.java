/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.baristamatic.drink.menu;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author  Tom Stewart (seasonedgeek@mac.com)
 */
public class SingleBaristaDrinkMenuTest {
    
    public SingleBaristaDrinkMenuTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getInstance method, of class SingleBaristaDrinkMenu.
     */
    @Test
    public void testGetInstance() {
        System.out.println("getInstance");
        SingleBaristaDrinkMenu expResult = null;
        SingleBaristaDrinkMenu result = SingleBaristaDrinkMenu.getInstance();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFeaturedDrinksMenu method, of class SingleBaristaDrinkMenu.
     */
    @Test
    public void testGetFeaturedDrinksMenu() {
        System.out.println("getFeaturedDrinksMenu");
        boolean noPrompt = false;
        SingleBaristaDrinkMenu instance = null;
        String expResult = "";
        String result = instance.getFeaturedDrinksMenu(noPrompt);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDrinkAttributes method, of class SingleBaristaDrinkMenu.
     */
    @Test
    public void testSetDrinkAttributes() {
        System.out.println("setDrinkAttributes");
        String drinkName = "";
        String drinkCost = "";
        SingleBaristaDrinkMenu instance = null;
        instance.setDrinkAttributes(drinkName, drinkCost);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateMenuDrinkAvailability method, of class SingleBaristaDrinkMenu.
     */
    @Test
    public void testUpdateMenuDrinkAvailability() {
        System.out.println("updateMenuDrinkAvailability");
        String drinkName = "";
        boolean inStock = false;
        SingleBaristaDrinkMenu instance = null;
        instance.updateMenuDrinkAvailability(drinkName, inStock);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of noSpaceComma method, of class SingleBaristaDrinkMenu.
     */
    @Test
    public void testNoSpaceComma() {
        System.out.println("noSpaceComma");
        SingleBaristaDrinkMenu instance = null;
        String expResult = "";
        String result = ",";
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of skipTwoLines method, of class SingleBaristaDrinkMenu.
     */
    @Test
    public void testSkipTwoLines() {
        System.out.println("skipTwoLines");
        SingleBaristaDrinkMenu instance = null;
        String expResult = "";
        String result = "\n\n";
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
