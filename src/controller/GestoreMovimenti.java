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
    
    // RIMASTO QUAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
    
    /**
     * Metodo che verifica e stabilisce i percorsi possibili sulla scacchiera di un Pedone
     * @param s - Lo spazio dove il pedone vuole muoversi
     * @return na matrice di interi che rappresenta i movimenti del pedone
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
               
                scacchiera[ x ][ y - 1 ] = 1;
            }
            
            //2 caselle più in alto
            if( !m[ x ][ y - 2 ].eOccupato() && p.mosso()){
         
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
            if( ( x + 1 ) <= 7 ){ // Controllo Posizione Valida ( Probabilmente Ridondante )
                
                if( m[ x + 1 ][ y - 1 ].eOccupato()  ){
                    if(m[ x + 1 ][ y - 1 ].getOccupante().getColore() instanceof Nero)
                        scacchiera[ x + 1 ][ y - 1 ] = 1;
                
                }
            
            }
        
        } else { // Fine IstanceOf Bianco, Inizio Caso IstanceOf Nero
            
            if( !m[ x ][ y + 1 ].eOccupato()){
              
                scacchiera[ x ][ y + 1 ] = 1;
            }
            //2 caselle in basso
            if( !m[ x ][ y + 2 ].eOccupato() && p.mosso() ){
        
                scacchiera[ x ][ y + 2 ] = 1;
            }
            
            if( ( x - 1 ) >= 0 ) // Controllo Posizione Valida ( Probabilmente Ridondante )
                
                if( m[ x - 1 ][ y + 1 ].eOccupato()  ){
                    if(m[ x - 1 ][ y + 1 ].getOccupante().getColore() instanceof Nero)
                        scacchiera[ x + 1 ][ y + 1 ] = 1;
                
                }
            
            if( ( x + 1 ) <= 7 ){ // Controllo Posizione Valida ( Probabilmente Ridondante )
                
                if( m[ x + 1 ][ y + 1 ].eOccupato()  ){
                    if(m[ x + 1 ][ y + 1 ].getOccupante().getColore() instanceof Nero)
                        scacchiera[ x + 1 ][ y + 1 ] = 1;
                
                }
            
            }
        
        }
        
        return scacchiera;
    
    } // Fine movimentiPedone
    
    /**
     * Segna una matrice che rappresenta la scacchiera con le posizioni disponibili al movimento della torre
     * @param s dove si trova la torre
     * @return una matrce di interi con segnate le posizioni dove si può muovere la torre     */
    private int[][] movimentiTorre( Spazio s ){
        Torre torre;
        int[][] scacchiera = new int[ 8 ][ 8 ];
        int x, y;
        int temp;
        torre=(Torre)s.getOccupante();
        x = s.getX();
        y = s.getY();

        // Direzione Verso Destra
        if( x + 1 <= 7 ){

            // Spazi Liberi A Destra
            for( temp = x + 1; temp <= 7 && !( m[ temp ][ y ].eOccupato()); temp++ ){
                
                scacchiera[ temp ][ y ] = 1;
            
            }

            // Primo Spazio Occupato A Destra
            temp++;
            
            if( temp <= 7 ){ // Controllo Per Non Andare Fuori Scachiera

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
        if( y + 1 <= 7 ){
            
            for( temp = y + 1; temp <= 7 && ! ( m[ x ][ temp ].eOccupato()); temp++ ){
                
                scacchiera[ x ][ temp ] = 1;
            
            }
            
            temp++;
            
            if( temp <= 7 ){ // Controllo Per Non Uscire Fuori Dalla Scachiera

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
    
    } // Fine movimentiTorre
    
    /**
     * Segna una matrice che rappresenta la scacchiera con le posizioni disponibili al movimento dell'alfiere
     * @param s dove si trova l'alfiere
     * @return una matrce di interi con segnate le posizioni dove si può muovere 'alfiere
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
        while( temp1 <= 7 && temp2 <= 7 && !m[ temp1 ][ temp2 ].eOccupato() ){
            
            scacchiera[ temp1 ][ temp2 ] = 1;
            temp1++;
            temp2++;
        
        }
        
        if( temp1 <=7 && temp2 <= 7 ){

                // Se Lo Spazzio E Occupato E Contiene Un Pezzo Del Colore Opposto
                if( m[ temp1 ][ temp2 ].eOccupato() ){
                    if(!m[ temp1 ][ temp2 ].getOccupante().getColore().equals( alfiere.getColore() ))
                        scacchiera[ temp1 ][ temp2 ] = 1;
                
                }
        
        }

        // Direzione Verso Basso Destra
        temp1 = x + 1;
        temp2 = y - 1;
        
        while( temp1 <= 7 && temp2 >= 0 && !m[ temp1 ][ temp2 ].eOccupato() ){
            
            scacchiera[ temp1 ][ temp2 ] = 1;
            temp1++;
            temp2--;
        
        }
        
        if( temp1 <= 7 && temp2 >= 0 ){

            // Se Lo Spazzio E Occupato E Contiene Un Pezzo Del Colore Opposto
            if( m[ temp1 ][ temp2 ].eOccupato() ){
                if(!m[ temp1 ][ temp2 ].getOccupante().getColore().equals( alfiere.getColore() ))
                    scacchiera[ temp1 ][ temp2 ] = 1;
            }
        
        }

        // Direzione Alto Sinistra
        temp1 = x - 1;
        temp2 = y + 1;
        
        while( temp1 >= 0 && temp2 <= 7 && !m[ temp1 ][ temp2 ].eOccupato() ){
            
            scacchiera[ temp1 ][ temp2 ] = 1;
            temp1--;
            temp2++;
        
        }
        
        if( temp1 >= 0 && temp2 <= 7 ){

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
    
    } // Fine MovimentiAlfiere
/**
     * Segna una matrice che rappresenta la scacchiera con le posizioni disponibili al movimento del cavallo
     * @param s dove si trova il cavallo
     * @return una matrce di interi con segnate le posizioni dove si può muovere il cavallo
     */
    private int[][] movimentiCavallo( Spazio s ){
        Cavallo cavallo;
        int[][] scacchiera = new int[ 8 ][ 8 ];
        int x, y;
        int temp;
        cavallo=(Cavallo) s.getOccupante();
        x = s.getX();
        y = s.getY();
        
        // Alto Destra
        if( x + 1 <= 7 && y + 2 <= 7 ){ // Libero O Del Avversario
            
            if( !m[ x + 1 ][ y + 2 ].eOccupato() || ( m[ x + 1 ][ y + 2 ].eOccupato() ) ){
                if(!m[ x + 1 ][ y + 2 ].getOccupante().getColore().equals( cavallo.getColore() ))
                    scacchiera[ x + 1 ][ y + 2 ] = 1;
            
            }
        
        }
        
        // Alto Sinistra
        if( x - 1 >= 0 && y + 2 <= 7 ){ // Libero O Del Avversario
            
            if( !m[ x - 1 ][ y + 2 ].eOccupato() || ( m[ x - 1 ][ y + 2 ].eOccupato()  ) ){
                if(!m[ x - 1 ][ y + 2 ].getOccupante().getColore().equals( cavallo.getColore() ))
                    scacchiera[ x - 1 ][ y + 2 ] = 1;
            
            }
        
        }

        // Destra Alto
        if( x + 2 <= 7 && y + 1 <= 7 ){ // Libero O Del Avversario
            
            if( !m[ x + 2 ][ y + 1 ].eOccupato() || ( m[ x + 2 ][ y + 1 ].eOccupato() ) ){
                if(!m[ x + 2 ][ y + 1 ].getOccupante().getColore().equals( cavallo.getColore() ))
                    scacchiera[ x + 2 ][ y + 1 ] = 1;
            
            }
        
        }

        // Destra Basso
        if( x + 2 <= 7 && y - 1 >= 0 ){ // Libero O Del Avversario
            
            if( !m[ x + 2 ][ y - 1 ].eOccupato() || ( m[ x + 2 ][ y - 1 ].eOccupato()  ) ){
                if(!m[ x + 2 ][ y - 1 ].getOccupante().getColore().equals( cavallo.getColore() ))
                    scacchiera[ x + 2 ][ y - 1 ] = 1;
            
            }
        
        }

        // Sinistra Alto
        if( x - 2 >= 0 && y + 1 <= 7 ){ // Libero O Del Avversario
            
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
        if( x + 1 <= 7 && y - 2 >= 0 ){ // Libero O Del Avversario
            
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
    
    /**
     * Segna una matrice che rappresenta la scacchiera con le posizioni disponibili al movimento della regina
     * @param s dove si trova la regina
     * @return una matrce di interi con segnate le posizioni dove si può muovere la regina
     */
    private int[][] movimentiRegina( Spazio s ){
        
        Regina regina;
        int[][] scacchiera = new int[ 8 ][ 8 ];
        int[][] m1;
        int[][] m2;
        
        int x, y;
        regina = (Regina) s.getOccupante();
        x = s.getX();
        y = s.getY();
        
        m1 = movimentiTorre( new Spazio(x, y ,new Torre( regina.getColore() )) );
        m2 = movimentiAlfiere( new Spazio(x, y, new Alfiere( regina.getColore() )) );
        
        // Combinazione Dei Risultati Della Torre E Alfiere
        for( int i = 0; i < 7; i++ ){
            
            for( int j = 0; j < 7; j++ ){
                
                if( m1[ i ][ j ] == 1 || m2[ i ][ j ] == 1 ){
                    
                    scacchiera[ i ][ j ] = 1;
                
                }
            
            }
        
        }
        
        return scacchiera;
        // Throw New UnsupportedOperationException
    
    } // Fine movimentoRegina
    
    /**
     * Questo metodo è diviso in più parti
     * Determina se c'è scacco matto
     * @param x ascissa dove si trova il re
     * @param y ordinata dove si trova il re
     * @param matrix maticce che simula la scacchiera
     * @return ritorna true se c'è scacco
    */
    public boolean controlloScacco( int x, int y,Spazio[][] matrix){
        Spazio[][] mat=coppiaMatrice(matrix);
        Colore colore=mat[x][y].getOccupante().getColore();
        int temp1, temp2; 
        
        // Controllo Per Torri / Regine In Orizzontale E Verticale
        
        // Verso Destra
        boolean uscita = false;
        for( int i = x+1; i <= 7 && !uscita; i++ ){
            if( mat[ i ][ y ].eOccupato() ){  
                uscita = true; // Uscita Dal Ciclo Dopo Aver Controllato Il Primo Spazio Per Non Fare Controlli Inutili
                if( !mat[ i ][ y ].getOccupante().getColore().equals( colore )){
                    if( mat[ i ][ y ].getOccupante() instanceof Torre || mat[ i ][ y ].getOccupante() instanceof Regina ){
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
                        return false;
                    }
                }
            }
        }

        // Verso il basso
        uscita = false;
        
        for( int i = y+1; i <= 7 && !uscita; i++ ){
            if( mat[ x ][ i ].eOccupato() ){ 
                uscita = true; // Uscita Dal Ciclo Dopo Aver Controllato Il Primo Spazio Per Non Fare Controlli Inutili
                if( !mat[ x ][ i ].getOccupante().getColore().equals( colore ) ){  
                    if( mat[ x ][ i ].getOccupante() instanceof Torre || mat[ x ][ i ].getOccupante() instanceof Regina ){
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
                        return false;
                    
                    }
                
                }
            
            }
        
        }

        // Controlli In Diagonale Per Alfieri E Regine

        // Verso L'Alto A Destra
        temp1 = x + 1;
        temp2 = y + 1;
        
        while( temp1 <= 7 && temp2 <= 7 && !mat[ temp1 ][ temp2 ].eOccupato() ){          
            temp1++;
            temp2++;
        }
        
        if( temp1 <= 7 && temp2 <= 7  ){
            if(!mat[ temp1 ][ temp2 ].getOccupante().getColore().equals( colore )){
                if( mat[ temp1 ][ temp2 ].getOccupante() instanceof Alfiere || mat[ temp1 ][ temp2 ].getOccupante() instanceof Regina ){
                    return false;
                }
            }
        
        }

        // Verso L'Alto A Sinistra
        temp1 = x - 1;
        temp2 = y + 1;
        
        while( temp1 >= 0 && temp2 <= 7 && !mat[ temp1 ][ temp2 ].eOccupato() ){        
            temp1--;
            temp2++;
        }
        
        if( temp1 >= 0 && temp2 <= 7 && !mat[ temp1 ][ temp2 ].getOccupante().getColore().equals( colore ) ){
            
            if( mat[ temp1 ][ temp2 ].getOccupante() instanceof Alfiere || mat[ temp1 ][ temp2 ].getOccupante() instanceof Regina ){
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
                    return false;
            
                }
            }
        }

        // Verso Il Basso A Destra
        temp1 = x + 1;
        temp2 = y - 1;
        
        while( temp1 <= 7 && temp2 >= 0 && !mat[ temp1 ][ temp2 ].eOccupato() ){
            
            temp1++;
            temp2--;
        
        }
        
        if( temp1 <= 7 && temp2 >= 0){
            if(!mat[ temp1 ][ temp2 ].getOccupante().getColore().equals( colore ) ){
                if( mat[ temp1 ][ temp2 ].getOccupante() instanceof Alfiere || mat[ temp1 ][ temp2 ].getOccupante() instanceof Regina ){
                    return false;
            
                }
            }
        }

        // Controlli Per Il Pedone ( Potrebbe Essere Integrato Nell'Alfiere In Casi Specifici )
        
        if( colore instanceof Nero ){ // Inizio Controllo Pedoni Neri
            
            if( x + 1 <= 7 && y + 1 <= 7 && mat[ x + 1 ][ y + 1 ].eOccupato() && mat[ x + 1 ][ y + 1 ].getOccupante() instanceof Pedone ){
                
                if( !mat[ x + 1 ][ y + 1 ].getOccupante().getColore().equals( colore ) ){

                    return false;
                
                }
            
            }
            if(x - 1 >= 0 && y + 1 <= 7){
                if(mat[ x - 1 ][ y + 1 ].eOccupato()){
                    if( mat[ x - 1 ][ y + 1 ].getOccupante() instanceof Pedone ){
                
                        if( !mat[ x - 1 ][ y + 1 ].getOccupante().getColore().equals( colore ) ){
                            
                            return false;
                
                        }
                    }
                }
            }
        
        } else { // Inizio Controllo Pedoni Bianchi
            
            if( x + 1 <= 7 && y - 1 >= 0 && mat[ x + 1 ][ y - 1 ].eOccupato() && mat[ x + 1 ][ y - 1 ].getOccupante() instanceof Pedone ){
                
                if( !mat[ x + 1 ][ y - 1 ].getOccupante().getColore().equals( colore ) ){
                    
                    return false;
                
                }
            
            }
            
            if( x - 1 >= 0 && y - 1 >= 0 && mat[ x - 1 ][ y - 1 ].eOccupato() ){
                if(mat[ x - 1 ][ y - 1 ].getOccupante() instanceof Pedone ){
                    if( !mat[ x - 1 ][ y - 1 ].getOccupante().getColore().equals( colore ) ){
                        
                        return false;
                
                    }
                }
            }
        
        }

        // Controlli Del Cavallo

        //In Alto A Destra
        if( x + 1 <= 7 && y + 2 <= 7 && mat[ x + 1 ][ y + 2 ].eOccupato() ){
            
            if( !mat[ x + 1 ][ y + 2 ].getOccupante().getColore().equals( colore ) && mat[ x + 1 ][ y + 2 ].getOccupante() instanceof Cavallo ){
                
                return false;
            
            }
        
        }

        //In Alto A Sinistra
        if( x - 1 >= 0 && y + 2 <= 7 && mat[ x - 1 ][ y + 2 ].eOccupato() ){
            
            if( !mat[ x - 1 ][ y + 2 ].getOccupante().getColore().equals( colore ) && mat[ x - 1 ][ y + 2 ].getOccupante() instanceof Cavallo ){
                
                return false;
            
            }
        
        }

        // A Destra In Alto
        if( x + 2 <= 7 && y + 1 <= 7 && mat[ x + 2 ][ y + 1 ].eOccupato() ){
            
            if( !mat[ x + 2 ][ y + 1 ].getOccupante().getColore().equals( colore ) && mat[ x + 2 ][ y + 1 ].getOccupante() instanceof Cavallo ){
                
                return false;
            
            }
        
        }

        // A Destra In Basso
        if( x + 2 <= 7 && y - 1 >= 0 && mat[ x + 2 ][ y - 1 ].eOccupato() ){       
            if( !mat[ x + 2 ][ y - 1 ].getOccupante().getColore().equals( colore ) && mat[ x + 2 ][ y - 1 ].getOccupante() instanceof Cavallo ){      
                
                return false;
            }
        }

        // A Sinistra In Alto
        if( x - 2 >= 0 && y + 1 <= 7 && mat[ x - 2 ][ y + 1 ].eOccupato() ){    
            if( !mat[ x - 2 ][ y + 1 ].getOccupante().getColore().equals( colore ) && mat[ x - 2 ][ y + 1 ].getOccupante() instanceof Cavallo ){    
                
                return false;
            }
        }

        // A Sinistra In Basso
        if( x - 2 >= 0 && y - 1 >= 0 && mat[ x - 2 ][ y - 1 ].eOccupato() ){
            
            if( !mat[ x - 2 ][ y - 1 ].getOccupante().getColore().equals( colore ) && mat[ x - 2 ][ y - 1 ].getOccupante() instanceof Cavallo ){
              
                return false;
            
            }
        
        }

        // In Basso A Destra
        if(x + 1 <= 7 && y - 2 >= 0){
            if( mat[ x + 1 ][ y - 2 ].eOccupato() ){
            
                if( !mat[ x + 1 ][ y - 2 ].getOccupante().getColore().equals( colore ) && mat[ x + 1 ][ y - 2 ].getOccupante() instanceof Cavallo ){
                 
                    return false;
                }
            }
        }

        // In Basso A Sinistra
        if( x - 1 >= 0 && y - 2 >= 0 && mat[ x - 1 ][ y - 2 ].eOccupato() ){
            
            if( !mat[ x - 1 ][ y - 2 ].getOccupante().getColore().equals( colore ) && mat[ x - 1 ][ y - 2 ].getOccupante() instanceof Cavallo ){
           
                return false;
            
            }
        
        }

        // Controllo Re Adiacente

        // Destra
        if( x + 1 <= 7 && mat[ x + 1 ][ y ].eOccupato() ){
            
            if( !mat[ x + 1 ][ y ].getOccupante().getColore().equals( colore ) && m[ x + 1 ][ y ].getOccupante() instanceof Re ){
            
                return false;
            
            }
        
        }

        // Sinistra
        if( x - 1 >= 0 && mat[ x - 1 ][ y ].eOccupato() ){          
            if( !mat[ x - 1 ][ y ].getOccupante().getColore().equals( colore ) ){
                if(mat[ x - 1 ][ y ].getOccupante() instanceof Re){
                
                    return false;
                }
            }
        }

        // Alto
        if( y + 1 <= 7 ){ 
            if(mat[ x ][ y + 1 ].eOccupato()){
                if( !mat[ x ][ y + 1 ].getOccupante().getColore().equals( colore )){  
                    if(mat[ x ][ y + 1 ].getOccupante() instanceof Re ){
                 
                        return false;
                    }
                }
            }
        }

        // Basso
        if( y - 1 >= 0  ){
            if(mat[ x ][ y - 1 ].eOccupato()){
                if( !mat[ x ][ y - 1 ].getOccupante().getColore().equals( colore ) && mat[ x ][ y - 1 ].getOccupante() instanceof Re ){
                   
                    return false;
                }
            }
        }

        // In Alto A Destra
        if( x + 1 <= 7 && y + 1 <= 7 ){
            if(mat[ x + 1 ][ y + 1 ].eOccupato()){
                if( !mat[ x + 1 ][ y + 1 ].getOccupante().getColore().equals( colore ) && mat[ x + 1 ][ y + 1 ].getOccupante() instanceof Re ){ 
                 
                    return false;
                }
            }
        }

        // In Alto A Sinistra
        if( x - 1 >= 0 && y + 1 <= 7 && mat[ x - 1 ][ y + 1 ].eOccupato() ){
            if( !mat[ x - 1 ][ y + 1 ].getOccupante().getColore().equals( colore ) && mat[ x - 1 ][ y + 1 ].getOccupante() instanceof Re ){ 
            
                return false;
            }
        }

        // In Basso A Destra
        if( x + 1 <= 7 && y - 1 >= 0 && mat[ x + 1 ][ y - 1 ].eOccupato() ){
            if( !mat[ x + 1 ][ y - 1 ].getOccupante().getColore().equals( colore ) && mat[ x + 1 ][ y - 1 ].getOccupante() instanceof Re ){      
       
                return false;
            }
        }

        // In Basso A Sinistra
        if( x - 1 >= 0 && y - 1 >= 0 && mat[ x - 1 ][ y - 1 ].eOccupato() ){         
            if( !mat[ x - 1 ][ y - 1 ].getOccupante().getColore().equals( colore ) && mat[ x - 1 ][ y - 1 ].getOccupante() instanceof Re ){   
            
                return false;
                
            }
        }
       
        return true;
    
    }
    
    /**
     * Questo metodo è diviso in più parti
     * Funzione di supporto per facilitare l'uso di controlloScacco
     * @param s casella dove si trova il re
     * @return true se c'è scacco
    */
    public boolean controlloScacco( Spazio s ){  
        
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
    public boolean controlloScacco( Colore colore, Spazio[][] matrice ) throws Exception{
        
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
    public boolean controlloScaccoReAvversario( Colore colore ) throws Exception{
        
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
    public boolean controlloScaccoReAvversario( Colore colore, Spazio[][] matrice ) throws Exception{
        
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
    public LinkedList<Pezzo> getPezziAttaccantiIlRe( Spazio s, Spazio[][] matrix ){
        
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
        for( int i = x; i <= 7 && !uscita; i++ ){
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
        
        for( int i = y; i <= 7 && !uscita; i++ ){
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
        
        while( temp1 <= 7 && temp2 <= 7 && !mat[ temp1 ][ temp2 ].eOccupato() ){
            
            temp1++;
            temp2++;
        
        }
        
        if( temp1 <= 7 && temp2 <= 7 && !mat[ temp1 ][ temp2 ].getOccupante().getColore().equals( colore ) ){
            
            if( mat[ temp1 ][ temp2 ].getOccupante() instanceof Alfiere || mat[ temp1 ][ temp2 ].getOccupante() instanceof Regina ){
                
                lista.add(mat[temp1][temp2].getOccupante());
            
            }
        
        }

        // Verso L'Alto A Sinistra
        temp1 = x - 1;
        temp2 = y + 1;
        
        while( temp1 >= 0 && temp2 <= 7 && !mat[ temp1 ][ temp2 ].eOccupato() ){
            
            temp1--;
            temp2++;
        
        }
        
        if( temp1 >= 0 && temp2 <= 7 && !mat[ temp1 ][ temp2 ].getOccupante().getColore().equals( colore ) ){
            
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
        
        while( temp1 <= 7 && temp2 >= 0 && !mat[ temp1 ][ temp2 ].eOccupato() ){
            
            temp1++;
            temp2--;
        
        }
        
        if( temp1 <= 7 && temp2 >= 0 && !mat[ temp1 ][ temp2 ].getOccupante().getColore().equals( colore ) ){
            
            if( mat[ temp1 ][ temp2 ].getOccupante() instanceof Alfiere || mat[ temp1 ][ temp2 ].getOccupante() instanceof Regina ){
                
                lista.add(mat[temp1][temp2].getOccupante());
            
            }
        
        }

        // Controlli Per Il Pedone ( Potrebbe Essere Integrato Nell'Alfiere In Casi Specifici )
        
        if( colore instanceof Nero ){ // Inizio Controllo Pedoni Neri
            
            if( x + 1 <= 7 && y + 1 <= 7 && mat[ x + 1 ][ y + 1 ].eOccupato() && mat[ x + 1 ][ y + 1 ].getOccupante() instanceof Pedone ){
                
                if( !mat[ x + 1 ][ y + 1 ].getOccupante().getColore().equals( colore ) ){
                    
                    lista.add(mat[x+1][y+1].getOccupante());
                
                }
            
            }
            
            if( x - 1 >= 0 && y + 1 <= 7 && mat[ x - 1 ][ y + 1 ].eOccupato() && mat[ x + 1 ][ y - 1 ].getOccupante() instanceof Pedone ){
                
                if( !mat[ x - 1 ][ y + 1 ].getOccupante().getColore().equals( colore ) ){
                    
                    lista.add(mat[x-1][y+1].getOccupante());
                
                }
            
            }
        
        } else { // Inizio Controllo Pedoni Bianchi
            
            if( x + 1 <= 7 && y - 1 >= 0 && mat[ x + 1 ][ y - 1 ].eOccupato() && mat[ x + 1 ][ y - 1 ].getOccupante() instanceof Pedone ){
                
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
        if( x + 1 <= 7 && y + 2 <= 7 && mat[ x + 1 ][ y + 2 ].eOccupato() ){
            
            if( !mat[ x + 1 ][ y + 2 ].getOccupante().getColore().equals( colore ) && mat[ x + 1 ][ y + 2 ].getOccupante() instanceof Cavallo ){
                
                lista.add(mat[x+1][y+2].getOccupante());
            
            }
        
        }

        //In Alto A Sinistra
        if( x - 1 >= 0 && y + 2 <= 7 && mat[ x - 1 ][ y + 2 ].eOccupato() ){
            
            if( !mat[ x - 1 ][ y + 2 ].getOccupante().getColore().equals( colore ) && mat[ x - 1 ][ y + 2 ].getOccupante() instanceof Cavallo ){
                
                lista.add(mat[x-1][y+2].getOccupante());
            
            }
        
        }

        // A Destra In Alto
        if( x + 2 <= 7 && y + 1 <= 7 && mat[ x + 2 ][ y + 1 ].eOccupato() ){
            
            if( !mat[ x + 2 ][ y + 1 ].getOccupante().getColore().equals( colore ) && mat[ x + 2 ][ y + 1 ].getOccupante() instanceof Cavallo ){
                
                lista.add(mat[x+2][y+1].getOccupante());
            
            }
        
        }

        // A Destra In Basso
        if( x + 2 <= 7 && y - 1 >= 0 && mat[ x + 2 ][ y - 1 ].eOccupato() ){
            
            if( !mat[ x + 2 ][ y - 1 ].getOccupante().getColore().equals( colore ) && mat[ x + 2 ][ y + 1 ].getOccupante() instanceof Cavallo ){
                
                lista.add(mat[x+2][y-1].getOccupante());
            
            }
        
        }

        // A Sinistra In Alto
        if( x - 2 >= 0 && y + 1 <= 7 && mat[ x - 2 ][ y + 1 ].eOccupato() ){
            
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
        if( x + 1 <= 7 && y - 2 <= 7 && mat[ x + 1 ][ y - 2 ].eOccupato() ){
            
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
        if( x + 1 <= 7 && mat[ x + 1 ][ y ].eOccupato() ){
            
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
        if( y + 1 <= 7 && m[ x ][ y + 1 ].eOccupato() ){ 
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
        if( x + 1 <= 7 && y + 1 <= 7 && mat[ x + 1 ][ y + 1 ].eOccupato() ){
            if( !mat[ x + 1 ][ y + 1 ].getOccupante().getColore().equals( colore ) && mat[ x + 1 ][ y + 1 ].getOccupante() instanceof Re ){ 
                lista.add(mat[x+1][y+1].getOccupante());
            }
        }

        // In Alto A Sinistra
        if( x - 1 >= 0 && y + 1 <= 7 && mat[ x - 1 ][ y + 1 ].eOccupato() ){
            if( !mat[ x - 1 ][ y + 1 ].getOccupante().getColore().equals( colore ) && mat[ x - 1 ][ y + 1 ].getOccupante() instanceof Re ){ 
                lista.add(mat[x-1][y+1].getOccupante());
            }
        }

        // In Basso A Destra
        if( x + 1 <= 7 && y - 1 >= 0 && mat[ x + 1 ][ y - 1 ].eOccupato() ){
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
    
    }
    
    /**
     * calcola se la torre si può muovere in una data posizione
     * @param s posizione dela torre
     * @param x l'ascissa della zona dove si vuole muovere la torre
     * @param y l'ordinata della zona dove si vuole muovere la torre
     * @return true se si può muovere in quella posizione
    */
    private boolean percorsoTorre( Spazio s, int x, int y ){
        
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
                
            } else {
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
     * calcola se l'alfiere si può muovere in una data posizione
     * @param s posizione alfiere
     * @param x l'ascissa della zona dove si vuole muovere l'alfiere
     * @param y l'ordinata della zona dove si vuole muovere l'alfiere
     * @return true se si può muovere in quella posizione
    */
    private boolean percorsoAlfiere( Spazio s, int x, int y ){
        
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
    public boolean spostabileIn( Spazio s, int x, int y ){
        
        int xp=s.getX();
        int yp=s.getY();
        int temp;//vriabile temporale per torre
        int temp1;//variabile temèorale per alfiere
        int temp2;// " "
        
        Pezzo p=s.getOccupante();
        
        if(x==xp && y==yp)
            return true;
        
        
        //controllo se la posizione finale è vuota o contiene un pezzo del colore opposto
        //(non posso spostarmi in un locazione con un pezzo dello stesso colore)
        if(m[x][y].eOccupato() ){
            
            if(!m[x][y].getOccupante().getColore().equals(s.getOccupante().getColore())){
                //divido i controlli in base al pezzo
                if (p instanceof Torre) {
                   
                    return percorsoTorre(s, x, y);
                }
                if (p instanceof Alfiere) {
                  
                    return percorsoAlfiere(s, x, y);
                }

                if (p instanceof Cavallo) {
                   
                    //caso base per la verifica successiva
                    if  (xp != x && yp != y) {
                    //funzione di verifica per la correttezza della posizione (modulo della somma dei 2 delta=3)
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
                   
                
                    return percorsoPedone(m[xp][yp], x, y, m);
                }

                if (p instanceof Re) {

                    //escludo il caso base
                    if (x != xp || y != yp) {
                    //posizioni adiacenti al re
                        if ((x == xp + 1 || x == xp - 1 || x == xp) && (y == yp + 1 || y == yp - 1 || y == yp)) {
                         
                            if (!m[x][y].eOccupato()
                                || (m[x][y].eOccupato()
                                && !m[x][y].getOccupante().getColore().equals(p.getColore()))) {
                                return true;
                            }
                        }
                     
                        if (!((Re) p).mosso()) {
                          
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
            
                return percorsoTorre(s, x, y);
            }
            if (p instanceof Alfiere) {
              
                return percorsoAlfiere(s, x, y);
            }

            if (p instanceof Cavallo) {
              
                //caso base per la verifica successiva
                if (xp != x && yp != y) {
                //funzione di verifica per la correttezza della posizione (modulo della somma dei 2 delta=3)
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
              
                
                return percorsoPedone(m[xp][yp], x, y, m);
            }

            if (p instanceof Re) {

                //escludo il caso base
                if (x != xp || y != yp) {
                    //posizioni adiacenti al re
                    if ((x == xp + 1 || x == xp - 1 || x == xp) && (y == yp + 1 || y == yp - 1 || y == yp)) {
                     
                        if (!m[x][y].eOccupato()
                                || (m[x][y].eOccupato()
                                && !m[x][y].getOccupante().getColore().equals(p.getColore()))) {
                            return true;
                        }
                    }
                 
                //arrocco
                    if (!((Re) p).mosso()) {
                   
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
    public boolean spostabileIn( Spazio s, int x, int y, Spazio[][] matrix ){
        
        Pezzo p=s.getOccupante();
        int xp=s.getX();
        int yp=s.getY();
        int temp;//vriabile temporale per torre
        int temp1;//variabile temèorale per alfiere
        int temp2;// " "

        //caso non considerato nei cicli sotto
        if(x==xp && y==yp)
            return true;
        
        
        //controllo se la posizione finale è vuota o contiene un pezzo del colore opposto
        //(non posso spostarmi in un locazione con un pezzo dello stesso colore)
        if(!s.eOccupato() || !s.getOccupante().getColore().equals(p.getColore())){
            //divido i controlli in base al pezzo
            if (p instanceof Torre) {

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
                return percorsoPedone(matrix[xp][yp], x, y, matrix);
            }

            if (p instanceof Re) {

            //escludo il caso base
                if (x != xp || y != yp) {
                    //posizioni adiacenti al re
                    if ((x == xp + 1 || x == xp - 1 || x == xp) && (y == yp + 1 || y == yp - 1 || y == yp)) {
                
                        if (!matrix[x][y].eOccupato()
                                || (matrix[x][y].eOccupato()
                                && !matrix[x][y].getOccupante().getColore().equals(p.getColore()))) {
                            return true;
                        }
                    }
           
                //arrocco
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
    /**
     * calcola se il pedone si può muovere in una data posizione
     * @param s posizione del pedone
     * @param x l'ascissa della zona dove si vuole muovere il pedone
     * @param y l'ordinata della zona dove si vuole muovere il pedone
     * @param matrice matrice della scacchiera
     * @return true se si può muovere in quella posizione
    */
    
    private boolean percorsoPedone(Spazio s,int x,int y,Spazio[][]matrice){
        Pezzo p;
        int xp=s.getX();
        int yp=s.getY();
        p=s.getOccupante();
        
        boolean test;
      
        
        if(!matrice[x][y].eOccupato()){
         
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
                            
                            if((y==yp-1) && !matrice[x][y].eOccupato())
                                return true;
                        
                        } else if(y==yp-1 || y==yp-2){ //p non si è mai mosso
                            
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
                        return y==yp+1;
                    }
                    if(p.getColore() instanceof Bianco){
                        return y==yp-1;
                    }
                }
                return false;
            }
    
    }
    
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
    public LinkedList<Spazio> getPezziSpostabiliQui( Spazio s, Colore c ){
        
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
        
        Spazio[][] originale=coppiaMatrice(matrice);
        LinkedList<Spazio> listaAttaccanti;
        LinkedList<Spazio> listaSalvatori=new LinkedList<>();
        Spazio re= originale[xRe][yRe];
        int[][] matricePosizioni=new int[8][8];
        int[][] percorsoAttaccante;
        boolean next;
        //azzero la matrice per sicurezza
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                matricePosizioni[i][j]=0;
            }
        }
        
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
        
        if(reSiSalvaDaScacco(re,originale))
            listaSalvatori.add(re);

        
        if(listaAttaccanti.getFirst().equals(listaAttaccanti.getLast())){
            
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
        
            for(int i=0;i<8;i++){
                for(int j=0;j<8;j++){
                    if(matricePosizioni[i][j]==1)
                        if(spostabileIn(matrice[i][j],listaAttaccanti.getFirst().getX(),listaAttaccanti.getFirst().getY(),m))
                            listaSalvatori.add(matrice[i][j]);
                }
            }
            
        
        }

 

        listaSalvatori.stream().forEach((elemento) -> {
           
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
        lista.stream().forEach((pezzo) -> {
            matriceRisultato[pezzo.getX()][pezzo.getY()]=1;
        });
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
       if(x>0 && y>0){
            if(!matrice[x-1][y-1].eOccupato()){
                matSimulata=coppiaMatrice(matrice);
            
                spostaPezzo(matSimulata[x][y], x-1, y-1, matSimulata);
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
       
       //provo in alto
        if(y>0){
            if(!matrice[x][y-1].eOccupato()){
                matSimulata=coppiaMatrice(matrice);
                spostaPezzo(matSimulata[x][y], x, y-1, matSimulata);
                if(controlloScacco(x,y-1,matSimulata)==false)
                    scacco=true;
                else
                    return true;
            }
            if(matrice[x][y-1].eOccupato()){
                if(!matrice[x][y-1].getOccupante().getColore().equals(re.getColore())){
                    matSimulata=coppiaMatrice(matrice);
                    spostaPezzo(matSimulata[x][y], x, y-1, matSimulata);
                    if(controlloScacco(x,y-1,matSimulata)==false)
                        scacco=true;
                    else
                        return true;
                }
            }
            
        }
        
        //provo in alto a destra
        if(x<7 && y>0){ 
            if(!matrice[x+1][y-1].eOccupato()){
                matSimulata=coppiaMatrice(matrice);
                spostaPezzo(matSimulata[x][y], x+1, y-1, matSimulata);
                if(controlloScacco(x+1,y-1,matSimulata)==false)
                    scacco=true;
                else
                    return true;
                }
            if(matrice[x+1][y-1].eOccupato()){
                if(!matrice[x+1][y-1].getOccupante().getColore().equals(re.getColore())){
                    matSimulata=coppiaMatrice(matrice);
                    spostaPezzo(matSimulata[x][y], x+1, y-1, matSimulata);
                    if(controlloScacco(x+1,y-1,matSimulata)==false)
                        scacco=true;
                    else
                        return true;
                
                }
            }
        }
        //provo a sinistra
        if(x>0){ 
            if(!matrice[x-1][y].eOccupato()){
                matSimulata=coppiaMatrice(matrice);
                spostaPezzo(matSimulata[x][y], x-1, y, matSimulata);
                if(controlloScacco(x-1,y,matSimulata)==false)
                    scacco=true;
                else
                    return true;
            }
            if(matrice[x-1][y].eOccupato()){
                if(!matrice[x-1][y].getOccupante().getColore().equals(re.getColore())){
                    matSimulata=coppiaMatrice(matrice);
                    spostaPezzo(matSimulata[x][y], x-1, y, matSimulata);
                    if(controlloScacco(x-1,y,matSimulata)==false)
                        scacco=true;
                    else
                        return true;
                }
            }
        }
        
        //provo a destra
        if(x<7){ 
            if(!matrice[x+1][y].eOccupato()){
                matSimulata=coppiaMatrice(matrice);
                spostaPezzo(matSimulata[x][y], x+1, y, matSimulata);
                if(controlloScacco(x+1,y,matSimulata)==false)
                    scacco=true;
                else
                    return true;
            }
            if(matrice[x+1][y].eOccupato()){
                if(!matrice[x+1][y].getOccupante().getColore().equals(re.getColore())){
                    matSimulata=coppiaMatrice(matrice);
                    spostaPezzo(matSimulata[x][y], x+1, y, matSimulata);
                    if(controlloScacco(x+1,y,matSimulata)==false)
                        scacco=true;
                    else
                        return true;
                }
            }
        }
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
        
   
        Pezzo p;
        int xp = s.getX();
        int yp = s.getY();
        p=s.getOccupante();
        s.setX( x );
        s.setY( y );
        
        scacchiera[ xp ][ yp ]=new Spazio( xp,yp );
        scacchiera[ x ][ y ]=new Spazio( x,y,s.getOccupante() );  
        if(scacchiera[x][y].getOccupante() instanceof Pedone){
           
            if(!((Pedone)scacchiera[x][y].getOccupante()).mosso()){
                ((Pedone)scacchiera[x][y].getOccupante()).impostaMosso();
            }
            
            //se il pedone arriva in fondo alla scacchiera lo trasformo
            
            if( scacchiera[x][y].getOccupante().getColore() instanceof Bianco ){ // Promozione Pedone Bianco
                                    
                if( y == 0 ){ // Se Il Pedone E In Fondo Alla Scacchiera
                    ig.promozionePedone(scacchiera[x][y],new Bianco(),scacchiera);
          
                }
                                
            } else { // Promozione Pedone Nero
                                    
                if( y == 7 ){ // Se Il Pedone E In Fondo Alla Scacchiera
                    ig.promozionePedone(scacchiera[x][y],new Nero(),scacchiera);
             
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
                                
                                spostaPezzo(m[7][y],x-1,y,scacchiera);
                            }
                        }
                    }
                }
                if (x == xp - 2) {
                    if (m[0][y].eOccupato() && m[0][y].getOccupante() instanceof Torre) {
                        if (!((Torre) m[0][y].getOccupante()).mosso()) {
                            if (!m[1][y].eOccupato() && !m[3][y].eOccupato()) {
                                spostaPezzo(m[0][y],x+1,y,scacchiera);
                            }
                        }
                    }
                }
            }
        }
    
    }
    
    /**
     * questo metodo è diviso in più parti
     * cambia le cordinate di un pezzo sulla scacchiera e aggiorna la scacchiera
     * @param s oggetto di calsse Spazio contenente il pezzo
     * @param x nuova ascissa del pezzo
     * @param y nuova ordinata del pezzo
    */
    public void spostaPezzo( Spazio s, int x, int y ){
      
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
              
                    ig.promozionePedone(m[x][y],new Bianco(),m);
                }
                                
            } else { // Promozione Pedone Nero
                                    
                if( y == 7 ){ // Se Il Pedone E In Fondo Alla Scacchiera
                    ig.promozionePedone(m[x][y],new Nero(),m);
                 
                }
                                
            }
        }
    
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
    
} // Fine Classe GestoreMovimenti