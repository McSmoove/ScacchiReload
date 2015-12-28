package model;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test di tutti i metodi della classe Torre
 * @author Viktor, Michael, Gaetano
*/
public class TorreTest{
    
    /**
     * Test del metodo mosso della classe Torre
    */
    @Test
    public void testMosso(){
        System.out.println("mosso");
        Torre instance = null;
        boolean expResult = false;
        boolean result = instance.mosso();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test del metodo impostaMosso della classe Torre
    */
    @Test
    public void testImpostaMosso(){
        System.out.println("impostaMosso");
        Torre instance = null;
        instance.impostaMosso();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
} // Fine Classe TorreTest