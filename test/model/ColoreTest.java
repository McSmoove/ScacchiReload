package model;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test di tutti i metodi della classe Colore
 * @author Viktor, Michael, Gaetano
*/
public class ColoreTest{

    /**
     * Test del metodo equals della classe Colore
    */
    @Test
    public void testEquals(){
        System.out.println("equals");
        Object o = null;
        Colore instance = new ColoreImpl();
        boolean expResult = false;
        boolean result = instance.equals(o);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test del metodo hashCode della classe Colore
    */
    @Test
    public void testHashCode(){
        System.out.println("hashCode");
        Colore instance = new ColoreImpl();
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test del metodo getColore della classe Colore
    */
    @Test
    public void testGetColore(){
        System.out.println("getColore");
        Colore instance = new ColoreImpl();
        int expResult = 0;
        int result = instance.getColore();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class ColoreImpl extends Colore{}
    
} // Fine Classe ColoreTest