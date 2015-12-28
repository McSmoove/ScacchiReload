package model;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test di tutti i metodi della classe Nero
 * @author Viktor, Michael, Gaetano
*/
public class NeroTest{
    
    /**
     * Test del metodo equals della classe Nero
    */
    @Test
    public void testEquals(){
        System.out.println("equals");
        Object o = null;
        Nero instance = new Nero();
        boolean expResult = false;
        boolean result = instance.equals(o);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test del metodo hashCode della classe Nero
    */
    @Test
    public void testHashCode(){
        System.out.println("hashCode");
        Nero instance = new Nero();
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test del metodo toString della classe Nero
    */
    @Test
    public void testToString(){
        System.out.println("toString");
        Nero instance = new Nero();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
} // Fine Classe NeroTest