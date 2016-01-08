package controller;

import java.util.LinkedList;
import org.junit.*;
import model.*;
import view.InterfacciaGrafica;
import static org.junit.Assert.*;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

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
        
        gestore.disegnaMatriceSpaziOccupati( gestore.getMatrice() );
        
        gestore.spostaPezzo( gestore.getMatrice()[ 5 ][ 6 ], 5, 4, gestore.getMatrice() );
        gestore.spostaPezzo( gestore.getMatrice()[ 4 ][ 1 ], 4, 3, gestore.getMatrice() );
        gestore.spostaPezzo( gestore.getMatrice()[ 6 ][ 6 ], 6, 4, gestore.getMatrice() );
        gestore.spostaPezzo( gestore.getMatrice()[ 3 ][ 0 ], 7, 4, gestore.getMatrice() );
        
        gestore.disegnaMatriceSpaziOccupati( gestore.getMatrice() );
        
        boolean risultato = gestore.controlloScacco( 4, 7, gestore.getMatrice() );
        
        assertEquals( false, risultato );
    
    }
    
    /**
     * Test del metodo disegnaMatriceSpaziOccupati senza parametri della classe GestoreMovimenti
     * @throws Exception - Eccezzione in caso di fallita creazione dell'oggetto
    */
    @Test
    public void testDisegnaMatriceSpaziOccupatiSenzaParametri() throws Exception{
        
        GestoreMovimenti gestore = new GestoreMovimenti();
        
        gestore.disegnaMatriceSpaziOccupati();
    
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
    
    /**
     * Test del metodo spostaPezzo con parametri Spazio, Int, Int e Spazio della classe GestoreMovimenti
     * @throws Exception - Eccezzione in caso di fallita creazione dell'oggetto
    */
    /*
    @Test
    public void testSpostaPezzoSpazioIntIntSpazio() throws Exception{
        
        GestoreMovimenti gestore = new GestoreMovimenti();

        Spazio spazio = new Spazio( 1, 1, new Alfiere( new Bianco() ) );
        
        int x = 1;
        int y = 1;
        Spazio[][] scacchiera = new Spazio[][]{ null };
        
        gestore.spostaPezzo( spazio, x, y, scacchiera );
    
    }
    */
    
    /**
     * Test del metodo controlloScacco con parametri Colore e Spazio della classe GestoreMovimenti
     * @throws Exception - Eccezzione in caso di fallita creazione dell'oggetto
    */
    /*
    @Test
    public void testControlloScaccoColoreSpazio() throws Exception{
        
        GestoreMovimenti gestore = new GestoreMovimenti();
        
        Colore colore = new Bianco();
        Spazio[][] matrice = new Spazio[][]{};
        
        boolean risultato = gestore.controlloScacco( colore, matrice );
        
        assertTrue( risultato );
    
    }
    */
    
    /**
     * Test del metodo controlloScaccoReAvversario con parametro Colore e Spazio della classe GestoreMovimenti
     * @throws Exception - Eccezzione in caso di fallita creazione dell'oggetto
    */
    /*
    @Test
    public void testControlloScaccoReAvversarioColoreSpazio() throws Exception{
        
        GestoreMovimenti gestore = new GestoreMovimenti();
        
        Colore colore = new Bianco();
        Spazio[][] matrice = new Spazio[][]{};
        
        boolean risultato = gestore.controlloScaccoReAvversario( colore, matrice );
        
        assertTrue( risultato );
    
    }
    */
    
    /**
     * Test del metodo coppiaMatrice della classe GestoreMovimenti
     * @throws Exception - Eccezzione in caso di fallita creazione dell'oggetto
    */
    /*
    @Test
    public void testCoppiaMatrice() throws Exception{
        
        GestoreMovimenti gestore = new GestoreMovimenti();

        Spazio[][] matrice = new Spazio[][]{};
        
        Spazio[][] risultato = gestore.coppiaMatrice(matrice);
        
        assertNotNull( risultato );
    
    }
    */
    
    /**
     * Test del metodo disegnaMatriceSpaziOccupati con parametro Spazio della classe GestoreMovimenti
     * @throws Exception - Eccezzione in caso di fallita creazione dell'oggetto
     */
    /*
    @Test
    public void testDisegnaMatriceSpaziOccupatiSpazio() throws Exception{
        
        GestoreMovimenti gestore = new GestoreMovimenti();

        Spazio[][] matrice = new Spazio[][]{};
        
        gestore.disegnaMatriceSpaziOccupati( matrice );
    
    }
    */
    
    /**
     * Test del metodo getMatricePezziChePrevengonoScacco della classe GestoreMovimenti
     * @throws Exception - Eccezzione in caso di fallita creazione dell'oggetto
    */
    /*
    @Test
    public void testGetMatricePezziChePrevengonoScacco() throws Exception{
        
        GestoreMovimenti gestore = new GestoreMovimenti();
        
        int xRe = 1;
        int yRe = 1;
        
        Spazio[][] matrice = new Spazio[][]{};
        Colore turno = new Bianco();
        
        int[][] risultato = gestore.getMatricePezziChePrevengonoScacco( xRe, yRe, matrice, turno );
        
        assertNotNull( risultato );
    
    }
    */
    
    /**
     * Test del metodo getPezziSpostabiliQui con parametro Spazio, Spazio e Colore della classe GestoreMovimenti
     * @throws Exception - Eccezzione in caso di fallita creazione dell'oggetto
    */
    /*
    @Test
    public void testGetPezziSpostabiliQuiSpazioSpazioColore() throws Exception{
        
        GestoreMovimenti gestore = new GestoreMovimenti();

        Spazio[][] matrice = new Spazio[][]{};
        
        Spazio spazio = new Spazio( 1, 1, new Alfiere( new Bianco() ) );
        Colore colore = new Bianco();
        
        LinkedList< Spazio > risultato = gestore.getPezziSpostabiliQui( matrice, spazio, colore );
        
        assertNotNull( risultato );
    
    }
    */
    
    /**
     * Test del metodo movimentiPedone della classe GestoreMovimenti
     * @throws Exception - Eccezzione in caso di fallita creazione dell'oggetto
    */
    /*
    @Test
    public void testMovimentiPedone() throws Exception{
        
        GestoreMovimenti gestore = new GestoreMovimenti();

        Spazio spazio = new Spazio( 1, 1, new Pedone( new Bianco() ) );
        
        int[][] risultato = gestore.movimentiPedone( spazio );
        
        assertNotNull( risultato );
    
    }
    */
    
    /**
     * Test del metodo setMatrice della classe GestoreMovimenti
     * @throws Exception - Eccezzione in caso di fallita creazione dell'oggetto
    */
    /*
    @Test
    public void testSetMatrice() throws Exception{
        
        GestoreMovimenti gestore = new GestoreMovimenti();
        
        Spazio[][] matrice = new Spazio[][]{};
        
        gestore.setMatrice( matrice );
    
    }
    */
    
    /**
     * Langio tutti i test della classe GestoreMovimentiTest
     * @param args - Argomenti da linea di commando
    */
    public static void main( String[] args ){
        
        Result risultato = JUnitCore.runClasses( GestoreMovimentiTest.class );
        
        if( risultato.getFailureCount() != 0 ){
            
            System.out.println( "Numero Di Test Della Classe GestoreMovimentiTest Che Non Sono Passati: " + risultato.getFailureCount() );
        
        } else {
            
            System.out.println( "Tutti I Test Della Classe GestoreMovimentiTest Sono Passati !!!" );
        
        }
    
    }

} // Fine Classe GestoreMovimentiTest