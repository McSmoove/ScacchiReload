package view;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.DebugGraphics;
import javax.swing.Icon;
import org.junit.*;
import static org.junit.Assert.*;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

/**
 * Test di tutti / alcuni i metodi della classe MergeIcon
 * @author Viktor, Michael, Gaetano
*/
public class MergeIconTest{
    
    /**
     * Test del costruttore MergeIconSenzaParametri
     * @throws Exception - Eccezzione in caso di fallita creazione dell'oggetto
    */
    @Test
    public void testMergeIconSenzaParametri() throws Exception{
        
        Image sfondo = null;
        Image icona = null;
        
        MergeIcon risultato = new MergeIcon( sfondo, icona );
        
        assertNotNull( risultato );
    
    }
    
    /**
     * Test del costruttore MergeIcon con parametro Image e Image 
     * @throws Exception - Eccezzione in caso di fallita creazione dell'oggetto
    */
    @Test
    public void testMergeIconImageImage() throws Exception{
        
        Image sfondo = null;
        Image icona = null;
        
        MergeIcon risultato = new MergeIcon( sfondo, icona );
        
        assertNotNull( risultato );
    
    }
    
    /**
     * Test del metodo getIconHeigh della classe MergeIcon
     * @throws Exception - Eccezzione in caso di fallita creazione dell'oggetto
    */
    @Test
    public void testGetIconHeight() throws Exception{
        
        MergeIcon merge = new MergeIcon( ( Image ) null, ( Image ) null );
        
        int risultato = merge.getIconHeight();
        
        assertEquals( 0, risultato );
    
    }
    
    /**
     * Test del metodo getIconWidth della classe MergeIcon
     * @throws Exception - Eccezzione in caso di fallita creazione dell'oggetto
    */
    @Test
    public void testGetIconWidth() throws Exception{
        
        MergeIcon merge = new MergeIcon( ( Image ) null, ( Image ) null );
        
        int risultato = merge.getIconWidth();
        
        assertEquals( 0, risultato );
    
    }
    
    /**
     * Test del metodo iconToBufferedImage della classe MergeIcon
     * @throws Exception - Eccezzione in caso di fallita creazione dell'oggetto
    */
    @Test
    public void testIconToBufferedImage() throws Exception{
        
        Icon icon = null;
        
        BufferedImage risultato = MergeIcon.iconToBufferedImage( icon );
        
        assertEquals( null, risultato );
    
    }
    
    /**
     * Test del metodo iconToImage della classe MergeIcon
     * @throws Exception - Eccezzione in caso di fallita creazione dell'oggetto
    */
    @Test
    public void testIconToImage() throws Exception{
        
        Icon icon = null;
        
        Image risultato = MergeIcon.iconToImage( icon );
        
        assertEquals( null, risultato );
    
    }
    
    /**
     * Test del metodo paintIcon della classe MergeIcon
     * @throws Exception - Eccezzione in caso di fallita creazione dell'oggetto
    */
    @Test
    public void testPaintIcon() throws Exception{
        
        MergeIcon merge = new MergeIcon( ( Image ) null, ( Image ) null );
        
        Component componente = new ImageButton();
        Graphics grafica = new DebugGraphics();
        
        int x = 1;
        int y = 1;
        
        merge.paintIcon( componente, grafica, x, y );
    
    }
    
    /**
     * Langio tutti i test della classe MergeIconTest
     * @param args - Argomenti da linea di commando
    */
    public static void main( String[] args ){
        
        Result risultato = JUnitCore.runClasses( MergeIconTest.class );
        
        if( risultato.getFailureCount() != 0 ){
            
            System.out.println( "Numero Di Test Della Classe MergeIconTest Che Non Sono Passati: " + risultato.getFailureCount() );
        
        } else {
            
            System.out.println( "Tutti I Test Della Classe MergeIconTest Sono Passati !!!" );
        
        }
    
    }

} // Fine Classe MergeIconTest