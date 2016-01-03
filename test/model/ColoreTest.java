package model;

import org.junit.*;
import static org.junit.Assert.*;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

/**
 * Test di tutti / alcuni i metodi della classe Colore
 * @author Viktor, Michael, Gaetano
*/
public class ColoreTest{
    
    /**
     * Test del metodo equals della classe Colore
     * @throws Exception - Eccezzione in caso di fallita creazione dell'oggetto
    */
    @Test
    public void testEquals() throws Exception{
        
        Colore bianco = new Bianco();
        Object objectBianco = new Bianco();
        
        boolean risultato = bianco.equals( objectBianco );
        
        assertEquals( true, risultato );
    
    }
    
    /**
     * Test del metodo getColore della classe Colore
     * Test passa perche 0 = Bianco
     * @throws Exception - Eccezzione in caso di fallita creazione dell'oggetto
    */
    @Test
    public void testGetColore() throws Exception{
        
        Colore bianco = new Bianco();
        
        int risultato = bianco.getColore();
        
        assertEquals( 0, risultato );
    
    }
    
    /**
     * Test del metodo hashCode della classe Colore
     * @throws Exception - Eccezzione in caso di fallita creazione dell'oggetto
    */
    @Test
    public void testHashCode() throws Exception{
        
        Colore bianco = new Bianco();
        
        int risultato = bianco.hashCode();
        
        assertEquals( 1, risultato );
    
    }
    
    /**
     * Langio tutti i test della classe ColoreTest
     * @param args - Argomenti da linea di commando
    */
    public static void main( String[] args ){
        
        Result result = JUnitCore.runClasses( ColoreTest.class );
        
        if( result.getFailureCount() != 0 ){
            
            System.out.println( "Numero Di Test Della Classe ColoreTest Che Non Sono Passati: " + result.getFailureCount() );
        
        } else {
            
            System.out.println( "Tutti I Test Della Classe ColoreTest Sono Passati !!!" );
        
        }
    
    }

} // Fine Classe ColoreTest