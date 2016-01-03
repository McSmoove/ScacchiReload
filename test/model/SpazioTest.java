package model;

import org.junit.*;
import static org.junit.Assert.*;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

/**
 * Test di tutti / alcuni i metodi della classe Spazio
 * @author Viktor, Michael, Gaetano
*/
public class SpazioTest{
    
    /**
     * Test del costruttore Spazio senza parametri
     * Passa perche -1, -1 e la posizione fuori scacchiera di default
     * @throws Exception - Eccezzione in caso di fallita creazione dell'oggetto
    */
    @Test
    public void testSpazioSenzaParametri() throws Exception{
        
        Spazio risultato = new Spazio();
        
        assertNotNull( risultato );
        assertEquals( -1, risultato.getX() );
        assertEquals( -1, risultato.getY() );
        assertEquals( false, risultato.eOccupato() );
        assertEquals( null, risultato.getOccupante() );
    
    }
    
    /**
     * Test del costruttore Spazio con parametro Spazio
     * Passa perche -1, -1 e la posizione fuori scacchiera di default
     * @throws Exception - Eccezzione in caso di fallita creazione dell'oggetto
    */
    @Test
    public void testSpazioParametroSpazio() throws Exception{
        
        Spazio spazioSenzaParametri = new Spazio();
        
        Spazio risultato = new Spazio( spazioSenzaParametri );
        
        assertNotNull( risultato );
        assertEquals( -1, risultato.getX() );
        assertEquals( -1, risultato.getY() );
        assertEquals( false, risultato.eOccupato() );
        assertEquals( null, risultato.getOccupante() );
    
    }
    
    /**
     * Test del costruttore Spazio con due parametri Int, Int
     * @throws Exception - Eccezzione in caso di fallita creazione dell'oggetto
    */
    @Test
    public void testSpazioParametroIntInt() throws Exception{
        
        int x = 0;
        int y = 0;
        
        Spazio risultato = new Spazio( x, y );
        
        assertNotNull( risultato );
        assertEquals( 0, risultato.getX() );
        assertEquals( 0, risultato.getY() );
        assertEquals( false, risultato.eOccupato() );
        assertEquals( null, risultato.getOccupante() );
    
    }
    
    /**
     * Test del costruttore Spazio con tre parametri Int, Int, Pezzo
     * @throws Exception - Eccezzione in caso di fallita creazione dell'oggetto
    */
    @Test
    public void testSpazioParametriIntIntPezzo() throws Exception{
        
        int x = 0;
        int y = 0;
        Pezzo alfiereBianco = new Alfiere( new Bianco() );
        
        Spazio risultato = new Spazio( x, y, alfiereBianco );
        
        assertNotNull( risultato );
        assertEquals( 0, risultato.getX() );
        assertEquals( 0, risultato.getY() );
        assertEquals( true, risultato.eOccupato() );
    
    }
    
    /**
     * Test del metodo cambiaPezzo della classe Spazio
     * @throws Exception - Eccezzione in caso di fallita creazione dell'oggetto
    */
    @Test
    public void testCambiaPezzo() throws Exception{
        
        Spazio spazio = new Spazio();
        Pezzo alfiereBianco = new Alfiere( new Bianco() );
        
        spazio.cambiaPezzo( alfiereBianco );
    
    }
    
    /**
     * Test del metodo eOccupato della classe Spazio
     * @throws Exception - Eccezzione in caso di fallita creazione dell'oggetto
    */
    @Test
    public void testEOccupato() throws Exception{
        
        Spazio spazio = new Spazio();
        
        boolean risultato = spazio.eOccupato();
        
        assertEquals( false, risultato );
    
    }
    
    /**
     * Test del metodo getOccupante della classe Spazio
     * @throws Exception - Eccezzione in caso di fallita creazione dell'oggetto
    */
    @Test
    public void testGetOccupante() throws Exception{
        
        Spazio spazio = new Spazio();
        
        Pezzo risultato = spazio.getOccupante();
        
        assertEquals( null, risultato );
    
    }
    
    /**
     * Test del metodo getX della classe Spazio
     * @throws Exception - Eccezzione in caso di fallita creazione dell'oggetto
    */
    @Test
    public void testGetX() throws Exception{
        
        Spazio spazio = new Spazio();
        
        int risultato = spazio.getX();
        
        assertEquals( -1, risultato );
    
    }
    
    /**
     * Test del metodo getY della classe Spazio
     * @throws Exception - Eccezzione in caso di fallita creazione dell'oggetto
    */
    @Test
    public void testGetY() throws Exception{
        
        Spazio spazio = new Spazio();
        
        int risultato = spazio.getY();
        
        assertEquals( -1, risultato );
    
    }
    
    /**
     * Test del metodo inizializzaSpazio con due parametri Int, Int della classe Spazio
     * @throws Exception - Eccezzione in caso di fallita creazione dell'oggetto
    */
    @Test
    public void testInizializzaSpazioIntInt() throws Exception{
        
        Spazio spazio = new Spazio();
        int x = 0;
        int y = 0;
        
        spazio.inizializzaSpazio( x, y );
    
    }
    
    /**
     * Test del metodo inizializzaSpazio con tre parametri Pezzo, Int, Int della classe Spazio
     * @throws Exception - Eccezzione in caso di fallita creazione dell'oggetto
    */
    @Test
    public void testInizializzaSpazioPezzoInt() throws Exception{
        
        Spazio spazio = new Spazio();
        Pezzo alfiereBianco = new Alfiere( new Bianco() );
        
        int x = 0;
        int y = 0;
        
        spazio.inizializzaSpazio( alfiereBianco, x, y );
    
    }
    
    /**
     * Test del metodo setOccupato della classe Spazio
     * @throws Exception - Eccezzione in caso di fallita creazione dell'oggetto
    */
    @Test
    public void testSetOccupato() throws Exception{
        
        Spazio spazio = new Spazio();
        boolean chePosto = false;
        
        spazio.setOccupato( chePosto );
    
    }
    
    /**
     * Test del metodo setX della classe Spazio
     * @throws Exception - Eccezzione in caso di fallita creazione dell'oggetto
    */
    @Test
    public void testSetX() throws Exception{
        
        Spazio spazio = new Spazio();
        int x = 0;
        
        spazio.setX( x );
    
    }
    
    /**
     * Test del metodo setY della classe Spazio
     * @throws Exception - Eccezzione in caso di fallita creazione dell'oggetto
    */
    @Test
    public void testSetY() throws Exception{
        
        Spazio spazio = new Spazio();
        
        int y = 0;
        
        spazio.setY( y );
    
    }
    
    /**
     * Langio tutti i test della classe SpazioTest
     * @param args - Argomenti da linea di commando
    */
    public static void main( String[] args ){
        
        Result result = JUnitCore.runClasses( SpazioTest.class );
        
        if( result.getFailureCount() != 0 ){
            
            System.out.println( "Numero Di Test Della Classe SpazioTest Che Non Sono Passati: " + result.getFailureCount() );
        
        } else {
            
            System.out.println( "Tutti I Test Della Classe SpazioTest Sono Passati !!!" );
        
        }
    
    }

} // Fine Classe SpazioTest