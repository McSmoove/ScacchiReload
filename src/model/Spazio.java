package model;

import view.InterfacciaGrafica;

/**
 * La classe spazio  essenziale per sapere quale pezzo sta occupando una 
 * casella di gioco o se la casella è libera
 * @author Viktor, Michael, Gaetano
*/
public class Spazio{ // Cella Della Scacchiera Caratterizzata Dalla Presenza O Non Di Una Pedina E Del Suo Tipo
    
    private boolean occupato;
    private Pezzo occupante;
    private int x, y;
    
    public Spazio(){
        
        occupato = false;
        occupante = null;
        x = -1;
        y = -1;
    
    }
    
    public Spazio( int x, int y ){
        
        occupato = false;
        this.x = x;
        this.y = y;
    
    }
    
    public Spazio( int x, int y, Pezzo p ){
        
        occupato = true;
        occupante = p;
        this.x = x;
        this.y = y;
        
    }
    
    public Spazio( Spazio s ){
        
        occupato = s.eOccupato();
        occupante = s.getOccupante();
        x = s.getX();
        y = s.getY();
    
    }
    
    public void cambiaPezzo(Pezzo p){
        occupante=p;
        if(occupante==null)
            occupato=false;
        else
            occupato=true;
    }
    
    
    public boolean eOccupato(){
        
        return occupato;
    
    }
    
    public Pezzo getOccupante(){
        
        return occupante;
    
    }
   
    
    public void setOccupato( boolean b ){
        
        occupato = b;
    
    }
    
    public int getX(){
        
        return x;
    
    }
    
    public int getY(){
        
        return y;
    
    }
    
    public void setX(int x){
        
        this.x = x;
    
    }
    
    public void setY(int y){
        
        this.y = y;
    
    }
    /*
    public void distruggi( InterfacciaGrafica ig ){
        
        occupato = false;
        occupante = null;
        ig.aggiungiPezzoMorto( occupante );
    
    }*/
    
    public void inizializzaSpazio( Pezzo p, int x, int y ){
        
        occupato = true;
        occupante = p;
        this.x = x;
        this.y = y;
    
    }
    
    public void inizializzaSpazio( int x, int y ){
        
        occupato = false;
        this.x = x;
        this.y = y;
    
    }
    
    public void toStringTipo(){
        Pezzo p=this.occupante;
        if(p instanceof Torre)
            System.err.println("TORRE");
        if(p instanceof Pedone)
            System.err.println("PEDONE");
        if(p instanceof Regina)
            System.err.println("REGINA");
        if(p instanceof Alfiere)
            System.err.println("ALFIERE");
        if(p instanceof Cavallo)
            System.err.println("CAVALLO");
    }

}