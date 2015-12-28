package model;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test di tutti i metodi della classe Re
 * @author Viktor, Michael, Gaetano
*/
public class ReTest{
    
    /**
     * Test del metodo mosso della classe Re
    */
    @Test
    public void testMosso(){
        System.out.println("mosso");
        Re instance = null;
        boolean expResult = false;
        boolean result = instance.mosso();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test del metodo impostaMosso della classe Re
    */
    @Test
    public void testImpostaMosso(){
        System.out.println("impostaMosso");
        Re instance = null;
        instance.impostaMosso();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
} // Fine Classe ReTest