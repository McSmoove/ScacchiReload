package model;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test di tutti i metodi della classe Pedone
 * @author Viktor, Michael, Gaetano
*/
public class PedoneTest{
    
    /**
     * Test del metodo mosso della classe Pedone
    */
    @Test
    public void testMosso(){
        System.out.println("mosso");
        Pedone instance = null;
        boolean expResult = false;
        boolean result = instance.mosso();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test del metodo impostaMosso della classe Pedone
    */
    @Test
    public void testImpostaMosso(){
        System.out.println("impostaMosso");
        Pedone instance = null;
        instance.impostaMosso();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
} // Fine Classe PedoneTest