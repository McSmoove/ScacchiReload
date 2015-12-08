
package view;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 * Il merge icon è quella classe che permette di stampare il pezzo sopra la 
 * texture della casella. Il metodo più importante è IconToBuffredImage. In 
 * pratica elabora un'immagine usando i componenti del package immagini e 
 * sovrapponendoli tra loro. E' il caso analogo, ad esempio, ai Layer di 
 * Photoshop. Inoltre prima fa anche un resize dell'imagine.
 * @author λ - s(h)ound
 */
public class MergeIcon implements Icon{

    private int larghezza;
    private int altezza;
    private BufferedImage buffer;
    
    public MergeIcon( Icon sfondo, Icon icona ){
        
        this( sfondo, icona, 0, 0 );
    
    }

    public MergeIcon( Image sfondo, Image icona ){
        
        this( sfondo, icona, 0, 0 );
    
    }
    
    public MergeIcon( Icon sfondo, Icon icona, int offsetX, int offsetY ){
        
        this( iconToImage( sfondo ), iconToImage( icona ), offsetX, offsetY );
    
    }
    
    /**
     * Costruttore principale.
     * @param sfondo
     * @param icona
     * @param offsetX
     * @param offsetY 
     */
    public MergeIcon( Image sfondo, Image icona, int offsetX, int offsetY ){
        
        larghezza = sfondo.getWidth( null );
        altezza = sfondo.getHeight( null );
        buffer = new BufferedImage( larghezza, altezza, BufferedImage.TYPE_INT_ARGB );
        
        Graphics2D g = ( Graphics2D ) buffer.getGraphics();
        
        g.drawImage( sfondo, 0, 0, null );
        
        if (icona != null) {
            
            g.drawImage( icona, offsetX, offsetY, null );
        
        }
    
    }
    
    @Override
    public int getIconWidth(){ return larghezza; }

    @Override
    public int getIconHeight(){ return altezza; }
    
    /**
     * Stampa l'immagine in posizione x,y usando la funzione iconToImage che a
     * sua volta usa iconToBufferedImage.
     * @param c
     * @param g
     * @param x
     * @param y 
     */
    @Override
    public void paintIcon( Component c, Graphics g, int x, int y ) { g.drawImage( buffer, x, y, null ); }
    
    /**
     * 
     * @param icon
     * @return 
     */
    public static BufferedImage iconToBufferedImage( Icon icon ){
        
        if ( icon == null ){
            
            return null;
        
        }
        
        BufferedImage image = new BufferedImage( icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_ARGB );
        icon.paintIcon( null, image.getGraphics(), 0, 0 );
        return image;
    
    }
    
    public static Image iconToImage( Icon icon ) {
        
        if( icon == null ){
            
            return null;
        
        }
        
        if( icon instanceof ImageIcon ){
            
            return ( (ImageIcon) icon ).getImage();
        
        }
        
        return iconToBufferedImage( icon );
    
    }

}