
package Aplicacion;

import java.awt.Toolkit;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;
/**
 * Clase que controla la entrada de datos en cuanto a números, letras o longitudes
 * 
 * @author Ana I. Iglesias Martínez
 */
public class ControlarEntradas {
    
    //Método para dejar que solo se introduzcan letras
    public void soloLetras(JTextField campo){
        //ponemos el JTextfield a la escucha para controlar lo que se introduce
        campo.addKeyListener(new KeyAdapter(){
        /*clase interna anónima que evitará la introduccin de números en campos donde
          solo debe haber letras*/  
            @Override
            public void keyTyped(KeyEvent e) {
                //variable char para recoger cada caracter tecleado
                char c = e.getKeyChar();
                if(Character.isDigit(c)){
                    Toolkit.getDefaultToolkit().beep();
                    e.consume();//si es un dígito se borra del JTextField
                    JOptionPane.showMessageDialog(null, "Solo se pueden introducir letras", "Error entrada de datos", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
    }
    
    //Método para dejar que solo se introduzcan números
    public void soloNumeros(JTextField campo){
        //ponemos el JTextfield a la escucha para controlar lo que se introduce
        campo.addKeyListener(new KeyAdapter(){
       /* clase interna anónima donde se evitará la introdcucción de letras u otros caracteres
          en campos donde solo debe haber números*/
            @Override
            public void keyTyped(KeyEvent e) {
                //variable char para recoger cada caracter tecleado
                char c = e.getKeyChar();
                if(!Character.isDigit(c)){
                   e.consume();//si no es un dígito se borra del JTextField
                   if(Character.isLetter(c)){//ponemos esta condición para que no salte la pantalla de error si borramos un dígito
                     Toolkit.getDefaultToolkit().beep();   
                     JOptionPane.showMessageDialog(null, "Solo se pueden introducir números", "Error entrada de datos", JOptionPane.ERROR_MESSAGE);
                   }  
                }
            }
            
        });
        
    }
    
    //Método para controlar el número de caracteres introducidos
    public void limiteCaracteres(JTextField campo, int numCaracteres){
        //ponemos el JTextfield a la escucha para controlar lo que se introduce
        campo.addKeyListener(new KeyAdapter(){
        //clase interna anónima que controla la longitud de los campos
            @Override
            public void keyTyped(KeyEvent e) {
                //variable char para recoger cada caracter tecleado
                char c = e.getKeyChar();
                int tamano = campo.getText().length();
                if(tamano>=numCaracteres){
                    Toolkit.getDefaultToolkit().beep();
                    e.consume();//si la longitud es mayor se borra
                    JOptionPane.showMessageDialog(null, "número de dígitos excesivo", "Error entrada de datos", JOptionPane.ERROR_MESSAGE);  
                }
            }
        }); 
    }
    //Método para controlar que el saldo es de tipo double
    public void soloDouble(JTextField campo){
        
        campo.addKeyListener(new KeyAdapter(){
        //clase interna anónima que controla la longitud de los campos
            @Override
            public void keyTyped(KeyEvent e) {
                //variable char para recoger cada caracter tecleado
                char c = e.getKeyChar();
                if(!Character.isDigit(c) & c!='.'){
                    Toolkit.getDefaultToolkit().beep();
                    e.consume();//si es un dígito se borra del JTextField
                    if(Character.isLetter(c))//ponemos esta condición para que no salte la pantalla de error si borramos un dígito
                      JOptionPane.showMessageDialog(null, "Solo acepta un número\ncon o sin decimales", "Error entrada de datos", JOptionPane.ERROR_MESSAGE);
                              
                }
            }
        });
    }
}
