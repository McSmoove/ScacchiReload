package controller;

import java.util.LinkedList;
import model.*;
import org.junit.Test;
import static org.junit.Assert.*;
import view.InterfacciaGrafica;

/**
 * Test di tutti i metodi della classe GestoreMovimenti
 * @author Viktor, Michael, Gaetano
*/
public class GestoreMovimentiTest{
    
    /**
     * Test del metodo getMatrice della classe GestoreMovimenti
    */
    @Test
    public void testGetMatrice(){
        System.out.println("getMatrice");
        GestoreMovimenti instance = new GestoreMovimenti();
        Spazio[][] expResult = null;
        Spazio[][] result = instance.getMatrice();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test del metodo getRe della classe GestoreMovimenti
     * @throws java.lang.Exception
    */
    @Test
    public void testGetRe() throws Exception{
        System.out.println("getRe");
        Colore c = null;
        GestoreMovimenti instance = new GestoreMovimenti();
        Re expResult = null;
        Re result = instance.getRe(c);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test del metodo getReNero della classe GestoreMovimenti
     * @throws java.lang.Exception
    */
    @Test
    public void testGetReNero() throws Exception{
        System.out.println("getReNero");
        GestoreMovimenti instance = new GestoreMovimenti();
        Re expResult = null;
        Re result = instance.getReNero();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test del metodo getSpazioRe della classe GestoreMovimenti
    */
    @Test
    public void testGetSpazioRe(){
        System.out.println("getSpazioRe");
        Colore c = null;
        GestoreMovimenti instance = new GestoreMovimenti();
        Spazio expResult = null;
        Spazio result = instance.getSpazioRe(c);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test del metodo getSpazioReAvversario della classe GestoreMovimenti
    */
    @Test
    public void testGetSpazioReAvversario(){
        System.out.println("getSpazioReAvversario");
        Colore c = null;
        GestoreMovimenti instance = new GestoreMovimenti();
        Spazio expResult = null;
        Spazio result = instance.getSpazioReAvversario(c);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test del metodo getSpazioReNero della classe GestoreMovimenti
    */
    @Test
    public void testGetSpazioReNero(){
        System.out.println("getSpazioReNero");
        GestoreMovimenti instance = new GestoreMovimenti();
        Spazio expResult = null;
        Spazio result = instance.getSpazioReNero();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test del metodo getSpazioReBianco della classe GestoreMovimenti
    */
    @Test
    public void testGetSpazioReBianco(){
        System.out.println("getSpazioReBianco");
        GestoreMovimenti instance = new GestoreMovimenti();
        Spazio expResult = null;
        Spazio result = instance.getSpazioReBianco();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test del metodo getReBianco della classe GestoreMovimenti
     * @throws java.lang.Exception
    */
    @Test
    public void testGetReBianco() throws Exception{
        System.out.println("getReBianco");
        GestoreMovimenti instance = new GestoreMovimenti();
        Re expResult = null;
        Re result = instance.getReBianco();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test del metodo movimentiPedone della classe GestoreMovimenti
    */
    @Test
    public void testMovimentiPedone(){
        System.out.println("movimentiPedone");
        Spazio s = null;
        GestoreMovimenti instance = new GestoreMovimenti();
        int[][] expResult = null;
        int[][] result = instance.movimentiPedone(s);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test del metodo controlloScacco della classe GestoreMovimenti
    */
    @Test
    public void testControlloScacco_3args(){
        System.out.println("controlloScacco");
        int x = 0;
        int y = 0;
        Spazio[][] matrix = null;
        GestoreMovimenti instance = new GestoreMovimenti();
        boolean expResult = false;
        boolean result = instance.controlloScacco(x, y, matrix);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test del metodo controlloScacco della classe GestoreMovimenti
    */
    @Test
    public void testControlloScacco_Spazio(){
        System.out.println("controlloScacco");
        Spazio s = null;
        GestoreMovimenti instance = new GestoreMovimenti();
        boolean expResult = false;
        boolean result = instance.controlloScacco(s);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test del metodo controlloScacco della classe GestoreMovimenti
     * @throws java.lang.Exception
    */
    @Test
    public void testControlloScacco_Colore_SpazioArrArr() throws Exception{
        System.out.println("controlloScacco");
        Colore colore = null;
        Spazio[][] matrice = null;
        GestoreMovimenti instance = new GestoreMovimenti();
        boolean expResult = false;
        boolean result = instance.controlloScacco(colore, matrice);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test del metodo controlloScaccoReAvversario della classe GestoreMovimenti
     * @throws java.lang.Exception
    */
    @Test
    public void testControlloScaccoReAvversario_Colore() throws Exception{
        System.out.println("controlloScaccoReAvversario");
        Colore colore = null;
        GestoreMovimenti instance = new GestoreMovimenti();
        boolean expResult = false;
        boolean result = instance.controlloScaccoReAvversario(colore);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test del metodo controlloScaccoReAvversario della classe GestoreMovimenti
     * @throws java.lang.Exception
    */
    @Test
    public void testControlloScaccoReAvversario_Colore_SpazioArrArr() throws Exception{
        System.out.println("controlloScaccoReAvversario");
        Colore colore = null;
        Spazio[][] matrice = null;
        GestoreMovimenti instance = new GestoreMovimenti();
        boolean expResult = false;
        boolean result = instance.controlloScaccoReAvversario(colore, matrice);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test del metodo getPezziAttaccantiIlRe della classe GestoreMovimenti
    */
    @Test
    public void testGetPezziAttaccantiIlRe(){
        System.out.println("getPezziAttaccantiIlRe");
        Spazio s = null;
        Spazio[][] matrix = null;
        GestoreMovimenti instance = new GestoreMovimenti();
        LinkedList<Pezzo> expResult = null;
        LinkedList<Pezzo> result = instance.getPezziAttaccantiIlRe(s, matrix);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test del metodo spostabileIn della classe GestoreMovimenti
    */
    @Test
    public void testSpostabileIn_3args(){
        System.out.println("spostabileIn");
        Spazio s = null;
        int x = 0;
        int y = 0;
        GestoreMovimenti instance = new GestoreMovimenti();
        boolean expResult = false;
        boolean result = instance.spostabileIn(s, x, y);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test del metodo spostabileIn della classe GestoreMovimenti
    */
    @Test
    public void testSpostabileIn_4args(){
        System.out.println("spostabileIn");
        Spazio s = null;
        int x = 0;
        int y = 0;
        Spazio[][] matrix = null;
        GestoreMovimenti instance = new GestoreMovimenti();
        boolean expResult = false;
        boolean result = instance.spostabileIn(s, x, y, matrix);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test del metodo getPezziSpostabiliQui della classe GestoreMovimenti
    */
    @Test
    public void testGetPezziSpostabiliQui_3args(){
        System.out.println("getPezziSpostabiliQui");
        Spazio[][] mat = null;
        Spazio s = null;
        Colore c = null;
        GestoreMovimenti instance = new GestoreMovimenti();
        LinkedList<Spazio> expResult = null;
        LinkedList<Spazio> result = instance.getPezziSpostabiliQui(mat, s, c);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test del metodo getPezziSpostabiliQui della classe GestoreMovimenti
    */
    @Test
    public void testGetPezziSpostabiliQui_Spazio_Colore(){
        System.out.println("getPezziSpostabiliQui");
        Spazio s = null;
        Colore c = null;
        GestoreMovimenti instance = new GestoreMovimenti();
        LinkedList<Spazio> expResult = null;
        LinkedList<Spazio> result = instance.getPezziSpostabiliQui(s, c);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test del metodo getListaPezziChePrevengonoScacco della classe GestoreMovimenti
    */
    @Test
    public void testGetListaPezziChePrevengonoScacco(){
        System.out.println("getListaPezziChePrevengonoScacco");
        int xRe = 0;
        int yRe = 0;
        Spazio[][] matrice = null;
        Colore turno = null;
        GestoreMovimenti instance = new GestoreMovimenti();
        LinkedList<Spazio> expResult = null;
        LinkedList<Spazio> result = instance.getListaPezziChePrevengonoScacco(xRe, yRe, matrice, turno);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test del metodo getMatricePezziChePrevengonoScacco della classe GestoreMovimenti
    */
    @Test
    public void testGetMatricePezziChePrevengonoScacco(){
        System.out.println("getMatricePezziChePrevengonoScacco");
        int xRe = 0;
        int yRe = 0;
        Spazio[][] matrice = null;
        Colore turno = null;
        GestoreMovimenti instance = new GestoreMovimenti();
        int[][] expResult = null;
        int[][] result = instance.getMatricePezziChePrevengonoScacco(xRe, yRe, matrice, turno);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test del metodo setInterfacciaGrafica della classe GestoreMovimenti
    */
    @Test
    public void testSetInterfacciaGrafica(){
        System.out.println("setInterfacciaGrafica");
        InterfacciaGrafica i = null;
        GestoreMovimenti instance = new GestoreMovimenti();
        instance.setInterfacciaGrafica(i);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test del metodo setMatrice della classe GestoreMovimenti
    */
    @Test
    public void testSetMatrice(){
        System.out.println("setMatrice");
        Spazio[][] matrice = null;
        GestoreMovimenti instance = new GestoreMovimenti();
        instance.setMatrice(matrice);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test del metodo scaccoMatto della classe GestoreMovimenti
    */
    @Test
    public void testScaccoMatto(){
        System.out.println("scaccoMatto");
        Colore turnoCorrente = null;
        GestoreMovimenti instance = new GestoreMovimenti();
        boolean expResult = false;
        boolean result = instance.scaccoMatto(turnoCorrente);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test del metodo spostaPezzo della classe GestoreMovimenti
    */
    @Test
    public void testSpostaPezzo_4args(){
        System.out.println("spostaPezzo");
        Spazio s = null;
        int x = 0;
        int y = 0;
        Spazio[][] scacchiera = null;
        GestoreMovimenti instance = new GestoreMovimenti();
        instance.spostaPezzo(s, x, y, scacchiera);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test del metodo spostaPezzo della classe GestoreMovimenti
    */
    @Test
    public void testSpostaPezzo_3args(){
        System.out.println("spostaPezzo");
        Spazio s = null;
        int x = 0;
        int y = 0;
        GestoreMovimenti instance = new GestoreMovimenti();
        instance.spostaPezzo(s, x, y);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test del metodo coppiaMatrice della classe GestoreMovimenti
    */
    @Test
    public void testCoppiaMatrice(){
        System.out.println("coppiaMatrice");
        Spazio[][] matrice = null;
        GestoreMovimenti instance = new GestoreMovimenti();
        Spazio[][] expResult = null;
        Spazio[][] result = instance.coppiaMatrice(matrice);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test del metodo disegnaMatriceSpaziOccupati della classe GestoreMovimenti
    */
    @Test
    public void testDisegnaMatriceSpaziOccupati_0args(){
        System.out.println("disegnaMatriceSpaziOccupati");
        GestoreMovimenti instance = new GestoreMovimenti();
        instance.disegnaMatriceSpaziOccupati();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test del metodo disegnaMatriceSpaziOccupati della classe GestoreMovimenti
    */
    @Test
    public void testDisegnaMatriceSpaziOccupati_SpazioArrArr(){
        System.out.println("disegnaMatriceSpaziOccupati");
        Spazio[][] matrice = null;
        GestoreMovimenti instance = new GestoreMovimenti();
        instance.disegnaMatriceSpaziOccupati(matrice);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
} // Fine Classe GestoreMovimentiTest