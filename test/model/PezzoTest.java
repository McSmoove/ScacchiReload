package model;

import org.junit.*;
import static org.junit.Assert.*;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

/**
 * Test di tutti / alcuni i metodi della classe Pezzo
 * @author Viktor, Michael, Gaetano
*/
public class PezzoTest{
    
    /**
     * Test del metodo getColore della classe Pezzo
     * Passa perche 0 = Bianco
     * @throws Exception - Eccezzione in caso di fallita creazione dell'oggetto
    */
    @Test
    public void testGetColore() throws Exception{
        
        Pezzo alfiereBianco = new Alfiere( new Bianco() );
        
        Colore risultato = alfiereBianco.getColore();
        
        assertNotNull( risultato );
        assertEquals( 0, risultato.getColore() );
    
    }
    
    /**
     * Langio tutti i test della classe PedoneTest
     * @param args - Argomenti da linea di commando
    */
    public static void main( String[] args ){
        
        Result result = JUnitCore.runClasses( PezzoTest.class );
        
        if( result.getFailureCount() != 0 ){
            
            System.out.println( "Numero Di Test Della Classe PezzoTest Che Non Sono Passati: " + result.getFailureCount() );
        
        } else {
            
            System.out.println( "Tutti I Test Della Classe PezzoTest Sono Passati !!!" );
        
        }
    
    }

} // Fine Classe PezzoTest