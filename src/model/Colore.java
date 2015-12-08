package model;

/**
 * Classe astratta del colore. Contiene alcuni metodi di base comunni in entrambi i colori
 * @author Viktor, Michael, Gaetano
 */

public abstract class Colore{
    
    private int colore; // Non Puo Essere Di Tipo Colore ???
    public static final int Bianco = 1;
    public static final int Nero = -1;
    
    @Override
    public boolean equals( Object o ){
        
        Colore c;
        
        if( o instanceof Colore ){
            
            c = ( Colore ) o;
            
            if( c.getColore() == this.colore ){ // If Rindondante
                
                return true;
            
            } else {
                
                return false;
            
            }
        
        }
        
        return false;
    
    }

    @Override
    public int hashCode(){ // La Equals Vuole La hashCode()
        
        return this.colore;
    
    }
    
    public int getColore(){
        
        return colore;
    
    }

}