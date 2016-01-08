package model;

/**
 * Classe astratta del Colore. Contiene alcuni metodi di base comuni in entrambi i colori
 * Tutte le classi dei colori definiscono oggetti che vengono presi in esame durante i controlli svolti dal package Contoller
 * @author Viktor Teren VR379996, Michael Andronic VR370063, Gaetano Cavaler VR379845
*/
public abstract class Colore{
    
    /**
     * Il colore preso in esame da questa classe
    */
    private int colore;
    
    /**
     * Variabile scelta da noi per indicare che il colore Bianco = 1
    */
    public static int Bianco = 1;
    
    /**
     * Variabile scelta da noi per indicare che il colore Nero = -1
    */
    public static int Nero = -1;
    
    
    /**
     * Override del metodo equals della classe Object
     * Dato un colore verifica se è uguale ad un altro oggetto di classe Colore
     * @param o - L'oggetto con il quale confrontare
     * @return True se sono uguali, false altrimenti
    */
    @Override
    public boolean equals( Object o ){
        
        Colore c;
        
        if( o instanceof Colore ){
            
            c = ( Colore ) o;
            
            return c.getColore() == this.colore;
        
        }
        
        return false;
    
    }
    
    /**
     * Override del metodo hashCode della classe Object
     * Dato un'oggetto di istanza Colore, ritorna il hashCode univoco di esso
     * @return Il hashCode del oggetto di istanza Colore
    */
    @Override
    public int hashCode(){ // La Equals Vuole La hashCode()
        
        return this.colore;
    
    }
    
    /**
     * Metodo che ritorna il colore dell'oggetto
     * @return colore - Il colore dell'oggetto
    */
    public int getColore(){
        
        return colore;
    
    }

} // Fine Classe Colore