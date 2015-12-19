package controller;

import static java.lang.Math.abs;
import java.util.LinkedList;
import model.*;
import view.InterfacciaGrafica;


/**
 * La classe contiene i metodi che calcolano le posizioni e i movimenti delle 
 * varie pedine sulla scacchiera. Contiene anche il calcolo dello scacco matto.
 * @author Viktor, Michael, Gaetano
*/
public class GestoreMovimenti{
    
    private final Colore turno;
    private Spazio[][] m;
    private final int MAXLENGTH = 7;
    private InterfacciaGrafica ig;
    //private Re reBianco;
    //private Re reNero;
    Spazio reBianco,reNero;
    //int xReBianco,xReNero,yReBianco,yReNero;
    
    /**
     * costruttore di base che inizializza la scacchiera
     */
    public GestoreMovimenti(){
        
        // Creare Una Matrice Con Le Posizioni Di Default
        m = new Spazio[ 8 ][ 8 ];
        turno = new Bianco();
        m[ 0 ][ 0 ] = new Spazio( 0, 0, new Torre( new Nero() ) );
        m[ 7 ][ 0 ] = new Spazio( 7, 0, new Torre( new Nero() ) );
        m[ 1 ][ 0 ] = new Spazio( 1, 0, new Cavallo( new Nero() ) );
        m[ 6 ][ 0 ] = new Spazio( 6, 0, new Cavallo( new Nero() ) );
        m[ 2 ][ 0 ] = new Spazio( 2, 0, new Alfiere( new Nero() ) );
        m[ 5 ][ 0 ] = new Spazio( 5, 0, new Alfiere( new Nero() ) );
        m[ 3 ][ 0 ] = new Spazio( 3, 0, new Regina( new Nero() ) );
        m[ 4 ][ 0 ] = new Spazio( 4, 0, new Re( new Nero() ) );
        
        for( int i = 0; i < 8; i++ ){
            
            m[ i ][ 1 ]= new Spazio( i, 1, new Pedone( new Nero() ) );
        
        } // Fine Inizializzazione Pezzi Neri
        
        m[ 0 ][ 7 ] = new Spazio( 0, 7 , new Torre( new Bianco() ) );
        m[ 7 ][ 7 ] = new Spazio( 7, 7, new Torre( new Bianco() ) );
        m[ 1 ][ 7 ] = new Spazio( 1, 7, new Cavallo( new Bianco() ) );
        m[ 6 ][ 7 ] = new Spazio( 6, 7, new Cavallo( new Bianco() ) );
        m[ 2 ][ 7 ] = new Spazio( 2, 7, new Alfiere( new Bianco() ) );
        m[ 5 ][ 7 ] = new Spazio( 5, 7, new Alfiere( new Bianco() ) );
        m[ 3 ][ 7 ] = new Spazio( 3, 7, new Regina( new Bianco() ) );
        m[ 4 ][ 7 ] = new Spazio( 4, 7, new Re( new Bianco() ) );
        
        for( int i = 0; i < 8; i++ ){
            
            m[ i ][ 6 ]= new Spazio( i, 6, new Pedone( new Bianco() ) );
        
        } // Fine Inizializzazione Pezzi Bianchi
        
        for( int i = 2; i <= 5; i++ ){
            
            for( int j = 0; j < 8; j++ ){
                
                m[ j ][ i ] = new Spazio( j, i );
            
            }
        
        }
    
    }
    
    /**
     * costruttore che inizializza il primo turno
     * @param matrice matrice della scacchiera
     */
    public GestoreMovimenti( Spazio[][] matrice ){
        
        turno=new Bianco();
    
    }
    
    /**
     * ritorna il parametro di classe m che contiene la matrice rappresentante la scacchiera
     * @return il parametro di questa classe (m)
     */
    public Spazio[][] getMatrice(){
        
        return m;
    
    }
    
    /**
     * Ritorna una matrice con i possibili movimenti del pezzo passato come 
     * parametro. Una volta passato il parametro ricerca il metodo che 
     * determina il movimeto di tale pezzo e lo usa per scrivere la matrice
     * da ritornare come risultato.
    */
    /*
    public int[][] getPossibiliMovimenti( Spazio s ){ // Ritorna La Matrice Con Le Posizioni Mosse Dove Un Pezzo Puo Spostarsi
    
        Pezzo p = s.getOccupante();
        
        if( p instanceof Pedone ){
            
            return movimentiPedone( s );
        
        } else if( p instanceof Torre ){
            
            return movimentiTorre( s );
        
        } else if( p instanceof Alfiere ){
            
            return movimentiAlfiere( s );
        
        } else if( p instanceof Cavallo ){
            
            return movimentiCavallo( s );
        
        } else if ( p instanceof Regina ){
            
            return movimentiRegina( s );
        
        } else if( p instanceof Re ){
            
            return movimentiRe( s );
        
        } else {
            
            return null;
        
        }
    
    }
    */
    /**
     * cerca dove si trova il re e lo ritorna (non ritorna le coordinate ma un oggetto di classe Re)
     * @param c turno corrente (colore)
     * @return oggetto di classe Re di colore c
     * @throws Exception 
     */
    public Re getRe(Colore c) throws Exception{
        if(c instanceof Bianco)
            return getReBianco();
        else
            return getReNero();
    }
    
    /**
     * cerca dove si trova il re nero e lo ritorna
     * @return oggetto di classe Re nero
     * @throws Exception 
     */
    public Re getReNero() throws Exception{
        
        for( int i = 0; i < 8; i++ ){
            
            for( int j = 0; j < 8; j++ ){
                
                if( m[ i ][ j ].eOccupato() ){
                    
                    if( m[ i ][ j ].getOccupante() instanceof Re ){
                        
                        if( m[ i ][ j ].getOccupante().getColore() instanceof Nero ){
                            
                            return ( Re ) m[ i ][ j ].getOccupante();
                        
                        }
                    
                    }
                
                }
            
            }
        
        }
        
        throw new Exception( "Re Nero Non Trovato");
    
    }
    
    /**
     * cerca la casella dove si trova il re
     * @param c turno corrente 
     * @return un oggetto Spazio rappresentate la casella dove si trova il re
     */
    public Spazio getSpazioRe(Colore c){
        if(c instanceof Bianco)
            return getSpazioReBianco();
        else 
            return getSpazioReNero();
    }
    
    /**
     * cerca la casella dove si trova il re (avversario)
     * @param c turno corrente
     * @return un oggetto Spazio rappresentate la casella dove si trova il re
     */
    public Spazio getSpazioReAvversario(Colore c){
        if(c instanceof Bianco)
            return getSpazioReNero();
        else 
            return getSpazioReBianco();
    }

