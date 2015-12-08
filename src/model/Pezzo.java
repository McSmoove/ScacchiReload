package model;

/**
 * Classe astratta che definisce alcune funzioni comuni a tutti i pezzi della schacchiera
 * @author Viktor, Michael, Gaetano
 */

public abstract class Pezzo{
    
    private Colore colore;
    private boolean locked;
    
    public Pezzo(Colore colore1 ){
        
        colore = colore1;
    
    }
    
    public Colore getColore(){ // Da Implementare
        
        return colore;
    
    }
   
    public void setLock( boolean l ){
        
        locked = l;
    
    }
    
    public boolean getLock(){
        
        return locked;
    
    }

}