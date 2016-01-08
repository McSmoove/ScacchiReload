package view;

/**
 * Classe principale del programma
 * @author Viktor Teren VR379996, Michael Andronic VR370063, Gaetano Cavaler VR379845
*/
public class Main{
    
    /**
     * Main del programma, quando viene eseguito esso lancia l'InterfacciaGrafica
     * @param args - Eventuali parametri da linea di commando
     * @throws Exception - Eccezione in caso di fallita esecuzione del programma
    */
    public static void main( String args[] ) throws Exception{
        
       InterfacciaGrafica interfaccia = new InterfacciaGrafica();
       interfaccia.start();
    
    } // Fine main

} // Fine Classe Main