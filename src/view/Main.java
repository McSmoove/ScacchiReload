package view;

/**
 * Classe contenente il main dove viene lanciata l'interfaccia grafica
 * @author Viktor, Michael, Gaetano
*/
public class Main{
    
    /**
     * Main del progetto che inizializza l'interfaccia grafica e la esegue
     * @param args - I parametri aggiuntivi alla esecuzione del programma
    */
    public static void main( String args[] ){
        
       InterfacciaGrafica interfaccia = new InterfacciaGrafica(); // Inizializzo l'interfaccia grafica
       interfaccia.start(); // Esegue quest'ultima
    
    } // Fine main

} // Fine Classe Main