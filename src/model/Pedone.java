package model;

/**
 * Classe del pedone. Tutte le classi dei pezzi definiscono oggetti che 
 * vengono presi in esame durante i controlli nelle classi del package Contoller
 * @author Viktor, Michael, Gaetano
*/
public class Pedone extends Pezzo{
    
    /**
     * Valore usato per il controllo se il pedone e stato mosso o meno
    */
    private boolean mosso = false;
    
    /**
     * Classe che descrive il comportamento di un Pedone
     * @param colore - Il colore del pedone
    */
    public Pedone( Colore colore ){
        
        super( colore );
    
    }
    
    /**
     * Metodo che verifica se il pedone si e mosso o meno
     * @return mosso - True se non si e mai mosso, false altrimenti
    */
    public boolean mosso(){
        
        return mosso;
    
    }
    
    /**
     * Metodo che, se chiamato, imposta che il pedone si e mosso 
    */
    public void impostaMosso(){
        
        mosso = true;
    
    }

} // Fine Classe Pedone