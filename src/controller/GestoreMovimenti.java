package controller;

import static java.lang.Math.*;
import java.util.LinkedList;
import model.*;
import view.*;

/**
 * La classe contiene i metodi che calcolano le posizioni e i movimenti delle 
 * varie pedine sulla scacchiera. Contiene anche il calcolo dello scacco matto.
 * @author Viktor, Michael, Gaetano
*/
public class GestoreMovimenti{
    
    /**
     * Variabile contenente il colore di un pezzo 
    */
    private final Colore turno;
    
    /**
     * Matrice di spazi che descrive una scacchiera 
    */
    private Spazio[][] m;
    
    /**
     * Collegamento GestoreMovimenti-InterfacciaGrafica
    */
    private InterfacciaGrafica ig;
    
    /**
     * Variabili contenenti le posizioni dei due Re
    */
    Spazio reBianco,reNero;
    
    /**
     * Costruttore di base senza parametri che inizializza la scacchiera
    */
    public GestoreMovimenti(){
        
        m = new Spazio[ 8 ][ 8 ]; // Creare Una Matrice Con Le Posizioni Di Default
        
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
     * Costruttore che, fornita una matrice di spazi, inizializza il primo turno e inizia il gioco
     * @param matrice - La matrice dei spazi della scacchiera
    */
    public GestoreMovimenti( Spazio[][] matrice ){
        
        turno = new Bianco();
    
    }
    
    /**
     * Metodo che ritorna la matrice m che rappresenta la scacchiera
     * @return m - La matrice rapresentante la scacchiera
    */
    public Spazio[][] getMatrice(){
        
        return m;
    
    }
    
    /**
     * Metodo che cerca dove si trova l'oggetto di classe Re del colore specificato
     * @param c - Il colore del Re da cercare
     * @return Il Re del colore specificato
     * @throws Exception nel caso in cui il Re del colore specificato non venga trovato
    */
    public Re getRe( Colore c ) throws Exception{
        
        if( c instanceof Bianco ){
            
            return getReBianco();
        
        } else {
            
            return getReNero();
        
        }
    
    }
    
    /**
     * Metodo che cerca dove si trova il Re Nero e lo ritorna
     * @return L'oggetto contenente il Re Nero se viene trovato
     * @throws Exception - Eccezzione nel caso in cui non venga trovato nessun Re Nero sulla scacchiera
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
        
        throw new Exception( "Re Nero Non Trovato" );
    
    }
    
    /**
     * Metodo che cerca lo spazio dove si trova il Re del Colore specificato
     * @param c - Il colore del Re che si vuole cercare
     * @return Lo spazio dove e collocato il Re del Colore specificato
    */
    public Spazio getSpazioRe( Colore c ){
        
        if( c instanceof Bianco ){
            
            return getSpazioReBianco();
        
        } else {
            
            return getSpazioReNero();
        
        }
    
    }
    
    /**
     * Metodo che cerca lo spazio dove si trova il Re del Colore opposto a quello specificato
     * @param c - Il colore opposto del Re che si vuole cercare
     * @return Lo spazio dove e collocato il Re del Colore opposto a quello specificato
    */
    public Spazio getSpazioReAvversario( Colore c ){
        
        if( c instanceof Bianco ){
            
            return getSpazioReNero();
        
        } else {
            
            return getSpazioReBianco();
        
        }
    
    }

    /**
     * Metodo che cerca lo spazio dove si trova il Re Nero
     * @return Lo spazio dove e collocato il Re Nero
    */
    public Spazio getSpazioReNero(){
        
        for( int i = 0; i < 8; i++ ){
            
            for( int j = 0; j < 8; j++ ){
                
                if( m[ i ][ j ].eOccupato() ){
                    
                    if( m[ i ][ j ].getOccupante() instanceof Re ){
                        
                        if( m[ i ][ j ].getOccupante().getColore() instanceof Nero ){
                            
                            return  m[ i ][ j ];
                        
                        }
                    
                    }
                
                }
            
            }
        
        }
        
        Spazio s = new Spazio();
        
        s.setOccupato( false );
        
        return s;
    
    }
    
    /**
     * Metodo che cerca lo spazio dove si trova il Re Bianco
     * @return Lo spazio dove e collocato il Re Bianco
    */
    public Spazio getSpazioReBianco(){
        
        for( int i = 0; i < 8; i++ ){
            
            for( int j = 0; j < 8; j++ ){
                
                if( m[ i ][ j ].eOccupato() ){
                    
                    if( m[ i ][ j ].getOccupante() instanceof Re ){
                        
                        if( m[ i ][ j ].getOccupante().getColore() instanceof Bianco ){
                            
                            return  m[ i ][ j ];
                        
                        }
                    
                    }
                
                }
            
            }
        
        }
        
        Spazio s = new Spazio();
        s.setOccupato( false );
        
        return s;
    
    }
    
    /**
     * Metodo che cerca dove si trova il Re Bianco e lo ritorna
     * @return L'oggetto contenente il Re Bianco se viene trovato
     * @throws Exception - Eccezzione nel caso in cui non venga trovato nessun Re Bianco sulla scacchiera
    */
    public Re getReBianco() throws Exception{
        
        for( int i = 0; i < 8; i++ ){
            
            for( int j = 0; j < 8; j++ ){
                
                if( m[ i ][ j ].eOccupato() ){
                    
                    if( m[ i ][ j ].getOccupante() instanceof Re ){
                        
                        if( m[ i ][ j ].getOccupante().getColore() instanceof Bianco ){
                            
                            return ( Re ) m[ i ][ j ].getOccupante();
                        
                        }
                    
                    }
                
                }
            
            }
        
        }
        
        throw new Exception( "Re Bianco Non Trovato" );
    
    }
    
    // PERCHE DUE VOLTE ISTANCE OF NERO ????????????????????????????????????????????????
    
    /**
     * Metodo che verifica e stabilisce i percorsi possibili sulla scacchiera di un Pedone
     * @param s - Lo spazio dove il pedone vuole muoversi
     * @return scacchiera - La matrice di interi che rappresenta i possibili movimenti del pedone
    */
    public int[][] movimentiPedone( Spazio s ){
        
        Pedone p;
        int[][] scacchiera = new int[ 8 ][ 8 ];
        int x, y;
        p = ( Pedone )s.getOccupante();
        x = s.getX();
        y = s.getY();
        
        if( p.getColore() instanceof Bianco ){

            if( !m[ x ][ y - 1 ].eOccupato() ){ // Una Casella In Alto
               
                scacchiera[ x ][ y - 1 ] = 1;
            }
            
            
            if( !m[ x ][ y - 2 ].eOccupato() && p.mosso() ){ // Due Caselle In Alto
         
                scacchiera[ x ][ y - 2 ] = 1;
            
            }
            
            if( ( x - 1 ) >= 0 ){ // In Alto A Sinistra
                
                if( m[ x - 1 ][ y - 1 ].eOccupato()  ){
                    
                    if( m[ x - 1][ y - 1 ].getOccupante().getColore() instanceof Nero ){
                        
                        scacchiera[ x - 1 ][ y - 1 ] = 1;
                    
                    }
                
                }
            
            }
            
            if( ( x + 1 ) <= 7 ){ // In Alto A Destra
                
                if( m[ x + 1 ][ y - 1 ].eOccupato() ){
                    
                    if( m[ x + 1 ][ y - 1 ].getOccupante().getColore() instanceof Nero ){
                        
                        scacchiera[ x + 1 ][ y - 1 ] = 1;
                    
                    }
                
                }
            
            }
        
        } else { // Il Pedone E Nero
            
            if( !m[ x ][ y + 1 ].eOccupato() ){ // Una Casella In Basso
              
                scacchiera[ x ][ y + 1 ] = 1;
            
            }
            
            if( !m[ x ][ y + 2 ].eOccupato() && p.mosso() ){ // Due Caselle In Bsaso
        
                scacchiera[ x ][ y + 2 ] = 1;
            
            }
            
            if( ( x - 1 ) >= 0 ){ // In Basso A Sinistra
                
                if( m[ x - 1 ][ y + 1 ].eOccupato() ){
                    
                    if( m[ x - 1 ][ y + 1 ].getOccupante().getColore() instanceof Nero ){
                        
                        scacchiera[ x + 1 ][ y + 1 ] = 1;
                    
                    }
                
                }
            
            }
            
            if( ( x + 1 ) <= 7 ){ // In Basso A Destra
                
                if( m[ x + 1 ][ y + 1 ].eOccupato() ){
                    
                    if( m[ x + 1 ][ y + 1 ].getOccupante().getColore() instanceof Nero ){
                        
                        scacchiera[ x + 1 ][ y + 1 ] = 1;
                    
                    }
                
                }
            
            }
        
        }
        
        return scacchiera;
    
    } // Fine movimentiPedone
    
    /**
     * Metodo che verifica e stabilisce i percorsi possibili sulla scacchiera di una Torre
     * @param s - Lo spazio dove la torre vuole muoversi
     * @return scacchiera - La matrice di interi che rappresenta i possibili movimenti della torre
    */
    private int[][] movimentiTorre( Spazio s ){
        
        int[][] scacchiera = new int[ 8 ][ 8 ];
        Torre torre = ( Torre ) s.getOccupante();
        int x = s.getX();
        int y = s.getY();
        int temp;
        
        if( x + 1 <= 7 ){ // Direzione Verso Destra
            
            for( temp = x + 1; temp <= 7 && !( m[ temp ][ y ].eOccupato() ); temp++ ){ // Spazi Liberi A Destra
                
                scacchiera[ temp ][ y ] = 1;
            
            }
            
            temp++; // Primo Spazio Occupato A Destra
            
            if( temp <= 7 ){ // Controllo Per Non Andare Fuori Scachiera
                
                if( m[ temp ][ y ].eOccupato() ){ // Se Lo Spazio E Occupato E Contiene Un Pezzo Del Colore Opposto
                    
                    if( !m[ temp ][ y ].getOccupante().getColore().equals( torre.getColore() ) ){
                        
                        scacchiera[ temp ][ y ] = 1;
                    
                    }
                
                }
            
            }
        
        }
        
        if( x - 1 >= 0 ){ // Direzione Verso Sinistra
            
            for( temp = x - 1; temp >= 0 && !( m[ temp ][ y ].eOccupato() ); temp-- ){ // Spazi Liberi A Sinistra
                
                scacchiera[ temp ][ y ] = 1;
            
            }
            
            temp--; // Primo Spazio Occupato A Sinistra
            
            if( temp >= 0 ){ // Controllo Per Non Andare Fuori Scachiera
                
                if( m[ temp ][ y ].eOccupato() ){ // Se Lo Spazio E Occupato E Contiene Un Pezzo Del Colore Opposto
                    
                    if( !m[ temp ][ y ].getOccupante().getColore().equals( torre.getColore() ) ){
                        
                        scacchiera[ temp ][ y ] = 1;
                    
                    }
                
                }
            
            }
        
        }
        
        if( y + 1 <= 7 ){ // Direzione Verso L'Alto
        
            
            for( temp = y + 1; temp <= 7 && ! ( m[ x ][ temp ].eOccupato() ); temp++ ){ // Spazi Liberi In Alto
                
                scacchiera[ x ][ temp ] = 1;
            
            }
            
            temp++; // Primo Spazio Occupato In Alto
            
            if( temp <= 7 ){ // Controllo Per Non Uscire Fuori Dalla Scachiera
                
                if( m[ x ][ temp ].eOccupato() ){ // Se Lo Spazio E Occupato E Contiene Un Pezzo Del Colore Opposto
                    
                    if( !m[ x ][ temp ].getOccupante().getColore().equals( torre.getColore() ) ){
                        
                        scacchiera[ x ][ temp ] = 1;
                    
                    }
                
                }
            
            }
        
        }
        
        if( y - 1 >= 0 ){ // Direzione Verso Il Basso
            
            for( temp = y - 1; temp >= 0 && !( m[ x ][ temp ].eOccupato() ); temp++ ){ // Spazi Liberi In Basso
                
                scacchiera[ x ][ temp ] = 1;
            
            }
            
            temp--; // Primo Spazio Occupato In Basso
            
            if( temp >= 0 ){ // Ccontrollo Per Non Uscire Fuori Dalla Scachiera
                
                if( m[ x ][ temp ].eOccupato() ){ // Se Lo Spazio E Occupato E Contiene Un Pezzo Del Colore Opposto
                    
                    if( !m[ x ][ temp ].getOccupante().getColore().equals( torre.getColore() ) ){
                        
                        scacchiera[ x ][ temp ] = 1;
                    
                    }
                
                }
            
            }
        
        }
        
        return scacchiera;
    
    } // Fine movimentiTorre
    
    /**
     * Metodo che verifica e stabilisce i percorsi possibili sulla scacchiera di un Alfiere
     * @param s - Lo spazio dove l'Alfiere vuole muoversi
     * @return scacchiera - La matrice di interi che rappresenta i possibili movimenti dell'alfiere
    */
    private int[][] movimentiAlfiere( Spazio s  ){
        
        int[][] scacchiera = new int[ 8 ][ 8 ];
        Alfiere alfiere = ( Alfiere ) s.getOccupante();
        int x = s.getX();
        int y = s.getY();
        int temp1 = x + 1; // Spostamento Sull'Asse X
        int temp2 = y + 1; // Spostamento Sull'Asse Y
        
        while( temp1 <= 7 && temp2 <= 7 && !m[ temp1 ][ temp2 ].eOccupato() ){ // Direzione Verso Alto A Destra, Spazi Verso Alto A Destra Liberi
            
            scacchiera[ temp1 ][ temp2 ] = 1;
            temp1++;
            temp2++;
        
        }
        
        if( temp1 <=7 && temp2 <= 7 ){
            
            if( m[ temp1 ][ temp2 ].eOccupato() ){ // Se Lo Spazio E Occupato E Contiene Un Pezzo Del Colore Opposto
                
                if( !m[ temp1 ][ temp2 ].getOccupante().getColore().equals( alfiere.getColore() ) ){
                    
                    scacchiera[ temp1 ][ temp2 ] = 1;
                
                }
            
            }
        
        }
        
        temp1 = x + 1;
        temp2 = y - 1;
        
        while( temp1 <= 7 && temp2 >= 0 && !m[ temp1 ][ temp2 ].eOccupato() ){ // Direzione Verso Basso Destra
            
            scacchiera[ temp1 ][ temp2 ] = 1;
            temp1++;
            temp2--;
        
        }
        
        if( temp1 <= 7 && temp2 >= 0 ){
            
            if( m[ temp1 ][ temp2 ].eOccupato() ){ // Se Lo Spazio E Occupato E Contiene Un Pezzo Del Colore Opposto
                
                if( !m[ temp1 ][ temp2 ].getOccupante().getColore().equals( alfiere.getColore() ) ){
                    
                    scacchiera[ temp1 ][ temp2 ] = 1;
                
                }
            
            }
        
        }
        
        temp1 = x - 1;
        temp2 = y + 1;
        
        while( temp1 >= 0 && temp2 <= 7 && !m[ temp1 ][ temp2 ].eOccupato() ){ // Direzione Alto Sinistra
            
            scacchiera[ temp1 ][ temp2 ] = 1;
            temp1--;
            temp2++;
        
        }
        
        if( temp1 >= 0 && temp2 <= 7 ){
            
            if( m[ temp1 ][ temp2 ].eOccupato() ){ // Se Lo Spazio E Occupato E Contiene Un Pezzo Del Colore Opposto
                
                if( !m[ temp1 ][ temp2 ].getOccupante().getColore().equals( alfiere.getColore() ) ){
                    
                    scacchiera[ temp1 ][ temp2 ] = 1;
                
                }
            
            }
        
        }
        
        temp1 = x - 1;
        temp2 = y - 1;
        
        while( temp1 >= 0 && temp2 >= 0 && !m[ temp1 ][ temp2 ].eOccupato() ){ // Direzione Basso Sinistra
            
            scacchiera[ temp1 ][ temp2 ] = 1;
            temp1--;
            temp2--;
        
        }
        
        if( temp1 >= 0 && temp2 >= 0 ){
            
            if( m[ temp1 ][ temp2 ].eOccupato() ){ // Se Lo Spazio E Occupato E Contiene Un Pezzo Del Colore Opposto
                
                if( !m[ temp1 ][ temp2 ].getOccupante().getColore().equals( alfiere.getColore() ) ){
                    
                    scacchiera[ temp1 ][ temp2 ] = 1;
                
                }
            
            }
        
        }
        
        return scacchiera;
    
    } // Fine MovimentiAlfiere
    
    /**
     * Metodo che verifica e stabilisce i percorsi possibili sulla scacchiera di un Cavallo
     * @param s - Lo spazio dove il Cavallo vuole muoversi
     * @return scacchiera - La matrice di interi che rappresenta i possibili movimenti del cavallo
    */
    private int[][] movimentiCavallo( Spazio s ){
        
        int[][] scacchiera = new int[ 8 ][ 8 ];
        Cavallo cavallo = ( Cavallo ) s.getOccupante();
        int x = s.getX();
        int y = s.getY();
        int temp;
        
        if( x + 1 <= 7 && y + 2 <= 7 ){ // In Alto A Destra
            
            if( !m[ x + 1 ][ y + 2 ].eOccupato() || ( m[ x + 1 ][ y + 2 ].eOccupato() ) ){ // Libero O Dell'Avversario
                
                if( !m[ x + 1 ][ y + 2 ].getOccupante().getColore().equals( cavallo.getColore() ) ){
                    
                    scacchiera[ x + 1 ][ y + 2 ] = 1;
                
                }
            
            }
        
        }
        
        if( x - 1 >= 0 && y + 2 <= 7 ){ // In Alto A Sinistra
            
            if( !m[ x - 1 ][ y + 2 ].eOccupato() || ( m[ x - 1 ][ y + 2 ].eOccupato() ) ){ // Libero O Dell'Avversario
                
                if( !m[ x - 1 ][ y + 2 ].getOccupante().getColore().equals( cavallo.getColore() ) ){
                    
                    scacchiera[ x - 1 ][ y + 2 ] = 1;
                
                }
            
            }
        
        }
        
        if( x + 2 <= 7 && y + 1 <= 7 ){ // A Destra In Alto
            
            if( !m[ x + 2 ][ y + 1 ].eOccupato() || ( m[ x + 2 ][ y + 1 ].eOccupato() ) ){ // Libero O Dell'Avversario
                
                if( !m[ x + 2 ][ y + 1 ].getOccupante().getColore().equals( cavallo.getColore() ) ){
                    
                    scacchiera[ x + 2 ][ y + 1 ] = 1;
                
                }
            
            }
        
        }
        
        if( x + 2 <= 7 && y - 1 >= 0 ){ // A Destra In Basso
            
            if( !m[ x + 2 ][ y - 1 ].eOccupato() || ( m[ x + 2 ][ y - 1 ].eOccupato() ) ){ // Libero O Dell'Avversario
                
                if( !m[ x + 2 ][ y - 1 ].getOccupante().getColore().equals( cavallo.getColore() ) ){
                    
                    scacchiera[ x + 2 ][ y - 1 ] = 1;
                
                }
            
            }
        
        }
        
        if( x - 2 >= 0 && y + 1 <= 7 ){ // A Sinistra In Alto
            
            if( !m[ x - 2 ][ y + 1 ].eOccupato() || ( m[ x - 2 ][ y + 1 ].eOccupato() ) ){ // Libero O Dell'Avversario
                
                if( !m[ x - 2 ][ y + 1 ].getOccupante().getColore().equals( cavallo.getColore() ) ){
                    
                    scacchiera[ x - 2 ][ y + 1 ] = 1;
                
                }
            
            }
        
        }
        
        if( x - 2 >= 0 && y - 1 >= 0 ){ // A Sinistra In Basso
            
            if( !m[ x - 2 ][ y - 1 ].eOccupato() || ( m[ x - 2 ][ y - 1 ].eOccupato() ) ){ // Libero O Dell'Avversario
                
                if( !m[ x - 2 ][ y - 1 ].getOccupante().getColore().equals( cavallo.getColore() ) ){
                    
                    scacchiera[ x - 2 ][ y - 1 ] = 1;
                
                }
            
            }
        
        }
        
        if( x + 1 <= 7 && y - 2 >= 0 ){ // In Basso A Desra
            
            if( !m[ x + 1 ][ y - 2 ].eOccupato() || ( m[ x + 1 ][ y - 2 ].eOccupato() ) ){ // Libero O Dell'Avversario
                
                if( !m[ x + 1 ][ y - 2 ].getOccupante().getColore().equals( cavallo.getColore() ) ){
                    
                    scacchiera[ x + 1 ][ y - 2 ] = 1;
                
                }
            
            }
        
        }
        
        if( x - 1 >= 0 && y - 2 >= 0 ){ // In Basso A Sinistra
            
            if( !m[ x - 1 ][ y - 2 ].eOccupato() || ( m[ x - 1 ][ y - 2 ].eOccupato() ) ){ // Libero O Dell'Avversario
                
                if( !m[ x - 1 ][ y - 2 ].getOccupante().getColore().equals( cavallo.getColore() ) ){
                    
                    scacchiera[ x - 1 ][ y - 2 ] = 1;
                
                }
            
            }
        
        }
        
        return scacchiera;
    
    } // Fine movimentiCavallo

    
    /**
     * Metodo che verifica e stabilisce i percorsi possibili sulla scacchiera di una Regina
     * @param s - Lo spazio dove la Regina vuole muoversi
     * @return scacchiera - La matrice di interi che rappresenta i possibili movimenti della regina
    */
    private int[][] movimentiRegina( Spazio s ){
        
        int[][] scacchiera = new int[ 8 ][ 8 ];
        Regina regina = ( Regina ) s.getOccupante();
        int x = s.getX();
        int y = s.getY();
        int[][] m1 = movimentiTorre( new Spazio( x, y , new Torre( regina.getColore() ) ) );
        int[][] m2 = movimentiAlfiere( new Spazio( x, y, new Alfiere( regina.getColore() ) ) );
        
        for( int i = 0; i < 7; i++ ){ // Combinazione Dei Risultati Della Torre E Dell'Alfiere
            
            for( int j = 0; j < 7; j++ ){
                
                if( m1[ i ][ j ] == 1 || m2[ i ][ j ] == 1 ){
                    
                    scacchiera[ i ][ j ] = 1;
                
                }
            
            }
        
        }
        
        return scacchiera;
    
    } // Fine movimentoRegina
    
    // COSA E LA BOOLEAN USCITA ?????????????????????????????????????????????????????????
    
    /**
     * Questo metodo verifica la presenza o meno di uno scacco al Re tramite tre parametri
     * @param x - La coordinata X dove si trova il Re
     * @param y - La coordinata Y dove si trova il Re
     * @param matrice - La matrice delle posizioni sulla scacchieras
     * @return True se il Re si trova sotto scacco, false altrimenti
    */
    public boolean controlloScacco( int x, int y, Spazio[][] matrice ){
        
        Spazio[][] mat = coppiaMatrice( matrice );
        Colore colore = mat[ x ][ y ].getOccupante().getColore();
        int temp1, temp2; 
        boolean uscita = false;
        
        // Controllo Per Torri / Regine In Orizzontale E Verticale
        
        for( int i = x + 1; i <= 7 && !uscita; i++ ){ // Controllo Verso Destra
            
            if( mat[ i ][ y ].eOccupato() ){
                
                uscita = true; // Uscita Dal Ciclo Dopo Aver Controllato Il Primo Spazio
                
                if( !mat[ i ][ y ].getOccupante().getColore().equals( colore ) ){
                    
                    if( mat[ i ][ y ].getOccupante() instanceof Torre || mat[ i ][ y ].getOccupante() instanceof Regina ){
                        
                        return false;
                    
                    }
                
                }
            
            }
        
        }
        
        uscita = false;
        
        for( int i = x - 1; i >= 0 && !uscita; i-- ){ // Controllo Verso Sinistra
            
            if( mat[ i ][ y ].eOccupato() ){
                
                uscita = true; // Uscita Dal Ciclo Dopo Aver Controllato Il Primo Spazio
                
                if( !mat[ i ][ y ].getOccupante().getColore().equals( colore ) ){
                    
                    if( mat[ i ][ y ].getOccupante() instanceof Torre || mat[ i ][ y ].getOccupante() instanceof Regina ){
                        
                        return false;
                    
                    }
                
                }
            
            }
        
        }
        
        uscita = false;
        
        for( int i = y + 1; i <= 7 && !uscita; i++ ){ // Controllo Verso Il Basso
            
            if( mat[ x ][ i ].eOccupato() ){
                
                uscita = true; // Uscita Dal Ciclo Dopo Aver Controllato Il Primo Spazio
                
                if( !mat[ x ][ i ].getOccupante().getColore().equals( colore ) ){
                    
                    if( mat[ x ][ i ].getOccupante() instanceof Torre || mat[ x ][ i ].getOccupante() instanceof Regina ){
                        
                        return false;
                    
                    }
                
                }
            
            }
        
        }
        
        uscita = false;
        
        for( int i = y - 1; i >= 0 && !uscita; i-- ){ // Controllo Verso L'Alto
            
            if( mat[ x ][ i ].eOccupato() ){
                
                uscita = true; // Uscita Dal Ciclo Dopo Aver Controllato Il Primo Spazio
                
                if( !mat[ x ][ i ].getOccupante().getColore().equals( colore ) ){
                    
                    if( mat[ x ][ i ].getOccupante() instanceof Torre || mat[ x ][ i ].getOccupante() instanceof Regina ){
                        
                        return false;
                    
                    }
                
                }
            
            }
        
        }

        // Controlli In Diagonale Per Alfieri E Regine
        
        temp1 = x + 1;
        temp2 = y + 1;
        
        while( temp1 <= 7 && temp2 <= 7 && !mat[ temp1 ][ temp2 ].eOccupato() ){ // Verso L'Alto A Destra
            
            temp1++;
            temp2++;
        
        }
        
        if( temp1 <= 7 && temp2 <= 7 ){
            
            if( !mat[ temp1 ][ temp2 ].getOccupante().getColore().equals( colore ) ){
                
                if( mat[ temp1 ][ temp2 ].getOccupante() instanceof Alfiere || mat[ temp1 ][ temp2 ].getOccupante() instanceof Regina ){
                    
                    return false;
                
                }
            
            }
        
        }
        
        temp1 = x - 1;
        temp2 = y + 1;
        
        while( temp1 >= 0 && temp2 <= 7 && !mat[ temp1 ][ temp2 ].eOccupato() ){ // Verso L'Alto A Sinistra
            
            temp1--;
            temp2++;
        
        }
        
        if( temp1 >= 0 && temp2 <= 7 && !mat[ temp1 ][ temp2 ].getOccupante().getColore().equals( colore ) ){
            
            if( mat[ temp1 ][ temp2 ].getOccupante() instanceof Alfiere || mat[ temp1 ][ temp2 ].getOccupante() instanceof Regina ){
                
                return false;
            
            }
        
        }
        
        temp1 = x - 1;
        temp2 = y - 1;
        
        while( temp1 >= 0 && temp2 >= 0 && !mat[ temp1 ][ temp2 ].eOccupato() ){ // Verso Il Basso A Sinistra
            
            temp1--;
            temp2--;
        
        }
        
        if( temp1 >= 0 && temp2 >= 0 ){
            
            if( !mat[ temp1 ][ temp2 ].getOccupante().getColore().equals( colore ) ){
                
                if( mat[ temp1 ][ temp2 ].getOccupante() instanceof Alfiere || mat[ temp1 ][ temp2 ].getOccupante() instanceof Regina ){
                    
                    return false;
                
                }
            
            }
        
        }
        
        temp1 = x + 1;
        temp2 = y - 1;
        
        while( temp1 <= 7 && temp2 >= 0 && !mat[ temp1 ][ temp2 ].eOccupato() ){ // Verso Il Basso A Destra
            
            temp1++;
            temp2--;
        
        }
        
        if( temp1 <= 7 && temp2 >= 0 ){
            
            if( !mat[ temp1 ][ temp2 ].getOccupante().getColore().equals( colore ) ){
                
                if( mat[ temp1 ][ temp2 ].getOccupante() instanceof Alfiere || mat[ temp1 ][ temp2 ].getOccupante() instanceof Regina ){
                    
                    return false;
                
                }
            
            }
        
        }

        // Controllo Per I Pedoni
        if( colore instanceof Nero ){ // Controllo Pedoni Neri
            
            if( x + 1 <= 7 && y + 1 <= 7 && mat[ x + 1 ][ y + 1 ].eOccupato() && mat[ x + 1 ][ y + 1 ].getOccupante() instanceof Pedone ){
                
                if( !mat[ x + 1 ][ y + 1 ].getOccupante().getColore().equals( colore ) ){
                    
                    return false;
                
                }
            
            }
            
            if( x - 1 >= 0 && y + 1 <= 7 ){
                
                if( mat[ x - 1 ][ y + 1 ].eOccupato() ){
                    
                    if( mat[ x - 1 ][ y + 1 ].getOccupante() instanceof Pedone ){
                        
                        if( !mat[ x - 1 ][ y + 1 ].getOccupante().getColore().equals( colore ) ){
                            
                            return false;
                        
                        }
                    
                    }
                
                }
            
            }
        
        } else { // Controllo Pedoni Bianchi
            
            if( x + 1 <= 7 && y - 1 >= 0 && mat[ x + 1 ][ y - 1 ].eOccupato() && mat[ x + 1 ][ y - 1 ].getOccupante() instanceof Pedone ){
                
                if( !mat[ x + 1 ][ y - 1 ].getOccupante().getColore().equals( colore ) ){
                    
                    return false;
                
                }
            
            }
            
            if( x - 1 >= 0 && y - 1 >= 0 && mat[ x - 1 ][ y - 1 ].eOccupato() ){
                
                if( mat[ x - 1 ][ y - 1 ].getOccupante() instanceof Pedone ){
                    
                    if( !mat[ x - 1 ][ y - 1 ].getOccupante().getColore().equals( colore ) ){
                        
                        return false;
                    
                    }
                
                }
            
            }
        
        }

        // Controlli Del Cavallo
        
        if( x + 1 <= 7 && y + 2 <= 7 && mat[ x + 1 ][ y + 2 ].eOccupato() ){ // In Alto A Destra
            
            if( !mat[ x + 1 ][ y + 2 ].getOccupante().getColore().equals( colore ) && mat[ x + 1 ][ y + 2 ].getOccupante() instanceof Cavallo ){
                
                return false;
            
            }
        
        }
        
        if( x - 1 >= 0 && y + 2 <= 7 && mat[ x - 1 ][ y + 2 ].eOccupato() ){ // In Alto A Sinistra
            
            if( !mat[ x - 1 ][ y + 2 ].getOccupante().getColore().equals( colore ) && mat[ x - 1 ][ y + 2 ].getOccupante() instanceof Cavallo ){
                
                return false;
            
            }
        
        }
        
        if( x + 2 <= 7 && y + 1 <= 7 && mat[ x + 2 ][ y + 1 ].eOccupato() ){ // A Destra In Alto
            
            if( !mat[ x + 2 ][ y + 1 ].getOccupante().getColore().equals( colore ) && mat[ x + 2 ][ y + 1 ].getOccupante() instanceof Cavallo ){
                
                return false;
            
            }
        
        }
        
        if( x + 2 <= 7 && y - 1 >= 0 && mat[ x + 2 ][ y - 1 ].eOccupato() ){ // A Destra In Basso
            
            if( !mat[ x + 2 ][ y - 1 ].getOccupante().getColore().equals( colore ) && mat[ x + 2 ][ y - 1 ].getOccupante() instanceof Cavallo ){
                
                return false;
            
            }
        
        }
        
        if( x - 2 >= 0 && y + 1 <= 7 && mat[ x - 2 ][ y + 1 ].eOccupato() ){ // A Sinistra In Alto
            
            if( !mat[ x - 2 ][ y + 1 ].getOccupante().getColore().equals( colore ) && mat[ x - 2 ][ y + 1 ].getOccupante() instanceof Cavallo ){
                
                return false;
            
            }
        
        }
        
        if( x - 2 >= 0 && y - 1 >= 0 && mat[ x - 2 ][ y - 1 ].eOccupato() ){ // A Sinistra In Basso
            
            if( !mat[ x - 2 ][ y - 1 ].getOccupante().getColore().equals( colore ) && mat[ x - 2 ][ y - 1 ].getOccupante() instanceof Cavallo ){
                
                return false;
            
            }
        
        }
        
        if( x + 1 <= 7 && y - 2 >= 0 ){ // In Basso A Destra
            
            if( mat[ x + 1 ][ y - 2 ].eOccupato() ){
                
                if( !mat[ x + 1 ][ y - 2 ].getOccupante().getColore().equals( colore ) && mat[ x + 1 ][ y - 2 ].getOccupante() instanceof Cavallo ){
                    
                    return false;
                
                }
            
            }
        
        }
        
        if( x - 1 >= 0 && y - 2 >= 0 && mat[ x - 1 ][ y - 2 ].eOccupato() ){ // In Basso A Sinistra
            
            if( !mat[ x - 1 ][ y - 2 ].getOccupante().getColore().equals( colore ) && mat[ x - 1 ][ y - 2 ].getOccupante() instanceof Cavallo ){
                
                return false;
            
            }
        
        }

        // Controllo Re Adiacente
        
        if( x + 1 <= 7 && mat[ x + 1 ][ y ].eOccupato() ){ // A Destra
            
            if( !mat[ x + 1 ][ y ].getOccupante().getColore().equals( colore ) && m[ x + 1 ][ y ].getOccupante() instanceof Re ){
                
                return false;
            
            }
        
        }
        
        if( x - 1 >= 0 && mat[ x - 1 ][ y ].eOccupato() ){ // A Sinistra
            
            if( !mat[ x - 1 ][ y ].getOccupante().getColore().equals( colore ) ){
                
                if( mat[ x - 1 ][ y ].getOccupante() instanceof Re ){
                    
                    return false;
                
                }
            
            }
        
        }
        
        if( y + 1 <= 7 ){ // In Alto
            
            if( mat[ x ][ y + 1 ].eOccupato() ){
                
                if( !mat[ x ][ y + 1 ].getOccupante().getColore().equals( colore ) ){
                    
                    if( mat[ x ][ y + 1 ].getOccupante() instanceof Re ){
                        
                        return false;
                    
                    }
                
                }
            
            }
        
        }
        
        if( y - 1 >= 0  ){ // In Basso
            
            if( mat[ x ][ y - 1 ].eOccupato() ){
                
                if( !mat[ x ][ y - 1 ].getOccupante().getColore().equals( colore ) && mat[ x ][ y - 1 ].getOccupante() instanceof Re ){
                    
                    return false;
                
                }
            
            }
        
        }
        
        if( x + 1 <= 7 && y + 1 <= 7 ){ // In Alto A Destra
            
            if( mat[ x + 1 ][ y + 1 ].eOccupato() ){
                
                if( !mat[ x + 1 ][ y + 1 ].getOccupante().getColore().equals( colore ) && mat[ x + 1 ][ y + 1 ].getOccupante() instanceof Re ){
                    
                    return false;
                
                }
            
            }
        
        }
        
        if( x - 1 >= 0 && y + 1 <= 7 && mat[ x - 1 ][ y + 1 ].eOccupato() ){ // In Alto A Sinistra
            
            if( !mat[ x - 1 ][ y + 1 ].getOccupante().getColore().equals( colore ) && mat[ x - 1 ][ y + 1 ].getOccupante() instanceof Re ){
                
                return false;
            
            }
        
        }
        
        if( x + 1 <= 7 && y - 1 >= 0 && mat[ x + 1 ][ y - 1 ].eOccupato() ){ // In Basso A Destra
            
            if( !mat[ x + 1 ][ y - 1 ].getOccupante().getColore().equals( colore ) && mat[ x + 1 ][ y - 1 ].getOccupante() instanceof Re ){
                
                return false;
            
            }
        
        }
        
        if( x - 1 >= 0 && y - 1 >= 0 && mat[ x - 1 ][ y - 1 ].eOccupato() ){ // In Basso A Sinistra
            
            if( !mat[ x - 1 ][ y - 1 ].getOccupante().getColore().equals( colore ) && mat[ x - 1 ][ y - 1 ].getOccupante() instanceof Re ){
                
                return false;
            
            }
        
        }
        
        return true;
    
    } // Fine ControlloScacco 3 Parametri

    /**
     * Questo metodo verifica la presenza o meno di uno scacco al Re passato tramite parametro
     * Usando la matrice del gestore
     * @param s - Lo spazio dove si trova il Re
     * @return True se il Re si trova sotto scacco, false altrimenti
    */
    public boolean controlloScacco( Spazio s ){
        
        return controlloScacco( s.getX(), s.getY(), m );
    
    }
    
    /**
     * Questo metodo verifica la presenza o meno di uno scacco al Re tramite due parametri
     * @param colore - Il colore del turno corrente
     * @param matrice - La matrice delle posizioni sulla scacchiera
     * @return True se il Re si trova sotto scacco, false altrimenti
    */
    public boolean controlloScacco( Colore colore, Spazio[][] matrice ){
        
        Spazio s;
        
        if( colore instanceof Bianco ){
            
            s = this.getSpazioReBianco();
        
        } else {
            
            s = this.getSpazioReNero();
        
        }
        
        return controlloScacco( s.getX(), s.getY(), matrice );
    
    }
    
    /**
     * Questo metodo verifica la presenza o meno di uno scacco al Re avversario del turno passato come parametro
     * @param colore - Il colore del turno corrente
     * @return True se il Re avversario si trova sotto scacco, false altrimenti
    */
    public boolean controlloScaccoReAvversario( Colore colore ){
        
        Spazio s;
        
        if( colore instanceof Bianco ){
            
            s = this.getSpazioReNero();
        
        } else {
            
            s = this.getSpazioReBianco();
        
        }
        
        return controlloScacco( s.getX(), s.getY(), m );
    
    }
    
    /**
     * Questo metodo verifica la presenza o meno di uno scacco al Re avversario
     * Passando come parametro il turno corrente e la matrice della scacchiera
     * @param colore - Il colore del turno corrente
     * @param matrice - La matrice delle posizioni sullascaccheria
     * @return True se il Re avversario si trova sotto scacco, false altrimenti
    */
    public boolean controlloScaccoReAvversario( Colore colore, Spazio[][] matrice ){
        
        Spazio s;
        
        if( colore instanceof Bianco ){
            
            s = this.getSpazioReNero();
        
        } else {
            
            s = this.getSpazioReBianco();
        
        }
        
        return controlloScacco( s.getX(), s.getY(), matrice );
    
    }
    
    // A COSA SERVE BOOLEAN USCITA?????????????????????????????????????????????????????
    
    /**
     * Questo metodo controlla quali e quanti pezzi del nemico mettono in pericolo / mangiano il Re
     * @param s - Lo spazio dove si trova il Re
     * @param matrice - La matrice delle posizioni sulla scacchiera
     * @return lista - La lista dei pezzi attacanti il Re
    */
    public LinkedList< Pezzo > getPezziAttaccantiIlRe( Spazio s, Spazio[][] matrice ){
        
        Spazio[][] mat = matrice;
        LinkedList< Pezzo > lista = new LinkedList<>(); 
        Re re = ( Re ) s.getOccupante();
        Colore colore = re.getColore();
        int x = s.getX();
        int y = s.getY();
        boolean uscita = false;
        int temp1, temp2;

        // Controllo Per Torri / Regine In Orizzontale E Verticale
        
        for( int i = x; i <= 7 && !uscita; i++ ){ // Controllo Verso Destra
            
            if( mat[ i ][ y ].eOccupato() ){
                
                uscita = true; // Uscita Dal Ciclo Dopo Aver Controllato Il Primo Spazio
                
                if( !mat[ i ][ y ].getOccupante().getColore().equals( colore ) ){
                    
                    if( mat[ i ][ y ].getOccupante() instanceof Torre || mat[ i ][ y ].getOccupante() instanceof Regina ){
                        
                        lista.add( mat[ i ][ y ].getOccupante() );
                    
                    }
                
                }
            
            }
        
        }
        
        uscita = false;
        
        for( int i = x; i >= 0 && !uscita; i-- ){ // Controllo Verso Sinistra
            
            if( mat[ i ][ y ].eOccupato() ){
                
                uscita = true; // Uscita Dal Ciclo Dopo Aver Controllato Il Primo Spazio
                
                if( !mat[ i ][ y ].getOccupante().getColore().equals( colore ) ){
                    
                    if( mat[ i ][ y ].getOccupante() instanceof Torre || mat[ i ][ y ].getOccupante() instanceof Regina ){
                        
                        lista.add( mat[ i ][ y ].getOccupante() );
                    
                    }
                
                }
            
            }
        
        }
        
        uscita = false;
        
        for( int i = y; i <= 7 && !uscita; i++ ){ // Controllo Verso L'Alto
            
            if( mat[ x ][ i ].eOccupato() ){
                
                uscita = true; // Uscita Dal Ciclo Dopo Aver Controllato Il Primo Spazio
                
                if( !mat[ x ][ i ].getOccupante().getColore().equals( colore ) ){
                    
                    if( mat[ x ][ i ].getOccupante() instanceof Torre || mat[ x ][ i ].getOccupante() instanceof Regina ){
                        
                        lista.add( mat[ x ][ i ].getOccupante() );
                    
                    }
                
                }
            
            }
        
        }
        
        uscita = false;
        
        for( int i = y; i >= 0 && !uscita; i-- ){ // Controllo Verso Il Basso
            
            if( mat[ x ][ i ].eOccupato() ){
                
                uscita = true; // Uscita Dal Ciclo Dopo Aver Controllato Il Primo Spazio
                
                if( !mat[ x ][ i ].getOccupante().getColore().equals( colore ) ){
                    
                    if( mat[ x ][ i ].getOccupante() instanceof Torre || mat[ x ][ i ].getOccupante() instanceof Regina ){
                        
                        lista.add( mat[ x ][ i ].getOccupante() );
                    
                    }
                
                }
            
            }
        
        }

        // Controllo In Diagonale Per Alfieri E Regine
        
        temp1 = x + 1;
        temp2 = y + 1;
        
        while( temp1 <= 7 && temp2 <= 7 && !mat[ temp1 ][ temp2 ].eOccupato() ){ // Verso L'Alto A Destra
            
            temp1++;
            temp2++;
        
        }
        
        if( temp1 <= 7 && temp2 <= 7 && !mat[ temp1 ][ temp2 ].getOccupante().getColore().equals( colore ) ){
            
            if( mat[ temp1 ][ temp2 ].getOccupante() instanceof Alfiere || mat[ temp1 ][ temp2 ].getOccupante() instanceof Regina ){
                
                lista.add( mat[ temp1 ][ temp2 ].getOccupante() );
            
            }
        
        }
        
        temp1 = x - 1;
        temp2 = y + 1;
        
        while( temp1 >= 0 && temp2 <= 7 && !mat[ temp1 ][ temp2 ].eOccupato() ){ // Verso L'Alto A Sinistra
            
            temp1--;
            temp2++;
        
        }
        
        if( temp1 >= 0 && temp2 <= 7 && !mat[ temp1 ][ temp2 ].getOccupante().getColore().equals( colore ) ){
            
            if( mat[ temp1 ][ temp2 ].getOccupante() instanceof Alfiere || mat[ temp1 ][ temp2 ].getOccupante() instanceof Regina ){
                
                lista.add( mat[ temp1 ][ temp2 ].getOccupante() );
            
            }
        
        }
        
        temp1 = x - 1;
        temp2 = y - 1;
        
        while( temp1 >= 0 && temp2 >= 0 && !m[ temp1 ][ temp2 ].eOccupato() ){ // Verso Il Basso A Sinistra
            
            temp1--;
            temp2--;
        
        }
        
        if( temp1 >= 0 && temp2 >= 0 && !mat[ temp1 ][ temp2 ].getOccupante().getColore().equals( colore ) ){
            
            if( mat[ temp1 ][ temp2 ].getOccupante() instanceof Alfiere || mat[ temp1 ][ temp2 ].getOccupante() instanceof Regina ){
                
                lista.add( mat[ temp1 ][ temp2 ].getOccupante() );
            
            }
        
        }
        
        temp1 = x + 1;
        temp2 = y - 1;
        
        while( temp1 <= 7 && temp2 >= 0 && !mat[ temp1 ][ temp2 ].eOccupato() ){ // Verso Il Basso A Destra
            
            temp1++;
            temp2--;
        
        }
        
        if( temp1 <= 7 && temp2 >= 0 && !mat[ temp1 ][ temp2 ].getOccupante().getColore().equals( colore ) ){
            
            if( mat[ temp1 ][ temp2 ].getOccupante() instanceof Alfiere || mat[ temp1 ][ temp2 ].getOccupante() instanceof Regina ){
                
                lista.add( mat[ temp1 ][ temp2 ].getOccupante() );
            
            }
        
        }

        // Controllo Per I Pedoni
        
        if( colore instanceof Nero ){ // Controllo Pedoni Neri
            
            if( x + 1 <= 7 && y + 1 <= 7 && mat[ x + 1 ][ y + 1 ].eOccupato() && mat[ x + 1 ][ y + 1 ].getOccupante() instanceof Pedone ){
                
                if( !mat[ x + 1 ][ y + 1 ].getOccupante().getColore().equals( colore ) ){
                    
                    lista.add( mat[ x + 1 ][ y + 1 ].getOccupante() );
                
                }
            
            }
            
            if( x - 1 >= 0 && y + 1 <= 7 && mat[ x - 1 ][ y + 1 ].eOccupato() && mat[ x + 1 ][ y - 1 ].getOccupante() instanceof Pedone ){
                
                if( !mat[ x - 1 ][ y + 1 ].getOccupante().getColore().equals( colore ) ){
                    
                    lista.add( mat[ x - 1 ][ y + 1 ].getOccupante() );
                
                }
            
            }
        
        } else { // Controllo Pedoni Bianchi
            
            if( x + 1 <= 7 && y - 1 >= 0 && mat[ x + 1 ][ y - 1 ].eOccupato() && mat[ x + 1 ][ y - 1 ].getOccupante() instanceof Pedone ){
                
                if( !mat[ x + 1 ][ y - 1 ].getOccupante().getColore().equals( colore ) ){
                    
                    lista.add( mat[ x + 1 ][ y - 1 ].getOccupante() );
                
                }
            
            }
            
            if( x - 1 >= 0 && y - 1 >= 0 && mat[ x - 1 ][ y - 1 ].eOccupato() && mat[ x - 1 ][ y - 1 ].getOccupante() instanceof Pedone ){
                
                if( !mat[ x - 1 ][ y - 1 ].getOccupante().getColore().equals( colore ) ){
                    
                    lista.add( mat[ x - 1 ][ y - 1 ].getOccupante() );
                
                }
            
            }
        
        }

        // Controlli Per I Cavalli
        if( x + 1 <= 7 && y + 2 <= 7 && mat[ x + 1 ][ y + 2 ].eOccupato() ){ // In Alto A Destra
            
            if( !mat[ x + 1 ][ y + 2 ].getOccupante().getColore().equals( colore ) && mat[ x + 1 ][ y + 2 ].getOccupante() instanceof Cavallo ){
                
                lista.add( mat[ x + 1 ][ y + 2 ].getOccupante() );
            
            }
        
        }
        
        if( x - 1 >= 0 && y + 2 <= 7 && mat[ x - 1 ][ y + 2 ].eOccupato() ){ // In Alto A Sinistra
            
            if( !mat[ x - 1 ][ y + 2 ].getOccupante().getColore().equals( colore ) && mat[ x - 1 ][ y + 2 ].getOccupante() instanceof Cavallo ){
                
                lista.add( mat[ x - 1 ][ y + 2 ].getOccupante() );
            
            }
        
        }
        
        if( x + 2 <= 7 && y + 1 <= 7 && mat[ x + 2 ][ y + 1 ].eOccupato() ){ // A Destra In Alto
            
            if( !mat[ x + 2 ][ y + 1 ].getOccupante().getColore().equals( colore ) && mat[ x + 2 ][ y + 1 ].getOccupante() instanceof Cavallo ){
                
                lista.add( mat[ x + 2 ][ y + 1 ].getOccupante() );
            
            }
        
        }
        
        if( x + 2 <= 7 && y - 1 >= 0 && mat[ x + 2 ][ y - 1 ].eOccupato() ){ // A Destra In Basso
            
            if( !mat[ x + 2 ][ y - 1 ].getOccupante().getColore().equals( colore ) && mat[ x + 2 ][ y + 1 ].getOccupante() instanceof Cavallo ){
                
                lista.add( mat[ x + 2 ][ y - 1 ].getOccupante() );
            
            }
        
        }
        
        if( x - 2 >= 0 && y + 1 <= 7 && mat[ x - 2 ][ y + 1 ].eOccupato() ){ // A Sinistra In Alto
            
            if( !mat[ x - 2 ][ y + 1 ].getOccupante().getColore().equals( colore ) && mat[ x - 2 ][ y + 1 ].getOccupante() instanceof Cavallo ){
                
                lista.add( mat[ x - 2 ][ y + 1 ].getOccupante() );
            
            }
        
        }
        
        if( x - 2 >= 0 && y - 1 >= 0 && mat[ x - 2 ][ y - 1 ].eOccupato() ){ // A Sinistra In Basso
            
            if( !mat[ x - 2 ][ y - 1 ].getOccupante().getColore().equals( colore ) && mat[ x - 2 ][ y - 1 ].getOccupante() instanceof Cavallo ){
                
                lista.add( mat[ x - 2 ][ y - 1 ].getOccupante() );
            
            }
        
        }
        
        if( x + 1 <= 7 && y - 2 <= 7 && mat[ x + 1 ][ y - 2 ].eOccupato() ){ // In Basso A Destra
            
            if( !mat[ x + 1 ][ y - 2 ].getOccupante().getColore().equals( colore ) && mat[ x + 1 ][ y - 2 ].getOccupante() instanceof Cavallo ){
                
                lista.add( mat[ x + 1 ][ y - 2 ].getOccupante() );
            
            }
        
        }
        
        if( x - 1 >= 0 && y - 2 >= 0 && mat[ x - 1 ][ y - 2 ].eOccupato() ){ // In Basso A Sinistra
            
            if( !mat[ x - 1 ][ y - 2 ].getOccupante().getColore().equals( colore ) && mat[ x - 1 ][ y - 2 ].getOccupante() instanceof Cavallo ){
                
                lista.add( mat[ x - 1 ][ y - 2 ].getOccupante() );
            
            }
        
        }

        // Controllo Re Adiacente
        
        if( x + 1 <= 7 && mat[ x + 1 ][ y ].eOccupato() ){ // A Destra
            
            if( !mat[ x + 1 ][ y ].getOccupante().getColore().equals( colore ) && m[ x + 1 ][ y ].getOccupante() instanceof Re ){
                
                lista.add( mat[ x + 1 ][ y ].getOccupante() );
            
            }
        
        }
        
        if( x - 1 >= 0 && mat[ x - 1 ][ y ].eOccupato() ){ // A Sinistra
            
            if( !mat[ x - 1 ][ y ].getOccupante().getColore().equals( colore ) && mat[ x - 1 ][ y ].getOccupante() instanceof Re ){
                
                lista.add( mat[ x - 1 ][ y ].getOccupante() );
            
            }
        
        }
        
        if( y + 1 <= 7 && m[ x ][ y + 1 ].eOccupato() ){ // In Alto
            
            if( !mat[ x ][ y + 1 ].getOccupante().getColore().equals( colore ) && mat[ x ][ y + 1 ].getOccupante() instanceof Re ){
                
                lista.add( mat[ x ][ y + 1 ].getOccupante() );
            
            }
        
        }
        
        if( y - 1 >= 0 && mat[ x ][ y - 1 ].eOccupato() ){ // In Basso
            
            if( !mat[ x ][ y - 1 ].getOccupante().getColore().equals( colore ) && mat[ x ][ y - 1 ].getOccupante() instanceof Re ){
                
                lista.add( mat[ x ][ y - 1 ].getOccupante() );
            
            }
        
        }
        
        if( x + 1 <= 7 && y + 1 <= 7 && mat[ x + 1 ][ y + 1 ].eOccupato() ){ // In Alto A Destra
            
            if( !mat[ x + 1 ][ y + 1 ].getOccupante().getColore().equals( colore ) && mat[ x + 1 ][ y + 1 ].getOccupante() instanceof Re ){
                
                lista.add( mat[ x + 1 ][ y + 1 ].getOccupante() );
            
            }
        
        }
        
        if( x - 1 >= 0 && y + 1 <= 7 && mat[ x - 1 ][ y + 1 ].eOccupato() ){ // In Alto A Sinistra
            
            if( !mat[ x - 1 ][ y + 1 ].getOccupante().getColore().equals( colore ) && mat[ x - 1 ][ y + 1 ].getOccupante() instanceof Re ){
                
                lista.add( mat[ x - 1 ][ y + 1 ].getOccupante() );
            
            }
        
        }
        
        if( x + 1 <= 7 && y - 1 >= 0 && mat[ x + 1 ][ y - 1 ].eOccupato() ){ // In Basso A Destra
            
            if( !mat[ x + 1 ][ y - 1 ].getOccupante().getColore().equals( colore ) && mat[ x + 1 ][ y - 1 ].getOccupante() instanceof Re ){
                
                lista.add( mat[ x + 1 ][ y - 1 ].getOccupante() );
            
            }
        
        }
        
        if( x - 1 >= 0 && y - 1 >= 0 && mat[ x - 1 ][ y - 1 ].eOccupato() ){ // In Basso A Sinistra
            
            if( !mat[ x - 1 ][ y - 1 ].getOccupante().getColore().equals( colore ) && mat[ x - 1 ][ y - 1 ].getOccupante() instanceof Re ){
                
                lista.add( mat[ x - 1 ][ y - 1 ].getOccupante() );
            
            }
        
        }
        
        return lista;
    
    }
    
    /**
     * Questo metodo valuta se, preso una Torre e la posizione dove la si vuole muovere, essa puo muoversi
     * @param s - Lo spazio della Torre contenente la sua posizione e proprieta
     * @param x - La coordinata X dove si vuole muovere la Torre
     * @param y - La coordinata Y dove si vuole muovere la Torre
     * @return True se la Torre puo muoversi nelle coordinate X e Y, false altrimenti
    */
    private boolean percorsoTorre( Spazio s, int x, int y ){
        
        Pezzo pezzoCorrente = s.getOccupante();
        int xPezzoCorrente = s.getX();
        int yPezzoCorrente = s.getY();
        int temp;
        
        if( x == xPezzoCorrente ){ // Verifico se la posizione in cui si vuole spostare e valida
            
            temp = yPezzoCorrente;
            
            if( temp < y ){ // Se ce un pezzo che blocca il passaggio
                
                temp++;
                
                while( temp < y ){
                    
                    if( m[ x ][ temp ].eOccupato() ){
                        
                        return false;
                    
                    }
                    
                    temp++;
                
                }
            
            } else {
                
                temp--;
                
                while( temp > y ){
                    
                    if( m[ x ][ temp ].eOccupato() ){
                        
                        return false;
                    
                    }
                    
                    temp--;
                
                }
            
            }
            
            return true;
        
        }
        
        if( y == yPezzoCorrente ){
            
            temp = xPezzoCorrente;
            
            if( temp < y ){ // Verifico se ce un pezzo che blocca il passaggio
                
                temp++;
                
                while( temp < x ){
                    
                    if( m[ temp ][ y ].eOccupato() ){
                        
                        return false;
                    
                    }
                    
                    temp++;
                
                }
            
            } else {
                
                temp--;
                
                while( temp > x ){
                    
                    if( m[ temp ][ y ].eOccupato() ){
                        
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
     * Questo metodo valuta se, preso un'Alfiere e la posizione dove lo si vuole muovere, esso puo muoversi
     * @param s - Lo spazio dell'Alfiere contenente la sua posizione e proprieta
     * @param x - La coordinata X dove si vuole muovere l'Alfiere
     * @param y - La coordinata Y dove si vuole muovere l'Alfiere
     * @return True se l'Alfiere puo muoversi nelle coordinate X e Y, false altrimenti
    */
    private boolean percorsoAlfiere( Spazio s, int x, int y ){
        
        Pezzo pezzoCorrente = s.getOccupante();
        int xPezzoCorrente = s.getX();
        int yPezzoCorrente = s.getY();
        int temp1 = xPezzoCorrente;
        int temp2 = yPezzoCorrente;

        // Controllo validita della posizione in cui si vuole spostare
        
        if( ( ( double )( x - xPezzoCorrente ) / ( y - yPezzoCorrente ) ) == 1 ){ // Primo E Terzo Quadrante
            
            if( xPezzoCorrente < x ){ // Primo Quadrante
                
                temp1++;
                temp2++;
                
                while( temp1 < x ){
                    
                    if( m[ temp1 ][ temp2 ].eOccupato() ){
                        
                        return false;
                    
                    }
                    
                    temp1++;
                    temp2++;
                
                }
            
            } else if( xPezzoCorrente > x ){ // Terzo Quadrante
                
                temp1--;
                temp2--;
                
                while( temp1 < x ){
                    
                    if( m[ temp1 ][ temp2 ].eOccupato() ){
                        
                        return false;
                    
                    }
                    
                    temp1--;
                    temp2--;
                
                }
            
            }
            
            return true;
        
        }
        
        if( ( ( double )( x - xPezzoCorrente ) / ( y - yPezzoCorrente ) ) == -1 ){ // Secondo E Quarto Quadrante
            
            if( xPezzoCorrente < x ){
                
                temp1++;
                temp2--;
                
                while( temp1 < x ){
                    
                    if( m[ temp1 ][ temp2 ].eOccupato() ){
                        
                        return false;
                    
                    }
                    
                    temp1++;
                    temp2--;
                
                }
            
            } else if( xPezzoCorrente > x ){
                
                temp1--;
                temp2++;
                
                while( temp1 > x ){
                    
                    if( m[ temp1 ][ temp2 ].eOccupato() ){
                        
                        return false;
                    
                    }
                    
                    temp1--;
                    temp2++;
                
                }
            
            }
            
            return true;
        
        }
        
        return false;
    
    }
    
    /**
     * Questo metodo valuta se, preso un Pezzo qualsiasi e la posizione dove lo si vuole muovere, esso puo muoversi
     * @param s - Lo spazio del Pezzo contenente la sua posizione e proprieta
     * @param x - La coordinata X dove si vuole muovere il Pezzo
     * @param y - La coordinata Y dove si vuole muovere il Pezzo
     * @return True se il Pezzo puo muoversi nelle coordinate X e Y, false altrimenti
    */
    public boolean spostabileIn( Spazio s, int x, int y ){
        
        Pezzo pezzoCorrente = s.getOccupante();
        int xPezzoCorrente = s.getX();
        int yPezzoCorrente = s.getY();
        int tempTorre; // Variabile Temporale Per Torri
        int tempAlfiere; // Variabile Temporale Per Alfieri
        
        if( x == xPezzoCorrente && y == yPezzoCorrente ){
            
            return true;
        
        }
        
        if( m[ x ][ y ].eOccupato() ){ // Controllo se la posizione finale e vuota o contiene un pezzo del colore opposto
            
            if( !m[ x ][ y ].getOccupante().getColore().equals( s.getOccupante().getColore() ) ){
                
                if( pezzoCorrente instanceof Torre ){
                    
                    return percorsoTorre( s, x, y );
                
                }
                
                if( pezzoCorrente instanceof Alfiere ){
                    
                    return percorsoAlfiere( s, x, y );
                
                }
                
                if( pezzoCorrente instanceof Cavallo ){
                    
                    if( xPezzoCorrente != x && yPezzoCorrente != y ){  // Caso Base Per La Verifica Successiva
                        
                        double deltaX = abs( ( double ) x - xPezzoCorrente );
                        double deltaY = abs( ( double ) y - yPezzoCorrente );        
                        
                        if( ( deltaX + deltaY ) == 3 ){ // Funzione Di Verifica Per La Correttezza Della Posizione Di Un Cavallo ( Modulo Della Somma Dei Due Delta == 3 )
                            
                            return true;
                        
                        }
                    
                    }
                    
                    return false;
                
                }
                
                if( pezzoCorrente instanceof Regina ){
                    
                    return percorsoAlfiere( s, x, y ) || percorsoTorre( s, x, y );
                
                }
                
                if( pezzoCorrente instanceof Pedone ){
                    
                    return percorsoPedone( m[ xPezzoCorrente ][ yPezzoCorrente ], x, y, m );
                
                }
                
                if( pezzoCorrente instanceof Re ){
                    
                    if( x != xPezzoCorrente || y != yPezzoCorrente ){ // Escludo Il Caso Base
                        
                        if( ( x == xPezzoCorrente + 1 || x == xPezzoCorrente - 1 || x == xPezzoCorrente ) && ( y == yPezzoCorrente + 1 || y == yPezzoCorrente - 1 || y == yPezzoCorrente ) ){ // Prendo Le Posizioni Adiacenti Al Re
                            
                            if( !m[ x ][ y ].eOccupato() || ( m[ x ][ y ].eOccupato() && !m[ x ][ y ].getOccupante().getColore().equals( pezzoCorrente.getColore() ) ) ){
                                
                                return true;
                            
                            }
                        
                        }
                        
                        if( !( ( Re ) pezzoCorrente).mosso() ){
                            
                            if( x == xPezzoCorrente + 2 ){
                                
                                if( m[ 7 ][ y ].eOccupato() && m[ 7 ][ y ].getOccupante() instanceof Torre ){
                                    
                                    if( !( ( Torre ) m[ 7 ][ y ].getOccupante()).mosso() ){
                                        
                                        if( !m[ 5 ][ y ].eOccupato() && !m[ 6 ][ y ].eOccupato() ){
                                            
                                            return true;
                                        
                                        }
                                    
                                    }
                                
                                }
                            
                            }
                            
                            if( x == xPezzoCorrente - 2 ){
                                
                                if( m[ 0 ][ y ].eOccupato() && m[ 0 ][ y ].getOccupante() instanceof Torre ){
                                    
                                    if( !( ( Torre ) m[ 0 ][ y ].getOccupante() ).mosso() ){
                                        
                                        if( !m[ 1 ][ y ].eOccupato() && !m[ 2 ][ y ].eOccupato() && !m[ 3 ][ y ].eOccupato() ){
                                            
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
        
        if( !m[ x ][ y ].eOccupato() ){
            
            if( pezzoCorrente instanceof Torre ){
                
                return percorsoTorre( s, x, y );
            
            }
            
            if( pezzoCorrente instanceof Alfiere ){
                
                return percorsoAlfiere( s, x, y );
            
            }
            
            if( pezzoCorrente instanceof Cavallo ){
                
                if( xPezzoCorrente != x && yPezzoCorrente != y ){ // Caso Base Per La Verifica Successiva
                    
                    double deltaX = abs( ( double ) x - xPezzoCorrente );
                    double deltaY = abs( ( double ) y - yPezzoCorrente );
                    
                    if( ( deltaX + deltaY ) == 3 ){ // Funzione Di Verifica Per La Correttezza Della Posizione Di Un Cavallo ( Modulo Della Somma Dei Due Delta == 3 )
                        
                        return true;
                    
                    }
                
                }
                
                return false;
            
            }
            
            if( pezzoCorrente instanceof Regina ){
                
                return percorsoAlfiere( s, x, y ) || percorsoTorre( s, x, y );
            
            }
            
            if( pezzoCorrente instanceof Pedone ){
                
                return percorsoPedone( m[ xPezzoCorrente ][ yPezzoCorrente ], x, y, m );
            
            }
            
            if( pezzoCorrente instanceof Re ){
                
                if( x != xPezzoCorrente || y != yPezzoCorrente ){ // Escludo Il Caso Base
                    
                    if( ( x == xPezzoCorrente + 1 || x == xPezzoCorrente - 1 || x == xPezzoCorrente ) && ( y == yPezzoCorrente + 1 || y == yPezzoCorrente - 1 || y == yPezzoCorrente ) ){ // Prendo Le Posizioni Adiacenti Al Re
                        
                        if( !m[ x ][ y ].eOccupato() || ( m[ x ][ y ].eOccupato() && !m[ x ][ y ].getOccupante().getColore().equals( pezzoCorrente.getColore() ) ) ){
                            
                            return true;
                        
                        }
                    
                    }
                    
                    if( !( ( Re ) pezzoCorrente).mosso() ){ // Verifica Per L'Arrocco
                        
                        if( x == xPezzoCorrente + 2 ){
                            
                            if( m[ 7 ][ y ].eOccupato() && m[ 7 ][ y ].getOccupante() instanceof Torre ){
                                
                                if( !( ( Torre ) m[ 7 ][ y ].getOccupante() ).mosso() ){
                                    
                                    if( !m[ 5 ][ y ].eOccupato() && !m[ 6 ][ y ].eOccupato() ){
                                        
                                        return true;
                                    
                                    }
                                
                                }
                            
                            }
                        
                        }
                        
                        if( x == xPezzoCorrente - 2 ){
                            
                            if( m[ 0 ][ y ].eOccupato() && m[ 0 ][ y ].getOccupante() instanceof Torre ){
                                
                                if( !( ( Torre ) m[ 0 ][ y ].getOccupante() ).mosso() ){
                                    
                                    if( !m[ 1 ][ y ].eOccupato() && !m[ 2 ][ y ].eOccupato() && !m[ 3 ][ y ].eOccupato() ){
                                        
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
     * Questo metodo valuta se, preso uno Spazio, una matrice e la posizione dove lo si vuole muovere, esso puo muoversi
     * @param s - Lo spazio del Pezzo contenente la sua posizione e proprieta
     * @param x - La coordinata X dove si vuole muovere il Pezzo
     * @param y - La coordinata Y dove si vuole muovere il Pezzo
     * @param matrice - La matrice delle posizioni sulla scacchiera
     * @return True se il Pezzo puo muoversi nelle coordinate X e Y, false altrimenti
    */
    public boolean spostabileIn( Spazio s, int x, int y, Spazio[][] matrice ){
        
        Pezzo pezzoCorrente = s.getOccupante();
        int xPezzoCorrente = s.getX();
        int yPezzoCorrente = s.getY();
        int tempTorre; // Variabile Temporanea Per La Torre
        int tempAlfiere; // Variabile Temporanea Per L'Alfiere
        
        if( x == xPezzoCorrente && y == yPezzoCorrente ){ // Caso Non Considerato Nei Cicli Sotto
            
            return true;
        
        }
        
        if( !s.eOccupato() || !s.getOccupante().getColore().equals( pezzoCorrente.getColore() ) ){ // Controllo Se La Posizione Finale E Vuota O Contiene Un Pezzo Del Colore Opposto
            
            if( pezzoCorrente instanceof Torre ){
                
                return percorsoTorre( s, x, y );
            
            }
            
            if( pezzoCorrente instanceof Alfiere ){
                
                return percorsoAlfiere( s, x, y );
            
            }
            
            if( pezzoCorrente instanceof Cavallo ){
                
                if( xPezzoCorrente != x && yPezzoCorrente != y ){ // Caso Base Per La Verifica Successiva
                    
                    double deltaX = abs( ( double ) x - xPezzoCorrente );
                    double deltaY = abs( ( double ) y - yPezzoCorrente );
                    
                    if( ( deltaX + deltaY ) == 3 ){ // Funzione Di Verifica Per La Correttezza Della Posizione Di Un Cavallo ( Modulo Della Somma Dei Due Delta == 3 )
                        
                        return true;
                    
                    }
                
                }
                
                return false;
            
            }
            
            if( pezzoCorrente instanceof Regina ){
                
                return percorsoAlfiere( s, x, y ) || percorsoTorre( s, x, y );
            
            }
            
            if( pezzoCorrente instanceof Pedone ){
                
                return percorsoPedone( matrice[ xPezzoCorrente ][ yPezzoCorrente ], x, y, matrice );
            
            }
            
            if( pezzoCorrente instanceof Re ){
                
                if( x != xPezzoCorrente || y != yPezzoCorrente ){ // Escludo Il Caso Base
                    
                    if( ( x == xPezzoCorrente + 1 || x == xPezzoCorrente - 1 || x == xPezzoCorrente ) && ( y == yPezzoCorrente + 1 || y == yPezzoCorrente - 1 || y == yPezzoCorrente ) ){ // Prendo Le Posizioni Adiacenti Al Re
                        
                        if( !matrice[ x ][ y ].eOccupato() || ( matrice[ x ][ y ].eOccupato() && !matrice[ x ][ y ].getOccupante().getColore().equals( pezzoCorrente.getColore() ) ) ){
                            
                            return true;
                        
                        }
                    
                    }
                    
                    if( !( ( Re ) pezzoCorrente ).mosso() ){ // Verifica Per L'Arrocco
                        
                        if( x == xPezzoCorrente + 2 ){
                            
                            if( matrice[ 7 ][ y ].eOccupato() && matrice[ 7 ][ y ].getOccupante() instanceof Torre ){
                                
                                if( !( ( Torre ) matrice[ 7 ][ y ].getOccupante()).mosso() ){
                                    
                                    if( !matrice[ 5 ][ y ].eOccupato() && !matrice[ 6 ][ y ].eOccupato() ){
                                        
                                        return true;
                                    
                                    }
                                
                                }
                            
                            }
                        
                        }
                        
                        if( x == xPezzoCorrente - 2 ){
                            
                            if( matrice[ 0 ][ y ].eOccupato() && matrice[ 0 ][ y ].getOccupante() instanceof Torre ){
                                
                                if( !( ( Torre ) matrice[ 0 ][ y ].getOccupante() ).mosso() ){
                                    
                                    if( !matrice[ 1 ][ y ].eOccupato() && !matrice[ 2 ][ y ].eOccupato() && !matrice[ 3 ][ y ].eOccupato() ){
                                        
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
     * Questo metodo valuta se, preso una Pedone, la posizione dove lo si vuole muovere e la matrice delle posizioni, esso puo muoversi
     * @param s - Lo spazio del Pedone contenente la sua posizione e proprieta
     * @param x - La coordinata X dove si vuole muovere il Pedone
     * @param y - La coordinata Y dove si vuole muovere il Pedone
     * @param matrice - La matrice delle posizioni sulla scacchiera
     * @return True se il Pedone puo muoversi nelle coordinate X e Y, false altrimenti
    */
    private boolean percorsoPedone( Spazio s, int x, int y, Spazio[][]matrice ){
        
        Pezzo pezzoCorrente = s.getOccupante();
        int xPezzoCorrente = s.getX();
        int yPezzoCorrente = s.getY();
        boolean test;
        
        if( !matrice[ x ][ y ].eOccupato() ){
            
            if( x == xPezzoCorrente ){ // Un Pedone Puo Spostari Solo In Avanti Su Spazi Vuoti
                
                if( pezzoCorrente.getColore() instanceof Nero ){ // Il Pedone E Nero
                    
                    if( ( ( Pedone ) pezzoCorrente ).mosso() ){ // Se Il Pedone Si E Gia Mosso
                        
                        if( y == yPezzoCorrente + 1 ){
                            
                            return true;
                        
                        }
                    
                    } else { // Il Pedone Non Si E Mai Mosso
                        
                        if( y == yPezzoCorrente + 1 || y == yPezzoCorrente + 2 ){
                            
                            return true;
                        
                        }
                    
                    }
                
                } else { // Il Pedone E Bianco
                    
                    if( ( ( Pedone ) s.getOccupante() ).mosso() ){ // Il Pedone Si E Gia Mosso
                        
                        if( ( y == yPezzoCorrente - 1 ) && !matrice[ x ][ y ].eOccupato() ){
                            
                            return true;
                        
                        }
                    
                    } else if( y == yPezzoCorrente - 1 || y == yPezzoCorrente - 2 ) { // Il Pedone Non Si E Mai Mosso
                        
                        if( !matrice[ x ][ y ].eOccupato() ){
                            
                            return true;
                        
                        }
                    
                    }
                
                }
            
            }
            
            return false;
        
        } else { // Se La Posizione Finale E Occupata Da Un Pezzo Avversario
            
            if( x == xPezzoCorrente + 1 || x == xPezzoCorrente - 1 ){ // Posizione Consentita In Orizzontale
                
                if( pezzoCorrente.getColore() instanceof Nero ){ // Pedone Nero
                    
                    return y == yPezzoCorrente + 1;
                
                }
                
                if( pezzoCorrente.getColore() instanceof Bianco ){ // Pedone Bianco
                    
                    return y == yPezzoCorrente - 1;
                
                }
            
            }
            
            return false;
        
        }
    
    }
    
    // DA CONTROLLARE QUESTO METODO SE FUNZIONA FORSE HO AGGIUNTO PARENTESI DI TROPPO??????????????????????????????????????????
    
    /**
     * Questo metodo controlla quali e quanti pezzi sono spostabili su un determinato Spazio s
     * @param mat - La matrice delle posizioni sulla scacchiera
     * @param s - La casella su cui si vuole spostare
     * @param c - Il Colore del turno corrente
     * @return lista - La lista dei pezzi spostabili nello Spazio s
    */
    public LinkedList< Spazio > getPezziSpostabiliQui( Spazio[][] mat, Spazio s, Colore c ){
        
        LinkedList< Spazio > lista = new LinkedList<>();
        Spazio[][] matrice = mat;
        Spazio spazio = s;
        Colore colore = c;
        int x = spazio.getX();
        int y = spazio.getY();
        
        for( int j = 0; j < 8; j++ ){
            
            for( int i = 0; i < 8; i++ ){
                
                if( matrice[ i ][ j ].eOccupato() ){
                    
                    if( matrice[ i ][ j ].getOccupante().getColore().equals( colore ) ){
                        
                        if( spostabileIn( matrice[ i ][ j ], x, y, matrice ) ){
                            
                            lista.add( matrice[ i ][ j ] );
                        
                        }
                    
                    }
                
                }
            
            }
        
        }
        
        return lista;
    
    }
    
    // DA CONTROLLARE QUESTO METODO SE FUNZIONA FORSE HO AGGIUNTO PARENTESI DI TROPPO??????????????????????????????????????????
    
    /**
     * Questo metodo controlla quali e quanti pezzi sono spostabili su un determinato Spazio s
     * @param s - La casella su cui si vuole spostare
     * @param c - Il Colore del turno corrente
     * @return lista - La lista dei pezzi spostabili nello Spazio s
    */
    public LinkedList< Spazio > getPezziSpostabiliQui( Spazio s, Colore c ){
        
        LinkedList<Spazio> lista = new LinkedList<>();
        Spazio spazio = s;
        Colore colore = c;
        int x = spazio.getX();
        int y = spazio.getY();
        
        for( int j = 0; j < 8; j++ ){
            
            for( int i = 0; i < 8; i++ ){
                
                if( m[ i ][ j ].eOccupato() ){
                    
                    if( m[ i ][ j ].getOccupante().getColore().equals( colore ) ){
                        
                        if( spostabileIn( m[ i ][ j ], x, y ) ){
                            
                            lista.add( m[ i ][ j ] );
                        
                        }
                    
                    }
                
                }
            
            }
        
        }
        
        return lista;
    
    }
    
    /**
     * Questo metodo controlla quali sono i possibili Pezzi che possono essere mossi
     * affinche si tolga lo stato di scacco
     * @param xRe - La coordinata X del Re sotto scacco
     * @param yRe - La coordinata Y del Re sotto scacco
     * @param matrice - La matrice delle posizioni sulla scacchiera
     * @param turno - Il turno corrente
     * @return lista - La lista dei pezzi che possono proteggere il Re dallo scacco
    */
    public LinkedList< Spazio > getListaPezziChePrevengonoScacco( int xRe, int yRe, Spazio[][] matrice, Colore turno ){
        
        Spazio[][] matriceOriginale = coppiaMatrice( matrice );
        LinkedList< Spazio > listaAttaccanti;
        LinkedList< Spazio > listaSalvatori = new LinkedList<>();
        Spazio re = matriceOriginale[ xRe ][ yRe ];
        int[][] matricePosizioni = new int[ 8 ][ 8 ];
        int[][] percorsoAttaccante;
        boolean next;
        
        for( int i = 0; i < 8; i++ ){ // Scorro La Matrice
            
            for( int j = 0; j < 8; j++ ){
                
                matricePosizioni[ i ][ j ] = 0; // Azzerro La Matrice Delle Posizioni
            
            }
        
        }
        
        for( int i = 0; i < 8; i++ ){ // Scorro La Matrice
            
            for( int j = 0; j < 8; j++ ){
                
                if( j != xRe && i != yRe ){ // Prendo Gli Spazi Non Vuoti
                    
                    if( matriceOriginale[ j ][ i ].eOccupato() ){ // Prendo Tutti I Pezzi Del Colore Del Turno Corrente
                        
                        if( matriceOriginale[ j ][ i ].getOccupante().getColore().equals( turno ) ){ // Imposto A 1 Le Posizioni Dove I Pezzi Sono Del Colore Giusto
                            
                            matricePosizioni[ j ][ i ] = 1;
                        
                        }
                    
                    }
                
                }
            
            }
        
        }
        
        if( re.getOccupante().getColore() instanceof Bianco ){ // Guardo I Pezzi Del Colore Opposto Che Possono Fare Scacco Al Re
            
            listaAttaccanti = getPezziSpostabiliQui( matriceOriginale, matriceOriginale[ xRe ][ yRe ], new Nero() );
        
        } else {
            
            listaAttaccanti = getPezziSpostabiliQui( matriceOriginale, matriceOriginale[ xRe ][ yRe ], new Bianco() );
        
        }
        
        if( reSiSalvaDaScacco( re, matriceOriginale ) ){ // Se Il Re Riesce Ad Uscire Dallo Scacco Spostandosi Da Solo
            
            listaSalvatori.add( re );
        
        }
        
        if( listaAttaccanti.getFirst().equals( listaAttaccanti.getLast() ) ){
            
            if( !( ( listaAttaccanti.getFirst().getOccupante() instanceof Cavallo ) || ( listaAttaccanti.getFirst().getOccupante() instanceof Pedone ) ) ){
                
                percorsoAttaccante = getPercorsoAttaccante( listaAttaccanti.getFirst(), re );
                
                for( int i = 0; i < 8; i++ ){ // Scorro La Matrice
                    
                    for( int j = 0; j < 8; j++ ){
                        
                        if( matriceOriginale[ j ][ i ].eOccupato() ){ //prendo gli spazi non vuoti
                        
                            if( matriceOriginale[ j ][ i ].getOccupante().getColore().equals( turno ) ){ // Prendo Tutti I Pezzi Del Colore Del Turno Corrente
                                
                                if( matricePosizioni[ i ][ j ] == 1 ){
                                    
                                    next = false;
                                    
                                    for( int k = 0; k < 8; k++ ){
                                        
                                        for( int p = 0; p < 8; p++ ){
                                            
                                            if( percorsoAttaccante[ k ][ p ] == 1 && next == false ){
                                                
                                                if( spostabileIn( matrice[ i ][ j ], k, p ) ){
                                                    
                                                    listaSalvatori.add( matrice[ i ][ j ] );
                                                    next = true;
                                                
                                                }
                                            
                                            }
                                        
                                        }
                                    
                                    }
                                
                                }
                            
                            }
                        
                        }
                    
                    }
                
                }
            
            }
            
            for( int i = 0; i < 8; i++ ){ // Scorro La Matrice
                
                for( int j = 0; j < 8; j++ ){
                    
                    if( matricePosizioni[ i ][ j ] == 1 ){
                        
                        if( spostabileIn( matrice[ i ][ j ], listaAttaccanti.getFirst().getX(), listaAttaccanti.getFirst().getY(), m ) ){
                            
                            listaSalvatori.add( matrice[ i ][ j ] );
                        
                        }
                    
                    }
                
                }
            
            }
        
        }
        
        listaSalvatori.stream().forEach( ( elemento ) -> {} ); // CHE E STA ROBA???????????????????????????????????????????????????????????????
        
        return listaSalvatori;
    
    }
    
    /**
     * Questo metodo controlla quali sono i possibili Pezzi che possono essere mossi
     * affinche si tolga lo stato di scacco
     * @param xRe - La coordinata X del Re sotto scacco
     * @param yRe - La coordinata Y del Re sotto scacco
     * @param matrice - La matrice delle posizioni sulla scacchiera
     * @param turno - Il turno corrente
     * @return matriceRisultato - La matrice dei pezzi dei pezzi che possono proteggere il Re dallo scacco
    */
    public int[][] getMatricePezziChePrevengonoScacco( int xRe, int yRe, Spazio[][] matrice, Colore turno ){
        
        int matriceRisultato[][] = new int[ 8 ][ 8 ];
        LinkedList< Spazio > listaPezziPrevenzioneScacco = getListaPezziChePrevengonoScacco( xRe, yRe, matrice, turno );
        
        for( int i = 0; i < 8; i++ ){ // Scorro La Matrice
            
            for( int j = 0; j < 8; j++ ){
                
                matriceRisultato[ i ][ j ] = 0; // Azzerro La Matrice Risultato
            
            }
        
        }
        
        listaPezziPrevenzioneScacco.stream().forEach( ( pezzo ) -> { // CHE E STA ROBA???????????????????????????????????????????????????????
            
            matriceRisultato[ pezzo.getX() ][ pezzo.getY() ] = 1;
        
        });
        
        return matriceRisultato;
    
    }
    
    /**
     * Questo metodo controlla se il re può uscire da solo dallo stato di scacco
     * Su una matrice simulata, senza variare la matrice originale
     * @param s - Lo Spazio dove si trova il Re in esame
     * @param matrice - La matrice delle posizioni sulla scacchiera
     * @return True se il Re puo uscire da solo dallo stato di scacco, false altrimenti
    */
    private boolean reSiSalvaDaScacco( Spazio s, Spazio[][] matrice ){
        
        Re reInEsame = ( Re ) s.getOccupante();
        int xReInEsame = s.getX();
        int yReInEsame = s.getY();
        Spazio[][] matriceOriginale = coppiaMatrice( matrice );
        Spazio[][] matSimulata;
        boolean scacco = true;

        // Metodi Che Spostano Su Una Matrice Simulata Il Re Nelle Locazioni Possibili E Una Volta Spostate Verificano Se Il Re Rimane Ancora Sotto Scacco
        // Questi Metodi Sono Una Simulazione
        
        if( xReInEsame > 0 && yReInEsame > 0 ){
            
            if( !matrice[ xReInEsame - 1 ][ yReInEsame - 1 ].eOccupato() ){
                
                matSimulata = coppiaMatrice( matrice );
                
                spostaPezzo( matSimulata[ xReInEsame ][ yReInEsame ], xReInEsame - 1, yReInEsame - 1, matSimulata );
                
                if( controlloScacco( xReInEsame - 1, yReInEsame - 1, matSimulata ) == false ){
                    
                    scacco = true;
                
                } else {
                    
                    return true;
                
                }
            
            }
            
            if( matrice[ xReInEsame - 1 ][ yReInEsame - 1 ].eOccupato() ){
                
                if( !matrice[ xReInEsame - 1 ][ yReInEsame - 1 ].getOccupante().getColore().equals( reInEsame.getColore() ) ){
                    
                    matSimulata = coppiaMatrice( matrice );
                    
                    spostaPezzo( matSimulata[ xReInEsame ][ yReInEsame ], xReInEsame - 1, yReInEsame - 1, matSimulata );
                    
                    if( controlloScacco( xReInEsame - 1, yReInEsame - 1, matSimulata ) == false ){
                        
                        scacco = true;
                    
                    } else {
                        
                        return true;
                    
                    }
                
                }
            
            }
        
        }
        
        if( yReInEsame > 0 ){ // Sposto Il Re In Alto
            
            if( !matrice[ xReInEsame ][ yReInEsame - 1 ].eOccupato() ){
                
                matSimulata = coppiaMatrice( matrice );
                
                spostaPezzo( matSimulata[ xReInEsame ][ yReInEsame ], xReInEsame, yReInEsame - 1, matSimulata );
                
                if( controlloScacco( xReInEsame, yReInEsame - 1, matSimulata ) == false ){
                    
                    scacco = true;
                
                } else {
                    
                    return true;
                
                }
            
            }
            
            if( matrice[ xReInEsame ][ yReInEsame - 1 ].eOccupato() ){
                
                if( !matrice[ xReInEsame ][ yReInEsame - 1 ].getOccupante().getColore().equals( reInEsame.getColore() ) ){
                    
                    matSimulata = coppiaMatrice( matrice );
                    
                    spostaPezzo( matSimulata[ xReInEsame ][ yReInEsame ], xReInEsame, yReInEsame - 1, matSimulata );
                    
                    if( controlloScacco( xReInEsame, yReInEsame - 1, matSimulata ) == false ){
                        
                        scacco = true;
                    
                    } else {
                        
                        return true;
                    
                    }
                
                }
            
            }
        
        }
        
        if( xReInEsame < 7 && yReInEsame > 0 ){ // In Alto A Destra
            
            if( !matrice[ xReInEsame + 1 ][ yReInEsame - 1 ].eOccupato() ){
                
                matSimulata = coppiaMatrice( matrice );
                
                spostaPezzo( matSimulata[ xReInEsame ][ yReInEsame ], xReInEsame + 1, yReInEsame - 1, matSimulata );
                
                if( controlloScacco( xReInEsame + 1, yReInEsame - 1, matSimulata ) == false ){
                    
                    scacco = true;
                
                } else {
                    
                    return true;
                
                }
            
            }
            
            if( matrice[ xReInEsame + 1 ][ yReInEsame - 1 ].eOccupato() ){
                
                if( !matrice[ xReInEsame + 1 ][ yReInEsame - 1 ].getOccupante().getColore().equals( reInEsame.getColore() ) ){
                    
                    matSimulata = coppiaMatrice( matrice );
                    
                    spostaPezzo( matSimulata[ xReInEsame ][ yReInEsame ], xReInEsame + 1, yReInEsame - 1, matSimulata );
                    
                    if( controlloScacco( xReInEsame + 1, yReInEsame - 1, matSimulata ) == false ){
                        
                        scacco = true;
                    
                    } else {
                        
                        return true;
                    
                    }
                
                }
            
            }
        
        }
        
        if( xReInEsame > 0 ){ // A Sinistra
            
            if( !matrice[ xReInEsame - 1 ][ yReInEsame ].eOccupato() ){
                
                matSimulata = coppiaMatrice( matrice );
                
                spostaPezzo( matSimulata[ xReInEsame ][ yReInEsame ], xReInEsame - 1, yReInEsame, matSimulata );
                
                if( controlloScacco( xReInEsame - 1, yReInEsame, matSimulata ) == false ){
                    
                    scacco = true;
                
                } else {
                    
                    return true;
                
                }
            
            }
            
            if( matrice[ xReInEsame - 1 ][ yReInEsame ].eOccupato() ){
                
                if( !matrice[ xReInEsame - 1 ][ yReInEsame ].getOccupante().getColore().equals( reInEsame.getColore() ) ){
                    
                    matSimulata = coppiaMatrice( matrice );
                    
                    spostaPezzo( matSimulata[ xReInEsame ][ yReInEsame ], xReInEsame - 1, yReInEsame, matSimulata );
                    
                    if( controlloScacco( xReInEsame - 1, yReInEsame, matSimulata ) == false ){
                        
                        scacco = true;
                    
                    } else {
                        
                        return true;
                    
                    }
                
                }
            
            }
        
        }
        
        if( xReInEsame < 7 ){ // A Destra
            
            if( !matrice[ xReInEsame + 1 ][ yReInEsame ].eOccupato() ){
                
                matSimulata = coppiaMatrice( matrice );
                
                spostaPezzo( matSimulata[ xReInEsame ][ yReInEsame ], xReInEsame + 1, yReInEsame, matSimulata );
                
                if( controlloScacco( xReInEsame + 1, yReInEsame, matSimulata ) == false ){
                    
                    scacco = true;
                
                } else {
                    
                    return true;
                
                }
            
            }
            
            if( matrice[ xReInEsame + 1 ][ yReInEsame ].eOccupato() ){
                
                if( !matrice[ xReInEsame + 1 ][ yReInEsame ].getOccupante().getColore().equals( reInEsame.getColore() ) ){
                    
                    matSimulata = coppiaMatrice( matrice );
                    
                    spostaPezzo( matSimulata[ xReInEsame ][ yReInEsame ], xReInEsame + 1, yReInEsame, matSimulata );
                    
                    if( controlloScacco( xReInEsame + 1, yReInEsame, matSimulata ) == false ){
                        
                        scacco = true;
                    
                    } else {
                        
                        return true;
                    
                    }
                
                }
            
            }
        
        }
        
        if( yReInEsame < 7 && xReInEsame > 0 ){ // In Basso A Sinistra
            
            if( !matrice[ xReInEsame - 1 ][ yReInEsame + 1 ].eOccupato() ){
                
                matSimulata = coppiaMatrice( matrice );
                
                spostaPezzo( matSimulata[ xReInEsame ][ yReInEsame ], xReInEsame - 1, yReInEsame + 1, matSimulata );
                
                if( controlloScacco( xReInEsame - 1, yReInEsame + 1, matSimulata ) == false ){
                    
                    scacco = true;
                
                } else {
                    
                    return true;
                
                }
            
            }
            
            if( matrice[ xReInEsame - 1 ][ yReInEsame + 1 ].eOccupato() ){
                
                if( !matrice[ xReInEsame - 1 ][ yReInEsame + 1 ].getOccupante().getColore().equals( reInEsame.getColore() ) ){
                    
                    matSimulata = coppiaMatrice( matrice );
                    
                    spostaPezzo( matSimulata[ xReInEsame ][ yReInEsame ], xReInEsame - 1, yReInEsame + 1, matSimulata );
                    
                    if( controlloScacco( xReInEsame - 1, yReInEsame + 1, matSimulata ) == false ){
                        
                        scacco = true;
                    
                    } else {
                        
                        return true;
                    
                    }
                
                }
            
            }
        
        }
        
        if( yReInEsame < 7 ){ // In Basso
            
            if( !matrice[ xReInEsame ][ yReInEsame + 1 ].eOccupato() ){
                
                matSimulata = coppiaMatrice( matrice );
                
                spostaPezzo( matSimulata[ xReInEsame ][ yReInEsame ], xReInEsame, yReInEsame + 1, matSimulata );
                
                if( controlloScacco( xReInEsame, yReInEsame + 1, matSimulata ) == false ){
                    
                    scacco = true;
                
                } else {
                    
                    return true;
                
                }
            
            }
            
            if( matrice[ xReInEsame ][ yReInEsame + 1 ].eOccupato() ){
                
                if( !matrice[ xReInEsame ][ yReInEsame + 1 ].getOccupante().getColore().equals( reInEsame.getColore() ) ){
                    
                    matSimulata = coppiaMatrice( matrice );
                    
                    spostaPezzo( matSimulata[ xReInEsame ][ yReInEsame ], xReInEsame, yReInEsame + 1, matSimulata );
                    
                    if( controlloScacco( xReInEsame, yReInEsame + 1, matSimulata ) == false ){
                        
                        scacco = true;
                    
                    } else {
                        
                        return true;
                    
                    }
                
                }
            
            }
        
        }
        
        if( yReInEsame < 7 && xReInEsame < 7 ){ // In Basso A Destra
            
            if( !matrice[ xReInEsame + 1 ][ yReInEsame + 1 ].eOccupato() ){
                
                matSimulata = coppiaMatrice( matrice );
                
                spostaPezzo( matSimulata[ xReInEsame ][ yReInEsame ], xReInEsame + 1, yReInEsame + 1, matSimulata );
                
                if( controlloScacco( xReInEsame + 1, yReInEsame + 1, matSimulata ) == false ){
                    
                    scacco = true;
                
                } else {
                    
                    return true;
                
                }
            
            }
            
            if( matrice[ xReInEsame + 1 ][ yReInEsame + 1 ].eOccupato() ){
                
                if( !matrice[ xReInEsame + 1 ][ yReInEsame + 1 ].getOccupante().getColore().equals( reInEsame.getColore() ) ){
                    
                    matSimulata = coppiaMatrice( matrice );
                    
                    spostaPezzo( matSimulata[ xReInEsame ][ yReInEsame ], xReInEsame + 1, yReInEsame + 1, matSimulata );
                    
                    if( controlloScacco( xReInEsame + 1, yReInEsame + 1, matSimulata ) == false ){
                        
                        scacco = true;
                    
                    } else {
                        
                        return true;
                    
                    }
                
                }
            
            }
        
        }
        
        return false;
    
    }
    
    /**
     * Questo metodo aggiorna l'InterfacciaGrafica
     * @param i - L'InterfacciaGrafica da aggiornare
    */
    public void setInterfacciaGrafica( InterfacciaGrafica i ){
        
        ig = i;
    
    }
    
    /**
     * Questo metodo aggiorna la matrice che viene rappresentata sulla scacchiera
     * @param matrice - La matrice da rappresentare sulla scacchiera
    */
    public void setMatrice( Spazio[][] matrice ){
        
        for( int i = 0; i < 8; i++ ){ // Scorro La Matrice
            
            for( int j = 0; j < 8; j++ ){
                
                m[ i ][ j ] = new Spazio( matrice[ i ][ j ]);
            
            }
        
        }
    
    }
    
    /**
     * Questo metodo viene eseguito quando il re non puo essere salvato, ovvero si trova in scacco matto
     * @param turnoCorrente - Il Colore del giocatore corrente
     * @return True ??????????????????????????????????????????????????????????????????????????????????
    */
    public boolean scaccoMatto( Colore turnoCorrente ){
        
        if( turnoCorrente instanceof Bianco ){
            
            return !getSpazioReNero().eOccupato();
        
        } else {
            
            return !getSpazioReBianco().eOccupato();
        
        }
    
    }
    
    // ???????????????????????????????????????????????????????????????????????????????????????????????
    
    /**
     * Questo metodo segna il percorso che un'attacante puo fare
     * @param att - Lo Spazio dove si trova l'attaccante
     * @param r - Gli Spazi dove si puo muovere l'attaccante
     * @return matriceRisultato - La matrice che rappresenta il percorso dell'attaccante
    */
    private int[][] getPercorsoAttaccante( Spazio att, Spazio r ){
        
        Pezzo attaccante = att.getOccupante();
        int xAttaccante = att.getX();
        int yAttaccante = att.getY();
        
        Pezzo re = r.getOccupante();
        int xRe = r.getX();
        int yRe = r.getY();
        
        int[][] matriceRisultato = new int[ 8 ][ 8 ];
        int temp1, temp2;
        
        for( int i = 0; i < 8; i++ ){ // Scorro La Matrice
            
            for( int j = 0; j < 8; j++ ){
                
                matriceRisultato[ i ][ j ] = 0; // Azzerro La Matrice Risultato
            
            }
        
        }
        
        if( attaccante instanceof Torre || attaccante instanceof Regina ){
            
            if( xRe == xAttaccante){
                
                for( int i = 0; i < 8; i++ ){
                    
                    if( ( i > yRe && i < yAttaccante ) || ( i > yAttaccante && i < yRe ) ){
                        
                        matriceRisultato[ xRe ][ i ] = 1;
                    
                    }
                
                }
            
            } else {
                
                for( int i = 0; i < 8; i++ ){
                    
                    if( ( i > xRe && i < xAttaccante ) || ( i > xAttaccante && i < xRe ) ){
                        
                        matriceRisultato[ i ][ yRe ] = 1;
                    
                    }
                
                }
            
            }
        
        }
        
        if( attaccante instanceof Alfiere || attaccante instanceof Regina ){
            
            temp1 = xAttaccante;
            temp2 = yAttaccante;
            
            if( xAttaccante > xRe && yAttaccante > yRe ){
                
                temp1--;
                temp2--;
                
                while( temp1 > xRe ){
                    
                    matriceRisultato[ temp1 ][ temp2 ] = 1;
                    temp1--;
                    temp2--;
                
                }
            
            }
            
            if( xAttaccante < xRe && yAttaccante > yRe ){
                
                temp1++;
                temp2--;
                
                while( temp1 < xRe ){
                    
                    matriceRisultato[ temp1 ][ temp2 ] = 1;
                    temp1++;
                    temp2--;
                
                }
            
            }
            
            if( xAttaccante < xRe && yAttaccante < yRe ){
                
                temp1++;
                temp2++;
                
                while( temp1 < xRe ){
                    
                    matriceRisultato[ temp1 ][ temp2 ] = 1;
                    temp1++;
                    temp2++;
                
                }
            
            }
            
            if( xAttaccante > xRe && yAttaccante < yRe ){
                
                temp1--;
                temp2++;
                
                while( temp1 > xRe ){
                    
                    matriceRisultato[ temp1 ][ temp2 ] = 1;
                    temp1--;
                    temp2++;
                
                }
            
            }
        
        }
        
        return matriceRisultato;
    
    }
    
    /**
     * Questo metodo sposta un un pezzo sulla scacchiera
     * @param s - Lo Spazio contenente il pezzo da spostare
     * @param x - La coordinata X del Pezzo da spostare
     * @param y - La coordinata Y del Pezzo da spostare
     * @param scacchiera - La matrice degli spazi su cui spostare il Pezzo
    */
    public void spostaPezzo( Spazio s, int x, int y , Spazio[][] scacchiera ){
        
        Pezzo pezzoCorrente = s.getOccupante();
        int xPezzoCorrente = s.getX();
        int yPezzoCorrente = s.getY();
     
        s.setX( x );
        s.setY( y );
        
        scacchiera[ xPezzoCorrente ][ yPezzoCorrente ] = new Spazio( xPezzoCorrente, yPezzoCorrente );
        scacchiera[ x ][ y ] = new Spazio( x, y, s.getOccupante() );  
        
        if( scacchiera[ x ][ y ].getOccupante() instanceof Pedone ){
            
            if( !( ( Pedone ) scacchiera[ x ][ y ].getOccupante() ).mosso() ){
                
                ( ( Pedone ) scacchiera[ x ][ y ].getOccupante() ).impostaMosso();
            
            }
            
            if( scacchiera[ x ][ y ].getOccupante().getColore() instanceof Bianco ){ // Promozione Pedone Bianco
                
                if( y == 0 ){ // Se Il Pedone Bianco Arriva In Fondo Alla Scacchiera
                    
                    ig.promozionePedone( scacchiera[ x ][ y ], new Bianco(), scacchiera );
                
                }
            
            } else { // Promozione Pedone Nero
                
                if( y == 7 ){ // Se Il Pedone Nero Arriva In Fondo Alla Scacchiera
                    
                    ig.promozionePedone( scacchiera[ x ][ y ], new Nero(), scacchiera );
                
                }
            
            }
        
        }
        
        if( scacchiera[ x ][ y ].getOccupante() instanceof Torre ){
            
            ( ( Torre ) scacchiera[ x ][ y ].getOccupante() ).impostaMosso();
            
            if( !( ( Torre ) scacchiera[ x ][ y ].getOccupante() ).mosso() ){
                
                ( ( Torre ) scacchiera[ x ][ y ].getOccupante() ).impostaMosso();
            
            }
        
        }
        
        if( scacchiera[ x ][ y ].getOccupante() instanceof Re ){
            
            if( !( ( Re ) scacchiera[ x ][ y ].getOccupante() ).mosso() ){
                
                ( ( Re ) scacchiera[ x ][ y ].getOccupante() ).impostaMosso();
                
                if( x == xPezzoCorrente + 2 ){
                    
                    if( m[ 7 ][ y ].eOccupato() && m[ 7 ][ y ].getOccupante() instanceof Torre ){
                        
                        if( !( ( Torre ) m[ 7 ][ y ].getOccupante() ).mosso() ){
                            
                            if( !m[ 5 ][ y ].eOccupato() ){
                                
                                spostaPezzo( m[ 7 ][ y ], x - 1, y, scacchiera );
                            
                            }
                        
                        }
                    
                    }
                
                }
                
                if( x == xPezzoCorrente - 2 ){
                    
                    if( m[ 0 ][ y ].eOccupato() && m[ 0 ][ y ].getOccupante() instanceof Torre ){
                        
                        if( !( ( Torre ) m[ 0 ][ y ].getOccupante() ).mosso() ){
                            
                            if( !m[ 1 ][ y ].eOccupato() && !m[ 3 ][ y ].eOccupato() ){
                                
                                spostaPezzo( m[ 0 ][ y ], x + 1, y, scacchiera );
                            
                            }
                        
                        }
                    
                    }
                
                }
            
            }
        
        }
    
    }
    
    /**
     * Questo metodo sposta un un pezzo sulla scacchiera
     * @param s - Lo Spazio contenente il pezzo da spostare
     * @param x - La coordinata X del Pezzo da spostare
     * @param y - La coordinata Y del Pezzo da spostare
    */
    public void spostaPezzo( Spazio s, int x, int y ){
        
        int xp = s.getX();
        int yp = s.getY();

        s.setX( x );
        s.setY( y );

        m[ x ][ y ] = new Spazio( x, y, s.getOccupante() );
        m[ xp ][ yp ] = new Spazio( xp, yp );
        
        if( s.getOccupante() instanceof Pedone ){
            
            if( !( ( Pedone ) s.getOccupante() ).mosso() ){
                
                ( ( Pedone ) s.getOccupante() ).impostaMosso();
            
            }
            
            if( m[ x ][ y ].getOccupante().getColore() instanceof Bianco ){ // Promozione Pedone Bianco
                                    
                if( y == 0 ){ // Se Il Pedone Bianco E In Fondo Alla Scacchiera
                    
                    ig.promozionePedone( m[ x ][ y ], new Bianco(), m );
                
                }
            
            } else { // Promozione Pedone Nero
                
                if( y == 7 ){ // Se Il Pedone Nero E In Fondo Alla Scacchiera
                    
                    ig.promozionePedone( m[ x ][ y ], new Nero(), m );
                
                }
            
            }
        
        }
    
    }
    
    /**
     * Questo metodo prende una matrice di spazi e la copia senza mantenere le referenze
     * @param matrice - La matrice da copiare
     * @return mat - La coppia della matrice originale senza referenze ad essa
    */
    public Spazio[][] coppiaMatrice( Spazio[][] matrice ){
        
        Spazio[][] mat = new Spazio[ 8 ][ 8 ];
        
        for( int i = 0; i < 8; i++ ){ // Scorro La Matrice
            
            for( int j = 0; j < 8; j++ ){
                
                if( matrice[ i ][ j ].eOccupato() ){
                    
                    mat[ i ][ j ] = new Spazio( i, j, matrice[ i ][ j ].getOccupante() );
                
                } else {
                    
                    mat[ i ][ j ] = new Spazio( i, j );
                
                }
            
            }
        
        }
        
        return mat;
    
    }
    
    /**
     * Questo metodo disegna la matrice tramite System.Out.Print
    */
    public void disegnaMatriceSpaziOccupati(){
        
        for( int i = 0; i < 8; i++ ){ // Scorro La Matrice
            
            for( int j = 0; j < 8; j++ ){
                
                if( m[ j ][ i ].eOccupato() ){
                    
                    System.err.print( "x" );
                
                } else {
                    
                    System.err.print( "_" );
                
                }
            
            }
            
            System.err.println( "" );
        
        }
        
        System.err.println("");
    
    }
    
    /**
     * Questo metodo prende una matrice di Spazi e la rappresenta tramite le System.Out.Print
     * @param matrice - La matrice da rappresentare tramite System.Out.Print
    */
    public void disegnaMatriceSpaziOccupati( Spazio[][] matrice ){
        
        System.err.println( "" );
        
        for( int i = 0; i < 8; i++ ){ // Scorro La Matrice
            
            for( int j = 0; j < 8; j++ ){
                
                if( matrice[ j ][ i ].eOccupato() ){
                    
                    System.err.print( "x" );
                
                } else {
                    
                    System.err.print( "_" );
                
                }
            
            }
            
            System.err.println( "" );
        
        }
        
        System.err.println( "" );
    
    }

} // Fine Classe GestoreMovimenti