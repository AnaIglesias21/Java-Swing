
package Aplicacion;

import java.awt.*;
import java.awt.event.*;
import java.util.Iterator;
import javax.swing.*;
import static Aplicacion.AplicacionCuentaBancaria.*;
import static Aplicacion.MarcoDatos.*;


/**
 * clase que contiene y gestiona los componentes necesarios para la 
 * introducción de datos y la cración de una nueva cuenta bancaria
 * @author Ana I. Iglesias Martínez
 */

class LaminaAbrirCuenta extends JPanel{
  
    private JButton aceptar, limpiar,generar;
    private ControlarEntradas control;//permite que los datos sean válidos 
    private String numeroCuenta;
    private Persona titular;
    private CuentaAhorro nuevaCuentaAhorro;
    private CuentaPersonal nuevaCuentaPersonal;
    private CuentaEmpresa nuevaCuentaEmpresa;
    private CuentaBancaria nuevaCuenta;
    private LaminaDatosCliente cliente;
    private LaminaDatosBancarios cuenta;
    private String nomCliente, apel1Cliente,apel2Cliente, fechNacCliente;
    private boolean repetido, datosCorrectos;
    private Double saldo=0.0;
    private Iterator <CuentaBancaria> it;
    
    public LaminaAbrirCuenta(){
        //indicamos la disposición de la lámina
        setLayout(new BorderLayout());
        setBackground(Color.GRAY);
        
        control = new ControlarEntradas();
        //creamos la lámina para introducir los datos personales del cliente
        cliente = new LaminaDatosCliente();
        //creamos la lámina para introducir los datos bancarios 
        cuenta = new LaminaDatosBancarios();
        
        //agragamos la lámina cliente a la zona norte
        add(cliente, BorderLayout.NORTH);
        cliente.updateUI();
        //agregamos la lámina cuenta a la zona centro
        add(cuenta, BorderLayout.CENTER);
        cuenta.updateUI();
        //creamos la lámina que contiene los botones 
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout());
        aceptar = new JButton("Crear cuenta");
        limpiar = new JButton("Limpiar campos");
        generar = new JButton("Generar cuentas");
        //asignamos un adescripción a los botones para que pasar por encima informen de lo que hace cada una
        aceptar.setToolTipText("Crea una cuenta bancaria con los datos introducidos");
        limpiar.setToolTipText("Limpia los campos para introducir nuevos datos");
        generar.setToolTipText("Crea cuentas bancarias sin introducir datos");
        if (generadas)
            generar.setEnabled(!generadas);
        //ponemos el boton crear cuenta a la escucha
        aceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               
                //comprobamos que los datos sean correctos
                if (cliente.dameNombreTitular().isEmpty() & 
                    cliente.damePrimerApellido().isEmpty() &
                    cliente.dameSegundoApellido().isEmpty() &
                    cliente.dameDiaNac().isEmpty() & 
                    cliente.dameMesNac().isEmpty() & cliente.dameAnnoNac().isEmpty()){
                    JOptionPane.showMessageDialog(null,"Los datos personales del cliente no"
                            + " pueden esta vacíos","Error datos",JOptionPane.ERROR_MESSAGE );
                }else if (cliente.dameNombreTitular().isEmpty()){
                    JOptionPane.showMessageDialog(null,"El nombre del cliente no"
                            + " puede estar vacío","Error datos",JOptionPane.ERROR_MESSAGE );
                }else if (cliente.damePrimerApellido().isEmpty() || cliente.dameSegundoApellido().isEmpty()){
                    JOptionPane.showMessageDialog(null,"Los apellidos del cliente no"
                            + " pueden estar vacíos","Error datos",JOptionPane.ERROR_MESSAGE );
                }else if ((cliente.dameDiaNac().isEmpty() || cliente.dameMesNac().isEmpty() 
                        || cliente.dameAnnoNac().isEmpty()) || !Recursos.validarFecha(cliente.dameFechaNac()) ){
                    JOptionPane.showMessageDialog(null,"La fecha de nacimiento es erróna"
                            ,"Error datos",JOptionPane.ERROR_MESSAGE );
                }else{//si los datos son correctos creamos el objeto de tipo persona
                   nomCliente = cliente.dameNombreTitular();
                   apel1Cliente = cliente.damePrimerApellido();
                   apel2Cliente = cliente.dameSegundoApellido();
                   fechNacCliente = cliente.dameFechaNac(); 
                   titular = new Persona(nomCliente,apel1Cliente,apel2Cliente ,
                      fechNacCliente);
                   datosCorrectos = true;
                }
                
