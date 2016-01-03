package view;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

/**
 * Test di tutte le classi dento il package View
 * @author Viktor, Michael, Gaetano
*/
public class TestAll{
    
    /**
     * Langio tutti i test di tutte le classi dentro il package View
     * @param args - Argomenti da linea di commando
    */
    public static void main( String[] args ){
        
        Class[] listaClassi = new Class[ 3 ];
        listaClassi[ 0 ] = InterfacciaGraficaTest.class;
        listaClassi[ 1 ] = MainTest.class;
        listaClassi[ 2 ] = MergeIconTest.class;
        
        for( Class lista : listaClassi ){
            
            Result result = JUnitCore.runClasses( lista );
            
            if (result.getFailureCount() != 0){
                
                System.out.println("Numero Di Test Della Classe " + lista.getSimpleName() + " Che Non Sono Passati: " + result.getFailureCount());
            
            } else {
                
                System.out.println("Tutti I Test Della Classe " + lista.getSimpleName() + " Sono Passati !!!");
            
            }
        
        }
    
    }

} // Fine Classe TestAll