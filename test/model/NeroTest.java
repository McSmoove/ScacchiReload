package model;

import org.junit.*;
import static org.junit.Assert.*;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

/**
 * Test di tutti / alcuni i metodi della classe Nero
 * @author Viktor, Michael, Gaetano
*/
public class NeroTest{
    
    /**
     * Test del costruttore Nero
     * Passa perche 0 = Bianco
     * @throws Exception - Eccezzione in caso di fallita creazione dell'oggetto
    */
    @Test
    public void testNero() throws Exception{
        
        Nero risultato = new Nero();
        
        assertNotNull( risultato );
        assertEquals( "Nero", risultato.toString() );
        assertEquals( 0, risultato.getColore() );
    
    }
    
    /**
     * Test del metodo equals della classe Nero
     * @throws Exception - Eccezzione in caso di fallita creazione dell'oggetto
    */
    @Test
    public void testEquals() throws Exception{
        
        Nero nero = new Nero();
        Object objectNero = new Nero();
        
        boolean risultato = nero.equals( objectNero );
        
        assertEquals( true, risultato );
    
    }
    
    /**
     * Test del metodo hashCode della classe Nero
     * Passa perche hash di Nero = -1
     * @throws Exception - Eccezzione in caso di fallita creazione dell'oggetto
    */
    @Test
    public void testHashCode() throws Exception{
        
        Nero nero = new Nero();
        
        int risultato = nero.hashCode();
        
        assertEquals( -1, risultato );
    
    }
    
    /**
     * Langio tutti i test della classe BiancoTest
     * @param args - Argomenti da linea di commando
    */
    public static void main( String[] args ){
        
        Result result = JUnitCore.runClasses( NeroTest.class );
        
        if( result.getFailureCount() != 0 ){
            
            System.out.println( "Numero Di Test Della Classe NeroTest Che Non Sono Passati: " + result.getFailureCount() );
        
        } else {
            
            System.out.println( "Tutti I Test Della Classe NeroTest Sono Passati !!!" );
        
        }
    
    }

} // Fine Classe NeroTest