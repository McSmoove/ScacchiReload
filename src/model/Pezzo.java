package model;

/**
 * Classe astratta che definisce alcune funzioni comuni a tutti i pezzi della schacchiera
 * @author Viktor, Michael, Gaetano
*/
public abstract class Pezzo{
    
    private Colore colore;
    private boolean locked;
    
    public Pezzo( Colore colore ){
        
        this.colore = colore;
    
    }
    
    public Colore getColore(){
        
        return colore;
    
    }
   
    public void setLock( boolean lock ){
        
        locked = lock;
    
    }
    
    public boolean getLock(){
        
        return locked;
    
    }

}