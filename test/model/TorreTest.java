package model;

import org.junit.*;
import static org.junit.Assert.*;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

/**
 * Test di tutti / alcuni i metodi della classe Torre
 * @author Viktor, Michael, Gaetano
*/
public class TorreTest{
    
    /**
     * Test del costruttore Torre 
     * @throws Exception - Eccezzione in caso di fallita creazione dell'oggetto
    */
    @Test
    public void testTorre() throws Exception{
        
        Colore colore = new Bianco();
        
        Torre risultato = new Torre( colore );
        
        assertNotNull( risultato );
        assertEquals( false, risultato.mosso() );
        
    }
    
    /**
     * Test del metodo impostaMosso della classe Torre 
     * @throws Exception - Eccezzione in caso di fallita creazione dell'oggetto
    */
    @Test
    public void testImpostaMosso() throws Exception{
        
        Torre torreBianca = new Torre( new Bianco() );
        
        torreBianca.impostaMosso();
    
    }
    
    /**
     * Test del metodo mosso della classe Torre 
     * @throws Exception - Eccezzione in caso di fallita creazione dell'oggetto
    */
    @Test
    public void testMosso() throws Exception{
        
        Torre torreBianca = new Torre( new Bianco() );
        
        boolean risultato = torreBianca.mosso();
        
        assertEquals( false, risultato );
    
    }
    
    /**
     * Langio tutti i test della classe Torre
     * @param args - Argomenti da linea di commando
    */
    public static void main( String[] args ){
        
        Result result = JUnitCore.runClasses( TorreTest.class );
        
        if( result.getFailureCount() != 0 ){
            
            System.out.println( "Numero Di Test Della Classe TorreTest Che Non Sono Passati: " + result.getFailureCount() );
        
        } else {
            
            System.out.println( "Tutti I Test Della Classe TorreTest Sono Passati !!!" );
        
        }
    
    }

} // Fine Classe TorreTest