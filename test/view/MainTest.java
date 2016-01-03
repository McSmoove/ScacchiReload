package view;

import org.junit.*;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

/**
 * Test di tutti / alcuni i metodi della classe Main
 * @author Viktor, Michael, Gaetano
*/
public class MainTest{
    
    /**
     * Test del metodo main della classe Main
     * @throws Exception - Eccezzione in caso di fallita creazione dell'oggetto
    */
    @Test
    public void testMain() throws Exception{
        
        String[] args = new String[]{};
        
        Main.main( args );
    
    }
    
    /**
     * Langio tutti i test della classe GestoreMovimentiTest
     * @param args - Argomenti da linea di commando
    */
    public static void main( String[] args ){
        
        Result risultato = JUnitCore.runClasses( MainTest.class );
        
        if( risultato.getFailureCount() != 0 ){
            
            System.out.println( "Numero Di Test Della Classe MainTest Che Non Sono Passati: " + risultato.getFailureCount() );
        
        } else {
            
            System.out.println( "Tutti I Test Della Classe MainTest Sono Passati !!!" );
        
        }
    
    }

} // Fine Classe Main