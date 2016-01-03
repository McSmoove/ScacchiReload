package model;

import org.junit.*;
import static org.junit.Assert.*;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

/**
 * Test di tutti / alcuni i metodi della classe Cavallo
 * @author Viktor, Michael, Gaetano
*/
public class CavalloTest{
    
    /**
     * Test del costruttore Cavallo
     * @throws Exception - Eccezzione in caso di fallita creazione dell'oggetto
    */
    @Test
    public void testCavallo() throws Exception{
        
        Colore colore = new Bianco();
        Cavallo risultato = new Cavallo( colore );
        
        assertNotNull( risultato );
    
    }
    
    /**
     * Langio tutti i test della classe CavalloTest
     * @param args - Argomenti da linea di commando
    */
    public static void main( String[] args ){
        
        Result risultato = JUnitCore.runClasses( CavalloTest.class );
        
        if( risultato.getFailureCount() != 0 ){
            
            System.out.println( "Numero Di Test Della Classe CavalloTest Che Non Sono Passati: " + risultato.getFailureCount() );
        
        } else {
            
            System.out.println( "Tutti I Test Della Classe CavalloTest Sono Passati !!!" );
        
        }
    
    }

} // Fine Classe CavalloTest