
package Aplicacion;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;


/**
 * Clase para visualizar los datos del titular de una cuenta concreta
 * @author Ana I. Iglesias Martínez
 */
public class LaminaConsultaTitular extends JPanel{
    
    private JLabel nombre,fechaNac;
    
    public LaminaConsultaTitular(){
       
        setLayout(new GridLayout(2,0)); 
    }
    
    public LaminaConsultaTitular(Persona titular){
        
        setLayout(new GridLayout(2,0));
        
        setBorder(javax.swing.BorderFactory.createCompoundBorder(new EmptyBorder(10,10,0,10), 
               new TitledBorder(new EtchedBorder(),"Titular",
               TitledBorder.DEFAULT_JUSTIFICATION,
               TitledBorder.DEFAULT_POSITION,
               new Font(Font.DIALOG,Font.BOLD,16),
               Color.ORANGE)));
        
        nombre = new JLabel("Nombre:                    "+titular.dameNombre()+ " "+titular.dameApellido1()+ " " + titular.dameApellido2());
        fechaNac = new JLabel("Fecha Nacimiento:  "+titular.dameFechaNac());
       
        add(nombre);
        add(fechaNac);
      
    }
}