                //comprobamos que los datos Bancarios son correctos
                numeroCuenta = cuenta.dameCuentaCompleta();
                if (numeroCuenta.length()<20 || !CuentaBancaria.comprobarDigito(Integer.parseInt(numeroCuenta.substring(0, 4)),
                 Integer.parseInt(numeroCuenta.substring(4, 8)),Integer.parseInt(numeroCuenta.substring(8, 10)),
                  Long.parseLong(numeroCuenta.substring(10, 20)))){
                    
                    JOptionPane.showMessageDialog(null,"Número de cuenta erróneo.", "Cuenta", JOptionPane.ERROR_MESSAGE);
                    
                }else{
                    
                    if (cuenta.dameSaldoCuenta().isEmpty()|| cuenta.dameSaldoCuenta().equals("0.0")){
                        
                       JOptionPane.showMessageDialog(null,"Saldo erróneo.", "Cuenta", JOptionPane.ERROR_MESSAGE);
                       
                    }else{
                        //según el tipo de cuenta elegido creamos el objeto 
                        switch (cuenta.dameTipoCuenta()){
                            case "ahorro":
                                nuevaCuentaAhorro = new CuentaAhorro(cuenta.dameCuentaCompleta(),
                                         titular,Double.parseDouble(cuenta.dameSaldoCuenta()));
                                nuevaCuenta = nuevaCuentaAhorro;
                            break;
                            case "personal":
                                nuevaCuentaPersonal = new CuentaPersonal(cuenta.dameCuentaCompleta(), 
                                           titular,Double.parseDouble(cuenta.dameSaldoCuenta()),
                                Double.parseDouble(interesComision),misEntidades); 
                                nuevaCuenta = nuevaCuentaPersonal;
                            break;
                            case "empresa":
                                nuevaCuentaEmpresa = new CuentaEmpresa(cuenta.dameCuentaCompleta(), 
                                        titular,Double.parseDouble(cuenta.dameSaldoCuenta()),
                                Double.parseDouble(interesDesc),Double.parseDouble(cantDescubierto),misEntidades); 
                                nuevaCuenta = nuevaCuentaEmpresa;
                            break;    
                        }  
                        
                        //comprobamos que el número de cuenta no esté duplicado mediante un iterator
                        it = listaCuentas.iterator();
                        while(it.hasNext() && !repetido){
                            
                          if (it.next().dameCuenta().equals(numeroCuenta))
                           repetido = true;
                          
                        }
                      
                        if (!repetido && datosCorrectos){
                          
                           listaCuentas.add(nuevaCuenta);
                           cliente.estableceNombreTitular("");
                           cliente.estableceApel1Titular("");
                           cliente.estableceApel2Titular("");
                           cliente.estableceDiaNac("");
                           cliente.estableceMesNac("");
                           cliente.estableceAnnoNac("");
                           cuenta.estableceDcCuenta("");
                           cuenta.estableceEntidadCuenta("");
                           cuenta.estableceOficionaCuenta("");
                           cuenta.estableceNumCuenta("");
                           cuenta.estableceSaldoCuenta("");
                           cuenta.estableceFechaCuenta();
                           datosCorrectos = false;
                        }else if (repetido){
                          
                            JOptionPane.showMessageDialog(null,"Número de cuenta ya existente.", "Cuenta", JOptionPane.ERROR_MESSAGE); 
                            repetido = false;
                        }
                    }
                }    
            }
        });
        
        //ponemos el boton limpiar a la escucha
        limpiar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                cliente.estableceNombreTitular("");
                cliente.estableceApel1Titular("");
                cliente.estableceApel2Titular("");
                cliente.estableceDiaNac("");
                cliente.estableceMesNac("");
                cliente.estableceAnnoNac("");
                cuenta.estableceDcCuenta("");
                cuenta.estableceEntidadCuenta("");
                cuenta.estableceOficionaCuenta("");
                cuenta.estableceNumCuenta("");
                cuenta.estableceSaldoCuenta("");
                cuenta.estableceFechaCuenta();
               
            }
        });
        
        //ponemos el boton generar a la escucha
        generar.addActionListener(new ActionListener(){
            
            @Override
            public void actionPerformed(ActionEvent e) {
                Recursos.generandoCuentas();
                generadas = true;
                JOptionPane.showMessageDialog(null, "Creadas 20 cuentas Bancarias", "Creacción de cuentas", JOptionPane.INFORMATION_MESSAGE);
                generar.setEnabled(!generadas);
            }

        });
        
        //añadimos los botones a la lámina
        panelBotones.add(aceptar);
        panelBotones.add(limpiar);
        panelBotones.add(generar);
        add(panelBotones, BorderLayout.SOUTH);
        this.updateUI();
    }

}
