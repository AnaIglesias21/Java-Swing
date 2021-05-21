
package Aplicacion;

import static Aplicacion.AplicacionCuentaBancaria.miIcono;
import javax.swing.*;

/**
 * Clase que crea el frame para la consulta de datos de una cuenta 
 * @author Ana I. Iglesias Martínez
 */
public class MarcoConsulta extends JFrame{
    
    public MarcoConsulta(){
        //obtenemos la resolución e la pantalla par posicionar le marco en el centro
        setSize(Recursos.dimensionPantalla()[0]/2,Recursos.dimensionPantalla()[1]/2+50);               
        //ahora la posicionamos dividiendo entre cuatro la altura y la anchura de nuestra pantalla
        setLocation(Recursos.dimensionPantalla()[0]/4,Recursos.dimensionPantalla()[1]/4);
        //Le ponemos un titulo
        setTitle(" BANCO ESTRELLA              Consulta Cuenta");
         //aplicamos la imagen a nuestro marco para personalizar el icono de la ventana
        setIconImage(miIcono);
        setResizable(false);//hacemos que no se pueda maximizar el marco
        //Instanciamos la clase LaminaConsulta para creal la lámina
        LaminaConsulta miLamina = new LaminaConsulta();
        miLamina.updateUI();
        //añadimos la lámina al marco
        add(miLamina);
    }
 
}
