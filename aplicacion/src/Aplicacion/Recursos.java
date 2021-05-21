
package Aplicacion;

import java.awt.*;
import java.text.*;
import java.util.*;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import static Aplicacion.AplicacionCuentaBancaria.listaCuentas;


/**
 * clase con métodos abstractos para difeentes utilidades
 * @author Ana I. Iglesias Martínez
 */
public abstract class Recursos {
    
    public static String pedirDatos(String datos){
        //Scanner entrada = new Scanner(System.in);
        String dato="";
        String interesExpr = "^\\d{0,2}\\.{0,1}\\d{0,2}";
        String cantidadExpr = "^\\d{1,7}\\.{0,1}\\d{0,2}";
        String fechaExpr = "^\\d{0,2}\\/\\d{0,1}\\/\\d{1,2}\\d{0|9}\\d{0,9}\\d{0,9}";
        
        do{
            try{
              
               dato = JOptionPane.showInputDialog(null,datos,"Datos",JOptionPane.QUESTION_MESSAGE);
               if (dato.isEmpty())
                  JOptionPane.showMessageDialog(null,"Debes introducir " + datos); 
               else{
               if (datos.equals("saldo: ")){
                   if(!Pattern.matches(cantidadExpr, dato)){
                      JOptionPane.showMessageDialog(null, "Saldo erróneo\n"
                              + "El formato es '99999999.99', '99999999'\n",
                               "Error datos", JOptionPane.ERROR_MESSAGE);
                      dato=""; 
                   }
               }
               
               if (datos.equals("cantidad: ")){
                   if(!Pattern.matches(cantidadExpr, dato)){
                      JOptionPane.showMessageDialog(null, "Cantidad errónea\n"
                              + "El formato es '99999999.99', '99999999'\n",
                               "Error datos", JOptionPane.ERROR_MESSAGE);
                      dato=""; 
                   }
               }
               
               if (datos.equals("interés remuneración: ")){
                  
                   if (!Pattern.matches(interesExpr, dato)){
                      JOptionPane.showMessageDialog(null, "Interés remuneración erróneo\n"
                              + "El formato es '99.99', '99' , '.99'\n"
                              + "Si se introduce '0' o cancelar se aplicará el 0.5%", "Error datos", JOptionPane.ERROR_MESSAGE);
                      dato="0";
                   }
                   
               }    
               if (datos.equals("comisión mantenimiento: ")){
                   if (!Pattern.matches(interesExpr, dato)){
                      JOptionPane.showMessageDialog(null, "Comisión de mantenimiento errónea\n"
                              + "El formato es '99.99', '99' , '.99'\n"
                              + "Si se introduce '0' o cancelar se aplicará el 1.2%", "Error datos", JOptionPane.ERROR_MESSAGE);
                      dato="";
                   }
               }   
              
               if (datos.equals("fecha nacimiento (dd/mm/aaaa): ")){
                   if (!Pattern.matches(fechaExpr, dato)){
                      dato = "";
                      JOptionPane.showMessageDialog(null, "Fecha errónea", "Error datos", JOptionPane.ERROR_MESSAGE);
                   }
               }
               }
            }catch (Exception e){
                
                switch (datos) {
                    case "saldo: ":
                        dato="0";
                        break;
                    case "cantidad: ":
                        dato="0";
                        break;
                    case "nombre: ":
                    case "primer apellido: ":
                    case "segundo apellido: ":
                    case "fecha nacimiento (dd/mm/aaaa): ":
                        dato = "";
                        break;
                    case "interés remuneración: ":
                        dato="0.0";
                        JOptionPane.showMessageDialog(null, "EL interés aplicado será 0.5%", "Interés asignado", JOptionPane.INFORMATION_MESSAGE);
                        break;
                    case "comisión mantenimiento: ":
                        dato="0.0";
                        JOptionPane.showMessageDialog(null, "La comisión de mantenimiento será 1.5%", "Comisión de mantenimiento asignada", JOptionPane.INFORMATION_MESSAGE);
                        break;
                }
                
            }
            
        }while (dato.isEmpty());  
        
        return dato;
    }
    
