
package Aplicacion;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 * Clase que permite introducir los datos de un cliente 
 * @author Ana I. Iglesias Martínez
 */
public class LaminaDatosCliente extends JPanel{
    
    //campos de clase 
    private JTextField nombreTitular, apel1Titular, apel2Titular, 
                       dia, mes, anno;
    private ControlarEntradas control = new ControlarEntradas();
    
    //Constructor
    public LaminaDatosCliente(){
        //damos disposición y personalizamos la lámina    
        setLayout(new GridLayout(3,0));
        //setBackground(Color.GRAY);
        setBorder(javax.swing.BorderFactory.createCompoundBorder(new EmptyBorder(10,10,0,10), 
               new TitledBorder(new EtchedBorder(),"Datos cliente",
               TitledBorder.DEFAULT_JUSTIFICATION,
               TitledBorder.DEFAULT_POSITION,
               new Font(Font.DIALOG,Font.BOLD,14),
               Color.ORANGE)));
        
        //Creamos la lámina para el nombre
        JPanel panelNombre = new JPanel();
        panelNombre.setLayout(new FlowLayout(FlowLayout.LEFT));
        nombreTitular = new JTextField(20);
        panelNombre.add(new JLabel("               Nombre: "));
        panelNombre.add(nombreTitular);
        //agregamos la lámina nombre
        add(panelNombre);
        
        //Creamos una lámina para los apellidos
        JPanel panelApellidos = new JPanel();
        //damos disposición a esta lámina
        panelApellidos.setLayout(new FlowLayout(FlowLayout.LEFT));
        //panelApellidos.setBackground(Color.GRAY);
        //creamos y agregamos los componentes a la lámina Apellidos
        panelApellidos.add(new JLabel("             Apellidos: "));
        apel1Titular = new JTextField(13);
        apel2Titular = new JTextField(13);
        panelApellidos.add(apel1Titular);
        panelApellidos.add(apel2Titular);    
        
        //aquí agragemos la lámina apellidos
        add(panelApellidos);
        
        //Creamos una lámina para la fecha 
        JPanel panelFecha = new JPanel();
        //damos disposicion a esta lámina
        panelFecha.setLayout(new FlowLayout(FlowLayout.LEFT));
        //panelFecha.setBackground(Color.GRAY);;
        //creamos los componentes de la lamina Fecha
        dia = new JTextField(2);
        mes = new JTextField(2);
        anno = new JTextField(4);
        panelFecha.add(new JLabel("     F. nacimiento: "));
        //agregamos los JTextField a la lámina fecha
        panelFecha.add(dia);
        panelFecha.add(new JLabel("/"));
        panelFecha.add(mes);
        panelFecha.add(new JLabel("/"));
        panelFecha.add(anno);
        
        //aquí agragamos la lámina fecha
        add(panelFecha);
            
        //controlamos la entrada de datos según el atributo y sus característcas
        control.soloLetras(nombreTitular);
        control.soloLetras(apel1Titular);
        control.soloLetras(apel2Titular);
        control.soloNumeros(dia);
        control.limiteCaracteres(dia, 2);
        control.soloNumeros(mes);
        control.limiteCaracteres(mes, 2);
        control.soloNumeros(anno);
        control.limiteCaracteres(anno, 4);
        
            
    }
    //Métodos setter utilizados para limpiar los campos de  los JtextFields
    public void estableceNombreTitular(String nombre){
         nombreTitular.setText(nombre);
    }
    public void estableceApel1Titular(String apel1){
         apel1Titular.setText(apel1);
    }
    public void estableceApel2Titular(String apel2){
         apel2Titular.setText(apel2);
    }
    public void estableceDiaNac(String diaNac){
         dia.setText(diaNac);
    }
    public void estableceMesNac(String mesNac){
         mes.setText(mesNac);
    }
    public void estableceAnnoNac(String annoNac){
         anno.setText(annoNac);
    }
    
    //Métodos getter para obtener los datos de los JTextFields
    public String dameNombreTitular(){
        return nombreTitular.getText();
    }
    public String damePrimerApellido(){
        return apel1Titular.getText();
    }
    public String dameSegundoApellido(){
        return apel2Titular.getText();
    }
    public String dameDiaNac(){
        return dia.getText();
    }
    public String dameMesNac(){
        return mes.getText();
    }
    public String dameAnnoNac(){
        return anno.getText();
    }
    public String dameFechaNac(){
        return dia.getText()+"/"+mes.getText()+"/"+anno.getText();
    }
}
