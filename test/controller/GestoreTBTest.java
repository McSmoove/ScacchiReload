package controller;

import java.awt.event.ActionEvent;
import model.*;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test di tutti i metodi della classe GestoreTB
 * @author Viktor, Michael, Gaetano
*/
public class GestoreTBTest{

    /**
     * Test del metodo getTurno della classe GestoreTB
    */
    @Test
    public void testGetTurno(){
        System.out.println("getTurno");
        GestoreTB instance = null;
        Colore expResult = null;
        Colore result = instance.getTurno();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test del metodo setTurno della classe GestoreTB
    */
    @Test
    public void testSetTurno(){
        System.out.println("setTurno");
        Colore t = null;
        GestoreTB instance = null;
        instance.setTurno(t);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test del metodo passaTurno della classe GestoreTB
    */
    @Test
    public void testPassaTurno(){
        System.out.println("passaTurno");
        GestoreTB instance = null;
        instance.passaTurno();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test del metodo attiva della classe GestoreTB
    */
    @Test
    public void testAttiva(){
        System.out.println("attiva");
        GestoreTB instance = null;
        instance.attiva();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test del metodo disattiva della classe GestoreTB
    */
    @Test
    public void testDisattiva(){
        System.out.println("disattiva");
        GestoreTB instance = null;
        instance.disattiva();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test del metodo isAttivato della classe GestoreTB
    */
    @Test
    public void testIsAttivato(){
        System.out.println("isAttivato");
        GestoreTB instance = null;
        boolean expResult = false;
        boolean result = instance.isAttivato();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test del metodo bloccoPezziIniziale della classe GestoreTB
    */
    @Test
    public void testBloccoPezziIniziale(){
        System.out.println("bloccoPezziIniziale");
        GestoreTB instance = null;
        instance.bloccoPezziIniziale();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test del metodo setXPedoneTrasformato della classe GestoreTB
    */
    @Test
    public void testSetXPedoneTrasformato(){
        System.out.println("setXPedoneTrasformato");
        int x = 0;
        GestoreTB instance = null;
        instance.setXPedoneTrasformato(x);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test del metodo setYPedoneTrasformato della classe GestoreTB
    */
    @Test
    public void testSetYPedoneTrasformato(){
        System.out.println("setYPedoneTrasformato");
        int y = 0;
        GestoreTB instance = null;
        instance.setYPedoneTrasformato(y);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test del metodo getXPedoneTrasformato della classe GestoreTB
    */
    @Test
    public void testGetXPedoneTrasformato(){
        System.out.println("getXPedoneTrasformato");
        GestoreTB instance = null;
        int expResult = 0;
        int result = instance.getXPedoneTrasformato();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test del metodo getYPedoneTrasformato della classe GestoreTB
    */
    @Test
    public void testGetYPedoneTrasformato(){
        System.out.println("getYPedoneTrasformato");
        GestoreTB instance = null;
        int expResult = 0;
        int result = instance.getYPedoneTrasformato();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test del metodo setPedoneTrasformato della classe GestoreTB
    */
    @Test
    public void testSetPedoneTrasformato(){
        System.out.println("setPedoneTrasformato");
        Spazio s = null;
        GestoreTB instance = null;
        instance.setPedoneTrasformato(s);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test del metodo pressionePulsanteScacchiera della classe GestoreTB
     * @throws java.lang.Exception
    */
    @Test
    public void testPressionePulsanteScacchiera() throws Exception{
        System.out.println("pressionePulsanteScacchiera");
        ActionEvent e = null;
        GestoreTB instance = null;
        instance.pressionePulsanteScacchiera(e);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test del metodo disegnaMatriceSpaziOccupati della classe GestoreTB
    */
    @Test
    public void testDisegnaMatriceSpaziOccupati(){
        System.out.println("disegnaMatriceSpaziOccupati");
        GestoreTB instance = null;
        instance.disegnaMatriceSpaziOccupati();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
} // Fine Classe GestoreTBTest