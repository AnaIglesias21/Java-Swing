
package Aplicacion;

import static Aplicacion.Recursos.*;
import javax.swing.JOptionPane;

/**
 *
 * @author Ana I. Iglesias Martínez
 * Clase para crear objetos de tipo CuenaBancaria
 */
public class CuentaBancaria {
    //campos de clase
    private String numeroCuenta;
    private final Persona titular;
    private String fechaApertura;
    private double saldo;
    
    /**
     * Método constructor para crear un objeto de tipo CuentaBancaria
     * @param cuenta
     * @param titular
     * @param saldo 
     */
    
    public CuentaBancaria(String cuenta, Persona titular, double saldo){
        
        //se comprueba que el número de cuenta sea correcto
        while (!comprobarDigito(Integer.parseInt(cuenta.substring(0, 4)),
            Integer.parseInt(cuenta.substring(4, 8)),Integer.parseInt(cuenta.substring(8, 10)),
            Long.parseLong(cuenta.substring(10, 20)))){
            JOptionPane.showMessageDialog(null,"Número de cuenta erróneo.", "Cuenta", JOptionPane.ERROR_MESSAGE);
            cuenta = pedirDatos("cuenta: ");
        }    
        
        numeroCuenta = cuenta;
        this.titular = titular;
        this.saldo = saldo;
        fechaApertura=fechaSistema();
                   
    }
    
    //Metodos setter para asignar/cambiar los valores de los campos de clase
    public void estableceCuenta(String cuenta){
        this.numeroCuenta = cuenta;
    }
    //Método para asignar el nombre del titular
    public void estableceNombre(Persona titular){
        titular.estableceNombre();
        titular.estableceapellido1();
        titular.estableceapellido2();
        titular.estableceFecha();
    }
    /**
     * Método setter para asignar el saldo
     */
    public void estableceSaldo(Double saldo){
        
        this.saldo = saldo;
        
    }
    
    /**
     * Método getter que devuelve el número de cuenta
     * @return 
     */
    public String dameCuenta(){
        return numeroCuenta;
    }
    /**
     * Método getter para consultar el nombre y apellidos del titular de la cuenta
     * @return 
     */
    public Persona dameTitular(){
        return titular;
        
    }
    /**
     * Método getter para consultar la fecha de nacimiento del titular de la cuenta
     * @return 
     */
    
    public String damefechaApertura(){ 
        return fechaApertura;
    }
    /**
     * Método getter para consultar el saldo de la cuenta
     * @return 
     */
    public double dameSaldo(){
        return saldo;
    }
    
    /**
     * Método estático para verificar que el dígito de control del número de cuenta
     * @param nEntidad
     * @param nOficina
     * @param dControl
     * @param nCuenta
     * @return 
     */
   
    public static boolean comprobarDigito(int nEntidad, int nOficina, int dControl, long nCuenta){
        boolean correcto;
        int sumaEntidad = 0,sumaOficina = 0, restoEntidad=nEntidad, restoOficina=nOficina,
               sumaCuenta = 0, resultado, resultado1;
        long restoCuenta = nCuenta;
        
        //Calculamos el primer número del dígito de control según el algoritmo para ello
        for (int i=0;i<4;i++){
            switch (i){
                case 0:
                    sumaEntidad += restoEntidad/1000*4;
                    sumaOficina += restoOficina/1000*9;
                    restoEntidad %= 1000;
                    restoOficina %= 1000;
                    break;
                case 1:
                    sumaEntidad += restoEntidad/100*8;
                    sumaOficina += restoOficina/100*7;
                    restoEntidad %= 100;
                    restoOficina %= 100;
                    break;
                case 2:
                    sumaEntidad += restoEntidad/10*5;
                    sumaOficina += restoOficina/10*3;
                    restoEntidad %= 10;
                    restoOficina %= 10;
                    break;
                case 3:
                    sumaEntidad += restoEntidad/1*10;
                    sumaOficina += restoOficina/1*6;
                    break;
            }        
        }
        
        if ((11-(sumaEntidad+sumaOficina)%11) == 10){
            resultado = 1;
            
        }else if(11-(sumaEntidad+sumaOficina)%11== 11){
            resultado = 0;
                    
        }else{
            resultado = 11 - (sumaEntidad+sumaOficina)%11;
        }
        
        //Calculamos el segundo numero del dígito de control según el algoritmo
        
        for (int i=0; i<10;i++){
            switch (i){
                case 0:
                    sumaCuenta += restoCuenta/1000000000;
                    restoCuenta %= 1000000000;
                    break;
                case 1:
                    sumaCuenta += restoCuenta/100000000*2;
                    restoCuenta %= 100000000;
                    break;  
                case 2:
                    sumaCuenta += restoCuenta/10000000*4;
                    restoCuenta %= 10000000;
                    break;    
                case 3:
                    sumaCuenta += restoCuenta/1000000*8;
                    restoCuenta %= 1000000;
                    break; 
                case 4:                  
                    sumaCuenta += restoCuenta/100000*5;
                    restoCuenta %= 100000;
                    break; 
                case 5:
                    sumaCuenta += restoCuenta/10000*10;
                    restoCuenta %= 10000;
                    break;
                case 6: 
                    sumaCuenta += restoCuenta/1000*9;
                    restoCuenta %= 1000;
                    break;   
                case 7:
                    sumaCuenta += restoCuenta/100*7;
                    restoCuenta %= 100;
                    break;
                case 8:
                    sumaCuenta += restoCuenta/10*3;
                    restoCuenta %= 10;
                    break;    
                case 9:
                    sumaCuenta += restoCuenta/1*6;
                    break;    
            }
        }
       
        if ((11-sumaCuenta%11) == 10){
            resultado1 = 1;
            
        }else if(11-sumaCuenta%11 == 11){
            resultado1 = 0;
                    
        }else{
            resultado1 = 11 - sumaCuenta%11;
        }
        
        //Comparamos el resultado obtenido con el digito de Control introducido
        correcto = dControl == resultado*10 + resultado1;
        
        return correcto;
    }
}


