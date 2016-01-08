package controller;

import javax.swing.*;
import java.awt.event.*;
import model.*;
import view.*;

/**
 * Questa classe gestisce i Turni e i Buttoni.
 * Esso, ad esempio, blocca i pulsanti affinche non possano essere premuti se tocca al colore opposto al turno corrente.
 * @author Viktor, Michael, Gaetano
*/
public class GestoreTB{
    
    /**
     * Variabile contenente il turno corrente 
    */
    private Colore turno;
    
    /**
     * Indica se uno spazio e attivato ( Prima Pressione ovvero Sorgente ) 
    */
    private boolean attivato;
    
    /**
     * Coordinate contenente la X e la Y dello spazio attivato sorgente 
    */
    private int xAttivato,yAttivato;
    
    /**
     * Collegmaneto GestoreTurniEBottoni (GestoreTB) con il GestoreMovimenti 
    */
    GestoreMovimenti gestoreMovimenti;
    
    /**
     * Collegmaneto GestoreTurniEBottoni (GestoreTB) con l'InterfacciaGrafica 
    */
    InterfacciaGrafica interfacciaGrafica;
    
    /**
     * Matrice dei bottoni contenente la situazione della scacchiera
    */
    JButton[][] matriceBottoni;
    
    /**
     * Coordinate contenente la X e la Y del / dei pedoni trasformati 
    */
    private int xPedoneTrasformato,yPedoneTrasformato;
    
    /**
     * Coordinate contenente la X e la Y della sorgente 
    */
    int x,y;
    
    /**
     * Matrice simulata usata per simulare i casi di possibili salvataggi dallo scacco 
    */
    Spazio[][] matriceSimulata = null;
    
    /**
     * Matrice simulata alternativa usata per tornare ad una situazione precedente 
    */
    Spazio[][] matriceSimulata2 = null;
    
    /**
     * Costruttore di base della classe
     * @param gm - Collegamento con il gestoreMovimenti
     * @param ig interfacciaGrafica - Collegamento con l'InterfacciaGrafica
    */
    public GestoreTB( GestoreMovimenti gm, InterfacciaGrafica ig ){
        
        attivato = false;
        turno = new Bianco();
        gestoreMovimenti = gm;
        interfacciaGrafica = ig;
        matriceBottoni = interfacciaGrafica.getMatriceBottoni();
        
    }
    
    /**
     * Metodo che ritorna il colore di collui che detiene il turno corrente della partita
     * @return turno - Il colore del giocatore di cui e il turno 
    */
    public Colore getTurno(){
        return turno;
    }
    
    /**
     * Imposta il turno e aggiorna l'interfaccia segnando a che colore corrisponde il turno
     * @param t Il colore del turno da impostare
    */
    public void setTurno( Colore t ){
        
        turno = t;
        bloccoPezziIniziale();
        getInterfacciaGrafica().setMessaggio( "Tocca Al " + t.toString() );
    
    }
    
    /**
     * Ritorna l'iInterfacciaGrafica affinche si possa aggiornare la posizione dei pezzi visualizzata dall'utente
     * @return interfacciaGrafica - L'interfaccia da impostare 
    */
    private InterfacciaGrafica getInterfacciaGrafica(){
        
        return interfacciaGrafica;
    
    }
    
    /**
     * Passo il turno al giocatore successivo dopo aver fatto una mossa
    */
    public void passaTurno(){
        

        if( turno instanceof Bianco ){
            
            turno = new Nero();
            getInterfacciaGrafica().setMessaggio( "Tocca Al Nero" );
        
        } else {
            
            turno = new Bianco();
            getInterfacciaGrafica().setMessaggio( "Tocca Al Bianco" );
        
        }
        
        attivato = false;
    
    }
    
    /**
     * Metodo che viene usato per attivare i bottoni per permettere una mossa
    */
    public void attiva(){
        
        attivato = true;
    
    }
    
    /**
     * Metodo che viene usato per disattivare i bottoni affinche una mossa non sia possibile
    */
    public void disattiva(){
        
        attivato = false;
    
    }
    
    /**
     * Ritorna la coordinata X dello spazio attivato
     * @return xAttivato - La coordinata X dello spazio attivato
    */
    private int getXAttivato(){
        
        return xAttivato;
    
    }
    
    /**
     * Ritorna la coordinata Y dello spazio attivato
     * @return yAttivato - La coordinata Y dello spazio attivato
    */
    private int getYAttivato(){
        
        return yAttivato;
    
    }
    
    /**
     * Imposto la xAttivata dello spazio
     * @param x - La coordinata X dello spazio da attivare
    */
    private void setXAttivato( int x ){
        
        xAttivato = x;
    
    }
    
