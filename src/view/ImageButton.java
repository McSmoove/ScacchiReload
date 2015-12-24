package view;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
/**
 * Classe delle immagini degli scacchi
 * @author Gaetano
 */
public class ImageButton extends JButton{
    
    private Image immagine;
    private Image icona;
    private final Rectangle area = new Rectangle();
    
    public ImageButton(){}
    
    /**
     * Costruttore di base
     * Setta l'immagine dello scacco
     * @param immagine 
     */
    public void setImage( Image immagine ){
        
        this.immagine = immagine;
        repaint();
    
    }
    
    /**
     * Ritorna l'immagine dell'oggetto
     * @return il parametro immagine (tipo image) della classe
     */
    public Image getImage(){ return immagine; }
    
    /**
     * Modifica il parametro icona con la nuova icona passata
     * @param icona (tipo image) l'icona da impostare
     */
    public void setIcona( Image icona ){
        
        this.icona = icona;
        repaint();
    
    }
    
    /**
     * Ritorna l'icona della classe
     * @return il parametro icona (tipo image) della classe) 
     */
    public Image getIcona(){ return icona; }
    
    /**
     * Disegna la casella (dove verrà impostat il bottone) sulla scacchiera
     * @param g la grafica passata
     */
    @Override
    protected void paintComponent( Graphics g ){
        
        super.paintComponent( g );
        
        if( immagine != null ){
            
            SwingUtilities.calculateInnerArea( this, area );
            g.drawImage( immagine, area.x, area.y, area.width, area.height, this );
        
        }
    
    }
    
    /**
     * disegna uno scacco disegnato sopra la texture del legno
     * @return la grafica da usare in paintComponent
     */
    public Graphics2D drawIcon(){
        
        int larghezza = immagine.getWidth(this);
        int altezza = immagine.getHeight(this);
        
        final BufferedImage combinedImage = new BufferedImage( larghezza, altezza, BufferedImage.TYPE_INT_ARGB );
        Graphics2D g = combinedImage.createGraphics();
        g.drawImage( immagine, 0, 0, null );
        g.drawImage( icona, 0, 0, null );
        g.dispose();
        
        return g;
        
    }
    
}