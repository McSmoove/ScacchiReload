package model;

/**
 * Classe del re. Tutte le classi dei pezzi definiscono oggetti che vengono 
 * presi in esame durante i controlli nelle classi del package Contoller
 * @author Viktor, Michael, Gaetano
 */

public class Re extends Pezzo{
    

    private boolean mosso = false; // Controllo Per Il Primo Movimento ( Perche )
    
    public Re(Colore colore ){
        
        super(colore );
    
    }
    
    public boolean mosso(){
        
        return mosso;
    
    }
    
    public void impostaMosso(){
        
        mosso = true;
    
    }

}