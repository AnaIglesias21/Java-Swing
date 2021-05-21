
package Aplicacion;
import javax.swing.JOptionPane;
import static Aplicacion.Recursos.pedirDatos;
import static Aplicacion.Recursos.validarFecha;

/**
 * Clase que nos permite crea objetos de tipo persona. Contiene los
 * atributos para recoger los datos personales de cualquier persona
 * @author Ana I. Iglesias Martínez
 * 
 */
public class Persona {
    /**
     * 
     */
    //atributos de clase
    private String nombre;
    private String apellido1;
    private String apellido2;
    private String fecha_nac;
    
    //constructores
    public Persona(){
        
        nombre = pedirDatos("Nombre: ");
        apellido1 = pedirDatos("Primer apellido: ");
        apellido2 = pedirDatos("Segundo apellido: ");
        estableceFecha();
        
    }
    
    public Persona(String nombre, String apellido1, String apellido2,String fecha_nac){
        if (nombre.isEmpty()){
          JOptionPane.showMessageDialog(null, "El nombre no puede estar vacío");
          this.nombre=pedirDatos("nombre: ");
        }else 
          this.nombre = nombre;
            
        if (apellido1.isEmpty()){
          JOptionPane.showMessageDialog(null,"El apellido no puede estar vacío", 
                    "Error datos", JOptionPane.ERROR_MESSAGE);
            this.apellido1=pedirDatos("primer apellido: ");
        }else{
            this.apellido1 = apellido1;
        }
        if (apellido2.isEmpty()){
          JOptionPane.showMessageDialog(null,"El apellido no puede estar vacío",
                    "Error datos", JOptionPane.ERROR_MESSAGE);
          this.apellido2=pedirDatos("segundo apellido: ");
        }else{
           this.apellido2 = apellido2;
        }   
        while(!validarFecha(fecha_nac)){
            JOptionPane.showMessageDialog(null, "La fecha es errónea", 
                    "Error datos", JOptionPane.ERROR_MESSAGE);
            estableceFecha();
        }
        this.fecha_nac = fecha_nac;
            
        
    }
    
    //metoos getter
    public String dameNombre(){
        return  nombre;
    }
    
    public String dameApellido1(){
        return apellido1;
    }
    
    public String dameApellido2(){
        return apellido2;
    }
    public String dameFechaNac(){
        return fecha_nac;
    }
    
    //metodos getter
    
    public void estableceNombre(){
        
        nombre=pedirDatos("nombre: ");
    }
    
    public void estableceapellido1(){
       
       apellido1=pedirDatos("primer Apellido: "); 
        
    }
    
    public void estableceapellido2(){
       
       apellido2=pedirDatos("segundo Apellido: "); 
        
    }
    
    public void estableceFecha(){
        String fecha;
        do{
          fecha = Recursos.pedirDatos("fecha nacimiento (dd/mm/aaaa): "); 
          
          if (!validarFecha(fecha))
              JOptionPane.showMessageDialog(null, "La fecha es errónea", 
                      "Error datos", JOptionPane.ERROR_MESSAGE);
        }while(!validarFecha(fecha));
        
        fecha_nac=fecha;
    }
    
}
