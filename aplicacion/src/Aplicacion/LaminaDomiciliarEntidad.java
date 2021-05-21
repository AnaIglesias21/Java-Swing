
package Aplicacion;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import static Aplicacion.AplicacionCuentaBancaria.misEntidades;


/**
 * Clase para crear los cheks para seleccionar las entidades domiciliadas asociadas a una cuenta
 * 
 * @author Ana I. Iglesias Martínez 
 */
public class LaminaDomiciliarEntidad extends JPanel{
    //estos lo pnomenos estáticos y públicos porque son los que se selecciona por defecto
    public static JCheckBox miCheck10,miCheck11,miCheck12;
    private JCheckBox miCheck20,miCheck21,miCheck22,miCheck30,miCheck31,miCheck32;
    //private Hashtable <String, String> seleccion = new Hashtable<String, String>();
    
    public LaminaDomiciliarEntidad(){
        Border bordeLamina2 = new TitledBorder(new EtchedBorder(),
                "Domiciliar",
                TitledBorder.DEFAULT_JUSTIFICATION,
                TitledBorder.DEFAULT_POSITION,
                new Font(Font.DIALOG,Font.BOLD,14),
                Color.ORANGE);
            
        this.setBorder(bordeLamina2);
        this.setBackground(Color.GRAY);
        this.setLayout(new GridLayout(3,3));
        
        //creamos los checks Box
            miCheck10 = new JCheckBox("Luz");
            miCheck10.setBackground(Color.GRAY);
            miCheck10.setFont(new Font("Italic",Font.PLAIN,12));
            miCheck10.setForeground(Color.LIGHT_GRAY);
            miCheck20= new JCheckBox("Seguro Local");
            miCheck20.setBackground(Color.GRAY);
            miCheck20.setFont(new Font("Italic",Font.PLAIN,12));
            miCheck20.setForeground(Color.LIGHT_GRAY);
            miCheck30= new JCheckBox("Proveedor1");
            miCheck30.setBackground(Color.GRAY);
            miCheck30.setFont(new Font("Italic",Font.PLAIN,12));
            miCheck30.setForeground(Color.LIGHT_GRAY);
            
            miCheck11 = new JCheckBox("Agua");
            miCheck11.setBackground(Color.GRAY);
            miCheck11.setFont(new Font("Italic",Font.PLAIN,12));
            miCheck11.setForeground(Color.LIGHT_GRAY);
            miCheck21 = new JCheckBox("Seguridad");
            miCheck21.setBackground(Color.GRAY);
            miCheck21.setFont(new Font("Italic",Font.PLAIN,12));
            miCheck21.setForeground(Color.LIGHT_GRAY);
            miCheck31 = new JCheckBox("Proveedor2");
            miCheck31.setBackground(Color.GRAY);
            miCheck31.setFont(new Font("Italic",Font.PLAIN,12));
            miCheck31.setForeground(Color.LIGHT_GRAY);
            
            miCheck12 = new JCheckBox("Teléfono");
            miCheck12.setBackground(Color.GRAY);
            miCheck12.setFont(new Font("Italic",Font.PLAIN,12));
            miCheck12.setForeground(Color.LIGHT_GRAY);
            miCheck22 = new JCheckBox("Empresa limpieza");
            miCheck22.setBackground(Color.GRAY);
            miCheck22.setFont(new Font("Italic",Font.PLAIN,12));
            miCheck22.setForeground(Color.LIGHT_GRAY);
            miCheck32 = new JCheckBox("Proveedor3");
            miCheck32.setBackground(Color.GRAY);
            miCheck32.setFont(new Font("Italic",Font.PLAIN,12));
            miCheck32.setForeground(Color.LIGHT_GRAY);
            
        // añadimos los checks a la lámina
            add(miCheck10);
            add(miCheck20);
            add(miCheck30);
            
            add(miCheck11);
            add(miCheck21);
            add(miCheck31);
            
            add(miCheck12);
            add(miCheck22);
            add(miCheck32);
        //ponemos cada check a la excucha
            miCheck10.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               misEntidades.put("01", miCheck10.getText());
               
            }
        });
            miCheck20.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
               misEntidades.put("02", miCheck20.getText());
               
            }
        });
            miCheck30.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
               misEntidades.put("03", miCheck30.getText());
               
            }
        });
            miCheck11.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
               misEntidades.put("04", miCheck11.getText());
               
            }
        });
            miCheck21.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
               misEntidades.put("05", miCheck21.getText());
               
            }
        });
            miCheck31.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
               misEntidades.put("06", miCheck31.getText());
               
            }
        });
            
            miCheck12.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
               misEntidades.put("07", miCheck12.getText());
               
            }
        });
            miCheck22.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
               misEntidades.put("08", miCheck22.getText());
               
            }
        });
            miCheck32.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
               misEntidades.put("09", miCheck32.getText());
               
            }
        });
            
        
    }
    
}
