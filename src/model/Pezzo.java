package model;

/**
 * Classe astratta che definisce alcune funzioni comuni a tutti i Pezzi della schacchiera
 * Tutte le classi dei colori definiscono oggetti che vengono presi in esame durante i controlli svolti dal package Contoller
 * @author Viktor Teren VR379996, Michael Andronic VR370063, Gaetano Cavaler VR379845
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