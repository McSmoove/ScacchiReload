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
    
    
} // Fine Classe InterfacciaGraficaTest