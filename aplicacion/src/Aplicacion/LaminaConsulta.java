
package Aplicacion;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import static Aplicacion.AplicacionCuentaBancaria.listaCuentas;

/**
 * Clase que crea la lámina para visualizar los datos de uan cuenta bancaria
 * @author Ana I. Iglesias Martínez
 */
public class LaminaConsulta extends JPanel{
    
    private JTextField entidad, oficina, dc, numCuenta; 
    private ControlarEntradas control = new ControlarEntradas();
    private JButton buscar;
    
    public LaminaConsulta(){
        
        //asignamos la disposicion de la lámina
        setLayout(new BorderLayout());
        
        //creamos una lamina para buscar la cuenta 
        JPanel laminaBuscar = new JPanel();
        //le damos una disposición a la lámina
        laminaBuscar.setLayout(new FlowLayout());
        
        //creamos y agregamos los componentes a la lámina panelCuenta
        entidad = new JTextField(4);
        oficina = new JTextField(4);
        dc = new JTextField(2);
        numCuenta = new JTextField(10);
        buscar = new JButton("Buscar");
        //asignamos una descripción al boton buscar
        buscar.setToolTipText("Busca la cuenta bancaria");
       
        laminaBuscar.add(new JLabel("Nº de cuenta: "));
        laminaBuscar.add(entidad);
        laminaBuscar.add(oficina);
        laminaBuscar.add(dc);
        laminaBuscar.add(numCuenta);
       
        //controlamos la entrada de datos según las características de los atributos
        control.soloNumeros(entidad);
        control.limiteCaracteres(entidad, 4);
        control.soloNumeros(oficina);
        control.limiteCaracteres(oficina, 4);
        control.soloNumeros(dc);
        control.limiteCaracteres(dc, 2);
        control.soloNumeros(numCuenta);
        control.limiteCaracteres(numCuenta, 10);
       
        //ponemos el botón  a la escucha
        buscar.addActionListener(new ActionListener() {
            
            private String numeroCuenta, tipoCuenta, fechaApertura;
            private Double saldo;
            private boolean encontrado;
            private Persona miPersona;
            private LaminaEntidades miEntidad;//panel para visualiar las entidades
            private LaminaConsultaTitular miLaminaTitular;//panel para visualizar los datos del titular
            private LaminaConsultar miLamina;
            private JPanel lamina;
            private CuentaBancaria miCuenta;
               
            @Override
            public void actionPerformed(ActionEvent e) {
                
                numeroCuenta = entidad.getText()+oficina.getText()+dc.getText()+numCuenta.getText();
                
                if (numeroCuenta.length()<20 || !CuentaBancaria.comprobarDigito(Integer.parseInt(entidad.getText()), Integer.parseInt(oficina.getText()), Integer.parseInt(dc.getText()), Long.parseLong(numCuenta.getText()))){
                   JOptionPane.showMessageDialog(null,"Número de cuenta erróneo.", "Cuenta", JOptionPane.ERROR_MESSAGE);
                   try{
                       //limpiamos el panel de datos anteriores
                       lamina.removeAll();
                       lamina.updateUI();
                    }catch (Exception ex){}
                }else{
                //buscamos el número de cuenta
                  int indice=0;
                  while(indice<listaCuentas.size() && !encontrado){
                       
                    if (listaCuentas.get(indice).dameCuenta().equals(numeroCuenta)){
                        encontrado = true;  
                        //creamos una lamina contenedora para los datos personales, datos bancarios y las entidades
                        lamina = new JPanel();
                        lamina.setLayout(new GridLayout(3,0));
                        
                        //creamos el objeto tipo Persona
                        miPersona = listaCuentas.get(indice).dameTitular();
                        //creamos la Lamina para visualizar los datos
                        miLaminaTitular = new LaminaConsultaTitular(miPersona);
                        //añadimos esta lamina a la lamina contenedora
                        lamina.add(miLaminaTitular);
                        
                        //creamos el objeto CuentaBancaria
                        miCuenta = listaCuentas.get(indice);
                        //dependiendo del tipo de Cuenta Bancaria creamos la lámina correspondiente 
                        if (miCuenta instanceof CuentaAhorro){
                            
                           tipoCuenta="Ahorro";
                           miLamina = new LaminaConsultar(tipoCuenta,miCuenta);
                           lamina.add(miLamina);
                           
                          
                        }else if (miCuenta instanceof CuentaPersonal){ 
                            
                            tipoCuenta="Corriente Personal";
                            miLamina = new LaminaConsultar(tipoCuenta,miCuenta);
                            lamina.add(miLamina);
                            //creamos la lámina para visualizar las entidades
                            miEntidad = new LaminaEntidades(tipoCuenta,miCuenta);
                            lamina.add(miEntidad);
                              
                            
                          }else{                    

                            tipoCuenta="Corriente Empresa";
                            miLamina = new LaminaConsultar(tipoCuenta,miCuenta);
                            lamina.add(miLamina);
                            //creamos la lámina para visualizar las entidades
                            miEntidad = new LaminaEntidades(tipoCuenta,miCuenta);
                            lamina.add(miEntidad);
                            
                          }
                        //agragasmos la lámina a la zoba central
                        add(lamina, BorderLayout.CENTER);
                        lamina.updateUI();
                        
                    }
                     
                    indice++;
                  }
                  
                  if(!encontrado){
                    JOptionPane.showMessageDialog(null,"Número de cuenta no encontrado.", "Cuenta", JOptionPane.ERROR_MESSAGE);
                    //limpiamos el panel
                    try{
                       lamina.removeAll();
                       lamina.updateUI();
                    }catch (Exception ex){}   
                  }
                  encontrado = false;
                }  
            }
            
        });
        
        //añadimos el botón buscar 
        laminaBuscar.add(buscar);
        //Colocamos la laminaBuscar en la zona norte del la laminaConsulta
        add(laminaBuscar, BorderLayout.NORTH);
        
    }    
}
