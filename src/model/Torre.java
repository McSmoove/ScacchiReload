package model;

/** 
 * Classe della Torre
 * Tutte le classi dei pezzi definiscono gli oggetti che vengono presi in esame durante i controlli svolti dal package Contoller
 * @author Viktor Teren VR379996, Michael Andronic VR370063, Gaetano Cavaler VR379845
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
     * Metodo che, se chiamato, imposta che la torre e stata mossa 
    */
    public void impostaMosso(){
        
        mosso = true;
    
    }

} // Fine Classe Torre