
package Aplicacion;

import java.util.Hashtable;

/**
 *
 * @author Ana I. Iglesias Martínez
 */
public class CuentaEmpresa extends CuentaCorriente{
    
    private final double INTERES_DESCUBIERTO = 1.3;
    private final double DESCUBIERTO = 2000;
    private double interes;
    private double descubierto;
    
    //Constructores
    public CuentaEmpresa(String cuenta, Persona titular, double saldo, Hashtable seleccion){
       super(cuenta,titular,saldo, seleccion);
       interes = INTERES_DESCUBIERTO;
       descubierto = DESCUBIERTO;
    }   
    
    public CuentaEmpresa(String cuenta, Persona titular, double saldo, double interes, double descubierto, Hashtable seleccion){
       super(cuenta,titular,saldo,seleccion);
       this.interes = interes;
       this.descubierto = descubierto;
    }
    
    
    //Métodos setter
    
    public void estableceInteres(){
        this.interes = Double.parseDouble(Recursos.pedirDatos("interés: "));
    }
    
    
    public void estableceDescubierto(Double descubierto){
        this.descubierto = descubierto;
    }
    
    //Método getter
    
    public double dameInteresDescubierto(){
        return interes;
    }
        
    public double dameDescubierto(){
        return descubierto;
    }
}
