
package Aplicacion;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.*;
import javax.swing.border.*;

/**
 *Clase para la creación de la lámina para visualizar los datos de una cuenta
 * 
 * @author Ana I. Iglesias Martínez
 * 
 */
public class LaminaConsultar extends JPanel{
    
    private JLabel tipo, saldo, fechaApertura, intRemunera, intComision, intDesc, desc;
    private TitledBorder tituloBorde = new TitledBorder(new EtchedBorder(),"Cuenta",
               TitledBorder.DEFAULT_JUSTIFICATION,
               TitledBorder.DEFAULT_POSITION,
               new Font(Font.DIALOG,Font.BOLD,16),
               Color.ORANGE);
    
    public LaminaConsultar(String tipoDeCuenta,CuentaBancaria cuenta){
        
        tituloBorde.setTitleColor(Color.ORANGE);
        
        switch (tipoDeCuenta){
            
            case "Ahorro":
                
                setLayout(new GridLayout(4,0));  
                setBorder(javax.swing.BorderFactory.createCompoundBorder(new EmptyBorder(10,10,0,10), tituloBorde));
                CuentaAhorro miCuenta= (CuentaAhorro)cuenta;
                tipo = new JLabel("Tipo cuenta:                         "+tipoDeCuenta);
                saldo = new JLabel("saldo:                                     "+miCuenta.dameSaldo()+" €");
                fechaApertura = new JLabel("Fecha apertura:                   "+miCuenta.damefechaApertura());
                intRemunera= new JLabel("Interés de remuneración: "+miCuenta.dameInteres()+ " %");
                
                add(tipo);
               
                add(saldo);
             
                add(fechaApertura);
              
                add(intRemunera);
             
                break;
                
            case "Corriente Personal":
                CuentaPersonal miCuentaPersonal = (CuentaPersonal)cuenta;
                setLayout(new GridLayout(4,0));
                setBorder(javax.swing.BorderFactory.createCompoundBorder(new EmptyBorder(10,10,0,10), tituloBorde));
                
                tipo = new JLabel("Tipo cuenta:            "+tipoDeCuenta);
              
                saldo = new JLabel("saldo:                        "+miCuentaPersonal.dameSaldo()+" €");
             
                fechaApertura = new JLabel("Fecha apertura:      "+miCuentaPersonal.damefechaApertura());
               
                intComision = new JLabel("Interés comisión:   "+miCuentaPersonal.dameComision()+" %");
                
                add(tipo);
               
                add(saldo);
              
                add(fechaApertura);
                
                add(intComision);
               
                break;
                
            case "Corriente Empresa":
                CuentaEmpresa miCuentaEmpresa = (CuentaEmpresa)cuenta;
                setLayout(new GridLayout(5,0));
                setBorder(javax.swing.BorderFactory.createCompoundBorder(new EmptyBorder(10,10,0,10), tituloBorde));
                
                tipo = new JLabel("Tipo cuenta:                   "+tipoDeCuenta);
               
                saldo = new JLabel("saldo:                               "+miCuentaEmpresa.dameSaldo()+" €");
               
                fechaApertura = new JLabel("Fecha apertura:             "+miCuentaEmpresa.damefechaApertura());
          
                intDesc = new JLabel("Interés descubierto:    "+miCuentaEmpresa.dameInteresDescubierto()+" %");
             
                desc = new JLabel("Cantidad descubierto: "+miCuentaEmpresa.dameDescubierto()+" €");
               
                add(tipo);
                
                add(saldo);
               
                add(fechaApertura);
               
                add(intDesc);
                
                add(desc);
               
                break;    
                
        }
      
    }
}