    /**
     * Imposto la yAttivata dello spazio
     * @param y - La coordinata Y dello spazio da attivare
    */
    private void setYAttivato( int y ){
        
        yAttivato = y;
    
    }
    
    /**
     * Metodo che verifica se una determinata casella è attivata o no
     * @return attivato - True se la casella e attivata, false altrimenti 
    */
    public boolean isAttivato(){
        
        return attivato;
    
    }
    
    /**
     * Metodo che blocca le caselle occupate da pezzi del giocatore opposto al turno corrente
     * Esso viene chiamato ad ogni turno
    */
    public void bloccoPezziIniziale(){
        
        for( int j = 0; j < 8; j++ ){
            
            for( int i = 0; i < 8; i++ ){

                if( !( gestoreMovimenti.getMatrice()[i][j].eOccupato() ) ){
                    
                    matriceBottoni[ i ][ j ].setEnabled( false );
                
                } else { // Qui sono sicuro che esiste l'occupante
                    
                    if( !( gestoreMovimenti.getMatrice()[i][j].getOccupante().getColore().equals( getTurno() ) ) ){ // Cerco le locazioni del colore sbagliato
                        
                        matriceBottoni[ i ][ j ].setEnabled( false );
                    
                    }
                    
                }
            
            }
        
        }
    
    }
    
    /**
     * Metodo che attiva un determinato spazio una volta fornite le coordinate 
     * @param x - La coordinata x dello spazio da attivare
     * @param y - La coordinata y dello spazio da attivare
    */
    private void attivaPosizione( int x, int y ){
        
        setXAttivato( x );
        setYAttivato( y );
        attiva();
    
    }
    
    /**
     * Metodo che disattiva un determinato spazio una volta fornite le coordinate 
     * @param x - La coordinata x dello spazio da disattivare
     * @param y - La coordinata y dello spazio da disattivare
    */
    private void disattivaPosizione( int x, int y ){
        
        disattiva();
        
    }
    
    /**
     * Metodo che disattiva un dato spazio
    */
    private void disattivaPosizione(){
        
        disattivaPosizione( getXAttivato(), getYAttivato() );
    
    }
    
    /**
     * Imposto la X del pedone trasformato dello spazio
     * @param x - La coordinata X del pedone trasformato
    */
    public void setXPedoneTrasformato( int x ){
        
        xPedoneTrasformato = x;
    
    }
    
    /**
     * Imposto la Y del pedone trasformato dello spazio
     * @param y - La coordinata Y del pedone trasformato
    */
    public void setYPedoneTrasformato( int y ){
        
        yPedoneTrasformato = y;
    
    }
    
    /**
     * Metodo che ritorna la coordinata X del pedone trasformato dello spazio
     * @return xPedoneTrasformato - La coordinata X del pedone trasformato
    */
    public int getXPedoneTrasformato(){
        
        return xPedoneTrasformato;
    
    }
    
    /**
     * Metodo che ritorna la coordinata Y del pedone trasformato dello spazio
     * @return yPedoneTrasformato - La coordinata Y del pedone trasformato
    */
    public int getYPedoneTrasformato(){
        
        return yPedoneTrasformato;
    
    }
    
    /**
     * Metodo che segna sulla scacchiera dove si trova un determinato pedone trasformato
     * @param s Lo spazio dove si trova un determinato pedone trasformato
    */
    public void setPedoneTrasformato( Spazio s ){
        
        Pezzo p = s.getOccupante();
        
        xPedoneTrasformato = x;
        yPedoneTrasformato = y;
        gestoreMovimenti.getMatrice()[ s.getX() ][ s.getY() ].cambiaPezzo( p );
        interfacciaGrafica.aggiornaBottoni( gestoreMovimenti.getMatrice() );
    
    }
    
