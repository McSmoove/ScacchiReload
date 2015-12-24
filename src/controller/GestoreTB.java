package controller;

import javax.swing.*;
import java.awt.event.*;
import model.*;
import view.*;

/**
 * Questa classe cambia i turni quando richiesto e blocca i pulsanti che non possono
 * essere premuti (per esempio, se non è il turno del bianco blocca tutti i pulsanti
 * con una pedina bianca sopra)
 * @author Viktor, Michael, Gaetano
*/
public class GestoreTB{
    
    private Colore turno;
    private boolean attivato;
    private int xAttivato,yAttivato;
    GestoreMovimenti gestoreMovimenti;
    InterfacciaGrafica interfacciaGrafica;
    JButton[][] matriceBottoni;
    private int xPedoneTrasformato,yPedoneTrasformato;
    int x,y;
    Spazio[][] matriceSimulata = null;
    Spazio[][] matriceSimulata2 = null;
    
    /**
     * Costruttore di base della classe
     * @param gm gestoreMovimenti
     * @param ig interfacciaGrafica
     */
    public GestoreTB( GestoreMovimenti gm, InterfacciaGrafica ig ){
        
        attivato = false;
        turno = new Bianco();
        gestoreMovimenti = gm;
        interfacciaGrafica = ig;
        matriceBottoni = interfacciaGrafica.getMatriceBottoni();
        
    }
    
    /**
     * 
     * @return ritorna il colore del giocatore di cui è il turno 
    */
    public Colore getTurno(){
        
        return turno;
    
    }
    
    /**
     * Inizializza il turno e aggiorna l'interfaccia segnado a quale colore corrisponde il turno
     * @param t colore del turno corrente
    */
    public void setTurno(Colore t){
        
        turno = t;
        bloccoPezziIniziale();
        getInterfacciaGrafica().setMessaggio( "Tocca Al " + t.toString() );
    
    }
    
    /**
     * Ritorna il parametro interfacciaGrafica (serve per aggiornare la posizione dei pezzi visualizzata dall'utente
     * @return 
    */
    private InterfacciaGrafica getInterfacciaGrafica(){
        
        return interfacciaGrafica;
    
    }
    
    /**
     * Cambia il colore del turno corrente
    */
    public void passaTurno(){
        
        System.out.println("Cambio del turno");
        if( turno instanceof Bianco ){
            
            turno = new Nero();
            getInterfacciaGrafica().setMessaggio( "Tocca Al Nero" );
        
        } else {
            
            turno = new Bianco();
            getInterfacciaGrafica().setMessaggio( "Tocca Al Bianco" );
        
        }
        
        System.err.println( "DEBUG: Non Chiamo Il Blocco Bottoni" );
        attivato = false;
    
    }
    
    /**
     * Viktor
     * Metodo di support per attivaPosizione
    */
    public void attiva(){
        
        attivato = true;
    
    }
    
    /**
     * Metodo di supporto per disattivaPosizione
    */
    public void disattiva(){
        
        attivato = false;
    
    }
    
    /**
     * Ritorna l'ascissa della coordinata della casella attiva
     * @return 
    */
    private int getXAttivato(){
        
        return xAttivato;
    
    }
    
    /**
     * Ritorna l'ordinata della coordinata della casella attiva
     * @return 
    */
    private int getYAttivato(){
        
        return yAttivato;
    
    }
    
    /**
     * memorizza l'ascissa della casella attivata
     * @param x 
    */
    private void setXAttivato( int x ){
        
        xAttivato = x;
    
    }
    
    /**
     * memorizza l'ordinata della casella attivata
     * @param y 
    */
    private void setYAttivato( int y ){
        
        yAttivato = y;
    
    }
    
    /**
     * 
     * @return ritorna se la casella è attivata oppure no 
    */
    public boolean isAttivato(){
        
        return attivato;
    
    }
    
    /**
     * Blocca Le Caselle Occupate Dalle Pedine Di Cui Non E Il Turno E Quelle Vuote.
     * Viene Chiamato Ad Ogni Turno.
    */
    public void bloccoPezziIniziale(){
        
        System.err.println( "DEBUG: blocco bottoni iniziale" );
        
        for( int j = 0; j < 8; j++ ){
            
            for( int i = 0; i < 8; i++ ){

                if( !( gestoreMovimenti.getMatrice()[i][j].eOccupato() ) ){
                    
                    matriceBottoni[ i ][ j ].setEnabled( false );
                
                } else { // Con L'Else Sono Sicuro Che Esiste L'Occupante
                    
                    if( !( gestoreMovimenti.getMatrice()[i][j].getOccupante().getColore().equals( getTurno() ) ) ){ // Cerco Locazioni Del Colore Sbagliato
                        
                        matriceBottoni[ i ][ j ].setEnabled( false );
                    
                    }
                    
                }
            
            }
        
        }
    
    }
    
    /**
     * Attiva la casella alla data coordinata 
     * @param x ascissa della coordinata
     * @param y rdinata della coordinata
    */
    private void attivaPosizione( int x,int y ){
        
        setXAttivato( x );
        setYAttivato( y );
        attiva();
    
    }
    
