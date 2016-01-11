package view;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.imageio.*;
import model.*;
import controller.*;

/**
 * La classe InterfacciaGrafica gestisce tutti i pannelli e i bottoni che vengono usati duranti il gioco
 * 
 * L'interfaccia e composta da:
 *
 * - 4 JPannel:
 *  
 *  - JPanel interfacciaGrafica - Contiene tutti i JPanel sottostanti ed e il gestore del layout e posizionamento dei JPanel sottostanti:
 *      
 *      - JPanel pezziBianchiMangiati - JPanel contenente i pezzi bianchi che sono stati mangiati
 *      - JPanel scacchiera - JPanel contenente la scacchiera del gioco     
 *      - JPanel pezziNeriMangiati - JPanel contenente i pezzi neri che sono stati mangiati
 *  
 * JButton personalizzati ImageButton quadratiScacchiera - I quadrati 8 * 8 dei pulsanti della scacchiera
 *
 * 2 Image:
 *  
 *  - Image immagine[ 12 ] - Array di Image contentente le 12 immagini dei pezzi dei scacchi
 *  - Image colore [ 2 ] - Array di Image contenente 2 immagini che indica il colore bianco / nero della scacchiera
 *
 * Classe personalizzata MergeIcon mergeIco[ 24 ] - Unisce il colore della scacchiera con i pezzi degli scacchi
 *
 * 3 JLabel:
 *  
 *  - JLabel pezziMangiatiBianchi[ 16 ] - Etichette dove vengono disegnati i pezzi bianchi mangiati con un contatore associato
 *  - JLabel pezziMangiatiNeri[ 16 ] - Etichette dove vengono disegnati i pezzi neri mangiati con un contatore assaciato
 *  - JLabel messaggioInfo - Visualzzare il messaggio del turno corrente " Tocca Al Bianco / Nero "
 * 
 * String colonne - Stringa contenente le lettere delle colonne della scacchiera
 *
 * Border bordo - Bordo di colore rosso per vedere i bordi di ogni panello
 * 
 * GestoreMovimenti gm - Collegamento InterfacciaGrafica-GestoreMovimenti
 * 
 * GestoreTB gestoreTB - Collegamento InterfacciaGrafica-GestoreTB

* @author Viktor Teren VR379996, Michael Andronic VR370063, Gaetano Cavaler VR379845
*/
public class InterfacciaGrafica{
    
    /**
    * JPanel contenente tutti i JPanel
    */
    private JPanel interfacciaGrafica = new JPanel( new BorderLayout() );
    
    /**
     * JPanel contenente i pezzi bianchi che sono stati mangiati
    */
    private JPanel pezziBianchiMangiati = new JPanel( new GridLayout( 8, 2 ) );
    
    /**
     * JPanel contenente i pezzi nero che sono stati mangiati
    */
    private JPanel pezziNeriMangiati = new JPanel( new GridLayout( 8, 2 ) );
    
    /**
     * JPanel contenente la scacchiera del gioco
    */
    private JPanel scacchiera;
    
    /**
     * Contenente i quadrati 8 * 8 dei pulsanti della scacchiera con icone personalizzate
    */
    private ImageButton[][] quadratiScacchiera = new ImageButton[ 8 ][ 8 ];
    
    /**
     * Array di Image contentente le 12 immagini dei pezzi dei scacchi
    */
    private final Image immagine[] = new Image[ 12 ];
    
    /**
     * Array di Image contenente 2 immagini che indica il colore bianco / nero della scacchiera
    */
    private final Image colore[] = new Image[ 2 ];
    
    /**
     * Unisce il colore della scacchiera con i pezzi degli scacchi
    */
    private MergeIcon[] mergeIco = new MergeIcon[ 24 ];
    
    /**
     * Visualzzare il messaggio del turno corrente " Tocca Al Bianco / Nero "
    */
    private final JLabel messaggioInfo = new JLabel( " Premi Nuova Partita Per Iniziare !!!" );
    
    /**
     * Etichette dove vengono disegnati i pezzi bianchi mangiati
    */
    private JLabel[] pezziMangiatiBianchi = new JLabel[ 16 ];
    
