package model;

/**
 * Classe del pedone. Tutte le classi dei pezzi definiscono oggetti che 
 * vengono presi in esame durante i controlli nelle classi del package Contoller
 * @author Viktor, Michael, Gaetano
 */

public class Pedone extends Pezzo{
    

    private boolean mosso = false; // Controllo Per Il Primo Movimento
    
    public Pedone( Colore colore ){
        
        super( colore );
    
    }
    
    public boolean mosso(){
        
        return mosso;
    
    }
    
    public void impostaMosso(){
        
        mosso = true;
        System.err.println("Pedone: mosso");
    
    }

}