    /**
     * Disattiva la casella alla data coordinata
     * @param x ascissa della coordinata
     * @param y ordinata della coordinata
    */
    private void disattivaPosizione( int x, int y ){
        
        disattiva();
        
    }
    
    /**
     * Metodo che chiama disattivaPosizione
    */
    private void disattivaPosizione(){
        
        disattivaPosizione( getXAttivato(), getYAttivato() );
    
    }
    
    /**
     * memorizza l'ascissa della casella sulla quale si trova il pedone promosso
     * @param x
    */
    public void setXPedoneTrasformato( int x ){
        
        xPedoneTrasformato = x;
    
    }
    
    /**
     * Memorizza l'ordinata della casella sulla quale si trova il pedone promosso
     * @param y 
    */
    public void setYPedoneTrasformato( int y ){
        
        yPedoneTrasformato = y;
    
    }
    
    /**
     * Ritorna l'ascissa di dove è stato promosso  il pedone
     * @return 
    */
    public int getXPedoneTrasformato(){
        
        return xPedoneTrasformato;
    
    }
    
    /**
     * Ritorna l'ordinata di dove è stato promosso  il pedone
     * @return 
    */
    public int getYPedoneTrasformato(){
        
        return yPedoneTrasformato;
    
    }
    
    /**
     * Segna sulla scacchiera dove si trova il pedone promosso
     * @param s lo spazio dove si trova il pedone promosso
    */
    public void setPedoneTrasformato( Spazio s ){
        
        Pezzo p = s.getOccupante();
        xPedoneTrasformato = x;
        yPedoneTrasformato = y;
        gestoreMovimenti.getMatrice()[ s.getX() ][ s.getY() ].cambiaPezzo( p );
        interfacciaGrafica.aggiornaBottoni( gestoreMovimenti.getMatrice() );
    
    }
    
