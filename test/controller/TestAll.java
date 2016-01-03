package controller;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

/**
 * Test di tutte le classi dento il package Controller
 * @author Viktor, Michael, Gaetano
*/
public class TestAll{
    
    /**
     * Langio tutti i test di tutte le classi dentro il package Controller
     * @param args - Argomenti da linea di commando
    */
    public static void main( String[] args ){
        
        Class[] listaClassi = new Class[ 2 ];
        listaClassi[ 0 ] = GestoreMovimentiTest.class;
        listaClassi[ 1 ] = GestoreTBTest.class;
        
        for( Class lista : listaClassi ){
            
            Result result = JUnitCore.runClasses( lista );
            
            if (result.getFailureCount() != 0){
                
                System.out.println("Numero Di Test Della Classe " + lista.getSimpleName() + " Che Non Sono Passati: " + result.getFailureCount());
            
            } else {
                
                System.out.println("Tutti I Test Della Classe " + lista.getSimpleName() + " Sono Passati !!!");
            
            }
        
        }
    
    }

} // Fine Classe TestAll Per Il Package Controller