    //Método estático que omprueba que la fecha introducida es correcta
    public static boolean validarFecha(String fecha){
        
        try {
            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
            formatoFecha.setLenient(false);
            formatoFecha.parse(fecha);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }
    
    //Metodo estático que obtiene la fecha actual
    public static String fechaSistema(){
        Calendar miFecha = new GregorianCalendar();
        
        return miFecha.get(Calendar.DATE)+"/"+(miFecha.get(Calendar.MONTH)+1)+"/"+miFecha.get(Calendar.YEAR);

    }
    
    //método que devuelve el ancho y el alto de la pantalla donde corre la aplicación para poder centrar los frames
    public static int [] dimensionPantalla(){
        //creamos una variable de tipo Toolkit para que nos de la resolucion de la pantalla
        Toolkit miPantalla = Toolkit.getDefaultToolkit();
        //cramos una variable de tipo dimension para recoger el tamaño de la pantalla
        Dimension tamano = miPantalla.getScreenSize();
        //se debe extraer los datos alto y ancho con width y height
        int alturaPantalla = tamano.height;
        int anchoPantalla = tamano.width;
        
        int [] vector = {anchoPantalla,alturaPantalla};
        return vector;
    }
    
    /**
     * Método estático para validar que tanto los intereses como la cantidad a ingresar
     * o a retirar sean correctas
     * @param valor
     * @return 
     */
    public static boolean isDouble(String n) {
		try {
                    //comprobamos que la cantidad introducida es un número y es mayor de cero
	            if (Double.parseDouble(n)>0)
                        return true; 
                    else 
                        return false;
		} catch (Exception nfe) {
			return false;
		}
    }
    //Método que genera cuentas bancarias
    public static void generandoCuentas(){
        Persona persona1 = new Persona("Laura","García","Pacual","15/4/2008");
        Persona persona2 = new Persona("Marco","Suárez","Gutiérrez","20/6/2000");
        Persona persona3 = new Persona("Miguel","Gutiérrez","Cañón","28/10/1980");
        Persona persona4 = new Persona("Soraya","LLana","Iglesias","13/5/2009");
        Persona persona5 = new Persona("Lucía","Peña","Pérez","1/2/1999");
        Persona persona6 = new Persona("Ana","Martínez","Díaz","20/9/1960");
        Persona persona7 = new Persona("Estefanía","López","Carrasco","15/11/1955");
        Persona persona8 = new Persona("Simón","García","Báez","12/7/1990");
        Persona persona9 = new Persona("Adrián","Pascual","Alonso","8/7/1985");
        Persona persona10 = new Persona("Teresa","Arias","Lorenzo","3/3/1992");
        Persona persona11 = new Persona("Sara","López","Rodriguez","19/12/1970");
        Persona persona12 = new Persona("Lara","Rodríguez","Blanco","29/7/1965");
        Persona persona13 = new Persona("José","Gutiérrez","Iglesias","3/3/1983");
        Persona persona14 = new Persona("Daniel","Aguado","Alvarez","27/2/1997");
        Persona persona15 = new Persona("Carolina","Peña","Pérez","13/12/1998");
        Persona persona16 = new Persona("Isabel","Martínez","Díaz","2/9/1960");
        Persona persona17 = new Persona("Bárbara","Requejo","Carrasco","21/11/1995");
        Persona persona18 = new Persona("Lucas","García","Báez","12/8/1993");
        
        Hashtable <String,String> ent1 =  new Hashtable<String,String>();
        ent1.put("10","Luz");
        ent1.put("11","Agua");
        ent1.put("12","Teléfono");
        Hashtable <String,String> ent2 =  new Hashtable<String,String>();
        ent2.put("10","Luz");
        ent2.put("11","Agua");
        ent2.put("12","Teléfono");
        ent2.put("22","Empresa Limpieza");
        ent2.put("21", "Seguridad");
        Hashtable <String,String> ent3 =  new Hashtable<String,String>();
        ent3.put("10","Luz");
        ent3.put("11","Agua");
        ent3.put("21", "Seguridad");
        ent3.put("22","Empresa Limpieza");
        ent3.put("30","Proveedor 1");
        ent3.put("31","Proveedor 2");
        ent3.put("32", "Proveedor 3");
        Hashtable <String,String> ent4 =  new Hashtable<String,String>();
        ent4.put("10","Luz");
        ent4.put("11","Agua");
        ent4.put("12","Teléfono");
        ent4.put("21", "Seguridad");
        Hashtable <String,String> ent5 =  new Hashtable<String,String>();
        ent5.put("10","Luz");
        ent5.put("11","Agua");
        ent5.put("12","Teléfono");
        ent5.put("20","Seguridad local");
        ent5.put("22","Empresa Limpieza");
        ent5.put("30","Proveedor 1");
        ent5.put("31","Proveedor 2");
        
        //Cuentas Ahorro
        CuentaBancaria c1 = new CuentaAhorro("30580957523320885272",persona1,2450,0.4);
        CuentaBancaria c2 = new CuentaAhorro("20678038580448634848",persona8,1500,0.3);
        CuentaBancaria c3 = new CuentaAhorro("20804619446947456913",persona12,450);
        CuentaBancaria c4 = new CuentaAhorro("61313726197773066173",persona18,150,0.35);
        CuentaBancaria c5 = new CuentaAhorro("97675559093866291224",persona14,3000,0.6);
        CuentaBancaria c6 = new CuentaAhorro("28462816116496164258",persona6,2100,0.55);
        CuentaBancaria c18 = new CuentaAhorro("53827853864081242268",persona9,2100,0.55);
        //Cuentas Corriente Personal
        CuentaBancaria c7 = new CuentaPersonal("18927426943739002708",persona3,2450,0.5,ent1);
        CuentaBancaria c8 = new CuentaPersonal("96531021525568015485",persona5,1500,0.3,ent4);
        CuentaBancaria c9 = new CuentaPersonal("04331091212883585404",persona10,450,ent2);
        CuentaBancaria c10 = new CuentaPersonal("73734593963791952533",persona17,150,0.35,ent4);
        CuentaBancaria c11 = new CuentaPersonal("27127774717261278498",persona13,3000,0.6,ent1);
        CuentaBancaria c12 = new CuentaPersonal("26135666130757845802",persona6,2100,0.55,ent2);
        CuentaBancaria c19 = new CuentaPersonal("07845444894141643208",persona15,5980,0.8,ent2);
        //Cuentas Eempresa
        CuentaBancaria c13 = new CuentaEmpresa("81969200215422115167",persona2,2450,0.4,1000,ent5);
        CuentaBancaria c14 = new CuentaEmpresa("00787693168104415142",persona4,1500,0.3,2500,ent3);
        CuentaBancaria c15 = new CuentaEmpresa("97120205456049077300",persona7,450,ent4);
        CuentaBancaria c16 = new CuentaEmpresa("83371404799372910142",persona9,150,0.35,3000,ent5);
        CuentaBancaria c17 = new CuentaEmpresa("27729015938191506246",persona11,3000,0.6,6500,ent3);
        CuentaBancaria c20 = new CuentaEmpresa("55394300911038441698",persona16,3000,0.6,500,ent4);
        
        listaCuentas.add(c1);
        listaCuentas.add(c2);
        listaCuentas.add(c3);
        listaCuentas.add(c4);
        listaCuentas.add(c5);
        listaCuentas.add(c6);
        listaCuentas.add(c7);
        listaCuentas.add(c8);
        listaCuentas.add(c9);
        listaCuentas.add(c10);
        listaCuentas.add(c11);
        listaCuentas.add(c12);
        listaCuentas.add(c13);
        listaCuentas.add(c14);
        listaCuentas.add(c15);
        listaCuentas.add(c16);
        listaCuentas.add(c17);
        listaCuentas.add(c18);
        listaCuentas.add(c19);
        listaCuentas.add(c20);
        
    }
    /**
     * Método estático para localizar una cuenta bancaria
     */
    public static boolean estaCuenta(String numeroCuenta, ArrayList listado){
        
        if (listado.size()>0){
            System.out.println("Entra aquí");
            for (int i=0;i<listado.size();i++){
                
                System.out.println("Estoy en el bucle for");
                
            }
            
        }else{
            return false;
        }
        
        return false;
    }
    
}
