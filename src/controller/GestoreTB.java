package controller;

import java.awt.event.ActionEvent;
import java.util.Arrays;
import javax.swing.JButton;
import model.Bianco;
import model.Colore;
import model.Nero;
import model.Pedone;
import model.Pezzo;
import model.Re;
import model.Spazio;
import model.Torre;
import view.InterfacciaGrafica;
import view.PromozionePedone;

/**
 *
 * @author Tyrande
 */
public class GestoreTB {
    
    private Colore turno;
    private boolean attivato;
    private int xAttivato,yAttivato;
    GestoreMovimenti gestoreMovimenti;
    InterfacciaGrafica interfacciaGrafica;
    JButton[][] matriceBottoni;
    private int xPedoneTrasformato,yPedoneTrasformato;
    int x,y;
    Spazio[][] spazioTemporaneo;
    Spazio[][] matriceSimulata = null;
    Spazio[][] matriceSimulata2 = null;
    
    public GestoreTB(GestoreMovimenti gm, InterfacciaGrafica ig){
        attivato = false;
        turno = new Bianco();
        gestoreMovimenti = gm;
        interfacciaGrafica = ig;
        matriceBottoni = interfacciaGrafica.getMatriceBottoni();
        
    }
    
    public Colore getTurno(){
        
        return turno;
    
    }
    
    public void setTurno(Colore t){
        
        turno = t;
        bloccoPezziIniziale();
        getInterfacciaGrafica().setMessaggio( "Tocca Al " + t.toString() );
    
    }
    
    private InterfacciaGrafica getInterfacciaGrafica(){
        return interfacciaGrafica;
    }
    
