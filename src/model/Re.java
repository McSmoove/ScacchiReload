package model;

/**
 * Classe del Re
 * Tutte le classi dei colori definiscono oggetti che vengono presi in esame durante i controlli svolti dal package Contoller
 * @author Viktor Teren VR379996, Michael Andronic VR370063, Gaetano Cavaler VR379845
*/
public class Re extends Pezzo{
    
    /**
     * Valore usato per il controllo se il re e stato mosso o meno
    */
    private boolean mosso = false;
    
    /**
     * Classe che descrive il comportamento di un Re
     * @param colore - Il colore del re
    */
    public Re( Colore colore ){
        
        super( colore );
    
    }
    
    /**
     * Metodo che verifica se il re si e mosso o meno
     * @return mosso - True se non si e mai mosso, false altrimenti
    */
    public boolean mosso(){
        
        return mosso;
    
    }
    
    /**
     * Metodo che, se chiamato, imposta che il re e stato mosso 
    */
    public void impostaMosso(){
        
        mosso = true;
    
    }

} // Fine Classe Re