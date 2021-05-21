
package Aplicacion;

import javax.swing.*;
import static Aplicacion.AplicacionCuentaBancaria.miIcono;

/**
 * Clase que crea el frame para el listdo de cuntas
 * @author Ana I. Iglesias Martínez
 */

public class MarcoListarCuentas extends JFrame{
 
    public MarcoListarCuentas(){
        
        setSize(Recursos.dimensionPantalla()[0]/2,Recursos.dimensionPantalla()[1]/2);
        //ahora la posicionamos dividiendo entre cuatro la altura y la anchura de nuestra pantalla
        setLocation(Recursos.dimensionPantalla()[0]/4,Recursos.dimensionPantalla()[1]/4);
        //Le ponemos un titulo
        setTitle(" BANCO ESTRELLA ");
        //aplicamos la imagen a nuestro marco
        setIconImage(miIcono);
        setResizable(false);//hacemos que no se pueda maximizar el marco
        //creamos la lámina para el listado de todas las cuentas
        LaminaListarCuentas miLaminaLista = new LaminaListarCuentas();
        //la añadimos al marco
        add(miLaminaLista);
       
    }
}
