
package Aplicacion;

import java.awt.*;
import java.util.*;
import javax.swing.*;

/**
 * Clase con el método main para arrancar la aplicación
 * 
 * @author Ana I. Iglesias Martínez
 */
public class AplicacionCuentaBancaria {
    
    public static ArrayList <CuentaBancaria> listaCuentas;
    public static Hashtable<String,String> misEntidades;
    //creamos un objeto image para cambiar el icono de la pantalla
    public static Image miIcono;
    public static boolean generadas;
    
    public static void main(String[] args) {
        
        listaCuentas = new ArrayList<>();
        misEntidades = new Hashtable<String,String>();
        miIcono = Toolkit.getDefaultToolkit().getImage("star.gif");
        
        // Creo el objeto de MarcoPrincipal
        MarcoPrincipal miMarco = new MarcoPrincipal();
        //si se cierra este frame finaliza la aplicación
        miMarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
    }
}
