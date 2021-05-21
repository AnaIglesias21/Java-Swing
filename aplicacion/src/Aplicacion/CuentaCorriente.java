
package Aplicacion;
import java.util.Hashtable;

/**
 * Clase que permite crear objetos de tipo CuentaCorriente
 * @author Ana I. Iglesias Martínez
 */
public class CuentaCorriente extends CuentaBancaria{
    /*campos de clase. todads las cuentas corrientes tendrán una lista
      de entidades autorizadas para domiciliar los recios*/
      
    private Hashtable<String, String> entidades = new Hashtable<String, String>();
    
    public CuentaCorriente(String cuenta, Persona titular, double saldo, Hashtable seleccion) {
        super(cuenta, titular, saldo);
        if (seleccion.size()==0){
            entidades.put("10", "Luz");
            entidades.put("11", "Agua");
            entidades.put("12", "Teléfono");
        }else    
           entidades = seleccion;
        
    }
    
    public void estableceEntidad(String codigo, String entidad){
        entidades.put(codigo,entidad);
    }
    
    public Hashtable dameEntidad(){
        return entidades;
    }
}
