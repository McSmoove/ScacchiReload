package view;

import controller.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.SwingUtilities;
import javax.swing.border.*;
import model.*;


/**
 * Classe inizialmente pensata per semplificare la promozione del pedone, poi non più usata
 * @author Tyrande
 */
public class PromozionePedone{
    JFrame frame = new JFrame( "Scacchi Beta !!!" );
    private final int bianco = 0;
    private final int nero = 1;
    /*
    private final int TORRE_BIANCA = 1;
    private final int CAVALLO_BIANCO = 2;
    private final int ALFIERE_BIANCO = 3;
    private final int REGINA_BIANCA = 4;
    private final int TORRE_NERA = 7;
    private final int CAVALLO_NERO = 8;
    private final int ALFIERE_NERO = 9;
    private final int REGINA_NERA = 10;
    */
    
    private JPanel panMain = new JPanel( new BorderLayout() );
    private JPanel scacchiera = new JPanel( new GridLayout( 1, 4 ) );
    
    private final Image immagine[] = new Image[ 12 ];
    private Image colore[] = new Image[ 3 ];
    private Border bordo = new CompoundBorder( new EmptyBorder( 5, 5, 5, 5 ), new LineBorder( Color.RED ) );
    private Border bordo2 = new CompoundBorder( new EmptyBorder( 5, 5, 5, 5 ), new LineBorder( Color.BLUE ) );
    private MergeIcon[] mergeIco = new MergeIcon[ 4 ];
    private ImageButton []bottone;
    private Pezzo prescelto;
    
    public PromozionePedone(GestoreMovimenti gm, InterfacciaGrafica ig,Spazio[][] matrice,Spazio s,Colore color,int x,int y){ // Inizializza Interfaccia Grafica ( Costruttore )
       
        try{
            
            immagine[ 0 ] = ImageIO.read( getClass().getResource( "../immagini/torreBianca.png" ) );
            immagine[ 1 ] = ImageIO.read( getClass().getResource( "../immagini/cavalloBianco.png" ) );
            immagine[ 2 ] = ImageIO.read( getClass().getResource( "../immagini/alfiereBianco.png" ) );
            immagine[ 3 ] = ImageIO.read( getClass().getResource( "../immagini/reginaBianca.png" ) );
            immagine[ 4 ] = ImageIO.read( getClass().getResource( "../immagini/torreNera.png" ) );
            immagine[ 5 ] = ImageIO.read( getClass().getResource( "../immagini/cavalloNero.png" ) );
            immagine[ 6 ] = ImageIO.read( getClass().getResource( "../immagini/alfiereNero.png" ) );
            immagine[ 7 ] = ImageIO.read( getClass().getResource( "../immagini/reginaNera.png" ) );
            
            colore[0] = ImageIO.read( getClass().getResource( "../immagini/bianco.png" ) );
            colore[1] = ImageIO.read( getClass().getResource( "../immagini/nero.png" ) );
            colore[2] = ImageIO.read( getClass().getResource( "../immagini/scacchiera.png" ) ); // Semmai Decidiamo Di Implementare La Sccacchiera Come Immaagine
        
        } catch( IOException e ){}
        
        // Creo I JButton Del Pannello Della Scacchiera
        Insets margineBottoni = new Insets( 0, 0, 0, 0 ); // Per Avere Margini = 0
        bottone = new ImageButton[4];
        for ( int i = 0; i < 4; i++ ){
            
            bottone[i] = new ImageButton();
            bottone[i].setMargin( margineBottoni );

            // I Pezzi Sono Da 64x64 Pixel E Trasparenti, Valori Default Lunghezza Scacchiera
            ImageIcon immaginePezzo = new ImageIcon( new BufferedImage( 200, 200, BufferedImage.TYPE_INT_ARGB ) );
            bottone[i].setIcon( immaginePezzo );
            if(color instanceof Bianco)
                bottone[i].setImage( immagine[ i ] );
            else
                bottone[i].setImage( immagine[ i+4 ] );
            
            bottone[i].setOpaque( true );
            bottone[i].setContentAreaFilled( false );
            bottone[i].setBorderPainted( true );
            
            scacchiera.add(bottone[i]);
            
            bottone[i].addActionListener((ActionEvent e) -> {
                //come faccio a distinguere i vari ImageButton e dove sono salvati se all'interno del ciclo modifichi sempre "bottone"
                //devono essere all'interno di una struttura e non solo dentro la scacchiera altrimenti non si riesce a tornare indietro
                
                if(e.getSource().equals(bottone[0]))
                    if(color instanceof Bianco){
                        s.cambiaPezzo(new Torre(new Bianco()));
                        //prescelto=new Torre(new Bianco());
                    }
                    else{
                        s.toStringTipo();
                        s.cambiaPezzo(new Torre(new Nero()));
                        s.toStringTipo();
                        //prescelto=new Torre(new Nero());
                    }
                
                if(e.getSource().equals(bottone[1]))
                    if(color instanceof Bianco){
                        s.toStringTipo();
                        s.cambiaPezzo(new Cavallo(new Bianco()));
                        s.toStringTipo();
                        //prescelto=new Cavallo(new Bianco());
                    }
                    else{
                        s.toStringTipo();
                        s.cambiaPezzo(new Cavallo(new Nero()));
                        s.toStringTipo();
                        //prescelto=new Cavallo(new Nero());
                    }
                if(e.getSource().equals(bottone[2]))
                    if(color instanceof Bianco){
                        s.toStringTipo();
                        s.cambiaPezzo(new Alfiere(new Bianco()));
                        s.toStringTipo();
                        //prescelto=new Alfiere(new Bianco());
                    }
                    else{
                        s.toStringTipo();
                        s.cambiaPezzo(new Alfiere(new Nero()));
                        s.toStringTipo();
                        //prescelto=new Alfiere(new Nero());
                    }
                if(e.getSource().equals(bottone[3]))
                    if(color instanceof Bianco){
                        s.toStringTipo();
                        s.cambiaPezzo(new Regina(new Bianco()));
                        s.toStringTipo();
                        //prescelto=new Regina(new Bianco());
                    }
                    else{
                        s.toStringTipo();
                        s.cambiaPezzo(new Regina(new Nero()));
                        s.toStringTipo();
                        //prescelto=new Regina(new Nero());
                    }
                //uscita
                matrice[x][y].toStringTipo();
                ig.aggiornaBottoni(matrice);
                frame.dispose();
                gm.notify();
                //this.notifyAll();
            });
        
        } // Fine For Colonne
        
        panMain.add( scacchiera );
    
    }
     
     public void start() {
        
        Runnable run = () -> {
            frame.add( panMain );
            
            frame.setLocationByPlatform( true );
            frame.setResizable( false );
            frame.pack();
            
            frame.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
            frame.setVisible( true );
        };
        
        SwingUtilities.invokeLater(run);
        
    } // Fine main
}
