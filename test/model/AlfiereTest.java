package model;

import org.junit.*;
import static org.junit.Assert.*;
import org.junit.runner.*;

/**
 * Test di tutti / alcuni i metodi della classe Alfiere
 * @author Viktor, Michael, Gaetano
*/
public class AlfiereTest{
    
    /**
     * Test del costruttore Alfiere 
     * @throws Exception - Eccezzione in caso di fallita creazione dell'oggetto
    */
    @Test
    public void testAlfiere() throws Exception{
        
        Colore colore = new Bianco();
        Alfiere risultato = new Alfiere( colore );
        
        assertNotNull( risultato );
    
    }
   
    /**
     * Langio tutti i test della classe AlfiereTest
     * @param args - Argomenti da linea di commando
    */
    public static void main( String[] args ){
        
        Result risultato = JUnitCore.runClasses( AlfiereTest.class );
        
        if( risultato.getFailureCount() != 0 ){
            
            System.out.println( "Numero Di Test Della Classe AlfiereTest Che Non Sono Passati: " + risultato.getFailureCount() );
        
        } else {
            
            System.out.println( "Tutti I Test Della Classe AlfiereTest Sono Passati !!!" );
        
        }
    
    }

} // Fine Classe AlfiereTest