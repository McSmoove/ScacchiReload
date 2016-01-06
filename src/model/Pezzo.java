package model;

/**
 * Classe astratta che definisce alcune funzioni comuni a tutti i pezzi della schacchiera
 * @author Viktor, Michael, Gaetano
*/
public abstract class Pezzo{
    
    /**
     * Indica il colore del pezzo preso in esame
    */
    private final Colore colore;
    
    /**
     * Metodo che inizializza un pezzo astratto di un certo colore
     * @param colore - Il colore del pezzo preso in esame
    */
    public Pezzo( Colore colore ){
        
        this.colore = colore;
    
    }
    
    /**
     * Metodo che ritorna il colore del pezzo astratto
     * @return colore - Il colore del pezzo astratto
    */
    public Colore getColore(){
        
        return colore;
    
    }

} // Fine Classe Pezzo