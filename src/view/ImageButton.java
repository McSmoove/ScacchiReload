package view;

import java.awt.*;
import javax.swing.*;

/**
 * Ridefinizione della classe Button affinche i bottoni abbiano
 * delle icone personalizzate create dalla Classe MergeIcon.
 * @author Viktor, Michael, Gaetano
*/
public class ImageButton extends JButton{
    
    private Image immagine; // Immagine O Icona Tipicamente Dalla Classe MergeIcon
    private final Rectangle area = new Rectangle(); // Rettangolo dove verra messa la dimensione del bottone da disegnare
    
    /**
     * Stampa l'icona specificata sul bottone e lo disegna in runtime
     * @param immagine - Il background da stampare
    */
    public void setImage( Image immagine ){
        
        this.immagine = immagine; // L'icona specificata
        repaint(); // Repaint in runtime
    
    }
    
    /**
     * Metodo che disegna la grafica passata sull'bottone
     * @param g - La Grafica Da Disegnare
    */
    @Override
    protected void paintComponent( Graphics g ){
        
        super.paintComponent( g ); // Se non e stata fornita nessuna icona unita dalla classe MergeIcon uso il paintComponent della superclasse
        
        if( immagine != null ){ // Se e stata specificata una icona dalla classe MergeIcon
            
            SwingUtilities.calculateInnerArea( this, area ); // Ogni volta che ristampo calcolo l'area della finestra attiva
            g.drawImage( immagine, area.x, area.y, area.width, area.height, this ); // Disegno sul bottone l'icona della classe MergeIcon sull'area calcolata
        
        }
    
    } // Fine paintComponent
    
} // Fine Classe ImageButton