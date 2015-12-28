package model;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test di tutti i metodi della classe Pezzo
 * @author Viktor, Michael, Gaetano
*/
public class PezzoTest{
    
    /**
     * Test del metodo getColore della classe Pezzo
    */
    @Test
    public void testGetColore(){
        System.out.println("getColore");
        Pezzo instance = null;
        Colore expResult = null;
        Colore result = instance.getColore();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test del metodo setLock della classe Pezzo
    */
    @Test
    public void testSetLock(){
        System.out.println("setLock");
        boolean lock = false;
        Pezzo instance = null;
        instance.setLock(lock);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test del metodo getLock della classe Pezzo
    */
    @Test
    public void testGetLock(){
        System.out.println("getLock");
        Pezzo instance = null;
        boolean expResult = false;
        boolean result = instance.getLock();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class PezzoImpl extends Pezzo{
        
        public PezzoImpl(){
            
            super( null );
        
        }
    
    }

} // Fine Classe PezzoTest