    /**
     * Contatore associato ai pezzi bianchi mangiati 
    */
    private int contaMortiBianchi = 0;
    
    /**
     * Etichette dove vengono disegnati i pezzi neri mangiati
    */
    private JLabel[] pezziMangiatiNeri = new JLabel[ 16 ];
    
    /**
     * Contatore associato ai pezzi neri mangiati
    */
    private int contaMortiNeri = 0;
    
    /**
     * Stringa contenente le lettere delle colonne della scacchiera
    */
    private String colonne = "ABCDEFGH";
    
    /**
     * Bordo di colore rosso per vedere i bordi di ogni panello
    */
    private Border bordo = new CompoundBorder( new EmptyBorder( 5, 5, 5, 5 ), new LineBorder( Color.BLUE ) );
    
    /**
     * Collegamento InterfacciaGrafica-GestoreMovimenti
    */
    private GestoreMovimenti gestoreMovi;
    
    /**
     * Collegamento InterfacciaGrafica-GestoreTB
    */
    private GestoreTB gestoreTB;
    
    private final JPanel pannelloMain;
    private final GridBagConstraints posizione;
    
    /**
     * Costruttore che prende le immagini e controlla che siano preseenti tutte le immagni necessarie avvisando se c'è un errore.
     * 
     * Dopodichè disegna un schacchiera vuota. Verrà ridisegnata e riempita con le posizioni iniziali dei pezzi se
     * verra premuto il pulsante " Nuova Partita " attraverso il metodo iniziaPartita().
     * 
     * Aggiunge anche una barra in alto con il bottone di " Nuova Partita " e un indicatore di chi è il turno.
     * Infine diegna ai lati della scacchiera la tavola coi pezzi mangiati.
     *
    */
    InterfacciaGrafica(){ // Inizializza L'Interfaccia Grafica ( Costruttore )
        
        try{
            
            immagine[ 0 ] = ImageIO.read( this.getClass().getResource( "/immagini/pedoneBianco.png" ) );
            immagine[ 1 ] = ImageIO.read( this.getClass().getResource( "/immagini/torreBianca.png" ) );
            immagine[ 2 ] = ImageIO.read( this.getClass().getResource( "/immagini/cavalloBianco.png" ) );
            immagine[ 3 ] = ImageIO.read( this.getClass().getResource( "/immagini/alfiereBianco.png" ) );
            immagine[ 4 ] = ImageIO.read( this.getClass().getResource( "/immagini/reginaBianca.png" ) );
            immagine[ 5 ] = ImageIO.read( this.getClass().getResource( "/immagini/reBianco.png" ) );

            // Recupero le immagini pezzi neri
            immagine[ 6 ] = ImageIO.read( this.getClass().getResource( "/immagini/pedoneNero.png" ) );
            immagine[ 7 ] = ImageIO.read( this.getClass().getResource( "/immagini/torreNera.png" ) );
            immagine[ 8 ] = ImageIO.read( this.getClass().getResource( "/immagini/cavalloNero.png" ) );
            immagine[ 9 ] = ImageIO.read( this.getClass().getResource( "/immagini/alfiereNero.png" ) );
            immagine[ 10 ] = ImageIO.read( this.getClass().getResource( "/immagini/reginaNera.png" ) );
            immagine[ 11 ] = ImageIO.read( this.getClass().getResource( "/immagini/reNero.png" ) );
            
            colore[0] = ImageIO.read( this.getClass().getResource( "/immagini/bianco.png" ) );
            colore[1] = ImageIO.read( this.getClass().getResource( "/immagini/nero.png" ) );
        
        } catch( IOException e ){}
        
        mergeIco[ 0 ] = new MergeIcon( colore[ 0 ],immagine[ 0 ] ); // Pedone Bianco su sfondo Bianco
        mergeIco[ 1 ] = new MergeIcon( colore[ 1 ],immagine[ 0 ] ); // Pedone Bianco su sfondo Nero
        mergeIco[ 2 ] = new MergeIcon( colore[ 0 ],immagine[ 1 ] ); // Torre Bianca su sfondo Bianco
        mergeIco[ 3 ] = new MergeIcon( colore[ 1 ],immagine[ 1 ] ); // Torre Bianca su sfondo Nero
        mergeIco[ 4 ] = new MergeIcon( colore[ 0 ],immagine[ 2 ] ); // Cavallo Bianco su sfondo Bianco
        mergeIco[ 5 ] = new MergeIcon( colore[ 1 ],immagine[ 2 ] ); // Cavallo Bianco su sfondo Nero
        mergeIco[ 6 ] = new MergeIcon( colore[ 0 ],immagine[ 3 ] ); // Alfiere Bianco su sfondo Bianco
        mergeIco[ 7 ] = new MergeIcon( colore[ 1 ],immagine[ 3 ] ); // Alfiere Bianco su sfondo Nero
        mergeIco[ 8 ] = new MergeIcon( colore[ 0 ],immagine[ 4 ] ); // Regina Bianca su sfondo Bianco
        mergeIco[ 9 ] = new MergeIcon( colore[ 1 ],immagine[ 4 ] ); // Regina Bianca su sfondo Nero
        mergeIco[ 10 ] = new MergeIcon( colore[ 0 ],immagine[ 5 ] ); // Re Bianco su sfondo Bianco
        mergeIco[ 11 ] = new MergeIcon( colore[ 1 ],immagine[ 5 ] ); // Re Bianco su sfondo Nero
        mergeIco[ 12 ] = new MergeIcon( colore[ 0 ],immagine[ 6 ] ); // Pedone Nero su sfondo Bianco
        mergeIco[ 13 ] = new MergeIcon( colore[ 1 ],immagine[ 6 ] ); // Pedone Nero su sfondo Nero
        mergeIco[ 14 ] = new MergeIcon( colore[ 0 ],immagine[ 7 ] ); // Torre Ner su sfondo Bianco
        mergeIco[ 15 ] = new MergeIcon( colore[ 1 ],immagine[ 7 ] ); // Torre Nera su sfondo Nero
        mergeIco[ 16 ] = new MergeIcon( colore[ 0 ],immagine[ 8 ] ); // Cavallo Nero su sfondo Bianco
        mergeIco[ 17 ] = new MergeIcon( colore[ 1 ],immagine[ 8 ] ); // Cavallo Nero su sfondo Nero
        mergeIco[ 18 ] = new MergeIcon( colore[ 0 ],immagine[ 9 ] ); // Alfiere Nero su sfondo Bianco
        mergeIco[ 19 ] = new MergeIcon( colore[ 1 ],immagine[ 9 ] ); // Alfiere Nero su sfondo Nero
        mergeIco[ 20 ] = new MergeIcon( colore[ 0 ],immagine[ 10 ] ); // Regina Nera su sfondo Bianco
        mergeIco[ 21 ] = new MergeIcon( colore[ 1 ],immagine[ 10 ] ); // Regina Nera su sfondo Nero
        mergeIco[ 22 ] = new MergeIcon( colore[ 0 ],immagine[ 11 ] ); // Re Nero su sfondo Bianco
        mergeIco[ 23 ] = new MergeIcon( colore[ 1 ],immagine[ 11 ] ); // Re Nero su sfondo Nero
        
        
        scacchiera = new JPanel( new GridLayout( 10, 10 ) ){ // Scacchiera E I Bottoni Associati
            
            Dimension pref = new Dimension();
            
            @Override
            public final Dimension getPreferredSize(){
               
                Dimension dimensione = super.getPreferredSize();
                Dimension dimensioneCambiata;
                Component c = getParent();
                
                if ( c == null ){
                    
                    dimensioneCambiata = new Dimension( ( int )dimensione.getWidth(), ( int )dimensione.getHeight() );
                
                } else if ( c.getWidth() > dimensione.getWidth() && c.getHeight() > dimensione.getHeight() ){
                    
                    dimensioneCambiata = c.getSize();
                
                } else {
                    
                    dimensioneCambiata = dimensione;
                
                }
                
                int larghezza = ( int )dimensioneCambiata.getWidth();
                int altezza = ( int )dimensioneCambiata.getHeight();
                
                // Prendo La Piu Piccola Tra Altezza E Larghezza Per Ridimensionare
                int scelta = ( larghezza > altezza ? altezza : larghezza );
                pref.setSize( scelta, scelta );
                return new Dimension( scelta, scelta );
            
            }
        
        };
        
        // Creo I JButton Del Pannello Della Scacchiera
        Insets margineBottoni = new Insets( 0, 0, 0, 0 ); // Per Avere Margini = 0
        
        for (int i = 0; i < quadratiScacchiera.length; i++ ){
            
            for ( int j = 0; j < quadratiScacchiera[ i ].length; j++ ){
                
                ImageButton bottone = new ImageButton();
                bottone.setMargin( margineBottoni );
                
                // I Pezzi Sono Da 64x64 Pixel E Trasparenti, Valori Default Lunghezza Scacchiera
                ImageIcon immaginePezzo = new ImageIcon( new BufferedImage( 64, 64, BufferedImage.TYPE_INT_ARGB ) );
                bottone.setIcon( immaginePezzo );
                
                // Coloro Lo Sfondo Dei Quadrati Se Sono Pari O Dispari
                if ( ( j % 2 == 1 && i % 2 == 1 ) || ( j % 2 == 0 && i % 2 == 0 ) ){

                    bottone.setBackground( Color.WHITE );
                    bottone.setImage( colore[ 0 ] );
                
                } else {

                    bottone.setBackground( Color.BLACK );
                    bottone.setImage( colore[ 1 ] );
                
                } // Fine If Else Con I Quadrati Colorati
                
                bottone.setOpaque( true );
                bottone.setContentAreaFilled( false );
                bottone.setBorderPainted( true );
               
                quadratiScacchiera[ j ][ i ] = bottone;
            
            } // Fine For Colonne
        
        } // Fine For Righe

        // Disegno Le Lettere In Alto
        scacchiera.add( new JLabel() ); // Primo Spazio Vuoto Per La Riga Contenente Le Lettere In Alto
        
        for( int i = 0; i < 8; i++ ){
            
            scacchiera.add( new JLabel( colonne.substring( i, i + 1 ), SwingConstants.CENTER ) );
        
        } // Fine For
        
        scacchiera.add( new JLabel() ); // Ultimo Spazio Vuoto Per La Riga Contenente Le Lettere In Alto
        
        JPanel pulsanti = new JPanel( new GridLayout( 8, 8 ) );
        
        for( int i = 0; i < 8; i++ ){
            
            for ( int j = 0; j < 8; j++ ){
                
                switch ( j ){
                    
                    case 0: scacchiera.add( new JLabel( "" + ( 9 - ( i + 1 ) ), SwingConstants.CENTER ) );
                    default: scacchiera.add( quadratiScacchiera[ j ][ i ] );
                
                }
            
            } // Fine For Righe
            
            scacchiera.add( new JLabel( "" + ( 9 - ( i + 1 ) ), SwingConstants.CENTER ) );
            
        } // Fine For Colonne

        // Disegno Le Lettere In Basso
        scacchiera.add( new JLabel() ); // Primo Spazio Vuoto Per La Riga Contenente Le Lettere In Basso
        
        for( int i = 0; i < 8; i++ ){
            
            scacchiera.add( new JLabel( colonne.substring( i, i + 1 ), SwingConstants.CENTER ) );
        
        } // Fine For
        
        scacchiera.add( new JLabel() ); // Ultimo Spazio Vuoto Per La Riga Contenente Le Lettere In Basso
        
        JToolBar menu = new JToolBar();
        menu.setFloatable( false );
        
        Action iniziaNuovaPartita = new AbstractAction( "Nuova Partita" ){
            
            @Override
            public void actionPerformed( ActionEvent e ){ 
                
                iniziaPartita();
                setMessaggio( "Tocca Al Bianco !!!" );
            
            }
        
        };
        
        menu.add( iniziaNuovaPartita );
        menu.addSeparator();
        menu.add( messaggioInfo );
        interfacciaGrafica.add( menu, BorderLayout.PAGE_START );
        
        // Aggiungo I Componenti Al Pannello Main
        pannelloMain = new JPanel( new GridBagLayout() );
        posizione = new GridBagConstraints();
        
        for( int i = 0; i < 16; i++ ){
            
            pezziMangiatiBianchi[ i ] = new JLabel( new ImageIcon( new BufferedImage( 64, 64, BufferedImage.TYPE_INT_ARGB ) ) );
            pezziBianchiMangiati.add( pezziMangiatiBianchi[ i ] );
        
        }
        
        posizione.fill = GridBagConstraints.EAST;
        pezziBianchiMangiati.setBorder( bordo );
        pannelloMain.add(pezziBianchiMangiati, posizione );
        
        posizione.fill = GridBagConstraints.CENTER;
        scacchiera.setBorder( bordo );
        pannelloMain.add( scacchiera, posizione );
        
        for( int i = 0; i < 16; i++ ){
            
            pezziMangiatiNeri[ i ] = new JLabel( new ImageIcon( new BufferedImage( 64, 64, BufferedImage.TYPE_INT_ARGB ) ) );
            pezziNeriMangiati.add( pezziMangiatiNeri[ i ] );
        
        }
        
        posizione.fill = GridBagConstraints.WEST;
        pezziNeriMangiati.setBorder( bordo );
        pannelloMain.add(pezziNeriMangiati, posizione );
        
        interfacciaGrafica.add( pannelloMain, BorderLayout.CENTER );
    
    } // Fine InterfacciaGrafica

