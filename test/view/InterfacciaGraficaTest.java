package view;

import controller.GestoreTB;
import javax.swing.JButton;
import model.*;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test di tutti i metodi della classe InterfacciaGrafica
 * @author Viktor, Michael, Gaetano
*/
public class InterfacciaGraficaTest{

    /**
     * Test del metodo getGestoreTB della classe InterfacciaGrafica
    */
    @Test
    public void testGetGestoreTB(){
        System.out.println("getGestoreTB");
        InterfacciaGrafica instance = new InterfacciaGrafica();
        GestoreTB expResult = null;
        GestoreTB result = instance.getGestoreTB();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test del metodo start della classe InterfacciaGrafica
    */
    @Test
    public void testStart(){
        System.out.println("start");
        InterfacciaGrafica instance = new InterfacciaGrafica();
        instance.start();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test del metodo getMatriceBottoni della classe InterfacciaGrafica
    */
    @Test
    public void testGetMatriceBottoni(){
        System.out.println("getMatriceBottoni");
        InterfacciaGrafica instance = new InterfacciaGrafica();
        JButton[][] expResult = null;
        JButton[][] result = instance.getMatriceBottoni();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test del metodo finePartita della classe InterfacciaGrafica
    */
    @Test
    public void testFinePartita(){
        System.out.println("finePartita");
        InterfacciaGrafica instance = new InterfacciaGrafica();
        instance.finePartita();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test del metodo setMessaggio della classe InterfacciaGrafica
    */
    @Test
    public void testSetMessaggio(){
        System.out.println("setMessaggio");
        String s = "";
        InterfacciaGrafica instance = new InterfacciaGrafica();
        instance.setMessaggio(s);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test del metodo aggiungiPezzoMorto della classe InterfacciaGrafica
    */
    @Test
    public void testAggiungiPezzoMorto(){
        System.out.println("aggiungiPezzoMorto");
        Pezzo p = null;
        InterfacciaGrafica instance = new InterfacciaGrafica();
        instance.aggiungiPezzoMorto(p);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test del metodo promozionePedone della classe InterfacciaGrafica
    */
    @Test
    public void testPromozionePedone(){
        System.out.println("promozionePedone");
        Spazio s = null;
        Colore colore = null;
        Spazio[][] matrice = null;
        InterfacciaGrafica instance = new InterfacciaGrafica();
        instance.promozionePedone(s, colore, matrice);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test del metodo aggiornaBottoni della classe InterfacciaGrafica
    */
    @Test
    public void testAggiornaBottoni(){
        System.out.println("aggiornaBottoni");
        Spazio[][] matrice = null;
        InterfacciaGrafica instance = new InterfacciaGrafica();
        instance.aggiornaBottoni(matrice);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
} // Fine Classe InterfacciaGraficaTest