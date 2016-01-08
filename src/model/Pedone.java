package model;

/**
 * Classe del Pedone
 * Tutte le classi dei colori definiscono oggetti che vengono presi in esame durante i controlli svolti dal package Contoller
 * @author Viktor Teren VR379996, Michael Andronic VR370063, Gaetano Cavaler VR379845
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
     * Metodo che, se chiamato, imposta che il pedone e stato mosso 
    */
    public void impostaMosso(){
        
        mosso = true;
    
    }

} // Fine Classe Pedone