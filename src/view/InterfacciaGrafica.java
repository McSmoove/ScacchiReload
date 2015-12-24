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
 * La classe InterfacciaGrafica gestisce tutti i pannelli e i bottoni che 
 * vengono usati duranti il gioco. L'interfaccia si compone di:
 * - 3 pannelli
 *  - uno per tutta l'interfaccia
 *  - uno per i pezzi bianchi e uno per quelli neri
 *  - un pannello per la schacchiera
 * - 3 Image
 *  - immagini dei pezzi (array da 12)
 *  - colori (array da 3)
 * - 2 jlabel per i pezzi mangiati
 * - 1 jlabel per visualzzare il messaggio: "tocca al bianco/nero"
 * 
 */
public class InterfacciaGrafica{
    
    private final int BIANCO = 0;
    private final int NERO = 1;
    
    private final int PEDONE_BIANCO = 0;
    private final int TORRE_BIANCA = 1;
    private final int CAVALLO_BIANCO = 2;
    private final int ALFIERE_BIANCO = 3;
    private final int REGINA_BIANCA = 4;
    private final int RE_BIANCO = 5;
    private final int PEDONE_NERO = 6;
    private final int TORRE_NERA = 7;
    private final int CAVALLO_NERO = 8;
    private final int ALFIERE_NERO = 9;
    private final int REGINA_NERA = 10;
    private final int RE_NERO = 11;
        
    private JPanel interfacciaGrafica = new JPanel( new BorderLayout() );
    private JPanel pezziBianchi = new JPanel( new GridLayout( 8, 2 ) );
    private JPanel pezziNeri = new JPanel( new GridLayout( 8, 2 ) );
    private JPanel scacchiera;
    
    private ImageButton[][] quadratiScacchiera = new ImageButton[ 8 ][ 8 ];
    
    private final Image immagine[] = new Image[ 12 ];
    private final Image colore[] = new Image[ 2 ];
    private MergeIcon[] mergeIco = new MergeIcon[ 24 ];
    
    private final JLabel messaggioInfo = new JLabel( "Tocca Al Bianco / Nero" );
    
    private JLabel[] pezziMangiatiBianchi = new JLabel[ 16 ];
    private int contaMortiBianchi = 0;
    
    private JLabel[] pezziMangiatiNeri = new JLabel[ 16 ];
    private int contaMortiNeri = 0;
    
    private static final String colonne = "ABCDEFGH";
    
    private Border bordo = new CompoundBorder( new EmptyBorder( 5, 5, 5, 5 ), new LineBorder( Color.RED ) );
    
    private GestoreMovimenti gm;
    private GestoreTB gestoreTB;
    
