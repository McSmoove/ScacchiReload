package model;

/** 
 * Classe della torre. Tutte le classi dei pezzi definiscono oggetti che 
 * vengono presi in esame durante i controlli nelle classi del package Contoller
 * @author Viktor, Michael, Gaetano
*/
public class Torre extends Pezzo{
    
    private boolean mosso = false;
    
    public Torre( Colore colore ){
        
        super( colore );
    
    }
    
    public boolean mosso(){
        
        return mosso;
    
    }
    
    public void impostaMosso(){
        
        mosso = true;
    
    }

}