    /**
     * cerca la casella dove si trova il re (nero)
     * usato in getSpazioRe e getSpazioReAvversario
     * @return un oggetto Spazio rappresentate la casella dove si trova il re
     */
    public Spazio getSpazioReNero(){
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                if(m[i][j].eOccupato())
                    if(m[i][j].getOccupante() instanceof Re)
                       if(m[i][j].getOccupante().getColore() instanceof Nero)
                           return  m[i][j];
            }
        }
        Spazio s=new Spazio();
        s.setOccupato(false);
        return s;
        
    }
    
    /**
     * cerca la casella dove si trova il re (bianco)
     * usato in getSpazioRe e getSpazioReAvversario
     * @return un oggetto Spazio rappresentate la casella dove si trova il re
     */
    public Spazio getSpazioReBianco(){
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                if(m[i][j].eOccupato())
                    if(m[i][j].getOccupante() instanceof Re)
                       if(m[i][j].getOccupante().getColore() instanceof Bianco)
                           return  m[i][j];
            }
        }
        Spazio s=new Spazio();
        s.setOccupato(false);
        return s;
        
    }
    
    /**
     * cerca dove si trova il re bianco e lo ritorna
     * @return oggetto di classe Re bianco
     * @throws Exception 
     */
    public Re getReBianco() throws Exception{
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                if(m[i][j].eOccupato())
                    if(m[i][j].getOccupante() instanceof Re)
                       if(m[i][j].getOccupante().getColore() instanceof Bianco)
                           return (Re) m[i][j].getOccupante();
            }
        }
        throw new Exception("Re bianco non trovato");
    }
    

    // Metodo Per Controllare Il Pedone
    // Manca Il Controllo Di Quando Supero Una Pedina Con Un'Altra
    // Mancan Il Controllo Che Impedisce La Mossa Nel Caso Di Scacco
    // Manca Il Controllo Della Ultima Riga -> Trasformazione In Un Altro Pezzo Solo Dopo Il Movimento
    // Non Controllo L'Ultima Riga Perche Il Pezzo Si Trasforma
    // Aggiungere Il Controllo Se Il Pedone Non Si E Mai Mosso Puo Moversi Di 2 Quadratini
    /**
     * ritorna in quali posizioni della scacchiera può muoversi il pedone
     * @param s casella dove si trova il pedone
     * @return una matrice di interi che rappresenta i movimenti del pedone
     */
    public int[][] movimentiPedone( Spazio s ){
        Pedone p;
        int[][] scacchiera = new int[ 8 ][ 8 ];
        int x, y;
        p=(Pedone)s.getOccupante();
        x = s.getX();
        y = s.getY();
        
        if( p.getColore() instanceof Bianco ){
            
            // Immediatamente sopra
            if( !m[ x ][ y - 1 ].eOccupato() ){
                System.err.println("DEBUG: spostamento in avanti con pedone bianco ok");
                scacchiera[ x ][ y - 1 ] = 1;
            }
            
            //2 caselle più in alto
            if( !m[ x ][ y - 2 ].eOccupato() && p.mosso()){
                System.err.println("DEBUG: spostamento in avanti con pedone bianco ok");
                scacchiera[ x ][ y - 2 ] = 1;
            }
            
            // In Alto A Sinistra
            if( ( x - 1 ) >= 0 ){ // Controllo Posizione Valida ( Probabilmente Ridondante )   
                if( m[ x - 1 ][ y - 1 ].eOccupato()  ){  
                    if(m[ x - 1][ y - 1 ].getOccupante().getColore() instanceof Nero)
                        scacchiera[ x - 1 ][ y - 1 ] = 1;
                }
            }

            // In Alto A Destra
            if( ( x + 1 ) <= MAXLENGTH ){ // Controllo Posizione Valida ( Probabilmente Ridondante )
                
                if( m[ x + 1 ][ y - 1 ].eOccupato()  ){
                    if(m[ x + 1 ][ y - 1 ].getOccupante().getColore() instanceof Nero)
                        scacchiera[ x + 1 ][ y - 1 ] = 1;
                
                }
            
            }
        
        } else { // Fine IstanceOf Bianco, Inizio Caso IstanceOf Nero
            
            if( !m[ x ][ y + 1 ].eOccupato()){
                System.err.println("DEBUG: spostamento in avanti con pedone nero ok");
                scacchiera[ x ][ y + 1 ] = 1;
            }
            //2 caselle in basso
            if( !m[ x ][ y + 2 ].eOccupato() && p.mosso() ){
                System.err.println("DEBUG: spostamento in avanti con pedone bianco ok");
                scacchiera[ x ][ y + 2 ] = 1;
            }
            
            if( ( x - 1 ) >= 0 ) // Controllo Posizione Valida ( Probabilmente Ridondante )
                
                if( m[ x - 1 ][ y + 1 ].eOccupato()  ){
                    if(m[ x - 1 ][ y + 1 ].getOccupante().getColore() instanceof Nero)
                        scacchiera[ x + 1 ][ y + 1 ] = 1;
                
                }
            
            if( ( x + 1 ) <= MAXLENGTH ){ // Controllo Posizione Valida ( Probabilmente Ridondante )
                
                if( m[ x + 1 ][ y + 1 ].eOccupato()  ){
                    if(m[ x + 1 ][ y + 1 ].getOccupante().getColore() instanceof Nero)
                        scacchiera[ x + 1 ][ y + 1 ] = 1;
                
                }
            
            }
        
        }
        
        return scacchiera;
    
    } // Fine movimentiPedone
    
    private int[][] movimentiTorre( Spazio s ){
        Torre torre;
        int[][] scacchiera = new int[ 8 ][ 8 ];
        int x, y;
        int temp;
        torre=(Torre)s.getOccupante();
        x = s.getX();
        y = s.getY();

        // Direzione Verso Destra
        if( x + 1 <= MAXLENGTH ){

            // Spazi Liberi A Destra
            for( temp = x + 1; temp <= MAXLENGTH && !( m[ temp ][ y ].eOccupato()); temp++ ){
                
                scacchiera[ temp ][ y ] = 1;
            
            }

            // Primo Spazio Occupato A Destra
            temp++;
            
            if( temp <= MAXLENGTH ){ // Controllo Per Non Andare Fuori Scachiera

                // Se Lo Spazzio E Occupato E Contiene Un Pezzo Del Colore Opposto... Controllo isBusy() Obsoleto
                if( m[ temp ][ y ].eOccupato()  ){
                    if(!m[ temp ][ y ].getOccupante().getColore().equals( torre.getColore() ))
                        scacchiera[ temp ][ y ] = 1;
                }
            
            }
        
        }

        // Direzione Verso Sinistra
        if( x - 1 >= 0 ){
            
            for( temp = x - 1; temp >= 0 && !( m[ temp ][ y ].eOccupato()); temp-- ){
                
                scacchiera[ temp ][ y ] = 1;
            
            }
            
            temp--;
            
            if( temp >= 0 ){ // Controllo Per Non Andare Fuori Scachiera

                // Se Lo Spazzio E Occupato E Contiene Un Pezzo Del Colore Opposto
                if( m[ temp ][ y ].eOccupato() ){
                    if(!m[ temp ][ y ].getOccupante().getColore().equals( torre.getColore() ) ){
                        scacchiera[ temp ][ y ] = 1;
                    }
                }
            
            }
        
        }

        // Direzione Verso L'Alto
        if( y + 1 <= MAXLENGTH ){
            
            for( temp = y + 1; temp <= MAXLENGTH && ! ( m[ x ][ temp ].eOccupato()); temp++ ){
                
                scacchiera[ x ][ temp ] = 1;
            
            }
            
            temp++;
            
            if( temp <= MAXLENGTH ){ // Controllo Per Non Uscire Fuori Dalla Scachiera

                // Se Lo Spazzio E Occupato E Contiene Un Pezzo Del Colore Opposto
                if( m[ x ][ temp ].eOccupato() ){
                    if(!m[ x ][ temp ].getOccupante().getColore().equals( torre.getColore() )){
                        scacchiera[ x ][ temp ] = 1;
                    }
                }
            
            }
        
        }

        // Direzione Verso Il Basso
        if( y - 1 >= 0 ){
            
            for( temp = y - 1; temp >= 0 && !( m[ x ][ temp ].eOccupato()); temp++ ){
                
                scacchiera[ x ][ temp ] = 1;
            }
            
            temp--;
            
            if( temp >= 0 ){//controllo per non uscire fuori dalla scachiera

                // Se Lo Spazzio E Occupato E Contiene Un Pezzo Del Colore Opposto
                if( m[ x ][ temp ].eOccupato()  ){
                    if(!m[ x ][ temp ].getOccupante().getColore().equals( torre.getColore() )){
                        scacchiera[ x ][ temp ] = 1;
                    }
                }
            
            }
        
        }
        
        return scacchiera;
        // Throw New UnsupportedOperationException
    
    } // Fine movimentiTorre
    
    /**
     * 
     * @param s 
     * @return 
     */
    private int[][] movimentiAlfiere( Spazio s  ){
        Alfiere alfiere;
        int[][] scacchiera = new int[ 8 ][ 8 ];
        int x, y;
        int temp1, temp2; // Variabili Di Aiuto Per Spostamenti In Diagonale
        alfiere = (Alfiere) s.getOccupante();
        x = s.getX();
        y = s.getY();
        temp1 = x + 1; // Spostamento Sul Asse X
        temp2 = y + 1; // Spostamento Sul Asse Y
        
        // Direzione Verso Alto A Destra, Spazi Verso Alto A Destra Liberi
        while( temp1 <= MAXLENGTH && temp2 <= MAXLENGTH && !m[ temp1 ][ temp2 ].eOccupato() ){
            
            scacchiera[ temp1 ][ temp2 ] = 1;
            temp1++;
            temp2++;
        
        }
        
        // Il primo spazio occupato
        // Incremento effettuato dento il ciclo while
        // Temp1++;
        // Temp2++;
        // Potrebbe Essersi Verificata La Terza Condizione Del Ciclo While Ma Non Le Prime Due
        if( temp1 <=MAXLENGTH && temp2 <= MAXLENGTH ){

                // Se Lo Spazzio E Occupato E Contiene Un Pezzo Del Colore Opposto
                if( m[ temp1 ][ temp2 ].eOccupato() ){
                    if(!m[ temp1 ][ temp2 ].getOccupante().getColore().equals( alfiere.getColore() ))
                        scacchiera[ temp1 ][ temp2 ] = 1;
                
                }
        
        }

        // Direzione Verso Basso Destra
        temp1 = x + 1;
        temp2 = y - 1;
        
        while( temp1 <= MAXLENGTH && temp2 >= 0 && !m[ temp1 ][ temp2 ].eOccupato() ){
            
            scacchiera[ temp1 ][ temp2 ] = 1;
            temp1++;
            temp2--;
        
        }
        
        if( temp1 <= MAXLENGTH && temp2 >= 0 ){

            // Se Lo Spazzio E Occupato E Contiene Un Pezzo Del Colore Opposto
            if( m[ temp1 ][ temp2 ].eOccupato() ){
                if(!m[ temp1 ][ temp2 ].getOccupante().getColore().equals( alfiere.getColore() ))
                    scacchiera[ temp1 ][ temp2 ] = 1;
            }
        
        }

        // Direzione Alto Sinistra
        temp1 = x - 1;
        temp2 = y + 1;
        
        while( temp1 >= 0 && temp2 <= MAXLENGTH && !m[ temp1 ][ temp2 ].eOccupato() ){
            
            scacchiera[ temp1 ][ temp2 ] = 1;
            temp1--;
            temp2++;
        
        }
        
        if( temp1 >= 0 && temp2 <= MAXLENGTH ){

            // Se Lo Spazzio E Occupato E Contiene Un Pezzo Del Colore Opposto
            if( m[ temp1 ][ temp2 ].eOccupato() ){
                if(!m[ temp1 ][ temp2 ].getOccupante().getColore().equals( alfiere.getColore() ))
                    scacchiera[ temp1 ][ temp2 ] = 1;
            
            }
        
        }

        // Direzione Basso Sinistra
        temp1 = x - 1;
        temp2 = y - 1;
        
        while( temp1 >= 0 && temp2 >= 0 && !m[ temp1 ][ temp2 ].eOccupato() ){
            
            scacchiera[ temp1 ][ temp2 ] = 1;
            temp1--;
            temp2--;
        
        }
        
        if( temp1 >= 0 && temp2 >= 0 ){

            // Se Lo Spazzio E Occupato E Contiene Un Pezzo Del Colore Opposto
            if( m[ temp1 ][ temp2 ].eOccupato() ){
                if(!m[ temp1 ][ temp2 ].getOccupante().getColore().equals( alfiere.getColore() ))
                    scacchiera[ temp1 ][ temp2 ] = 1;
            
            }
        
        }
        
        return scacchiera;
        // Throw New UnsupportedOperationException
    
    } // Fine MovimentiAlfiere

    // Da Migliorare I Controlli Delle Posizioni Permesse Ragruppando Gli If
    private int[][] movimentiCavallo( Spazio s ){
        Cavallo cavallo;
        int[][] scacchiera = new int[ 8 ][ 8 ];
        int x, y;
        int temp;
        cavallo=(Cavallo) s.getOccupante();
        x = s.getX();
        y = s.getY();
        
        // Alto Destra
        if( x + 1 <= MAXLENGTH && y + 2 <= MAXLENGTH ){ // Libero O Del Avversario
            
            if( !m[ x + 1 ][ y + 2 ].eOccupato() || ( m[ x + 1 ][ y + 2 ].eOccupato() ) ){
                if(!m[ x + 1 ][ y + 2 ].getOccupante().getColore().equals( cavallo.getColore() ))
                    scacchiera[ x + 1 ][ y + 2 ] = 1;
            
            }
        
        }
        
        // Alto Sinistra
        if( x - 1 >= 0 && y + 2 <= MAXLENGTH ){ // Libero O Del Avversario
            
            if( !m[ x - 1 ][ y + 2 ].eOccupato() || ( m[ x - 1 ][ y + 2 ].eOccupato()  ) ){
                if(!m[ x - 1 ][ y + 2 ].getOccupante().getColore().equals( cavallo.getColore() ))
                    scacchiera[ x - 1 ][ y + 2 ] = 1;
            
            }
        
        }

        // Destra Alto
        if( x + 2 <= MAXLENGTH && y + 1 <= MAXLENGTH ){ // Libero O Del Avversario
            
            if( !m[ x + 2 ][ y + 1 ].eOccupato() || ( m[ x + 2 ][ y + 1 ].eOccupato() ) ){
                if(!m[ x + 2 ][ y + 1 ].getOccupante().getColore().equals( cavallo.getColore() ))
                    scacchiera[ x + 2 ][ y + 1 ] = 1;
            
            }
        
        }

        // Destra Basso
        if( x + 2 <= MAXLENGTH && y - 1 >= 0 ){ // Libero O Del Avversario
            
            if( !m[ x + 2 ][ y - 1 ].eOccupato() || ( m[ x + 2 ][ y - 1 ].eOccupato()  ) ){
                if(!m[ x + 2 ][ y - 1 ].getOccupante().getColore().equals( cavallo.getColore() ))
                    scacchiera[ x + 2 ][ y - 1 ] = 1;
            
            }
        
        }

        // Sinistra Alto
        if( x - 2 >= 0 && y + 1 <= MAXLENGTH ){ // Libero O Del Avversario
            
            if( !m[ x - 2 ][ y + 1 ].eOccupato() || ( m[ x - 2 ][ y + 1 ].eOccupato() ) ){
                if(!m[ x - 2 ][ y + 1 ].getOccupante().getColore().equals( cavallo.getColore() ))
                    scacchiera[ x - 2 ][ y + 1 ] = 1;
            
            }
        
        }

        // Sinistra Basso
        if( x - 2 >= 0 && y - 1 >= 0 ){ // Libero O Del Avversario
            
            if( !m[ x - 2 ][ y - 1 ].eOccupato() || ( m[ x - 2 ][ y - 1 ].eOccupato()  ) ){
                if(!m[ x - 2 ][ y - 1 ].getOccupante().getColore().equals( cavallo.getColore() ))
                    scacchiera[ x - 2 ][ y - 1 ] = 1;
            
            }
        
        }
        
        // Basso Desra
        if( x + 1 <= MAXLENGTH && y - 2 >= 0 ){ // Libero O Del Avversario
            
            if( !m[ x + 1 ][ y - 2 ].eOccupato() || ( m[ x + 1 ][ y - 2 ].eOccupato() ) ){
                if(!m[ x + 1 ][ y - 2 ].getOccupante().getColore().equals( cavallo.getColore() ))
                    scacchiera[ x + 1 ][ y - 2 ] = 1;
            
            }
        
        }
        
        // Basso Sinistra
        if( x - 1 >= 0 && y - 2 >= 0 ){ // Libero O Del Avversario
            
            if( !m[ x - 1 ][ y - 2 ].eOccupato() || ( m[ x - 1 ][ y - 2 ].eOccupato()  ) ){
                if(!m[ x - 1 ][ y - 2 ].getOccupante().getColore().equals( cavallo.getColore() ))
                    scacchiera[ x - 1 ][ y - 2 ] = 1;
            
            }
        
        }
        
        return scacchiera;
        // Throw New UnsupportedOperationException
    
    } // Fine movimentiCavallo
    
    private int[][] movimentiRegina( Spazio s ){
        Regina regina;
        int[][] scacchiera = new int[ 8 ][ 8 ];
        int[][] m1 = new int[ 8 ][ 8 ];
        int[][] m2 = new int[ 8 ][ 8 ];
        
        int x, y;
        regina = (Regina) s.getOccupante();
        x = s.getX();
        y = s.getY();
        
        m1 = movimentiTorre( new Spazio(x, y ,new Torre( regina.getColore() )) );
        m2 = movimentiAlfiere( new Spazio(x, y, new Alfiere( regina.getColore() )) );
        
        // Combinazione Dei Risultati Della Torre E Alfiere
        for( int i = 0; i < MAXLENGTH; i++ ){
            
            for( int j = 0; j < MAXLENGTH; j++ ){
                
                if( m1[ i ][ j ] == 1 || m2[ i ][ j ] == 1 ){
                    
                    scacchiera[ i ][ j ] = 1;
                
                }
            
            }
        
        }
        
        return scacchiera;
        // Throw New UnsupportedOperationException
    
    } // Fine movimentoRegina

    // Prendo Tutte Le Posizioni Adiacenti Togliendo Gli Spazi Occupati Da 
    // Pezzi Dello Stesso Colore, Tolgo Posizioni Vuote Che Potrebbero Essere
    // Attaccate Da Altri Pezzi E Aggiungo La Posizione Per Scambiare Il Re
    // Con La Torre Se Nessuno Dei 2 Si E Mosso
    /*
    private int[][] movimentiRe( Spazio s){
        System.err.println("DEBUG: movimentiRe in GestoreMovimenti");
        int[][] scacchiera = new int[ 8 ][ 8 ];
        int x, y;
        Re re;
        
        re = (Re) s.getOccupante();
        x = s.getX();
        y = s.getY();
        System.err.println("DEBUG: entrato in getMovimentiRe");
        // Parte Destra
        if( x + 1 <= MAXLENGTH ){
            
            if( !m[ x + 1 ][ y ].eOccupato() || !m[ x + 1 ][ y ].getOccupante().getColore().equals( re.getColore() ) ){
                
                scacchiera[ x + 1 ][ y ] = 1;
            
            }
            
            if( y + 1 <= MAXLENGTH && !m[ x + 1 ][ y + 1 ].eOccupato() || !m[ x + 1 ][ y + 1 ].getOccupante().getColore().equals( re.getColore() ) ){
                
                scacchiera[ x + 1 ][ y + 1 ] = 1;
            
            }
            
            if( y - 1 >= 0 && !m[ x + 1 ][ y - 1 ].eOccupato() || !m[ x + 1 ][ y - 1 ].getOccupante().getColore().equals( re.getColore() ) ){
                
                scacchiera[ x + 1 ][ y - 1 ] = 1;
            
            }
        
        }

        // Parte Sinistra
        if( x - 1 >= 0){
            
            if( !m[ x - 1 ][ y ].eOccupato() || !m[ x - 1 ][ y ].getOccupante().getColore().equals( re.getColore() ) ){
                
                scacchiera[ x - 1 ][ y ] = 1;
            
            }
            
            if( y + 1 <= MAXLENGTH && !m[ x - 1 ][ y + 1 ].eOccupato() || !m[ x - 1 ][ y + 1 ].getOccupante().getColore().equals( re.getColore() ) ){
                
                scacchiera[ x - 1 ][ y + 1 ] = 1;
            
            }
            
            if( y - 1 >= 0 && !m[ x - 1 ][ y - 1 ].eOccupato() || !m[ x - 1 ][ y - 1 ].getOccupante().getColore().equals( re.getColore() ) ){
                
                scacchiera[ x - 1 ][ y - 1 ] = 1;
            
            }
        
        }

        // Rimanente Spazio In Alto
        if( y + 1 <= MAXLENGTH && !m[ x ][ y + 1 ].eOccupato() || !m[ x ][ y + 1 ].getOccupante().getColore().equals( re.getColore() ) ){
            
            scacchiera[ x ][ y + 1 ] = 1;
        
        }

        // Rimanente Spazio In Basso
        if( y - 1 >= 0 && !m[ x ][ y - 1 ].eOccupato() || !m[ x ][ y - 1 ].getOccupante().getColore().equals( re.getColore() ) ){
            
            scacchiera[ x ][ y - 1 ] = 1;
        
        }
        System.err.println("DEBUG: calcolato le posizioni dove spostarsi senza considerare gli spazi sotto scacco");
        System.err.println(m.toString());
        // Da Implementare Ancora I Controlli Dello Scacco Inverso / Pericolo
        for( int i = 0; i <= MAXLENGTH; i++ ){
            
            for( int j = 0; j <= MAXLENGTH; j++ ){
                
                if( scacchiera[ i ][ j ] == 1 ){
                    
                    //m[ i ][ j ] = controlloScacco( i, j, re.getColore(),m );
                    
                    //getPezziAttaccantiIlRe(re,this.m);
                    //vedo se ci sono pezzi del colore opposto che possono muoversi nella locazione dove potrebbe spostarsi il Re
                    //se è cosi contrassegno la posizione come non consentita
                    if(re.getColore() instanceof Bianco){
                        if(!(getPezziSpostabiliQui(m,m[i][j],new Nero()).isEmpty()))
                            scacchiera[i][j]=0;
                    }
                    else{
                        if(!(getPezziSpostabiliQui(m,m[i][j],new Bianco()).isEmpty()))
                            scacchiera[i][j]=0;
                    }
                }
            }
        }

        // Da Implementare Il Controllo Dello Scambio Con Una Delle 2 Torri ( Arrocco )
        // In Questo Caso Il Re Si Sposta Di 2 Caselle Verso La Torre E La Torre Gli Si Mette Di Fianco Dall'Altra Parte
        // Simulare La m Modificata E Vedere Se Lo Scambio Non Porta A Una Situazione Di Scacco Matto
        if( !re.mosso() && controlloScacco( x, y,m ) == true ){ // Re Mai Mosso E Non E Sotto Scacco

            // Gli Spazi Fra Re E Torre Sinistra Sono Liberi?
            if( !m[ 1 ][ y ].eOccupato() && !m[ 2 ][ y ].eOccupato() && !m[ 3 ][ y ].eOccupato() ){

                // A Sinistra Ce Una Torre Nella Sua Posizione Originale
                if( m[ 0 ][ y ].getOccupante() instanceof Torre){

                    // Torre A Sinistra Mai Mossa
                    if( !( ( Torre ) m[ 0 ][ y ].getOccupante()).mosso() ){

                        // Controllo Se Tutti Gli Spazi Tra La Posizione Attuale Del Re E Quella Finale Non E Sotto Scacco
                        /*
                        if( ( controlloScacco( x - 1, y, re.getColore(),m ) == 1 ) ){
                            
                            if( ( controlloScacco( x - 2, y, re.getColore(),m ) == 1 ) ){
                                
                                m[ x - 2 ][ y ] = 1;
                            
                            }
                        
                        }
                        
                        //sostituisco il codice sopra con una chiamata alternativa
                        if(re.getColore() instanceof Bianco){
                            if( ( getPezziSpostabiliQui( m,m[x-1][y], new Nero()).isEmpty() ) ){
                                if( ( getPezziSpostabiliQui( m,m[x-2][y], new Nero()).isEmpty() )  ){
                                    scacchiera[ x - 2 ][ y ] = 1;
                                }
                            }
                        }
                        else{
                            if( ( getPezziSpostabiliQui( m,m[x-1][y], new Bianco()).isEmpty() ) ){
                                if( ( getPezziSpostabiliQui( m,m[x-2][y], new Bianco()).isEmpty() )  ){
                                    scacchiera[ x - 2 ][ y ] = 1;
                                }
                            }
                        }
                    }
                
                }
            
            }

            // Gli Spazi Tra Re E Torre Destra Sono Liberi?
            if( !m[ 5 ][ y ].eOccupato() && !m[ 6 ][ y ].eOccupato() ){

                // A Sinistra Ce Una Torre Nella Sua Posizione Originale
                if( m[ MAXLENGTH ][ y ].getOccupante() instanceof Torre ){

                    // Torre A Sinistra Mai Mossa
                    if( !( ( Torre ) m[ MAXLENGTH ][ y ].getOccupante()).mosso() ){

                        // Controllo Se Tutti Gli Spazi Tra La Posizione Attuale Del Re E Quella Finale Non Sono Sotto Scacco
                        if(re.getColore() instanceof Bianco){
                            if( ( getPezziSpostabiliQui( m,m[x+1][y], new Nero()).isEmpty() ) ){
                                if( ( getPezziSpostabiliQui( m,m[x+2][y], new Nero()).isEmpty() )  ){
                                    scacchiera[ x + 2 ][ y ] = 1;
                                }
                            }
                        }
                        else{
                            if( ( getPezziSpostabiliQui( m,m[x+1][y], new Bianco()).isEmpty() ) ){
                                if( ( getPezziSpostabiliQui( m,m[x+2][y], new Bianco()).isEmpty() )  ){
                                    scacchiera[ x + 2 ][ y ] = 1;
                                }
                            }
                        }
                    }
                }
            }
        }
        
        return scacchiera;
        // Throw New UnsupportedOperationException
    
    } // Fine movimentiRe
    */
    // Minimizzare Il Codice Ragruppando Re E Pedone In Casi Specifici ( Come Regina Con Torre E Alfiere )
    // Si Possono Fare Delle Chiamate Per Minimizzare Il Codice
    //false=c'è scacco
    //true=non c'è scacco
    /**
     * Questo metodo è diviso in più parti
     * Determina se c'è scacco matto
     * @param x ascissa dove si trova il re
     * @param y ordinata dove si trova il re
     * @param matrix maticce che simula la scacchiera
     * @return ritorna true se c'è scacco
     */
    public boolean controlloScacco( int x, int y,Spazio[][] matrix){
        System.err.println("DEBUG: inizia controlloScacco");
        Spazio[][] mat=coppiaMatrice(matrix);
        Colore colore=mat[x][y].getOccupante().getColore();
        int temp1, temp2; 
        // Controllo Per Torri / Regine In Orizzontale E Verticale
        
        //disegnaMatriceSpaziOccupati(matrix);
        // Verso Destra
        boolean uscita = false;
        for( int i = x+1; i <= MAXLENGTH && !uscita; i++ ){
            if( mat[ i ][ y ].eOccupato() ){  
                uscita = true; // Uscita Dal Ciclo Dopo Aver Controllato Il Primo Spazio Per Non Fare Controlli Inutili
                if( !mat[ i ][ y ].getOccupante().getColore().equals( colore )){
                    if( mat[ i ][ y ].getOccupante() instanceof Torre || mat[ i ][ y ].getOccupante() instanceof Regina ){
                        System.err.println("IL RE è SOTTO SCACCO: attacco da destra");
                        return false;
                    }
                }
            }
        }

        // Verso Sinistra
        uscita = false;
        
        for( int i = x-1; i >= 0 && !uscita; i-- ){
            if( mat[ i ][ y ].eOccupato() ){
                uscita = true; // Uscita Dal Ciclo Dopo Aver Controllato Il Primo Spazio Per Non Fare Controlli Inutili
                if( !mat[ i ][ y ].getOccupante().getColore().equals( colore ) ){
                    if( mat[ i ][ y ].getOccupante() instanceof Torre || mat[ i ][ y ].getOccupante() instanceof Regina ){
                        System.err.println("IL RE è SOTTO SCACCO: attacco da sinistra");
                        return false;
                    }
                }
            }
        }

        // Verso il basso
        uscita = false;
        
        for( int i = y+1; i <= MAXLENGTH && !uscita; i++ ){
            if( mat[ x ][ i ].eOccupato() ){ 
                uscita = true; // Uscita Dal Ciclo Dopo Aver Controllato Il Primo Spazio Per Non Fare Controlli Inutili
                if( !mat[ x ][ i ].getOccupante().getColore().equals( colore ) ){  
                    if( mat[ x ][ i ].getOccupante() instanceof Torre || mat[ x ][ i ].getOccupante() instanceof Regina ){
                        System.err.println("IL RE è SOTTO SCACCO: attacco dall'alto");
                        return false;
                    }
                }
            }
        }

        // Verso l'alto
        uscita = false;
        
        for( int i = y-1; i >= 0 && !uscita; i-- ){
            
            if( mat[ x ][ i ].eOccupato() ){
                
                uscita = true; // Uscita Dal Ciclo Dopo Aver Controllato Il Primo Spazio Per Non Fare Controlli Inutili
                
                if( !mat[ x ][ i ].getOccupante().getColore().equals( colore ) ){
                    
                    if( mat[ x ][ i ].getOccupante() instanceof Torre || mat[ x ][ i ].getOccupante() instanceof Regina ){
                        System.err.println("IL RE è SOTTO SCACCO: attacco dal basso");
                        return false;
                    
                    }
                
                }
            
            }
        
        }

        // Controlli In Diagonale Per Alfieri E Regine

        // Verso L'Alto A Destra
        temp1 = x + 1;
        temp2 = y + 1;
        
        while( temp1 <= MAXLENGTH && temp2 <= MAXLENGTH && !mat[ temp1 ][ temp2 ].eOccupato() ){          
            temp1++;
            temp2++;
        }
        
        if( temp1 <= MAXLENGTH && temp2 <= MAXLENGTH  ){
            if(!mat[ temp1 ][ temp2 ].getOccupante().getColore().equals( colore )){
                if( mat[ temp1 ][ temp2 ].getOccupante() instanceof Alfiere || mat[ temp1 ][ temp2 ].getOccupante() instanceof Regina ){
                    System.err.println("IL RE è SOTTO SCACCO: attacco in diagonale alto destra");
                    return false;
                }
            }
        
        }

        // Verso L'Alto A Sinistra
        temp1 = x - 1;
        temp2 = y + 1;
        
        while( temp1 >= 0 && temp2 <= MAXLENGTH && !mat[ temp1 ][ temp2 ].eOccupato() ){        
            temp1--;
            temp2++;
        }
        
        if( temp1 >= 0 && temp2 <= MAXLENGTH && !mat[ temp1 ][ temp2 ].getOccupante().getColore().equals( colore ) ){
            
            if( mat[ temp1 ][ temp2 ].getOccupante() instanceof Alfiere || mat[ temp1 ][ temp2 ].getOccupante() instanceof Regina ){
                System.err.println("IL RE è SOTTO SCACCO: attacco in diagonale alto sinistra");
                return false;
            
            }
        
        }

        // Verso Il Basso A Sinistra
        temp1 = x - 1;
        temp2 = y - 1;
        
        while( temp1 >= 0 && temp2 >= 0 && !mat[ temp1 ][ temp2 ].eOccupato() ){
            
            temp1--;
            temp2--;
        
        }
        
        if( temp1 >= 0 && temp2 >= 0 ){
            if(!mat[ temp1 ][ temp2 ].getOccupante().getColore().equals( colore )){
                if( mat[ temp1 ][ temp2 ].getOccupante() instanceof Alfiere || mat[ temp1 ][ temp2 ].getOccupante() instanceof Regina ){
                    System.err.println("IL RE è SOTTO SCACCO: attacco in diagonale basso sinistra");
                    return false;
            
                }
            }
        }

        // Verso Il Basso A Destra
        temp1 = x + 1;
        temp2 = y - 1;
        
        while( temp1 <= MAXLENGTH && temp2 >= 0 && !mat[ temp1 ][ temp2 ].eOccupato() ){
            
            temp1++;
            temp2--;
        
        }
        
        if( temp1 <= MAXLENGTH && temp2 >= 0){
            if(!mat[ temp1 ][ temp2 ].getOccupante().getColore().equals( colore ) ){
                if( mat[ temp1 ][ temp2 ].getOccupante() instanceof Alfiere || mat[ temp1 ][ temp2 ].getOccupante() instanceof Regina ){
                    System.err.println("IL RE è SOTTO SCACCO: attacco in diagonale basso destra");
                    return false;
            
                }
            }
        }

        // Controlli Per Il Pedone ( Potrebbe Essere Integrato Nell'Alfiere In Casi Specifici )
        
        if( colore instanceof Nero ){ // Inizio Controllo Pedoni Neri
            
            if( x + 1 <= MAXLENGTH && y + 1 <= MAXLENGTH && mat[ x + 1 ][ y + 1 ].eOccupato() && mat[ x + 1 ][ y + 1 ].getOccupante() instanceof Pedone ){
                
                if( !mat[ x + 1 ][ y + 1 ].getOccupante().getColore().equals( colore ) ){
                    System.err.println("IL RE è SOTTO SCACCO da un pedone");
                    return false;
                
                }
            
            }
            if(x - 1 >= 0 && y + 1 <= MAXLENGTH){
                if(mat[ x - 1 ][ y + 1 ].eOccupato()){
                    if( mat[ x - 1 ][ y + 1 ].getOccupante() instanceof Pedone ){
                
                        if( !mat[ x - 1 ][ y + 1 ].getOccupante().getColore().equals( colore ) ){
                            System.err.println("IL RE è SOTTO SCACCO da un pedone");
                            return false;
                
                        }
                    }
                }
            }
        
        } else { // Inizio Controllo Pedoni Bianchi
            
            if( x + 1 <= MAXLENGTH && y - 1 >= 0 && mat[ x + 1 ][ y - 1 ].eOccupato() && mat[ x + 1 ][ y - 1 ].getOccupante() instanceof Pedone ){
                
                if( !mat[ x + 1 ][ y - 1 ].getOccupante().getColore().equals( colore ) ){
                    System.err.println("IL RE è SOTTO SCACCO da un pedone");
                    return false;
                
                }
            
            }
            
            if( x - 1 >= 0 && y - 1 >= 0 && mat[ x - 1 ][ y - 1 ].eOccupato() ){
                if(mat[ x - 1 ][ y - 1 ].getOccupante() instanceof Pedone ){
                    if( !mat[ x - 1 ][ y - 1 ].getOccupante().getColore().equals( colore ) ){
                        System.err.println("IL RE è SOTTO SCACCO da un pedone");
                        return false;
                
                    }
                }
            }
        
        }

        // Controlli Del Cavallo

        //In Alto A Destra
        if( x + 1 <= MAXLENGTH && y + 2 <= MAXLENGTH && mat[ x + 1 ][ y + 2 ].eOccupato() ){
            
            if( !mat[ x + 1 ][ y + 2 ].getOccupante().getColore().equals( colore ) && mat[ x + 1 ][ y + 2 ].getOccupante() instanceof Cavallo ){
                System.err.println("IL RE è SOTTO SCACCO cavallo alto destra");
                return false;
            
            }
        
        }

        //In Alto A Sinistra
        if( x - 1 >= 0 && y + 2 <= MAXLENGTH && mat[ x - 1 ][ y + 2 ].eOccupato() ){
            
            if( !mat[ x - 1 ][ y + 2 ].getOccupante().getColore().equals( colore ) && mat[ x - 1 ][ y + 2 ].getOccupante() instanceof Cavallo ){
                System.err.println("IL RE è SOTTO SCACCO cavallo alto sinistra");
                return false;
            
            }
        
        }

        // A Destra In Alto
        if( x + 2 <= MAXLENGTH && y + 1 <= MAXLENGTH && mat[ x + 2 ][ y + 1 ].eOccupato() ){
            
            if( !mat[ x + 2 ][ y + 1 ].getOccupante().getColore().equals( colore ) && mat[ x + 2 ][ y + 1 ].getOccupante() instanceof Cavallo ){
                System.err.println("IL RE è SOTTO SCACCO cavallo destra alto");
                return false;
            
            }
        
        }

        // A Destra In Basso
        if( x + 2 <= MAXLENGTH && y - 1 >= 0 && mat[ x + 2 ][ y - 1 ].eOccupato() ){       
            if( !mat[ x + 2 ][ y - 1 ].getOccupante().getColore().equals( colore ) && mat[ x + 2 ][ y - 1 ].getOccupante() instanceof Cavallo ){      
                System.err.println("IL RE è SOTTO SCACCO cavallo destra basso");
                return false;
            }
        }

        // A Sinistra In Alto
        if( x - 2 >= 0 && y + 1 <= MAXLENGTH && mat[ x - 2 ][ y + 1 ].eOccupato() ){    
            if( !mat[ x - 2 ][ y + 1 ].getOccupante().getColore().equals( colore ) && mat[ x - 2 ][ y + 1 ].getOccupante() instanceof Cavallo ){    
                System.err.println("IL RE è SOTTO SCACCO cavallo sinistra alto");
                return false;
            }
        }

        // A Sinistra In Basso
        if( x - 2 >= 0 && y - 1 >= 0 && mat[ x - 2 ][ y - 1 ].eOccupato() ){
            
            if( !mat[ x - 2 ][ y - 1 ].getOccupante().getColore().equals( colore ) && mat[ x - 2 ][ y - 1 ].getOccupante() instanceof Cavallo ){
                System.err.println("IL RE è SOTTO SCACCO cavallo sinistra basso");
                return false;
            
            }
        
        }

        // In Basso A Destra
        if(x + 1 <= MAXLENGTH && y - 2 >= 0){
            if( mat[ x + 1 ][ y - 2 ].eOccupato() ){
            
                if( !mat[ x + 1 ][ y - 2 ].getOccupante().getColore().equals( colore ) && mat[ x + 1 ][ y - 2 ].getOccupante() instanceof Cavallo ){
                    System.err.println("IL RE è SOTTO SCACCO cavallo basso destra");
                    return false;
                }
            }
        }

        // In Basso A Sinistra
        if( x - 1 >= 0 && y - 2 >= 0 && mat[ x - 1 ][ y - 2 ].eOccupato() ){
            
            if( !mat[ x - 1 ][ y - 2 ].getOccupante().getColore().equals( colore ) && mat[ x - 1 ][ y - 2 ].getOccupante() instanceof Cavallo ){
                System.err.println("IL RE è SOTTO SCACCO cavallo basso sinistra");
                return false;
            
            }
        
        }

        // Controllo Re Adiacente

        // Destra
        if( x + 1 <= MAXLENGTH && mat[ x + 1 ][ y ].eOccupato() ){
            
            if( !mat[ x + 1 ][ y ].getOccupante().getColore().equals( colore ) && m[ x + 1 ][ y ].getOccupante() instanceof Re ){
                System.err.println("IL RE è SOTTO SCACCO re");
                return false;
            
            }
        
        }

        // Sinistra
        if( x - 1 >= 0 && mat[ x - 1 ][ y ].eOccupato() ){          
            if( !mat[ x - 1 ][ y ].getOccupante().getColore().equals( colore ) ){
                if(mat[ x - 1 ][ y ].getOccupante() instanceof Re){
                    System.err.println("IL RE è SOTTO SCACCO re");
                    return false;
                }
            }
        }

        // Alto
        if( y + 1 <= MAXLENGTH ){ 
            if(mat[ x ][ y + 1 ].eOccupato()){
                if( !mat[ x ][ y + 1 ].getOccupante().getColore().equals( colore )){  
                    if(mat[ x ][ y + 1 ].getOccupante() instanceof Re ){
                        System.err.println("IL RE è SOTTO SCACCO re");
                        return false;
                    }
                }
            }
        }

        // Basso
        if( y - 1 >= 0  ){
            if(mat[ x ][ y - 1 ].eOccupato()){
                if( !mat[ x ][ y - 1 ].getOccupante().getColore().equals( colore ) && mat[ x ][ y - 1 ].getOccupante() instanceof Re ){
                    System.err.println("IL RE è SOTTO SCACCO re");
                    return false;
                }
            }
        }

        // In Alto A Destra
        if( x + 1 <= MAXLENGTH && y + 1 <= MAXLENGTH ){
            if(mat[ x + 1 ][ y + 1 ].eOccupato()){
                if( !mat[ x + 1 ][ y + 1 ].getOccupante().getColore().equals( colore ) && mat[ x + 1 ][ y + 1 ].getOccupante() instanceof Re ){ 
                    System.err.println("IL RE è SOTTO SCACCO re");
                    return false;
                }
            }
        }

        // In Alto A Sinistra
        if( x - 1 >= 0 && y + 1 <= MAXLENGTH && mat[ x - 1 ][ y + 1 ].eOccupato() ){
            if( !mat[ x - 1 ][ y + 1 ].getOccupante().getColore().equals( colore ) && mat[ x - 1 ][ y + 1 ].getOccupante() instanceof Re ){ 
                System.err.println("IL RE è SOTTO SCACCO re");
                return false;
            }
        }

        // In Basso A Destra
        if( x + 1 <= MAXLENGTH && y - 1 >= 0 && mat[ x + 1 ][ y - 1 ].eOccupato() ){
            if( !mat[ x + 1 ][ y - 1 ].getOccupante().getColore().equals( colore ) && mat[ x + 1 ][ y - 1 ].getOccupante() instanceof Re ){      
                System.err.println("IL RE è SOTTO SCACCO re");
                return false;
            }
        }

        // In Basso A Sinistra
        if( x - 1 >= 0 && y - 1 >= 0 && mat[ x - 1 ][ y - 1 ].eOccupato() ){         
            if( !mat[ x - 1 ][ y - 1 ].getOccupante().getColore().equals( colore ) && mat[ x - 1 ][ y - 1 ].getOccupante() instanceof Re ){   
                System.err.println("IL RE è SOTTO SCACCO re");
                return false;
                
            }
        }
        System.err.println("IL RE NON è SOTTO SCACCO");
        return true;
        
        // Throw New UnsupportedOperationException
    
    }
    
    /**
     * Questo metodo è diviso in più parti
     * Funzione di supporto per facilitare l'uso di controlloScacco
     * @param s casella dove si trova il re
     * @return true se c'è scacco
     */
    public boolean controlloScacco(Spazio s) {  
        //Re r=(Re) s.getOccupante();
        return controlloScacco( s.getX(), s.getY(),m );
    }
    
    /**
     * Questo metodo è diviso in più parti
     * Facilita l'utilizzo degli altri controlloScacco ritornando la casella sulla 
     * quale si trova il re del turno corrente
     * @param colore turno corrente
     * @param matrice matrice che rappresenta la scacchiera
     * @return true se c'è scacco
     * @throws Exception 
     */
    public boolean controlloScacco(Colore colore,Spazio[][] matrice) throws Exception{
        Spazio s;
        if(colore instanceof Bianco)
            s=this.getSpazioReBianco(); 
        else
           s=this.getSpazioReNero();  
        
        return controlloScacco( s.getX(), s.getY(),matrice ); 
    }
    
    /**
     * questo metodo è diviso in più parti
     * semplifica il controlloScaccoReAvversario ritornando la posizione del re avversario
     * @param colore turno corrente
     * @return true se c'è scacco
     * @throws Exception 
     */
    public boolean controlloScaccoReAvversario(Colore colore) throws Exception{
        Spazio s;
        if(colore instanceof Bianco)
            s=this.getSpazioReNero(); 
        else
           s=this.getSpazioReBianco();  
        
        return controlloScacco( s.getX(), s.getY(),m ); 
    }
    
    /**
     * questo metodo è diviso in più parti
     * serve nel per controllare in anticipo se, dopo un movimento, il re nero è sotto scacco
     * @param colore turno corrente
     * @param matrice matrice raffigurante la scacchiera
     * @return true se c'è scacco
     * @throws Exception 
     */
    public boolean controlloScaccoReAvversario(Colore colore,Spazio[][] matrice) throws Exception{
        Spazio s;
        if(colore instanceof Bianco)
            s=this.getSpazioReNero(); 
        else
           s=this.getSpazioReBianco();  
        
        return controlloScacco( s.getX(), s.getY(),matrice ); 
    }
    
    /**
     * Controlla se il re è messo in pericolo da eventuali pezzi nemici
     * @param s casella dove si trova il re
     * @param matrix matrice della scacchiera
     * @return una lista con i pezzi che attaccan il re
     */
    public LinkedList<Pezzo> getPezziAttaccantiIlRe(Spazio s,Spazio[][] matrix){
        LinkedList<Pezzo> lista; 
        Spazio[][] mat=matrix;
        Re re;
        lista = new LinkedList<>();
        int x=s.getX();
        int y=s.getY();
        re=(Re) s.getOccupante();
        Colore colore=re.getColore();
        int temp1, temp2; 
        // Controllo Per Torri / Regine In Orizzontale E Verticale

        // Verso Destra
        boolean uscita = false;
        for( int i = x; i <= MAXLENGTH && !uscita; i++ ){
            if( mat[ i ][ y ].eOccupato() ){  
                uscita = true; // Uscita Dal Ciclo Dopo Aver Controllato Il Primo Spazio Per Non Fare Controlli Inutili
                if( !mat[ i ][ y ].getOccupante().getColore().equals( colore )){
                    if( mat[ i ][ y ].getOccupante() instanceof Torre || mat[ i ][ y ].getOccupante() instanceof Regina ){
                        lista.add(mat[i][y].getOccupante());
                    }
                }
            }
        }

        // Verso Sinistra
        uscita = false;
        
        for( int i = x; i >= 0 && !uscita; i-- ){
            if( mat[ i ][ y ].eOccupato() ){
                uscita = true; // Uscita Dal Ciclo Dopo Aver Controllato Il Primo Spazio Per Non Fare Controlli Inutili
                if( !mat[ i ][ y ].getOccupante().getColore().equals( colore ) ){
                    if( mat[ i ][ y ].getOccupante() instanceof Torre || mat[ i ][ y ].getOccupante() instanceof Regina ){
                        lista.add(mat[i][y].getOccupante());
                    }
                }
            }
        }

        // Verso L'Alto
        uscita = false;
        
        for( int i = y; i <= MAXLENGTH && !uscita; i++ ){
            if( mat[ x ][ i ].eOccupato() ){ 
                uscita = true; // Uscita Dal Ciclo Dopo Aver Controllato Il Primo Spazio Per Non Fare Controlli Inutili
                if( !mat[ x ][ i ].getOccupante().getColore().equals( colore ) ){  
                    if( mat[ x ][ i ].getOccupante() instanceof Torre || mat[ x ][ i ].getOccupante() instanceof Regina ){
                        lista.add(mat[x][i].getOccupante());
                    }
                }
            }
        }

        // Verso Il Basso
        uscita = false;
        
        for( int i = y; i >= 0 && !uscita; i-- ){
            
            if( mat[ x ][ i ].eOccupato() ){
                
                uscita = true; // Uscita Dal Ciclo Dopo Aver Controllato Il Primo Spazio Per Non Fare Controlli Inutili
                
                if( !mat[ x ][ i ].getOccupante().getColore().equals( colore ) ){
                    
                    if( mat[ x ][ i ].getOccupante() instanceof Torre || mat[ x ][ i ].getOccupante() instanceof Regina ){
                        
                        lista.add(mat[x][i].getOccupante());
                    
                    }
                
                }
            
            }
        
        }

        // Controlli In Diagonale Per Alfieri E Regine

        // Verso L'Alto A Destra
        temp1 = x + 1;
        temp2 = y + 1;
        
        while( temp1 <= MAXLENGTH && temp2 <= MAXLENGTH && !mat[ temp1 ][ temp2 ].eOccupato() ){
            
            temp1++;
            temp2++;
        
        }
        
        if( temp1 <= MAXLENGTH && temp2 <= MAXLENGTH && !mat[ temp1 ][ temp2 ].getOccupante().getColore().equals( colore ) ){
            
            if( mat[ temp1 ][ temp2 ].getOccupante() instanceof Alfiere || mat[ temp1 ][ temp2 ].getOccupante() instanceof Regina ){
                
                lista.add(mat[temp1][temp2].getOccupante());
            
            }
        
        }

        // Verso L'Alto A Sinistra
        temp1 = x - 1;
        temp2 = y + 1;
        
        while( temp1 >= 0 && temp2 <= MAXLENGTH && !mat[ temp1 ][ temp2 ].eOccupato() ){
            
            temp1--;
            temp2++;
        
        }
        
        if( temp1 >= 0 && temp2 <= MAXLENGTH && !mat[ temp1 ][ temp2 ].getOccupante().getColore().equals( colore ) ){
            
            if( mat[ temp1 ][ temp2 ].getOccupante() instanceof Alfiere || mat[ temp1 ][ temp2 ].getOccupante() instanceof Regina ){
                
                lista.add(mat[temp1][temp2].getOccupante());
            
            }
        
        }

        // Verso Il Basso A Sinistra
        temp1 = x - 1;
        temp2 = y - 1;
        
        while( temp1 >= 0 && temp2 >= 0 && !m[ temp1 ][ temp2 ].eOccupato() ){
            
            temp1--;
            temp2--;
        
        }
        
        if( temp1 >= 0 && temp2 >= 0 && !mat[ temp1 ][ temp2 ].getOccupante().getColore().equals( colore ) ){
            
            if( mat[ temp1 ][ temp2 ].getOccupante() instanceof Alfiere || mat[ temp1 ][ temp2 ].getOccupante() instanceof Regina ){
                
                lista.add(mat[temp1][temp2].getOccupante());
            
            }
        
        }

        // Verso Il Basso A Destra
        temp1 = x + 1;
        temp2 = y - 1;
        
        while( temp1 <= MAXLENGTH && temp2 >= 0 && !mat[ temp1 ][ temp2 ].eOccupato() ){
            
            temp1++;
            temp2--;
        
        }
        
        if( temp1 <= MAXLENGTH && temp2 >= 0 && !mat[ temp1 ][ temp2 ].getOccupante().getColore().equals( colore ) ){
            
            if( mat[ temp1 ][ temp2 ].getOccupante() instanceof Alfiere || mat[ temp1 ][ temp2 ].getOccupante() instanceof Regina ){
                
                lista.add(mat[temp1][temp2].getOccupante());
            
            }
        
        }

        // Controlli Per Il Pedone ( Potrebbe Essere Integrato Nell'Alfiere In Casi Specifici )
        
        if( colore instanceof Nero ){ // Inizio Controllo Pedoni Neri
            
            if( x + 1 <= MAXLENGTH && y + 1 <= MAXLENGTH && mat[ x + 1 ][ y + 1 ].eOccupato() && mat[ x + 1 ][ y + 1 ].getOccupante() instanceof Pedone ){
                
                if( !mat[ x + 1 ][ y + 1 ].getOccupante().getColore().equals( colore ) ){
                    
                    lista.add(mat[x+1][y+1].getOccupante());
                
                }
            
            }
            
            if( x - 1 >= 0 && y + 1 <= MAXLENGTH && mat[ x - 1 ][ y + 1 ].eOccupato() && mat[ x + 1 ][ y - 1 ].getOccupante() instanceof Pedone ){
                
                if( !mat[ x - 1 ][ y + 1 ].getOccupante().getColore().equals( colore ) ){
                    
                    lista.add(mat[x-1][y+1].getOccupante());
                
                }
            
            }
        
        } else { // Inizio Controllo Pedoni Bianchi
            
            if( x + 1 <= MAXLENGTH && y - 1 >= 0 && mat[ x + 1 ][ y - 1 ].eOccupato() && mat[ x + 1 ][ y - 1 ].getOccupante() instanceof Pedone ){
                
                if( !mat[ x + 1 ][ y - 1 ].getOccupante().getColore().equals( colore ) ){
                    
                    lista.add(mat[x+1][y-1].getOccupante());
                }
            
            }
            
            if( x - 1 >= 0 && y - 1 >= 0 && mat[ x - 1 ][ y - 1 ].eOccupato() && mat[ x - 1 ][ y - 1 ].getOccupante() instanceof Pedone ){
                
                if( !mat[ x - 1 ][ y - 1 ].getOccupante().getColore().equals( colore ) ){
                    
                    lista.add(mat[x-1][y-1].getOccupante());
                
                }
            
            }
        
        }

        // Controlli Del Cavallo

        //In Alto A Destra
        if( x + 1 <= MAXLENGTH && y + 2 <= MAXLENGTH && mat[ x + 1 ][ y + 2 ].eOccupato() ){
            
            if( !mat[ x + 1 ][ y + 2 ].getOccupante().getColore().equals( colore ) && mat[ x + 1 ][ y + 2 ].getOccupante() instanceof Cavallo ){
                
                lista.add(mat[x+1][y+2].getOccupante());
            
            }
        
        }

        //In Alto A Sinistra
        if( x - 1 >= 0 && y + 2 <= MAXLENGTH && mat[ x - 1 ][ y + 2 ].eOccupato() ){
            
            if( !mat[ x - 1 ][ y + 2 ].getOccupante().getColore().equals( colore ) && mat[ x - 1 ][ y + 2 ].getOccupante() instanceof Cavallo ){
                
                lista.add(mat[x-1][y+2].getOccupante());
            
            }
        
        }

        // A Destra In Alto
        if( x + 2 <= MAXLENGTH && y + 1 <= MAXLENGTH && mat[ x + 2 ][ y + 1 ].eOccupato() ){
            
            if( !mat[ x + 2 ][ y + 1 ].getOccupante().getColore().equals( colore ) && mat[ x + 2 ][ y + 1 ].getOccupante() instanceof Cavallo ){
                
                lista.add(mat[x+2][y+1].getOccupante());
            
            }
        
        }

        // A Destra In Basso
        if( x + 2 <= MAXLENGTH && y - 1 >= 0 && mat[ x + 2 ][ y - 1 ].eOccupato() ){
            
            if( !mat[ x + 2 ][ y - 1 ].getOccupante().getColore().equals( colore ) && mat[ x + 2 ][ y + 1 ].getOccupante() instanceof Cavallo ){
                
                lista.add(mat[x+2][y-1].getOccupante());
            
            }
        
        }

        // A Sinistra In Alto
        if( x - 2 >= 0 && y + 1 <= MAXLENGTH && mat[ x - 2 ][ y + 1 ].eOccupato() ){
            
            if( !mat[ x - 2 ][ y + 1 ].getOccupante().getColore().equals( colore ) && mat[ x - 2 ][ y + 1 ].getOccupante() instanceof Cavallo ){
                
                lista.add(mat[x-2][y+1].getOccupante());
            
            }
        
        }

        // A Sinistra In Basso
        if( x - 2 >= 0 && y - 1 >= 0 && mat[ x - 2 ][ y - 1 ].eOccupato() ){
            
            if( !mat[ x - 2 ][ y - 1 ].getOccupante().getColore().equals( colore ) && mat[ x - 2 ][ y - 1 ].getOccupante() instanceof Cavallo ){
                
                lista.add(mat[x-2][y-1].getOccupante());
            
            }
        
        }

        // In Basso A Destra
        if( x + 1 <= MAXLENGTH && y - 2 <= MAXLENGTH && mat[ x + 1 ][ y - 2 ].eOccupato() ){
            
            if( !mat[ x + 1 ][ y - 2 ].getOccupante().getColore().equals( colore ) && mat[ x + 1 ][ y - 2 ].getOccupante() instanceof Cavallo ){
                
                lista.add(mat[x+1][y-2].getOccupante());
            
            }
        
        }

        // In Basso A Sinistra
        if( x - 1 >= 0 && y - 2 >= 0 && mat[ x - 1 ][ y - 2 ].eOccupato() ){
            
            if( !mat[ x - 1 ][ y - 2 ].getOccupante().getColore().equals( colore ) && mat[ x - 1 ][ y - 2 ].getOccupante() instanceof Cavallo ){
                
                lista.add(mat[x-1][y-2].getOccupante());
            
            }
        
        }

        // Controllo Re Adiacente

        // Destra
        if( x + 1 <= MAXLENGTH && mat[ x + 1 ][ y ].eOccupato() ){
            
            if( !mat[ x + 1 ][ y ].getOccupante().getColore().equals( colore ) && m[ x + 1 ][ y ].getOccupante() instanceof Re ){
                
                lista.add(mat[x+1][y].getOccupante());
            
            }
        
        }

        // Sinistra
        if( x - 1 >= 0 && mat[ x - 1 ][ y ].eOccupato() ){          
            if( !mat[ x - 1 ][ y ].getOccupante().getColore().equals( colore ) && mat[ x - 1 ][ y ].getOccupante() instanceof Re ){
                lista.add(mat[x-1][y].getOccupante());
            }
        }

        // Alto
        if( y + 1 <= MAXLENGTH && m[ x ][ y + 1 ].eOccupato() ){ 
            if( !mat[ x ][ y + 1 ].getOccupante().getColore().equals( colore ) && mat[ x ][ y + 1 ].getOccupante() instanceof Re ){             
                lista.add(mat[x][y+1].getOccupante());
            }
        }

        // Basso
        if( y - 1 >= 0 && mat[ x ][ y - 1 ].eOccupato() ){
            if( !mat[ x ][ y - 1 ].getOccupante().getColore().equals( colore ) && mat[ x ][ y - 1 ].getOccupante() instanceof Re ){
                lista.add(mat[x][y-1].getOccupante());
            }
        }

        // In Alto A Destra
        if( x + 1 <= MAXLENGTH && y + 1 <= MAXLENGTH && mat[ x + 1 ][ y + 1 ].eOccupato() ){
            if( !mat[ x + 1 ][ y + 1 ].getOccupante().getColore().equals( colore ) && mat[ x + 1 ][ y + 1 ].getOccupante() instanceof Re ){ 
                lista.add(mat[x+1][y+1].getOccupante());
            }
        }

        // In Alto A Sinistra
        if( x - 1 >= 0 && y + 1 <= MAXLENGTH && mat[ x - 1 ][ y + 1 ].eOccupato() ){
            if( !mat[ x - 1 ][ y + 1 ].getOccupante().getColore().equals( colore ) && mat[ x - 1 ][ y + 1 ].getOccupante() instanceof Re ){ 
                lista.add(mat[x-1][y+1].getOccupante());
            }
        }

        // In Basso A Destra
        if( x + 1 <= MAXLENGTH && y - 1 >= 0 && mat[ x + 1 ][ y - 1 ].eOccupato() ){
            if( !mat[ x + 1 ][ y - 1 ].getOccupante().getColore().equals( colore ) && mat[ x + 1 ][ y - 1 ].getOccupante() instanceof Re ){      
                lista.add(mat[x+1][y-1].getOccupante());
            }
        }

        // In Basso A Sinistra
        if( x - 1 >= 0 && y - 1 >= 0 && mat[ x - 1 ][ y - 1 ].eOccupato() ){         
            if( !mat[ x - 1 ][ y - 1 ].getOccupante().getColore().equals( colore ) && mat[ x - 1 ][ y - 1 ].getOccupante() instanceof Re ){   
                lista.add(mat[x-1][y-1].getOccupante());
            }
        }
        
        return lista;
        
        
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
    }
    
    
    private boolean percorsoTorre(Spazio s,int x,int y){
        Pezzo p;
        int xp=s.getX();
        int yp=s.getY();
        int temp;
        p= s.getOccupante();
        //vedo se la posizione in cui spostarsi è valida
        if (x == xp) {
            temp = yp;
            
            if (temp < y) {
                //se c'è un pezzo che ostruisce il passaggio
                temp++;
                while (temp < y){
                    if (m[x][temp].eOccupato()) {
                        return false;
                    }
                    temp++;
                }
                
            }
            else{
                temp--;
                while (temp > y){
                    
                    if (m[x][temp].eOccupato()) {
                        return false;
                    }
                    temp--;
                }  
               
            }
            return true;
        }

        if (y == yp) {
            temp = xp;
            if (temp< y) {
                //se c'è un pezzo che ostruisce il passaggio
                temp++;
                while (temp < x){
                    if (m[temp][y].eOccupato()) {
                        return false;
                    }
                    temp++;
                }
                
            }
            else{
                temp--;
                while (temp > x){
                    if (m[temp][y].eOccupato()) {
                        return false;
                    }
                    temp--;
                }  
               
            }
            return true;
        }
        return false;
    }
    
    /**
     * percorso dell'alfiere
     * @param s posizione alfiere
     * @param x 
     * @param y
     * @return 
     */
    private boolean percorsoAlfiere(Spazio s,int x,int y){
        Pezzo p;
        int temp1,temp2;
        int xp=s.getX();
        int yp=s.getY();
        p=s.getOccupante();
        //controllo validità posizione
            temp1=xp;
            temp2=yp;
            //primo quadrante e terzo quadrante
            if(((double)(x-xp)/(y-yp))==1){
                //primo quadrante
                if(xp<x){
                    temp1++;
                    temp2++;
                    while(temp1<x){//comprende anche il caso delle y
                        if(m[temp1][temp2].eOccupato())
                            return false;
                        temp1++;
                        temp2++;
                    }
                    
                }
                //terzo quadrante
                else if(xp>x){
                    temp1--;
                    temp2--;
                    while(temp1<x){//comprende anche il caso delle y
                        if(m[temp1][temp2].eOccupato())
                            return false;
                        temp1--;
                        temp2--;
                    } 
                }
                return true;
            }
            
            //secondo quadrante e quarto quadrante
            if(((double)(x-xp)/(y-yp))==-1){
                if(xp<x){
                    temp1++;
                    temp2--;
                    while(temp1<x){//comprende anche il caso delle y
                        if(m[temp1][temp2].eOccupato())
                            return false;
                        temp1++;
                        temp2--;
                    }
                }
                else if(xp>x){
                    temp1--;
                    temp2++;
                    while(temp1>x){//comprende anche il caso delle y
                        if(m[temp1][temp2].eOccupato())
                            return false;
                        temp1--;
                        temp2++;
                    }
                }
                return true;
            }
            return false;  
    }
    
    /**
     * Determina in quale posizione può essere spostato il pezzo
     * @param s oggetto Spazio sappresentante l'attuale casella del pezzo preso in esame
     * @param x ascissa della casella da controllare
     * @param y ordinata della casella da controllare
     * @return true se può essere spostato in quella casella
     */
    public boolean spostabileIn(Spazio s,int x, int y){
        
        //System.err.println("entro in spostabileIn() spazio posizionato da "+s.getX()+" "+s.getY()+" in "+x+" "+y);
        disegnaMatriceSpaziOccupati();
        int xp=s.getX();
        int yp=s.getY();
        int temp;//vriabile temporale per torre
        int temp1;//variabile temèorale per alfiere
        int temp2;// " "
        
        Pezzo p=s.getOccupante();
        //devo controllare se nella posizione finale c'è uno spazio libero o un pezzo del colore opposto e poi
        //tutti gli spazi intermedi x tuti i tipi di pezzo tranne il cavallo
        
        
        //caso non considerato nei cicli sotto
        if(x==xp && y==yp)
            return true;
        
        
        //controllo se la posizione finale è vuota o contiene un pezzo del colore opposto
        //(non posso spostarmi in un locazione con un pezzo dello stesso colore)
        //System.err.println(" occupato in 6,5:"+m[6][5].eOccupato());
        if(m[x][y].eOccupato() ){
            //System.err.println("Occupato in 6,5 da: "+m[6][5].getOccupante()+" occupato:"+m[6][5].eOccupato());
            if(!m[x][y].getOccupante().getColore().equals(s.getOccupante().getColore())){
                //divido i controlli in base al pezzo
                if (p instanceof Torre) {
                    System.err.println("chiamo percorsoTorre");
                    System.err.println(percorsoTorre(s, x, y));
                    return percorsoTorre(s, x, y);
                }
                if (p instanceof Alfiere) {
                    System.err.println("chiamo percorsoAlfiere");
                    return percorsoAlfiere(s, x, y);
                }

                if (p instanceof Cavallo) {
                    System.err.println("chiamo percorsoCavallo");
                    //caso base per la verifica successiva
                    if  (xp != x && yp != y) {
                    //funzione di verifica per la correttezza della posizione
                        //(modulo della somma dei 2 delta=3)
                        if (abs((double) x - xp) + abs((double) y - yp) == 3) {
                            return true;
                        }
                    }
                    return false;
                }

                if (p instanceof Regina) {
                    System.err.println("chiamo percorsoRegina");
                    return percorsoAlfiere(s, x, y) || percorsoTorre(s, x, y);
                }

                if (p instanceof Pedone) {
                    System.err.println("chiamo percorsoPedone");
                
                    return percorsoPedone(m[xp][yp], x, y, m);
                }

                if (p instanceof Re) {

                    //escludo il caso base
                    if (x != xp || y != yp) {
                    //posizioni adiacenti al re
                        if ((x == xp + 1 || x == xp - 1 || x == xp) && (y == yp + 1 || y == yp - 1 || y == yp)) {
                            System.err.println("posizione adiacente al re");
                            if (!m[x][y].eOccupato()
                                || (m[x][y].eOccupato()
                                && !m[x][y].getOccupante().getColore().equals(p.getColore()))) {
                                return true;
                            }
                        }
                        System.err.println("DEBUG: posizione non adiacente al re");
                    //arrocco
                        //devo aggiungere la condizione
                        //secondo la quale le posizioni tra il re e la torre non sono sotto scacco
                        //re mai mosso
                        if (!((Re) p).mosso()) {
                            System.err.println("RE: re mai mosso (x arrocco)");
                            if (x == xp + 2) {
                                if (m[7][y].eOccupato() && m[7][y].getOccupante() instanceof Torre ) {
                                    if (!((Torre) m[7][y].getOccupante()).mosso()) {
                                        if (!m[5][y].eOccupato() && !m[6][y].eOccupato()) {
                                            return true;
                                        }
                                    }
                                }
                            }
                            if (x == xp - 2) {
                                if (m[0][y].eOccupato() && m[0][y].getOccupante() instanceof Torre) {
                                    if (!((Torre) m[0][y].getOccupante()).mosso()) {
                                        if (!m[1][y].eOccupato()
                                            && !m[2][y].eOccupato()
                                            && !m[3][y].eOccupato()) {
                                            return true;
                                        }
                                    }
                                }
                            }
                        }
                    }
                    return false;
                }
            }
        }
        if(!m[x][y].eOccupato()){
            //divido i controlli in base al pezzo
            if (p instanceof Torre) {
                System.err.println("chiamo percorsoTorre");
                System.err.println(percorsoTorre(s, x, y));
                return percorsoTorre(s, x, y);
            }
            if (p instanceof Alfiere) {
                System.err.println("chiamo percorsoAlfiere");
                return percorsoAlfiere(s, x, y);
            }

            if (p instanceof Cavallo) {
                System.err.println("chiamo percorsoCavallo");
                //caso base per la verifica successiva
                if (xp != x && yp != y) {
                //funzione di verifica per la correttezza della posizione
                    //(modulo della somma dei 2 delta=3)
                    if (abs((double) x - xp) + abs((double) y - yp) == 3) {
                        return true;
                    }
                }
                return false;
            }

            if (p instanceof Regina) {
                System.err.println("chiamo percorsoRegina");
                return percorsoAlfiere(s, x, y) || percorsoTorre(s, x, y);
            }

            if (p instanceof Pedone) {
                System.err.println("chiamo percorsoPedone");
                
                return percorsoPedone(m[xp][yp], x, y, m);
            }

            if (p instanceof Re) {

                //escludo il caso base
                if (x != xp || y != yp) {
                    //posizioni adiacenti al re
                    if ((x == xp + 1 || x == xp - 1 || x == xp) && (y == yp + 1 || y == yp - 1 || y == yp)) {
                        System.err.println("posizione adiacente al re");
                        if (!m[x][y].eOccupato()
                                || (m[x][y].eOccupato()
                                && !m[x][y].getOccupante().getColore().equals(p.getColore()))) {
                            return true;
                        }
                    }
                    System.err.println("DEBUG: posizione non adiacente al re");
                //arrocco
                    //devo aggiungere la condizione
                    //secondo la quale le posizioni tra il re e la torre non sono sotto scacco
                    //re mai mosso
                    if (!((Re) p).mosso()) {
                        System.err.println("RE: re mai mosso (x arrocco)");
                        if (x == xp + 2) {
                            if (m[7][y].eOccupato() && m[7][y].getOccupante() instanceof Torre ) {
                                if (!((Torre) m[7][y].getOccupante()).mosso()) {
                                    if (!m[5][y].eOccupato() && !m[6][y].eOccupato()) {
                                        return true;
                                    }
                                }
                            }
                        }
                        if (x == xp - 2) {
                            if (m[0][y].eOccupato() && m[0][y].getOccupante() instanceof Torre) {
                                if (!((Torre) m[0][y].getOccupante()).mosso()) {
                                    if (!m[1][y].eOccupato()
                                            && !m[2][y].eOccupato()
                                            && !m[3][y].eOccupato()) {
                                        return true;
                                    }
                                }
                            }
                        }
                    }
                }
                return false;
            }
        }
        return false;
    }
    
    /**
     * Determina in quale posizione può essere spostato il pezzo
     * @param s oggetto Spazio sappresentante l'attuale casella del pezzo preso in esame
     * @param x ascissa della casella da controllare
     * @param y ordinata della casella da controllare
     * @param matrix matrice della scacchiera
     * @return true se può essere spostato in quella casella
     */
    public boolean spostabileIn(Spazio s,int x, int y,Spazio[][] matrix){
        //System.err.println("entro in spostabileIn() p,x,y");
        Pezzo p=s.getOccupante();
        int xp=s.getX();
        int yp=s.getY();
        int temp;//vriabile temporale per torre
        int temp1;//variabile temèorale per alfiere
        int temp2;// " "
        
        //devo controllare se nella posizione finale c'è uno spazio libero o un pezzo del colore opposto e poi
        //tutti gli spazi intermedi x tuti i tipi di pezzo tranne il cavallo
        
        
        //caso non considerato nei cicli sotto
        if(x==xp && y==yp)
            return true;
        
        
        //controllo se la posizione finale è vuota o contiene un pezzo del colore opposto
        //(non posso spostarmi in un locazione con un pezzo dello stesso colore)
        if(!s.eOccupato() || !s.getOccupante().getColore().equals(p.getColore())){
            //divido i controlli in base al pezzo
            if (p instanceof Torre) {
                System.err.println("chiamo percorsoTorre");
                System.err.println(percorsoTorre(s, x, y));
                return percorsoTorre(s, x, y);
            }
            if (p instanceof Alfiere) {
                return percorsoAlfiere(s, x, y);
            }

            if (p instanceof Cavallo) {
                //caso base per la verifica successiva
                if (xp != x && yp != y) {
                //funzione di verifica per la correttezza della posizione
                    //(modulo della somma dei 2 delta=3)
                    if (abs((double) x - xp) + abs((double) y - yp) == 3) {
                        return true;
                    }
                }
                return false;
            }

            if (p instanceof Regina) {
                return percorsoAlfiere(s, x, y) || percorsoTorre(s, x, y);
            }

            if (p instanceof Pedone) {
                //System.err.println("chiamo percorsoPedone");
                //matrice.toString();
                return percorsoPedone(matrix[xp][yp], x, y, matrix);
            }

            if (p instanceof Re) {

            //escludo il caso base
                if (x != xp || y != yp) {
                    //posizioni adiacenti al re
                    if ((x == xp + 1 || x == xp - 1 || x == xp) && (y == yp + 1 || y == yp - 1 || y == yp)) {
                        System.err.println("posizione adiacente al re");
                        if (!matrix[x][y].eOccupato()
                                || (matrix[x][y].eOccupato()
                                && !matrix[x][y].getOccupante().getColore().equals(p.getColore()))) {
                            return true;
                        }
                    }
                    System.err.println("DEBUG: posizione non adiacente al re");
                //arrocco
                    //devo aggiungere la condizione
                    //secondo la quale le posizioni tra il re e la torre non sono sotto scacco
                    //re mai mosso
                    if (!((Re) p).mosso()) {
                        if (x == xp + 2) {
                            if (matrix[7][y].eOccupato() && matrix[7][y].getOccupante() instanceof Torre ) {
                                if (!((Torre) matrix[7][y].getOccupante()).mosso()) {
                                    if (!matrix[5][y].eOccupato() && !matrix[6][y].eOccupato()) {
                                        return true;
                                    }
                                }
                            }
                        }
                        if (x == xp - 2) {
                            if (matrix[0][y].eOccupato() && matrix[0][y].getOccupante() instanceof Torre) {
                                if (!((Torre) matrix[0][y].getOccupante()).mosso()) {
                                    if (!matrix[1][y].eOccupato()
                                            && !matrix[2][y].eOccupato()
                                            && !matrix[3][y].eOccupato()) {
                                        return true;
                                    }
                                }
                            }
                        }
                    }
                }
                return false;
            }
        }
        return false;
    }
    
    private boolean percorsoPedone(Spazio s,int x,int y,Spazio[][]matrice){
        Pezzo p;
        int xp=s.getX();
        int yp=s.getY();
        p=s.getOccupante();
        
        boolean test;
      
        
        if(!matrice[x][y].eOccupato()){
                System.err.println("percorsoPedone()");
                //un pedone può spostari solo in avanti su celle vuote
                if(x==xp){
                    if(p.getColore() instanceof Nero){
                        //si è già mosso
                        if(((Pedone) p).mosso()){
                            if(y==yp+1)
                                return true;
                        }
                        //p non si è mai mosso
                        else {
                            if(y==yp+1 || y==yp+2){
                                return true;
                            }
                        }    
                    }
                    //Bianco
                    else {
                        //il pedone si è già mosso
                        if(((Pedone)s.getOccupante()).mosso()){
                            
                            test = ((Pedone)p).mosso();
                            System.out.println("Valore Test 1: " + test);
                            if((y==yp-1) && !matrice[x][y].eOccupato())
                                return true;
                        
                        } else if(y==yp-1 || y==yp-2){ //p non si è mai mosso
                            
                            test = ((Pedone)s.getOccupante()).mosso();
                            System.out.println("Valore Test 2: " + test);
                            if(!matrice[x][y].eOccupato())
                                return true;
                        }
                    }
                }
                
                return false;
            }
            //posizione finale occupata da un pezzo avversario
            else{
                //posizione consentita nel range orizzontale
                if(x==xp+1 || x==xp-1){
                    if(p.getColore() instanceof Nero){
                        if(y==yp+1)
                            return true;
                        return false;
                    }
                    if(p.getColore() instanceof Bianco){
                        if(y==yp-1)
                            return true;
                        return false;
                    }
                }
                return false;
            }
        
    }    
    
    
    //passa come parametro il colore considerato != colore Re
    /**
     * Questo metodo è diviso in più parti
     * Controlla ogni casella sulla m e definisce quali pezzi possono
     * essere spostati in quella posizione
     * @param mat matrice della scacchiera
     * @param s casella da esaminare
     * @param c turno corrente
     * @return lista dei pezzi spostabili in quella posizione
     */
    public LinkedList<Spazio> getPezziSpostabiliQui(Spazio[][] mat,Spazio s,Colore c){
        LinkedList<Spazio> lista;
        lista=new LinkedList<>();
        Spazio[][] matrix=mat;
        Spazio spazio=s;
        Colore colore=c;
        int x=spazio.getX();
        int y=spazio.getY();
        for(int j=0;j<8;j++){
            for(int i=0;i<8;i++){
                if(matrix[i][j].eOccupato())
                    if(matrix[i][j].getOccupante().getColore().equals(colore))
                        if(spostabileIn(matrix[i][j],x, y,matrix));  
                            lista.add(matrix[i][j]);
            }
        }
        return lista;
    }
    
    /**
     * Questo metodo è diviso in più parti
     * Controlla ogni casella sulla m e definisce quali pezzi possono
     * @param s spazio da esaminare
     * @param c turno corrente
     * @return lista dei pezzi spostabili in quella posizione
     */
    public LinkedList<Spazio> getPezziSpostabiliQui(Spazio s,Colore c){
        LinkedList<Spazio> lista;
        lista=new LinkedList<>();
        
        Spazio spazio=s;
        Colore colore=c;
        int x=spazio.getX();
        int y=spazio.getY();
        for(int j=0;j<8;j++){
            for(int i=0;i<8;i++){
                if(m[i][j].eOccupato())
                    if(m[i][j].getOccupante().getColore().equals(colore))
                        if(spostabileIn(m[i][j],x, y));  
                            lista.add(m[i][j]);
            }
        }
        return lista;
    }
    
    /**
     * Controlla quali pezzi della matrice impediscono lo scacco matto
     * @param xRe ascissa della casella contenente il re
     * @param yRe ordinata della casella contenente il re
     * @param matrice matrice della scacchiera
     * @param turno turno corrente
     * @return una lista con gli scacchi che salvano il re
     */
    public LinkedList<Spazio> getListaPezziChePrevengonoScacco(int xRe,int yRe,Spazio[][] matrice,Colore turno){
        //MatriceDeiPezzi originale=matrice;
        Spazio[][] originale=coppiaMatrice(matrice);
        LinkedList<Spazio> listaAttaccanti=new LinkedList<>();
        LinkedList<Spazio> listaSalvatori=new LinkedList<>();
        Spazio re= originale[xRe][yRe];
        System.err.println("Sono in getListaPezziChePrevengonoScacco");
        disegnaMatriceSpaziOccupati();
        int[][] matricePosizioni=new int[8][8];
        int[][] percorsoAttaccante;
        boolean next;
        //azzero la matrice per sicurezza
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                matricePosizioni[i][j]=0;
            }
        }
        System.err.println("GetLista... inizio");
        disegnaMatriceSpaziOccupati(originale);
        //scorro la m
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){         
                if(j!=xRe && i!=yRe)
                    //prendo gli spazi non vuoti
                    if(originale[j][i].eOccupato())
                        //prendo tutti i pezzi del colore del turno corrente
                        if(originale[j][i].getOccupante().getColore().equals(turno))
                            //setto a 1 le posizioni dove i pezzi sono del colore giusto
                            matricePosizioni[j][i]=1;
            }
        }
        
        //guardo i pezzi del colore opposto che possono fare scacco al re
        //(chi può spostarsi nella sua locazione)
        //li salvo in una lista
        if(re.getOccupante().getColore() instanceof Bianco)
            listaAttaccanti=getPezziSpostabiliQui(originale,originale[xRe][yRe],new Nero());
        else
            listaAttaccanti=getPezziSpostabiliQui(originale,originale[xRe][yRe],new Bianco());
        
        //per ogni pezzo mio vedo chi può neutralizzarli tutti contemporaneamente e li salvo in un'altra lista
        //ritorno la lista coi pezzi che salvano il re

        //forse utilizzo l'algoritmo + efficace che non usa combinazioni inutili
        
        /*si può salvare il re cosi:
            1)Il re può spostarsi (si salva da solo)
            2)Mi metto tra l'attaccante e il Re (un solo attaccante e l'attaccante non è un cavallo)
            3)Mangio l'attaccante (un solo attaccante)
        Per fare il punto 2 potrei salvarmi le posizioni intermedie tra gli attaccanti e il re (esclusa la torre)    
        
        */
        //1)
        if(reSiSalvaDaScacco(re,originale))
            listaSalvatori.add(re);
        System.err.println("GetLista... dopo resisalvadascacco");
        //disegnaMatriceSpaziOccupati(originale);
        //i casi 2 e 3 hanno senso solo se c'è un solo attaccante
        //eper mettere questa condizione controllo se la lista contiene un solo oggetto
        //confrontando il primo oggetto della lista con l'ultimo
        //condizione vera solo se la lista contiene un solo oggetto
        if(listaAttaccanti.getFirst().equals(listaAttaccanti.getLast())){
            
            //2)
            //non ha senso la ricerca del percorso quando l'attaccante è un cavallo o un pedone
            if(!((listaAttaccanti.getFirst().getOccupante() instanceof Cavallo)||(listaAttaccanti.getFirst().getOccupante() instanceof Pedone))){
                percorsoAttaccante=getPercorsoAttaccante(listaAttaccanti.getFirst(),re);
                //scorro la m
                for(int i=0;i<8;i++){
                    for(int j=0;j<8;j++){         
                        
                        //prendo gli spazi non vuoti
                        if(originale[j][i].eOccupato())
                            //prendo tutti i pezzi del colore del turno corrente
                            if(originale[j][i].getOccupante().getColore().equals(turno))
                                if(matricePosizioni[i][j]==1){
                                    next=false;
                                    for(int k=0;k<8;k++){
                                        for(int p=0;p<8;p++){
                                            if(percorsoAttaccante[k][p]==1 && next==false){
                                                if(spostabileIn(matrice[i][j],k,p)){
                                                    listaSalvatori.add(matrice[i][j]);
                                                    next=true;
                                                }
                                            }
                                        }
                                    }
                                }
                                
                    }
                }
            }
        //...
        //NON funziona se tra gli attaccanti c'è un cavallo
        //guardo il tipo di pezzo dell'attaccante o degli attaccanti
        //sommo le 2 matrici ricevute avendo tutte le posizioni che intercorrono tra i 2 attaccanti
        //le posizioni di intersezione saranno le posizioni nelle quali spostarsi per evitare lo scacco
        //il numero nelle celle da usare deve essere uguale al numero di attaccanti
        //nel caso contrario il metodo 2 non può funzionare
        //mi ricavo le posizioni intermedie
        //System.err.println("GetLista... dopo step 2");
        //disegnaMatriceSpaziOccupati(originale);
        
            //3)
            for(int i=0;i<8;i++){
                for(int j=0;j<8;j++){
                    if(matricePosizioni[i][j]==1)
                        if(spostabileIn(matrice[i][j],listaAttaccanti.getFirst().getX(),listaAttaccanti.getFirst().getY(),m))
                            listaSalvatori.add(matrice[i][j]);
                }
            }
            
        
        }
        System.err.println("GetLista... fine");
        disegnaMatriceSpaziOccupati(originale);
        System.err.println("elementi nella lista di salvatori: "+listaSalvatori.size());
        listaSalvatori.stream().forEach((elemento) -> {
            System.err.println(elemento.toString());
        });
        return listaSalvatori;
    
    }
    /**
     * Ritorna una matrice con segnate le posizioni di dove si trovano i pezzi in grado di salvare il re
     * @param xRe ascissa del re
     * @param yRe ordinata del re
     * @param matrice matrice della scacchiera
     * @param turno turno corrente (colore)
     * @return 
     */
    public int[][] getMatricePezziChePrevengonoScacco(int xRe,int yRe,Spazio[][] matrice,Colore turno){
        int matriceRisultato[][]=new int[8][8];
        LinkedList<Spazio> lista=getListaPezziChePrevengonoScacco(xRe,yRe,matrice,turno);
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                matriceRisultato[i][j]=0;
            }
        }
        for(Spazio pezzo:lista){
            matriceRisultato[pezzo.getX()][pezzo.getY()]=1;
        }
        return matriceRisultato;
    }
    
    /**
     * legge la scacchiera e valuta se il re è in grado di salvarsi dallo scacco
     * @param s dove si trova il re
     * @param matrice scacchiera
     * @return true se si salva
     */
    private boolean reSiSalvaDaScacco(Spazio s,Spazio[][] matrice){
        Re re=(Re) s.getOccupante();
        Spazio[][] originale=coppiaMatrice(matrice);
        Spazio[][] matSimulata;
        int x=s.getX();
        int y=s.getY();
        
        boolean scacco=true;
        //vedo se l'attaccante è adiacente al re? (non posso farlo senza avere la lista degli attaccanti[elaborioso])
        //sposto il re nelle locazioni nelle quali può andare e controllo se rimane la situazione di scacco
        //System.err.println("ReSiSalva... inizio");
        //disegnaMatriceSpaziOccupati(originale);
        //provo in alto a sinistra
        if(x>0 && y>0){
            if(!matrice[x-1][y-1].eOccupato()){
                matSimulata=coppiaMatrice(matrice);
            
                spostaPezzo(matSimulata[x][y], x-1, y-1, matSimulata);
                //matSimulata[x-1][y-1].inizializzaSpazio(new Re(re.getColore()),x-1,y-1);
                //matSimulata[x][y]= new Spazio(x,y);
                //matSimulata[x][y].setOccupato(false);
                //System.err.println("ReSiSalva... dopo alto sinistra matSimulata");
                //disegnaMatriceSpaziOccupati(matSimulata);
                if(controlloScacco(x-1,y-1,matSimulata)==false)
                    scacco=true;
                else
                    return true;
            }
            if(matrice[x-1][y-1].eOccupato()){
                if(!matrice[x-1][y-1].getOccupante().getColore().equals(re.getColore())){
                    matSimulata=coppiaMatrice(matrice);
            
                    spostaPezzo(matSimulata[x][y], x-1, y-1, matSimulata);
                    if(controlloScacco(x-1,y-1,matSimulata)==false)
                        scacco=true;
                    else
                        return true;
                }
            }
        }
        //System.err.println("ReSiSalva... dopo alto sinistra");
        //disegnaMatriceSpaziOccupati(originale);
        //provo in alto
        if(y>0){
            if(!matrice[x][y-1].eOccupato()){
                matSimulata=coppiaMatrice(matrice);
                //matSimulata[x][y-1].inizializzaSpazio(new Re(re.getColore()),x,y-1);
                //matSimulata[x][y]= new Spazio(x,y);
                spostaPezzo(matSimulata[x][y], x, y-1, matSimulata);
                //System.err.println("ReSiSalva... dopo alto matSimulata");
                //disegnaMatriceSpaziOccupati(matSimulata);
                if(controlloScacco(x,y-1,matSimulata)==false)
                    scacco=true;
                else
                    return true;
            }
            if(matrice[x][y-1].eOccupato()){
                if(!matrice[x][y-1].getOccupante().getColore().equals(re.getColore())){
                    matSimulata=coppiaMatrice(matrice);
                    //matSimulata[x][y-1].inizializzaSpazio(new Re(re.getColore()),x,y-1);
                    //matSimulata[x][y]= new Spazio(x,y);
                    spostaPezzo(matSimulata[x][y], x, y-1, matSimulata);
                    //System.err.println("ReSiSalva... dopo alto matSimulata");
                    //disegnaMatriceSpaziOccupati(matSimulata);
                    if(controlloScacco(x,y-1,matSimulata)==false)
                        scacco=true;
                    else
                        return true;
                }
            }
            
        }
        //System.err.println("ReSiSalva... dopo alto");
        //disegnaMatriceSpaziOccupati(originale);
        //provo in alto a destra
        if(x<7 && y>0){ 
            if(!matrice[x+1][y-1].eOccupato()){
                matSimulata=coppiaMatrice(matrice);
                //matSimulata[x][y-1].inizializzaSpazio(new Re(re.getColore()),x+1,y-1);
                //matSimulata[x][y]= new Spazio(x,y);
                spostaPezzo(matSimulata[x][y], x+1, y-1, matSimulata);
                if(controlloScacco(x+1,y-1,matSimulata)==false)
                    scacco=true;
                else
                    return true;
                }
            if(matrice[x+1][y-1].eOccupato()){
                if(!matrice[x+1][y-1].getOccupante().getColore().equals(re.getColore())){
                    matSimulata=coppiaMatrice(matrice);
                    //matSimulata[x][y-1].inizializzaSpazio(new Re(re.getColore()),x+1,y-1);
                    //matSimulata[x][y]= new Spazio(x,y);
                    spostaPezzo(matSimulata[x][y], x+1, y-1, matSimulata);
                    if(controlloScacco(x+1,y-1,matSimulata)==false)
                        scacco=true;
                    else
                        return true;
                
                }
            }
        }
        //System.err.println("ReSiSalva... dopo alto destra");
        //disegnaMatriceSpaziOccupati(originale);
        //provo a sinistra
        if(x>0){ 
            if(!matrice[x-1][y].eOccupato()){
                matSimulata=coppiaMatrice(matrice);
                //matSimulata[x-1][y].inizializzaSpazio(new Re(re.getColore()),x-1,y);
                //matSimulata[x][y]= new Spazio(x,y);
                spostaPezzo(matSimulata[x][y], x-1, y, matSimulata);
                if(controlloScacco(x-1,y,matSimulata)==false)
                    scacco=true;
                else
                    return true;
            }
            if(matrice[x-1][y].eOccupato()){
                if(!matrice[x-1][y].getOccupante().getColore().equals(re.getColore())){
                    matSimulata=coppiaMatrice(matrice);
                    //matSimulata[x-1][y].inizializzaSpazio(new Re(re.getColore()),x-1,y);
                    //matSimulata[x][y]= new Spazio(x,y);
                    spostaPezzo(matSimulata[x][y], x-1, y, matSimulata);
                    if(controlloScacco(x-1,y,matSimulata)==false)
                        scacco=true;
                    else
                        return true;
                }
            }
        }
        
        //System.err.println("ReSiSalva... dopo destra");
        //disegnaMatriceSpaziOccupati(originale);
        //provo a destra
        if(x<7){ 
            if(!matrice[x+1][y].eOccupato()){
                matSimulata=coppiaMatrice(matrice);
                //matSimulata[x-1][y].inizializzaSpazio(new Re(re.getColore()),x-1,y);
                //matSimulata[x][y]= new Spazio(x,y);
                spostaPezzo(matSimulata[x][y], x+1, y, matSimulata);
                if(controlloScacco(x+1,y,matSimulata)==false)
                    scacco=true;
                else
                    return true;
            }
            if(matrice[x+1][y].eOccupato()){
                if(!matrice[x+1][y].getOccupante().getColore().equals(re.getColore())){
                    matSimulata=coppiaMatrice(matrice);
                    //matSimulata[x+1][y].inizializzaSpazio(new Re(re.getColore()),x-1,y);
                    //matSimulata[x][y]= new Spazio(x,y);
                    spostaPezzo(matSimulata[x][y], x+1, y, matSimulata);
                    if(controlloScacco(x+1,y,matSimulata)==false)
                        scacco=true;
                    else
                        return true;
                }
            }
        }
        //System.err.println("ReSiSalva... dopo sinistra");
        //disegnaMatriceSpaziOccupati(originale);
        //provo in basso a sinistra
        if(y<7 && x>0){ 
            if(!matrice[x-1][y+1].eOccupato()){
                matSimulata=coppiaMatrice(matrice);
                spostaPezzo(matSimulata[x][y], x-1, y+1, matSimulata);
                if(controlloScacco(x-1,y+1,matSimulata)==false)
                    scacco=true;
                else
                    return true;
            }
            if(matrice[x-1][y+1].eOccupato()){
                if(!matrice[x-1][y+1].getOccupante().getColore().equals(re.getColore())){
                    matSimulata=coppiaMatrice(matrice);
                    spostaPezzo(matSimulata[x][y], x-1, y+1, matSimulata);
                    if(controlloScacco(x-1,y+1,matSimulata)==false)
                        scacco=true;
                    else
                        return true;
                }
            }
        }
        //System.err.println("ReSiSalva... dopo basso sinistra");
        //&disegnaMatriceSpaziOccupati(originale);
        //provo in basso
        if(y<7){ 
            if(!matrice[x][y+1].eOccupato()){
                matSimulata=coppiaMatrice(matrice);
                spostaPezzo(matSimulata[x][y], x, y+1, matSimulata);
                if(controlloScacco(x,y+1,matSimulata)==false)
                    scacco=true;
                else
                    return true;
            }
            if(matrice[x][y+1].eOccupato()){
                if(!matrice[x][y+1].getOccupante().getColore().equals(re.getColore())){
                    matSimulata=coppiaMatrice(matrice);
                    spostaPezzo(matSimulata[x][y], x, y+1, matSimulata);
                    if(controlloScacco(x,y+1,matSimulata)==false)
                        scacco=true;
                    else
                        return true;
                }
            }
        }
        //System.err.println("ReSiSalva... dopo basso");
        //disegnaMatriceSpaziOccupati(originale);
        //provo in basso a destra
        if(y<7 && x<7){ 
            if(!matrice[x+1][y+1].eOccupato()){
                matSimulata=coppiaMatrice(matrice);
                spostaPezzo(matSimulata[x][y], x+1, y+1, matSimulata);
                if(controlloScacco(x+1,y+1,matSimulata)==false)
                    scacco=true;
                else
                    return true;
            }
            if(matrice[x+1][y+1].eOccupato()){
                if(!matrice[x+1][y+1].getOccupante().getColore().equals(re.getColore())){
                    matSimulata=coppiaMatrice(matrice);
                    spostaPezzo(matSimulata[x][y], x+1, y+1, matSimulata);
                    if(controlloScacco(x+1,y+1,matSimulata)==false)
                        scacco=true;
                    else
                        return true;
                }
            }
        }
        //System.err.println("ReSiSalva... prima di return false(dopo basso a destra)");
        //disegnaMatriceSpaziOccupati(originale);
        return false; 
    }
    
    /**
     * aggiorna l'interfaccia grafica
     * @param i l'interfaccia da sostituire
     */
    public void setInterfacciaGrafica( InterfacciaGrafica i ){
        
        ig = i;
    
    }
    
    /**
     * aggiorna il parametro di calsse m (matrice che rappresenta la scacchiera)
     * usando la matrice passata come parametro
     * @param matrice matrice della scacchiera
     */
    public void setMatrice(Spazio[][] matrice){
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                m[i][j]=new Spazio(matrice[i][j]);
            }
        }
    }
    