    /**
     * Costruttore che prende le immagini dal package "immagini" e le associa a
     * una zona di memoria dell'array "immagine" di tipo Image. Da notare che 
     * prima controlla che siano preseenti tutte le immagni necessarie e avvisa
     * se c'è un errore.
     * Dopodichè disegna un schacchiera VUOTA. Verrà ridisegnata e riempita con 
     * le posizioni iniziali dei pezzi se verrà premuto il pulsante "nuova 
     * partita" attraverso il metodo iniziaPartita
     * Aggiunge anche una barra in alto con il bottone di "nuova partita" e un
     * indicatore di chi è il turno.
     * Infine diegna ai lati della scacchiera la tavola coi pezzi mangiati
     */
    InterfacciaGrafica(){ // Inizializza Interfaccia Grafica ( Costruttore )
        
        try{
            
            immagine[ 0 ] = ImageIO.read( getClass().getResource( "../immagini/pedoneBianco.png" ) );
            immagine[ 1 ] = ImageIO.read( getClass().getResource( "../immagini/torreBianca.png" ) );
            immagine[ 2 ] = ImageIO.read( getClass().getResource( "../immagini/cavalloBianco.png" ) );
            immagine[ 3 ] = ImageIO.read( getClass().getResource( "../immagini/alfiereBianco.png" ) );
            immagine[ 4 ] = ImageIO.read( getClass().getResource( "../immagini/reginaBianca.png" ) );
            immagine[ 5 ] = ImageIO.read( getClass().getResource( "../immagini/reBianco.png" ) );

            // Recupero le immagini pezzi neri
            immagine[ 6 ] = ImageIO.read( getClass().getResource( "../immagini/pedoneNero.png" ) );
            immagine[ 7 ] = ImageIO.read( getClass().getResource( "../immagini/torreNera.png" ) );
            immagine[ 8 ] = ImageIO.read( getClass().getResource( "../immagini/cavalloNero.png" ) );
            immagine[ 9 ] = ImageIO.read( getClass().getResource( "../immagini/alfiereNero.png" ) );
            immagine[ 10 ] = ImageIO.read( getClass().getResource( "../immagini/reginaNera.png" ) );
            immagine[ 11 ] = ImageIO.read( getClass().getResource( "../immagini/reNero.png" ) );
            
            colore[0] = ImageIO.read( getClass().getResource( "../immagini/bianco.png" ) );
            colore[1] = ImageIO.read( getClass().getResource( "../immagini/nero.png" ) );
        
        } catch( IOException e ){}
        
        mergeIco[0] = new MergeIcon( colore[0],immagine[PEDONE_BIANCO] );
        mergeIco[1] = new MergeIcon( colore[1],immagine[PEDONE_BIANCO] );
        mergeIco[2] = new MergeIcon( colore[0],immagine[PEDONE_NERO] );
        mergeIco[3] = new MergeIcon( colore[1],immagine[PEDONE_NERO] );
        mergeIco[4] = new MergeIcon( colore[0],immagine[TORRE_BIANCA] );
        mergeIco[5] = new MergeIcon( colore[1],immagine[TORRE_BIANCA] );
        mergeIco[6] = new MergeIcon( colore[0],immagine[TORRE_NERA] );
        mergeIco[7] = new MergeIcon( colore[1],immagine[TORRE_NERA] );
        mergeIco[8] = new MergeIcon( colore[0],immagine[CAVALLO_BIANCO] );
        mergeIco[9] = new MergeIcon( colore[1],immagine[CAVALLO_BIANCO] );
        mergeIco[10] = new MergeIcon( colore[0],immagine[CAVALLO_NERO] );
        mergeIco[11] = new MergeIcon( colore[1],immagine[CAVALLO_NERO] );
        mergeIco[12] = new MergeIcon( colore[0],immagine[ALFIERE_BIANCO] );
        mergeIco[13] = new MergeIcon( colore[1],immagine[ALFIERE_BIANCO] );
        mergeIco[14] = new MergeIcon( colore[0],immagine[ALFIERE_NERO] );
        mergeIco[15] = new MergeIcon( colore[1],immagine[ALFIERE_NERO] );
        mergeIco[16] = new MergeIcon( colore[0],immagine[REGINA_BIANCA] );
        mergeIco[17] = new MergeIcon( colore[1],immagine[REGINA_BIANCA] );
        mergeIco[18] = new MergeIcon( colore[0],immagine[REGINA_NERA] );
        mergeIco[19] = new MergeIcon( colore[1],immagine[REGINA_NERA] );
        mergeIco[20] = new MergeIcon( colore[0],immagine[RE_BIANCO] );
        mergeIco[21] = new MergeIcon( colore[1],immagine[RE_BIANCO] );
        mergeIco[22] = new MergeIcon( colore[0],immagine[RE_NERO] );
        mergeIco[23] = new MergeIcon( colore[1],immagine[RE_NERO] );
        
        // Scacchiera E I Bottoni Associati
        scacchiera = new JPanel( new GridLayout( 10, 10 ) ){
            
            Dimension pref = new Dimension();
            Image back = null; // Per Lo Sfondo Se Decidiamo Implementarlo
            
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
            
            } // Fine getPrefferedSize
        
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

                    bottone.setBackground( Color.WHITE ); // Utile A Controlli Futuri, Lo Sfondo Ce Ma Mascherato Dall'Immagine
                    bottone.setImage( colore[ BIANCO ] );
                
                } else {

                    bottone.setBackground( Color.BLACK ); // Utile A Controlli Futuri, Lo Sfondo Ce Ma Mascherato Dall'Immagine
                    bottone.setImage( colore[ NERO ] );
                
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
            
            }
        
        };
        
        menu.add( iniziaNuovaPartita );
        menu.addSeparator();
        menu.add( messaggioInfo );
        interfacciaGrafica.add( menu, BorderLayout.PAGE_START );
        
        // Aggiungo I Componenti Al Pannello Main
        JPanel pannelloMain = new JPanel( new GridBagLayout() );
        GridBagConstraints posizione = new GridBagConstraints();
        
        for( int i = 0; i < 16; i++ ){
            
            pezziMangiatiBianchi[ i ] = new JLabel( new ImageIcon( new BufferedImage( 64, 64, BufferedImage.TYPE_INT_ARGB ) ) );
            pezziBianchi.add( pezziMangiatiBianchi[ i ] );
        
        }
        
        posizione.fill = GridBagConstraints.EAST;
        pezziBianchi.setBorder( bordo );
        pannelloMain.add( pezziBianchi, posizione );
        
        posizione.fill = GridBagConstraints.CENTER;
        scacchiera.setBorder( bordo );
        pannelloMain.add( scacchiera, posizione );
        
        for( int i = 0; i < 16; i++ ){
            
            pezziMangiatiNeri[ i ] = new JLabel( new ImageIcon( new BufferedImage( 64, 64, BufferedImage.TYPE_INT_ARGB ) ) );
            pezziNeri.add( pezziMangiatiNeri[ i ] );
        
        }
        
        posizione.fill = GridBagConstraints.WEST;
        pezziNeri.setBorder( bordo );
        pannelloMain.add( pezziNeri, posizione );
        
        interfacciaGrafica.add( pannelloMain, BorderLayout.CENTER );
        interfacciaGrafica.setBorder( bordo );
    
    } // Fine InterfacciaGrafica

    /**
     * Metodo privato che inizializza le immagini quando si inizia un nuova 
     * parita. Si aggiunge un ActionListener per ogni casella della scacchiera.
     */
    private void iniziaPartita(){
        
        gm = new GestoreMovimenti( ); // Collegamento Interfaccia-Gestore
        gm.setInterfacciaGrafica( this ); // Collegamento Gestore-Interfaccia
        gestoreTB=new GestoreTB(gm, this);
        
        // Da Modificare Il Testo In Base Al Turno
        messaggioInfo.setText( "Fai Una Mossa !!!");
        gestoreTB.disegnaMatriceSpaziOccupati(); 
        if(gestoreTB.getTurno() instanceof Bianco)
            System.err.println("Bianco");
        else
            System.err.println("Nero");
        // Qui Si Metteranno I Pezzi Collegandoli Alle Immagini
        aggiornaBottoni(gm.getMatrice());
        
        
        //aggiungo i listener a tutti i bottoni
        for (ImageButton[] quadratiScacchiera1 : quadratiScacchiera) {
            for (int j = 0; j<quadratiScacchiera.length; j++) {
                if(quadratiScacchiera1[j].getActionListeners().length<1){
                quadratiScacchiera1[j].addActionListener((ActionEvent e) -> {
                    try {
                        gestoreTB.pressionePulsanteScacchiera( e );
                    } catch (Exception ex) {}
                });   
                }
            }
        }
    
    } // Fine iniziaPartita
    
    /**
     * ritorna l'oggetto di classe gestoreTB contenuta in questa classe
     * @return Un oggetto di classe gestoreTB
     */
    public GestoreTB getGestoreTB(){
        return gestoreTB;
    }
    
    /**
     * Inizio del programma
     */
    public void start() {
        
        Runnable run = () -> {
            JFrame frame = new JFrame( "Scacchi Beta !!!" );
            frame.add( interfacciaGrafica );
            
            frame.setLocationByPlatform( true );
            System.out.println(""+ pezziNeri.getMinimumSize());
            frame.setMinimumSize( frame.getMinimumSize() );
            
            frame.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
            frame.setVisible( true );
        };
        
        SwingUtilities.invokeLater(run);
    
    } // Fine main
    
    /**
     * Ritorna i bottoni della scacchiera organizzati su una matrice
     * @return Una matrice quadrata di classe JButton
     */
    public JButton[][] getMatriceBottoni(){
        
        return quadratiScacchiera;
    
    }

    /**
     * aggiorna l'interfaccia quando finisce la partita, segnando che è finita
     */
    public void finePartita(){
        
        int scelta = JOptionPane.showOptionDialog( interfacciaGrafica, "La Partita è Finita\nIl Vincitore è Il Colore " + gestoreTB.getTurno(), "Fine Partita", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new String[]{"Inizia Nuova Partita", "Esci Dal Programma"},"");
        
        if( scelta == 0 ){
            
            iniziaPartita();
        
        } else {
            
            System.exit( 0 );
        
        }
    
    }
    
    /**
     * aggiorna il messaggio contenuto sulla barra in alto (vicino al pulsante nuovaPartita)
     * @param s il messaggio da scrivere
     */
    public void setMessaggio(String s){
        messaggioInfo.setText(s);
    }
    
    /**
     * Ogni volta che viene mangiato un pezzo viene aggiunto ai box laterali
     * tramite questa funzione. Analizza quale pezzo è stato mangiato e lo
     * aggiunge al box.
     * @param p 
     */
    public void aggiungiPezzoMorto( Pezzo p ){
        
        int valore = 0;
        
        if( p.getColore() instanceof Bianco ){
            if(p instanceof Pedone)
                valore=PEDONE_BIANCO;
            else if(p instanceof Torre)
                    valore=TORRE_BIANCA;
            else if(p instanceof Alfiere)
                    valore=ALFIERE_BIANCO;
            else if(p instanceof Cavallo)
                    valore=CAVALLO_BIANCO;
            else if(p instanceof Re)
                    valore=RE_BIANCO;
            else if(p instanceof Regina)
                    valore=REGINA_BIANCA;
                       pezziMangiatiBianchi[ contaMortiBianchi ].setIcon( new ImageIcon( immagine[valore].getScaledInstance( 64, 64, Image.SCALE_FAST ) ) );
                       contaMortiBianchi++;
        }
        else{
            if(p instanceof Pedone)
                valore=PEDONE_NERO;
            else if(p instanceof Torre)
                    valore=TORRE_NERA;
            else if(p instanceof Alfiere)
                    valore=ALFIERE_NERO;
            else if(p instanceof Cavallo)
                    valore=CAVALLO_NERO;
            else if(p instanceof Re)
                    valore=RE_NERO;
            else if(p instanceof Regina)
                    valore=REGINA_NERA; 
            pezziMangiatiNeri[ contaMortiNeri ].setIcon( new ImageIcon( immagine[valore].getScaledInstance( 64, 64, Image.SCALE_FAST ) ) );
            contaMortiNeri++;
        
        }
             
    }
    
    /**
     * ridisegna il pedone (permete di scegliere quale scacco) quando viene promosso
     * per facilitare 
     * @param s lo spazio dove il pedone è stato promosso
     * @param colore il colore del pedone promosso
     * @param matrice la matrice della scacchiera
     */
    public void promozionePedone(Spazio s,Colore colore,Spazio[][] matrice){
        
        int scelta = JOptionPane.showOptionDialog(null, "Scegli In Cosa Vuoi Promuovere Il Pedone", "Promozione Pedone", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new String[]{"Torre","Cavallo","Alfiere","Regina"},"");
        switch(scelta){
            case 0: s.cambiaPezzo(new Torre(colore));break;
            case 1: s.cambiaPezzo(new Cavallo(colore));break;
            case 2: s.cambiaPezzo(new Alfiere(colore));break;
            case 3: s.cambiaPezzo(new Regina(colore));break;
        }
    }

    /**
     * Dato che ogni casella usa una texture bianca/nera ogni volta che si 
     * sposta un pezzo si deve sostituire con una mergeIcon. Per inciso, 
     * controlla tutte le posizioni, controlla quale pezzo è presente su quella 
     * posizione e sostituisce il mergeIcon 
     * del pezzo sulla texture della scacchiera. Deve anche 
     * aggiornare le posizioni vuote per eliminare immagini obsolete.
     * @param matrice 
     */
    public void aggiornaBottoni(Spazio[][] matrice) {
        
        Pezzo p;
        
        for( int i = 0; i < 8; i++ ){
            
            for( int j = 0; j < 8; j++ ){
                if( matrice[ i ][ j ].eOccupato() ){
                    p  = matrice[ i ][ j ].getOccupante();
                    // Pensavo Di Utilizzare Uno Switch
                    if( p instanceof Pedone ){       
                        if( p.getColore() instanceof Bianco ){
                            if( quadratiScacchiera[ i ][ j ].getBackground() == Color.WHITE ){
                                quadratiScacchiera[ i ][ j ].setImage( MergeIcon.iconToImage( mergeIco[0] ) );
                            } else {
                                quadratiScacchiera[ i ][ j ].setImage( MergeIcon.iconToImage( mergeIco[1] ) );
                            }
                        } else {
                            if( quadratiScacchiera[ i ][ j ].getBackground() == Color.WHITE ){
                                quadratiScacchiera[ i ][ j ].setImage( MergeIcon.iconToImage( mergeIco[2] ) );
                            } else {
                                quadratiScacchiera[ i ][ j ].setImage( MergeIcon.iconToImage( mergeIco[3] ) );
                            }
                        }
                    }
                    
                    if( p instanceof Alfiere ){ 
                        if( p.getColore() instanceof Bianco ){
                            if( quadratiScacchiera[ i ][ j ].getBackground() == Color.WHITE ){
                                quadratiScacchiera[ i ][ j ].setImage( MergeIcon.iconToImage( mergeIco[12] ) );
                            } else {
                                quadratiScacchiera[ i ][ j ].setImage( MergeIcon.iconToImage( mergeIco[13] ) );
                            }
                        } else {
                            if( quadratiScacchiera[ i ][ j ].getBackground() == Color.WHITE ){
                                quadratiScacchiera[ i ][ j ].setImage( MergeIcon.iconToImage( mergeIco[14] ) );
                            } else {
                                quadratiScacchiera[ i ][ j ].setImage( MergeIcon.iconToImage( mergeIco[15] ) );
                            }
                        }
                    }
                    
                    if( p instanceof Torre ){
                        if( p.getColore() instanceof Bianco ){
                            if( quadratiScacchiera[ i ][ j ].getBackground() == Color.WHITE ){
                                quadratiScacchiera[ i ][ j ].setImage( MergeIcon.iconToImage( mergeIco[4] ) );
                            } else {
                                quadratiScacchiera[ i ][ j ].setImage( MergeIcon.iconToImage( mergeIco[5] ) );
                            }
                        } else {
                            if( quadratiScacchiera[ i ][ j ].getBackground() == Color.WHITE ){
                                quadratiScacchiera[ i ][ j ].setImage( MergeIcon.iconToImage( mergeIco[6] ) );
                            } else {
                                quadratiScacchiera[ i ][ j ].setImage( MergeIcon.iconToImage( mergeIco[7] ) );
                            }
                        }
                    }
                    
                    if( p instanceof Cavallo ){                 
                        if( p.getColore() instanceof Bianco ){
                            if( quadratiScacchiera[ i ][ j ].getBackground() == Color.WHITE ){
                                quadratiScacchiera[ i ][ j ].setImage( MergeIcon.iconToImage( mergeIco[8] ) );
                            } else {
                                quadratiScacchiera[ i ][ j ].setImage( MergeIcon.iconToImage( mergeIco[9] ) );
                            }
                        } else {
                            if( quadratiScacchiera[ i ][ j ].getBackground() == Color.WHITE ){
                                    quadratiScacchiera[ i ][ j ].setImage( MergeIcon.iconToImage( mergeIco[10] ) );
                            } else {
                                quadratiScacchiera[ i ][ j ].setImage( MergeIcon.iconToImage( mergeIco[11] ) );
                            }
                        }
                    }
                    
                    if( p instanceof Regina ){
                        if( p.getColore() instanceof Bianco ){
                            if( quadratiScacchiera[ i ][ j ].getBackground() == Color.WHITE ){
                                quadratiScacchiera[ i ][ j ].setImage( MergeIcon.iconToImage( mergeIco[16] ) );
                            } else {
                                quadratiScacchiera[ i ][ j ].setImage( MergeIcon.iconToImage( mergeIco[17] ) );
                            }
                        } else {
                            if( quadratiScacchiera[ i ][ j ].getBackground() == Color.WHITE ){
                                quadratiScacchiera[ i ][ j ].setImage( MergeIcon.iconToImage( mergeIco[18] ) );
                            } else {
                                quadratiScacchiera[ i ][ j ].setImage( MergeIcon.iconToImage( mergeIco[19] ) );
                            }
                        }
                    }
                    
                    if( p instanceof Re ){
                        if( p.getColore() instanceof Bianco ){ 
                            if( quadratiScacchiera[ i ][ j ].getBackground() == Color.WHITE ){
                                quadratiScacchiera[ i ][ j ].setImage( MergeIcon.iconToImage( mergeIco[20] ) );
                            } else {
                                quadratiScacchiera[ i ][ j ].setImage( MergeIcon.iconToImage( mergeIco[21] ) );
                            }
                        } else {
                            if( quadratiScacchiera[ i ][ j ].getBackground() == Color.WHITE ){
                                quadratiScacchiera[ i ][ j ].setImage( MergeIcon.iconToImage( mergeIco[22] ) );
                            } else {
                                quadratiScacchiera[ i ][ j ].setImage( MergeIcon.iconToImage( mergeIco[23] ) );
                            }
                        }
                    }
                }

                // Posizione Vuota ( Bisogna Aggiornare Per Eliminare Texture Obsolete )
                else{
                    if( quadratiScacchiera[ i ][ j ].getBackground() == Color.WHITE ){
                                quadratiScacchiera[ i ][ j ].setImage( colore[0] );
                            } else {
                                quadratiScacchiera[ i ][ j ].setImage( colore[1] );
                            }
                }
            }
        }
        
        
    }
    
} // Fine Classe InterfacciaGrafica