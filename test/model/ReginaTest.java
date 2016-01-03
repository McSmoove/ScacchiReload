package model;

import org.junit.*;
import static org.junit.Assert.*;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

/**
 * Test di tutti / alcuni i metodi della classe Regina
 * @author Viktor, Michael, Gaetano
*/
public class ReginaTest{
    
    /**
     * Test del costruttore Regina
     * @throws Exception - Eccezzione in caso di fallita creazione dell'oggetto
    */
    @Test
    public void testRegina() throws Exception{
        
        Colore colore = new Bianco();
        
        Regina risultato = new Regina( colore );
        
        assertNotNull( risultato );
       
    }
    
    /**
     * Langio tutti i test della classe ReginaTest
     * @param args - Argomenti da linea di commando
     */
    public static void main( String[] args ){
        
        Result risultato = JUnitCore.runClasses( ReginaTest.class );
        
        if( risultato.getFailureCount() != 0 ){
            
            System.out.println( "Numero Di Test Della Classe ReginaTest Che Non Sono Passati: " + risultato.getFailureCount() );
        
        } else {
            
            System.out.println( "Tutti I Test Della Classe ReginaTest Sono Passati !!!" );
        
        }
    
    }

} // Fine Classe ReginaTest