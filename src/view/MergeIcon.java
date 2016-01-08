package view;

import java.awt.*;
import javax.swing.*;
import java.awt.image.BufferedImage;

/**
 * Ridefinizione della classe Icon affinche sia possibile di sovraporre due immagini
 * creando un'icona per i bottoni della scacchiera contenente le due immagini sovrapposte.
 * @author Viktor Teren VR379996, Michael Andronic VR370063, Gaetano Cavaler VR379845
*/
public class MergeIcon implements Icon{
    
    /**
     * La larghezza del'immagine unita
    */
    private final int larghezza;
    
    /**
     * L'altezza dell'immagine unita
    */
    private final int altezza;
    
    /**
     * Buffer dell'immagine unita
    */
    private final BufferedImage buffer;

    /**
     * Metodo che unisce due immagini in una sola
     * @param sfondo - L'immagine in backgroud
     * @param icona - L'immagine in foreground
    */
    public MergeIcon( Image sfondo, Image icona ){
        
        larghezza = sfondo.getWidth( null ); // Prendo la larghezza dell'immagine in background
        altezza = sfondo.getHeight( null ); // Prendo l'altezza dell'immagine in background
        buffer = new BufferedImage( larghezza, altezza, BufferedImage.TYPE_INT_ARGB ); // Inizializzo un buffer delle dimensioni prese
        
        Graphics2D g = ( Graphics2D ) buffer.getGraphics(); // Creo il backgroud
        
        g.drawImage( sfondo, 0, 0, null ); // Disegno il backgroud
        
        if (icona != null) { // Se e stata fornita un'icona
            
            g.drawImage( icona, 0, 0, null ); // Disegno l'icona sopra il buffer che ha gia il backgroud disegnato
        
        }
    
    } // Fine MergeIcon
    
    /**
     * Metodo che ritorna la larghezza in pixel dell'icona unita
     * @return larghezza - La Larghezza in pixel dell'icona unita
    */
    @Override
    public int getIconWidth(){
        
        return larghezza; // Ritorna la larghezza in pixel dell'icona unita
    
    } // Fine getIconWidth

    /**
     * Metodo che ritorna l'altezza in pixel dell'icona unita
     * @return altezza - L'altezza in pixel dell'icona unita
    */
    @Override
    public int getIconHeight(){ 
        
        return altezza; // Ritorna l'altezza in pixel dell'icona unita
    
    } // Fine getIconHeigh
    
    /**
     * Metodo che stampa l'icona unita del buffer
     * @param c - Il componente da usare se l'icona non ha un'image observer
     * @param g - Il buffer grafico dell'icona
     * @param x - La coordinana X dall'alto sinistra dell'icona
     * @param y - La Coordinata Y dall'alto sinistra dall'icona
    */
    @Override
    public void paintIcon( Component c, Graphics g, int x, int y ){ 
       
        g.drawImage( buffer, x, y, null ); // Disegno il buffer contenente l'icona unita
        
    } // Fine paintIcon
    
    /**
     * Metodo che prende una icona e la bufferizza
     * @param icon - L'icona da bufferizzare
     * @return L'icona bufferizzata
    */
    public static BufferedImage iconToBufferedImage( Icon icon ){
        
        if ( icon == null ){ // Se non e stata fornita nessuna icona
            
            return null;
        
        }
        
        BufferedImage image = new BufferedImage( icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_ARGB ); // Bufferizzo l'icona fornita
        icon.paintIcon( null, image.getGraphics(), 0, 0 ); // Stampo l'icona fornita
        return image; // Ritorno l'icona buferizzata
    
    } // Fine iconToBufferedImage

    /**
     * Fornita un'icona ritorno l'immagine di essa
     * @param icon - L'Icona fornita
     * @return L'Immagine dell'icona fornita
    */
    public static Image iconToImage( Icon icon ) {
        
        if( icon == null ){ // Se non e stata fornita nessuna icona
            
            return null;
        
        }
        
        if( icon instanceof ImageIcon ){ // Se e una istanza di ImageIcon
            
            return ( (ImageIcon) icon ).getImage(); // Forzo a ImageIcon l'icona
        
        }
        
        return iconToBufferedImage( icon ); // Buferizzo l'icona estraendo l'immagine
    
    } // Fine iconToImage

} // Fine Classe MergeIcon