package model;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test di tutti i metodi della classe Spazio
 * @author Viktor, Michael, Gaetano
*/
public class SpazioTest{
    
    /**
     * Test del metodo cambiaPezzo della classe Spazio
    */
    @Test
    public void testCambiaPezzo(){
        System.out.println("cambiaPezzo");
        Pezzo p = null;
        Spazio instance = new Spazio();
        instance.cambiaPezzo(p);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test del metodo eOccupato della classe Spazio
    */
    @Test
    public void testEOccupato(){
        System.out.println("eOccupato");
        Spazio instance = new Spazio();
        boolean expResult = false;
        boolean result = instance.eOccupato();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test del metodo getOccupante della classe Spazio
    */
    @Test
    public void testGetOccupante(){
        System.out.println("getOccupante");
        Spazio instance = new Spazio();
        Pezzo expResult = null;
        Pezzo result = instance.getOccupante();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test del metodo setOccupato della classe Spazio
    */
    @Test
    public void testSetOccupato(){
        System.out.println("setOccupato");
        boolean b = false;
        Spazio instance = new Spazio();
        instance.setOccupato(b);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test del metodo getX della classe Spazio
    */
    @Test
    public void testGetX(){
        System.out.println("getX");
        Spazio instance = new Spazio();
        int expResult = 0;
        int result = instance.getX();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test del metodo getY della classe Spazio
    */
    @Test
    public void testGetY(){
        System.out.println("getY");
        Spazio instance = new Spazio();
        int expResult = 0;
        int result = instance.getY();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test del metodo setX della classe Spazio
    */
    @Test
    public void testSetX(){
        System.out.println("setX");
        int x = 0;
        Spazio instance = new Spazio();
        instance.setX(x);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test del metodo setY della classe Spazio
    */
    @Test
    public void testSetY(){
        System.out.println("setY");
        int y = 0;
        Spazio instance = new Spazio();
        instance.setY(y);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test del metodo inizializzaSpazio della classe Spazio
    */
    @Test
    public void testInizializzaSpazio_3args(){
        System.out.println("inizializzaSpazio");
        Pezzo p = null;
        int x = 0;
        int y = 0;
        Spazio instance = new Spazio();
        instance.inizializzaSpazio(p, x, y);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test del metodo inizializzaSpazio della classe Spazio
    */
    @Test
    public void testInizializzaSpazio_int_int(){
        System.out.println("inizializzaSpazio");
        int x = 0;
        int y = 0;
        Spazio instance = new Spazio();
        instance.inizializzaSpazio(x, y);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
} // Fine Classe SpazioTest