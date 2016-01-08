package controller;

import java.util.LinkedList;
import org.junit.*;
import model.*;
import view.InterfacciaGrafica;
import static org.junit.Assert.*;

/**
 * Test di alcuni metodi della classe GestoreMovimenti
 * @author Viktor Teren VR379996, Michael Andronic VR370063, Gaetano Cavaler VR379845
*/
public class GestoreMovimentiTest{
    
    /**
     * Test del costruttore GestoreMovimenti senza parametri 
     * @throws Exception - Eccezione lanciata nel caso in cui non si riesce a generare il GestoreMovimenti
    */
    @Test
    public void testGestoreMovimenti() throws Exception{
        
        GestoreMovimenti risultato = new GestoreMovimenti();
        
        assertNotNull( risultato );
    
    }
    
    /**
     * Test del costruttore GestoreMovimenti con parametro la matrice di spazi
     * @throws Exception - Eccezione lanciata nel caso in cui non si riesce a generare il GestoreMovimenti con la matrice specificata
    */
    @Test
    public void testGestoreMovimentiSpazio() throws Exception{
        
        Spazio[][] matrice = new Spazio[][]{};
        GestoreMovimenti risultato = new GestoreMovimenti( matrice );
        
        assertNotNull( risultato );
    
    }
    
    /**
     * Test del metodo controlloScacco con parametro Spazio
     * @throws Exception - Eccezione lanciata nel caso in cui non si riesca a verificare se ce lo scacco in una determinata posizione
    */
    @Test
    public void testControlloScaccoSpazio() throws Exception{
        
        GestoreMovimenti gestore = new GestoreMovimenti();
        
        Spazio check = new Spazio( 1, 1, new Alfiere( new Bianco() ) );
        
        boolean risultato = gestore.controlloScacco( check );
        
        assertEquals( true, risultato );
    
    }
    
    /**
     * Test del metodo controlloScaccoReAvversario con parametro Colore
     * @throws Exception - Eccezione lanciata nel caso in cui non si riesca a verificare se il re avversario e sotto scacco
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
     * In questo test abbiamo simulato una partita e scritto le mosse per eseguire uno scacco matto il piu velocemente possibile
     * @throws Exception - Eccezione lanciata nel caso in cui non si inizializzi qualche oggetto
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
     * @throws Exception - Eccezione lanciata nel caso in cui non si riesce a reperire la matrice della scacchiera
    */
    @Test
    public void testGetMatrice() throws Exception{
        
        GestoreMovimenti gestore = new GestoreMovimenti();
        
        Spazio[][] risultato = gestore.getMatrice();
        
        assertNotNull( risultato );
    
    }
    
    /**
     * Test del metodo getPezziSpostabiliQui con parametro Spazio e Colore
     * @throws Exception - Eccezione lanciata nel caso in cui non si riesca a reperire la lista dei pezzi spostabili nella posizione specificata
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
     * Test del metodo getSpazioRe
     * @throws Exception - Eccezione lanciata nel caso in cui non si riesca a reperire la posizione del Re del turno corrente
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
     * Test del metodo getSpazioReAvversario
     * @throws Exception - Eccezione lanciata nel caso in cui non si riesca a reperire la posizione del Re Bianco avversario
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
     * Test del metodo getSpazioReBianco
     * @throws Exception - Eccezione lanciata nel caso in cui non si riesca a reperire la posizione del Re Bianco
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
     * Test del metodo getSpazioReNero
     * @throws Exception - Eccezione lanciata nel caso in cui non si riesca a reperire la posizione del Re Nero
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
     * Test del metodo scaccoMatto
     * @throws Exception - Eccezione lanciata nel caso in cui non si riesca ad eseguire uno scacco matto
    */
    @Test
    public void testScaccoMatto() throws Exception{
        
        GestoreMovimenti gestore = new GestoreMovimenti();

        Colore turnoCorrente = new Bianco();
        
        boolean risultato = gestore.scaccoMatto( turnoCorrente );
        
        assertEquals( false, risultato );
    
    }
    
    /**
     * Test del metodo setInterfacciaGrafica
     * @throws Exception - Eccezione lanciata nel caso in cui non si riesca ad impostare l'InterfacciaGrafica
    */
    @Test
    public void testSetInterfacciaGrafica() throws Exception{
        
        GestoreMovimenti gestore = new GestoreMovimenti();

        InterfacciaGrafica interfaccia = null;
        
        gestore.setInterfacciaGrafica( interfaccia );
    
    }
    
    /**
     * Test del metodo spostaPezzo con parametri Spazio, Int e Int
     * @throws Exception - Eccezione lanciata nel caso in cui non si riesca ad spostare il pezzo
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