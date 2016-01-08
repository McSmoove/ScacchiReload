package view;

import javax.swing.JButton;
import model.*;
import org.junit.*;
import static org.junit.Assert.*;


/**
 * Test di alcuni metodi della classe InterfacciaGrafica
 * @author Viktor Teren VR379996, Michael Andronic VR370063, Gaetano Cavaler VR379845
*/
public class InterfacciaGraficaTest{
    
    /**
     * Test del metodo aggiungiPezzoMorto
     * @throws Exception - Eccezione lanciata nel caso di impossibilita di aggiunta di un pezzo morto
    */
    @Test
    public void testAggiungiPezzoMorto() throws Exception{
        
        InterfacciaGrafica interfaccia = new InterfacciaGrafica();
        Pezzo pezzo = new Alfiere( new Bianco() );
        
        interfaccia.aggiungiPezzoMorto( pezzo );
    
    }
    
    /**
     * Test del metodo getMatriceBottoni
     * @throws Exception - Eccezione lanciata nel casoo in cui non riesco a reperire la matrice dei bottoni
    */
    @Test
    public void testGetMatriceBottoni() throws Exception{
        
        InterfacciaGrafica interfaccia = new InterfacciaGrafica();
        
        JButton[][] risultato = interfaccia.getMatriceBottoni();
        
        assertNotNull( risultato );
    
    }

    /**
     * Test del metodo promozionePedone
     * @throws Exception - Eccezione lanciata nel caso di fallita promozione del pedone
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
     * Test del metodo setMessaggio
     * @throws Exception - Eccezione lanciata nel caso di impossibilita di scrivere il messaggio specificato
    */
    @Test
    public void testSetMessaggio() throws Exception{
        
        InterfacciaGrafica interfaccia = new InterfacciaGrafica();
        String stringa = "Scacco Matto !!!";
        
        interfaccia.setMessaggio( stringa );
    
    }
    
    /**
     * Test del metodo start
     * @throws Exception - Eccezione lanciata nel caso in cui l'interfaccia non puo essere lanciata
    */
    @Test
    public void testStart() throws Exception{
        
        InterfacciaGrafica interfaccia = new InterfacciaGrafica();
        
        interfaccia.start();
    
    }
    
    
} // Fine Classe InterfacciaGraficaTest