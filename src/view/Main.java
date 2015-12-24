package view;

import java.io.IOException;

public class Main{
    
    /**
     * Main Del Progetto Che Fa Inizializza L'Interfaccia Grafica E Fa Eseguire Il Thread
     * @param args - I Parametri Aggiuntivi Alla Esecuzione Del Programma
     * @throws IOException - Se Il Thread Si Blocca Genero Una Eccezione Di IO
    */
    public static void main( String args[] ) throws IOException{
        
       InterfacciaGrafica interfaccia = new InterfacciaGrafica(); // Inizializzo L'Interfaccia Grafica
       interfaccia.start(); // Inizio Un Thread Che Esegua L'Interfaccia Grafica
    
    } // Fine main

} // Fine Classe Main