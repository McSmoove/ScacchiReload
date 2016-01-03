package model;

import org.junit.*;
import static org.junit.Assert.*;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

/**
 * Test di tutti / alcuni i metodi della classe Pedone
 * @author Viktor, Michael, Gaetano
*/
public class PedoneTest{
    
    /**
     * Test del costruttore Pedone 
     * @throws Exception - Eccezzione in caso di fallita creazione dell'oggetto
    */
    @Test
    public void testPedone() throws Exception{
        
        Colore colore = new Bianco();
        
        Pedone risultato = new Pedone(colore);
        
        assertNotNull( risultato );
        assertEquals( false, risultato.mosso() );
        
    }
    
    /**
     * Test del metodo impostaMosso della classe Pedone 
     * @throws Exception - Eccezzione in caso di fallita creazione dell'oggetto
    */
    @Test
    public void testImpostaMosso() throws Exception{
        
        Pedone pedone = new Pedone( new Bianco() );
        
        pedone.impostaMosso();
    
    }
    
    /**
     * Test del metodo impostaMosso della classe Pedone 
     * @throws Exception - Eccezzione in caso di fallita creazione dell'oggetto
    */
    @Test
    public void testMosso() throws Exception{
        
        Pedone pedone = new Pedone( new Bianco() );
        
        boolean risultato = pedone.mosso();
        
        assertEquals( false, risultato );
    
    }
    
    /**
     * Langio tutti i test della classe PedoneTest
     * @param args - Argomenti da linea di commando
    */
    public static void main( String[] args ){
        
        Result risultato = JUnitCore.runClasses( PedoneTest.class );
        
        if( risultato.getFailureCount() != 0 ){
            
            System.out.println( "Numero Di Test Della Classe PedoneTest Che Non Sono Passati: " + risultato.getFailureCount() );
        
        } else {
            
            System.out.println( "Tutti I Test Della Classe PedoneTest Sono Passati !!!" );
        
        }
    
    }

} // Fine Classe PedoneTest