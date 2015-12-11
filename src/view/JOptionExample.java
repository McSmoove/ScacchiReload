package view;

/**
 *
 * @author Tyrande
 */
import javax.swing.*;
import model.Bianco;
import model.Torre;

public class JOptionExample
{
    public static void main(String... args)
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                   int scelta = JOptionPane.showOptionDialog(null, "Scegli In Cosa Vuoi Promuovere Il Pedone", "Promozione Pedone", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new String[]{"Torre","Cavallo","Alfiere","Regina"},"");
                    
                    if( scelta == 0 ){
                        
                        //s.cambiaPezzo(new Torre(new Bianco()));;
                        System.out.println("torre");
                    } else {
                        
                        System.exit( 0 );
                    
                    }
            }           
        });
    }
}
