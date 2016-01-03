package model;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

/**
 * Test di tutte le classi dento il package Model
 * @author Viktor, Michael, Gaetano
*/
public class TestAll{
    
    /**
     * Langio tutti i test di tutte le classi dentro il package Model
     * @param args - Argomenti da linea di commando
    */
    public static void main( String[] args ){
        
        Class[] listaClassi = new Class[ 11 ];
        
        listaClassi[ 0 ] = AlfiereTest.class;
        listaClassi[ 1 ] = BiancoTest.class;
        listaClassi[ 2 ] = CavalloTest.class;
        listaClassi[ 3 ] = ColoreTest.class;
        listaClassi[ 4 ] = NeroTest.class;
        listaClassi[ 5 ] = PedoneTest.class;
        listaClassi[ 6 ] = PezzoTest.class;
        listaClassi[ 7 ] = ReTest.class;
        listaClassi[ 8 ] = ReginaTest.class;
        listaClassi[ 9 ] = SpazioTest.class;
        listaClassi[ 10 ] = TorreTest.class;
        
        for( Class lista : listaClassi ){
            
            Result result = JUnitCore.runClasses( lista );
            
            if (result.getFailureCount() != 0){
                
                System.out.println("Numero Di Test Della Classe " + lista.getSimpleName() + " Che Non Sono Passati: " + result.getFailureCount());
            
            } else {
                
                System.out.println("Tutti I Test Della Classe " + lista.getSimpleName() + " Sono Passati !!!");
            
            }
        
        }
    
    }

} // Fine Classe TestAll Per Il Package Model