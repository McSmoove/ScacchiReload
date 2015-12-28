package view;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.Icon;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test di tutti i metodi della classe MergeIcon
 * @author Viktor, Michael, Geatano
*/
public class MergeIconTest{
    
    /**
     * Test del metodo getIconWidth della classe MergeIcon
    */
    @Test
    public void testGetIconWidth(){
        System.out.println("getIconWidth");
        MergeIcon instance = null;
        int expResult = 0;
        int result = instance.getIconWidth();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test del metodo getIconHeight della classe MergeIcon
    */
    @Test
    public void testGetIconHeight(){
        System.out.println("getIconHeight");
        MergeIcon instance = null;
        int expResult = 0;
        int result = instance.getIconHeight();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test del metodo paintIcon della classe MergeIcon
    */
    @Test
    public void testPaintIcon(){
        System.out.println("paintIcon");
        Component c = null;
        Graphics g = null;
        int x = 0;
        int y = 0;
        MergeIcon instance = null;
        instance.paintIcon(c, g, x, y);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test del metodo iconToBufferedImage della classe MergeIcon
    */
    @Test
    public void testIconToBufferedImage(){
        System.out.println("iconToBufferedImage");
        Icon icon = null;
        BufferedImage expResult = null;
        BufferedImage result = MergeIcon.iconToBufferedImage(icon);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test del metodo iconToImage della classe MergeIcon
    */
    @Test
    public void testIconToImage(){
        System.out.println("iconToImage");
        Icon icon = null;
        Image expResult = null;
        Image result = MergeIcon.iconToImage(icon);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
} // Fine Classe MergeIconTest