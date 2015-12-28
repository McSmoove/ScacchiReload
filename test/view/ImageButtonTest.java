package view;

import java.awt.Graphics;
import java.awt.Image;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test di tutti i metodi della classe ImageButton
 * @author Viktor, Michael, Gaetano
*/
public class ImageButtonTest{
    
    /**
     * Test del metodo setImage della classe ImageButton
    */
    @Test
    public void testSetImage(){
        System.out.println("setImage");
        Image immagine = null;
        ImageButton instance = new ImageButton();
        instance.setImage(immagine);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test del metodo paintComponent della classe ImageButton
    */
    @Test
    public void testPaintComponent(){
        System.out.println("paintComponent");
        Graphics g = null;
        ImageButton instance = new ImageButton();
        instance.paintComponent(g);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
} // Fine Classe ImageButtonTest