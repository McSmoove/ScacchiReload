package model;

/** 
 * Classe della torre. Tutte le classi dei pezzi definiscono oggetti che 
 * vengono presi in esame durante i controlli nelle classi del package Contoller
 * @author Viktor, Michael, Gaetano
*/
public class Torre extends Pezzo{
    
    /**
     * Valore usato per il controllo se la torre e stata mossa o meno
    */
    private boolean mosso = false;
    
    /**
     * Classe che descrive il comportamento di una Torre
     * @param colore - Il colore della torre
    */
    public Torre( Colore colore ){
        
        super( colore );
    
    }
    
    /**
     * Metodo che verifica se la torre si e mossa o meno
     * @return mosso - True se non si e mai mossa, false altrimenti
    */
    public boolean mosso(){
        
        return mosso;
    
    }
    
    /**
     * Metodo che, se chiamato, imposta che la torre si e mossa 
    */
    public void impostaMosso(){
        
        mosso = true;
    
    }

} // Fine Classe Torre