package controller;

import java.awt.event.ActionEvent;
import model.*;
import view.InterfacciaGrafica;
import model.Alfiere;
import model.Bianco;
import org.junit.*;
import static org.junit.Assert.*;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

/**
 * Test di tutti / alcuni i metodi della classe GestoreTB
 * @author Viktor, Michael, Gaetano
*/
public class GestoreTBTest{
    
    /**
     * Test del costruttore GestoreTB 
     * @throws Exception - Eccezzione in caso di fallita creazione dell'oggetto
    */
    @Test
    public void testGestoreTB() throws Exception{
        
        GestoreMovimenti gestore = new GestoreMovimenti();
        InterfacciaGrafica interfaccia = null;
        
        GestoreTB risultato = new GestoreTB( gestore, interfaccia );
        
        assertNotNull( risultato );
    
    }
    
    /**
     * Test del metodo attiva della classe GestoreTB
     * @throws Exception - Eccezzione in caso di fallita creazione dell'oggetto
    */
    @Test
    public void testAttiva() throws Exception{
        
        GestoreTB gestore = new GestoreTB( new GestoreMovimenti(), ( InterfacciaGrafica ) null );
        
        gestore.setXPedoneTrasformato( 1 );
        gestore.setYPedoneTrasformato( 1 );
        gestore.setTurno( new Bianco() );
        
        gestore.y = 1;
        gestore.x = 1;
        
        gestore.attiva();
    
    }
    
    /**
     * Test del metodo bloccoPezziInizialeattiva della classe GestoreTB
     * @throws Exception - Eccezzione in caso di fallita creazione dell'oggetto
    */
    @Test
    public void testBloccoPezziIniziale() throws Exception{
        
        GestoreTB gestore = new GestoreTB( new GestoreMovimenti(), ( InterfacciaGrafica ) null );
        
        gestore.setXPedoneTrasformato( 1 );
        gestore.setYPedoneTrasformato( 1 );
        gestore.setTurno( new Bianco() );
        
        gestore.y = 1;
        gestore.x = 1;
        
        gestore.bloccoPezziIniziale();
    
    }
    
    /**
     * Test del metodo disattiva della classe GestoreTB
     * @throws Exception - Eccezzione in caso di fallita creazione dell'oggetto
    */
    @Test
    public void testDisattiva() throws Exception{
        
        GestoreTB gestore = new GestoreTB( new GestoreMovimenti(), ( InterfacciaGrafica ) null );
        
        gestore.setXPedoneTrasformato( 1 );
        gestore.setYPedoneTrasformato( 1 );
        gestore.setTurno( new Bianco() );
        
        gestore.y = 1;
        gestore.x = 1;
        
        gestore.disattiva();
    
    }
    
    /**
     * Test del metodo disegnaMatriceSpaziOccupati della classe GestoreTB
     * @throws Exception - Eccezzione in caso di fallita creazione dell'oggetto
    */
    @Test
    public void testDisegnaMatriceSpaziOccupati() throws Exception{
        
        GestoreTB gestore = new GestoreTB( new GestoreMovimenti(), ( InterfacciaGrafica ) null );
        
        gestore.setXPedoneTrasformato( 1 );
        gestore.setYPedoneTrasformato( 1 );
        gestore.setTurno( new Bianco() );
        
        gestore.y = 1;
        gestore.x = 1;
        
        gestore.disegnaMatriceSpaziOccupati();
    
    }
    
    /**
     * Test del metodo getTurno della classe GestoreTB
     * @throws Exception - Eccezzione in caso di fallita creazione dell'oggetto
    */
    @Test
    public void testGetTurno() throws Exception{
        
        GestoreTB gestore = new GestoreTB( new GestoreMovimenti(), ( InterfacciaGrafica ) null );
        gestore.setXPedoneTrasformato( 1 );
        gestore.setYPedoneTrasformato( 1 );
        gestore.setTurno( new Bianco() );
        
        gestore.y = 1;
        gestore.x = 1;
        
        Colore risultato = gestore.getTurno();
        
        assertNotNull( risultato );
    
    }
    
    /**
     * Test del metodo getXPedoneTrasformato della classe GestoreTB
     * @throws Exception - Eccezzione in caso di fallita creazione dell'oggetto
    */
    @Test
    public void testGetXPedoneTrasformato() throws Exception{
        
        GestoreTB gestore = new GestoreTB( new GestoreMovimenti(), ( InterfacciaGrafica ) null );
        
        gestore.setXPedoneTrasformato( 1 );
        gestore.setYPedoneTrasformato( 1 );
        gestore.setTurno( new Bianco() );
        
        gestore.y = 1;
        gestore.x = 1;
        
        int risultato = gestore.getXPedoneTrasformato();
        
        assertEquals( 0, risultato );
    
    }
    
    /**
     * Test del metodo getYPedoneTrasformato della classe GestoreTB
     * @throws Exception - Eccezzione in caso di fallita creazione dell'oggetto
    */
    @Test
    public void testGetYPedoneTrasformato() throws Exception{
        
        GestoreTB gestore = new GestoreTB( new GestoreMovimenti(), ( InterfacciaGrafica ) null );
        
        gestore.setXPedoneTrasformato( 1 );
        gestore.setYPedoneTrasformato( 1 );
        gestore.setTurno( new Bianco() );
        
        gestore.y = 1;
        gestore.x = 1;
        
        int risultato = gestore.getYPedoneTrasformato();
        
        assertEquals( 0, risultato );
    
    }
    
