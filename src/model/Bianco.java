package model;

// VIKTOR CERCA TU dI MIGLIORARE IL JAVADOC DI STA CLASSE
// NON CI CAPISCO NULLA SU COME GESTIAMO STI COLORI

/**
 * Classe del colore bianco. Tutte le classi dei colori definiscono oggetti che 
 * vengono presi in esame durante i controlli nelle classi del package Contoller
 * @author Viktor, Michael, Gaetano
*/
public class Bianco extends Colore{
    
    /**
     * Il colore preso in esame da questa classe
    */
    private final int colore;
    
    /**
     * Costruttore di base. Definisce quale colore prende l'oggetto colore.
    */
    public Bianco(){
        
        colore = Colore.Bianco;
    
    }
    
    /**
     * Override del metodo equals della classe Object
     * Confronta se un dato ogetto e un'istanza di Bianco
     * @param o - L'oggetto con il quale confrontare
     * @return True se sono uguali, false altrimenti
    */
    @Override
    public boolean equals( Object o ){
            
        return o instanceof Bianco;
    
    }
    
    /**
     * Override del metodo hashCode della classe Object
     * Dato un'oggetto di istanza Bianco, ritorna il hashCode univoco di esso
     * @return Il hashCode del oggetto di istanza Bianco
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
        
        return "Bianco";
    
    }

} // Fine Classe Bianco