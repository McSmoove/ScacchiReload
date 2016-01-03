package model;

/**
 * Classe astratta che definisce alcune funzioni comuni a tutti i pezzi della schacchiera
 * @author Viktor, Michael, Gaetano
*/
public abstract class Pezzo{
    
    private final Colore colore;
    private boolean locked;
    
    public Pezzo( Colore colore ){
        
        this.colore = colore;
    
    }
    
    public Colore getColore(){
        
        return colore;
    
    }

}