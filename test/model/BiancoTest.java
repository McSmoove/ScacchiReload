package model;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test di tutti i metodi della classe Bianco
 * @author Viktor, Michael, Gaetano
*/
public class BiancoTest{
    
    /**
     * Test del metodo equals della classe Bianco
    */
    @Test
    public void testEquals(){
        System.out.println("equals");
        Object o = null;
        Bianco instance = new Bianco();
        boolean expResult = false;
        boolean result = instance.equals(o);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test del metodo hashCode della classe Bianco
     */
    @Test
    public void testHashCode(){
        System.out.println("hashCode");
        Bianco instance = new Bianco();
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test del metodo toString della classe Bianco
    */
    @Test
    public void testToString(){
        System.out.println("toString");
        Bianco instance = new Bianco();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
} // Fine Classe BiancoTest