
package Aplicacion;

/**
 * Clase que permite crear Objetos de tipo CuentaAhorro
 * @author Ana I. Iglesias Martínez
 */
public class CuentaAhorro extends CuentaBancaria{
    private final double INTERES = 0.5;
    private double interes;
    
    //Métodos constructores
    
    public CuentaAhorro(String cuenta, Persona titular, double saldo){
        
       super(cuenta,titular,saldo);
       interes = INTERES;
    }
    public CuentaAhorro(String cuenta, Persona titular, double saldo, double interes){
        super(cuenta,titular,saldo);
        this.interes = interes;
    }
    
    
    //Métodos setter
    
    public void estableceInteres(){
        this.interes = Double.parseDouble(Recursos.pedirDatos("interés: "));
    }
    
    //Método getter
    
    public double dameInteres(){
        return interes;
    }
    
}
