
package Aplicacion;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.regex.Pattern;
import javax.swing.*;
import javax.swing.border.*;
import static Aplicacion.AplicacionCuentaBancaria.*;
import static Aplicacion.LaminaDomiciliarEntidad.miCheck10;
import static Aplicacion.LaminaDomiciliarEntidad.miCheck11;
import static Aplicacion.LaminaDomiciliarEntidad.miCheck12;
/**
 *
 * @author Ana I. Iglesias Martínez
 */
public class MarcoDatos extends JFrame{
    
    private JLabel intDesc, desc,lblComision;
    private JTextField interes, descubierto,comision;
    private ControlarEntradas control = new ControlarEntradas();
    private LaminaDomiciliarEntidad domiciliada;
    private Border bordeLamina;
    public static String interesDesc, cantDescubierto,interesComision;
    
    public MarcoDatos(String texto){
        //creamo el hashtable para guardar los pagos domiciliados
        misEntidades = new Hashtable<String,String>();  
        setVisible(true);
        setTitle(texto);
        setSize(450,350);
        setLocationRelativeTo(null);
        setResizable(false);//hacemos que no se pueda maximizar el marco
        //aplicamos la imagen a nuestro marco para personalizar el icono de la ventana
        setIconImage(miIcono);
        //LaminaEmpresa miLamina = new LaminaEmpresa();
        JPanel laminaDatos = new JPanel();
        //indicamos la disposición de la lámina
        laminaDatos.setLayout(new BorderLayout());
        //aplicamos color de fondo
        laminaDatos.setBackground(Color.GRAY);
        //según el tipo de cuenta creamos unas lámina u otras
        switch (texto){
            case "Cuenta personal":
                JPanel panelDatosPersonal = new JPanel();
                bordeLamina = new TitledBorder(new EtchedBorder(),
                        texto,
                        TitledBorder.DEFAULT_JUSTIFICATION,
                        TitledBorder.DEFAULT_POSITION,
                        new Font(Font.DIALOG,Font.BOLD,14),
                        Color.ORANGE);
            
                panelDatosPersonal.setBorder(bordeLamina);
                panelDatosPersonal.setBackground(Color.GRAY);
                panelDatosPersonal.setLayout(new GridLayout(1,0));
                //panel para el interes
                JPanel panelComision = new JPanel();
                panelComision.setLayout(new FlowLayout(FlowLayout.LEFT));
                panelComision.setBackground(Color.GRAY);
                lblComision = new JLabel("Interés comisión: ");
                lblComision.setForeground(Color.LIGHT_GRAY);
                lblComision.setFont(new Font(Font.DIALOG_INPUT,Font.BOLD,12));
                comision = new JTextField(5);
                panelComision.add(lblComision);
                panelComision.add(comision);
                panelDatosPersonal.add(panelComision); 
                //controlamos la entrada de datos para que sea correcta
                control.soloDouble(comision);
                control.limiteCaracteres(comision, 5);
                laminaDatos.add(panelDatosPersonal, BorderLayout.NORTH);
                
            break;
            
            case "Cuenta empresa":
                //creamos otra lámina para los datos
                JPanel panelEmpresa = new JPanel();
                //definimos un borde
                bordeLamina = new TitledBorder(new EtchedBorder(),
                    texto,
                    TitledBorder.DEFAULT_JUSTIFICATION,
                    TitledBorder.DEFAULT_POSITION,
                    new Font(Font.DIALOG,Font.BOLD,14),
                    Color.ORANGE);
            
                panelEmpresa.setBorder(bordeLamina);
                panelEmpresa.setBackground(Color.GRAY);
                panelEmpresa.setLayout(new GridLayout(2,0));
      
                //panel para el interes
                JPanel panelInteres = new JPanel();
                panelInteres.setLayout(new FlowLayout(FlowLayout.LEFT));
                panelInteres.setBackground(Color.GRAY);
                intDesc = new JLabel("Interés descubierto: ");
                intDesc.setForeground(Color.LIGHT_GRAY);
                intDesc.setFont(new Font(Font.DIALOG_INPUT,Font.BOLD,12));
                interes = new JTextField(5);
                panelInteres.add(intDesc);
                panelInteres.add(interes);
      
               //controlamos la entrada de datos para que sea correcta
               control.soloDouble(interes);
               control.limiteCaracteres(interes, 5);
            
               //creamos una lámina par la cantidad de descubierto
               JPanel panelDescubierto = new JPanel();
               panelDescubierto.setLayout(new FlowLayout(FlowLayout.LEFT));
               panelDescubierto.setBackground(Color.GRAY);
               //Creamos la etiqueta y el campo y lo añadimos a la lámina
               desc = new JLabel("Descubierto: ");
               desc.setForeground(Color.LIGHT_GRAY);
               desc.setFont(new Font(Font.DIALOG_INPUT,Font.BOLD,12));
               descubierto = new JTextField(5);
               panelDescubierto.add(desc);
               panelDescubierto.add(descubierto);
               //controlamos la entrada de datos
               control.soloDouble(descubierto);
               control.limiteCaracteres(descubierto, 10);
            
               //agragamos a la lámina panelEmpresa las láminas panelInteres y panelDescubierto    
               panelEmpresa.add(panelInteres);
               panelEmpresa.add(panelDescubierto);
               //añadimos la lámina panelEmpresa a la zona norte
               laminaDatos.add(panelEmpresa, BorderLayout.NORTH);
               
            break;    
        }
  
        //Creamos la lámina para elegir las entidades domiciliadas
        domiciliada = new LaminaDomiciliarEntidad();
        //agregamos la lámina panelDomiciliar a la zona centro
        laminaDatos.add(domiciliada, BorderLayout.CENTER);
        
        //Creamos una lámina para los botones
        JPanel panelBotonDatos = new JPanel();
        panelBotonDatos.setLayout(new FlowLayout());
        panelBotonDatos.setBackground(Color.GRAY);
        //creamos los botones y los añadimos a la lámina botonDatos
        JButton btnAceptar = new JButton("Aceptar");
        JButton btnAsignar = new JButton("Datos por defecto");
        //describimos las acciones de los botones como ayuda 
        btnAceptar.setToolTipText("Asignar los valores vistos en pantalla");
        btnAsignar.setToolTipText("Asignar los valores por defecto");
        //añadimos los botones a la lámina panelBotonDatos
        panelBotonDatos.add(btnAceptar);
        panelBotonDatos.add(btnAsignar);      
        
        //agragamos la lámina panelBotonDatos a la zona sur
        laminaDatos.add(panelBotonDatos, BorderLayout.SOUTH);
        laminaDatos.updateUI();
        //agregamos la laminaDatos al frame
        this.add(laminaDatos);
        
        //ponemos el boton Aceptar a la escucha creando una clase interna anónima
        btnAceptar.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                //según el tipo e cuenta se introduciran unos datos u otros
                switch (texto){
                    case "Cuenta empresa":
                        
                    if (interes.getText().isEmpty() & descubierto.getText().isEmpty()){
                       JOptionPane.showMessageDialog(null, 
                        "Se debe introducir el interés y la cantidad de descubierto\n"
                             + "o pulsar en Datos por defecto",
                        texto, JOptionPane.INFORMATION_MESSAGE);
                       
                    }else if (descubierto.getText().isEmpty() ){
                      JOptionPane.showMessageDialog(null, 
                      "Se debe introducir la cantidad de descubierto",
                      texto, JOptionPane.INFORMATION_MESSAGE);
                    }else if (!Recursos.isDouble(descubierto.getText())){
                        JOptionPane.showMessageDialog(null, "Cantidad de descubierto errónea,\n"
                              + "debe ser una cantidad mayor de 0 en formato '9999999.99'\n",
                           "C. empresa -> Cantidad Descubierto", JOptionPane.ERROR_MESSAGE);
                        
                     }else if(interes.getText().isEmpty()){
                        JOptionPane.showMessageDialog(null, 
                         "Se debe introducir el interés de descubierto",
                           "Cuenta Empresa ", JOptionPane.INFORMATION_MESSAGE);
                         }else if (!Pattern.matches("^\\d{0,2}\\.{0,1}\\d{0,2}", interes.getText())){
                           JOptionPane.showMessageDialog(null, "Interés de descubierto erróneo\n"
                             + "El formato es '99.99', '99' , '.99'\n",
                              "C. Empresa -> Interés descubierto", JOptionPane.ERROR_MESSAGE);
                }
              
                interesDesc = interes.getText();
                cantDescubierto = descubierto.getText();
                if (!interes.getText().isEmpty() & !descubierto.getText().isEmpty())
                   setVisible(false);
                break;
                        
                case "Cuenta personal":
                        
                    if(comision.getText().isEmpty())
                      JOptionPane.showMessageDialog(null,
                        "Se debe introducir el interés de comisión",
                        texto, JOptionPane.INFORMATION_MESSAGE); 
                    else //comprobamos que los datos son válidos utilizando 
                        if(!Pattern.matches("^\\d{0,2}\\.{0,1}\\d{0,2}", comision.getText()))
                        
                          JOptionPane.showMessageDialog(null, "Interés de comisión erróneo\n"
                          + "El formato es '99.99', '99' , '.99'\n",
                          "C. Personal -> Interés Comisión", JOptionPane.ERROR_MESSAGE);
                        else
                           interesComision = comision.getText();
                    
                    if (!comision.getText().isEmpty())
                       setVisible(false);     
                    break;
                        
                }
                
                // si no se han elegido entidades para domiciliar se asignan por defecto
                if (misEntidades.isEmpty()){
                    misEntidades.put("10", "Luz");
                    misEntidades.put("11", "Agua");
                    misEntidades.put("12", "Teléfono");
                    //marcamos las entidades domiciliadas por defecto
                    miCheck10.setSelected(true);
                    miCheck11.setSelected(true);
                    miCheck12.setSelected(true);
                }
                
            }
        });
    
        //ponemos el boton Asignar a la escucha creando una clase interna anónima
        btnAsignar.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                
                switch (texto){
                    
                    case "Cuenta empresa":
                      //asignamos los valores por defecto 
                      interes.setText("1.3");
                      descubierto.setText("2000"); 
                      interesDesc = "1.3";
                      cantDescubierto = "2000";
                      
                    break;
                    
                 case "Cuenta personal":
                     
                      comision.setText("0.75");
                      interesComision = "0.75";
                      
                     break;
                }                    
                // si no se han elegido entidades para domiciliar se asignan por defecto
                if (misEntidades.isEmpty()){
                    misEntidades.put("10", "Luz");
                    misEntidades.put("11", "Agua");
                    misEntidades.put("12", "Teléfono");
                     //marcamos las entidades domiciliadas por defecto
                    miCheck10.setSelected(true);
                    miCheck11.setSelected(true);
                    miCheck12.setSelected(true);
                }
                           
            }    
                  
        });
        
    }
  
}