    /**
     * Metodo privato che inizializza le immagini e gli Action Listener per ogni bottone quando si inizia un nuova parita
    */
    private void iniziaPartita(){
        
        gestoreMovi = new GestoreMovimenti( );
        gestoreMovi.setInterfacciaGrafica( this ); // Collegamento InterfacciaGrafica-GestoreMovimenti
        gestoreTB = new GestoreTB( gestoreMovi, this ); // Collegamento InterfacciaGrafica-Gestore Turni E Bottoni
        
        aggiornaBottoni( gestoreMovi.getMatrice() );
        
        for( ImageButton[] quadratiScacchieraCiclo : quadratiScacchiera ) { // Aggiungo gli Acion Listener a tutti i bottoni
            
            for( int j = 0; j < quadratiScacchiera.length; j++ ){
                
                if( quadratiScacchieraCiclo[ j ].getActionListeners().length < 1 ){
                    
                    quadratiScacchieraCiclo[ j ].addActionListener( ( ActionEvent e ) -> {
                        
                        try{
                            
                            gestoreTB.pressionePulsanteScacchiera( e );
                        
                        } catch( Exception ex ){}
                    
                    });
                
                }
            
            }
        
        }
        
        pezziBianchiMangiati.removeAll(); // Azzerro il pannello dei pezzi bianchi mangiati dalla partita precedente
        
        contaMortiBianchi = 0;
        
        for( int i = 0; i < 16; i++ ){ // Azzero Panello Con Pezzi Morti Bianchi
            
            pezziMangiatiBianchi[ i ] = new JLabel( new ImageIcon( new BufferedImage( 64, 64, BufferedImage.TYPE_INT_ARGB ) ) );
            pezziBianchiMangiati.add( pezziMangiatiBianchi[ i ] );
        
        }
        
        pezziNeriMangiati.removeAll(); // Azzerro il pannello dei pezzi neri mangiati dalla partita precedente
        
        contaMortiNeri = 0;
        
        for( int i = 0; i < 16; i++ ){ // Azzero Panello Con Pezzi Morti Neri
            
            pezziMangiatiNeri[ i ] = new JLabel( new ImageIcon( new BufferedImage( 64, 64, BufferedImage.TYPE_INT_ARGB ) ) );
            pezziNeriMangiati.add( pezziMangiatiNeri[ i ] );
        
        }
        
        setMessaggio( "Tocca Al Bianco !!!" );
        
    } // Fine iniziaPartita
    
