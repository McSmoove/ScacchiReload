package controller;

import java.util.LinkedList;
import org.junit.*;
import model.*;
import view.InterfacciaGrafica;
import static org.junit.Assert.*;

/**
 * Test di tutti / alcuni i metodi della classe GestoreMovimenti
 * @author Viktor, Michael, Gaetano
*/
public class GestoreMovimentiTest{
    
    /**
     * Test del costruttore GestoreMovimenti 
     * @throws Exception - Eccezzione in caso di fallita creazione dell'oggetto
    */
    @Test
    public void testGestoreMovimenti() throws Exception{
        
        GestoreMovimenti risultato = new GestoreMovimenti();
        
        assertNotNull( risultato );
    
    }
    
    /**
     * Test del costruttore GestoreMovimenti con Parametro Spazio[][]
     * @throws Exception - Eccezzione in caso di fallita creazione dell'oggetto
    */
    @Test
    public void testGestoreMovimentiSpazio() throws Exception{
        
        Spazio[][] matrice = new Spazio[][]{};
        GestoreMovimenti risultato = new GestoreMovimenti( matrice );
        
        assertNotNull( risultato );
    
    }
    
    /**
     * Test del metodo controlloScacco con parametro Spazio della classe GestoreMovimenti
     * @throws Exception - Eccezzione in caso di fallita creazione dell'oggetto
    */
    @Test
    public void testControlloScaccoSpazio() throws Exception{
        
        GestoreMovimenti gestore = new GestoreMovimenti();
        
        Spazio check = new Spazio( 1, 1, new Alfiere( new Bianco() ) );
        
        boolean risultato = gestore.controlloScacco( check );
        
        assertEquals( true, risultato );
    
    }
    
    /**
     * Test del metodo controlloScaccoReAvversario con parametro Colore della classe GestoreMovimenti
     * @throws Exception - Eccezzione in caso di fallita creazione dell'oggetto
    */
    @Test
    public void testControlloScaccoReAvversarioColore() throws Exception{
        
        GestoreMovimenti gestore = new GestoreMovimenti();
        
        Colore colore = new Bianco();
        
        boolean risultato = gestore.controlloScaccoReAvversario( colore );
        
        assertEquals( true, risultato );
    
    }
    
    /**
     * Test del metodo controlloScacco della classe GestoreMovimenti
     * @throws Exception - Eccezzione in caso di fallita creazione dell'oggetto
    */
    @Test
    public void testControlloScaccoImplementato() throws Exception{
        
        int x;
        int y;
        Spazio[][] m = new Spazio[8][8];
        
        for( int colonne = 0; colonne < 8; colonne++ ){
            
            for( int righe = 0; righe < 8; righe++ ){
                
                m[ righe ][ colonne ] = new Spazio( righe, colonne );
            
            }
        
        }
        
        GestoreMovimenti gestore = new GestoreMovimenti();
            
        gestore.spostaPezzo( gestore.getMatrice()[ 5 ][ 6 ], 5, 4, gestore.getMatrice() );
        gestore.spostaPezzo( gestore.getMatrice()[ 4 ][ 1 ], 4, 3, gestore.getMatrice() );
        gestore.spostaPezzo( gestore.getMatrice()[ 6 ][ 6 ], 6, 4, gestore.getMatrice() );
        gestore.spostaPezzo( gestore.getMatrice()[ 3 ][ 0 ], 7, 4, gestore.getMatrice() );
        
        boolean risultato = gestore.controlloScacco( 4, 7, gestore.getMatrice() );
        
        assertEquals( false, risultato );
    
    }
    
    
    
    /**
     * Test del metodo getMatrice della classe GestoreMovimenti
     * @throws Exception - Eccezzione in caso di fallita creazione dell'oggetto
    */
    @Test
    public void testGetMatrice() throws Exception{
        
        GestoreMovimenti gestore = new GestoreMovimenti();
        
        Spazio[][] risultato = gestore.getMatrice();
        
        assertNotNull( risultato );
    
    }
    
