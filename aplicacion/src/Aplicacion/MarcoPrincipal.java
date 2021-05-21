
package Aplicacion;

import static Aplicacion.AplicacionCuentaBancaria.miIcono;
import java.awt.*;
import javax.swing.*;

/**
 * clase para crar el frame contenedor de la intefaz de usuario, lo crearemos 
   centrado según la resolución de la patalla donde se ejecute la aplicación
 * @author Ana I. Iglesias Martínez
 */

class MarcoPrincipal extends JFrame{
    
    //Constructor del frame
    public MarcoPrincipal(){
        
        /*Para hacer que salga centrado el marco debemos crearlo dándole la 
          mitad de la altura de la pantalla (dato obtenido del método 
          dimensionPantalla de la clase abstracta Recursos) 
          y la mitad de la anchura de la pantalla (obtenido de igual forma que
          la altura
        */
        
        setSize(Recursos.dimensionPantalla()[0]/2,Recursos.dimensionPantalla()[1]/2);
        //ahora la posicionamos dividiendo entre cuatro la altura y la anchura de nuestra pantalla
        setLocation(Recursos.dimensionPantalla()[0]/4,Recursos.dimensionPantalla()[1]/4);
        //Le ponemos un titulo
        setTitle("  BANCO ESTRELLA         Gestion de Cuentas Bancarias");
        //aplicamos la imagen a nuestro marco
        setIconImage(miIcono);
        setVisible(true);//hacemos el marco visible
        setResizable(false);//hacemos que no se pueda maximizar el marco
        
        //instanciamos la lámina
        LaminaPrincipal miLamina = new LaminaPrincipal();
        //agragamos la lámina al marco
        add(miLamina);
        //refrecamos para que se visualice la lámina
        miLamina.updateUI();
    }
    
}
