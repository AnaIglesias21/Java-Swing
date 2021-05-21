
package Aplicacion;

import java.util.Hashtable;

/**
 * Clase que permite crear objetos de tipo CuentaPersonal
 * 
 * @author Ana I. Iglesias Martínez
 */
public class CuentaPersonal extends CuentaCorriente{
    
    private final double COMISION = 0.75;
    private double comision;
    
    //Métodos constructores
    public CuentaPersonal(String cuenta, Persona titular, double saldo, Hashtable seleccion){
        super(cuenta,titular,saldo, seleccion);
        this.comision = COMISION;
    }
    
    public CuentaPersonal(String cuenta, Persona titular, double saldo, double comision, Hashtable seleccion){
        super(cuenta,titular,saldo, seleccion);
        this.comision = comision;
    }
    
    //Métodos setter
    
    public void estableceComision(){
        this.comision = Double.parseDouble(Recursos.pedirDatos("comisión: "));
    }
    
    //Método getter
    
    public double dameComision(){
        return comision;
    }
}