    /**
     * Metodo che segna le coordinate del pulsante che stato premuto sulla scacchiera
     * @param e - La coordinata presa dal sistema che identifica il JButton sul quale e stato premuto sulla schacchiera
     * @throws Exception Nel caso in cui l'evento non venga registrato
    */
    public void pressionePulsanteScacchiera( ActionEvent e ) throws Exception{
        
        x = 0; // Identificatore X della matrice dei bottoni dove si e premuto
        y = 0;// Identificatore Y della matrice dei bottoni dove si e premuto
        
        JButton b;
        
        GestoreMovimenti gestoreTemporaneo;
        Pezzo pezzoDaSpostare;
        turno = getTurno();
        JButton[][] matriceScacchiera = interfacciaGrafica.getMatriceBottoni();
        int indiciBottoni[][] = new int[ 8 ][ 8 ];
        
        b = ( JButton ) e.getSource();
        
        for( int i = 0; i < 8; i++ ){ // Ricerca Inversa Della Posizione Del Bottone
            
            for( int j = 0; j < 8; j++ ){
                
                if( b.equals( matriceScacchiera[ j ][ i ] ) ){
                    
                    x = j;
                    y = i;
                    break;
                
                }
            
            }
        
        }
        
        if( !isAttivato() ){ // Guardo Se Nello Stesso Turno Non è stato Abilitato Un Bottone
            
            if( gestoreMovimenti.getMatrice()[ x ][ y ].eOccupato() ){ // Guardo Se Lo Spazio Contiene Qualcosa
                
                if( turno.equals( gestoreMovimenti.getMatrice()[ x ][ y ].getOccupante().getColore() ) ){ //Se Cio Che Contiene Ha Lo Stesso Colore Del Turno Corrente

                        attivaPosizione( x, y );
                    
                    if( ( ( turno instanceof Bianco ) && gestoreMovimenti.controlloScacco( gestoreMovimenti.getSpazioReBianco().getX(), gestoreMovimenti.getSpazioReBianco().getY(), gestoreMovimenti.getMatrice() ) == true ) || ( ( turno instanceof Nero ) && gestoreMovimenti.controlloScacco( gestoreMovimenti.getSpazioReNero().getX(), gestoreMovimenti.getSpazioReNero().getY(), gestoreMovimenti.getMatrice() ) == true ) ){ // Se Il Re E Sotto Scacco

                        // Se Il Pezzo Può Salvare Il Re Devo Controllare La Matrice Simulata, Ma Prima Devo Crearla In Base Al Turno
                      
                        if( turno instanceof Bianco ){ // Il Pezzo E Bianco
                            
                            if( gestoreMovimenti.getMatricePezziChePrevengonoScacco( gestoreMovimenti.getSpazioReBianco().getX(), gestoreMovimenti.getSpazioReBianco().getY(), gestoreMovimenti.getMatrice(), new Bianco() )[ x ][ y ] == 1 ){
                                
                                attivaPosizione( x, y );
                            
                            }
                        
                        } else if( gestoreMovimenti.getMatricePezziChePrevengonoScacco( gestoreMovimenti.getSpazioReBianco().getX(), gestoreMovimenti.getSpazioReBianco().getY(), gestoreMovimenti.getMatrice(), new Nero() )[ x ][ y ] == 1 ){ // Il Pezzo E Nero
                            
                            attivaPosizione( x, y );
                        
                        }
                    
                    }
                        
                }
            
            }
        
        } else { // Se Sono Qui significa Che è Stato Premuto Precedentemente Un Pezzo Valido
            
            if( x == xAttivato && y == yAttivato ){ // Se La Posizione Dove Premo E Quella Dove Ho Premuto Prima
            
                disattiva(); // Imposto Come Se Niente Fosse Stato Premuto
            
            } else { // Se Sono Qua Ho Premuto In Una Posizione Diversa
                
                if( !gestoreMovimenti.getMatrice()[ x ][ y ].eOccupato() ){ // Se E Una Locazione Vuota
                    
                    if( gestoreMovimenti.spostabileIn( gestoreMovimenti.getMatrice()[ xAttivato ][ yAttivato ], x, y ) ){ // Se E Una Locazione Consentita Dal Pezzo
                        
                        matriceSimulata=gestoreMovimenti.getMatrice().clone();
                        matriceSimulata2=gestoreMovimenti.coppiaMatrice(matriceSimulata);
                        gestoreMovimenti.spostaPezzo( gestoreMovimenti.getMatrice()[ xAttivato ][ yAttivato ], x, y, matriceSimulata );

                        // Se il re non e sotto scacco dentro la matrice simulata
                        if( (getTurno() instanceof Bianco && gestoreMovimenti.controlloScacco(new Bianco(),matriceSimulata)) || (getTurno() instanceof Nero && gestoreMovimenti.controlloScacco(new Nero(),matriceSimulata))){
                            
                            gestoreMovimenti.setMatrice(matriceSimulata); // Sposto definitivamente il pezzo
                            interfacciaGrafica.aggiornaBottoni( gestoreMovimenti.getMatrice() ); // Aggiorno la visuale
                            
                            if( !gestoreMovimenti.controlloScaccoReAvversario( turno, matriceSimulata ) ){
 
                                // Se il Re avversario non puo essere salvato
                                if( gestoreMovimenti.getListaPezziChePrevengonoScacco( gestoreMovimenti.getSpazioReAvversario( turno ).getX(), gestoreMovimenti.getSpazioReAvversario( turno ).getY(), gestoreMovimenti.getMatrice(), turno ).isEmpty() ){
            
                                    interfacciaGrafica.finePartita();
                                    if(turno instanceof Bianco)
                                        passaTurno();
                                }
                            
                            }
                            
                            disattivaPosizione(); // Disattivo la posizione dove e stato premuto
                            passaTurno(); // Passo il turno
                            
                        
                        } else { // Se Provoco Lo Scacco Matto
                            
                            gestoreMovimenti.setMatrice( gestoreMovimenti.coppiaMatrice( matriceSimulata2 ) ); // Torno alla situazione sicura di prima
                            disattivaPosizione(); // Disattivo la posizione dove e stato premuto
                        
                        }
                    
                    } else { // Se Non E Una Locazione Consentita Dal Pezzo
                    
                        disattivaPosizione(); // Disattivo la posizione dove e stato premuto
                    
                    }
                
                } else { // Se E Una Locazione Non Vuota
                    
                    if( gestoreMovimenti.spostabileIn( gestoreMovimenti.getMatrice()[ xAttivato ][ yAttivato ], x, y ) ){ // Se E Una Locazione Consentita Dal Pezzo E Contiene Un Pezzo Di Colore Diverso
                        
                        matriceSimulata=gestoreMovimenti.getMatrice().clone();
                        matriceSimulata2=gestoreMovimenti.coppiaMatrice( matriceSimulata );
                        pezzoDaSpostare=gestoreMovimenti.getMatrice()[ x ][ y ].getOccupante();
                        gestoreMovimenti.spostaPezzo( gestoreMovimenti.getMatrice()[ xAttivato ][ yAttivato ], x, y, matriceSimulata );
                        
                        if( ( getTurno() instanceof Bianco && gestoreMovimenti.controlloScacco( new Bianco(), matriceSimulata ) ) || ( getTurno() instanceof Nero && gestoreMovimenti.controlloScacco( new Nero(), matriceSimulata ) ) ){
                            
                            gestoreMovimenti.setMatrice( matriceSimulata );
                            interfacciaGrafica.aggiungiPezzoMorto( pezzoDaSpostare );
                            interfacciaGrafica.aggiornaBottoni( gestoreMovimenti.getMatrice() ); // Aggiorno La Visuale
                            
                            if( gestoreMovimenti.scaccoMatto(turno) ){ // Verifico Scacco Matto / Se il Re Verra Mangiato
                                
                                interfacciaGrafica.finePartita();
                                
                            }
                            
                            if( !gestoreMovimenti.controlloScaccoReAvversario( turno, matriceSimulata ) ){
                                
                                // Se Il Re Avversario Non Puo Essere Salvato
                                if( gestoreMovimenti.getListaPezziChePrevengonoScacco( gestoreMovimenti.getSpazioReAvversario( turno ).getX(), gestoreMovimenti.getSpazioReAvversario( turno ).getY(), gestoreMovimenti.getMatrice(), turno ).isEmpty() ){
                                    
                                    interfacciaGrafica.finePartita();
                                    if(turno instanceof Bianco)
                                        passaTurno();
                                }
                            
                            }
                            
                            disattivaPosizione(); // Disattivo la posizione dove e stato premuto
                            passaTurno(); // Passo il turno
                            getInterfacciaGrafica().setMessaggio( "Tocca Al Bianco" );
                        
                        } else { // Se Provoco Lo Scacco
                            
                            gestoreMovimenti.setMatrice( gestoreMovimenti.coppiaMatrice( matriceSimulata2 ) );
                            disattivaPosizione(); // Imposto Come Se Niente Fosse Stato Premuto
                        
                        }
                    
                    } else { // Se Contiene Un Pezzo Del Colore Corrente
                        
                        disattivaPosizione(); // Disattivo la posizione dove e stato premuto
                    
                    }
                
                }
            
            }
        
        }
    
    }
    
    /**
     * Metodo usato durante il debug che disegna la matrice delle posizioni occupate da un pezzo
     * Usato per il debug
    */
    public void disegnaMatriceSpaziOccupati(){
        
        System.err.println( "" );
        
        for( int i = 0; i < 8; i++ ){
            
            for( int j = 0; j < 8; j++ ){
                
                if( gestoreMovimenti.getMatrice()[ j ][ i ].eOccupato() ){
                    
                    System.err.print( "x" );
                
                } else {
                    
                    System.err.print( "_" );
                
                }
            
            }
            
            System.err.println( "" );
        
        }
        
        System.err.println( "" );
    
    }
    
    /**
     * Metodo usato durante il debug che prendento una matrice ne disegna le posizioni occupate da un pezzo e non
     * @param matrice - La matrice da disegnare le posizioni occupate e non
    */
    private void disegnaMatriceSpaziOccupati( Spazio[][] matrice ){
        
        System.err.println( "" );
        
        for( int i = 0; i < 8; i++ ){
            
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

} // Fine Classe GestoreTB