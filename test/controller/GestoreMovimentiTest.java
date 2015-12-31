package controller;

import java.util.LinkedList;
import model.*;
import org.junit.Test;
import static org.junit.Assert.*;
import view.InterfacciaGrafica;

/**
 * Test di tutti i metodi della classe GestoreMovimenti
 * @author Viktor, Michael, Gaetano
*/
public class GestoreMovimentiTest{
    
    
    /**
     * Test del metodo controlloScacco della classe GestoreMovimenti
    */
    @Test
    public void testControlloScacco_3args(){
        System.out.println("controlloScacco");
        int x;
        int y;
        Spazio[][] m;
        
        m = new Spazio[8][8];
        
        
        for(int b = 0; b < 8; b++){
            for(int a = 0; a < 8; a++){
               
                m[a][b] = new Spazio(a, b);
                
            }
        }
        
        //m[5][0].cambiaPezzo(new Re(new Bianco()));
        //m[3][2].cambiaPezzo(new Torre(new Nero()));
        //m[3][0].cambiaPezzo(new Torre(new Nero()));
        GestoreMovimenti instance = new GestoreMovimenti();
        System.err.println("QUI");
        //m=instance.getMatrice();
        instance.disegnaMatriceSpaziOccupati(instance.getMatrice());
        instance.spostaPezzo(instance.getMatrice()[5][6], 5, 4, instance.getMatrice());
        instance.spostaPezzo(instance.getMatrice()[4][1], 4, 3, instance.getMatrice());
        instance.spostaPezzo(instance.getMatrice()[6][6], 6, 4, instance.getMatrice());
        instance.spostaPezzo(instance.getMatrice()[3][0], 7, 4, instance.getMatrice());
        instance.disegnaMatriceSpaziOccupati(instance.getMatrice());
        System.err.println("QUI");
        
        boolean expResult = false;
        boolean result = instance.controlloScacco(4, 7, instance.getMatrice());
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test del metodo scaccoMatto della classe GestoreMovimenti
    */
    @Test
    public void testScaccoMatto(){
        System.out.println("scaccoMatto");
        Colore turnoCorrente = new Bianco();
        GestoreMovimenti instance = new GestoreMovimenti();
        boolean expResult = false;
        boolean result = instance.scaccoMatto(turnoCorrente);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    
} // Fine Classe GestoreMovimentiTest