    /**
     * Test del metodo getPezziSpostabiliQui con parametro Spazio e Colore della classe GestoreMovimenti
     * @throws Exception - Eccezzione in caso di fallita creazione dell'oggetto
    */
    @Test
    public void testGetPezziSpostabiliQuiSpazioColore() throws Exception{
        
        GestoreMovimenti gestore = new GestoreMovimenti();
        
        Spazio spazio = new Spazio( 1, 1, new Alfiere( new Bianco() ) );
        Colore colore = new Bianco();
        
        LinkedList< Spazio > risultato = gestore.getPezziSpostabiliQui( spazio, colore );
        
        assertNotNull( risultato );
        assertEquals( 64, risultato.size() );
    
    }
    
    /**
     * Test del metodo getSpazioRe della classe GestoreMovimenti
     * @throws Exception - Eccezzione in caso di fallita creazione dell'oggetto
    */
    @Test
    public void testGetSpazioRe() throws Exception{
        
        GestoreMovimenti gestore = new GestoreMovimenti();
        
        Colore colore = new Bianco();
        
        Spazio risultato = gestore.getSpazioRe( colore );
        
        assertNotNull( risultato );
        assertEquals( 7, risultato.getY() );
        assertEquals( 4, risultato.getX() );
        assertEquals( true, risultato.eOccupato() );
    
    }
    
    /**
     * Test del metodo getSpazioReAvversario della classe GestoreMovimenti
     * @throws Exception - Eccezzione in caso di fallita creazione dell'oggetto
    */
    @Test
    public void testGetSpazioReAvversario() throws Exception{
        
        GestoreMovimenti gestore = new GestoreMovimenti();

        Colore colore = new Bianco();
        
        Spazio risultato = gestore.getSpazioReAvversario( colore );
        
        assertNotNull( risultato );
        assertEquals( 0, risultato.getY() );
        assertEquals( 4, risultato.getX() );
        assertEquals( true, risultato.eOccupato() );
    
    }
    
    /**
     * Test del metodo getSpazioReBianco della classe GestoreMovimenti
     * @throws Exception - Eccezzione in caso di fallita creazione dell'oggetto
    */
    @Test
    public void testGetSpazioReBianco() throws Exception{
        
        GestoreMovimenti gestore = new GestoreMovimenti();
        
        Spazio risultato = gestore.getSpazioReBianco();
        
        assertNotNull( risultato );
        assertEquals( 7, risultato.getY() );
        assertEquals( 4, risultato.getX() );
        assertEquals( true, risultato.eOccupato() );
    
    }
    
    /**
     * Test del metodo getSpazioReNero della classe GestoreMovimenti
     * @throws Exception - Eccezzione in caso di fallita creazione dell'oggetto
    */
    @Test
    public void testGetSpazioReNero() throws Exception {
        
        GestoreMovimenti gestore = new GestoreMovimenti();
        
        Spazio risultato = gestore.getSpazioReNero();
        
        assertNotNull( risultato );
        assertEquals( 0, risultato.getY() );
        assertEquals( 4, risultato.getX() );
        assertEquals( true, risultato.eOccupato() );
    
    }
    
    /**
     * Test del metodo scaccoMatto della classe GestoreMovimenti
     * @throws Exception - Eccezzione in caso di fallita creazione dell'oggetto
    */
    @Test
    public void testScaccoMatto() throws Exception{
        
        GestoreMovimenti gestore = new GestoreMovimenti();

        Colore turnoCorrente = new Bianco();
        
        boolean risultato = gestore.scaccoMatto( turnoCorrente );
        
        assertEquals( false, risultato );
    
    }
    
    /**
     * Test del metodo setInterfacciaGrafica della classe GestoreMovimenti
     * @throws Exception - Eccezzione in caso di fallita creazione dell'oggetto
    */
    @Test
    public void testSetInterfacciaGrafica() throws Exception{
        
        GestoreMovimenti gestore = new GestoreMovimenti();

        InterfacciaGrafica interfaccia = null;
        
        gestore.setInterfacciaGrafica( interfaccia );
    
    }
    
    /**
     * Test del metodo spostaPezzo con parametri Spazio, Int e Int della classe GestoreMovimenti
     * @throws Exception - Eccezzione in caso di fallita creazione dell'oggetto
    */
    @Test
    public void testSpostaPezzoSpazioIntInt() throws Exception{
        
        GestoreMovimenti gestore = new GestoreMovimenti();

        Spazio spazio = new Spazio( 1, 1, new Pedone( new Bianco() ) );
        
        int x = 1;
        int y = 7;
        
        gestore.spostaPezzo( spazio, x, y );
    
    }
    
} // Fine Classe GestoreMovimentiTest