    public void passaTurno(){
        
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
    
    public void attiva(){
        
        attivato = true;
    
    }
    
    public void disattiva(){
        
        attivato = false;
    
    }
    
    private int getXAttivato(){
        return xAttivato;
    }
    
    private int getYAttivato(){
        return yAttivato;
    }
    
    private void setXAttivato(int x){
        xAttivato=x;
    }
    
    private void setYAttivato(int y){
        yAttivato=y;
    }
    
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

                // Per Evitare Il Null Pointer Exception Bisogna
                // Dividere Il If In Due Casi Per Non Fare Il Controllo Su Un Pezzo Che Non Esiste Cercanco Locazioni Vuote
                if( !( gestoreMovimenti.getMatrice()[i][j].eOccupato() ) ){
                    
                    matriceBottoni[ i ][ j ].setEnabled( false );
                
                } else { // Con L'Else Sono Sicuro Che Esiste L'Occupante
                    
                    if( !( gestoreMovimenti.getMatrice()[i][j].getOccupante().getColore().equals( getTurno() ) ) ){ // Cerco Locazioni Del Colore Sbagliato
                        
                        matriceBottoni[ i ][ j ].setEnabled( false );
                    
                    }
                    //else colore del turno corrente ma essendo sotto scacco non salvano il re
                
                }
            
            }
        
        }
    
    }
    
    private void attivaPosizione(int x,int y){
        setXAttivato(x);
        setYAttivato(y);
        attiva();
    }
    
        private void disattivaPosizione( int x, int y ){
        
        disattiva();
        
    }
        
    private void disattivaPosizione(){
        
        disattivaPosizione( getXAttivato(), getYAttivato() );
    
    }
    
    public void setXPedoneTrasformato(int x){
        xPedoneTrasformato=x;
    }
    
    public void setYPedoneTrasformato(int y){
        yPedoneTrasformato=y;
    }
    
    public int getXPedoneTrasformato(){
        return xPedoneTrasformato;
    }
    
    public int getYPedoneTrasformato(){
        return yPedoneTrasformato;
    }
    
    public void setPedoneTrasformato(Spazio s){
        Pezzo p=s.getOccupante();
        xPedoneTrasformato=x;
        yPedoneTrasformato=y;
        gestoreMovimenti.getMatrice()[s.getX()][s.getY()].setOccupante(p);
        interfacciaGrafica.aggiornaBottoni( gestoreMovimenti.getMatrice() );
    }
    
    
    public void pressionePulsanteScacchiera(ActionEvent e) throws Exception{
        
        x = 0; // Identificatore X Della Matrice Del Bottone Dove Premo
        y = 0;// Identificatore Y Della Matrice Del Bottone Dove Premo
        
        JButton b;
        
        GestoreMovimenti gestoreTemporaneo;
        Colore turno = getTurno();
        JButton[][] matriceScacchiera = interfacciaGrafica.getMatriceBottoni();
        int indiciBottoni[][] = new int[ 8 ][ 8 ];
        
        b = ( JButton ) e.getSource();
        
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
        
        if( !isAttivato() ){ // Guardo Se Nello Stesso Turno Non E Abilitato Un Bottone
            
            if( gestoreMovimenti.getMatrice()[ x ][ y ].eOccupato() ){ // Guardo Se Lo Spazio Contiene Qualcosa
                
                if( turno.equals( gestoreMovimenti.getMatrice()[ x ][ y ].getOccupante().getColore() ) ){ //Se Cio Che Contiene Ha Lo Stesso Colore Del Turno Corrente

                    // Se Il Re Del Turno Corrente Non è Sotto Scacco
                    System.err.println( " Controllo Scacco Fare Debug" );
                    if( true ){

                        // Abilito Le Posizioni Dove Puo Muoversi Il Pezzo ( Non Implementato )
                        // Metto Il Pezzo Premuto In Gestione Turno E Lo Attivo
                        attivaPosizione( x, y );
                        //salvaStato();
                    
                    } else if( ( ( turno instanceof Bianco ) && gestoreMovimenti.controlloScacco( gestoreMovimenti.getSpazioReBianco().getX(), gestoreMovimenti.getSpazioReBianco().getY(), new Bianco(), gestoreMovimenti.getMatrice() ) == true ) || ( ( turno instanceof Nero ) && gestoreMovimenti.controlloScacco( gestoreMovimenti.getSpazioReNero().getX(), gestoreMovimenti.getSpazioReNero().getY(), new Nero(), gestoreMovimenti.getMatrice() ) == true ) ){ // Se Il Re E Sotto Scacco

                        // Se Il Pezzo Può Salvare Il Re Devo Controllare La Matrice Simulata, Ma Prima Devo Crearla In Base Al Turno
                      
                        if( turno instanceof Bianco ){ // Il Pezzo E Bianco
                            
                            if( gestoreMovimenti.getMatricePezziChePrevengonoScacco( gestoreMovimenti.getSpazioReBianco().getX(), gestoreMovimenti.getSpazioReBianco().getY(), gestoreMovimenti.getMatrice(), new Bianco() )[ x ][ y ] == 1 ){
                                
                                attivaPosizione( x, y );
                                //salvaStato();
                            
                            }
                        
                        } else if( gestoreMovimenti.getMatricePezziChePrevengonoScacco( gestoreMovimenti.getSpazioReBianco().getX(), gestoreMovimenti.getSpazioReBianco().getY(), gestoreMovimenti.getMatrice(), new Nero() )[ x ][ y ] == 1 ){ // Il Pezzo E Nero
                            
                            attivaPosizione( x, y );
                            //salvaStato();
                            //disegnaMatriceSpaziOccupati();
                        
                        }
                    
                    }
                        
                }
            
            }
        
        } else { // Se Sono Qui significa Che è Stato Premuto Precedentemente Un Pezzo Valido
            
            if( x==xAttivato && y ==yAttivato ){ // Se La Posizione Dove Premo E Quella Dove Ho Premuto Prima
                
                System.err.println( "DEBUG: Premuto Sulla Posizione Precedente" );
                disattiva(); // Imposto Come Se Niente Fosse Stato Premuto
            
            } else { // Se Sono Qua Ho Premuto In Una Posizione Diversa
                
                if( !gestoreMovimenti.getMatrice()[ x ][ y ].eOccupato() ){ // Se E Una Locazione Vuota
                    
                    System.err.println( "DEBUG: Premuto Su Una Locazione Vuota" );
                    
                    if( gestoreMovimenti.spostabileIn( gestoreMovimenti.getMatrice()[xAttivato][yAttivato], x, y ) ){ // Se E Una Locazione Consentita Dal Pezzo
                    
                        System.err.println( "DEBUG: Premuto In Una Posizione Valida" );
                        //TRASCURO QUESTA PARTE, CALCOLI TROPPO PESANTI
                        // Se Spostando Il Pezzo In Questa Locazione Non Ho Scacco / Scacco Matto, Devo Simulare La Scachiera Dopo La Mossa
                        //matriceSimulata = coppiaMatrice( gestoreMovimenti.getMatrice() );
                        //matriceSimulata.spostaPezzo( gestoreTurni.getSpazioAttivato().getOccupante(), x, y );
                        
                        
                        //se non provoco scacco matto (da implementare)
                        //matriceSimulata=gestoreMovimenti.getMatriceCoppia(); 
                        matriceSimulata=gestoreMovimenti.getMatrice().clone();
                        System.err.println("Matrice simulata 1 :");
                        disegnaMatriceSpaziOccupati(matriceSimulata);
                        //matriceSimulata2=new Spazio[8][8];
                        matriceSimulata2=gestoreMovimenti.coppiaMatrice(matriceSimulata);
                        System.err.println("Matrice simulata 2 :");
                        disegnaMatriceSpaziOccupati(matriceSimulata2);
                        //con la prossima riga spostando il pezzo nella matrice simulata si perde
                        //il legame con lo spazioa ttivato e il pezzo attivato
                        gestoreMovimenti.spostaPezzo( gestoreMovimenti.getMatrice()[xAttivato][yAttivato], x, y,matriceSimulata );
                        //gestoreMovimenti.spostaPezzo( gestoreMovimenti.getMatrice()[xAttivato][yAttivato], x, y );
                        //System.err.println(((Pedone)gestoreMovimenti.getMatrice()[xAttivato][yAttivato].getOccupante()).mosso()); //debug
                        //gestoreMovimenti.getReBianco().getColore(); //debug
                        
                        //gestoreMovimenti.controlloScacco(gestoreMovimenti.getReBianco()); //debug
                        //gestoreMovimenti.controlloScacco(gestoreMovimenti.getReNero()); //debug
                        //gestoreTurni.getSpazioAttivato().getOccupante();//debug
                        //gestoreTurni.getSpazioAttivato().getOccupante().getColore(); //debug
                        //if(true){ //debug
                        
                        
                        //se il re non è sotto scacco
                        if( (getTurno() instanceof Bianco && gestoreMovimenti.controlloScacco(new Bianco(),matriceSimulata)) 
                        
                        || (getTurno() instanceof Nero && gestoreMovimenti.controlloScacco(new Nero(),matriceSimulata))){
                            
                            //gestoreMovimenti.setMatrice( matriceSimulata ); // Contrassegno Lo Spazio Occupato Prima Come Non Occupato
                            //versione senza matrice simulata:
                            //gestoreMovimenti.spostaPezzo( gestoreMovimenti.getMatrice()[xAttivato][yAttivato], x, y );//DEBUG:getSpazioAttivato è null!!!
                            gestoreMovimenti.setMatrice(matriceSimulata);
                            disattivaPosizione(); // Tolgo Il Bordo Del Bottone ???
                            passaTurno(); // Passo Il Turno
                            disegnaMatriceSpaziOccupati();
                            
                            
                            interfacciaGrafica.aggiornaBottoni( gestoreMovimenti.getMatrice() ); // Aggiorna La Visuale
                        
                        } else { // Se Provoca Lo Scacco Matto
                            //gestoreMovimenti.setMatrice(matriceSimulata2);
                            System.err.println("Prima del roll back:");
                            disegnaMatriceSpaziOccupati();
                            //matriceSimulata=gestoreMovimenti.coppiaMatrice(matriceSimulata2);
                            gestoreMovimenti.setMatrice(gestoreMovimenti.coppiaMatrice(matriceSimulata2));
                            //gestoreMovimenti.setMatrice(gestoreMovimenti.coppiaMatrice(matriceSimulata2));
                            disattivaPosizione(); // Imposto Come Se Niente Fosse Stato Premuto
                            System.err.println("Dopo il roll back originale:");
                            disegnaMatriceSpaziOccupati();
                            //System.err.println("Dopo il roll back matrice simulata 1:");
                            //disegnaMatriceSpaziOccupati(matriceSimulata);
                            
                        }
                    
                    } else { // Se Non è Una Locazione Consentita Dal Pezzo
                    
                        System.err.println( "DEBUG: Premuto In Una Posizione Non Consentita Dal Pezzo, Bisogna Farla Diventare Rossa" );
                        disattivaPosizione(); // Imposto Come Se Niente Fosse Stato Premuto
                    
                    }
                } else { // Se E Una Locazione Non Vuota
                    
                    System.err.println( "DEBUG: Schiaccio Su Una Locazione Non Vuota" );
                    
                    if( gestoreMovimenti.spostabileIn( gestoreMovimenti.getMatrice()[xAttivato][yAttivato], x, y ) ){ // Se E Una Locazione Consentita Dal Pezzo E Contiene Un Pezzo Di Colore Diverso
                        
                        System.err.println( "Del Colore Diverso" ); // Se Non Provoca Scacco Del Proprio Colore -> Scacco Matto ( Faccio La Simulazione )
                        //matriceSimulata=coppiaMatrice(gestoreMovimenti.getMatrice());
                        //gestoreMovimenti.spostaPezzo(gestoreMovimenti.getMatrice()[xAttivato][yAttivato], x, y,matriceSimulata);
                        System.err.println( "DEBUG: Non Faccio Il Controllo Scacco QUI" );
                        //bisognerà togliere if true
                        if( true ){ // Mangia Il Pezzo In Questa Locazione
                            
                            //non so se è indispensabile x il refresh
                            //x ora lo tolgo?
                            //gestoreMovimenti.getMatrice()[x][y].distruggi( interfacciaGrafica );
                            //la riga sucessiva sostituisce quella dopo commentata
                            gestoreMovimenti.spostaPezzo( gestoreMovimenti.getMatrice()[xAttivato][yAttivato], x, y);
                            //gestoreMovimenti.setMatrice( matriceSimulata ); // Sposto Effettivamente Il Pezzo
                            
                            // Contrassegno Lo Spazio Occupato Prima Come Non Occupato
                            //matriceSimulata.getSpazio( gestoreTurni.getSpazioAttivato().getX(), gestoreTurni.getSpazioAttivato().getY() ).setOccupato( false ); // Prova Per Debug
                            //matriceSimulata.getSpazio( gestoreTurni.getSpazioAttivato().getX(), gestoreTurni.getSpazioAttivato().getY() ).setOccupante( null );
                            disegnaMatriceSpaziOccupati();
                            disattivaPosizione(); // Disattiva Il Bordo
                            System.err.println("Truno correnti: "+turno);
                            passaTurno(); // Passo Il Turno
                            System.err.println("Truno correnti: "+turno);
                            interfacciaGrafica.aggiornaBottoni( gestoreMovimenti.getMatrice() ); // Aggiorna La Visuale
                            
                            if( gestoreMovimenti.scaccoMatto() ){ // Verifico Scacco Matto
                                
                                interfacciaGrafica.finePartita();
                            
                            }

                        
                        } else { // Se Provoco Lo Scacco Matto
                            
                            disattivaPosizione(); // Imposto Come Se Niente Fosse Stato Premuto
                        
                        }
                    
                    } else { // Se Contiene Un Pezzo Del Colore Corrente
                        
                        disattivaPosizione(); // Imposto Come Se Niente Fosse Stato Premuto
                    
                    }
                
                }
            
            }
        
        }
    
    }
    
    private void disegnaMatriceSpaziOccupati(){
        
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
    
}

