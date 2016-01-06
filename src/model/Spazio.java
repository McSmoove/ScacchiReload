package model;

// VIKTOR CERCA TU TI MIGLIORARE IL JAVADOC DI STA CLASSE
// NON SO PERCHE ABBIAMO 3 COSTRUTTORI DIVERSI E POI IN SEGUITO ABBIAMO INIZIALIZZA CHE FA LA STESSA COSA DEI COSTRUTTORI

/**
 * La classe spazio  essenziale per sapere quale pezzo sta occupando una 
 * casella di gioco o se la casella e libera
 * @author Viktor, Michael, Gaetano
*/
public class Spazio{
    
    /**
     * Variabile che indica se un determinato spazio e occupato o meno
    */
    private boolean occupato;
    
    /**
     * Variabile che indica l'occupante di un determinato spazio
    */
    private Pezzo occupante;
    
    /**
     * Coordinate dello spazio in esame
    */
    private int x, y;
    
    /**
     * Costruttore senza parametri di uno spazio usato per inizializzare
     * Coordinate identificate tramite X e Y della scacchiera
     * Identificano la presenza o non di un certo pezzo sulla scacchiera
     * -1, -1 Identificano uno spazio fuori dalla casella di gioco usato per i pezzi distrutti
    */
    public Spazio(){
        
        occupato = false;
        occupante = null;
        x = -1;
        y = -1;
    
    }
    
    /**
     * Costruttore con parametri X e Y contenente le coordinate dove si vuole collocare uno spazio vuoto
     * @param x - L'ascissa sulla quale collocare lo spazio vuoto
     * @param y - L'ordinata sulla quale collocare uno spazio vuoto
    */
    public Spazio( int x, int y ){
        
        occupato = false;
        this.x = x;
        this.y = y;
    
    }
    
    /**
     * Costruttore con parametri X e Y contenente le coordinate dove si vuole collocare un'determinato pezzo P
     * @param x - L'ascissa sulla quale collocare lo spazio vuoto
     * @param y - L'ordinata sulla quale collocare uno spazio vuoto
     * @param p - Il pezzo che si vuole collocare sulle coordinate fornite
    */
    public Spazio( int x, int y, Pezzo p ){
        
        occupato = true;
        occupante = p;
        this.x = x;
        this.y = y;
        
    }
    
    /**
     * Costruttore con parametro spazio che clona se stesso ???
     * @param s - Lo spazio da clonare ???
    */
    public Spazio( Spazio s ){
        
        occupato = s.eOccupato();
        occupante = s.getOccupante();
        x = s.getX();
        y = s.getY();
    
    }
    
    /**
     * Metodo che preso uno spazio in esame cambia il pezzo su di esso
     * @param p - Il pezzo con il quale cambiare quello in esame
    */
    public void cambiaPezzo( Pezzo p ){
        
        occupante=p;
        occupato = occupante != null;
    
    }
    
    /**
     * Metodo che verifica se lo spazio corrente e occupato o meno
     * @return True se e occupato, false altrimenti
    */
    public boolean eOccupato(){
        
        return occupato;
    
    }
    
    /**
     * Metodo che ritorna il pezzo occupante o meno dello spazio in esame
     * @return occupante - Il pezzo che occupa o meno lo spazio in esame
     */
    public Pezzo getOccupante(){
        
        return occupante;
    
    }
    
    /**
     * Metodo che imposta se lo spazio in esame e occupato o meno
     * @param b True se lo spazio in esame si vuole impostare come occupato, false altrimenti
    */
    public void setOccupato( boolean b ){
        
        occupato = b;
    
    }
    
    /**
     * Metodo che ritorna la coordinata X dello spazio in esame
     * @return x - La coordinata X dello spazio in esame
    */
    public int getX(){
        
        return x;
    
    }
    
    /**
     * Metodo che ritorna la coordinata Y dello spazio in esame
     * @return y - La coordinata Y dello spazio in esame
    */
    public int getY(){
        
        return y;
    
    }
    
    /**
     * Metodo che imposta la coordinata X sullo spazio in esame
     * @param x - La coordinata X da impostare sullo spazio in esame
    */
    public void setX(int x){
        
        this.x = x;
    
    }
    
    /**
     * Metodo che imposta la coordinata Y sullo spazio in esame
     * @param y - La coordinata Y da impostare sullo spazio in esame
    */
    public void setY(int y){
        
        this.y = y;
    
    }
    
    /**
     * Metodo che inizializza un certo spazio X, Y
     * @param x - La coordinata X dello spazio da inizializzare
     * @param y - La coordinata Y dello spazio da inizializzare
    */
    public void inizializzaSpazio( int x, int y ){
        
        occupato = false;
        this.x = x;
        this.y = y;
    
    }
    
    /**
     * Metodo che inizializza un certo spazio X, Y con un pezzo P
     * @param p - Il pezzo con il quale si vuole inizializzare lo spazio
     * @param x - La coordinata X dello spazio da inizializzare
     * @param y - La coordinata Y dello spazio da inizializzare
    */
    public void inizializzaSpazio( Pezzo p, int x, int y ){
        
        occupato = true;
        occupante = p;
        this.x = x;
        this.y = y;
    
    }

} // Fine Classe Spazio