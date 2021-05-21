
package Aplicacion;

import java.awt.*;
import java.util.Enumeration;
import javax.swing.*;
import javax.swing.border.*;

/**
 * Clase para la creación de la lámina que mostrará la entidades autorizadas
 * para efectuar los pagos domiciliados
 * 
 * @author Ana I. Iglesias Martínez
 */
public class LaminaEntidades extends JPanel{
    
    private TitledBorder tituloBorde =  new TitledBorder(new EtchedBorder(),"Pagos domiciliados",
               TitledBorder.DEFAULT_JUSTIFICATION,
               TitledBorder.DEFAULT_POSITION,
               new Font(Font.DIALOG,Font.BOLD,16),
               Color.ORANGE);
    private int filas;//recoge la cantidad de entidades de una cuenta concreta
    private Enumeration e;//nos permite obtener los elemsntos de una Hashtable
    private JLabel entidad;
        
    public LaminaEntidades(String tipoDeCuenta,CuentaBancaria cuenta){
        
        //definimos un tipo de letara y tamaño para el titulo de las entidades
        tituloBorde.setTitleFont(new Font(Font.DIALOG,Font.BOLD,16));
        
        switch (tipoDeCuenta){
            
            case "Corriente Personal":
                
                CuentaPersonal miCuentaPersonal = (CuentaPersonal)cuenta;
                //recogemos el número de entidades asociadas a la cuenta para crear ela lámina 
                filas = miCuentaPersonal.dameEntidad().size();
                if (filas<4)
                  setLayout(new GridLayout(1,3));
                else if (filas>3 & filas<7)
                  setLayout(new GridLayout(2,3)); 
                else
                  setLayout(new GridLayout(3,3)); 
                
                  setBorder(javax.swing.BorderFactory.createCompoundBorder(new EmptyBorder(10,10,0,10),
                        tituloBorde));
                  
                  e = miCuentaPersonal.dameEntidad().elements();
        
                  while (e.hasMoreElements()){
                     entidad = new JLabel(e.nextElement().toString());
                     add(entidad);
                  }
                
                break;    
                
             case "Corriente Empresa":
                 
                CuentaEmpresa miCuentaEmpresa = (CuentaEmpresa)cuenta;
                //recogemos el número de entidades asociadas a la cuenta para crear ela lámina 
                filas = miCuentaEmpresa.dameEntidad().size();
                if (filas<4)
                  setLayout(new GridLayout(1,3));
                else if (filas>3 & filas<7)
                  setLayout(new GridLayout(2,3)); 
                else
                  setLayout(new GridLayout(3,3)); 
                
                setBorder(javax.swing.BorderFactory.createCompoundBorder(new EmptyBorder(10,10,0,10), 
                        tituloBorde));
                
                e = miCuentaEmpresa.dameEntidad().elements();
        
                while (e.hasMoreElements()){
                   entidad = new JLabel(e.nextElement().toString());
                   add(entidad);
                }
                
                break;
        }
    }
}
