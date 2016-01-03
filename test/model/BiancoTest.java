package model;

import org.junit.*;
import static org.junit.Assert.*;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

/**
 * Test di tutti / alcuni i metodi della classe Bianco
 * @author Viktor, Michael, Gaetano
*/
public class BiancoTest{
    
    /**
     * Test del costruttore Bianco
     * Passa perche 0 = Bianco
     * @throws Exception - Eccezzione in caso di fallita creazione dell'oggetto
    */
    @Test
    public void testBianco() throws Exception{
        
        Bianco risultato = new Bianco();
        
        assertNotNull( risultato );
        assertEquals( "Bianco", risultato.toString() );
        assertEquals( 0, risultato.getColore() );
    
    }
    
    /**
     * Test del metodo equals della classe Bianco
     * @throws Exception - Eccezzione in caso di fallita creazione dell'oggetto
    */
    @Test
    public void testEquals() throws Exception{
        
        Bianco bianco = new Bianco();
        Object objectBianco = new Bianco();
        
        boolean risultato = bianco.equals( objectBianco );
        
        assertEquals( true, risultato );
    
    }
    
    /**
     * Test del metodo hashCode della classe Bianco
     * Passa perche hash di Bianco = 1
     * @throws Exception - Eccezzione in caso di fallita creazione dell'oggetto
    */
    @Test
    public void testHashCode() throws Exception{
        
        Bianco bianco = new Bianco();
        int risultato = bianco.hashCode();
        
        assertEquals( 1, risultato );
    
    }
    
    /**
     * Langio tutti i test della classe BiancoTest
     * @param args - Argomenti da linea di commando
    */
    public static void main( String[] args ){
        
        Result risultato = JUnitCore.runClasses( BiancoTest.class );
        
        if( risultato.getFailureCount() != 0 ){
            
            System.out.println( "Numero Di Test Della Classe BiancoTest Che Non Sono Passati: " + risultato.getFailureCount() );
        
        } else {
            
            System.out.println( "Tutti I Test Della Classe BiancoTest Sono Passati !!!" );
        
        }
    
    }

} // Fine Classe BiancoTest