    /**
     * segna le coordinate di dove è stata premuta la scacchiera
     * @param e pressione di un pulsante sulla schacchiera
     * @throws Exception 
    */
    public void pressionePulsanteScacchiera( ActionEvent e ) throws Exception{
        
        x = 0; // Identificatore X Della Matrice Del Bottone Dove Premo
        y = 0;// Identificatore Y Della Matrice Del Bottone Dove Premo
        
        JButton b;
        
        GestoreMovimenti gestoreTemporaneo;
        turno = getTurno();
        JButton[][] matriceScacchiera = interfacciaGrafica.getMatriceBottoni();
        int indiciBottoni[][] = new int[ 8 ][ 8 ];
        
        b = ( JButton ) e.getSource();
        
        System.err.println("Alla pressione del pulsante:");
        
        // Ricerca Inversa Della Posizione Del Bottone
        for( int i = 0; i < 8; i++ ){
            
            for( int j = 0; j < 8; j++ ){
                
                if( b.equals( matriceScacchiera[ j ][ i ] ) ){
                    
                    x = j;
                    y = i;
                    break;
                
                }
            
            }
        
        }
        
        System.out.println( "DEBUG:\n x : " + x + " \n y: " + y );
        
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
                
                System.err.println( "DEBUG: Premuto Sulla Posizione Precedente" );
                disattiva(); // Imposto Come Se Niente Fosse Stato Premuto
            
            } else { // Se Sono Qua Ho Premuto In Una Posizione Diversa
                
                if( !gestoreMovimenti.getMatrice()[ x ][ y ].eOccupato() ){ // Se E Una Locazione Vuota
                    
                    System.err.println( "DEBUG: Premuto Su Una Locazione Vuota" );
                    System.err.println("Prima di chiamare spostabileIn");
                    // Se E Una Locazione Consentita Dal Pezzo
                    if( gestoreMovimenti.spostabileIn( gestoreMovimenti.getMatrice()[ xAttivato ][ yAttivato ], x, y ) ){ 
                        
                        matriceSimulata=gestoreMovimenti.getMatrice().clone();
                        matriceSimulata2=gestoreMovimenti.coppiaMatrice(matriceSimulata);
                        gestoreMovimenti.spostaPezzo( gestoreMovimenti.getMatrice()[xAttivato][yAttivato], x, y,matriceSimulata );

                        // Se il re non è sotto scacco (dentro la matrice simulata)
                        if( (getTurno() instanceof Bianco && gestoreMovimenti.controlloScacco(new Bianco(),matriceSimulata)) || (getTurno() instanceof Nero && gestoreMovimenti.controlloScacco(new Nero(),matriceSimulata))){
                            
                            // Sposto definitivamente il pezzo
                            gestoreMovimenti.setMatrice(matriceSimulata);
                            interfacciaGrafica.aggiornaBottoni( gestoreMovimenti.getMatrice() ); // Aggiorna La Visuale
                            System.err.println("Prima del controllo se l'avversario è sotto scacco");
                            
                            if( !gestoreMovimenti.controlloScaccoReAvversario( turno, matriceSimulata ) ){
                                System.err.println("Il re avversario adesso è sotto SCACCO");
                                System.out.println("Prima di re avversario non può essere salvato");
                                //se il re avversario non può essere salvato
                                if(gestoreMovimenti.getListaPezziChePrevengonoScacco(gestoreMovimenti.getSpazioReAvversario(turno).getX(), gestoreMovimenti.getSpazioReAvversario(turno).getY(), gestoreMovimenti.getMatrice(), turno).isEmpty()){
                                    System.err.println("Scacco matto");
                                    interfacciaGrafica.finePartita();
                                }
                            }
                            
                            //e passo il turno
                            disattivaPosizione(); 
                            passaTurno();
                            
                        
                        } else { // Se Provoca Lo Scacco Matto
                            
                            //torno indietro
                            gestoreMovimenti.setMatrice(gestoreMovimenti.coppiaMatrice(matriceSimulata2));
                            disattivaPosizione(); // Imposto Come Se Niente Fosse Stato Premuto
                        
                        }
                    
                    } else { // Se Non è Una Locazione Consentita Dal Pezzo
                    
                        System.err.println( "DEBUG: Premuto In Una Posizione Non Consentita Dal Pezzo, Bisogna Farla Diventare Rossa" );
                        disattivaPosizione(); // Imposto Come Se Niente Fosse Stato Premuto
                    
                    }
                
                } else { // Se E Una Locazione Non Vuota
                    
                    System.err.println( "DEBUG: Schiaccio Su Una Locazione Non Vuota" );
                    System.err.println("Prima di chiamare spostabile in");
                    
                    if( gestoreMovimenti.spostabileIn( gestoreMovimenti.getMatrice()[xAttivato][yAttivato], x, y ) ){ // Se E Una Locazione Consentita Dal Pezzo E Contiene Un Pezzo Di Colore Diverso
                        
                        System.err.println( "Del Colore Diverso" ); // Se Non Provoca Scacco Del Proprio Colore -> Scacco Matto ( Faccio La Simulazione )
                        matriceSimulata=gestoreMovimenti.getMatrice().clone();
                        matriceSimulata2=gestoreMovimenti.coppiaMatrice(matriceSimulata);
                        gestoreMovimenti.spostaPezzo(gestoreMovimenti.getMatrice()[xAttivato][yAttivato], x, y,matriceSimulata);
                        
                        if( (getTurno() instanceof Bianco && gestoreMovimenti.controlloScacco(new Bianco(),matriceSimulata)) || (getTurno() instanceof Nero && gestoreMovimenti.controlloScacco(new Nero(),matriceSimulata))){
                            
                            gestoreMovimenti.setMatrice(matriceSimulata);
                            System.err.println("Dopo aver mangiato un pezzo:");
                            disegnaMatriceSpaziOccupati();
                            interfacciaGrafica.aggiornaBottoni( gestoreMovimenti.getMatrice() ); // Aggiorna La Visuale
                            
                            //se il re è stato mangiato
                            if( gestoreMovimenti.scaccoMatto(turno) ){ // Verifico Scacco Matto
                                System.err.println("Scacco matto");
                                disegnaMatriceSpaziOccupati();
                                interfacciaGrafica.finePartita();
                                
                            }
                            if(!gestoreMovimenti.controlloScaccoReAvversario(turno,matriceSimulata)){
                                System.err.println("Il re avversario adesso è sotto SCACCO");
                                System.err.println("Prima del controllo se il re avversario può essere salvato");
                                
                                //se il re avversario non può essere salvato
                                if(gestoreMovimenti.getListaPezziChePrevengonoScacco(gestoreMovimenti.getSpazioReAvversario(turno).getX(), gestoreMovimenti.getSpazioReAvversario(turno).getY(), gestoreMovimenti.getMatrice(), turno).isEmpty()){
                                    System.err.println("Scacco matto");
                                    interfacciaGrafica.finePartita();
                                }
                            }
                            
                            disattivaPosizione();
                            passaTurno();

                        
                        } else { // Se Provoco Lo Scacco
                            
                            gestoreMovimenti.setMatrice(gestoreMovimenti.coppiaMatrice(matriceSimulata2));
                            disattivaPosizione(); // Imposto Come Se Niente Fosse Stato Premuto
                        
                        }
                    
                    } else { // Se Contiene Un Pezzo Del Colore Corrente
                        
                        disattivaPosizione(); // Imposto Come Se Niente Fosse Stato Premuto
                    
                    }
                
                }
            
            }
        
        }
    
    }
    
    /**
     * Ricompila la matrice segnando quali spazi sono occupati da un pezzo
     */
    public void disegnaMatriceSpaziOccupati(){
        
        System.err.println("");
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                if(gestoreMovimenti.getMatrice()[j][i].eOccupato())
                    System.err.print("x");
                else
                    System.err.print("_");
            }
            System.err.println("");
        }
        System.err.println("");
    }
    
    /**
     * Segna sulla matrice passata come parametro quali spazi sono occupati da 
     * un pezzo
     * @param matrice la matrice da segnare
     */
     private void disegnaMatriceSpaziOccupati(Spazio[][] matrice){
        
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
    
} // Fine Classe GestoreTB