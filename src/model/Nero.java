package model;

// VIKTOR CERCA TU TI MIGLIORARE IL JAVADOC DI STA CLASSE
// NON CI CAPISCO NULLA SU COME GESTIAMO STI COLORI

/**
 * Classe del colore nero. Tutte le classi dei colori definiscono oggetti che 
 * vengono presi in esame durante i controlli nelle classi del package Contoller
 * @author Viktor, Michael, Gaetano
*/
public class Nero extends Colore{
    
    /**
     * Il colore preso in esame da questa classe
    */
    private final int colore;
    
    /**
     * Classe che descrive il colore Nero
    */
    public Nero(){
        
        colore = Colore.Nero;
    
    }
    
    /**
     * Override del metodo equals della classe Object
     * Confronta se un dato ogetto e un istanza di Nero
     * @param o - L'oggetto con il quale confrontare
     * @return True se sono uguali, false altrimenti
    */
    @Override
    public boolean equals( Object o ){
        
        return o instanceof Nero;
    
    }
    
    /**
     * Override del metodo hashCode della classe Object
     * Dato un oggetto di istanza Nero, ritorna il hashCode univoco di esso
     * @return Il hashCode del oggetto di istanza Nero
    */
    @Override
    public int hashCode(){
        
        return colore ^ colore;
    
    }
    
    /**
     * Override del metodo toString della classe Object
     * Ritorna una stringa che descrive l'ogetto di questa classe
     * @return La stringa che descrive l'oggetto di questa classe
    */
    @Override
    public String toString(){
        
        return "Nero";
    
    }

} // Fine Classe Nero