
package Aplicacion;

import javax.swing.*;
import static Aplicacion.AplicacionCuentaBancaria.miIcono;

/**
 * Clase que crea el frame para la creación de una nueva cuenta Bancaria
 * @author Ana I. Iglesias Martínez
 */

class MarcoAbrirCuenta extends JFrame{
    
    public MarcoAbrirCuenta(){
        //obtenemos la resolución e la pantalla par posicionar le marco en el centro
        setSize(Recursos.dimensionPantalla()[0]/2,Recursos.dimensionPantalla()[1]/2);
        //ahora la posicionamos dividiendo entre cuatro la altura y la anchura de nuestra pantalla
        setLocation(Recursos.dimensionPantalla()[0]/4,Recursos.dimensionPantalla()[1]/4);
        //Le ponemos un titulo
        setTitle(" BANCO ESTRELLA              Creando cuenta nueva");
        //aplicamos la imagen a nuestro marco para personalizar el icono de la ventana
        setIconImage(miIcono);
        setResizable(false);//hacemos que no se pueda maximizar el marco
        //creamos la lámina para introducir los datos 
        LaminaAbrirCuenta miLaminaCuenta = new LaminaAbrirCuenta();
        
        add(miLaminaCuenta);
    }

}
