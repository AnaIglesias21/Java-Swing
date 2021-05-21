
package Aplicacion;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import static Aplicacion.AplicacionCuentaBancaria.listaCuentas;

/**
 * clase que crea y gestiona  las opciones del menú mediante botones
 * @author Ana I. Iglesias Martínez
 */
//
class LaminaPrincipal extends JPanel{
    
    //campos de clase para los botones que configuran el menú
    private JButton abrirCuenta, listarCuentas, datosCuenta, ingresar, reintegro, consultaSaldo;
   
    public LaminaPrincipal(){
        
        //indicamos la disposición de los componetes de la lámina
        this.setLayout(new GridLayout(2,3));
        
        //Instanciamos la clase AccionRealizar
        AccionRealizar accion = new AccionRealizar();
        //Creamos, añadimos y ponemos los botones a la escucha mediante el metodo ponerBoton
        ponerBoton("Abrir cuenta", accion);
        ponerBoton("Listar cuentas", accion);
        ponerBoton("Datos cuenta", accion);
        ponerBoton("Ingresar", accion);
        ponerBoton("Reintegro", accion);
        ponerBoton("Consultar Saldo", accion);
        
    }
    
    /*método para crear, añadir  y personalizar la pariencia de los botones cuendo el rtón
      pase sobre ello, es private porque es una clase
      interna a la que solo debe tener acceso la clase LaminaPrincipal
    */
   
    private void ponerBoton(String titulo, ActionListener oyente){
        
        JButton boton = new JButton(titulo);
        
        boton.addActionListener(oyente);
        boton.setBackground(Color.DARK_GRAY);
        boton.setBorder(new LineBorder(Color.WHITE));
        boton.setForeground(Color.YELLOW);
        boton.setFont(new Font("MONOSPACED",Font.PLAIN,16));
        
        /*Uso la clase adaptadora para sosbreescribir solo los métods que me interesan
          utilizando una clase interna anónima*/
        
        boton.addMouseListener(new MouseAdapter() {
            
            //Sobreescribo los métodos necesarios para mostrar la apariencia de los botones del menú
            @Override
            public void mouseEntered(MouseEvent e) {
            
                boton.setFont(new Font("Arial",Font.BOLD,16));
                boton.setBackground(new Color(245,252,114));
                boton.setBorder(new LineBorder(Color.DARK_GRAY));
                boton.setForeground(Color.BLACK);
                            
            }
        
            @Override
            public void mouseExited(MouseEvent e) {
                boton.setFont(new Font("MONOSPACED",Font.PLAIN,16));
                boton.setBackground(Color.DARK_GRAY);
                boton.setBorder(new LineBorder(Color.WHITE));
		boton.setForeground(new Color(245,252,114));
            }
        });
        
        add(boton);
    } 
    
    
    //clase  interna para dar funcionalidad a los botones y gestionar cada una de las opciones del menú
    private class AccionRealizar implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            
            String opcion = e.getActionCommand();
        
            switch (opcion) {
                
                case "Abrir cuenta":
                    
                    //Creamos una nueva venta para crear una nueva cuenta
                    MarcoAbrirCuenta miMarcoCuenta = new MarcoAbrirCuenta();
                    //lo hacemos visible
                    miMarcoCuenta.setVisible(true);
                    //lo cerramos cuando se pilse la x del marco
                    miMarcoCuenta.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    break;
                    
                case "Listar cuentas":
                    
                    if (listaCuentas.size()>0){
                        
                      //Creamos una nueva ventana para consultar las cuentas
                      MarcoListarCuentas miMarcoListado = new MarcoListarCuentas();
                      miMarcoListado. setVisible(true);
                      miMarcoListado.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                     
                    }else{
                       Toolkit.getDefaultToolkit().beep(); 
                       JOptionPane.showMessageDialog(null, "No hay ninguna cuenta registrada", "Cuentas",JOptionPane.INFORMATION_MESSAGE);  
                    }
                    break;
                    
                case "Datos cuenta":
                    
                    if (listaCuentas.size()>0){
                        
                       //Creamos una nueva ventana para consultar los datos de una cuenta
                       MarcoConsulta miConsulta = new MarcoConsulta();
                       miConsulta.setVisible(true);
                       miConsulta.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                     
                    }else{
                       Toolkit.getDefaultToolkit().beep(); 
                       JOptionPane.showMessageDialog(null, "No hay ninguna cuenta registrada", "Cuentas",JOptionPane.INFORMATION_MESSAGE);  
                    }
                    break;
                    
                case "Ingresar":
                   
                    if (listaCuentas.size()>0){
                        
                        //Creamos una nueva ventana para realizar un ingreso en una cuenta 
                       MarcoOperaciones miMarcoIngreso = new MarcoOperaciones("Ingresar ");
                       miMarcoIngreso.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                       
                    }else{
                       Toolkit.getDefaultToolkit().beep(); 
                       JOptionPane.showMessageDialog(null, "No hay ninguna cuenta registrada", "Cuentas",JOptionPane.INFORMATION_MESSAGE); 
                    }
                    break;
                    
                case "Reintegro":
                    
                    if (listaCuentas.size()>0){
                        
                       //Creamos una nueva ventana para realizar un reintegro en una cuenta
                       MarcoOperaciones miMarcoReintegro = new MarcoOperaciones("Reintegro ");
                       miMarcoReintegro.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                       
                    }else{
                      Toolkit.getDefaultToolkit().beep();  
                      JOptionPane.showMessageDialog(null, "No hay ninguna cuenta registrada", "Cuentas",JOptionPane.INFORMATION_MESSAGE);  
                    } 
                    break;
                      
                    
                case "Consultar Saldo":
                    
                    if (listaCuentas.size()>0){
                        
                       //Creamos una nueva ventana para consultar el saldo de una cuenta
                       MarcoOperaciones miMarcoSaldo = new MarcoOperaciones("Consulta saldo ");
                       miMarcoSaldo.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                       
                    }else{
                      Toolkit.getDefaultToolkit().beep();  
                      JOptionPane.showMessageDialog(null, "No hay ninguna cuenta registrada", "Cuentas",JOptionPane.INFORMATION_MESSAGE);
                    }
                    break;
                
            }
        }  
    }
}