    /**
     * Test del metodo isAttivato della classe GestoreTB
     * @throws Exception - Eccezzione in caso di fallita creazione dell'oggetto
    */
    @Test
    public void testIsAttivato() throws Exception{
        
        GestoreTB gestore = new GestoreTB( new GestoreMovimenti(), ( InterfacciaGrafica ) null );
        
        gestore.setXPedoneTrasformato( 1 );
        gestore.setYPedoneTrasformato( 1 );
        gestore.setTurno( new Bianco() );
        
        gestore.y = 1;
        gestore.x = 1;
        
        boolean risultato = gestore.isAttivato();
        
        assertTrue( risultato );
    
    }
    
    /**
     * Test del metodo passaTurno della classe GestoreTB
     * @throws Exception - Eccezzione in caso di fallita creazione dell'oggetto
    */
    @Test
    public void testPassaTurno() throws Exception{
        
        GestoreTB gestore = new GestoreTB( new GestoreMovimenti(), ( InterfacciaGrafica ) null );
        
        gestore.setXPedoneTrasformato( 1 );
        gestore.setYPedoneTrasformato( 1 );
        gestore.setTurno( new Bianco() );
        
        gestore.y = 1;
        gestore.x = 1;
        
        gestore.passaTurno();
    
    }
    
    /**
     * Test del metodo pressionePulsanteScacchiera della classe GestoreTB
     * @throws Exception - Eccezzione in caso di fallita creazione dell'oggetto
    */
    @Test
    public void testPressionePulsanteScacchiera() throws Exception{
        
        GestoreTB gestore = new GestoreTB( new GestoreMovimenti(), ( InterfacciaGrafica ) null );
        
        gestore.setXPedoneTrasformato( 1 );
        gestore.setYPedoneTrasformato( 1 );
        gestore.setTurno( new Bianco() );
        
        gestore.y = 1;
        gestore.x = 1;
        
        ActionEvent evento = new ActionEvent( new Object(), 1, "" );
        
        gestore.pressionePulsanteScacchiera( evento );
    
    }
    
    /**
     * Test del metodo setPedoneTrasformato della classe GestoreTB
     * @throws Exception - Eccezzione in caso di fallita creazione dell'oggetto
    */
    @Test
    public void testSetPedoneTrasformato() throws Exception{
        
        GestoreTB gestore = new GestoreTB( new GestoreMovimenti(), ( InterfacciaGrafica ) null );
        
        gestore.setXPedoneTrasformato( 1 );
        gestore.setYPedoneTrasformato( 1 );
        gestore.setTurno( new Bianco() );
        
        gestore.y = 1;
        gestore.x = 1;
        
        Spazio spazio = new Spazio( 1, 1, new Alfiere( new Bianco() ) );
        
        gestore.setPedoneTrasformato( spazio );
    
    }
    
    /**
     * Test del metodo setTurno della classe GestoreTB
     * @throws Exception - Eccezzione in caso di fallita creazione dell'oggetto
    */
    @Test
    public void testSetTurno() throws Exception{
        
        GestoreTB gestore = new GestoreTB( new GestoreMovimenti(), ( InterfacciaGrafica ) null );
        
        gestore.setXPedoneTrasformato( 1 );
        gestore.setYPedoneTrasformato( 1 );
        gestore.setTurno( new Bianco() );
        
        gestore.y = 1;
        gestore.x = 1;
        
        Colore colore = new Bianco();
        
        gestore.setTurno( colore );
    
    }
    
    /**
     * Test del metodo setXPedoneTrasformato della classe GestoreTB
     * @throws Exception - Eccezzione in caso di fallita creazione dell'oggetto
    */
    @Test
    public void testSetXPedoneTrasformato() throws Exception{
        
        GestoreTB gestore = new GestoreTB( new GestoreMovimenti(), ( InterfacciaGrafica ) null );
        
        gestore.setXPedoneTrasformato( 1 );
        gestore.setYPedoneTrasformato( 1 );
        gestore.setTurno( new Bianco() );
        
        gestore.y = 1;
        gestore.x = 1;
        
        int x = 1;
        
        gestore.setXPedoneTrasformato( x );
    
    }
    
    /**
     * Test del metodo setYPedoneTrasformato della classe GestoreTB
     * @throws Exception - Eccezzione in caso di fallita creazione dell'oggetto
    */
    @Test
    public void testSetYPedoneTrasformato() throws Exception{
        
        GestoreTB gestore = new GestoreTB( new GestoreMovimenti(), ( InterfacciaGrafica ) null );
        
        gestore.setXPedoneTrasformato( 1 );
        gestore.setYPedoneTrasformato( 1 );
        gestore.setTurno( new Bianco() );
        
        gestore.y = 1;
        gestore.x = 1;
        
        int y = 1;
        
        gestore.setYPedoneTrasformato( y );
    
    }
    
    /**
     * Langio tutti i test della classe GestoreTBTest
     * @param args - Argomenti da linea di commando
    */
    public static void main( String[] args ){
        
        Result risultato = JUnitCore.runClasses( GestoreTBTest.class );
        
        if( risultato.getFailureCount() != 0 ){
            
            System.out.println( "Numero Di Test Della Classe GestoreTBTest Che Non Sono Passati: " + risultato.getFailureCount() );
        
        } else {
            
            System.out.println( "Tutti I Test Della Classe GestoreTBTest Sono Passati !!!" );
        
        }
    
    }

} // Fine Classe GestoreTBTest