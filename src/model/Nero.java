package model;

/**
 * Classe del colore nero. Tutte le classi dei colori definiscono oggetti che 
 * vengono presi in esame durante i controlli nelle classi del package Contoller
 * @author Viktor, Michael, Gaetano
*/
public class Nero extends Colore{
    
    private final int colore;
    
    public Nero(){
        
        colore = Colore.Nero;
    
    }
    
    @Override
    public boolean equals( Object o ){
        
        return o instanceof Nero;
    
    }

    @Override
    public int hashCode(){ // La Equals Vuole La hashCode()
        
        return colore;
    
    }
    
    @Override
    public String toString(){
        
        return "Nero";
    
    }

}