/**
 * metodo che lacia lo scacco matto se il re è stato mangiato (usato per il debug)
 * @param turnoCorrente turno corrente (di tipo colore)
 * @return 
 */
    public boolean scaccoMatto(Colore turnoCorrente) {
        if(turnoCorrente instanceof Bianco){
            return !(getSpazioReNero().eOccupato());      
        }
        else{
            return !getSpazioReBianco().eOccupato();
        }
    }
    
    /**
     * cerca il percorso dei pezzi che minacciano il re
     * @param att casella dove si trova l'attaccante
     * @param r caselle dove si può muovere l'attaccante
     * @return una matrice d interi che rappresenta il percorso dell'attaccante
     */
    private int[][] getPercorsoAttaccante(Spazio att,Spazio r){
        Pezzo attaccante=att.getOccupante();
        Pezzo re=r.getOccupante();
        int[][] matriceRisultato=new int[8][8];
        int xAttaccante=att.getX();
        int yAttaccante=att.getY();
        int temp1,temp2;
        int xRe=r.getX();
        int yRe=r.getY();
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                matriceRisultato[i][j]=0;
            }
        }
        if(attaccante instanceof Torre || attaccante instanceof Regina){
            if(xRe==xAttaccante){
                for(int i=0;i<8;i++){
                    if((i>yRe && i<yAttaccante)||(i>yAttaccante && i<yRe))
                        matriceRisultato[xRe][i]=1;
                }
            }
            else{
                for(int i=0;i<8;i++){
                    if((i>xRe && i<xAttaccante)||(i>xAttaccante && i<xRe))
                        matriceRisultato[i][yRe]=1;
                }
            }
        }
        if(attaccante instanceof Alfiere || attaccante instanceof Regina){
            temp1=xAttaccante;
            temp2=yAttaccante;
            if(xAttaccante>xRe && yAttaccante>yRe){
                temp1--;
                temp2--;
                while(temp1>xRe){
                    matriceRisultato[temp1][temp2]=1;
                    temp1--;
                    temp2--;
                }
            }
            if(xAttaccante<xRe && yAttaccante>yRe){
                temp1++;
                temp2--;
                while(temp1<xRe){
                    matriceRisultato[temp1][temp2]=1;
                    temp1++;
                    temp2--;
                }
            }
            if(xAttaccante<xRe && yAttaccante<yRe){
                temp1++;
                temp2++;
                while(temp1<xRe){
                    matriceRisultato[temp1][temp2]=1;
                    temp1++;
                    temp2++;
                }
            }
            if(xAttaccante>xRe && yAttaccante<yRe){
                temp1--;
                temp2++;
                while(temp1>xRe){
                    matriceRisultato[temp1][temp2]=1;
                    temp1--;
                    temp2++;
                }
            }
        }
        return matriceRisultato;
        
    }
    
    /**
     * questo metodo è diviso in più parti
     * cambia le cordinate di un pezzo sulla scacchiera e aggiorna la scacchiera
     * @param s oggetto di calsse Spazio contenente il pezzo
     * @param x nuova ascissa del pezzo
     * @param y nuova ordinata del pezzo
     * @param scacchiera matrice della scacchiera
     */
    public void spostaPezzo( Spazio s, int x, int y ,Spazio[][] scacchiera){
        System.err.println("Sposta pezzo");
        Pezzo p;
        int xp = s.getX();
        int yp = s.getY();
        p=s.getOccupante();
        s.setX( x );
        s.setY( y );
        
        scacchiera[ xp ][ yp ]=new Spazio( xp,yp );
        scacchiera[ x ][ y ]=new Spazio( x,y,s.getOccupante() );  
        if(scacchiera[x][y].getOccupante() instanceof Pedone){
            //((Pedone)scacchiera[x][y].getOccupante()).impostaMosso();
            
            if(!((Pedone)scacchiera[x][y].getOccupante()).mosso()){
                ((Pedone)scacchiera[x][y].getOccupante()).impostaMosso();
            }
            
            //se il pedone arriva in fondo alla scacchiera lo trasformo
            
            if( scacchiera[x][y].getOccupante().getColore() instanceof Bianco ){ // Promozione Pedone Bianco
                                    
                if( y == 0 ){ // Se Il Pedone E In Fondo Alla Scacchiera
                    ig.promozionePedone(scacchiera[x][y],new Bianco(),scacchiera);     
                    /*
                    System.err.println( "DEBUG: Promuovo Il Pedone Nell'Pezzo Scelto" );
                    scacchiera[x][y].toStringTipo();
                    PromozionePedone promozione;
                    promozione = new PromozionePedone(this,ig, scacchiera ,scacchiera[x][y], new Bianco() ,x,y); // Trasformo Il Pedone In Altro Scelto Dalla Promozione
                    promozione.start();
                    try{
                        this.wait();
                    }catch(Exception e){
                        System.err.println("Si riparte");
                    }
                    System.err.println("Pedone promosso");
                    scacchiera[x][y].toStringTipo();*/
                }
                                
            } else { // Promozione Pedone Nero
                                    
                if( y == 7 ){ // Se Il Pedone E In Fondo Alla Scacchiera
                    ig.promozionePedone(scacchiera[x][y],new Nero(),scacchiera);
                    System.err.println( "DEBUG: Promuovo Il Pedone Nel Pezzo Scelto" );
                    //PromozionePedone promozione;
                    //scacchiera[x][y].toStringTipo();
                    //scacchiera[x][y].cambiaPezzo(new Torre(new Bianco()));
                    /*
                    promozione = new PromozionePedone(this,ig, scacchiera,scacchiera[x][y], new Nero(),x,y ); // Trasformo Il Pedone In Altro Scelto Dalla Promozione
                    promozione.start();
                    try{
                        this.wait();
                    }catch(Exception e){
                        System.err.println("Si riparte");
                    }
                    System.err.println("Pedone promosso");
                    //scacchiera[x][y].toStringTipo();*/
                }
                                
            }
            
        }
        
        if(scacchiera[x][y].getOccupante() instanceof Torre){
            ((Torre)scacchiera[x][y].getOccupante()).impostaMosso();
            
            if(!((Torre)scacchiera[x][y].getOccupante()).mosso()){
                ((Torre)scacchiera[x][y].getOccupante()).impostaMosso();
            }
        }
        
        if(scacchiera[x][y].getOccupante() instanceof Re){
            
            if(!((Re)scacchiera[x][y].getOccupante()).mosso()){
                ((Re)scacchiera[x][y].getOccupante()).impostaMosso();
                if (x == xp + 2) {
                    if (m[7][y].eOccupato() && m[7][y].getOccupante() instanceof Torre ) {
                        if (!((Torre) m[7][y].getOccupante()).mosso()) {
                            if (!m[5][y].eOccupato()) {
                                        //return true;
                                spostaPezzo(m[7][y],x-1,y,scacchiera);
                            }
                        }
                    }
                }
                if (x == xp - 2) {
                    if (m[0][y].eOccupato() && m[0][y].getOccupante() instanceof Torre) {
                        if (!((Torre) m[0][y].getOccupante()).mosso()) {
                            if (!m[1][y].eOccupato() && !m[3][y].eOccupato()) {
                                        //return true;
                                        spostaPezzo(m[0][y],x+1,y,scacchiera);
                            }
                        }
                    }
                }
            }
        }
        //disegnaMatriceSpaziOccupati();
    }
    
    /**
     * questo metodo è diviso in più parti
     * cambia le cordinate di un pezzo sulla scacchiera e aggiorna la scacchiera
     * @param s oggetto di calsse Spazio contenente il pezzo
     * @param x nuova ascissa del pezzo
     * @param y nuova ordinata del pezzo
     */
    public void spostaPezzo( Spazio s, int x, int y ){
        System.err.println("Sposta pezzo");
        int xp = s.getX();
        int yp = s.getY();

        s.setX( x );
        s.setY( y );

        m[ x ][ y ]=new Spazio( x,y,s.getOccupante() );
        m[ xp ][ yp ]=new Spazio( xp,yp );
        if(s.getOccupante() instanceof Pedone){
            if(!((Pedone)s.getOccupante()).mosso()){
                ((Pedone)s.getOccupante()).impostaMosso();
            }
            
            //se il pedone arriva in fondo alla scacchiera lo trasformo
            
            if( m[x][y].getOccupante().getColore() instanceof Bianco ){ // Promozione Pedone Bianco
                                    
                if( y == 0 ){ // Se Il Pedone E In Fondo Alla Scacchiera
                                    
                    System.err.println( "DEBUG: Promuovo Il Pedone Nell'Pezzo Scelto" );
                    ig.promozionePedone(m[x][y],new Bianco(),m);
                    /*
                    PromozionePedone promozione;
                    promozione = new PromozionePedone(this,ig, m,m[x][y], new Bianco(),x,y ); // Trasformo Il Pedone In Altro Scelto Dalla Promozione
                    promozione.start();
                    try{
                        this.wait();
                    }catch(Exception e){
                        System.err.println("Si riparte");
                    }
                    System.err.println("Pedone promosso");  */             
                }
                                
            } else { // Promozione Pedone Nero
                                    
                if( y == 7 ){ // Se Il Pedone E In Fondo Alla Scacchiera
                    ig.promozionePedone(m[x][y],new Nero(),m);
                    System.err.println( "DEBUG: Promuovo Il Pedone Nel Pezzo Scelto" );
                    /*
                    PromozionePedone promozione;
                    promozione = new PromozionePedone(this,ig,m, m[x][y], new Nero(),x,y ); // Trasformo Il Pedone In Altro Scelto Dalla Promozione
                    promozione.start();
                    while(!promozione.equals(null)){
                        try {
                            this.wait(50);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(GestoreMovimenti.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }*/
                    /*
                    try{
                        this.wait();
                    }catch(Exception e){
                        System.err.println("Si riparte");
                    }*/
                    //System.err.println("Pedone promosso");               
                }
                                
            }
        }
        //disegnaMatriceSpaziOccupati();
    }
    
    /**
     * duplica la matrice che rappresenta la scacchiera
     * @param matrice la matrice da duplicare
     * @return il duplicato della matrice della scacchiera
     */
    public Spazio[][] coppiaMatrice(Spazio[][] matrice){
        Spazio[][] mat=new Spazio[8][8];
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                if(matrice[i][j].eOccupato())
                    mat[i][j]=new Spazio(i,j,matrice[i][j].getOccupante());
                else
                    mat[i][j]=new Spazio(i,j);
            }
        }
        return mat;
    }
    
    /**
     * questo metodo è diviso in più parti
     * segna sullo schermo quali spazi sono occupati
     */
    public void disegnaMatriceSpaziOccupati(){
        
        System.err.println("");
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                if(m[j][i].eOccupato())
                    System.err.print("x");
                else
                    System.err.print("_");
            }
            System.err.println("");
        }
        System.err.println("");
    }
    
    /**
     * questo metodo è diviso in più parti
     * funzione di supporto che viene chiamata per disegnare sullo schermo gli spazi 
     * occupati dalle pedine sulla scacchiera
     * @param matrice 
     */
    public void disegnaMatriceSpaziOccupati(Spazio[][] matrice){
        
        System.err.println("");
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                if(matrice[j][i].eOccupato())
                    System.err.print("x");
                else
                    System.err.print("_");
            }
            System.err.println("");
        }
        System.err.println("");
    }
    
}
