package model;

/**
 * Classe del colore bianco. Tutte le classi dei colori definiscono oggetti che 
 * vengono presi in esame durante i controlli nelle classi del package Contoller
 * @author Viktor, Michael, Gaetano
 */

public class Bianco extends Colore{
    
    private final int colore;
    
    public Bianco(){
        
        colore = Colore.Bianco;
    
    }
    
    @Override
    public boolean equals( Object o ){
        
        if( o instanceof Bianco ){ // If Innutile
            
            return true;
        
        }
        
        return false;
    
    }
    
    @Override
    public int hashCode(){ // Per La Equals Serve La hashCode()
        
        return colore;
    
    }
    
    @Override
    public String toString(){
        
        return "Bianco";
    
    }

}