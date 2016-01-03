package model;

import org.junit.*;
import static org.junit.Assert.*;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

/**
 * Test di tutti / alcuni i metodi della classe Re
 * @author Viktor, Michael, Gaetano
*/
public class ReTest{
    
    /**
     * Test del costruttore Re 
     * @throws Exception - Eccezzione in caso di fallita creazione dell'oggetto
    */
    @Test
    public void testRe() throws Exception{
        
        Colore colore = new Bianco();
        
        Re risultato = new Re( colore );
        
        assertNotNull( risultato );
        assertEquals( false, risultato.mosso() );
        
    }
    
    /**
     * Test del metodo impostaMosso della classe Re
     * @throws Exception - Eccezzione in caso di fallita creazione dell'oggetto
    */
    @Test
    public void testImpostaMosso() throws Exception{
        
        Re reBianco = new Re( new Bianco() );
        
        reBianco.impostaMosso();
    
    }
    
    /**
     * Test del metodo mosso della classe Re
     * @throws Exception - Eccezzione in caso di fallita creazione dell'oggetto
    */
    @Test
    public void testMosso() throws Exception{
        
        Re reBianco = new Re( new Bianco() );
        
        boolean risultato = reBianco.mosso();
        
        assertEquals( false, risultato );
    
    }
    
    /**
     * Langio tutti i test della classe ReTest
     * @param args - Argomenti da linea di commando
    */
    public static void main( String[] args ){
        
        Result risultato = JUnitCore.runClasses( ReTest.class );
        
        if( risultato.getFailureCount() != 0 ){
            
            System.out.println( "Numero Di Test Della Classe ReTest Che Non Sono Passati: " + risultato.getFailureCount() );
        
        } else {
            
            System.out.println( "Tutti I Test Della Classe ReTest Sono Passati !!!" );
        
        }
    
    }

} // Fine Classe ReTest