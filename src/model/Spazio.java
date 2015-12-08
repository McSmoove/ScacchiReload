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
        occupante=null;
        occupato = false;
        x=-1;
        y=-1;
    }
    
    public Spazio( int x,int y ){
        
        occupato = false;
        this.x = x;
        this.y = y;
    
    }
    
    public Spazio( int x, int y, Pezzo p ){
        
        occupato = true;
        this.x = x;
        this.y = y;
        occupante = p;
    
    }
    
    public Spazio(Spazio s){
        occupato=s.eOccupato();
        occupante=s.getOccupante();
        x=s.getX();
        y=s.getY();
    }
    
    
    public boolean eOccupato(){
        
        return occupato;
    
    }
    
    public Pezzo getOccupante(){
        
        return occupante;
    
    }
    
    public void setOccupante( Pezzo p ){
        
        occupante = p;
    
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
    
    public void distruggi(InterfacciaGrafica ig){
        ig.aggiungiPezzoMorto(occupante);
        occupante=null;
        occupato=false;
    }
    
    public void inizializzaSpazio(Pezzo p,int x,int y){
        occupante=p;
        this.x=x;
        this.y=y;
        occupato=true;
    }
    
    public void inizializzaSpazio(int x,int y){
        this.x=x;
        this.y=y;
        occupato=false;
    }

}