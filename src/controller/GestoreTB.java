package controller;

import java.awt.event.ActionEvent;
import javax.swing.JButton;
import model.Bianco;
import model.Colore;
import model.Nero;
import model.Pezzo;
import model.Spazio;
import view.InterfacciaGrafica;


/**
 * Questa classe cambia i turni quando richiesto e blocca i pulsanti che non possono
 * essere premuti (per esempio, se non è il turno del bianco blocca tutti i pulsanti
 * con una pedina bianca sopra)
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
    
    /**
     * 
     * @return ritorna il colore del giocatore di cui è il turno 
     */
    public Colore getTurno(){
        
        return turno;
    
    }
    
    /**
     * Inizializza il turno e aggiorna l'interfaccia segnado a quale colore corrisponde il
     * turno
     * @param t colore del turno corrente
     */
    public void setTurno(Colore t){
        
        turno = t;
        bloccoPezziIniziale();
        getInterfacciaGrafica().setMessaggio( "Tocca Al " + t.toString() );
    
    }
    
    /**
     * Ritorna il parametro interfacciaGrafica (serve per aggiornare la posizione dei pezzi
     * visualizzata dall'utente)
     * @return 
     */
    private InterfacciaGrafica getInterfacciaGrafica(){
        return interfacciaGrafica;
    }
    
    /**
     * Cambia il colore del turno corrente
     */
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
    private void setXAttivato(int x){
        xAttivato=x;
    }
    
    /**
     * memorizza l'ordinata della casella attivata
     * @param x 
     */
    private void setYAttivato(int y){
        yAttivato=y;
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
    
    /**
     * Attiva la casella alla data coordinata 
     * @param x ascissa della coordinata
     * @param y rdinata della coordinata
     */
    private void attivaPosizione(int x,int y){
        setXAttivato(x);
        setYAttivato(y);
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
         * Viktor (ridondante)
         * Metodo che chiama disattivaPosizione
         */
    private void disattivaPosizione(){
        
        disattivaPosizione( getXAttivato(), getYAttivato() );
    
    }
    
    /**
     * memorizza l'ascissa della casella sulla quale si trova il pedone promosso
     * @param y 
     */
    public void setXPedoneTrasformato(int x){
        xPedoneTrasformato=x;
    }
    
    /**
     * memorizza l'ordinata della casella sulla quale si trova il pedone promosso
     * @param y 
     */
    public void setYPedoneTrasformato(int y){
        yPedoneTrasformato=y;
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
     * segna sulla scacchiera dove si trova il pedone promosso
     * @param s lo spazio dove si trova il pedone promosso
     */
    public void setPedoneTrasformato(Spazio s){
        Pezzo p=s.getOccupante();
        xPedoneTrasformato=x;
        yPedoneTrasformato=y;
        gestoreMovimenti.getMatrice()[s.getX()][s.getY()].cambiaPezzo(p);
        interfacciaGrafica.aggiornaBottoni( gestoreMovimenti.getMatrice() );
    }
    
    /**
     * segna le coordinate di dove è stata premuta la scacchiera
     * @param e pressione di un pulsante sulla schacchiera
     * @throws Exception 
     */
    public void pressionePulsanteScacchiera(ActionEvent e) throws Exception{
        
        x = 0; // Identificatore X Della Matrice Del Bottone Dove Premo
        y = 0;// Identificatore Y Della Matrice Del Bottone Dove Premo
        
        JButton b;
        
        GestoreMovimenti gestoreTemporaneo;
        Colore turno = getTurno();
        JButton[][] matriceScacchiera = interfacciaGrafica.getMatriceBottoni();
        int indiciBottoni[][] = new int[ 8 ][ 8 ];
        
        b = ( JButton ) e.getSource();
        
        System.err.println("Alla pressione del pulsante:");
        //disegnaMatriceSpaziOccupati();
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
                    System.err.println("Prima di chiamare spostabileIn");
                    //disegnaMatriceSpaziOccupati();
                    if( gestoreMovimenti.spostabileIn( gestoreMovimenti.getMatrice()[xAttivato][yAttivato], x, y ) ){ // Se E Una Locazione Consentita Dal Pezzo
                        
                        System.err.println( "DEBUG: Premuto In Una Posizione Valida" );
                        //disegnaMatriceSpaziOccupati();
                        //TRASCURO QUESTA PARTE, CALCOLI TROPPO PESANTI
                        // Se Spostando Il Pezzo In Questa Locazione Non Ho Scacco / Scacco Matto, Devo Simulare La Scachiera Dopo La Mossa
                        //matriceSimulata = coppiaMatrice( gestoreMovimenti.getMatrice() );
                        //matriceSimulata.spostaPezzo( gestoreTurni.getSpazioAttivato().getOccupante(), x, y );
                        
                        
                        //se non provoco scacco matto (da implementare)
                        //matriceSimulata=gestoreMovimenti.getMatriceCoppia(); 
                        matriceSimulata=gestoreMovimenti.getMatrice().clone();
                        System.err.println("Matrice simulata 1 :");
                        //disegnaMatriceSpaziOccupati(matriceSimulata);
                        //matriceSimulata2=new Spazio[8][8];
                        matriceSimulata2=gestoreMovimenti.coppiaMatrice(matriceSimulata);
                        System.err.println("Matrice simulata 2 :");
                        //disegnaMatriceSpaziOccupati(matriceSimulata2);
                        //con la prossima riga spostando il pezzo nella matrice simulata si perde
                        //il legame con lo spazioa ttivato e il pezzo attivato
                        System.err.println("Prima di chiamare sposta pezzo");
                        //disegnaMatriceSpaziOccupati();
                        gestoreMovimenti.spostaPezzo( gestoreMovimenti.getMatrice()[xAttivato][yAttivato], x, y,matriceSimulata );
                        System.err.println("Dopo aver chiamato sposta pezzo");
                        //disegnaMatriceSpaziOccupati();
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
                            interfacciaGrafica.aggiornaBottoni( gestoreMovimenti.getMatrice() ); // Aggiorna La Visuale
                        
                        } else { // Se Provoca Lo Scacco Matto
                            //gestoreMovimenti.setMatrice(matriceSimulata2);
                            //System.err.println("Prima del roll back:");
                            //disegnaMatriceSpaziOccupati();
                            //matriceSimulata=gestoreMovimenti.coppiaMatrice(matriceSimulata2);
                            gestoreMovimenti.setMatrice(gestoreMovimenti.coppiaMatrice(matriceSimulata2));
                            //gestoreMovimenti.setMatrice(gestoreMovimenti.coppiaMatrice(matriceSimulata2));
                            disattivaPosizione(); // Imposto Come Se Niente Fosse Stato Premuto
                            //System.err.println("Dopo il roll back originale:");
                            //disegnaMatriceSpaziOccupati();
                            //System.err.println("Dopo il roll back matrice simulata 1:");
                            //disegnaMatriceSpaziOccupati(matriceSimulata);
                            
                        }
                    
                    } else { // Se Non è Una Locazione Consentita Dal Pezzo
                    
                        System.err.println( "DEBUG: Premuto In Una Posizione Non Consentita Dal Pezzo, Bisogna Farla Diventare Rossa" );
                        disattivaPosizione(); // Imposto Come Se Niente Fosse Stato Premuto
                    
                    }
                } else { // Se E Una Locazione Non Vuota
                    
                    System.err.println( "DEBUG: Schiaccio Su Una Locazione Non Vuota" );
                    System.err.println("Prima di chiamare spostabile in");
                    disegnaMatriceSpaziOccupati();
                    if( gestoreMovimenti.spostabileIn( gestoreMovimenti.getMatrice()[xAttivato][yAttivato], x, y ) ){ // Se E Una Locazione Consentita Dal Pezzo E Contiene Un Pezzo Di Colore Diverso
                        
                        System.err.println( "Del Colore Diverso" ); // Se Non Provoca Scacco Del Proprio Colore -> Scacco Matto ( Faccio La Simulazione )
                        disegnaMatriceSpaziOccupati();
                        //matriceSimulata=coppiaMatrice(gestoreMovimenti.getMatrice());
                        matriceSimulata=gestoreMovimenti.getMatrice().clone();
                        gestoreMovimenti.spostaPezzo( gestoreMovimenti.getMatrice()[xAttivato][yAttivato], x, y);
                        //gestoreMovimenti.spostaPezzo(gestoreMovimenti.getMatrice()[xAttivato][yAttivato], x, y,matriceSimulata);
                        //System.err.println( "DEBUG: Non Faccio Il Controllo Scacco QUI" );
                        
                        if( (getTurno() instanceof Bianco && gestoreMovimenti.controlloScacco(new Bianco(),matriceSimulata)) 
                        
                        || (getTurno() instanceof Nero && gestoreMovimenti.controlloScacco(new Nero(),matriceSimulata))){
                            
                            //non so se è indispensabile x il refresh
                            //x ora lo tolgo?
                            //gestoreMovimenti.getMatrice()[x][y].distruggi( interfacciaGrafica );
                            //la riga sucessiva sostituisce quella dopo commentata
                            
                            //gestoreMovimenti.setMatrice( matriceSimulata ); // Sposto Effettivamente Il Pezzo
                            gestoreMovimenti.setMatrice(matriceSimulata);
                            System.err.println("Dopo aver mangiato un pezzo:");
                            disegnaMatriceSpaziOccupati();
                            //disegnaMatriceSpaziOccupati();
                            // Contrassegno Lo Spazio Occupato Prima Come Non Occupato
                            //matriceSimulata.getSpazio( gestoreTurni.getSpazioAttivato().getX(), gestoreTurni.getSpazioAttivato().getY() ).setOccupato( false ); // Prova Per Debug
                            //matriceSimulata.getSpazio( gestoreTurni.getSpazioAttivato().getX(), gestoreTurni.getSpazioAttivato().getY() ).setOccupante( null );
                            //disegnaMatriceSpaziOccupati();
                            disattivaPosizione(); // Disattiva Il Bordo
                           
                            System.err.println("Truno correnti: "+turno);
                            interfacciaGrafica.aggiornaBottoni( gestoreMovimenti.getMatrice() ); // Aggiorna La Visuale
                            //se il re è stato mangiato
                            disegnaMatriceSpaziOccupati();
                            if( gestoreMovimenti.scaccoMatto(turno) ){ // Verifico Scacco Matto
                                System.err.println("Scacco matto");
                                disegnaMatriceSpaziOccupati();
                                interfacciaGrafica.finePartita();
                                
                            }
                            System.err.println("Prima del controllo se il re può essere salvato");
                            disegnaMatriceSpaziOccupati();
                            //se il re non può essere salvato
                            if(gestoreMovimenti.getListaPezziChePrevengonoScacco(gestoreMovimenti.getSpazioRe(turno).getX(), gestoreMovimenti.getSpazioRe(turno).getY(), gestoreMovimenti.getMatrice(), turno).isEmpty()){
                            //if(!gestoreMovimenti.controlloScaccoReAvversario(turno)){
                                System.err.println("Scacco matto");
                                interfacciaGrafica.finePartita();
                            }
                            System.err.println("Dopo il controllo se il re può essere salvato");
                            disegnaMatriceSpaziOccupati();
                            passaTurno();

                        
                        } else { // Se Provoco Lo Scacco Matto
                            gestoreMovimenti.setMatrice(gestoreMovimenti.coppiaMatrice(matriceSimulata2));
                            //gestoreMovimenti.setMatrice(gestoreMovimenti.coppiaMatrice(matriceSimulata2));
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
     * Viktor (ridondante)
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
    
}