    /**
     * Metodo che ritorna l'oggetto di classe gestoreTB contenuta in questa classe
     * @return L'oggetto di classe gestoreTB contenuta in questa classe
    */
    public GestoreTB getGestoreTB(){
        
        return gestoreTB;
    
    }
    
    /**
     * Creazione, esecuzione e visualizzazione del JFrame principale del programma
    */
    public void start(){
        
        Runnable run = () -> {
            
            JFrame frame = new JFrame( "Progetto Scacchi Di Viktor Teren VR379996, Michael Andronic VR370063 E Gaetano Cavaler VR379845 " );
            frame.add( interfacciaGrafica );
            
            frame.setLocationByPlatform( true ); // Imposta il JFrame al centro dello schermo
            frame.setMinimumSize( frame.getMinimumSize() ); // Il JFrame non verra rimpiciolito dispetto ai suoi componenti
            
            frame.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE ); // Se il JFrame viene chiuso, chiudo il programma
            frame.setVisible( true );
        
        };
        
        SwingUtilities.invokeLater( run );
    
    }
    
    /**
     * Ritorna i bottoni della scacchiera organizzati su una matrice
     * @return I bottoni della scacchiera
    */
    public JButton[][] getMatriceBottoni(){
        
        return quadratiScacchiera;
    
    }

    /**
     * Metodo che, se chiamato, indica che ce stato un scacco matto
     * Una volta chiamato il metodo, esso visualizza un messaggio di scelta
    */
    public void finePartita(){
        
        int scelta = JOptionPane.showOptionDialog( interfacciaGrafica, "La Partita E Finita\nIl Vincitore E Il Colore " + gestoreTB.getTurno(), "Fine Partita", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new String[]{"Inizia Nuova Partita", "Esci Dal Programma"},"");
        
        if( scelta == 0 ){
            
            iniziaPartita();
            setMessaggio( "Tocca Al Bianco !!!" );
        
        } else {
            
            System.exit( 0 );
        
        }
    
    }
    
    /**
     * Aggiorno il messaggio contenente sulla JLabel messaggio
     * @param s Il messaggio da scrivere
    */
    public void setMessaggio( String s ){
        
        messaggioInfo.setText( s );
    
    }
    
    /**
     * Quando viene mangiato un pezzo viene aggiunto ai JPanel laterali tramite questa funzione.
     * In base al colore del pezzo e lo aggiunge al JPanel corrispondente
     * @param p  - Il pezzo che e stato mangiato
    */
    public void aggiungiPezzoMorto( Pezzo p ){
        
        int valore = 0;
        
        if( p.getColore() instanceof Bianco ){
            
            if( p instanceof Pedone ){
                
                valore = 0;
            
            } else if( p instanceof Torre ){
                
                valore = 1;
            
            } else if( p instanceof Cavallo ){
                
                valore = 2;
            
            } else if( p instanceof Alfiere ){
                
                valore = 3;
            
            } else if( p instanceof Regina ){
                
                valore = 4;
            
            } else if( p instanceof Re ){
                
                valore = 5;
            
            }
            
            pezziMangiatiBianchi[ contaMortiBianchi ].setIcon( new ImageIcon( immagine[ valore ].getScaledInstance( 64, 64, Image.SCALE_FAST ) ) );
            contaMortiBianchi++;
        
        } else {
            
            if( p instanceof Pedone ){
                
                valore = 6;
            
            } else if( p instanceof Torre ){
                
                valore = 7;
            
            } else if( p instanceof Cavallo ){
                
                valore = 8;
            
            } else if( p instanceof Alfiere ){
                
                valore = 9;
            
            } else if( p instanceof Regina ){
                
                valore = 10;
            
            } else if( p instanceof Re ){
                
                valore = 11;
            
            }
            
            pezziMangiatiNeri[ contaMortiNeri ].setIcon( new ImageIcon( immagine[valore].getScaledInstance( 64, 64, Image.SCALE_FAST ) ) );
            contaMortiNeri++;
        
        }
    
    }
    
    /**
     * Questo metodo viene chiamato quando un pedone viene promosso
     * Permette tramite un'interfaccia di scegliere in cosa promuoverlo.
     * @param s - Lo spazio dove il pedone e arrivato per promuoverlo
     * @param colore - Il colore del pedone da promuovere
     * @param matrice - La matrice della scacchiera completa
    */
    public void promozionePedone( Spazio s, Colore colore, Spazio[][] matrice ){
        
        int scelta = JOptionPane.showOptionDialog( null, "Scegli In Cosa Vuoi Promuovere Il Pedone", "Promozione Pedone", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new String[]{ "Torre", "Cavallo", "Alfiere", "Regina" }, "" );
        
        switch( scelta ){
            
            case 0: s.cambiaPezzo( new Torre( colore ) ); break;
            case 1: s.cambiaPezzo( new Cavallo( colore ) ); break;
            case 2: s.cambiaPezzo( new Alfiere( colore ) ); break;
            case 3: s.cambiaPezzo( new Regina( colore ) ); break;
        
        }
    
    }

    /**
     * Metodo che viene chiamato dopo ogni mossa.
     * Verifica l'integrita della mossa e aggiorna la visuale.
     * @param matrice - La matrice aggiornata contenente le posizioni per aggiornare la visuale
    */
    public void aggiornaBottoni( Spazio[][] matrice ){
        
        Pezzo p;
        
        for( int i = 0; i < 8; i++ ){
            
            for( int j = 0; j < 8; j++ ){
                
                if( matrice[ i ][ j ].eOccupato() ){
                    
                    p  = matrice[ i ][ j ].getOccupante();
                    
                    if( p instanceof Pedone ){
                        
                        if( p.getColore() instanceof Bianco ){
                            
                            if( quadratiScacchiera[ i ][ j ].getBackground() == Color.WHITE ){
                                
                                quadratiScacchiera[ i ][ j ].setImage( MergeIcon.iconToImage( mergeIco[ 0 ] ) );
                            
                            } else {
                                
                                quadratiScacchiera[ i ][ j ].setImage( MergeIcon.iconToImage( mergeIco[ 1 ] ) );
                            
                            }
                        
                        } else { // Il Colore E Nero
                            
                            if( quadratiScacchiera[ i ][ j ].getBackground() == Color.WHITE ){
                                
                                quadratiScacchiera[ i ][ j ].setImage( MergeIcon.iconToImage( mergeIco[ 12 ] ) );
                            
                            } else {
                                
                                quadratiScacchiera[ i ][ j ].setImage( MergeIcon.iconToImage( mergeIco[ 13 ] ) );
                            
                            }
                        
                        }
                    
                    }
                    
                    if( p instanceof Torre ){
                        
                        if( p.getColore() instanceof Bianco ){
                            
                            if( quadratiScacchiera[ i ][ j ].getBackground() == Color.WHITE ){
                                
                                quadratiScacchiera[ i ][ j ].setImage( MergeIcon.iconToImage( mergeIco[ 2 ] ) );
                            
                            } else {
                                
                                quadratiScacchiera[ i ][ j ].setImage( MergeIcon.iconToImage( mergeIco[ 3 ] ) );
                            
                            }
                        
                        } else { // Il Colore E Nero
                            
                            if( quadratiScacchiera[ i ][ j ].getBackground() == Color.WHITE ){
                                
                                quadratiScacchiera[ i ][ j ].setImage( MergeIcon.iconToImage( mergeIco[ 14 ] ) );
                            
                            } else {
                                
                                quadratiScacchiera[ i ][ j ].setImage( MergeIcon.iconToImage( mergeIco[ 15 ] ) );
                            
                            }
                        
                        }
                    
                    }
                    
                    if( p instanceof Cavallo ){
                        
                        if( p.getColore() instanceof Bianco ){
                            
                            if( quadratiScacchiera[ i ][ j ].getBackground() == Color.WHITE ){
                                
                                quadratiScacchiera[ i ][ j ].setImage( MergeIcon.iconToImage( mergeIco[ 4 ] ) );
                            
                            } else {
                                
                                quadratiScacchiera[ i ][ j ].setImage( MergeIcon.iconToImage( mergeIco[ 5 ] ) );
                            
                            }
                        
                        } else { // Il colore e nero
                            
                            if( quadratiScacchiera[ i ][ j ].getBackground() == Color.WHITE ){
                                
                                quadratiScacchiera[ i ][ j ].setImage( MergeIcon.iconToImage( mergeIco[ 16 ] ) );
                            
                            } else {
                                
                                quadratiScacchiera[ i ][ j ].setImage( MergeIcon.iconToImage( mergeIco[ 17 ] ) );
                            
                            }
                        
                        }
                    
                    }
                    
                    if( p instanceof Alfiere ){
                        
                        if( p.getColore() instanceof Bianco ){
                            
                            if( quadratiScacchiera[ i ][ j ].getBackground() == Color.WHITE ){
                                
                                quadratiScacchiera[ i ][ j ].setImage( MergeIcon.iconToImage( mergeIco[ 6 ] ) );
                            
                            } else {
                                
                                quadratiScacchiera[ i ][ j ].setImage( MergeIcon.iconToImage( mergeIco[ 7 ] ) );
                            
                            }
                        
                        } else { // Il Colore E Nero
                            
                            if( quadratiScacchiera[ i ][ j ].getBackground() == Color.WHITE ){
                                
                                quadratiScacchiera[ i ][ j ].setImage( MergeIcon.iconToImage( mergeIco[ 18 ] ) );
                            
                            } else {
                                
                                quadratiScacchiera[ i ][ j ].setImage( MergeIcon.iconToImage( mergeIco[ 19 ] ) );
                            
                            }
                        }
                    }
                    
                    if( p instanceof Regina ){
                        
                        if( p.getColore() instanceof Bianco ){
                            
                            if( quadratiScacchiera[ i ][ j ].getBackground() == Color.WHITE ){
                                
                                quadratiScacchiera[ i ][ j ].setImage( MergeIcon.iconToImage( mergeIco[ 8 ] ) );
                            
                            } else {
                                
                                quadratiScacchiera[ i ][ j ].setImage( MergeIcon.iconToImage( mergeIco[ 9 ] ) );
                            
                            }
                        
                        } else { // Il colore e nero
                            
                            if( quadratiScacchiera[ i ][ j ].getBackground() == Color.WHITE ){
                                
                                quadratiScacchiera[ i ][ j ].setImage( MergeIcon.iconToImage( mergeIco[ 20 ] ) );
                            
                            } else {
                                
                                quadratiScacchiera[ i ][ j ].setImage( MergeIcon.iconToImage( mergeIco[ 21 ] ) );
                            
                            }
                        
                        }
                    
                    }
                    
                    if( p instanceof Re ){
                        
                        if( p.getColore() instanceof Bianco ){
                            
                            if( quadratiScacchiera[ i ][ j ].getBackground() == Color.WHITE ){
                                
                                quadratiScacchiera[ i ][ j ].setImage( MergeIcon.iconToImage( mergeIco[ 10 ] ) );
                            
                            } else {
                                
                                quadratiScacchiera[ i ][ j ].setImage( MergeIcon.iconToImage( mergeIco[ 11 ] ) );
                            
                            }
                        
                        } else { // Il colore e nero
                            
                            if( quadratiScacchiera[ i ][ j ].getBackground() == Color.WHITE ){
                                
                                quadratiScacchiera[ i ][ j ].setImage( MergeIcon.iconToImage( mergeIco[ 22 ] ) );
                            
                            } else {
                                
                                quadratiScacchiera[ i ][ j ].setImage( MergeIcon.iconToImage( mergeIco[ 23 ] ) );
                            
                            }
                        
                        }
                    
                    }
                
                } else { // Posizione Non Occupata
                    
                    if( quadratiScacchiera[ i ][ j ].getBackground() == Color.WHITE ){
                        
                        quadratiScacchiera[ i ][ j ].setImage( colore[ 0 ] );
                    
                    } else {
                        
                        quadratiScacchiera[ i ][ j ].setImage( colore[ 1 ] );
                    
                    }
                
                }
            
            }
        
        }
    
    }

} // Fine Classe InterfacciaGrafica