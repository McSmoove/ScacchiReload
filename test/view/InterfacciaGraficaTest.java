package view;

import javax.swing.JButton;
import model.*;
import org.junit.*;
import controller.GestoreTB;
import static org.junit.Assert.*;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

/**
 * Test di tutti / alcuni i metodi della classe InterfacciaGrafica
 * @author Viktor, Michael, Gaetano
*/
public class InterfacciaGraficaTest{
    
    /**
     * Test del metodo aggiungiPezzoMorto della classe InterfacciaGrafica
     * @throws Exception - Eccezzione in caso di fallita creazione dell'oggetto
    */
    @Test
    public void testAggiungiPezzoMorto() throws Exception{
        
        InterfacciaGrafica interfaccia = new InterfacciaGrafica();
        Pezzo pezzo = new Alfiere( new Bianco() );
        
        interfaccia.aggiungiPezzoMorto( pezzo );
    
    }
    
    /**
     * Test del metodo finePartita della classe InterfacciaGrafica
     * @throws Exception - Eccezzione in caso di fallita creazione dell'oggetto
    */
    @Test
    public void testFinePartita() throws Exception{
        
        InterfacciaGrafica interfaccia = new InterfacciaGrafica();
        
        interfaccia.finePartita();
    
    }

    /**
     * Test del metodo getGestoreTB della classe InterfacciaGrafica
     * @throws Exception - Eccezzione in caso di fallita creazione dell'oggetto
    */
    @Test
    public void testGetGestoreTB() throws Exception{
        
        InterfacciaGrafica interfaccia = new InterfacciaGrafica();
        
        GestoreTB risultato = interfaccia.getGestoreTB();
        
        assertNotNull( risultato );
    
    }

    /**
     * Test del metodo getMatriceBottoni della classe InterfacciaGrafica
     * @throws Exception - Eccezzione in caso di fallita creazione dell'oggetto
    */
    @Test
    public void testGetMatriceBottoni() throws Exception{
        
        InterfacciaGrafica interfaccia = new InterfacciaGrafica();
        
        JButton[][] risultato = interfaccia.getMatriceBottoni();
        
        assertNotNull( risultato );
    
    }

    /**
     * Test del metodo promozionePedone della classe InterfacciaGrafica
     * @throws Exception - Eccezzione in caso di fallita creazione dell'oggetto
    */
    @Test
    public void testPromozionePedone() throws Exception{
        
        InterfacciaGrafica interfaccia = new InterfacciaGrafica();
        
        Spazio spazio = new Spazio();
        Colore colore = new Bianco();
        Spazio[][] matrice = new Spazio[][]{};
        
        interfaccia.promozionePedone( spazio, colore, matrice );
    
    }
    
    /**
     * Test del metodo setMessaggio della classe InterfacciaGrafica
     * @throws Exception - Eccezzione in caso di fallita creazione dell'oggetto
    */
    @Test
    public void testSetMessaggio() throws Exception{
        
        InterfacciaGrafica interfaccia = new InterfacciaGrafica();
        String stringa = "";
        
        interfaccia.setMessaggio( stringa );
    
    }
    
    /**
     * Test del metodo start della classe InterfacciaGrafica
     * @throws Exception - Eccezzione in caso di fallita creazione dell'oggetto
    */
    @Test
    public void testStart() throws Exception{
        
        InterfacciaGrafica interfaccia = new InterfacciaGrafica();
        
        interfaccia.start();
    
    }
    
    /**
     * Langio tutti i test della classe GestoreMovimentiTest
     * @param args - Argomenti da linea di commando
    */
    public static void main( String[] args ){
        
        Result risultato = JUnitCore.runClasses( InterfacciaGraficaTest.class );
        
        if( risultato.getFailureCount() != 0 ){
            
            System.out.println( "Numero Di Test Della Classe InterfacciaGraficaTest Che Non Sono Passati: " + risultato.getFailureCount() );
        
        } else {
            
            System.out.println( "Tutti I Test Della Classe InterfacciaGraficaTest Sono Passati !!!" );
        
        }
    
    }

} // Fine Classe InterfacciaGraficaTest