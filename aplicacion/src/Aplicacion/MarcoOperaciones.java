
package Aplicacion;

import javax.swing.*;
import static Aplicacion.AplicacionCuentaBancaria.miIcono;

/**
 * Clase para crear una ventana que permita consultar los datos de una cuenta
 * @author Ana I. Iglesias Martínez
 */
public class MarcoOperaciones extends JFrame{
    
    public MarcoOperaciones(String titulo){
        
        setVisible(true);
        setTitle(titulo);
        setBounds(450,200,500,250);
        
        //aplicamos la imagen a nuestro marco
        setIconImage(miIcono);
        setResizable(false);//hacemos que no se pueda maximizar el marco
        LaminaOperaciones miLamina = new LaminaOperaciones(titulo);
        
        add(miLamina);
        miLamina.updateUI();
    }
    
    public String dameTitulo(){
        return getTitle();
    }
    
}
